package com.tangcheng.zhiban.sns.todo.web.viewcontroller;

import com.tangcheng.zhiban.sns.todo.domain.req.ChangePwdReqVO;
import com.tangcheng.zhiban.sns.todo.service.biz.UserService;
import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by tangcheng on 9/1/2017.
 */
@Controller
@RequestMapping(ApiVersion.WEB_V1 + "/user/profile")
public class UserController {

    @Autowired
    private LogoutHandler logoutHandler;

    @Autowired
    private UserService userService;

    @PostMapping("change-pwd")
    public String changePwd(HttpServletRequest request,
                            HttpServletResponse response,
                            Principal principal,
                            @Valid ChangePwdReqVO changePwdReqVO) {
        int count = userService.changePwd(principal.getName(), changePwdReqVO);
        if (count == 1) {
            logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
            return "redirect:/login";
        }
        return "profile/changePwdPage";
    }

    @GetMapping("change-pwd")
    public String changePwd() {
        return "profile/changePwdPage";
    }

}
