package com.tangcheng.zhiban.sns.todo.domain.global;

/**
 * Created by tangcheng on 8/26/2017.
 */
public class ResultData<T> {
    private Integer code;
    private String msg;
    private T items;

    public ResultData(BizError bizError) {
        this(bizError, null);
    }

    public ResultData(BizError bizError, T items) {
        this.code = bizError.getCode();
        this.msg = bizError.getMsg();
        this.items = items;
    }

    public ResultData(T items) {
        this(GlobalCode.SUCCESS, items);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }
}
