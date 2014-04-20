package com.github.webdriverextensions.junitrunner.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface IgnoreBrowsers {
    public IgnoreAndroid[] android() default {};
    public IgnoreChrome[] chrome() default {};
    public IgnoreFirefox[] firefox() default {};
    public IgnoreHtmlUnit[] htmlUnit() default {};
    public IgnoreIPhone[] iPhone() default {};
    public IgnoreIPad[] iPad() default {};
    public IgnoreInternetExplorer[] internetExplorer() default {};
    public IgnoreOpera[] opera() default {};
    public IgnorePhantomJS[] phantomJS() default {};
    public IgnoreSafari[] safari() default {};
    public IgnoreBrowser[] browser() default {};
}
