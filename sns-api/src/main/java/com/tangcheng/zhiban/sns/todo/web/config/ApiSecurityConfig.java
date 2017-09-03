package com.tangcheng.zhiban.sns.todo.web.config;

import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by tangcheng on 8/26/2017.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@Order(1)//将api配置和web view跳转的security配置分离
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
/*        auth.inMemoryAuthentication()
                .withUser("admin").password("yigepingguo.com").roles("ADMIN", "USER")
                .and()
                .withUser("user").password("user").roles("USER");*/
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher(ApiVersion.API_V1 + "/**")
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET).hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST).hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE).access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')");
    }


}
