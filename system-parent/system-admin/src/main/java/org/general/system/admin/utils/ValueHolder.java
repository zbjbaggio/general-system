package org.general.system.admin.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValueHolder {

    private ThreadLocal<String> userNameHolder = new ThreadLocal<>();

    public String getUserNameHolder() {
        return userNameHolder.get();
    }

    public void setUserNameHolder(String userIdHolder) {
        this.userNameHolder.set(userIdHolder);
    }

    public void removeUserNameHolder() {
        this.userNameHolder.remove();
    }


}
