package com.hsartori.spring.web.jaxrs;

import com.hsartori.spring.web.jaxrs.mappers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPatternParser;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Order(0)
@Configuration
public class JaxrsControllerRegister {

    private static final Logger log = LogManager.getLogger(JaxrsControllerRegister.class);

    // Autowired
    private final RequestMappingHandlerMapping mapping;
    private final JaxrsControllerScanner scanner;
    private final JaxrsHandlerMapper handlerMapper;

    @Autowired
    public JaxrsControllerRegister(RequestMappingHandlerMapping mapping, JaxrsControllerScanner scanner,
                                   JaxrsHandlerMapper handlerMapper) {
        this.mapping = mapping;
        this.scanner = scanner;
        this.handlerMapper = handlerMapper;
    }

    @PostConstruct
    protected void register() {
        final var beans = scanner.getJaxrsControllers();
        for (final var bean : beans.values()) {
            for (final var method : bean.getClass().getDeclaredMethods()) {
                final var info = handlerMapper.toRequestMappingInfo(method);
                final var handler = handlerMapper.getHandler(bean, method);
                try {
                    mapping.registerMapping(info, handler, handler.getClass().getMethod("handle"));
                    log.debug("Mapped Jax-RS endpoint {}", info);
                } catch (final NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
