package com.tangcheng.zhiban.sns.todo.web.config.security.qq;

import com.tangcheng.zhiban.sns.todo.web.config.QQTokenHttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangcheng
 * 2017/12/07
 */
public class QQOAuth2RestTemplate extends OAuth2RestTemplate {


    public QQOAuth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
        super(resource, context);
        QQTokenHttpMessageConverter qqTokenHttpMessageConverter = new QQTokenHttpMessageConverter();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter());//for https://graph.qq.com/oauth2.0/me
        messageConverters.add(qqTokenHttpMessageConverter); //for  token
        messageConverters.add(new MappingJackson2HttpMessageConverter() {
            @Override
            protected boolean canRead(MediaType mediaType) {
                return true;
            }
        });//for userInfo need custom

        this.setMessageConverters(messageConverters);
        this.setAccessTokenProvider(new QQAuthorizationCodeAccessTokenProvider(messageConverters));
    }


}
