package com.tangcheng.zhiban.sns.todo.domain.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tangcheng on 8/26/2017.
 */
@Configuration
@MapperScan("com.tangcheng.zhiban.sns.todo.domain.mapper")
public class MapperConfig {
    /**
     *
     * 2017-08-26 20:29:19.301  WARN 5248 --- [           main] o.m.s.mapper.ClassPathMapperScanner      : No MyBatis mapper was found in '[com.tangcheng.zhiban.sns.todo]' package. Please check your configuration.
     */
}
