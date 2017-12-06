package com.tangcheng.zhiban.sns.todo.web.config;


import com.tangcheng.zhiban.sns.todo.core.constant.Flag;
import com.tangcheng.zhiban.sns.todo.core.util.NetworkUtil;
import com.tangcheng.zhiban.sns.todo.core.util.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class WebGlobalHandler {

    @ModelAttribute
    public void addAttribute(HttpServletResponse response, Model model, Exception e) {
        String lastAccessUri = RequestHolder.getLastAccessUri();
        log.warn("{} {} visit {},msg:{}", Flag.BizLogFlag.WARN_CHECK, NetworkUtil.getRemoteIp(), lastAccessUri, e.getMessage());
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
    public ModelAndView handleBindException(BindException e) {
        StringBuilder result = new StringBuilder();
        for (FieldError fieldError : e.getFieldErrors()) {
            result.append(fieldError.getField()).append(":").
                    append(fieldError.getDefaultMessage()).
                    append(System.lineSeparator());
        }
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", result.toString());
        return modelAndView;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException:{},url:{}", e.getMessage(), RequestHolder.getLastAccessUri());
        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            builder.append(fieldError.getField())
                    .append(":")
                    .append(fieldError.getDefaultMessage())
                    .append(System.lineSeparator());
        }
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", builder.toString());
        return modelAndView;
    }


    @ExceptionHandler(Exception.class)
    public ModelAndView handleDbException(Exception e) {
        log.error("default:{}", e.getMessage(), e);
        ModelAndView modelAndView = new ModelAndView("error");
        if (e instanceof SQLException || e instanceof DataAccessException) {
            String msg = "has error.Look for tangcheng@hujiang.com to solve";
            modelAndView.addObject("message", msg);
            return modelAndView;
        }
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }


}
