package org.general.system.common.service.impl.system;

import lombok.RequiredArgsConstructor;
import org.general.system.common.data.entity.system.SystemRole;
import org.general.system.common.mapper.system.SystemRoleMapper;
import org.general.system.common.service.system.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 服务层实现
 *
 * @author eason
 * @date 2020-03-06
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SystemRoleServiceImpl implements SystemRoleService {

	private final SystemRoleMapper systemRoleMapper;

	/**
     * 查询角色信息
     *
     * @param id 角色ID
     * @return 角色信息
     */
    @Override
	public SystemRole selectSystemRoleById(Long id) {
	    return systemRoleMapper.selectSystemRoleById(id);
	}

	/**
     * 查询角色列表
     *
     * @param systemRole 角色信息
     * @return 角色集合
     */
	@Override
	public List<SystemRole> selectSystemRoleList(SystemRole systemRole) {
	    return systemRoleMapper.selectSystemRoleList(systemRole);
	}

    /**
     * 新增角色
     *
     * @param systemRole 角色信息
     * @return 结果
     */
	@Override
	public int insertSystemRole(SystemRole systemRole) {
	    return systemRoleMapper.insertSystemRole(systemRole);
	}

	/**
     * 修改角色
     *
     * @param systemRole 角色信息
     * @return 结果
     */
	@Override
	public int updateSystemRole(SystemRole systemRole) {
	    return systemRoleMapper.updateSystemRole(systemRole);
	}

	/**
     * 删除角色对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSystemRoleByIds(Long[] ids) {
		return systemRoleMapper.deleteSystemRoleByIds(ids);
	}

}
