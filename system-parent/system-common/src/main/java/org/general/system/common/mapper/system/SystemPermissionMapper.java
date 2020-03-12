package org.general.system.common.mapper.system;

import org.general.system.common.data.entity.system.SystemPermission;
import java.util.List;	

/**
 * 菜单权限 数据层
 * 
 * @author eason
 * @date 2020-03-06
 */
public interface SystemPermissionMapper {
	/**
     * 查询菜单权限信息
     * 
     * @param ${primaryKey.attrname} 菜单权限ID
     * @return 菜单权限信息
     */
	public SystemPermission selectSystemPermissionById(Long id);
	
	/**
     * 查询菜单权限列表
     * 
     * @param systemPermission 菜单权限信息
     * @return 菜单权限集合
     */
	public List<SystemPermission> selectSystemPermissionList(SystemPermission systemPermission);
	
	/**
     * 新增菜单权限
     * 
     * @param systemPermission 菜单权限信息
     * @return 结果
     */
	public int insertSystemPermission(SystemPermission systemPermission);
	
	/**
     * 修改菜单权限
     * 
     * @param systemPermission 菜单权限信息
     * @return 结果
     */
	public int updateSystemPermission(SystemPermission systemPermission);
	
	/**
     * 删除菜单权限
     * 
     * @param ${primaryKey.attrname} 菜单权限ID
     * @return 结果
     */
	public int deleteSystemPermissionById(Long id);
	
	/**
     * 批量删除菜单权限
     * 
     * @param id 需要删除的数据ID
     * @return 结果
     */
	public int deleteSystemPermissionByIds(Long[] id);
	
}