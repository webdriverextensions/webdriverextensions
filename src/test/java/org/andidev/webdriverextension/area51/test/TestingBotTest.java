package org.andidev.webdriverextension.area51.test;

import org.andidev.webdriverextension.area51.test.SeleniumGridRunner;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.andidev.webdriverextension.area51.annotations.RemoteAddress;
import org.andidev.webdriverextension.area51.annotations.browsers.Android;
import org.andidev.webdriverextension.area51.annotations.browsers.Browsers;
import org.andidev.webdriverextension.area51.annotations.browsers.Chrome;
import org.andidev.webdriverextension.area51.annotations.browsers.Firefox;
import org.andidev.webdriverextension.area51.annotations.browsers.IPad;
import org.andidev.webdriverextension.area51.annotations.browsers.IPhone;
import org.andidev.webdriverextension.area51.annotations.browsers.InternetExplorer;
import org.andidev.webdriverextension.area51.annotations.browsers.Opera;
import org.andidev.webdriverextension.area51.annotations.browsers.PhantomJS;
import org.andidev.webdriverextension.area51.annotations.browsers.Safari;
import org.junit.Assert;
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
//}, customBrowsers = {
//    @CustomBrowser(browserName = "galaxytab", platform = Platform.ANDROID),
//    @CustomBrowser(browserName = "nexusone", platform = Platform.ANDROID),
//    @CustomBrowser(browserName = "nexuss", platform = Platform.ANDROID),
//    @CustomBrowser(browserName = "htcdesire", platform = Platform.ANDROID)
//})
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

//        System.out.println("browserName = " + CurrentBrowser.getBrowserName());
//        System.out.println("browserVersion = " + CurrentBrowser.getBrowserVersion());
//        System.out.println("platformName = " + CurrentBrowser.getPlatform().toString());
//
//        CurrentBrowser.getDriver().get("http://www.google.com");
//        Assert.assertEquals("Google", CurrentBrowser.getDriver().getTitle());
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