package com.github.webdriverextensions.junitrunner.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
public @interface DriverPaths {
    String chrome() default "";
    String edge() default "";
    String internetExplorer() default "";
}
