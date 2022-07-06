package com.hsartori.spring.web.jaxrs.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPatternParser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class JaxrsHandlerMapper {

    // Autowired
    private final RequestMappingHandlerMapping mapping;
    private final JaxrsPathMapper pathMapper;
    private final JaxrsMethodMapper methodMapper;
    private final JaxrsMediaTypeMapper mediaTypeMapper;
    private final JaxrsParamMapper paramMapper;

    @Autowired
    public JaxrsHandlerMapper(RequestMappingHandlerMapping mapping, JaxrsPathMapper pathMapper, JaxrsMethodMapper methodMapper, JaxrsMediaTypeMapper mediaTypeMapper, JaxrsParamMapper paramMapper) {
        this.mapping = mapping;
        this.pathMapper = pathMapper;
        this.methodMapper = methodMapper;
        this.mediaTypeMapper = mediaTypeMapper;
        this.paramMapper = paramMapper;
    }

    public RequestMappingInfo toRequestMappingInfo(final Method method) {
        final var options = new RequestMappingInfo.BuilderConfiguration();
        options.setPathMatcher(new AntPathMatcher());
        options.setPatternParser(new PathPatternParser());
        options.setContentNegotiationManager(mapping.getContentNegotiationManager());
        return RequestMappingInfo
                .paths(pathMapper.getPath(method))
                .methods(methodMapper.getHttpMethods(method))
                .consumes(mediaTypeMapper.getConsumes(method))
                .produces(mediaTypeMapper.getProduces(method))
                .options(options)
                .build();
    }

    /**
     * TODO: Threat {@link javax.ws.rs.core.Response} return.
     */
    public Object getHandler(final Object bean, final Method method) {
        if (method.getReturnType().isAssignableFrom(HttpEntity.class)) {
            return buildResponseHandler(bean, method);
        } else {
            return buildBodyHandler(bean, method);
        }
    }

    private Object buildBodyHandler(final Object bean, final Method method) {
        return new Object() {

            @ResponseBody
            public Object handle() throws InvocationTargetException, IllegalAccessException {
                return method.invoke(bean);
            }

        };
    }

    private Object buildResponseHandler(final Object bean, final Method method) {
        return new Object() {

            public Object handle() throws InvocationTargetException, IllegalAccessException {
                return method.invoke(bean);
            }

        };
    }

}
