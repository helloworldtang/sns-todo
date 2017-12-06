package com.tangcheng.zhiban.sns.todo.dao.biz;

import com.tangcheng.zhiban.sns.todo.core.constant.RoleEnum;
import com.tangcheng.zhiban.sns.todo.domain.mapper.SnsUserRoleDOMapper;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsUserRoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.tangcheng.zhiban.sns.todo.core.constant.RoleEnum.ADMIN;
import static com.tangcheng.zhiban.sns.todo.core.constant.RoleEnum.USER;

/**
 * Created by tangcheng on 8/31/2017.
 */
@Repository
public class UserRoleRepository {

    private final SnsUserRoleDOMapper snsUserRoleDOMapper;

    @Autowired
    public UserRoleRepository(SnsUserRoleDOMapper snsUserRoleDOMapper) {
        this.snsUserRoleDOMapper = snsUserRoleDOMapper;
    }

    public void save(Long userId, RoleEnum roleEnum) {
        long[] roleFlagArray = new long[0];
        if (roleEnum == ADMIN) {
            roleFlagArray = RoleEnum.adminRoles();
        } else if (roleEnum == USER) {
            roleFlagArray = RoleEnum.userRoles();
        }

        if (roleFlagArray.length == 0) {
            return;
        }

        List<SnsUserRoleDO> doList = new ArrayList<>(roleFlagArray.length);
        for (long roleFlag : roleFlagArray) {
            SnsUserRoleDO snsUserRoleDO = new SnsUserRoleDO();
            snsUserRoleDO.setUserId(userId);
            snsUserRoleDO.setRoleId(roleFlag);
            doList.add(snsUserRoleDO);
        }
        snsUserRoleDOMapper.insertList(doList);
    }

}
