package com.tangcheng.zhiban.sns.todo.service.biz;

import com.tangcheng.zhiban.sns.todo.domain.req.ChangePwdReqVO;

/**
 * Created by tangcheng on 9/1/2017.
 */
public interface UserService {
    int changePwd(String name, ChangePwdReqVO changePwdReqVO);

    Long save(String thirdPartId, byte type, String nickname, String icon);

    Long save(String thirdPartId, byte type, String loginName, String icon, String bio, String email);

    Long save(String thirdPartId, byte type, String nickname, String icon, Boolean gender, String bio, String email);

    Long save(String thirdPartId, byte type, String nickname, String icon, Boolean gender);

}
