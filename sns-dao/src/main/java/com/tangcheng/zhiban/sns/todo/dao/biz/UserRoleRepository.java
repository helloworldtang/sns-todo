package com.tangcheng.zhiban.sns.todo.dao.biz;

import com.tangcheng.zhiban.sns.todo.domain.mapper.SnsUserRoleDOMapper;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsUserRoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by tangcheng on 8/31/2017.
 */
@Repository
public class UserRoleRepository {

    @Autowired
    private SnsUserRoleDOMapper snsUserRoleDOMapper;

    public void save(Long userId, long userRoleFlag) {
        SnsUserRoleDO snsUserRoleDO = new SnsUserRoleDO();
        snsUserRoleDO.setUserId(userId);
        snsUserRoleDO.setRoleId(userRoleFlag);
        snsUserRoleDOMapper.insert(snsUserRoleDO);
    }
}
