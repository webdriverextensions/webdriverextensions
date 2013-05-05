package org.andidev.webdriverextension.area51.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DesiredCapability {
    public String key();
    public String value();
}
