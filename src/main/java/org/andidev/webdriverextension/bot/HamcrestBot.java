package org.andidev.webdriverextension.bot;

import java.util.List;
import org.andidev.webdriverextension.exceptions.WebDriverExtensionException;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

public class HamcrestBot {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HamcrestBot.class);
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        if (threadLocalDriver.get() == null) {
            throw new WebDriverExtensionException("WebDriver in HamcrestBot is not set. Please set the driver with JUnitBot.setDriver(driver) before using the JUnitBot static methods. Note that the driver will be thread safe since it is set with ThreadLocal so don't worry about thread safety.");
        }
        return threadLocalDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public static String tagNameOf(WebElement webElement) {
        return webElement.getTagName();
    }

    public static String idIn(WebElement webElement) {
        return webElement.getAttribute("id");
    }

    public static String nameIn(WebElement webElement) {
        return webElement.getAttribute("name");
    }

    public static String classIn(WebElement webElement) {
        return webElement.getAttribute("class");
    }

    public static String valueIn(WebElement webElement) {
        return webElement.getAttribute("vale");
    }

    public static String hrefIn(WebElement webElement) {
        return webElement.getAttribute("href");
    }

    public static String textIn(WebElement webElement) {
        return webElement.getText();
    }

    public static Double numberIn(WebElement webElement) {
        return NumberUtils.createDouble(webElement.getText());
    }

    public static int numberOf(List<WebElement> webElements) {
        return webElements.size();
    }

    public static String url() {
        return getDriver().getCurrentUrl();
    }
}
