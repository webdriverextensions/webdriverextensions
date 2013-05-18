package org.andidev.webdriverextension.area51.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.andidev.webdriverextension.area51.enums.BooleanOption;

@Retention(RetentionPolicy.RUNTIME)
public @interface JavascriptEnabled {
    public BooleanOption value() default BooleanOption.ANY;
}
