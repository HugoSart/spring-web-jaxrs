package com.hsartori.spring.web.jaxrs.mappers;

import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import java.lang.reflect.Method;

@Component
public class JaxrsPathMapper {

    public String getPath(final Method method) {

        // Find annotation in method or class
        final var pathClass = method.getDeclaringClass().getAnnotation(Path.class);
        final var pathMethod= method.getAnnotation(Path.class);

        // Map
        return pathClass.value() + pathMethod.value();

    }

}
