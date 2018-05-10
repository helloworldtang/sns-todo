package com.tangcheng.zhiban.sns.todo.web.config.security;

import com.tangcheng.zhiban.sns.todo.domain.exception.CaptchaException;
import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tangcheng on 8/26/2017.
 */
public class LoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    public static final String PASS_ERROR_URL = ApiVersion.WEB_V1 + "/login?error";
    public static final String CODE_ERROR_URL = ApiVersion.WEB_V1 + "/login?code";
    public static final String EXPIRED_URL = ApiVersion.WEB_V1 + "/login?expire";
    public static final String DISABLED_URL = ApiVersion.WEB_V1 + "/login?disabled";
    public static final String LOCKED_URL = ApiVersion.WEB_V1 + "/login?locked";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {

        if (exception instanceof CaptchaException) {
            getRedirectStrategy().sendRedirect(request, response, CODE_ERROR_URL);
        } else {
            getRedirectStrategy().sendRedirect(request, response, PASS_ERROR_URL);
        }
    }


}
