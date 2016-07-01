package com.github.webdriverextensions.junitrunner.annotations;

import org.openqa.selenium.Platform;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface IgnoreEdge {
    String version() default "";
    Platform platform() default Platform.ANY;
}
