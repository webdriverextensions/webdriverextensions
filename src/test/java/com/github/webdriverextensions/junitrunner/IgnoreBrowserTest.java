package com.github.webdriverextensions.junitrunner;

import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreFirefox;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(WebDriverRunner.class)
@Firefox()
public class IgnoreBrowserTest {

    @Test
    @IgnoreFirefox
    public void ignoredTest() {
        assert false; // Fail test if test runs
    }
}