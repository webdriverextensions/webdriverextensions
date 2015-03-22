package com.github.webdriverextensions.junitrunner;

import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.ImplicitlyWait;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(WebDriverRunner.class)
@Firefox
@ImplicitlyWait(1)
public class WebDriverRunnerImplicitlyWaitTest {

    @Test
    public void testThatImplicitlyWaitAsClassAnnotationDoesNotThrowException() {
        System.out.println("Testing @ImplicitlyWait annotation on class and makes sure it does not throw any exception");
    }

    @Test
    @ImplicitlyWait(value = 100, unit = TimeUnit.HOURS)
    public void testThatImplicitlyWaitAsMethodAnnotationDoesNotThrowException() {
        System.out.println("Testing @ImplicitlyWait annotation on method and makes sure it does not throw any exception");
    }

}
