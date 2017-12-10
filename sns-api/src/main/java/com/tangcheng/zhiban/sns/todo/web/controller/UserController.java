package com.tangcheng.zhiban.sns.todo.web.controller;

import com.tangcheng.zhiban.sns.todo.domain.bo.UserBO;
import com.tangcheng.zhiban.sns.todo.domain.req.ChangePwdReqVO;
import com.tangcheng.zhiban.sns.todo.domain.res.UserProfileVO;
import com.tangcheng.zhiban.sns.todo.domain.util.SecurityUtil;
import com.tangcheng.zhiban.sns.todo.service.biz.UserService;
import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by tangcheng on 9/1/2017.
 */
@Api(value = "User Profile Value", tags = "User Profile", description = "User Profile")
@Controller
@RequestMapping(ApiVersion.WEB_V1 + "/user/profile")
public class UserController {

    @Autowired
    private LogoutHandler logoutHandler;

    @Autowired
    private UserService userService;

    @ApiOperation("do change pwd")
    @PostMapping("change-pwd")
    public String changePwd(HttpServletRequest request,
                            HttpServletResponse response,
                            Principal principal,
                            @Valid ChangePwdReqVO changePwdReqVO,
                            RedirectAttributes redirectAttributes) {
        int count = userService.changePwd(principal.getName(), changePwdReqVO);
        if (count == 1) {
            logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
            return "redirect:" + ApiVersion.WEB_V1 + "/login";
        }
        redirectAttributes.addFlashAttribute("error", true);
        return "profile/changePwdPage";
    }

    @ApiOperation("display change pwd page")
    @GetMapping("change-pwd")
    public String changePwd() {
        return "profile/changePwdPage";
    }

    @ApiOperation("display user profile")
    @GetMapping
    public String profile(Model model) {
        UserBO userBO = SecurityUtil.getUserBO();
        UserProfileVO profile = new UserProfileVO();
        BeanUtils.copyProperties(userBO, profile);
        model.addAttribute("profile", profile);
        return "profile/profilePage";
    }

}
