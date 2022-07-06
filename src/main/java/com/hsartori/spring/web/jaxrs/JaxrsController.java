package com.hsartori.spring.web.jaxrs;

import org.springframework.stereotype.Component;
import java.lang.annotation.*;

/**
 * Indicated that the annotated class is a "controller" that uses Jax-RS annotations.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface JaxrsController {
    // empty
}
