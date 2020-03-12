package org.general.system.common.data.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SystemUserDTO {

    @NotEmpty(groups = LoginGroup.class)
    private String username;

    @NotEmpty(groups = LoginGroup.class)
    private String password;

    public interface LoginGroup {

    }

}
