package com.tangcheng.zhiban.sns.todo.web.config;

import com.tangcheng.zhiban.sns.todo.domain.exception.CaptchaException;
import com.tangcheng.zhiban.sns.todo.web.config.security.LoginAuthenticationFailureHandler;
import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

import javax.security.auth.login.AccountExpiredException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl(ApiVersion.WEB_V1 + "/todo")
                .and()
                .logout()
                .invalidateHttpSession(true)//用户的HTTP session将会在退出时被失效。在一些场景下，这是必要的（如用户拥有一个购物车时）
                .clearAuthentication(true)
                .logoutSuccessUrl("/login")//用户在退出后将要被重定向到的URL。默认为/。将会通过HttpServletResponse.redirect来处理。
                .and()
                .authorizeRequests()
                .antMatchers("/web/jars/**", "/favicon.ico", "/css/**", "/js/**", "login", "/signin/**", "/signup")
                .permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        ExceptionMappingAuthenticationFailureHandler failureHandler = new ExceptionMappingAuthenticationFailureHandler();
        Map<String, String> failureUrlMap = new HashMap<>();
        failureUrlMap.put(BadCredentialsException.class.getName(), LoginAuthenticationFailureHandler.PASS_ERROR_URL);
        failureUrlMap.put(CaptchaException.class.getName(), LoginAuthenticationFailureHandler.CODE_ERROR_URL);
        failureUrlMap.put(AccountExpiredException.class.getName(), LoginAuthenticationFailureHandler.EXPIRED_URL);
        failureUrlMap.put(LockedException.class.getName(), LoginAuthenticationFailureHandler.LOCKED_URL);
        failureUrlMap.put(DisabledException.class.getName(), LoginAuthenticationFailureHandler.DISABLED_URL);
        failureHandler.setExceptionMappings(failureUrlMap);
        return failureHandler;
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //allow Swagger URL to be accessed without authentication
        web.ignoring().antMatchers("/v2/api-docs",//change to /swagger and custom the groupName
                "/swagger",// Resolve conflicts version number
                "/swagger-resources/configuration/ui",//用来获取支持的动作
                "/swagger-resources",//用来获取api-docs的URI
                "/swagger-resources/configuration/security",//安全选项
                "/webjars/**",///swagger-ui.html使用的一些资源文件在webjars目录下。eg:http://localhost/webjars/springfox-swagger-ui/images/logo_small.png
                "/swagger-ui.html");
    }


}
