package com.tangcheng.zhiban.sns.todo.web.config.security.qq;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;

import java.util.List;

/**
 * https://www.cnblogs.com/kingsy/p/6375881.html
 *
 * @author tangcheng
 * 2017/12/07
 */
public class QQAuthorizationCodeAccessTokenProvider extends AuthorizationCodeAccessTokenProvider {

    public QQAuthorizationCodeAccessTokenProvider(List<HttpMessageConverter<?>> messageConverters) {
        this.setMessageConverters(messageConverters);
    }

}
