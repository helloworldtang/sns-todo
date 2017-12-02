package com.tangcheng.zhiban.sns.todo.web.global;

import com.tangcheng.zhiban.sns.todo.core.util.NetworkUtil;
import com.tangcheng.zhiban.sns.todo.core.util.RequestHolder;
import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

import static com.tangcheng.zhiban.sns.todo.core.constant.Flag.BizLogFlag.WARN_CHECK;

@Controller
@RequestMapping(ApiVersion.WEB_V1 + "/global")
public class GlobalController {

    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalController.class);

    @GetMapping("error")
    public ModelAndView globalError(HttpServletRequest request) {
        String remoteIp = NetworkUtil.getRemoteIp();
        String lastAccessUri = RequestHolder.getLastAccessUri();

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("uri", request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE));
        modelAndView.addObject("status", request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE));
        Object error = request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);
        modelAndView.addObject("error", error);
        Object msg = request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE);
        modelAndView.addObject("message", msg);
        LOGGER.warn("{} {} visit {},error:{},msg:{}", WARN_CHECK, remoteIp, lastAccessUri, error, msg);
        return modelAndView;
    }

}
