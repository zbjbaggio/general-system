package org.general.system.common.service.impl.system;

import lombok.extern.slf4j.Slf4j;
import org.general.system.common.constants.SystemUserStatusContant;
import org.general.system.common.data.dto.SystemUserDTO;
import org.general.system.common.data.entity.system.SystemUser;
import org.general.system.common.data.vo.system.MenuAndButtonVO;
import org.general.system.common.data.vo.system.PermissionVO;
import org.general.system.common.data.vo.system.SystemUserVO;
import org.general.system.common.enmus.ErrorInfo;
import org.general.system.common.exception.PrivateException;
import org.general.system.common.mapper.system.SystemUserMapper;
import org.general.system.common.service.RedisService;
import org.general.system.common.service.system.SystemPermissionService;
import org.general.system.common.service.system.SystemUserService;
import org.general.system.common.utils.JwtUtil;
import org.general.system.common.utils.PasswordUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * 系统用户 服务层实现
 *
 * @author eason
 * @date 2020-03-06
 */
@Service
@Slf4j
public class SystemUserServiceImpl implements SystemUserService {

	@Autowired
	private SystemUserMapper systemUserMapper;

	@Autowired
	private SystemPermissionService systemPermissionService;

	@Autowired
	private RedisService redisService;

	/**
	 * 管理系统用户登录
	 *
	 * @param systemUserDTO 登录用户信息
	 * @return 系统用户
	 */
	@Override
	public SystemUserVO login(SystemUserDTO systemUserDTO) {
		//TODO 检验验证码

		SystemUser systemUser = systemUserMapper.getByUsername(systemUserDTO.getUsername());
		// 用户名不存在
		if (systemUser == null) {
			throw new PrivateException(ErrorInfo.LOGIN_ERROR);
		}
		// 检查用户密码 暂时使用 可优化
		String password = PasswordUtil.encrypt(systemUser.getUsername(), systemUserDTO.getPassword(), systemUser.getSolt());
		if (!systemUser.getPassword().equals(password)) {
			throw new PrivateException(ErrorInfo.LOGIN_ERROR);
		}
		SystemUserVO systemUserVO = new SystemUserVO();
		MenuAndButtonVO menu = systemPermissionService.getMenu(systemUser.getId());
		BeanUtils.copyProperties(menu, systemUserVO);
		List<PermissionVO> routerList = systemUserVO.getRouterList();
		PermissionVO permissionVO = routerList.get(0);
		PermissionVO permissionVO1 = permissionVO.getChildren().get(0);
		permissionVO1.setName("1111111111111");
		systemUserVO.setId(systemUser.getId());
		systemUserVO.setUsername(systemUser.getUsername());
		systemUserVO.setToken(JwtUtil.sign(systemUserVO.getUsername(), UUID.randomUUID().toString()));
		redisService.saveSystemLogin(systemUserVO);
		return systemUserVO;
	}

	/**
	 * 根据用户id查询拥有的权限
	 * @param userId 系统用户id
	 * @return 后端权限url
	 */
	@Override
	public Set<String> listPermissionsByUserId(Long userId) {
		return systemUserMapper.selectPermissionByUserId(userId);
	}

	/**
	 * 根据用户id修改用户状态
	 * @param userId 用户id
	 * @param freeze 状态 {@link SystemUserStatusContant}
	 */
	@Override
	public void updateStatus(Long userId, int freeze) {

	}

	/**
     * 查询系统用户信息
     *
     * @param id 系统用户ID
     * @return 系统用户信息
     */
    @Override
	public SystemUser selectSystemUserById(Long id) {
	    return systemUserMapper.selectSystemUserById(id);
	}

	/**
     * 查询系统用户列表
     *
     * @param systemUser 系统用户信息
     * @return 系统用户集合
     */
	@Override
	public List<SystemUser> selectSystemUserList(SystemUser systemUser) {
	    return systemUserMapper.selectSystemUserList(systemUser);
	}

    /**
     * 新增系统用户
     *
     * @param systemUser 系统用户信息
     * @return 返回带有id的用户信息
     */
	@Override
	public SystemUser insertSystemUser(SystemUser systemUser) {
		int count = systemUserMapper.insertSystemUser(systemUser);
		if (count != 1) {
			log.info("用户保存失败！用户信息：{}", systemUser);
			throw new PrivateException(ErrorInfo.ADD_ERROR);
		}
		return systemUser;
	}

	/**
     * 修改系统用户
     *
     * @param systemUser 系统用户信息
     * @return 结果
     */
	@Override
	public int updateSystemUser(SystemUser systemUser) {
	    return systemUserMapper.updateSystemUser(systemUser);
	}

	/**
     * 删除系统用户对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSystemUserByIds(Long[] ids) {
		return systemUserMapper.deleteSystemUserByIds(ids);
	}

}
