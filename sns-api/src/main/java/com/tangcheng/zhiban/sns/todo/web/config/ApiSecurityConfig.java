package com.tangcheng.zhiban.sns.todo.web.config;

import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by tangcheng on 8/26/2017.
 */
@Configuration
@Order(1)//将api配置和web view跳转的security配置分离
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("yigepingguo.com").roles("ADMIN", "USER")
                .and()
                .withUser("user").password("user").roles("USER");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher(ApiVersion.API_V1 + "/**")
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET).hasRole("USER")
                .antMatchers(HttpMethod.POST).hasRole("USER")
                .antMatchers(HttpMethod.PUT).hasRole("USER")
                .antMatchers(HttpMethod.DELETE).access("hasRole('ROLE_USER')");
    }


}
