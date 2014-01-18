package com.github.webdriverextensions.junitrunner;

import com.github.webdriverextensions.junitrunner.annotations.Browsers;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreBrowsers;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreFirefox;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;

//@RunWith(SeleniumGridRunner.class)
@Browsers(firefox =
    @Firefox(version = "20"))
@Ignore
public class SeleniumGridRunnerTest {

//    @Test
//    public void testSetDriverByDriver() {
//        System.out.println("browserName = " + ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getBrowserName());
//        System.out.println("browserVersion = " + ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getVersion());
//        System.out.println("platformName = " + ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getPlatform().toString());
//    }
//
//    @Test
//    @IgnoreChrome
//    public void testSetDriverByDriver2() {
//        System.out.println("browserName = " + ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getBrowserName());
//        System.out.println("browserVersion = " + ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getVersion());
//        System.out.println("platformName = " + ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getPlatform().toString());
//    }
    @Test
    @IgnoreFirefox
    public void successfulTest() {
        System.out.println("Running successfulTest");
    }
    @Test
    @IgnoreFirefox
    public void successfulTest2() {
        System.out.println("Running successfulTest");
    }
    @Test
    @IgnoreFirefox
    public void successfulTest3() {
        System.out.println("Running successfulTest");
    }
//    @Test
//    @Ignore
//    public void ignoredTest() {
//        System.out.println("Running ignoredTest");
//    }
//
//    @Test
//    public void failingTest() {
//        System.out.println("Running failingTest");
//        Assert.fail("Failing test!");
//    }
//
//    @Test
//    public void throwingExceptionTest() {
//        System.out.println("Running throwingExceptionTest");
//        throw new RuntimeException("Throwing exception from test");
//    }
}