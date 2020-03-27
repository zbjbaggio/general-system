package org.general.system.admin.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.general.system.common.data.vo.system.SystemUserVO;
import org.general.system.common.service.RedisService;
import org.general.system.common.service.system.SystemUserService;
import org.general.system.common.utils.JwtUtil;
import org.general.system.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 用户登录鉴权和获取用户授权
 * @Author: Scott
 * @Date: 2019-4-23 8:13
 * @Version: 1.1
 */
@Component
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private RedisService redisService;

	@Autowired
	private SystemUserService systemUserService;

	/**
	 * 必须重写此方法，不然Shiro会报错
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}

	/**
     * 权限信息认证(包括角色以及权限)是用户访问controller的时候才进行验证(redis存储的此处权限信息)
	 * 触发检测用户权限时才会调用此方法，例如checkRole,checkPermission
     *
     * @param principals 身份信息
	 * @return AuthorizationInfo 权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if (principals != null) {
			SystemUserVO systemUserVO = (SystemUserVO) principals.getPrimaryPrincipal();
			info.addStringPermissions(systemUserService.listPermissionsByUserId(systemUserVO.getId()));
		}
		return info;
	}

	/**
     * 用户信息认证是在用户进行登录的时候进行验证(不存redis)
	 * 也就是说验证用户输入的账号和密码是否正确，错误抛出异常
	 *
	 * @param auth 用户登录的账号密码信息
	 * @return 返回封装了用户信息的 AuthenticationInfo 实例
     * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
		String token = (String) auth.getCredentials();
		if (StringUtil.isEmpty(token)) {
			throw new AuthenticationException("token为空!");
		}
		SystemUserVO systemUserVO = checkUserTokenIsEffect(token);
		return new SimpleAuthenticationInfo(systemUserVO, token, getName());
	}

	/**
	 * 校验token的有效性
	 *
	 * @param token
	 */
	private SystemUserVO checkUserTokenIsEffect(String token) throws AuthenticationException {
		// 解密获得username，用于和数据库进行对比
		String username = JwtUtil.getUsername(token);
		//redisService.test(token);
		SystemUserVO systemLogin = redisService.getSystemLogin(username);
		if (systemLogin == null) {
			throw new AuthenticationException("token超时，重新登录!");
		}
		if (!token.equals(systemLogin.getToken())) {
			throw new AuthenticationException("token不正确，重新登录!");
		}
		redisService.saveSystemLogin(systemLogin);
		return systemLogin;
	}

    /**
     * 清除当前用户的权限认证缓存
     *
     * @param principals 权限信息
     */
   /* @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }*/

}
