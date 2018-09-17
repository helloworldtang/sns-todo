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
        mpUserPOMapper.insertUseGeneratedKeys(po);
    }

    public void markUnsubscribe(String openId) {
        mpUserPOMapper.markUnsubscribe(openId,new Date());
    }
}
