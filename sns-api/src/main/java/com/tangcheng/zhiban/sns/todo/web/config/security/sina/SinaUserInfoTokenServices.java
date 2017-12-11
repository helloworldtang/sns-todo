package com.tangcheng.zhiban.sns.todo.web.config.security.sina;

import com.tangcheng.zhiban.sns.todo.core.constant.Flag;
import com.tangcheng.zhiban.sns.todo.domain.bo.UserBO;
import com.tangcheng.zhiban.sns.todo.service.biz.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author tangcheng
 * 2017/12/11
 */
@Slf4j
public class SinaUserInfoTokenServices extends UserInfoTokenServices {

    private UserService userService;
    private AuthoritiesExtractor authoritiesExtractor = new FixedAuthoritiesExtractor();
    private String userInfoEndpointUrl;
    private String clientId;

    private OAuth2RestOperations restTemplate;

    public SinaUserInfoTokenServices(String userInfoEndpointUrl, String clientId) {
        super(userInfoEndpointUrl, clientId);
        this.userInfoEndpointUrl = userInfoEndpointUrl;
        this.clientId = clientId;
    }


    @Override
    protected Object getPrincipal(Map<String, Object> map) {
        /**
         * http://open.weibo.com/wiki/2/users/show
         *返回值字段	字段类型	字段说明
         id	int64	用户UID
         idstr	string	字符串型的用户UID
         screen_name	string	用户昵称
         name	string	友好显示名称
         province	int	用户所在省级ID
         city	int	用户所在城市ID
         location	string	用户所在地
         description	string	用户个人描述
         url	string	用户博客地址
         profile_image_url	string	用户头像地址（中图），50×50像素
         profile_url	string	用户的微博统一URL地址
         domain	string	用户的个性化域名
         weihao	string	用户的微号
         gender	string	性别，m：男、f：女、n：未知
         followers_count	int	粉丝数
         friends_count	int	关注数
         statuses_count	int	微博数
         favourites_count	int	收藏数
         created_at	string	用户创建（注册）时间
         following	boolean	暂未支持
         allow_all_act_msg	boolean	是否允许所有人给我发私信，true：是，false：否
         geo_enabled	boolean	是否允许标识用户的地理位置，true：是，false：否
         verified	boolean	是否是微博认证用户，即加V用户，true：是，false：否
         verified_type	int	暂未支持
         remark	string	用户备注信息，只有在查询用户关系时才返回此字段
         status	object	用户的最近一条微博信息字段 详细
         allow_all_comment	boolean	是否允许所有人对我的微博进行评论，true：是，false：否
         avatar_large	string	用户头像地址（大图），180×180像素
         avatar_hd	string	用户头像地址（高清），高清头像原图
         verified_reason	string	认证原因
         follow_me	boolean	该用户是否关注当前登录用户，true：是，false：否
         online_status	int	用户的在线状态，0：不在线、1：在线
         bi_followers_count	int	用户的互粉数
         lang	string	用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语
         */
        log.info("{}", map);
        String loginName = map.get("screen_name").toString();
        String thirdPartId = map.get("idstr").toString();
        String icon = map.get("profile_image_url").toString();
        String bio = map.get("description").toString();
        String sex = map.get("gender").toString().trim();
        Boolean gender = null;
        if ("m".equals(sex)) {
            gender = true;
        } else if ("f".equals(sex)) {
            gender = false;
        }

        Long id = userService.save(thirdPartId, Flag.UserTypeFlag.GITHUB, loginName, icon, gender, bio, "");
        return new UserBO(id,
                thirdPartId,
                Flag.UserTypeFlag.SINA,
                loginName,
                bio,
                icon,
                gender,
                "",
                loginName,
                thirdPartId,
                this.authoritiesExtractor.extractAuthorities(map));
    }


    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        Map<String, Object> map = getMap(userInfoEndpointUrl, accessToken);
        if (map.containsKey("error")) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("userinfo returned error: " + map.get("error"));
            }
            throw new InvalidTokenException(accessToken);
        }

        return extractAuthentication(map);
    }

    private OAuth2Authentication extractAuthentication(Map<String, Object> map) {
        Object principal = getPrincipal(map);
        List<GrantedAuthority> authorities = this.authoritiesExtractor
                .extractAuthorities(map);
        OAuth2Request request = new OAuth2Request(null, this.clientId, null, true, null,
                null, null, null, null);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                principal, "N/A", authorities);
        token.setDetails(map);
        return new OAuth2Authentication(request, token);
    }

    @SuppressWarnings({"unchecked"})
    private Map<String, Object> getMap(String path, String accessToken) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Getting user info from: " + path);
        }
        try {
            String openIdUri = "https://api.weibo.com/2/account/get_uid.json";
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(openIdUri);
            builder.queryParam("access_token", accessToken);
            Map uidMap = restTemplate.getForObject(builder.build().encode().toUri(), Map.class);
            /**
             *callback( {"client_id":"101446208","openid":"A193D12113B979C63F73211447C84A91"} );
             */
            Object uid = uidMap.get("uid");
            log.info("{},openId:{}", uidMap, uid);
            builder = UriComponentsBuilder
                    .fromHttpUrl(path);
            builder.queryParam("uid", uid);
            builder.queryParam("access_token", accessToken);
            URI userInfoUrl = builder.build().encode().toUri();
            log.info("userInfoUrl:{}", userInfoUrl.toString());
            Map result = restTemplate.getForEntity(userInfoUrl, Map.class).getBody();
            log.info("userInfo:{}", result);
            return result;
        } catch (Exception ex) {
            this.logger.warn("Could not fetch user details: " + ex.getClass() + ", "
                    + ex.getMessage());
            return Collections.<String, Object>singletonMap("error",
                    "Could not fetch user details");
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setRestTemplate(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }
}
