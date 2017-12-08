package com.tangcheng.zhiban.sns.todo.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.oauth2.common.OAuth2AccessToken.*;

/**
 * @author tangcheng
 * 2017/12/07
 */
@Slf4j
public class QQTokenHttpMessageConverter implements GenericHttpMessageConverter<OAuth2AccessToken> {


    @Override
    public boolean canRead(Type type, Class contextClass, MediaType mediaType) {
        log.info("canRead TypeName:{},TypeName CanonicalName:{},mediaType:{}", type.getTypeName(), type.getClass().getCanonicalName(), mediaType);
        if (type.equals(OAuth2AccessToken.class) && getSupportedMediaTypes().contains(mediaType)) {
            return true;
        }
        return false;
    }


    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        log.info("{},{}", clazz.getCanonicalName(), mediaType);
        return canRead(clazz, null, mediaType);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(MediaType.TEXT_HTML);
    }

    @Override
    public OAuth2AccessToken read(Class<? extends OAuth2AccessToken> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return read(clazz, null, inputMessage);
    }

    @Override
    public OAuth2AccessToken read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Charset charset = getContentTypeCharset(inputMessage.getHeaders().getContentType());
        String tokenResult = StreamUtils.copyToString(inputMessage.getBody(), charset);
        log.info("tokenResult:{}", tokenResult);
        String[] results = tokenResult.split("&");
        if (results.length == 3) {
            Map<String, String> tokenParams = new HashMap<>();
            tokenParams.put(ACCESS_TOKEN, results[0].replace("access_token=", ""));
            tokenParams.put(EXPIRES_IN, results[1].replace("expires_in=", ""));
            tokenParams.put(REFRESH_TOKEN, results[2].replace("refresh_token=", ""));
            return DefaultOAuth2AccessToken.valueOf(tokenParams);
        }
        return null;
    }

    @Override
    public boolean canWrite(Type type, Class clazz, MediaType mediaType) {
        log.info("canWrite TypeName:{},TypeName CanonicalName:{},class:{},mediaType:{}", type.getTypeName(), type.getClass().getCanonicalName(), clazz.getCanonicalName(), mediaType);
        return false;
    }

    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public void write(OAuth2AccessToken oAuth2AccessToken, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }

    @Override
    public void write(OAuth2AccessToken oAuth2AccessToken, Type type, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }

    private Charset getContentTypeCharset(MediaType contentType) {
        if (contentType != null && contentType.getCharset() != null) {
            return contentType.getCharset();
        } else {
            return Charset.forName("ISO-8859-1");
        }
    }


}
