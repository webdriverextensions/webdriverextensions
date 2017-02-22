package com.github.webdriverextensions.junitrunner;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreFirefox;

@RunWith(WebDriverRunner.class)
@Firefox
public class IgnoreBrowserTest {

    @Test
    @IgnoreFirefox
    public void ignoredTest() {
	fail("This test should be ignored");
    }
}