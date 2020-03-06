package org.general.system.common.mapper;

import org.general.system.common.data.entity.SystemUser;
import java.util.List;	

/**
 * 系统用户 数据层
 * 
 * @author eason
 * @date 2020-03-06
 */
interface SystemUserMapper {
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
	
}