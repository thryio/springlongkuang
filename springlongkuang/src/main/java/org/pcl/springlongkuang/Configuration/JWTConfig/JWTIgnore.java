package org.pcl.springlongkuang.Configuration.JWTConfig;


import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JWTIgnore {
    boolean required() default true;
}
