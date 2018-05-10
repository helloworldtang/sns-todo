package com.tangcheng.zhiban.sns.todo.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;
import java.util.List;

/**
 * Created by tangcheng on 8/26/2017.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(ApiVersion.WEB_V1 + "/login").setViewName("login");
        registry.addViewController("/").setViewName("redirect:" + ApiVersion.WEB_V1 + "/user/todo?finished=false");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        // See SPR-7316,for weixin token verify.fastjson will convert string 123 to "123"
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        converters.add(stringHttpMessageConverter);
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(fastJsonHttpMessageConverter());
    }

    private FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter httpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteEnumUsingToString,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        fastJsonConfig.setSerializeFilters(new ValueFilter() {
            @Override
            public Object process(Object o, String s, Object source) {
                if (source == null) {
                    return "";
                }
                if (source instanceof Date) {
                    return ((Date) source).getTime();
                }
                return source;
            }
        });
        httpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return httpMessageConverter;
    }


}
