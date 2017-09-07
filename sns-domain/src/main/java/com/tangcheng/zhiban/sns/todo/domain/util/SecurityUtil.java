package com.tangcheng.zhiban.sns.todo.domain.util;

import com.tangcheng.zhiban.sns.todo.core.util.NetworkUtil;
import com.tangcheng.zhiban.sns.todo.core.constant.Flag;
import com.tangcheng.zhiban.sns.todo.domain.exception.ApiBizException;
import com.tangcheng.zhiban.sns.todo.domain.global.GlobalCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by tangcheng on 9/1/2017.
 */
public class SecurityUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtil.class);

    public static String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            return authentication.getName();
        }

        if (authentication instanceof AnonymousAuthenticationToken) {
            LOGGER.warn(" user {} not login,from {}", authentication.getName(), NetworkUtil.getRemoteIp());
            return authentication.getName();
        }

        LOGGER.warn("{} isAuthenticated():{},name:{},details:{}", Flag.BizLogFlag.WARN_CHECK, authentication.isAuthenticated(), authentication.getName(), authentication.getDetails());
        throw new ApiBizException(GlobalCode.UNKNOWN);

    }
}
