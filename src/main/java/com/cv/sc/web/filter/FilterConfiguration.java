package com.cv.sc.web.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 19/09/22
 */
@Component
@Configuration
public class FilterConfiguration {
        @Bean
        public FilterRegistrationBean<AuthenticationFilter> authenticationFilterConfig() {
            FilterRegistrationBean<AuthenticationFilter> registration = new FilterRegistrationBean<>();
            registration.setFilter(getAuthenticationFilter());
            registration.addUrlPatterns("/*");
            registration.setName("authenticationFilter");
            registration.setOrder(1);
            return registration;
        }

    @Bean
    public AuthenticationFilter getAuthenticationFilter() {
        return new AuthenticationFilter();
    }
}
