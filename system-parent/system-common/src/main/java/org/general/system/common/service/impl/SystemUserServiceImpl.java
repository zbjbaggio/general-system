package org.general.system.common.service.impl;

import java.util.List;

import org.general.system.common.mapper.SystemUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.general.system.common.data.entity.SystemUser;
import org.general.system.common.service.SystemUserService;

/**
 * 系统用户 服务层实现
 * 
 * @author eason
 * @date 2020-03-06
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

	@Autowired
	private SystemUserMapper systemUserMapper;

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
