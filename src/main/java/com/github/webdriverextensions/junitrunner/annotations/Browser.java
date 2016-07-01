package com.github.webdriverextensions.junitrunner.annotations;

import com.github.webdriverextensions.junitrunner.NoDesiredCapabilities;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Browser {
    String browserName();
    String version() default "";
    String platform() default "ANY";
    String desiredCapabilities() default "";
    Class desiredCapabilitiesClass() default NoDesiredCapabilities.class;
}
