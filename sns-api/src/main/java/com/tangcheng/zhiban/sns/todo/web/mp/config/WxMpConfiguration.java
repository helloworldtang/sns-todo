package com.tangcheng.zhiban.sns.todo.web.mp.config;

import com.google.common.collect.Maps;
import com.tangcheng.zhiban.sns.todo.web.mp.handler.LogHandler;
import com.tangcheng.zhiban.sns.todo.web.mp.handler.MsgHandler;
import com.tangcheng.zhiban.sns.todo.web.mp.handler.SubscribeHandler;
import com.tangcheng.zhiban.sns.todo.web.mp.handler.UnsubscribeHandler;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;

import static me.chanjar.weixin.common.api.WxConsts.*;

/**
 * wechat mp configuration
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Configuration
@EnableConfigurationProperties(WxMpProperties.class)
public class WxMpConfiguration {
    private LogHandler logHandler;
    private final MsgHandler msgHandler;
    private UnsubscribeHandler unsubscribeHandler;
    private SubscribeHandler subscribeHandler;

    private WxMpProperties properties;

    private static Map<String, WxMpMessageRouter> routers = Maps.newHashMap();
    private static Map<String, WxMpService> mpServices = Maps.newHashMap();

    @Autowired
    public WxMpConfiguration(LogHandler logHandler,
                             MsgHandler msgHandler, UnsubscribeHandler unsubscribeHandler,
                             SubscribeHandler subscribeHandler, WxMpProperties properties) {
        this.logHandler = logHandler;
        this.msgHandler = msgHandler;
        this.unsubscribeHandler = unsubscribeHandler;
        this.subscribeHandler = subscribeHandler;
        this.properties = properties;
    }

    public static Map<String, WxMpMessageRouter> getRouters() {
        return routers;
    }

    public static Map<String, WxMpService> getMpServices() {
        return mpServices;
    }

    @Bean
    public Object services() {
        mpServices = this.properties.getConfigs()
                .stream()
                .map(a -> {
                    WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
                    configStorage.setAppId(a.getAppId());
                    configStorage.setSecret(a.getSecret());
                    configStorage.setToken(a.getToken());
                    configStorage.setAesKey(a.getAesKey());

                    WxMpService service = new WxMpServiceImpl();
                    service.setWxMpConfigStorage(configStorage);
                    routers.put(a.getAppId(), this.newRouter(service));
                    return service;
                }).collect(Collectors.toMap(s -> s.getWxMpConfigStorage().getAppId(), a -> a));

        return Boolean.TRUE;
    }

    private WxMpMessageRouter newRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(this.logHandler).next();

        // 关注事件
        newRouter.rule().async(true).msgType(XmlMsgType.EVENT)
                .event(EventType.SUBSCRIBE).handler(this.subscribeHandler)
                .end();

        // 取消关注事件
        newRouter.rule().async(true).msgType(XmlMsgType.EVENT)
                .event(EventType.UNSUBSCRIBE)
                .handler(this.unsubscribeHandler).end();
        // 默认
        newRouter.rule().async(false).handler(this.msgHandler).end();

        return newRouter;
    }

}
