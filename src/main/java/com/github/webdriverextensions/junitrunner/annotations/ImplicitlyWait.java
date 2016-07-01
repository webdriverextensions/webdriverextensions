package com.github.webdriverextensions.junitrunner.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

/**
 * Sets the driver's implicitly wait
 *
 * <p>Annotate a test class to set implicitly wait for all tests</p>
 * <pre>
 * &#064;RunWith(WebDriverRunner.class)
 * &#064;Firefox
 * &#064;ImplicitlyWait(1)
 * public class SomeTest {
 *     &#064;Test
 *     public void somethingToTest() {
 *         // Implicittly wait is set to one second
 *     }
 *     &#064;Test
 *     public void somethingElseToTest() {
 *         // Implicittly wait is set to one second
 *     }
 * }</pre>
 *
 * <p>Annotate a test class method to set implicitly wait for a single test</p>
 * <pre>
 * &#064;RunWith(WebDriverRunner.class)
 * &#064;Firefox
 * &#064;ImplicitlyWait(1)
 * public class SomeTest {
 *     &#064;Test
 *     public void somethingToTest() {
 *         // Implicittly wait is set to one second
 *     }
 *     &#064;Test
 *     &#064;ImplicitlyWait(value = 1, unit = MINUTES)
 *     public void somethingElseToTest() {
 *         // Implicittly wait is set to one minute
 *     }
 * }</pre>
 *
 * <p>The annotation sets the implicitly wait by calling {@code driver.manage().timeouts().implicitlyWait(value, unit)}</p>
 *
 * @see com.github.webdriverextensions.junitrunner.WebDriverRunner
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ImplicitlyWait {
    int value();
    TimeUnit unit() default TimeUnit.SECONDS;
}
