package org.general.system.admin.controller.advice.sysem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.general.system.admin.utils.ValueHolder;
import org.general.system.common.data.vo.system.SystemUserVO;
import org.general.system.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserInfoController {

    private final ValueHolder valueHolder;

    private final RedisService redisService;

    @GetMapping(value = "/getInfo")
    public SystemUserVO getInfo() {
       return redisService.getSystemLogin(valueHolder.getUserNameHolder());
    }

}
