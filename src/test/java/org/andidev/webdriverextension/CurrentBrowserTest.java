package org.andidev.webdriverextension;

import org.andidev.webdriverextension.internal.ThreadDriver;
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
        ThreadDriver.getDriver().quit();
    }

    @Test
    public void testSetDriverByDriver() {
        ThreadDriver.setDriver(new FirefoxDriver());
        System.out.println("browserName = " + ThreadDriver.getBrowserName());
        System.out.println("browserVersion = " + ThreadDriver.getBrowserVersion());
        System.out.println("platformName = " + ThreadDriver.getPlatform().toString());
    }

    @Test
    public void testSetDriverByDesiredCapabilities() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("firefox");
        desiredCapabilities.setVersion("20.0");
        desiredCapabilities.setPlatform(org.openqa.selenium.Platform.ANY);
        ThreadDriver.setDriver(desiredCapabilities);
        System.out.println("browserName = " + ThreadDriver.getBrowserName());
        System.out.println("browserVersion = " + ThreadDriver.getBrowserVersion());
        System.out.println("platformName = " + ThreadDriver.getPlatform().toString());
    }

    @Test
    public void testSetDriverByBrowserName() {
        ThreadDriver.setDriver("firefox");
        System.out.println("browserName = " + ThreadDriver.getBrowserName());
        System.out.println("browserVersion = " + ThreadDriver.getBrowserVersion());
        System.out.println("platformName = " + ThreadDriver.getPlatform().toString());
    }
}