package com.tangcheng.zhiban.sns.todo.web.api;

import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * sns-todo
 *
 * @author tangcheng
 * @date 6/22/2018 8:01 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class TodoDetailControllerTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + ApiVersion.API_V1 + "/test/hello/world");
    }

    @Test
    public void testDemo() {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        log.info("url:{} ,body:{}", base, response.getBody());
        assertEquals(response.getBody(), "Hello world");
    }

}