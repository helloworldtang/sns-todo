package com.tangcheng.zhiban.sns.todo.manager.biz;

import com.tangcheng.zhiban.sns.todo.core.constant.GenderEnum;

/**
 * Created by tangcheng on 8/31/2017.
 */
public interface UserManager {
    Long save(String thirdPartId, byte type, String nickname, String icon);

    Long save(String thirdPartId, byte type, String nickname, String icon, GenderEnum gender);

    Long save(String thirdPartId, byte type, String nickname, String icon, String bio, String email, String location);

    Long save(String thirdPartId, byte type, String nickname, String icon, GenderEnum gender, String bio, String email);

    Long save(String thirdPartId, byte type, String nickname, String icon, GenderEnum gender, String bio, String email, String location);
}
