package org.general.system.common.data.vo;

import lombok.Data;

import java.util.Set;

@Data
public class SystemUserVO {

    private Long id;

    // token
    private String token;

    // 用户名
    private String username;

    // 权限
    private Set<String> permissionSet;

}
