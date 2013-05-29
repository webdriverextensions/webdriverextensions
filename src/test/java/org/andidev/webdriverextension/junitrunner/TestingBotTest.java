package org.andidev.webdriverextension.junitrunner;

import org.andidev.webdriverextension.junitrunner.SeleniumGridRunner;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.andidev.webdriverextension.junitrunner.annotations.RemoteAddress;
import org.andidev.webdriverextension.junitrunner.annotations.Android;
import org.andidev.webdriverextension.junitrunner.annotations.Browsers;
import org.andidev.webdriverextension.junitrunner.annotations.Chrome;
import org.andidev.webdriverextension.junitrunner.annotations.Firefox;
import org.andidev.webdriverextension.junitrunner.annotations.IPad;
import org.andidev.webdriverextension.junitrunner.annotations.IPhone;
import org.andidev.webdriverextension.junitrunner.annotations.InternetExplorer;
import org.andidev.webdriverextension.junitrunner.annotations.Opera;
import org.andidev.webdriverextension.junitrunner.annotations.PhantomJS;
import org.andidev.webdriverextension.junitrunner.annotations.Safari;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;

// Problem with running
// browserName=android, version=, platform=ANDROID
@RunWith(SeleniumGridRunner.class)
@RemoteAddress("http://863f4cca30fc77b59d4d5c171d96b0ea:487d3cf91deac1da52d43156941de39d@europe.testingbot.com:4444/wd/hub")
@Browsers(
        android = { //    @Android(platform = Platform.ANDROID)
//},
//        chrome = {
//    @Chrome(platform = Platform.WINDOWS),
//    @Chrome(platform = Platform.WIN8),
//    @Chrome(platform = Platform.LINUX)
//                ,
//    @Chrome(platform = Platform.MAC)
//}, firefox = {
//    @Firefox(platform = Platform.WINDOWS),
//    @Firefox(platform = Platform.WIN8),
//    @Firefox(platform = Platform.LINUX)
//        ,
//    @Firefox(platform = Platform.MAC)
}, iPhone = { //    @IPhone(platform = Platform.MAC)
}, iPad = { //    @IPad(platform = Platform.MAC)
//}, internetExplorer = {
//    @InternetExplorer(platform = Platform.WINDOWS),
//    @InternetExplorer(platform = Platform.WIN8)
}, opera = {
    //    @Opera(platform = Platform.WINDOWS),
    @Opera(platform = Platform.MAC)
}, phantomJS = { //    @PhantomJS(platform = Platform.LINUX)
}, safari = {
    @Safari(platform = Platform.WINDOWS),
    @Safari(platform = Platform.MAC)
})
//}, browsers = {
//    @Browser(browserName = "galaxytab", platform = Platform.ANDROID),
//    @Browser(browserName = "nexusone", platform = Platform.ANDROID),
//    @Browser(browserName = "nexuss", platform = Platform.ANDROID),
//    @Browser(browserName = "htcdesire", platform = Platform.ANDROID)
//})
@Ignore
public class TestingBotTest {

    @Test
    public void openGoogleTest() {
        try {
            int l = (new Random()).nextInt(10000 - 0 + 1) + 0;
            System.out.println("sleep = " + 10);
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeleniumGridRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Running test 1!!!");

//        System.out.println("browserName = " + ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getBrowserName());
//        System.out.println("browserVersion = " + ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getVersion());
//        System.out.println("platformName = " + ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getPlatform().toString());
//
//        ThreadDriver.getDriver().get("http://www.google.com");
//        Assert.assertEquals("Google", ThreadDriver.getDriver().getTitle());
    }
    //    @Test
//    @IgnoreFirefox
//    public void successfulTest() {
//        System.out.println("Running successfulTest");
//    }
//
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