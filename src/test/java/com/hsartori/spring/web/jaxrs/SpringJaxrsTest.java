package com.hsartori.spring.web.jaxrs;

import com.hsartori.spring.web.testing.DummyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DummyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringJaxrsTest {

    @Autowired
    private RequestMappingHandlerMapping mapping;

    @LocalServerPort
    private int port;

    private static final RestTemplate rest = new RestTemplate();

    @Test
    public void springController_works() {
        final ResponseEntity<String> r = rest.getForEntity("http://localhost:" + port + "/spring/value", String.class);
        assertEquals("0", r.getBody());
    }

    @Test
    public void jaxrsController_works() {
        final ResponseEntity<String> r = rest.getForEntity("http://localhost:" + port + "/jaxrs/value", String.class);
        assertEquals("0", r.getBody());
    }

}
