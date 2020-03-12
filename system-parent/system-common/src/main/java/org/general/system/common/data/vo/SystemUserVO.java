package org.general.system.common.data.vo;

import lombok.Data;
import org.general.system.common.data.entity.system.SystemPermission;

import java.util.Set;

@Data
public class SystemUserVO {

    // token
    private String token;

    // 用户名
    private String username;

    // 权限
    private Set<SystemPermission> permissionSet;

}
