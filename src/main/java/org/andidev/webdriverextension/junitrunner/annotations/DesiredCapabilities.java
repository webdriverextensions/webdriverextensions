package org.andidev.webdriverextension.junitrunner.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DesiredCapabilities {
    public String value() default "";
}
