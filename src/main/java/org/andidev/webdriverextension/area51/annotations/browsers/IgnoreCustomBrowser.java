package org.andidev.webdriverextension.area51.annotations.browsers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.andidev.webdriverextension.area51.enums.BooleanOption;

@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreCustomBrowser {
    public String browserName();
    public String version() default "";
    public String platform() default "";
    public BooleanOption javascriptEnabled() default BooleanOption.ANY;
    public String desiredCapabilities() default "";
}
