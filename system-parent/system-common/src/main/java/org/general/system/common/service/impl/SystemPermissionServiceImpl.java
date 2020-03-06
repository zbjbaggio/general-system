package org.general.system.common.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.general.system.common.mapper.SystemPermissionMapper;
import org.general.system.common.data.entity.system.SystemPermission;
import org.general.system.common.service.SystemPermissionService;

/**
 * 菜单权限 服务层实现
 * 
 * @author eason
 * @date 2020-03-06
 */
@Service
public class SystemPermissionServiceImpl implements SystemPermissionService {

	@Autowired
	private SystemPermissionMapper systemPermissionMapper;

	/**
     * 查询菜单权限信息
     * 
     * @param  id 菜单权限ID
     * @return 菜单权限信息
     */
    @Override
	public SystemPermission selectSystemPermissionById(Long id) {
	    return systemPermissionMapper.selectSystemPermissionById(id);
	}
	
	/**
     * 查询菜单权限列表
     * 
     * @param systemPermission 菜单权限信息
     * @return 菜单权限集合
     */
	@Override
	public List<SystemPermission> selectSystemPermissionList(SystemPermission systemPermission) {
	    return systemPermissionMapper.selectSystemPermissionList(systemPermission);
	}
	
    /**
     * 新增菜单权限
     * 
     * @param systemPermission 菜单权限信息
     * @return 结果
     */
	@Override
	public int insertSystemPermission(SystemPermission systemPermission) {
	    return systemPermissionMapper.insertSystemPermission(systemPermission);
	}
	
	/**
     * 修改菜单权限
     * 
     * @param systemPermission 菜单权限信息
     * @return 结果
     */
	@Override
	public int updateSystemPermission(SystemPermission systemPermission) {
	    return systemPermissionMapper.updateSystemPermission(systemPermission);
	}

	/**
     * 删除菜单权限对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSystemPermissionByIds(Long[] ids) {
		return systemPermissionMapper.deleteSystemPermissionByIds(ids);
	}
	
}
