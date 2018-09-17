package com.tangcheng.zhiban.sns.todo.service.biz;

import com.tangcheng.zhiban.sns.todo.dao.biz.MpUserRepository;
import com.tangcheng.zhiban.sns.todo.domain.model.MpUserDO;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * my-space
 *
 * @author tangcheng
 * @date 9/16/2018 10:45 PM
 */
@Service
public class MpUserServiceImpl implements MpUserService {

    @Autowired
    private MpUserRepository mpUserRepository;

    @Override
    public Long insert(WxMpUser userWxInfo) {
        MpUserDO po = new MpUserDO();
        po.setAppid("wx91c9c0fe266f1c5b");
        po.setUnionid(userWxInfo.getUnionId());
        po.setOpenid(userWxInfo.getOpenId());
        po.setSubscribe(userWxInfo.getSubscribe());
        po.setSubscribeScene(userWxInfo.getSubscribeScene());
        po.setQrScene(userWxInfo.getQrScene());
        po.setQrSceneStr(userWxInfo.getQrSceneStr());
        po.setCount(1);
        po.setNickname(userWxInfo.getNickname());
        po.setHeadImgUrl(userWxInfo.getHeadImgUrl());
        po.setCountry(userWxInfo.getCountry());
        po.setProvince(userWxInfo.getProvince());
        po.setCity(userWxInfo.getCity());
        po.setLanguage(userWxInfo.getLanguage());
        po.setSexDesc(userWxInfo.getSexDesc());
        Date now = new Date();
        po.setCreateTime(now);
        po.setUpdateTime(now);
        mpUserRepository.insertUseGeneratedKeys(po);
        return po.getId();
    }

    /**
     * 标记取关的用户
     *
     * @param openId
     */
    @Override
    public void markUnsubscribe(String openId) {
        mpUserRepository.markUnsubscribe(openId);
    }
}
