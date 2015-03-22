package com.github.webdriverextensions.junitrunner.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enables taking screenshots on test failure in the WebDriverRunner
 *
 * @see com.github.webdriverextensions.junitrunner.WebDriverRunner
 * @see com.github.webdriverextensions.junitrunner.annotations.ScreenshotsPath
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
public @interface TakeScreenshotOnFailure {}
