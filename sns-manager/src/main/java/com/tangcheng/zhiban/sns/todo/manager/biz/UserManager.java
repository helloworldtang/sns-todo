package com.tangcheng.zhiban.sns.todo.manager.biz;
/**
 * Created by tangcheng on 8/31/2017.
 */
public interface UserManager {
    Long save(String thirdPartId, byte type, String nickname, String icon);

    Long save(String thirdPartId, byte type, String nickname, String icon, Boolean gender);

    Long save(String thirdPartId, byte type, String nickname, String icon, String bio, String email);

    Long save(String thirdPartId, byte type, String nickname, String icon, Boolean gender, String bio, String email);
}
