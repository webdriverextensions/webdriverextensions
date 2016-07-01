package com.github.webdriverextensions.junitrunner.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Browsers {
    Android[] android() default {};
    Chrome[] chrome() default {};
    Edge[] edge() default {};
    Firefox[] firefox() default {};
    HtmlUnit[] htmlUnit() default {};
    IPhone[] iPhone() default {};
    IPad[] iPad() default {};
    InternetExplorer[] internetExplorer() default {};
    Opera[] opera() default {};
    PhantomJS[] phantomJS() default {};
    Safari[] safari() default {};
    Browser[] browser() default {};
}
