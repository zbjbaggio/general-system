package org.general.system.admin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.general.system.admin.utils.ValueHolder;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {
    private long startTime = 0; // 开始时间

    @Autowired
    private ValueHolder valueHolder;

    @Pointcut("execution (public * org.general.system.admin.controller..*.*(..)) && !execution(* org.general.system.admin.controller.ExceptionControllerAdvice.*(..))  ")
    private void aspectMethod(){}

    @Before(value = "aspectMethod()")
    public void before(JoinPoint point) {
        startTime = System.currentTimeMillis();   //获取开始时间
        log.info("日志【请求】－－－－－username : " + valueHolder.getUserNameHolder() + "－－－－－－方法为:" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + "      参数为：" + Arrays.toString(point.getArgs()));
    }

    @AfterReturning(value = "aspectMethod()", returning = "returnValue")
    public void after(JoinPoint point, Object returnValue) {
        log.info("日志【返回】－－－－－－－－－－－方法为:" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + "      返回值为：" + returnValue + "【共耗时-" + (System.currentTimeMillis() - startTime) + "-毫秒】");
    }

}
