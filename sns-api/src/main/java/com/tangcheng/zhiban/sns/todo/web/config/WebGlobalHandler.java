package com.tangcheng.zhiban.sns.todo.web.config;


import com.tangcheng.zhiban.sns.todo.core.constant.Flag;
import com.tangcheng.zhiban.sns.todo.core.util.NetworkUtil;
import com.tangcheng.zhiban.sns.todo.core.util.RequestHolder;
import com.tangcheng.zhiban.sns.todo.domain.global.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@ControllerAdvice
public class WebGlobalHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(WebGlobalHandler.class);


    @ModelAttribute
    public void addAttribute(HttpServletResponse response, Model model, Exception e) {
        String lastAccessUri = RequestHolder.getLastAccessUri();
        LOGGER.warn("{} {} visit {},msg:{}", Flag.BizLogFlag.WARN_CHECK, NetworkUtil.getRemoteIp(), lastAccessUri, e.getMessage());
        model.addAttribute("uri", lastAccessUri);
        model.addAttribute("status", response.getStatus());
    }


    @ExceptionHandler(Throwable.class)
    public ModelAndView handException(Throwable throwable) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", throwable.getMessage());
        //todo 只是想加个日志，看看有没有更优雅的方式
        return modelAndView;
    }


    @ExceptionHandler(BindException.class)
    public ModelAndView handleBindException(BindException e, ModelAndView modelAndView) {
        modelAndView.setViewName("error");
        StringBuilder result = new StringBuilder();
        for (FieldError fieldError : e.getFieldErrors()) {
            result.append(fieldError.getField()).append(":").
                    append(fieldError.getDefaultMessage()).
                    append(System.lineSeparator());
        }
        modelAndView.addObject("message", result.toString());
        return modelAndView;
    }


}
