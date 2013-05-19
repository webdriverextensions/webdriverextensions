package org.andidev.webdriverextension.junitrunner.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.openqa.selenium.Platform;

@Retention(RetentionPolicy.RUNTIME)
public @interface IPhone {
    public String version() default "";
    public Platform platform() default Platform.ANY;
    public BooleanOption javascriptEnabled() default BooleanOption.ANY;
    public String desiredCapabilities() default "";
}
