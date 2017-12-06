package com.tangcheng.zhiban.sns.todo.web.config;

import com.tangcheng.zhiban.sns.todo.domain.exception.CaptchaException;
import com.tangcheng.zhiban.sns.todo.web.config.security.ClientResources;
import com.tangcheng.zhiban.sns.todo.web.config.security.LoginAuthenticationFailureHandler;
import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.filter.CompositeFilter;

import javax.security.auth.login.AccountExpiredException;
import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableOAuth2Client// 启用 OAuth 2.0 客户端
@Configuration
public class FormLoginSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandler accessDeniedHandler;

    private final UserDetailsService userDetailsService;

    private final OAuth2ClientContext oauth2ClientContext;

    @Autowired
    public FormLoginSecurityConfig(AccessDeniedHandler accessDeniedHandler, UserDetailsService userDetailsService, OAuth2ClientContext oauth2ClientContext) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.userDetailsService = userDetailsService;
        this.oauth2ClientContext = oauth2ClientContext;
    }

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
        http.csrf().disable();
        http.formLogin()
                .loginPage(ApiVersion.WEB_V1 + "/login")
                .loginProcessingUrl(ApiVersion.API_V1 + "/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .logout()
                .logoutUrl(ApiVersion.API_V1 + "/logout")
                .invalidateHttpSession(true)//用户的HTTP session将会在退出时被失效。在一些场景下，这是必要的（如用户拥有一个购物车时）
                .clearAuthentication(true)
                .logoutSuccessUrl(ApiVersion.WEB_V1 + "/login")//用户在退出后将要被重定向到的URL。默认为/。将会通过HttpServletResponse.redirect来处理。
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/wx").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/favicon.ico",
                        "/static/**")
                .permitAll()
                .antMatchers(ApiVersion.API_V1 + "/user/todo/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(ApiVersion.WEB_V1 + "/user/todo/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(ApiVersion.WEB_V1 + "/user/profile/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .and()
                .addFilterBefore(ssoFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }


    /**
     * Handling the Redirects
     * <p>
     * The last change we need to make is to explicitly support the redirects from our app to Facebook.
     * This is handled in Spring OAuth2 with a servlet Filter, and the filter is already available in the application context because we used @EnableOAuth2Client.
     * All that is needed is to wire the filter up so that it gets called in the right order in our Spring Boot application.
     * To do that we need a FilterRegistrationBean:
     * <p>
     * We autowire the already available filter, and register it with a sufficiently low order that it comes before the main Spring Security filter.
     * In this way we can use it to handle redirects signaled by expceptions in authentication requests.
     * zh:
     * 注册一个额外的Filter：OAuth2ClientContextFilter，主要作用是重定向，当遇到需要权限的页面或URL，代码抛出异常，
     * 这时这个Filter将重定向到OAuth鉴权的地址，本例中github的会redirect /login/github
     *
     * @param filter
     * @return
     */
    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        /**
         * register it with a sufficiently low order that it comes before the main Spring Security filter.
         * In this way we can use it to handle redirects signaled by expceptions in authentication requests.
         */
        registration.setOrder(-100);
        return registration;
    }


    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(ssoFilter(github(), "/login/github"));
        filters.add(ssoFilter(qq(), "/login/qq"));
        filters.add(ssoFilter(sina(), "/login/sina"));
        filter.setFilters(filters);
        return filter;
    }

    @Bean
    @ConfigurationProperties("github")
    public ClientResources github() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("qq")
    public ClientResources qq() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("sina")
    public ClientResources sina() {
        return new ClientResources();
    }

    private Filter ssoFilter(ClientResources client, String path) {
        OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationFilter = new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        oAuth2ClientAuthenticationFilter.setRestTemplate(oAuth2RestTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(), client.getClient().getClientId());
        tokenServices.setRestTemplate(oAuth2RestTemplate);
        oAuth2ClientAuthenticationFilter.setTokenServices(tokenServices);
        return oAuth2ClientAuthenticationFilter;
    }


    @Bean
    public LogoutHandler logoutHandler() {
        return new SecurityContextLogoutHandler();
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
