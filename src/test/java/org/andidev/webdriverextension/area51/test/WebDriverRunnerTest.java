package org.andidev.webdriverextension.area51.test;

import org.andidev.webdriverextension.area51.annotations.browsers.Browsers;
import org.andidev.webdriverextension.area51.annotations.browsers.Chrome;
import org.andidev.webdriverextension.area51.annotations.browsers.Firefox;
import org.andidev.webdriverextension.area51.annotations.browsers.IgnoreBrowsers;
import org.andidev.webdriverextension.area51.annotations.browsers.IgnoreFirefox;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;

//@RunWith(SeleniumGridRunner.class)
@Browsers(firefox =
    @Firefox(version = "20"))
public class WebDriverRunnerTest {

//    @Test
//    public void testSetDriverByDriver() {
//        System.out.println("browserName = " + CurrentBrowser.getBrowserName());
//        System.out.println("browserVersion = " + CurrentBrowser.getBrowserVersion());
//        System.out.println("platformName = " + CurrentBrowser.getPlatform().toString());
//    }
//
//    @Test
//    @IgnoreChrome
//    public void testSetDriverByDriver2() {
//        System.out.println("browserName = " + CurrentBrowser.getBrowserName());
//        System.out.println("browserVersion = " + CurrentBrowser.getBrowserVersion());
//        System.out.println("platformName = " + CurrentBrowser.getPlatform().toString());
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