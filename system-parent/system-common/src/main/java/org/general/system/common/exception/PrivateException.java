package org.general.system.common.exception;

import lombok.Data;
import lombok.ToString;
import org.general.system.common.enmus.ErrorInfo;

/**
 * controller 异常
 * Created by eason on 2016-10-25.
 */
@Data
@ToString
public class PrivateException extends RuntimeException {

    private int code;

    private String msg;

    public PrivateException(ErrorInfo errorInfo) {
        super(errorInfo.getName());
        this.code = errorInfo.getValue();
        this.msg = errorInfo.getName();
    }

    public PrivateException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public PrivateException() {
        super();
    }
}
