package com.huawei.mock.device.config;
import javax.servlet.Filter;

import com.huawei.mock.device.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/*
 *配置过滤器 
 */
@SpringBootConfiguration
public class FilterConfig {
	
	@Bean
    public FilterRegistrationBean<Filter> companyUrlFilterRegister() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        //注入过滤器
        registration.setFilter(new TokenFilter());
        //拦截规则
        registration.addUrlPatterns("/*");
        //拦截放行的路径
        registration.addInitParameter("unTokenFilter", "/token");
        //过滤器名称
        registration.setName("tokenFilter");
        //过滤器顺序
        registration.setOrder(0);
        return registration;
    }
}

