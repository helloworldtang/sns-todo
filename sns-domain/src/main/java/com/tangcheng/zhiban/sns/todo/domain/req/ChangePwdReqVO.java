package com.tangcheng.zhiban.sns.todo.domain.req;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by tangcheng on 9/1/2017.
 */
public class ChangePwdReqVO {

    @NotEmpty
    private String oldPwd;

    @NotEmpty
    private String newPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
