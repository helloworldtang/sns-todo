package com.tangcheng.zhiban.sns.todo;

import com.mxixm.fastboot.weixin.annotation.EnableWxMvc;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@EnableWxMvc(menuAutoCreate = false)//订阅号没有创建菜单的权限，订阅号没有需要开发的
@SpringBootApplication
public class SnsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnsApiApplication.class, args);
    }
}
