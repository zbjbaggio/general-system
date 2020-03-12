package org.general.system.common.mapper.system;

import org.general.system.common.data.entity.system.SystemRole;
import java.util.List;	

/**
 * 角色 数据层
 * 
 * @author eason
 * @date 2020-03-06
 */
public interface SystemRoleMapper {
	/**
     * 查询角色信息
     * 
     * @param id 角色ID
     * @return 角色信息
     */
	SystemRole selectSystemRoleById(Long id);
	
	/**
     * 查询角色列表
     * 
     * @param systemRole 角色信息
     * @return 角色集合
     */
	List<SystemRole> selectSystemRoleList(SystemRole systemRole);
	
	/**
     * 新增角色
     * 
     * @param systemRole 角色信息
     * @return 结果
     */
	int insertSystemRole(SystemRole systemRole);
	
	/**
     * 修改角色
     * 
     * @param systemRole 角色信息
     * @return 结果
     */
	int updateSystemRole(SystemRole systemRole);
	
	/**
     * 删除角色
     * 
     * @param id 角色ID
     * @return 结果
     */
	int deleteSystemRoleById(Long id);
	
	/**
     * 批量删除角色
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteSystemRoleByIds(Long[] ids);
	
}