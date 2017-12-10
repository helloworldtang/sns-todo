package com.tangcheng.zhiban.sns.todo.web.config.security.github;

import com.tangcheng.zhiban.sns.todo.core.constant.Flag;
import com.tangcheng.zhiban.sns.todo.domain.bo.UserBO;
import com.tangcheng.zhiban.sns.todo.service.biz.UserService;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;

import java.util.Map;

public class GitHubUserInfoTokenServices extends UserInfoTokenServices {

    private UserService userService;
    private AuthoritiesExtractor authoritiesExtractor = new FixedAuthoritiesExtractor();


    public GitHubUserInfoTokenServices(String userInfoEndpointUrl, String clientId) {
        super(userInfoEndpointUrl, clientId);
    }


    @Override
    protected Object getPrincipal(Map<String, Object> map) {
        String loginName = map.get("login").toString();
        String thirdPartId = map.get("id").toString();
        String icon = map.get("avatar_url").toString();
        String bio = map.get("bio").toString();
        String email = map.get("email").toString();
        Long id = userService.save(thirdPartId, Flag.UserTypeFlag.GITHUB, loginName, icon, bio, email);
        return new UserBO(id,
                thirdPartId,
                Flag.UserTypeFlag.GITHUB,
                loginName,
                bio,
                icon,
                email,
                loginName,
                thirdPartId,
                this.authoritiesExtractor.extractAuthorities(map));
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
