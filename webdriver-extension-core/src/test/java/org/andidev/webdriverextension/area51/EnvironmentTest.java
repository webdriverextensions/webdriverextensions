package org.andidev.webdriverextension.area51;

import java.net.MalformedURLException;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
public class EnvironmentTest {

    @After
    public void tearDown() {
        Environment.getDriver().quit();
    }

    @Test
    public void testSetDriverByDriver() {
        Environment.setDriver(new FirefoxDriver());
        System.out.println("browserName = " + Environment.getBrowserName());
        System.out.println("browserVersion = " + Environment.getBrowserVersion());
        System.out.println("platformName = " + Environment.getPlatform().toString());
    }

    @Test
    @Ignore
    public void testSetDriverByDesiredCapabilities() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("firefox");
        desiredCapabilities.setVersion("20.0");
        desiredCapabilities.setPlatform(org.openqa.selenium.Platform.ANY);
        Environment.setDriver(desiredCapabilities);
        System.out.println("browserName = " + Environment.getBrowserName());
        System.out.println("browserVersion = " + Environment.getBrowserVersion());
        System.out.println("platformName = " + Environment.getPlatform().toString());
    }

    @Test
    @Ignore
    public void testSetDriverByBrowserName() {
        Environment.setDriver("firefox");
        System.out.println("browserName = " + Environment.getBrowserName());
        System.out.println("browserVersion = " + Environment.getBrowserVersion());
        System.out.println("platformName = " + Environment.getPlatform().toString());
    }
}