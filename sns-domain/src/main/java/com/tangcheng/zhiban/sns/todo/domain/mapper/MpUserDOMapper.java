package com.tangcheng.zhiban.sns.todo.domain.mapper;


import com.tangcheng.zhiban.sns.todo.domain.model.MpUserDO;
import com.tangcheng.zhiban.sns.todo.domain.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface MpUserDOMapper extends MyMapper<MpUserDO> {
    void markUnsubscribe(@Param("openid") String openId, @Param("now") Date date);

    void markSubscribeStatus(@Param("appid") String appid, @Param("openid") String openid, @Param("subscribe") boolean subscribe, @Param("now") Date date);
}