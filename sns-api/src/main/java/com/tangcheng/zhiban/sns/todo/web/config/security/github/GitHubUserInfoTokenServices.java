package com.tangcheng.zhiban.sns.todo.web.config.security.github;

import com.tangcheng.zhiban.sns.todo.core.constant.Flag;
import com.tangcheng.zhiban.sns.todo.domain.bo.UserBO;
import com.tangcheng.zhiban.sns.todo.service.biz.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;

@Slf4j
public class GitHubUserInfoTokenServices extends UserInfoTokenServices {

    private UserService userService;
    private AuthoritiesExtractor authoritiesExtractor = new FixedAuthoritiesExtractor();


    public GitHubUserInfoTokenServices(String userInfoEndpointUrl, String clientId) {
        super(userInfoEndpointUrl, clientId);
    }


    @Override
    protected Object getPrincipal(Map<String, Object> map) {
        log.info("https://api.github.com/user :{}", map);
        /**
         * https://developer.github.com/v3/users/
         {
         "login": "helloworldtang",
         "id": 13701989,
         "avatar_url": "https://avatars2.githubusercontent.com/u/13701989?v=4",
         "gravatar_id": "",
         "url": "https://api.github.com/users/helloworldtang",
         "html_url": "https://github.com/helloworldtang",
         "followers_url": "https://api.github.com/users/helloworldtang/followers",
         "following_url": "https://api.github.com/users/helloworldtang/following{/other_user}",
         "gists_url": "https://api.github.com/users/helloworldtang/gists{/gist_id}",
         "starred_url": "https://api.github.com/users/helloworldtang/starred{/owner}{/repo}",
         "subscriptions_url": "https://api.github.com/users/helloworldtang/subscriptions",
         "organizations_url": "https://api.github.com/users/helloworldtang/orgs",
         "repos_url": "https://api.github.com/users/helloworldtang/repos",
         "events_url": "https://api.github.com/users/helloworldtang/events{/privacy}",
         "received_events_url": "https://api.github.com/users/helloworldtang/received_events",
         "type": "User",
         "site_admin": false,
         "name": "Chen.Tang",
         "company": null,
         "blog": "",
         "location": null,
         "email": "helloworld.tang@qq.com",
         "hireable": true,
         "bio": "A code cleanliness code farmers,\r\nA programmer concentrate on elegant design,\r\nA want to do with the product architect",
         "public_repos": 320,
         "public_gists": 1,
         "followers": 7,
         "following": 24,
         "created_at": "2015-08-08T02:06:44Z",
         "updated_at": "2017-12-11T10:30:29Z"
         }
         *
         *
         */
        String loginName = map.get("login").toString();
        String thirdPartId = map.get("id").toString();
        String icon = map.get("avatar_url").toString();
        String bio = map.get("bio").toString();
        String email = map.get("email").toString();
        String location;
        Object locationGithub = map.get("location");
        if (locationGithub == null) {
            location = "";
        } else {
            location = locationGithub.toString();
        }
        Long id = userService.save(thirdPartId, Flag.UserTypeFlag.GITHUB, loginName, icon, bio, email, location);
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

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        log.info("accessToken:{}", accessToken);
        return super.loadAuthentication(accessToken);
    }

}
