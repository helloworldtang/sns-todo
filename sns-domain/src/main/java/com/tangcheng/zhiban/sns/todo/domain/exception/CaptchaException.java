package com.tangcheng.zhiban.sns.todo.domain.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by tangcheng on 8/26/2017.
 */
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg, Throwable t) {
        super(msg, t);
    }

    public CaptchaException(String msg) {
        super(msg);
    }


}
