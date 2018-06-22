package com.tangcheng.zhiban.sns.todo.web.api;

import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sns-todo
 *
 * @author tangcheng
 * @date 6/22/2018 8:03 AM
 */
@Api(tags = "Demo", description = "Demo")
@RestController
@RequestMapping(ApiVersion.API_V1 + "/test")
public class TestController {

    @ApiOperation("测试用的API")
    @GetMapping("/hello/world")
    public String testDemo() {
        return "Hello world";
    }

}