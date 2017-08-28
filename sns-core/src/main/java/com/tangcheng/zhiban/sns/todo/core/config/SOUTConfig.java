package com.tangcheng.zhiban.sns.todo.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4JServletContextListener;

@Configuration
public class SOUTConfig {
    @Bean
    public SysOutOverSLF4JServletContextListener slf4JServletContextListener() {
        return new SysOutOverSLF4JServletContextListener();
    }

}
