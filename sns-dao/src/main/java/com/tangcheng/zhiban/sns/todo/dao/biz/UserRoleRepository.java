package com.tangcheng.zhiban.sns.todo.dao.biz;

import com.tangcheng.zhiban.sns.todo.domain.mapper.SnsUserRoleDOMapper;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsUserRoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.tangcheng.zhiban.sns.todo.core.constant.Flag.UserRoleFlag;

/**
 * Created by tangcheng on 8/31/2017.
 */
@Repository
public class UserRoleRepository {

    @Autowired
    private SnsUserRoleDOMapper snsUserRoleDOMapper;

    public void save(Long userId, long userRoleFlag) {
        long[] roleFlagArray = new long[0];
        if (userRoleFlag == UserRoleFlag.ADMIN) {
            roleFlagArray = UserRoleFlag.adminRoles();
        } else if (userRoleFlag == UserRoleFlag.USER) {
            roleFlagArray = UserRoleFlag.userRoles();
        }

        List<SnsUserRoleDO> doList = new ArrayList<>(roleFlagArray.length);
        for (long roleFlag : roleFlagArray) {
            SnsUserRoleDO snsUserRoleDO = new SnsUserRoleDO();
            snsUserRoleDO.setUserId(userId);
            snsUserRoleDO.setRoleId(roleFlag);
            doList.add(snsUserRoleDO);
        }
        if (doList.isEmpty()) {
            return;
        }
        snsUserRoleDOMapper.insertList(doList);
    }
}
