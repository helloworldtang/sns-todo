package com.tangcheng.zhiban.sns.todo.domain.util;

import com.tangcheng.zhiban.sns.todo.core.constant.Flag;
import com.tangcheng.zhiban.sns.todo.core.util.NetworkUtil;
import com.tangcheng.zhiban.sns.todo.domain.bo.UserBO;
import com.tangcheng.zhiban.sns.todo.domain.exception.ApiBizException;
import com.tangcheng.zhiban.sns.todo.domain.global.GlobalCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * Created by tangcheng on 9/1/2017.
 */
@Slf4j
public class SecurityUtil {

    public static String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            return authentication.getName();
        }

        if (authentication instanceof OAuth2Authentication) {
            log.info("third part login.authentication:{}, user {},from {}", authentication, authentication.getName(), NetworkUtil.getRemoteIp());
            return authentication.getName();
        }

        if (authentication instanceof AnonymousAuthenticationToken) {
            log.warn(" user {} not login,from {}", authentication.getName(), NetworkUtil.getRemoteIp());
            return authentication.getName();
        }

        log.warn("{} isAuthenticated():{},name:{},details:{}", Flag.BizLogFlag.WARN_CHECK, authentication.isAuthenticated(), authentication.getName(), authentication.getDetails());
        throw new ApiBizException(GlobalCode.UNKNOWN);
    }


    public static Long getUserId() {
        return getUserBO().getId();
    }

    public static UserBO getUserBO() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserBO) {
            return (UserBO) principal;
        }
        log.info("has no userId");
        throw new ApiBizException(GlobalCode.UNKNOWN);
    }


}
