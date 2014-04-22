package com.github.webdriverextensions.junitrunner;

import com.github.webdriverextensions.ThreadDriver;
import com.github.webdriverextensions.junitrunner.annotations.Browser;
import com.github.webdriverextensions.junitrunner.annotations.RemoteAddress;
import com.github.webdriverextensions.junitrunner.annotations.Browsers;
import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreBrowsers;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreFirefox;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreSafari;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;

@RunWith(WebDriverRunner.class)
@RemoteAddress("http://andidev:80b7768e-dc06-4d5b-b793-5b3b83f0e24c@ondemand.saucelabs.com:80/wd/hub")
@Browsers(android = { //    @Android(platform = Platform.LINUX)
//}, chrome = {
//    @Chrome(platform = Platform.WINDOWS),
//    @Chrome(platform = Platform.WIN8),
//    @Chrome(platform = Platform.LINUX),
//    @Chrome(platform = Platform.MAC)
}, firefox = {
    //    @Firefox(platform = Platform.WINDOWS, desiredCapabilities = "{}"),
    //    @Firefox(platform = Platform.WIN8),
    //    @Firefox(platform = Platform.LINUX),
    @Firefox(version = "11"), @Firefox()},
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
 browser = {
    @Browser(browserName = "galaxytab", platform = "ANDROID"),
    @Browser(browserName = "nexusone", platform = "ANDROID"),
    @Browser(browserName = "nexuss", platform = "ANDROID"),
    @Browser(browserName = "htcdesire", platform = "ANDROID")
})
public class SauceLabsTest {

    @Test
//    @IgnoreBrowsers(firefox = {
//        @Firefox(platform = Platform.WIN8),
//        @Firefox(platform = Platform.LINUX),
//        @Firefox(platform = Platform.MAC)}, safari =
//            @Safari(platform = Platform.WINDOWS))
    @IgnoreBrowsers(
//    browser = @IgnoreBrowser(browserName = "firefox"),
    firefox  = {
        @IgnoreFirefox(platform = Platform.WIN8),
        @IgnoreFirefox(platform = Platform.LINUX),
        @IgnoreFirefox(platform = Platform.MAC)}, safari
            = @IgnoreSafari(platform = Platform.WINDOWS)

    )
    public void test1() throws InterruptedException {
        ThreadDriver.getDriver().get("http://www.google.com");
        Assert.assertEquals("Google", ThreadDriver.getDriver().getTitle());
    }
//    @Test
//    public void test2() {
//        ThreadDriver.getDriver().get("http://www.google.com");
//        Assert.assertEquals("Google", ThreadDriver.getDriver().getTitle());
//    }
//
//    @Test
//    public void test3() {
//        ThreadDriver.getDriver().get("http://www.google.com");
//        Assert.assertEquals("Google", ThreadDriver.getDriver().getTitle());
//    }
//
//    @Test
//    @IgnoreOpera
//    @IgnoreFirefox
//    @IgnoreChrome
//    public void test8() {
//    }
}
