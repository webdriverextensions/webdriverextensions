package com.github.webdriverextensions.junitrunner.annotations;

import com.github.webdriverextensions.junitrunner.NoDesiredCapabilities;
import org.openqa.selenium.Platform;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Edge {
    String version() default "";
    Platform platform() default Platform.ANY;
    String desiredCapabilities() default "";
    Class desiredCapabilitiesClass() default NoDesiredCapabilities.class;
}
