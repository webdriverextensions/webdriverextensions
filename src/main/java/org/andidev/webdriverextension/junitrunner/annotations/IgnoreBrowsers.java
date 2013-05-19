package org.andidev.webdriverextension.junitrunner.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreBrowsers {
    public Android[] android() default {};
    public Chrome[] chrome() default {};
    public Firefox[] firefox() default {};
    public HtmlUnit[] htmlUnit() default {};
    public IPhone[] iPhone() default {};
    public IPad[] iPad() default {};
    public InternetExplorer[] internetExplorer() default {};
    public Opera[] opera() default {};
    public PhantomJS[] phantomJS() default {};
    public Safari[] safari() default {};
    public CustomBrowser[] customBrowser() default {};
}
