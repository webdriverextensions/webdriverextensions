package com.github.webdriverextensions;

import static com.github.webdriverextensions.Bot.*;
import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Android;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.HtmlUnit;
import com.github.webdriverextensions.junitrunner.annotations.IPad;
import com.github.webdriverextensions.junitrunner.annotations.IPhone;
import com.github.webdriverextensions.junitrunner.annotations.InternetExplorer;
import com.github.webdriverextensions.junitrunner.annotations.Opera;
import com.github.webdriverextensions.junitrunner.annotations.PhantomJS;
import com.github.webdriverextensions.junitrunner.annotations.RemoteAddress;
import com.github.webdriverextensions.junitrunner.annotations.Safari;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;

@RunWith(WebDriverRunner.class)
@RemoteAddress("http://andidev:80b7768e-dc06-4d5b-b793-5b3b83f0e24c@ondemand.saucelabs.com:80/wd/hub")
public class BotSauceLabsTest {

    @Test
    @Firefox
    public void browserIsTest() {
        assertThat(browser(), equalTo("firefox"));
        assertThat(browserIs("FIREFOX"), equalTo(true));
        assertThat(browserIsNot("chrome"), equalTo(true));
    }

    @Test
    @Android
    public void browserIsAndroidTest() {
        assertThat(browser(), equalTo(BrowserType.ANDROID));
        assertThat(browserIsAndroid(), equalTo(true));
        assertThat(browserIsNotChrome(), equalTo(true));
        assertThat(browserIsNotFirefox(), equalTo(true));
        assertThat(browserIsNotHtmlUnit(), equalTo(true));
        assertThat(browserIsNotIPad(), equalTo(true));
        assertThat(browserIsNotIPhone(), equalTo(true));
        assertThat(browserIsNotInternetExplorer(), equalTo(true));
        assertThat(browserIsNotOpera(), equalTo(true));
        assertThat(browserIsNotPhantomJS(), equalTo(true));
        assertThat(browserIsNotSafari(), equalTo(true));
    }

    @Test
    @Chrome
    public void browserIsChromeTest() {
        assertThat(browser(), equalTo(BrowserType.CHROME));
        assertThat(browserIsNotAndroid(), equalTo(true));
        assertThat(browserIsChrome(), equalTo(true));
        assertThat(browserIsNotFirefox(), equalTo(true));
        assertThat(browserIsNotHtmlUnit(), equalTo(true));
        assertThat(browserIsNotIPad(), equalTo(true));
        assertThat(browserIsNotIPhone(), equalTo(true));
        assertThat(browserIsNotInternetExplorer(), equalTo(true));
        assertThat(browserIsNotOpera(), equalTo(true));
        assertThat(browserIsNotPhantomJS(), equalTo(true));
        assertThat(browserIsNotSafari(), equalTo(true));
    }

    @Test
    @Firefox
    public void browserIsFirefoxTest() {
        assertThat(browser(), equalTo(BrowserType.FIREFOX));
        assertThat(browserIsNotAndroid(), equalTo(true));
        assertThat(browserIsNotChrome(), equalTo(true));
        assertThat(browserIsFirefox(), equalTo(true));
        assertThat(browserIsNotHtmlUnit(), equalTo(true));
        assertThat(browserIsNotIPad(), equalTo(true));
        assertThat(browserIsNotIPhone(), equalTo(true));
        assertThat(browserIsNotInternetExplorer(), equalTo(true));
        assertThat(browserIsNotOpera(), equalTo(true));
        assertThat(browserIsNotPhantomJS(), equalTo(true));
        assertThat(browserIsNotSafari(), equalTo(true));
    }

    @Ignore // Not available at Sauce Labs
    @Test
    @HtmlUnit
    public void browserIsHtmlUnitTest() {
        assertThat(browser(), equalTo(BrowserType.HTMLUNIT));
        assertThat(browserIsNotAndroid(), equalTo(true));
        assertThat(browserIsNotChrome(), equalTo(true));
        assertThat(browserIsNotFirefox(), equalTo(true));
        assertThat(browserIsHtmlUnit(), equalTo(true));
        assertThat(browserIsNotIPad(), equalTo(true));
        assertThat(browserIsNotIPhone(), equalTo(true));
        assertThat(browserIsNotInternetExplorer(), equalTo(true));
        assertThat(browserIsNotOpera(), equalTo(true));
        assertThat(browserIsNotPhantomJS(), equalTo(true));
        assertThat(browserIsNotSafari(), equalTo(true));
    }

    @Test
    @IPad
    public void browserIsIPadTest() {
        assertThat(browser(), equalTo(BrowserType.IPAD));
        assertThat(browserIsNotAndroid(), equalTo(true));
        assertThat(browserIsNotChrome(), equalTo(true));
        assertThat(browserIsNotFirefox(), equalTo(true));
        assertThat(browserIsNotHtmlUnit(), equalTo(true));
        assertThat(browserIsIPad(), equalTo(true));
        assertThat(browserIsNotIPhone(), equalTo(true));
        assertThat(browserIsNotInternetExplorer(), equalTo(true));
        assertThat(browserIsNotOpera(), equalTo(true));
        assertThat(browserIsNotPhantomJS(), equalTo(true));
        assertThat(browserIsNotSafari(), equalTo(true));
    }

    @Test
    @IPhone
    public void browserIsIPhoneTest() {
        assertThat(browser(), equalTo(BrowserType.IPHONE));
        assertThat(browserIsNotAndroid(), equalTo(true));
        assertThat(browserIsNotChrome(), equalTo(true));
        assertThat(browserIsNotFirefox(), equalTo(true));
        assertThat(browserIsNotHtmlUnit(), equalTo(true));
        assertThat(browserIsNotIPad(), equalTo(true));
        assertThat(browserIsIPhone(), equalTo(true));
        assertThat(browserIsNotInternetExplorer(), equalTo(true));
        assertThat(browserIsNotOpera(), equalTo(true));
        assertThat(browserIsNotPhantomJS(), equalTo(true));
        assertThat(browserIsNotSafari(), equalTo(true));
    }

    @Test
    @InternetExplorer
    public void browserIsInternetExplorerTest() {
        assertThat(browserIsNotAndroid(), equalTo(true));
        assertThat(browserIsNotChrome(), equalTo(true));
        assertThat(browserIsNotFirefox(), equalTo(true));
        assertThat(browserIsNotHtmlUnit(), equalTo(true));
        assertThat(browserIsNotIPad(), equalTo(true));
        assertThat(browserIsNotIPhone(), equalTo(true));
        assertThat(browserIsInternetExplorer(), equalTo(true));
        assertThat(browserIsNotOpera(), equalTo(true));
        assertThat(browserIsNotPhantomJS(), equalTo(true));
        assertThat(browserIsNotSafari(), equalTo(true));
    }

    @Test
    @Opera
    public void browserIsOperaTest() {
        assertThat(browser(), equalTo(BrowserType.OPERA));
        assertThat(browserIsNotAndroid(), equalTo(true));
        assertThat(browserIsNotChrome(), equalTo(true));
        assertThat(browserIsNotFirefox(), equalTo(true));
        assertThat(browserIsNotHtmlUnit(), equalTo(true));
        assertThat(browserIsNotIPad(), equalTo(true));
        assertThat(browserIsNotIPhone(), equalTo(true));
        assertThat(browserIsNotInternetExplorer(), equalTo(true));
        assertThat(browserIsOpera(), equalTo(true));
        assertThat(browserIsNotPhantomJS(), equalTo(true));
        assertThat(browserIsNotSafari(), equalTo(true));
    }

    @Test
    @PhantomJS
    @Ignore // Not available at Sauce Labs
    public void browserIsPhantomJSTest() {
        assertThat(browser(), equalTo(BrowserType.PHANTOMJS));
        assertThat(browserIsNotAndroid(), equalTo(true));
        assertThat(browserIsNotChrome(), equalTo(true));
        assertThat(browserIsNotFirefox(), equalTo(true));
        assertThat(browserIsNotHtmlUnit(), equalTo(true));
        assertThat(browserIsNotIPad(), equalTo(true));
        assertThat(browserIsNotIPhone(), equalTo(true));
        assertThat(browserIsNotInternetExplorer(), equalTo(true));
        assertThat(browserIsNotOpera(), equalTo(true));
        assertThat(browserIsPhantomJS(), equalTo(true));
        assertThat(browserIsNotSafari(), equalTo(true));
    }

    @Test
    @Safari
    public void browserIsSafariTest() {
        assertThat(browser(), equalTo(BrowserType.SAFARI));
        assertThat(browserIsNotAndroid(), equalTo(true));
        assertThat(browserIsNotChrome(), equalTo(true));
        assertThat(browserIsNotFirefox(), equalTo(true));
        assertThat(browserIsNotHtmlUnit(), equalTo(true));
        assertThat(browserIsNotIPad(), equalTo(true));
        assertThat(browserIsNotIPhone(), equalTo(true));
        assertThat(browserIsNotInternetExplorer(), equalTo(true));
        assertThat(browserIsNotOpera(), equalTo(true));
        assertThat(browserIsNotPhantomJS(), equalTo(true));
        assertThat(browserIsSafari(), equalTo(true));
    }

    @Test
    @Firefox(version = "30.0")
    public void versionIsTest() {
        assertThat(version(), equalTo("30.0"));
        assertThat(versionIs("30.0"), equalTo(true));
        assertThat(versionIsNot("31.0"), equalTo(true));
    }

    @Test
    @Firefox(platform = Platform.LINUX)
    public void platformIsTest() {
        assertThat(platform(), equalTo(Platform.LINUX));
        assertThat(platformIs(Platform.LINUX), equalTo(true));
        assertThat(platformIsNot(Platform.WINDOWS), equalTo(true));
    }

    @Test
    @Android(platform = Platform.ANDROID)
    public void platformIsAndroidTest() {
        assertThat(platformIsAndroid(), equalTo(true));
        assertThat(platformIsLinux(), equalTo(true));
        assertThat(platformIsNotMac(), equalTo(true));
        assertThat(platformIsUnix(), equalTo(true));
        assertThat(platformIsNotWindows(), equalTo(true));
        assertThat(platformIsNotWin8(), equalTo(true));
        assertThat(platformIsNotWin8_1(), equalTo(true));
        assertThat(platformIsNotVista(), equalTo(true));
        assertThat(platformIsNotXP(), equalTo(true));
    }

    @Test
    @Firefox(platform = Platform.LINUX)
    public void platformIsLinuxTest() {
        assertThat(platformIsNotAndroid(), equalTo(true));
        assertThat(platformIsLinux(), equalTo(true));
        assertThat(platformIsNotMac(), equalTo(true));
        assertThat(platformIsUnix(), equalTo(true));
        assertThat(platformIsNotWindows(), equalTo(true));
        assertThat(platformIsNotWin8(), equalTo(true));
        assertThat(platformIsNotWin8_1(), equalTo(true));
        assertThat(platformIsNotVista(), equalTo(true));
        assertThat(platformIsNotXP(), equalTo(true));
    }

    @Test
    @Safari(platform = Platform.MAC)
    public void platformIsMacTest() {
        assertThat(platformIsNotAndroid(), equalTo(true));
        assertThat(platformIsNotLinux(), equalTo(true));
        assertThat(platformIsMac(), equalTo(true));
        assertThat(platformIsNotUnix(), equalTo(true));
        assertThat(platformIsNotWindows(), equalTo(true));
        assertThat(platformIsNotWin8(), equalTo(true));
        assertThat(platformIsNotWin8_1(), equalTo(true));
        assertThat(platformIsNotVista(), equalTo(true));
        assertThat(platformIsNotXP(), equalTo(true));
    }

    @Test
    @Firefox(platform = Platform.UNIX)
    public void platformIsUnixTest() {
        assertThat(platformIsNotAndroid(), equalTo(true));
        assertThat(platformIsNotMac(), equalTo(true));
        assertThat(platformIsUnix(), equalTo(true));
        assertThat(platformIsNotWindows(), equalTo(true));
        assertThat(platformIsNotWin8(), equalTo(true));
        assertThat(platformIsNotWin8_1(), equalTo(true));
        assertThat(platformIsNotVista(), equalTo(true));
        assertThat(platformIsNotXP(), equalTo(true));
    }

    @Test
    @Firefox(platform = Platform.WINDOWS)
    public void platformIsWindowsTest() {
        assertThat(platformIsNotAndroid(), equalTo(true));
        assertThat(platformIsNotLinux(), equalTo(true));
        assertThat(platformIsNotMac(), equalTo(true));
        assertThat(platformIsNotUnix(), equalTo(true));
        assertThat(platformIsWindows(), equalTo(true));
    }

    @Ignore // since platform is not correctly set by sauce labs
    @Test
    @InternetExplorer(platform = Platform.WIN8)
    public void platformIsWin8Test() {
        assertThat(platformIsNotAndroid(), equalTo(true));
        assertThat(platformIsNotLinux(), equalTo(true));
        assertThat(platformIsNotMac(), equalTo(true));
        assertThat(platformIsNotUnix(), equalTo(true));
        assertThat(platformIsWindows(), equalTo(true));
        assertThat(platformIsWin8(), equalTo(true));
        assertThat(platformIsNotWin8_1(), equalTo(true));
        assertThat(platformIsNotVista(), equalTo(true));
        assertThat(platformIsNotXP(), equalTo(true));
    }

    @Ignore // since platform is not correctly set by sauce labs
    @Test
    @InternetExplorer(platform = Platform.WIN8)
    public void platformIsWin8_1Test() {
        assertThat(platformIsNotAndroid(), equalTo(true));
        assertThat(platformIsNotLinux(), equalTo(true));
        assertThat(platformIsNotMac(), equalTo(true));
        assertThat(platformIsNotUnix(), equalTo(true));
        assertThat(platformIsWindows(), equalTo(true));
        assertThat(platformIsNotWin8(), equalTo(true));
        assertThat(platformIsWin8_1(), equalTo(true));
        assertThat(platformIsNotVista(), equalTo(true));
        assertThat(platformIsNotXP(), equalTo(true));
    }

    @Ignore // since platform is not correctly set by sauce labs
    @Test
    @InternetExplorer(platform = Platform.VISTA)
    public void platformIsVistaTest() {
        assertThat(platformIsNotAndroid(), equalTo(true));
        assertThat(platformIsNotLinux(), equalTo(true));
        assertThat(platformIsNotMac(), equalTo(true));
        assertThat(platformIsNotUnix(), equalTo(true));
        assertThat(platformIsWindows(), equalTo(true));
        assertThat(platformIsNotWin8(), equalTo(true));
        assertThat(platformIsNotWin8_1(), equalTo(true));
        assertThat(platformIsVista(), equalTo(true));
        assertThat(platformIsNotXP(), equalTo(true));
    }

    @Ignore // since platform is not correctly set by sauce labs
    @Test
    @InternetExplorer(platform = Platform.XP)
    public void platformIsXPTest() {
        assertThat(platformIsNotAndroid(), equalTo(true));
        assertThat(platformIsNotLinux(), equalTo(true));
        assertThat(platformIsNotMac(), equalTo(true));
        assertThat(platformIsNotUnix(), equalTo(true));
        assertThat(platformIsWindows(), equalTo(true));
        assertThat(platformIsNotWin8(), equalTo(true));
        assertThat(platformIsNotWin8_1(), equalTo(true));
        assertThat(platformIsNotVista(), equalTo(true));
        assertThat(platformIsXP(), equalTo(true));
    }

}
