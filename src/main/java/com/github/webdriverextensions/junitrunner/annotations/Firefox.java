package com.github.webdriverextensions.junitrunner.annotations;

import com.github.webdriverextensions.junitrunner.NoDesiredCapabilities;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.openqa.selenium.Platform;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Firefox {
    String version() default "";
    Platform platform() default Platform.ANY;
    String desiredCapabilities() default "";
    Class desiredCapabilitiesClass() default NoDesiredCapabilities.class;
}
