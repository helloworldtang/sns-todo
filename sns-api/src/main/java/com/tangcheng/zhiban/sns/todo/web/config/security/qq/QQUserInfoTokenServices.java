package com.tangcheng.zhiban.sns.todo.web.config.security.qq;

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
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
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
 * 2017/12/08
 */
@Slf4j
public class QQUserInfoTokenServices extends UserInfoTokenServices {
    private String userInfoEndpointUrl;
    private OAuth2RestOperations restTemplate;
    private String clientId;

    private AuthoritiesExtractor authoritiesExtractor = new FixedAuthoritiesExtractor();


    /**
     * 获取 OpenID 的 API 地址
     */
    private final static String openIdUri = "https://graph.qq.com/oauth2.0/me";
    private UserService userService;


    public QQUserInfoTokenServices(String userInfoEndpointUrl, String clientId) {
        super(userInfoEndpointUrl, clientId);
        this.clientId = clientId;
        this.userInfoEndpointUrl = userInfoEndpointUrl;
    }

    @Override
    protected Object getPrincipal(Map<String, Object> map) {
        return new UserBO(Long.parseLong(
                map.get("id").toString()),
                map.get("thirdPartId").toString(),
                Flag.UserTypeFlag.QQ,
                map.get("nickname").toString(),
                "",
                map.get("icon").toString(),
                "",
                map.get("thirdPartId").toString(),
                map.get("thirdPartId").toString(),
                this.authoritiesExtractor.extractAuthorities(map));
    }

    @Override
    public void setRestTemplate(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
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
            OAuth2RestOperations restTemplate = this.restTemplate;
            if (restTemplate == null) {
                BaseOAuth2ProtectedResourceDetails resource = new BaseOAuth2ProtectedResourceDetails();
                resource.setClientId(this.clientId);
                restTemplate = new OAuth2RestTemplate(resource);
            }
            OAuth2AccessToken existingToken = restTemplate.getOAuth2ClientContext()
                    .getAccessToken();
            if (existingToken == null || !accessToken.equals(existingToken.getValue())) {
                DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(
                        accessToken);
                String tokenType = DefaultOAuth2AccessToken.BEARER_TYPE;
                token.setTokenType(tokenType);
                restTemplate.getOAuth2ClientContext().setAccessToken(token);
            }
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(openIdUri);
            builder.queryParam("access_token", accessToken);
            String resultText = restTemplate.getForObject(builder.build().encode().toUri(), String.class);
            /**
             *callback( {"client_id":"101446208","openid":"A193D12113B979C63F73211447C84A91"} );
             */
            String openIdStr = resultText.split(":")[2];
            String openId = openIdStr.substring(1, openIdStr.lastIndexOf("\""));
            log.info("{},openId:{}", resultText, openId);
            builder = UriComponentsBuilder
                    .fromHttpUrl(path);
            builder.queryParam("openid", openId);
            builder.queryParam("access_token", accessToken);
            builder.queryParam("oauth_consumer_key", clientId);
            builder.queryParam("format", "json");
            URI userInfoUrl = builder.build().encode().toUri();
            log.info("userInfoUrl:{}", userInfoUrl.toString());

            /**
             * 参数说明	描述
             ret	返回码
             msg	如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。
             nickname	用户在QQ空间的昵称。
             figureurl	大小为30×30像素的QQ空间头像URL。
             figureurl_1	大小为50×50像素的QQ空间头像URL。
             figureurl_2	大小为100×100像素的QQ空间头像URL。
             figureurl_qq_1	大小为40×40像素的QQ头像URL。
             figureurl_qq_2	大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有。
             gender	性别。 如果获取不到则默认返回"男"
             is_yellow_vip	标识用户是否为黄钻用户（0：不是；1：是）。
             vip	标识用户是否为黄钻用户（0：不是；1：是）
             yellow_vip_level	黄钻等级
             level	黄钻等级
             is_yellow_year_vip	标识是否为年费黄钻用户（0：不是； 1：是）
             */
            Map result = restTemplate.getForEntity(userInfoUrl, Map.class).getBody();
            log.info("userInfo:{}", result);
            if (Integer.parseInt(result.get("ret").toString()) == 0) {
                String icon = result.get("figureurl_2").toString();
                Long id = userService.save(openId, Flag.UserTypeFlag.QQ, result.get("nickname").toString(), icon);
                result.put("id", id);
                result.put("thirdPartId", openId);
                result.put("icon", icon);
            }
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

}
