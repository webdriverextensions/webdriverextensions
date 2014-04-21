package com.github.webdriverextensions.junitrunner.annotations;

import com.github.webdriverextensions.junitrunner.NoDesiredCapabilities;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Browser {
    public String browserName();
    public String version() default "";
    public String platform() default "ANY";
    public String desiredCapabilities() default "";
    public Class desiredCapabilitiesClass() default NoDesiredCapabilities.class;
}
