package com.tangcheng.zhiban.sns.todo.web.mp.handler;

import com.tangcheng.zhiban.sns.todo.service.biz.MpUserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@Slf4j
public class UnsubscribeHandler extends AbstractHandler {

    @Autowired
    private MpUserService mpUserService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        String openId = wxMessage.getFromUser();
        log.info("取消关注用户 OPENID: {}  ", openId);
        // TODO 可以更新本地数据库为取消关注状态
        mpUserService.markUnsubscribe(openId);

        return null;
    }

}
