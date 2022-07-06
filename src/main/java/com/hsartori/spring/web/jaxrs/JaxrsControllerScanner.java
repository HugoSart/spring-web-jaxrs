package com.hsartori.spring.web.jaxrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JaxrsControllerScanner {

    // Autowired
    private final ApplicationContext context;

    @Autowired
    public JaxrsControllerScanner(ApplicationContext context) {
        this.context = context;
    }

    public Map<String, Object> getJaxrsControllers() {
        return context.getBeansWithAnnotation(JaxrsController.class);
    }

}
