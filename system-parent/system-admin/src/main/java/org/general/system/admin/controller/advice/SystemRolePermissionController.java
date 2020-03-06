/*
package org.general.system.admin.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.general.system.common.data.entity.system.SystemRolePermission;
import org.general.system.common.service.SystemRolePermissionService;
import org.general.system.common.controller.BaseController;
import org.general.system.common.data.page.TableDataInfo;

*/
/**
 * 角色权限 信息操作处理
 * 
 * @author eason
 * @date 2020-03-05
 *//*

@RestController
@RequestMapping("/system/systemRolePermission")
@Slf4j
public class SystemRolePermissionController extends BaseController {
	
	@Autowired
	private SystemRolePermissionService systemRolePermissionService;
	
	*/
/**
	 * 查询角色权限列表
	 *//*

	@RequiresPermissions("system:systemRolePermission:list")
	@GetMapping("/list")
	@ResponseBody
	public TableDataInfo list(SystemRolePermission systemRolePermission) {
		startPage();
        List<SystemRolePermission> list = systemRolePermissionService.selectSystemRolePermissionList(systemRolePermission);
		return getDataTable(list);
	}
	
	
	*/
/**
	 * 导出角色权限列表
	 *//*

	@RequiresPermissions("system:systemRolePermission:export")
    @PostMapping("/export")
    @ResponseBody
    public void export(SystemRolePermission systemRolePermission) {
    	List<SystemRolePermission> list = systemRolePermissionService.selectSystemRolePermissionList(systemRolePermission);
        ExcelUtil<SystemRolePermission> util = new ExcelUtil<SystemRolePermission>(SystemRolePermission.class);
        return util.exportExcel(list, "systemRolePermission");
    }
	
	*/
/**
	 * 新增保存角色权限
	 *//*

	@RequiresPermissions("system:systemRolePermission:add")
	@PostMapping("/add")
	@ResponseBody
	public SystemRolePermission add(SystemRolePermission systemRolePermission) {
		return systemRolePermissionService.insertSystemRolePermission(systemRolePermission);
	}

	*/
/**
	 * 修改角色权限
	 *//*

	@GetMapping("/detail/{id}")
	public SystemRolePermission detail(@PathVariable("id") Long id, ModelMap mmap) {
	    return systemRolePermissionService.selectSystemRolePermissionById(id);
	}
	
	*/
/**
	 * 修改保存角色权限
	 *//*

	@RequiresPermissions("system:systemRolePermission:edit")
	@PostMapping("/update")
	@ResponseBody
	public void update(SystemRolePermission systemRolePermission) {
		systemRolePermissionService.updateSystemRolePermission(systemRolePermission);
	}
	
	*/
/**
	 * 删除角色权限
	 *//*

	@RequiresPermissions("system:systemRolePermission:remove")
	@PostMapping( "/remove")
	@ResponseBody
	public void remove(Long[] ids) {
		systemRolePermissionService.deleteSystemRolePermissionByIds(ids);
	}
	
}
*/
