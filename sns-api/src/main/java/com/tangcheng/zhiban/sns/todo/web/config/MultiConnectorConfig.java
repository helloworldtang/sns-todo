package com.tangcheng.zhiban.sns.todo.web.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author tangcheng
 * 2017/11/16
 */
@Profile("prod")
@Slf4j
@Configuration
public class MultiConnectorConfig {

    @Autowired
    private ServerProperties serverProperties;

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        log.info("MultiConnectorConfig :{}", serverProperties.getPort());
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            /**
             *这个方法一定要覆盖，不然connector.setRedirectPort(serverProperties.getPort()); 不能生效
             * SpringSecurity的.portMapper().http(80).mapsTo(443)也不会生效
             * @param context context
             */
            @Override
            protected void postProcessContext(Context context) {
                //SecurityConstraint必须存在，可以通过其为不同的URL设置不同的重定向策略。
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(80);
        connector.setSecure(false);
        connector.setRedirectPort(serverProperties.getPort());
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }


}
