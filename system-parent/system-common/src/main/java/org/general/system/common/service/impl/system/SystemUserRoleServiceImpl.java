package org.general.system.common.service.impl.system;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.general.system.common.mapper.system.SystemUserRoleMapper;
import org.general.system.common.data.entity.system.SystemUserRole;
import org.general.system.common.service.system.SystemUserRoleService;

/**
 * 用户角色 服务层实现
 *
 * @author eason
 * @date 2020-03-06
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SystemUserRoleServiceImpl implements SystemUserRoleService {

	private final SystemUserRoleMapper systemUserRoleMapper;

	/**
     * 查询用户角色信息
     *
     * @param id 用户角色ID
     * @return 用户角色信息
     */
    @Override
	public SystemUserRole selectSystemUserRoleById(Long id) {
	    return systemUserRoleMapper.selectSystemUserRoleById(id);
	}

	/**
     * 查询用户角色列表
     *
     * @param systemUserRole 用户角色信息
     * @return 用户角色集合
     */
	@Override
	public List<SystemUserRole> selectSystemUserRoleList(SystemUserRole systemUserRole) {
	    return systemUserRoleMapper.selectSystemUserRoleList(systemUserRole);
	}

    /**
     * 新增用户角色
     *
     * @param systemUserRole 用户角色信息
     * @return 结果
     */
	@Override
	public int insertSystemUserRole(SystemUserRole systemUserRole) {
	    return systemUserRoleMapper.insertSystemUserRole(systemUserRole);
	}

	/**
     * 修改用户角色
     *
     * @param systemUserRole 用户角色信息
     * @return 结果
     */
	@Override
	public int updateSystemUserRole(SystemUserRole systemUserRole) {
	    return systemUserRoleMapper.updateSystemUserRole(systemUserRole);
	}

	/**
     * 删除用户角色对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSystemUserRoleByIds(Long[] ids) {
		return systemUserRoleMapper.deleteSystemUserRoleByIds(ids);
	}

}
