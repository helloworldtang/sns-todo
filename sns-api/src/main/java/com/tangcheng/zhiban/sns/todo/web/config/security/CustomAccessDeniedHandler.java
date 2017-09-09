package com.tangcheng.zhiban.sns.todo.web.config.security;

import com.tangcheng.zhiban.sns.todo.core.constant.Flag;
import com.tangcheng.zhiban.sns.todo.core.util.RequestHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    /**
     * https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/htmlsingle/#csrf-timeouts
     * https://stackoverflow.com/questions/32446903/what-is-the-best-way-to-handle-invalid-csrf-token-found-in-the-request-when-sess
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        LOGGER.warn("{},{},{}", Flag.BizLogFlag.WARN_CHECK, RequestHolder.getLastAccessUri(), accessDeniedException.getClass().getCanonicalName());
        if (accessDeniedException instanceof MissingCsrfTokenException
                || accessDeniedException instanceof InvalidCsrfTokenException) {
            response.sendRedirect("/");
        }
    }
}
