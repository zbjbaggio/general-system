package org.general.system.common.service;


import org.general.system.common.data.vo.SystemUserVO;

/**
 * 描述：redis 服务
 * Created by eason on 2017-9-28.
 */
public interface RedisService {

    void saveSystemLogin(SystemUserVO login);

    SystemUserVO getSystemLogin(String username);
}
