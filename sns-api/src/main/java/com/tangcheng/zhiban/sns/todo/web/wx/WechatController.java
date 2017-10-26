package com.tangcheng.zhiban.sns.todo.web.wx;

import com.mxixm.fastboot.weixin.annotation.*;
import com.mxixm.fastboot.weixin.module.event.WxEvent;
import com.mxixm.fastboot.weixin.module.message.WxMessage;
import com.mxixm.fastboot.weixin.module.message.WxMessageBody;
import com.mxixm.fastboot.weixin.module.user.WxUser;
import com.mxixm.fastboot.weixin.module.web.WxRequest;
import com.mxixm.fastboot.weixin.module.web.WxRequestBody;
import com.mxixm.fastboot.weixin.module.web.session.WxSession;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalTime;

/**
 * Created by tangcheng on 9/24/2017.
 */
@Slf4j
@WxController
public class WechatController {


    /**
     * 定义微信菜单
     */
    @WxButton(group = WxButton.Group.LEFT, main = true, name = "左")
    public void left() {
    }

    /**
     * 定义微信菜单
     */
    @WxButton(group = WxButton.Group.RIGHT, main = true, name = "Hi")
    public String right(WxUser wxUser) {
        log.info("wxUser:{}", wxUser);
        int hourOfDay = LocalTime.now().getHourOfDay();
        String wenhou;
        if (hourOfDay >= 7 && hourOfDay < 12) {
            wenhou = "上午好";
        } else if (hourOfDay == 12) {
            wenhou = "中午好";
        } else if (hourOfDay > 12 && hourOfDay < 19) {
            wenhou = "下午好";
        } else if (hourOfDay >= 19 && hourOfDay < 22) {
            wenhou = "晚上好";
        } else {
            wenhou = "太晚了。生活再忙，也要休息";
        }
        log.info("wenhou:{}", wenhou);
        return wxUser.getNickName() + "," + wenhou;
    }

    /**
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.CLICK,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.FIRST,
            name = "文本消息")
    public String leftFirst(WxRequest wxRequest, WxUser wxUser) {
        return "测试文本消息";
    }

    /**
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.VIEW,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.SECOND,
            url = "http://chaojihao.net",
            name = "点击链接")
    @WxAsyncMessage
    public WxMessage link() {
        return WxMessage.News.builder().addItem("测试图文消息", "测试", "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white.png", "http://baidu.com").build();
    }


    /**
     * 接受微信事件--退订公众号
     *
     * @param wxRequest
     * @param wxUser
     */
    @WxEventMapping(type = WxEvent.Type.UNSUBSCRIBE)
    public void unsubscribe(WxRequest wxRequest, WxUser wxUser) {
        System.out.println(wxUser.getNickName() + "退订了公众号");
    }


    /**
     * 接受微信事件--订阅公众号
     *
     * @param wxRequest
     * @param wxUser
     */
    @WxEventMapping(type = WxEvent.Type.SUBSCRIBE)
    public void subscribe(WxRequest wxRequest, WxUser wxUser) {
        System.out.println(wxUser.getNickName() + "订阅了公众号");
    }

    /**
     * 接受用户文本消息，异步返回文本消息
     *
     * @param content
     * @return dummy
     */
    @WxMessageMapping(type = WxMessage.Type.TEXT)
    @WxAsyncMessage
    public String text(WxUser wxUser, String content) {
        log.info("wxUser:{}", wxUser);
        return wxUser.getNickName() + "说了：" + content;
    }


    /**
     * 接受用户文本消息，同步返回图文消息
     *
     * @param content
     * @return dummy
     */
    @WxMessageMapping(type = WxMessage.Type.TEXT, wildcard = "1*")
    public WxMessage message(WxSession wxSession, String content) {
        wxSession.setAttribute("last", content);
        return WxMessage.News.builder()
                .addItem(WxMessageBody.News.Item.builder().title(content).description("随便一点")
                        .picUrl("http://k2.jsqq.net/uploads/allimg/1702/7_170225142233_1.png")
                        .url("http://baidu.com").build())
                .addItem(WxMessageBody.News.Item.builder().title("第二条").description("随便二点")
                        .picUrl("http://k2.jsqq.net/uploads/allimg/1702/7_170225142233_1.png")
                        .url("http://baidu.com").build())
                .build();
    }


    /**
     * 接受用户文本消息，异步返回文本消息
     *
     * @param content
     * @return dummy
     */
    @WxMessageMapping(type = WxMessage.Type.TEXT, wildcard = "2*")
    @WxAsyncMessage
    public String text2(WxRequestBody.Text text, String content) {
        boolean match = text.getContent().equals(content);
        return "收到消息内容为" + content + "!结果匹配！" + match;
    }


}
