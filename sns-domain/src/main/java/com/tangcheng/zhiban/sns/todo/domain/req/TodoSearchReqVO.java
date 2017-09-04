package com.tangcheng.zhiban.sns.todo.domain.req;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by tangcheng on 9/4/2017.
 */
public class TodoSearchReqVO extends PageVO {
    private Object all;

    @NotEmpty
    @Size(max = 50)
    private String key;

    public Object getAll() {
        return all;
    }

    public void setAll(Object all) {
        this.all = all;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
