package org.general.system.admin.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.general.system.common.data.dto.SystemUserDTO;
import org.general.system.common.data.vo.SystemUserVO;
import org.general.system.common.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private SystemUserService systemUserService;

    /**
     * 登录接口
     *
     * @param systemUserDTO
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login")
    public SystemUserVO login(@RequestBody @Validated(SystemUserDTO.LoginGroup.class) SystemUserDTO systemUserDTO, BindingResult bindingResult) {
        return systemUserService.login(systemUserDTO);
    }

    @PostMapping(value = "/user/login")
    public void test(@RequestBody @Validated(SystemUserDTO.LoginGroup.class) SystemUserDTO systemUserDTO, BindingResult bindingResult) throws Exception {
    }

}
