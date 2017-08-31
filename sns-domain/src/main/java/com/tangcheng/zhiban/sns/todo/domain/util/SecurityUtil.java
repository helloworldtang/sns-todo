package com.tangcheng.zhiban.sns.todo.domain.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Created by tangcheng on 9/1/2017.
 */
public class SecurityUtil {
    public static String getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        return user.getUsername();
    }
}
