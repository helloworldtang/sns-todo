package com.tangcheng.zhiban.sns.todo.web.config;


import com.tangcheng.zhiban.sns.todo.core.constant.Flag;
import com.tangcheng.zhiban.sns.todo.core.util.NetworkUtil;
import com.tangcheng.zhiban.sns.todo.core.util.RequestHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebGlobalHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(WebGlobalHandler.class);

    @ExceptionHandler(Throwable.class)
    public void handException(Throwable throwable) {
        LOGGER.warn("{} {} visit {},msg:{}", Flag.BizLogFlag.WARN_CHECK, NetworkUtil.getRemoteIp(), RequestHolder.getLastAccessUri(), throwable.getMessage());
    }


}
