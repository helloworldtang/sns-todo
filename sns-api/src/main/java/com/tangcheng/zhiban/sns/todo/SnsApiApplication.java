package com.tangcheng.zhiban.sns.todo;

import com.didispace.swagger.EnableSwagger2Doc;
import com.mxixm.fastboot.weixin.annotation.WxApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@WxApplication
public class SnsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnsApiApplication.class, args);
    }
}
