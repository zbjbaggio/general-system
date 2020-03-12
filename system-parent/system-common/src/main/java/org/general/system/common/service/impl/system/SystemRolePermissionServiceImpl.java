package org.general.system.common.service.impl.system;

import org.general.system.common.data.entity.system.SystemRolePermission;
import org.general.system.common.enmus.ErrorInfo;
import org.general.system.common.exception.PrivateException;
import org.general.system.common.mapper.system.SystemRolePermissionMapper;
import org.general.system.common.service.system.SystemRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限 服务层实现
 * 
 * @author eason
 * @date 2020-03-05
 */
@Service
public class SystemRolePermissionServiceImpl implements SystemRolePermissionService {

	@Autowired
	private SystemRolePermissionMapper systemRolePermissionMapper;

	/**
     * 查询角色权限信息
     * 
     * @param id 角色权限ID
     * @return 角色权限信息
     */
    @Override
	public SystemRolePermission selectSystemRolePermissionById(Long id) {
	    return systemRolePermissionMapper.selectSystemRolePermissionById(id);
	}
	
	/**
     * 查询角色权限列表
     * 
     * @param systemRolePermission 角色权限信息
     * @return 角色权限集合
     */
	@Override
	public List<SystemRolePermission> selectSystemRolePermissionList(SystemRolePermission systemRolePermission) {
	    return systemRolePermissionMapper.selectSystemRolePermissionList(systemRolePermission);
	}
	
    /**
     * 新增角色权限
     * 
     * @param systemRolePermission 角色权限信息
     * @return 结果
     */
	@Override
	public SystemRolePermission insertSystemRolePermission(SystemRolePermission systemRolePermission) {
		int count = systemRolePermissionMapper.insertSystemRolePermission(systemRolePermission);
		if (count != 1) {
			throw new PrivateException(ErrorInfo.SAVE_ERROR);
		}
		return systemRolePermission;
	}
	
	/**
     * 修改角色权限
     * 
     * @param systemRolePermission 角色权限信息
     * @return 结果
     */
	@Override
	public int updateSystemRolePermission(SystemRolePermission systemRolePermission) {
	    return systemRolePermissionMapper.updateSystemRolePermission(systemRolePermission);
	}

	/**
     * 删除角色权限对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSystemRolePermissionByIds(Long[] ids) {
		return systemRolePermissionMapper.deleteSystemRolePermissionByIds(ids);
	}
	
}
