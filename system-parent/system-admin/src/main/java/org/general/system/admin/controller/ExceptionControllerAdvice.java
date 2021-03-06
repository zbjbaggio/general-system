package org.general.system.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.general.system.common.data.ResponseResult;
import org.general.system.common.enmus.ErrorInfo;
import org.general.system.common.exception.PrivateException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 异常处理
 * Created by eason on 2016-10-25.
 */
@ControllerAdvice(basePackages = "org.general.system.admin.controller.advice")
@Slf4j
public class ExceptionControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return ResponseResult.ok(body);
    }

    @ExceptionHandler()
    @ResponseBody
    public ResponseResult handler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> set = e.getConstraintViolations();
        if (set != null && set.size() > 0) {
            StringBuffer msg = new StringBuffer();
            for (ConstraintViolation<?> cv : set) {
                msg.append(cv.getMessage()).append(",");
            }
            log.info("参数错误！{}", msg.substring(0, msg.length() -1));
        }
        return ResponseResult.build(ErrorInfo.PARAMS_ERROR);
    }

    @ExceptionHandler()
    @ResponseBody
    public ResponseResult handler(PrivateException e) {
        log.info(e.toString());
        return ResponseResult.build(e.getCode(), e.getMsg());
    }

   /* @ExceptionHandler()
    @ResponseBody
    public ResponseResult handler(AuthorizationException e) {
        log.info(e.getMessage());
        log.info("-------------------------------------------");
        return ResponseResult.build(ErrorInfo.NO_AUTHORITY);
    }*/

    @ExceptionHandler()
    @ResponseBody
    public ResponseResult handler(Exception e) {
        log.error("系统异常！", e);
        return ResponseResult.build(ErrorInfo.ERROR);
    }
}
