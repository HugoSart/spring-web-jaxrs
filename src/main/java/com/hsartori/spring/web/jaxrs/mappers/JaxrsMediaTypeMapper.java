package com.hsartori.spring.web.jaxrs.mappers;

import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.lang.reflect.Method;

@Component
public class JaxrsMediaTypeMapper {

    public String[] getConsumes(final Method method) {

        // Find annotation in method or class
        var consumes = method.getAnnotation(Consumes.class);
        if (consumes == null) {
            final Class<?> type = method.getDeclaringClass();
            consumes = type.getAnnotation(Consumes.class);
        }

        // Map
        return consumes == null ? new String[]{"*/*"} : (consumes.value());

    }

    public String[] getProduces(final Method method) {

        // Find annotation in method or class
        var produces = method.getAnnotation(Produces.class);
        if (produces == null) {
            final Class<?> type = method.getDeclaringClass();
            produces = type.getAnnotation(Produces.class);
        }

        // Map
        return produces == null ? new String[]{"*/*"} : (produces.value());

    }

}
