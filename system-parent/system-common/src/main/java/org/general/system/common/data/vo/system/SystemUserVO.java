package org.general.system.common.data.vo.system;

import lombok.Data;

import java.util.List;

@Data
public class SystemUserVO extends MenuAndButtonVO{

    private Long id;

    // token
    private String token;

    // 用户名
    private String username;


}
