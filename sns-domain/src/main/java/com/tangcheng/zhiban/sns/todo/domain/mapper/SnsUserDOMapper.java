package com.tangcheng.zhiban.sns.todo.domain.mapper;

import com.tangcheng.zhiban.sns.todo.domain.model.CustomUserDetails;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsUserDO;
import com.tangcheng.zhiban.sns.todo.domain.util.MyMapper;

public interface SnsUserDOMapper extends MyMapper<SnsUserDO> {
    CustomUserDetails getUserByName(String username);
}