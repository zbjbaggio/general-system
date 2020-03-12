package org.general.system.common.service.impl.system;

import lombok.extern.slf4j.Slf4j;
import org.general.system.common.data.dto.SystemUserDTO;
import org.general.system.common.data.entity.system.SystemUser;
import org.general.system.common.data.vo.SystemUserVO;
import org.general.system.common.enmus.ErrorInfo;
import org.general.system.common.exception.PrivateException;
import org.general.system.common.mapper.system.SystemUserMapper;
import org.general.system.common.service.RedisService;
import org.general.system.common.service.system.SystemUserService;
import org.general.system.common.utils.JwtUtil;
import org.general.system.common.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
		// TODO 权限 菜单

		SystemUserVO systemUserVO = new SystemUserVO();
		systemUserVO.setUsername(systemUser.getUsername());
		systemUserVO.setToken(JwtUtil.sign(systemUserVO.getUsername(), PasswordUtil.getSalt()));
		redisService.saveSystemLogin(systemUserVO);
		return systemUserVO;
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
     * @return 结果
     */
	@Override
	public int insertSystemUser(SystemUser systemUser) {
	    return systemUserMapper.insertSystemUser(systemUser);
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
