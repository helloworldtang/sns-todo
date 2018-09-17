package com.tangcheng.zhiban.sns.todo.web.mp.handler;

import com.tangcheng.zhiban.sns.todo.service.biz.MpUserService;
import com.tangcheng.zhiban.sns.todo.web.mp.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@Slf4j
public class SubscribeHandler extends AbstractHandler {

    @Autowired
    private MpUserService mpUserService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        log.info("新关注用户 OPENID: {} ", wxMessage.getFromUser());
        // 获取微信用户基本信息
        try {
            mpUserService.insertMpUser(wxMessage.getFromUser());
//            WxMpUser userWxInfo = wxMpService.getUserService()
//                    .userInfo(wxMessage.getFromUser(), null);
//            if (userWxInfo != null) {
//                // TODO 可以添加关注用户到本地数据库
//                mpUserService.insert(userWxInfo);
//            }

            return null;
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }


        try {
            return new TextBuilder().build("感谢关注", wxMessage, wxMpService);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }


}
