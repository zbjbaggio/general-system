package org.general.system.common.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.general.system.common.data.entity.system.SystemUser;
import java.util.List;
import java.util.Set;

/**
 * 系统用户 数据层
 * 
 * @author eason
 * @date 2020-03-06
 */
@Mapper
public interface SystemUserMapper {
	/**
     * 查询系统用户信息
     * 
     * @param id 系统用户ID
     * @return 系统用户信息
     */
	SystemUser selectSystemUserById(Long id);
	
	/**
     * 查询系统用户列表
     * 
     * @param systemUser 系统用户信息
     * @return 系统用户集合
     */
	List<SystemUser> selectSystemUserList(SystemUser systemUser);
	
	/**
     * 新增系统用户
     * 
     * @param systemUser 系统用户信息
     * @return 结果
     */
	int insertSystemUser(SystemUser systemUser);
	
	/**
     * 修改系统用户
     * 
     * @param systemUser 系统用户信息
     * @return 结果
     */
	int updateSystemUser(SystemUser systemUser);
	
	/**
     * 删除系统用户
     * 
     * @param id 系统用户ID
     * @return 结果
     */
	int deleteSystemUserById(Long id);
	
	/**
     * 批量删除系统用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteSystemUserByIds(Long[] ids);

	/**
	 * 根据用户名查询正常用户的相关数据
	 * @param username
	 * @return
	 */
    SystemUser getByUsername(String username);

	/**
	 * 根据用户id查询该用户拥有的权限
	 * @param userId
	 * @return 后端权限url
	 */
	Set<String> selectPermissionByUserId(Long userId);

}