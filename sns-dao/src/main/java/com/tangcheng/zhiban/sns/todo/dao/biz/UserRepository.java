package com.tangcheng.zhiban.sns.todo.dao.biz;

import com.tangcheng.zhiban.sns.todo.core.util.NetworkUtil;
import com.tangcheng.zhiban.sns.todo.domain.mapper.SnsUserDOMapper;
import com.tangcheng.zhiban.sns.todo.domain.model.CustomUserDetails;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsUserDO;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsUserDOExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by tangcheng on 8/31/2017.
 */
@Repository
public class UserRepository {

    @Autowired
    private SnsUserDOMapper snsUserDOMapper;

    public CustomUserDetails getUser(String username) {
        return snsUserDOMapper.getUserByName(username);
    }


    public Long save(SnsUserDO snsUserDO) {
        snsUserDOMapper.insertUseGeneratedKeys(snsUserDO);
        return snsUserDO.getId();
    }

    public int updatePwd(String username, String newPwd) {
        SnsUserDO record = new SnsUserDO();
        record.setPassword(newPwd);
        record.setUpdateTime(new Date());
        record.setUpdateIp(NetworkUtil.getRemoteIp());
        SnsUserDOExample example = new SnsUserDOExample();
        example.createCriteria().andUsernameEqualTo(username);
       return snsUserDOMapper.updateByExampleSelective(record, example);
    }
}
