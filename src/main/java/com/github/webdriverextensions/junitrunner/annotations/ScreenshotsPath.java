package com.github.webdriverextensions.junitrunner.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Sets path to the directory where the screenshots are saved
 *
 * @see com.github.webdriverextensions.junitrunner.WebDriverRunner
 * @see com.github.webdriverextensions.junitrunner.annotations.TakeScreenshotOnFailure
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
public @interface ScreenshotsPath {
    String value();
}
