package org.andidev.webdriverextension.junitrunner;

import org.andidev.webdriverextension.internal.ThreadDriver;
import org.andidev.webdriverextension.junitrunner.SeleniumGridRunner;
import org.andidev.webdriverextension.junitrunner.annotations.RemoteAddress;
import org.andidev.webdriverextension.junitrunner.annotations.Android;
import org.andidev.webdriverextension.junitrunner.annotations.Browsers;
import org.andidev.webdriverextension.junitrunner.annotations.Chrome;
import org.andidev.webdriverextension.junitrunner.annotations.Firefox;
import org.andidev.webdriverextension.junitrunner.annotations.IPad;
import org.andidev.webdriverextension.junitrunner.annotations.IPhone;
import org.andidev.webdriverextension.junitrunner.annotations.IgnoreBrowsers;
import org.andidev.webdriverextension.junitrunner.annotations.InternetExplorer;
import org.andidev.webdriverextension.junitrunner.annotations.Opera;
import org.andidev.webdriverextension.junitrunner.annotations.Safari;
import org.andidev.webdriverextension.junitrunner.annotations.BooleanOption;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;

@RunWith(SeleniumGridRunner.class)
@RemoteAddress("http://andidev:80b7768e-dc06-4d5b-b793-5b3b83f0e24c@ondemand.saucelabs.com:80/wd/hub")
@Browsers(android = {
//    @Android(platform = Platform.LINUX)
//}, chrome = {
//    @Chrome(platform = Platform.WINDOWS),
//    @Chrome(platform = Platform.WIN8),
//    @Chrome(platform = Platform.LINUX),
//    @Chrome(platform = Platform.MAC)
}, firefox = {
    @Firefox(platform = Platform.WINDOWS, desiredCapabilities = "{}"),
    @Firefox(platform = Platform.WIN8, javascriptEnabled = BooleanOption.TRUE),
//    @Firefox(platform = Platform.LINUX),
    @Firefox(platform = Platform.MAC, javascriptEnabled = BooleanOption.FALSE)})
//}, iPhone = {
//    @IPhone(platform = Platform.MAC)
//}, iPad = {
//    @IPad(platform = Platform.MAC)
//}, internetExplorer = {
//    @InternetExplorer(platform = Platform.WINDOWS),
//    @InternetExplorer(platform = Platform.WIN8)
//}, opera = {
//    @Opera(platform = Platform.WINDOWS),
//    @Opera(platform = Platform.MAC),
//    @Opera(platform = Platform.LINUX)
//}, safari = {
//    @Safari(platform = Platform.WINDOWS),
//    @Safari(platform = Platform.MAC)
//})
//, customBrowsers = {
//    @CustomBrowser(browserName = "galaxytab", platform = Platform.ANDROID),
//    @CustomBrowser(browserName = "nexusone", platform = Platform.ANDROID),
//    @CustomBrowser(browserName = "nexuss", platform = Platform.ANDROID),
//    @CustomBrowser(browserName = "htcdesire", platform = Platform.ANDROID)
//})
@Ignore
public class SauceLabsTest {

    @Test
    @IgnoreBrowsers(firefox = {
    @Firefox(platform = Platform.WIN8),
@Firefox(platform = Platform.LINUX),
@Firefox(platform = Platform.MAC)}, safari = @Safari(platform = Platform.WINDOWS, desiredCapabilities = "{ }"))
    public void test1() throws InterruptedException {
        System.out.println("browserName = " + ThreadDriver.getBrowserName());
        System.out.println("browserVersion = " + ThreadDriver.getBrowserVersion());
        System.out.println("platformName = " + ThreadDriver.getPlatform().toString());

        ThreadDriver.getDriver().get("http://www.google.com");
        Assert.assertEquals("Google", ThreadDriver.getDriver().getTitle());
    }

//    @Test
//    public void test2() {
//        System.out.println("browserName = " + CurrentBrowser.getBrowserName());
//        System.out.println("browserVersion = " + CurrentBrowser.getBrowserVersion());
//        System.out.println("platformName = " + CurrentBrowser.getPlatform().toString());
//
//        CurrentBrowser.getDriver().get("http://www.google.com");
//        Assert.assertEquals("Google", CurrentBrowser.getDriver().getTitle());
//    }
//
//    @Test
//    public void test3() {
//        System.out.println("browserName = " + CurrentBrowser.getBrowserName());
//        System.out.println("browserVersion = " + CurrentBrowser.getBrowserVersion());
//        System.out.println("platformName = " + CurrentBrowser.getPlatform().toString());
//
//        CurrentBrowser.getDriver().get("http://www.google.com");
//        Assert.assertEquals("Google", CurrentBrowser.getDriver().getTitle());
//    }
//
//    @Test
//    @IgnoreOpera
//    @IgnoreFirefox
//    @IgnoreChrome
//    public void test8() {
//    }
}