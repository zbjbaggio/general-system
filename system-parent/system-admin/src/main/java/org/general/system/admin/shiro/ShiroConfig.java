package org.general.system.admin.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class ShiroConfig {

	@Value("${spring.redis.port}")
	private String port;

	@Value("${spring.redis.database}")
	private int database;

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.password}")
	private String redisPassword;

	/**
	 * Filter Chain定义说明 
	 * 
	 * 1、一个URL可以配置多个Filter，使用逗号分隔
	 * 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 */
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 拦截器
		Map<String, String> filterChainDefinitionMap = new HashMap<>();
		// 添加自己的过滤器并且取名为jwt
		Map<String, Filter> filterMap = new HashMap<>(1);
		filterMap.put("jwt", new JwtFilter());
		shiroFilterFactoryBean.setFilters(filterMap);
		// <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
		filterChainDefinitionMap.put("/user/**", "jwt");

		// 未授权界面返回JSON
		shiroFilterFactoryBean.setUnauthorizedUrl("/sys/common/403");
		shiroFilterFactoryBean.setLoginUrl("/sys/common/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean("securityManager")
	public DefaultWebSecurityManager securityManager(ShiroRealm myRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm);
		/*
		 * 关闭shiro自带的session，详情见文档
		 */
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
		securityManager.setSubjectDAO(subjectDAO);
        //自定义缓存实现,使用redis
        securityManager.setCacheManager(redisCacheManager());
		return securityManager;
	}

	/**
	 * 下面的代码是添加注解支持
	 * @return
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager redisCacheManager() {
        log.info("===============(1)创建缓存管理器RedisCacheManager");
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //redis中针对不同用户缓存(此处的id需要对应user实体中的id字段,用于唯一标识)
        redisCacheManager.setPrincipalIdFieldName("username");
        //用户权限信息缓存时间
        redisCacheManager.setExpire(200000);
        return redisCacheManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        log.info("===============(2)创建RedisManager,连接Redis..URL= " + host + ":" + port);
        RedisManager redisManager = new RedisManager();
		redisManager.setHost(host+ ":" + port);
		redisManager.setDatabase(database);
		redisManager.setTimeout(0);
        if (!StringUtils.isEmpty(redisPassword)) {
            redisManager.setPassword(redisPassword);
        }
        return redisManager;
    }

}
