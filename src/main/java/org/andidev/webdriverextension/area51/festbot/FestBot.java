package org.andidev.webdriverextension.area51.festbot;

import java.util.List;
import org.andidev.webdriverextension.JUnitBot;
import org.andidev.webdriverextension.internal.BotUtils;
import org.andidev.webdriverextension.internal.Openable;
import org.andidev.webdriverextension.internal.area51.festbot.AssertTypes;
import org.andidev.webdriverextension.internal.area51.festbot.IsTypes;
import org.andidev.webdriverextension.internal.area51.festbot.PressKeysTypes;
import org.andidev.webdriverextension.internal.area51.festbot.PressTypes;
import org.andidev.webdriverextension.internal.area51.festbot.ReadDriverTypes;
import org.andidev.webdriverextension.internal.area51.festbot.ReadTypes;
import org.andidev.webdriverextension.internal.area51.festbot.SelectOptionInTypes;
import org.andidev.webdriverextension.internal.area51.festbot.SelectOptionTypes;
import org.andidev.webdriverextension.internal.area51.festbot.TypeInTypes;
import org.andidev.webdriverextension.internal.area51.festbot.TypeTypes;
import org.andidev.webdriverextension.internal.area51.festbot.WaitForTimeTypes;
import org.andidev.webdriverextension.internal.area51.festbot.WaitForWebElementTypes;
import org.andidev.webdriverextension.internal.WebDriverExtensionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FestBot {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JUnitBot.class);
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        if (threadLocalDriver.get() == null) {
            throw new WebDriverExtensionException("WebDriver in FestBot is not set. Please set the driver with FestBot.setDriver(driver) before using the FestBot static methods. Note that the driver will be thread safe since it is set with ThreadLocal so don't worry about thread safety.");
        }
        return threadLocalDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    /* Read */
    public static ReadDriverTypes read() {
        return new ReadDriverTypes(getDriver());
    }

    public static ReadTypes read(WebElement webElement) {
        return new ReadTypes(getDriver(), webElement);
    }

    /* Count */
    public static int count(List<WebElement> webElements) {
        return BotUtils.count(webElements);
    }

    /* Clear */
    public static void clear(WebElement webElement) {
        BotUtils.clear(webElement);
    }

    /* Type */
    public static TypeTypes type() {
        return new TypeTypes();
    }

    public static TypeInTypes type(String text) {
        return new TypeInTypes(text);
    }

    public static TypeInTypes type(double number) {
        return new TypeInTypes(BotUtils.toString(number));
    }

    /* Clear and Type */
    public static void clearAndType(String text, WebElement webElement) {
        BotUtils.clearAndType(text, webElement);
    }

    public static void clearAndType(double number, WebElement webElement) {
        BotUtils.clearAndTypeNumber(number, webElement);
    }

    /* Press */
    public static PressTypes press() {
        return new PressTypes();
    }

    public static PressKeysTypes press(CharSequence... keys) {
        return new PressKeysTypes(keys);

    }

    /* Click */
    public static void click(WebElement webElement) {
        BotUtils.click(webElement);
    }

    /* Select */
    public static void select(WebElement webElement) {
        BotUtils.select(webElement);
    }

    public static SelectOptionTypes select() {
        return new SelectOptionTypes();
    }

    public static SelectOptionInTypes select(String text) {
        return new SelectOptionInTypes(text);
    }

    public static void deselect(WebElement webElement) {
        BotUtils.deselect(webElement);
    }

//    public static DeselectOptionTypes deselect() {
//        return new DeselectOptionTypes();
//    }
//
//    public static DeselectOptionInTypes deselect(String text) {
//        return new DeselectOptionInTypes(text);
//    }

    /* Check */
    public static void check(WebElement webElement) {
        BotUtils.check(webElement);
    }

    public static void uncheck(WebElement webElement) {
        BotUtils.uncheck(webElement);
    }

    /* Open */
    public static void open(String url) {
        BotUtils.open(url, getDriver());
    }

    public static void open(Openable openable) {
        BotUtils.open(openable);
    }

    /* Wait */
    public static WaitForTimeTypes waitFor(double time) {
        return new WaitForTimeTypes(time);
    }

    public static WaitForWebElementTypes waitFor(WebElement webElement) {
        return new WaitForWebElementTypes(webElement);
    }

    /* Debug */

    /* Has */

    /* Is */
    public static IsTypes is(WebElement webElement) {
        return new IsTypes(getDriver(), webElement);
    }

    /* Select */
    public static AssertTypes assertThat(WebElement webElement) {
        return new AssertTypes(getDriver(), webElement);
    }
}
