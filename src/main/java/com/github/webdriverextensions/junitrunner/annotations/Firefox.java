package com.github.webdriverextensions.junitrunner.annotations;

import com.github.webdriverextensions.junitrunner.NoDesiredCapabilities;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.openqa.selenium.Platform;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Firefox {
    public String version() default "";
    public Platform platform() default Platform.ANY;
    public String desiredCapabilities() default "";
    public Class desiredCapabilitiesClass() default NoDesiredCapabilities.class;
}
