package org.general.system.common.service.impl.system;

import lombok.RequiredArgsConstructor;
import org.general.system.common.constants.PermissionTypeContant;
import org.general.system.common.data.entity.system.SystemPermission;
import org.general.system.common.data.vo.system.MenuAndButtonVO;
import org.general.system.common.data.vo.system.PermissionVO;
import org.general.system.common.mapper.system.SystemPermissionMapper;
import org.general.system.common.service.system.SystemPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 菜单权限 服务层实现
 *
 * @author eason
 * @date 2020-03-06
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SystemPermissionServiceImpl implements SystemPermissionService {

	private final SystemPermissionMapper systemPermissionMapper;

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

	/**
	 * '根据系统用户id获取菜单list 后端list 按钮list
	 * @param systemUserId 登录用户id
	 * @return 菜单和权限
	 */
	@Override
	public MenuAndButtonVO getMenu(long systemUserId) {
		//查询权限
		List<PermissionVO> permissionVOS = systemPermissionMapper.listByUserId(systemUserId);
		Set<String> permissionSet = new HashSet<>();
		List<PermissionVO> routerList = new ArrayList<>();
		for (PermissionVO permissionVO : permissionVOS) {
			if (permissionVO.getPermission() != null) {
				permissionSet.add(permissionVO.getPermission());
			}
			// 一级菜单没有parentId
			if (-1L == permissionVO.getParentId()) {
				routerList.add(permissionVO);
			} else if (PermissionTypeContant.MENU == permissionVO.getType()) {
				setChild(routerList.get(routerList.size() - 1), permissionVO);
			} else if (PermissionTypeContant.BUTTON == permissionVO.getType()) {
				setButton(routerList.get(routerList.size() - 1), permissionVO);
				//routerList.get(routerList.size() - 1).setMeta(meta);
			}
		}
		MenuAndButtonVO menuAndButtonDTO = new MenuAndButtonVO();
		menuAndButtonDTO.setRouters(routerList);
		menuAndButtonDTO.setPermissionSet(permissionSet);
		return menuAndButtonDTO;
	}

	private void setChild(PermissionVO parentPermission, PermissionVO childrenPermissionVO) {
		List<PermissionVO> child = parentPermission.getChildren();
		if (child == null) {
			child = new ArrayList<>();
			child.add(childrenPermissionVO);
		} else if (child.get(child.size() - 1).getParentId().equals(childrenPermissionVO.getParentId())) {
			child.add(childrenPermissionVO);
		} else {
			setChild(child.get(child.size() - 1), childrenPermissionVO);
		}
		parentPermission.setChildren(child);
	}

	private void setButton(PermissionVO parentPermission, PermissionVO childrenPermissionVO) {
		if (parentPermission.getId().equals(childrenPermissionVO.getParentId())) {
			parentPermission.getMeta().getPermission().add(childrenPermissionVO.getPermission());
		} else {
			List<PermissionVO> children = parentPermission.getChildren();
			setButton(children.get(children.size() - 1), childrenPermissionVO);
		}
	}

}
