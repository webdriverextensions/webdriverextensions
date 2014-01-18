package com.github.webdriverextensions.junitrunner.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.openqa.selenium.Platform;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface IgnoreOpera {
    public String version() default "";
    public Platform platform() default Platform.ANY;
    public BooleanOption javascriptEnabled() default BooleanOption.ANY;
    public String desiredCapabilities() default "";
}
