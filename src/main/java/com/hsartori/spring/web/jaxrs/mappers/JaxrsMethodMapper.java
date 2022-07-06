package com.hsartori.spring.web.jaxrs.mappers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.HashSet;

@Component
public class JaxrsMethodMapper {

    public RequestMethod[] getHttpMethods(final Method method) {

        // Find all annotations annotated with javax.ws.rs.HttpMethod
        final var methods = new HashSet<RequestMethod>();
        for (final var annotation : method.getAnnotations()) {
            final var methodAnnotation = annotation instanceof javax.ws.rs.HttpMethod
                    ? (javax.ws.rs.HttpMethod) annotation
                    : annotation.annotationType().getAnnotation(javax.ws.rs.HttpMethod.class);
            if (methodAnnotation != null) {
                methods.add(RequestMethod.valueOf(methodAnnotation.value()));
            }
        }

        // Map
        return methods.toArray(new RequestMethod[0]);

    }

}
