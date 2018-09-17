package com.tangcheng.zhiban.sns.todo.service.biz;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * my-space
 *
 * @author tangcheng
 * @date 9/16/2018 10:45 PM
 */
public interface MpUserService {
    Long insert(WxMpUser userWxInfo);

    void markUnsubscribe(String openId);
}
