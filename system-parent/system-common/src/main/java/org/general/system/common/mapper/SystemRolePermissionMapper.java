package org.general.system.common.mapper;

import org.general.system.common.data.entity.system.SystemRolePermission;
import java.util.List;	

/**
 * 角色权限 数据层
 * 
 * @author eason
 * @date 2020-03-05
 */
public interface SystemRolePermissionMapper {
	/**
     * 查询角色权限信息
     * 
     * @param id 角色权限ID
     * @return 角色权限信息
     */
	SystemRolePermission selectSystemRolePermissionById(Long id);
	
	/**
     * 查询角色权限列表
     * 
     * @param systemRolePermission 角色权限信息
     * @return 角色权限集合
     */
	List<SystemRolePermission> selectSystemRolePermissionList(SystemRolePermission systemRolePermission);
	
	/**
     * 新增角色权限
     * 
     * @param systemRolePermission 角色权限信息
     * @return 结果
     */
	int insertSystemRolePermission(SystemRolePermission systemRolePermission);
	
	/**
     * 修改角色权限
     * 
     * @param systemRolePermission 角色权限信息
     * @return 结果
     */
	int updateSystemRolePermission(SystemRolePermission systemRolePermission);
	
	/**
     * 删除角色权限
     * 
     * @param id 角色权限ID
     * @return 结果
     */
	int deleteSystemRolePermissionById(Long id);
	
	/**
     * 批量删除角色权限
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteSystemRolePermissionByIds(Long[] ids);
	
}