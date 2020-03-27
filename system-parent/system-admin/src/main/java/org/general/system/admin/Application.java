package org.general.system.admin;

import org.general.system.admin.filter.CORSFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.Filter;

/**
 * 启动程序
 */
@ComponentScan("org.general.system")
@SpringBootApplication
@MapperScan("org.general.system.**.mapper")
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

/*    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(corsFilter());
        registration.addUrlPatterns("/*");
//		registration.addInitParameter("paramName", "paramValue");
        registration.setName("corsFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean(name = "corsFilter")
    public Filter corsFilter() {
        return new CORSFilter();
    }*/
}
