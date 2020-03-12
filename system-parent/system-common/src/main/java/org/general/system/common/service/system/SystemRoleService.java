package org.general.system.common.service.system;

import org.general.system.common.data.entity.system.SystemRole;
import java.util.List;

/**
 * 角色 服务层
 * 
 * @author eason
 * @date 2020-03-06
 */
public interface SystemRoleService {
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
     * 删除角色信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteSystemRoleByIds(Long[] ids);
	
}
