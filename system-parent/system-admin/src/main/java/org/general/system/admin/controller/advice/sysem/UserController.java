package org.general.system.admin.controller.advice.sysem;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.general.system.common.constants.SystemUserStatusContant;
import org.general.system.common.controller.BaseController;
import org.general.system.common.data.entity.BaseEntity;
import org.general.system.common.data.entity.system.SystemUser;
import org.general.system.common.data.page.TableDataInfo;
import org.general.system.common.service.RedisService;
import org.general.system.common.service.system.SystemRoleService;
import org.general.system.common.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user/system/user")
public class UserController extends BaseController {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemRoleService systemRoleService;

    @Autowired
    private RedisService shiroRealm;

    /**
     * 系统用户查询
     * @param searchStr
     * @param status
     * @return 系统用户列表
     */
    @RequiresPermissions("system:user:list")
    @GetMapping(value = "/list")
    public TableDataInfo list(@RequestParam(value = "searchStr", defaultValue = "-1") String searchStr,
                              @RequestParam(value = "status", defaultValue = "-1") int status) {
        startPage();
        SystemUser systemUser = new SystemUser();
        shiroRealm.delete("");
        return getDataTable(systemUserService.selectSystemUserList(systemUser));
    }

    /**
     * 添加管理员
     *
     * @param systemUser
     * @return
     */
    @RequiresPermissions("system:user:add")
    @PostMapping(value = "/add")
    public SystemUser add(@RequestBody @Validated(value = {SystemUser.Add.class, BaseEntity.Insert.class}) SystemUser systemUser,
                          BindingResult bindingResult) throws Exception {
        return systemUserService.insertSystemUser(systemUser);
    }

    /**
     * 系统用户详情
     *
     * @param userId 系统用户id
     * @return 系统用户详情
     */
    @RequiresPermissions("system:user:getDetail")
    @GetMapping(value = "/getDetail/{userId}")
    public SystemUser getDetail(@PathVariable("userId") Long userId) {
        return systemUserService.selectSystemUserById(userId);
    }

    /**
     * 系统用户冻结
     *
     * @param userId 用户id
     */
    @RequiresPermissions("system:user:freeze")
    @PostMapping(value = "/freeze/{userId}")
    public void freeze(@PathVariable Long userId) {
        systemUserService.updateStatus(userId, SystemUserStatusContant.FREEZE);
    }

    /**
     * 系统用户解冻
     *
     * @param userId 用户id
     */
    @RequiresPermissions("system:user:unFreeze")
    @PostMapping(value = "/unFreeze/{userId}")
    public void unFreeze(@PathVariable Long userId) {
        systemUserService.updateStatus(userId, SystemUserStatusContant.UNFREEZE);
    }

}
