package com.tangcheng.zhiban.sns.todo.web.mp.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
public abstract class AbstractBuilder {

    public abstract WxMpXmlOutMessage build(String content,
                                            WxMpXmlMessage wxMessage, WxMpService service);
}
