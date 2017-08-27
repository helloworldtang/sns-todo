package com.tangcheng.zhiban.sns.todo.domain.global;

public enum GlobalCode implements BizError {
    SUCCESS(0, "success"),
    FAIL(-1, "fail"),
    NOT_EXIST(-2, "not exist");
    private final int code;
    private final String msg;

    GlobalCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}