package org.general.system.common.service.system;

import org.general.system.common.data.entity.system.SystemUserRole;
import java.util.List;

/**
 * 用户角色 服务层
 * 
 * @author eason
 * @date 2020-03-06
 */
public interface SystemUserRoleService {
	/**
     * 查询用户角色信息
     * 
     * @param id 用户角色ID
     * @return 用户角色信息
     */
	SystemUserRole selectSystemUserRoleById(Long id);
	
	/**
     * 查询用户角色列表
     * 
     * @param systemUserRole 用户角色信息
     * @return 用户角色集合
     */
	List<SystemUserRole> selectSystemUserRoleList(SystemUserRole systemUserRole);
	
	/**
     * 新增用户角色
     * 
     * @param systemUserRole 用户角色信息
     * @return 结果
     */
	int insertSystemUserRole(SystemUserRole systemUserRole);
	
	/**
     * 修改用户角色
     * 
     * @param systemUserRole 用户角色信息
     * @return 结果
     */
	int updateSystemUserRole(SystemUserRole systemUserRole);
		
	/**
     * 删除用户角色信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteSystemUserRoleByIds(Long[] ids);
	
}
