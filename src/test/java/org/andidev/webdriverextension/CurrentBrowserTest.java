package org.andidev.webdriverextension;

import org.andidev.webdriverextension.internal.CurrentBrowser;
import java.net.MalformedURLException;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

@Ignore
public class CurrentBrowserTest {

    @After
    public void tearDown() {
        CurrentBrowser.getDriver().quit();
    }

    @Test
    public void testSetDriverByDriver() {
        CurrentBrowser.setDriver(new FirefoxDriver());
        System.out.println("browserName = " + CurrentBrowser.getBrowserName());
        System.out.println("browserVersion = " + CurrentBrowser.getBrowserVersion());
        System.out.println("platformName = " + CurrentBrowser.getPlatform().toString());
    }

    @Test
    public void testSetDriverByDesiredCapabilities() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("firefox");
        desiredCapabilities.setVersion("20.0");
        desiredCapabilities.setPlatform(org.openqa.selenium.Platform.ANY);
        CurrentBrowser.setDriver(desiredCapabilities);
        System.out.println("browserName = " + CurrentBrowser.getBrowserName());
        System.out.println("browserVersion = " + CurrentBrowser.getBrowserVersion());
        System.out.println("platformName = " + CurrentBrowser.getPlatform().toString());
    }

    @Test
    public void testSetDriverByBrowserName() {
        CurrentBrowser.setDriver("firefox");
        System.out.println("browserName = " + CurrentBrowser.getBrowserName());
        System.out.println("browserVersion = " + CurrentBrowser.getBrowserVersion());
        System.out.println("platformName = " + CurrentBrowser.getPlatform().toString());
    }
}