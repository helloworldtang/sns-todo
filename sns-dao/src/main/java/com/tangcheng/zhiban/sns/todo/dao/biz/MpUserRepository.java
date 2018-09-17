package com.tangcheng.zhiban.sns.todo.dao.biz;

import com.tangcheng.zhiban.sns.todo.domain.mapper.MpUserDOMapper;
import com.tangcheng.zhiban.sns.todo.domain.model.MpUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * my-space
 *
 * @author tangcheng
 * @date 9/16/2018 10:23 PM
 */
@Repository
public class MpUserRepository {

    @Autowired
    private MpUserDOMapper mpUserPOMapper;

    public void insertUseGeneratedKeys(MpUserDO po) {
        MpUserDO userDO = new MpUserDO();
        userDO.setAppid(po.getAppid());
        userDO.setOpenid(po.getOpenid());
        int count = mpUserPOMapper.selectCount(userDO);
        if (count == 0) {
            po.setQrSceneStr("现在有134个follower了，加油");
            mpUserPOMapper.insertUseGeneratedKeys(po);
            return;
        }
        mpUserPOMapper.markSubscribeStatus(po.getAppid(), po.getOpenid(), true, new Date());
    }

    public void markUnsubscribe(String appid, String openId) {
        mpUserPOMapper.markSubscribeStatus(appid, openId, false, new Date());
    }
}
