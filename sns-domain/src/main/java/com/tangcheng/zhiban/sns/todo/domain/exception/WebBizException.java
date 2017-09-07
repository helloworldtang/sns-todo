package com.tangcheng.zhiban.sns.todo.domain.exception;

import com.tangcheng.zhiban.sns.todo.domain.global.BizError;
import com.tangcheng.zhiban.sns.todo.domain.global.GlobalCode;

public class WebBizException extends RuntimeException {

    private Integer code = GlobalCode.UNKNOWN.getCode();

    public WebBizException(BizError bizError) {
        this(bizError, null);
    }

    public WebBizException(BizError bizError, Throwable cause) {
        super(bizError.getMsg(), cause);
        this.setCode(bizError.getCode());
    }


    public Integer getCode() {
        return code;
    }

    private void setCode(Integer code) {
        this.code = code;
    }


}
