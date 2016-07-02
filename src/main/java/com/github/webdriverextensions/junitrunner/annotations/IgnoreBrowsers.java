package com.github.webdriverextensions.junitrunner.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface IgnoreBrowsers {
    IgnoreAndroid[] android() default {};
    IgnoreChrome[] chrome() default {};
    IgnoreEdge[] edge() default {};
    IgnoreFirefox[] firefox() default {};
    IgnoreHtmlUnit[] htmlUnit() default {};
    IgnoreIPhone[] iPhone() default {};
    IgnoreIPad[] iPad() default {};
    IgnoreInternetExplorer[] internetExplorer() default {};
    IgnoreOpera[] opera() default {};
    IgnorePhantomJS[] phantomJS() default {};
    IgnoreSafari[] safari() default {};
    IgnoreBrowser[] browser() default {};
}
