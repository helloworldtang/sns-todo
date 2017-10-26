package com.tangcheng.zhiban.sns.todo.web.wx;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tangcheng on 9/24/2017.
 */
@Api(tags = "oauth2.0 callback url", description = "oauth2.0 callback url")
@RestController
@RequestMapping("wx")
public class WechatCallbackController {

    public static final Logger LOGGER = LoggerFactory.getLogger(WechatCallbackController.class);

    @ApiOperation("oauth2.0 callback")
    @GetMapping("callback")
    public String callback(@RequestParam String code, @RequestParam String state) {
        LOGGER.info("code:{},state:{}", code, state);
        return code;
    }


}
