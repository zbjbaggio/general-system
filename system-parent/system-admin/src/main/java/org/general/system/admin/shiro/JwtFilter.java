package org.general.system.admin.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.general.system.admin.constants.Constants;
import org.general.system.common.data.ResponseResult;
import org.general.system.common.enmus.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description: 鉴权登录拦截器
 * @Author: Scott
 * @Date: 2018/10/7
 **/
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

	/**
	 * 执行登录认证
	 *
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(Constants.X_ACCESS_TOKEN);
        JwtToken jwtToken = new JwtToken(token);
        getSubject(request, response).login(jwtToken);
        return true;
	}

	@Override
	public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException{
		try {
			super.doFilterInternal(request,response, chain);
		} catch (ServletException e) {
			if (e.getCause() instanceof AuthenticationException) {
				log.info("", e);
				response.setContentType("application/json; charset=utf-8");
				response.setCharacterEncoding("UTF-8");
				OutputStream out = response.getOutputStream();
				out.write(ResponseResult.build(ErrorInfo.LOGIN_AGAIN).toString().getBytes("UTF-8"));
				out.flush();
			}
			throw e;
		}
	}

	/**
	 *
	 */
	/*@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String token = httpServletRequest.getHeader(X_ACCESS_TOKEN);
		JwtToken jwtToken = new JwtToken(token);
		// 提交给realm进行登入，如果错误他会抛出异常并被捕获
		getSubject(request, response).login(jwtToken);
		// 如果没有抛出异常则代表登入成功，返回true
		return true;
	}*/

	/**
	 * 对跨域提供支持
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			httpServletResponse.setStatus(HttpStatus.OK.value());
			return false;
		}
			return super.preHandle(request, response);

	}


}
