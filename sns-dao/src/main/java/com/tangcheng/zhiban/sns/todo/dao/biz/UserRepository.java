package com.tangcheng.zhiban.sns.todo.dao.biz;

import com.tangcheng.zhiban.sns.todo.domain.mapper.SnsUserDOMapper;
import com.tangcheng.zhiban.sns.todo.domain.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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


}
