package org.general.system.admin.filter;

import org.general.system.admin.constants.Constants;
import org.general.system.admin.utils.ValueHolder;
import org.general.system.common.utils.JwtUtil;
import org.general.system.common.utils.StringUtil;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A {@link Filter} that adds cross-origin resource sharing (CORS) headers to the response.
 * <p>
 * Created by matthias.wendt on 19.05.15.
 */
@Component
public class CORSFilter implements Filter {

    @Autowired
    private ValueHolder valueHolder;

    /**
     * Adds CORS headers to the response. Then calls {@link FilterChain#doFilter} on the rest of the chain.
     *
     * @param request  the servlet request.
     * @param response the servlet response.
     * @param chain    the filter chain.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String header = httpServletRequest.getHeader(Constants.X_ACCESS_TOKEN);
        if (!StringUtil.isEmpty(header)) {
            valueHolder.setUserNameHolder(JwtUtil.getUsername(header));
        }
        // 日志sessionId赋值
        MDC.put("sessionId", httpServletRequest.getSession().getId());
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.setHeader("Access-Control-Allow-Origin", "*");
        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        servletResponse.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
        servletResponse.setHeader("Access-Control-Max-Age", "3600");
        servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept, X-Access-Token");
        chain.doFilter(request, response);
    }

    /**
     * No effect.
     *
     * @param config the filter config.
     */
    @Override
    public void init(FilterConfig config) {// make sonar happy
    }

    /**
     * No effect.
     */
    @Override
    public void destroy() {
        MDC.remove("sessionId");
    }

}
