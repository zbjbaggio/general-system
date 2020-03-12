package org.general.system.common.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j;
import org.general.system.common.data.vo.SystemUserVO;
import org.general.system.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 * Created by jay on 2017-9-28.
 */
@Service
@Log4j
public class RedisServiceImpl implements RedisService {

    // 系统用户登录key前缀
    private final String SYSTEM_USER_LOGIN_KEY = "system_user_login_";

    // 系统用户保存redis时长为30分钟
    private final int SYSTEM_USER_LOGIN_MINUTES = 30;

    @Autowired
    private StringRedisTemplate template;

    /**
     * 系统登录用户信息保存
     * @param login 登录用户信息
     */
    @Override
    public void saveSystemLogin(SystemUserVO login) {
        save(SYSTEM_USER_LOGIN_KEY + login.getUsername(), login, SYSTEM_USER_LOGIN_MINUTES, TimeUnit.MINUTES);
    }

    /**
     * 获取系统登录用户信息
     * @param username 用户名
     * @return 登录用户信息
     */
    @Override
    public SystemUserVO getSystemLogin(String username) {
        return get(SYSTEM_USER_LOGIN_KEY + username, SystemUserVO.class);
    }


    private void save(String key, Object value, long time, TimeUnit timeUnit) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, JSON.toJSONString(value), time, timeUnit);
    }

    private <T> T get(String key, Class<T> clazz) {
        ValueOperations<String, String> ops = template.opsForValue();
        String value = ops.get(key);
        T t = null;
        if (value != null && value.length() > 0) {
            t = JSON.parseObject(value, clazz);
        }
        return t;
    }

    private String get(String key) {
        ValueOperations<String, String> ops = template.opsForValue();
        return ops.get(key);
    }


}