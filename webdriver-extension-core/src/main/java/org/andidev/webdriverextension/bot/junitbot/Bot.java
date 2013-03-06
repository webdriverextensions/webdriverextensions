package org.andidev.webdriverextension.bot.junitbot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.andidev.webdriverextension.Openable;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import static org.andidev.webdriverextension.bot.junitbot.BotUtils.*;
import org.apache.commons.lang3.StringUtils;

public abstract class Bot {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Bot.class);

    /* Click */
    public static void click(WebElement webElement) {
        webElement.click();
    }

    /* Read */
    public static String read(WebElement webElement) {
        return webElement.getText();
    }

    public static Double readNumber(WebElement webElement) {
        try {
            return NumberUtils.createDouble(webElement.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static List<String> readOptions(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        List<String> optionTexts = new ArrayList<String>();
        for (WebElement option : options) {
            optionTexts.add(read(option));
        }
        return optionTexts;
    }

    public static String readUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public static String readTagName(WebElement webElement) {
        return webElement.getTagName();
    }

    public static String readAttribute(String name, WebElement webElement) {
        return webElement.getAttribute(name);
    }

    public static String readId(WebElement webElement) {
        return readAttribute("id", webElement);
    }

    public static String readName(WebElement webElement) {
        return readAttribute("name", webElement);
    }

    public static String readClass(WebElement webElement) {
        return readAttribute("class", webElement);
    }

    public static String[] readClasses(WebElement webElement) {
        return StringUtils.split(readClass(webElement));
    }

    public static String readValue(WebElement webElement) {
        return readAttribute("value", webElement);
    }

    public static String readHref(WebElement webElement) {
        return readAttribute("href", webElement);
    }

    /* Clear */
    public static void clear(WebElement webElement) {
        webElement.clear();
    }

    /* Type */
    public static void type(String text, WebElement webElement) {
        if (text == null) {
            return;
        }
        webElement.sendKeys(text);
    }

    public static void typeNumber(Double number, WebElement webElement) {
        if (number == null) {
            return;
        }
        type(number.toString(), webElement);
    }

    public static void clearAndType(String text, WebElement webElement) {
        clear(webElement);
        type(text, webElement);
    }

    public static void clearAndTypeNumber(Double number, WebElement webElement) {
        clear(webElement);
        typeNumber(number, webElement);
    }

    /* Press */
    public static void pressEnter(WebElement webElement) {
        pressKeys(webElement, Keys.ENTER);
    }

    public static void pressKeys(WebElement webElement, CharSequence... keys) {
        webElement.sendKeys(keys);
    }

    /* Select */

    /* Select Option */

    /* Check/Uncheck */
    public static void check(WebElement webElement) {
        if (isUnchecked(webElement)) {
            click(webElement);
        }
    }

    public static void uncheck(WebElement webElement) {
        if (isChecked(webElement)) {
            click(webElement);
        }
    }

    /* Open */
    public static void open(String url, WebDriver driver) {
        driver.get(url);
    }

    public static void open(Openable openable) {
        openable.open();
    }

    /* Count */
    public static int count(List<? extends WebElement> webElements) {
        return webElements.size();
    }

    /* Wait */
    public static void delay(double seconds) {
        if (seconds > 0) {
            try {
                TimeUnit.MILLISECONDS.sleep((long) seconds * 1000);
            } catch (InterruptedException ex) {
                // Swallow exception
                ex.printStackTrace();
            }
        }
    }

    public static void delay(long time, TimeUnit unit) {
        if (time > 0) {
            try {
                unit.sleep(time);
            } catch (InterruptedException ex) {
                // Swallow exception
                ex.printStackTrace();
            }
        }
    }

    public static void waitForElementToDisplay(WebElement webElement, WebDriver driver) {
        waitForElementToDisplay(webElement, 30, driver);
    }

    public static void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds, long sleepInMillis, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, sleepInMillis);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /* Debug */
    public static void debug(String str) {
        log.debug(str);
    }

    public static void debug(WebElement webElement) {
        log.debug("Tag <{}> has class = \"{}\"", readTagName(webElement), readClass(webElement));
    }

    public static void debug(List<? extends WebElement> webElements) {
        for (WebElement webElement : webElements) {
            debug(webElement);
        }
    }

    public static void debugText(WebElement webElement) {
        log.debug("Tag <{}> contains text: \"{}\"", readTagName(webElement), read(webElement));
    }

    public static void debugText(List<? extends WebElement> webElements) {
        for (WebElement webElement : webElements) {
            debugText(webElement);
        }
    }

    public static void debugNumberOfElements(List<? extends WebElement> webElements) {
        log.debug("List contains {} number of tags");
    }

    /* Tag Name */
    public static boolean isTagName(String value, WebElement webElement) {
        return is(value, readTagName(webElement));
    }

    public static boolean isTagNameNot(String value, WebElement webElement) {
        return isNot(value, readTagName(webElement));
    }

    public static void assertTagName(String value, WebElement webElement) {
        assertIs("Tag name", value, readTagName(webElement));
    }

    public static void assertTagNameNot(String value, WebElement webElement) {
        assertIsNot("Tag name", value, readTagName(webElement));
    }

    /* Attribute */
    public static boolean hasAttribute(String name, WebElement webElement) {
        return webElement.getAttribute(name) != null;
    }

    public static boolean hasNotAttribute(String name, WebElement webElement) {
        return !hasAttribute(name, webElement);
    }

    public static boolean isAttribute(String name, String value, WebElement webElement) {
        return is(value, readAttribute(name, webElement));
    }

    public static boolean isAttributeNot(String name, String value, WebElement webElement) {
        return isNot(value, readAttribute(name, webElement));
    }

    public static boolean isAttributeContaining(String name, String searchText, WebElement webElement) {
        return isContaining(searchText, readAttribute(name, webElement));
    }

    public static boolean isAttributeNotContaining(String name, String searchText, WebElement webElement) {
        return isNotContaining(searchText, readAttribute(name, webElement));
    }

    public static boolean isAttributeStartingWith(String name, String prefix, WebElement webElement) {
        return isStartingWith(prefix, readAttribute(name, webElement));
    }

    public static boolean isAttributeNotStartingWith(String name, String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readAttribute(name, webElement));
    }

    public static boolean isAttributeEndingWith(String name, String suffix, WebElement webElement) {
        return isEndingWith(suffix, readAttribute(name, webElement));
    }

    public static boolean isAttributeNotEndingWith(String name, String suffix, WebElement webElement) {
        return isNotEndingWith(suffix, readAttribute(name, webElement));
    }

    public static void assertHasAttribute(String name, WebElement webElement) {
        if (hasNotAttribute(name, webElement)) {
            Assert.fail(describeTag(webElement, name) + " does not have the " + name + " attribute!");
        }
    }

    public static void assertHasNotAttribute(String name, WebElement webElement) {
        if (hasAttribute(name, webElement)) {
            Assert.fail(describeTag(webElement, name) + " has the " + name + " attribute when it shouldn't!");
        }
    }

    public static void assertAttribute(String name, String value, WebElement webElement) {
        assertIs(name, value, readAttribute(name, webElement));
    }

    public static void assertAttributeNot(String name, String value, WebElement webElement) {
        assertIsNot(name, value, readAttribute(name, webElement));
    }

    public static void assertAttributeContains(String name, String searchText, WebElement webElement) {
        assertContains(name, searchText, readAttribute(name, webElement));
    }

    public static void assertAttributeNotContains(String name, String searchText, WebElement webElement) {
        assertNotContains(name, searchText, readAttribute(name, webElement));
    }

    public static void assertAttributeStartsWith(String name, String prefix, WebElement webElement) {
        assertStartsWidth(name, prefix, readAttribute(name, webElement));
    }

    public static void assertAttributeNotStartsWith(String name, String prefix, WebElement webElement) {
        assertNotStartsWidth(name, prefix, readAttribute(name, webElement));
    }

    public static void assertAttributeEndsWith(String name, String suffix, WebElement webElement) {
        assertEndsWidth(name, suffix, readAttribute(name, webElement));
    }

    public static void assertAttributeNotEndsWith(String name, String suffix, WebElement webElement) {
        assertNotEndsWidth(name, suffix, readAttribute(name, webElement));
    }

    /* Id */
    public static boolean hasId(WebElement webElement) {
        return hasAttribute("id", webElement);
    }

    public static boolean hasNotId(WebElement webElement) {
        return hasNotAttribute("id", webElement);
    }

    public static boolean isId(String value, WebElement webElement) {
        return is(value, readId(webElement));
    }

    public static boolean isIdNot(String value, WebElement webElement) {
        return isNot(value, readId(webElement));
    }

    public static boolean isIdContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readId(webElement));
    }

    public static boolean isIdNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readId(webElement));
    }

    public static boolean isIdStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, readId(webElement));
    }

    public static boolean isIdNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readId(webElement));
    }

    public static boolean isIdEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readId(webElement));
    }

    public static boolean isIdNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readId(webElement));
    }

    public static void assertHasId(WebElement webElement) {
        assertHasAttribute("id", webElement);
    }

    public static void assertHasNotId(WebElement webElement) {
        assertHasNotAttribute("id", webElement);
    }

    public static void assertId(String value, WebElement webElement) {
        assertIs("id", value, readId(webElement));
    }

    public static void assertIdNot(String value, WebElement webElement) {
        assertIsNot("id", value, readId(webElement));
    }

    public static void assertIdContains(String searchText, WebElement webElement) {
        assertContains("id", searchText, readId(webElement));
    }

    public static void assertIdNotContains(String searchText, WebElement webElement) {
        assertNotContains("id", searchText, readId(webElement));
    }

    public static void assertIdStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("id", prefix, readId(webElement));
    }

    public static void assertIdNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("id", prefix, readId(webElement));
    }

    public static void assertIdEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("id", suffix, readId(webElement));
    }

    public static void assertIdNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("id", suffix, readId(webElement));
    }

    /* Name */
    public static boolean hasName(WebElement webElement) {
        return hasAttribute("name", webElement);
    }

    public static boolean hasNotName(WebElement webElement) {
        return hasNotAttribute("name", webElement);
    }

    public static boolean isName(String value, WebElement webElement) {
        return is(value, readName(webElement));
    }

    public static boolean isNameNot(String value, WebElement webElement) {
        return isNot(value, readName(webElement));
    }

    public static boolean isNameContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readName(webElement));
    }

    public static boolean isNameNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readName(webElement));
    }

    public static boolean isNameStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, readName(webElement));
    }

    public static boolean isNameNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readName(webElement));
    }

    public static boolean isNameEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readName(webElement));
    }

    public static boolean isNameNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readName(webElement));
    }

    public static void assertHasName(WebElement webElement) {
        assertHasAttribute("name", webElement);
    }

    public static void assertHasNotName(WebElement webElement) {
        assertHasNotAttribute("name", webElement);
    }

    public static void assertName(String value, WebElement webElement) {
        assertIs("name", value, readName(webElement));
    }

    public static void assertNameNot(String value, WebElement webElement) {
        assertIsNot("name", value, readName(webElement));
    }

    public static void assertNameContains(String searchText, WebElement webElement) {
        assertContains("name", searchText, readName(webElement));
    }

    public static void assertNameNotContains(String searchText, WebElement webElement) {
        assertNotContains("name", searchText, readName(webElement));
    }

    public static void assertNameStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("name", prefix, readName(webElement));
    }

    public static void assertNameNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("name", prefix, readName(webElement));
    }

    public static void assertNameEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("name", suffix, readName(webElement));
    }

    public static void assertNameNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("name", suffix, readName(webElement));
    }

    /* Class */
    public static boolean hasClass(WebElement webElement) {
        return hasAttribute("class", webElement);
    }

    public static boolean hasNotClass(WebElement webElement) {
        return hasNotAttribute("class", webElement);
    }

    public static boolean hasClass(String className, WebElement webElement) {
        return readClass(webElement).matches("(\\\"|\\s)" + className.trim() + "(\\\"|\\s)");
    }

    public static boolean hasNotClass(String className, WebElement webElement) {
        return !hasClass(className, webElement);
    }

    public static boolean hasClassContaining(String searchText, WebElement webElement) {
        String[] classes = readClasses(webElement);
        for (String clazz : classes) {
            if (isContaining(searchText, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasClassNotContaining(String searchText, WebElement webElement) {
        return !hasClassContaining(searchText, webElement);
    }

    public static boolean hasClassStartingWith(String prefix, WebElement webElement) {
        String[] classes = readClasses(webElement);
        for (String clazz : classes) {
            if (isStartingWith(prefix, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasClassNotStartingWith(String prefix, WebElement webElement) {
        return !hasClassStartingWith(prefix, webElement);
    }

    public static boolean hasClassEndingWith(String suffix, WebElement webElement) {
        String[] classes = readClasses(webElement);
        for (String clazz : classes) {
            if (isEndingWith(suffix, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasClassNotEndingWith(String suffix, WebElement webElement) {
        return !hasClassEndingWith(suffix, webElement);
    }

    public static void assertHasClass(WebElement webElement) {
        assertHasAttribute("class", webElement);
    }

    public static void assertHasNotClass(WebElement webElement) {
        assertHasNotAttribute("class", webElement);
    }

    public static void assertHasClass(String className, WebElement webElement) {
        if (hasNotClass(className, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class " + className.trim() + "!");
        }
    }

    public static void assertHasNotClass(String className, WebElement webElement) {
        if (hasClass(className, webElement)) {
            Assert.fail(describeTag(webElement) + " has class " + className.trim() + " when it shouldn't!");
        }
    }

    public static void assertHasClassContains(String searchText, WebElement webElement) {
        if (hasClassNotContaining(searchText, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class containing text " + searchText.trim() + "!");
        }
    }

    public static void assertHasClassNotContains(String searchText, WebElement webElement) {
        if (hasClassContaining(searchText, webElement)) {
            Assert.fail(describeTag(webElement) + " has class containing text " + searchText.trim() + " when it shouldn't!");
        }
    }

    public static void assertHasClassStartsWith(String prefix, WebElement webElement) {
        if (hasClassNotStartingWith(prefix, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class containing prefix " + prefix.trim() + "!");
        }
    }

    public static void assertHasClassNotStartsWith(String prefix, WebElement webElement) {
        if (hasClassStartingWith(prefix, webElement)) {
            Assert.fail(describeTag(webElement) + " has class containing prefix " + prefix.trim() + " when it shouldn't!");
        }
    }

    public static void assertHasClassEndsWith(String suffix, WebElement webElement) {
        if (hasClassNotEndingWith(suffix, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class containing suffix " + suffix.trim() + "!");
        }
    }

    public static void assertHasClassNotEndsWith(String suffix, WebElement webElement) {
        if (hasClassEndingWith(suffix, webElement)) {
            Assert.fail(describeTag(webElement) + " has class containing suffix " + suffix.trim() + " when it shouldn't!");
        }
    }

    /* Value */
    public static boolean hasValue(WebElement webElement) {
        return hasAttribute("value", webElement);
    }

    public static boolean hasNotValue(WebElement webElement) {
        return hasNotAttribute("value", webElement);
    }

    public static boolean isValue(String value, WebElement webElement) {
        return is(value, readValue(webElement));
    }

    public static boolean isValueNot(String value, WebElement webElement) {
        return isNot(value, readValue(webElement));
    }

    public static boolean isValueContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readValue(webElement));
    }

    public static boolean isValueNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readValue(webElement));
    }

    public static boolean isValueStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, readValue(webElement));
    }

    public static boolean isValueNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readValue(webElement));
    }

    public static boolean isValueEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readValue(webElement));
    }

    public static boolean isValueNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readValue(webElement));
    }

    public static void assertHasValue(WebElement webElement) {
        assertHasAttribute("value", webElement);
    }

    public static void assertHasNotValue(WebElement webElement) {
        assertHasNotAttribute("value", webElement);
    }

    public static void assertValue(String value, WebElement webElement) {
        assertIs("value", value, readValue(webElement));
    }

    public static void assertValueNot(String value, WebElement webElement) {
        assertIsNot("value", value, readValue(webElement));
    }

    public static void assertValueContains(String searchText, WebElement webElement) {
        assertContains("value", searchText, readValue(webElement));
    }

    public static void assertValueNotContains(String searchText, WebElement webElement) {
        assertNotContains("value", searchText, readValue(webElement));
    }

    public static void assertValueStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("value", prefix, readValue(webElement));
    }

    public static void assertValueNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("value", prefix, readValue(webElement));
    }

    public static void assertValueEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("value", suffix, readValue(webElement));
    }

    public static void assertValueNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("value", suffix, readValue(webElement));
    }


    /* Href */
    public static boolean hasHref(WebElement webElement) {
        return hasAttribute("href", webElement);
    }

    public static boolean hasNotHref(WebElement webElement) {
        return hasNotAttribute("href", webElement);
    }

    public static boolean isHref(String value, WebElement webElement) {
        return is(value, readHref(webElement));
    }

    public static boolean isHrefNot(String value, WebElement webElement) {
        return isNot(value, readHref(webElement));
    }

    public static boolean isHrefContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readHref(webElement));
    }

    public static boolean isHrefNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readHref(webElement));
    }

    public static boolean isHrefStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, readHref(webElement));
    }

    public static boolean isHrefNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readHref(webElement));
    }

    public static boolean isHrefEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readHref(webElement));
    }

    public static boolean isHrefNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readHref(webElement));
    }

    public static void assertHasHref(WebElement webElement) {
        assertHasAttribute("href", webElement);
    }

    public static void assertHasNotHref(WebElement webElement) {
        assertHasNotAttribute("href", webElement);
    }

    public static void assertHref(String value, WebElement webElement) {
        assertIs("href", value, readHref(webElement));
    }

    public static void assertHrefNot(String value, WebElement webElement) {
        assertIsNot("href", value, readHref(webElement));
    }

    public static void assertHrefContains(String searchText, WebElement webElement) {
        assertContains("href", searchText, readHref(webElement));
    }

    public static void assertHrefNotContains(String searchText, WebElement webElement) {
        assertNotContains("href", searchText, readHref(webElement));
    }

    public static void assertHrefStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("href", prefix, readHref(webElement));
    }

    public static void assertHrefNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("href", prefix, readHref(webElement));
    }

    public static void assertHrefEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("href", suffix, readHref(webElement));
    }

    public static void assertHrefNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("href", suffix, readHref(webElement));
    }

    /* Text */
    public static boolean isText(String text, WebElement webElement) {
        return is(text, read(webElement));
    }

    public static boolean isTextNot(String text, WebElement webElement) {
        return isNot(text, read(webElement));
    }

    public static boolean isTextContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, read(webElement));
    }

    public static boolean isTextNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, read(webElement));
    }

    public static boolean isTextStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, read(webElement));
    }

    public static boolean isTextNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, read(webElement));
    }

    public static boolean isTextEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, read(webElement));
    }

    public static boolean isTextNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, read(webElement));
    }

    public static void assertText(String text, WebElement webElement) {
        assertIs("Text", text, read(webElement));
    }

    public static void assertTextNot(String text, WebElement webElement) {
        assertIsNot("Text", text, read(webElement));
    }

    public static void assertTextContains(String searchText, WebElement webElement) {
        assertContains("Text", searchText, read(webElement));
    }

    public static void assertTextNotContains(String searchText, WebElement webElement) {
        assertNotContains("Text", searchText, read(webElement));
    }

    public static void assertTextStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("Text", prefix, read(webElement));
    }

    public static void assertTextNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("Text", prefix, read(webElement));
    }

    public static void assertTextEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("Text", suffix, read(webElement));
    }

    public static void assertTextNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("Text", suffix, read(webElement));
    }

    /* Number */
    public static boolean isNumber(Double number, WebElement webElement) {
        return is(number, readNumber(webElement));
    }

    public static boolean isNumberNot(Double number, WebElement webElement) {
        return isNot(number, readNumber(webElement));
    }

    public static boolean isNumberSmallerThan(Double number, WebElement webElement) {
        return isSmallerThan(number, readNumber(webElement));
    }

    public static boolean isNumberSmallerThanOrEquals(Double number, WebElement webElement) {
        return isSmallerThanOrEquals(number, readNumber(webElement));
    }

    public static boolean isNumberLargerThan(Double number, WebElement webElement) {
        return isLargerThan(number, readNumber(webElement));
    }

    public static boolean isNumberLargerThanOrEquals(Double number, WebElement webElement) {
        return isLargerThanOrEquals(number, readNumber(webElement));
    }

    public static void assertNumber(Double number, WebElement webElement) {
        assertIs("Number", number, readNumber(webElement));
    }

    public static void assertNumberNot(Double number, WebElement webElement) {
        assertIsNot("Number", number, readNumber(webElement));
    }

    public static void assertNumberSmallerThan(Double number, WebElement webElement) {
        assertIsSmallerThan("Number", number, readNumber(webElement));
    }

    public static void assertNumberSmallerThanOrEquals(Double number, WebElement webElement) {
        assertIsSmallerThanOrEquals("Number", number, readNumber(webElement));
    }

    public static void assertNumberLargerThan(Double number, WebElement webElement) {
        assertIsLargerThan("Number", number, readNumber(webElement));
    }

    public static void assertNumberLargerThanOrEquals(Double number, WebElement webElement) {
        assertIsLargerThanOrEquals("Number", number, readNumber(webElement));
    }

    /* Browser Url */
    public static boolean isUrl(String url, WebDriver driver) {
        return is(url, readUrl(driver));
    }

    public static boolean isUrlNot(String url, WebDriver driver) {
        return isNot(url, readUrl(driver));
    }

    public static boolean isUrlMatching(String regExp, WebDriver driver) {
        return isMatching(regExp, readUrl(driver));
    }

    public static boolean isUrlNotMatching(String regExp, WebDriver driver) {
        return isNotMatching(regExp, readUrl(driver));
    }

    public static boolean isUrlMatching(Openable openable, WebDriver driver) {
        return isMatching(openable.getUrl(), readUrl(driver));
    }

    public static boolean isUrlNotMatching(Openable openable, WebDriver driver) {
        return isNotMatching(openable.getUrl(), readUrl(driver));
    }

    public static boolean isUrlContaining(String searchText, WebDriver driver) {
        return isContaining(searchText, readUrl(driver));
    }

    public static boolean isUrlNotContaining(String searchText, WebDriver driver) {
        return isNotContaining(searchText, readUrl(driver));
    }

    public static boolean isUrlStartingWidth(String prefix, WebDriver driver) {
        return isStartingWith(prefix, readUrl(driver));
    }

    public static boolean isUrlNotStartingWidth(String prefix, WebDriver driver) {
        return isNotStartingWith(prefix, readUrl(driver));
    }

    public static boolean isUrlEndingWidth(String suffix, WebDriver driver) {
        return isEndingWith(suffix, readUrl(driver));
    }

    public static boolean isUrlNotEndingWidth(String suffix, WebDriver driver) {
        return isNotEndingWith(suffix, readUrl(driver));
    }

    public static void assertUrl(String url, WebDriver driver) {
        assertIs("Url", url, readUrl(driver));
    }

    public static void assertUrlNot(String url, WebDriver driver) {
        assertIsNot("Url", url, readUrl(driver));
    }

    public static void assertUrlMatching(String regExp, WebDriver driver) {
        assertIsMatching("Url", regExp, readUrl(driver));
    }

    public static void assertUrlNotMatching(String regExp, WebDriver driver) {
        assertIsNotMatching("Url", regExp, readUrl(driver));
    }

    public static void assertUrlMatching(Openable openable, WebDriver driver) {
        assertIsMatching("Url", openable.getUrl(), readUrl(driver));
    }

    public static void assertUrlNotMatching(Openable openable, WebDriver driver) {
        assertIsNotMatching("Url", openable.getUrl(), readUrl(driver));
    }

    public static void assertUrlContains(String searchText, WebDriver driver) {
        assertContains("Url", searchText, readUrl(driver));
    }

    public static void assertUrlNotContains(String searchText, WebDriver driver) {
        assertNotContains("Url", searchText, readUrl(driver));
    }

    public static void assertUrlStartsWidth(String prefix, WebDriver driver) {
        assertStartsWidth("Url", prefix, readUrl(driver));
    }

    public static void assertUrlNotStartsWidth(String prefix, WebDriver driver) {
        assertNotStartsWidth("Url", prefix, readUrl(driver));
    }

    public static void assertUrlEndsWidth(String suffix, WebDriver driver) {
        assertEndsWidth("Url", suffix, readUrl(driver));
    }

    public static void assertUrlNotEndsWidth(String suffix, WebDriver driver) {
        assertNotEndsWidth("Url", suffix, readUrl(driver));
    }

    /* Open */
    public static boolean isOpen(Openable openable) {
        return openable.isOpen();
    }

    public static boolean isNotOpen(Openable openable) {
        return openable.isNotOpen();
    }

    public static void assertIsOpen(Openable openable) {
        openable.assertIsOpen();
    }

    public static void assertIsNotOpen(Openable openable) {
        openable.assertIsNotOpen();
    }

    /* Selected */
    public static boolean isSelected(WebElement webElement) {
        return webElement.isSelected();
    }

    public static boolean isDeselected(WebElement webElement) {
        return !isSelected(webElement);
    }

    public static void assertIsSelected(WebElement webElement) {
        if (isDeselected(webElement)) {
            Assert.fail(describeTag(webElement) + " is not selected!");
        }
    }

    public static void assertIsDeselected(WebElement webElement) {
        if (isSelected(webElement)) {
            Assert.fail(describeTag(webElement) + " is not deselected!");
        }
    }

    /* Checked/Unchecked */
    public static boolean isChecked(WebElement webElement) {
        return webElement.isSelected();
    }

    public static boolean isUnchecked(WebElement webElement) {
        return !isChecked(webElement);
    }

    public static void assertIsChecked(WebElement webElement) {
        if (isUnchecked(webElement)) {
            Assert.fail(describeTag(webElement) + " is not checked!");
        }
    }

    public static void assertIsUnchecked(WebElement webElement) {
        if (isChecked(webElement)) {
            Assert.fail(describeTag(webElement) + " is not unchecked!");
        }
    }

    /* Enabled/Disabled */
    public static boolean isEnabled(WebElement webElement) {
        return webElement.isEnabled();
    }

    public static boolean isDisabled(WebElement webElement) {
        return !isEnabled(webElement);
    }

    public static void assertIsEnabled(WebElement webElement) {
        if (isUnchecked(webElement)) {
            Assert.fail(describeTag(webElement) + " is not enabled!");
        }
    }

    public static void assertIsDisabled(WebElement webElement) {
        if (isChecked(webElement)) {
            Assert.fail(describeTag(webElement) + " is not disabled!");
        }
    }

    /* Select */
    public static void select(WebElement webElement) {
        if (isDeselected(webElement)) {
            webElement.click();
        }
    }

    public static void deselect(WebElement webElement) {
        if (isSelected(webElement)) {
            webElement.click();
        }
    }

    /* Select Option */
    public static void selectOption(String text, WebElement webElement) {
        new Select(webElement).selectByVisibleText(text);
    }

    public static void deselectOption(String text, WebElement webElement) {
        new Select(webElement).deselectByVisibleText(text);
    }

    public static void selectAllOptions(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            select(webElement);
        }
    }

    public static void deselectAllOptions(WebElement webElement) {
        new Select(webElement).deselectAll();
    }


    /* Select Option Value */
    public static void selectOptionWithValue(String value, WebElement webElement) {
        new Select(webElement).selectByValue(value);
    }

    public static void deselectOptionWithValue(String value, WebElement webElement) {
        new Select(webElement).deselectByValue(value);
    }

    /* Select Option Index */
    public static void selectOptionWithIndex(int index, WebElement webElement) {
        new Select(webElement).selectByIndex(index);
    }

    public static void deselectOptionWithIndex(int index, WebElement webElement) {
        new Select(webElement).selectByIndex(index);
    }

    /* Display */
    public static boolean isDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isNotDisplayed(WebElement webElement) {
        return isDisplayed(webElement);
    }

    public static void assertIsDisplayed(WebElement webElement) {
        if (isNotDisplayed(webElement)) {
            Assert.fail("WebElement is not displayed!");
        }
    }

    public static void assertIsNotDisplayed(WebElement webElement) {
        if (isDisplayed(webElement)) {
            Assert.fail("WebElement is displayed when it shouldn't!");
        }
    }

    /* Number of Elements */
    public static boolean isNumberOfElements(int number, List<? extends WebElement> webElements) {
        return is((double) number, (double) webElements.size());
    }

    public static boolean isNumberOfElementsNot(int number, List<? extends WebElement> webElements) {
        return isNot((double) number, (double) webElements.size());
    }

    public static boolean isNumberOfElementsSmallerThan(int number, List<? extends WebElement> webElements) {
        return isSmallerThan((double) number, (double) webElements.size());
    }

    public static boolean isNumberOfElementsSmallerThanOrEquals(int number, List<? extends WebElement> webElements) {
        return isSmallerThanOrEquals((double) number, (double) webElements.size());
    }

    public static boolean isNumberOfElementsLargerThan(int number, List<? extends WebElement> webElements) {
        return isLargerThan((double) number, (double) webElements.size());
    }

    public static boolean isNumberOfElementsLargerThanOrEquals(int number, List<? extends WebElement> webElements) {
        return isLargerThanOrEquals((double) number, (double) webElements.size());
    }

    public static void assertNumberOfElements(int number, List<? extends WebElement> webElements) {
        assertIs("Number of elements", (double) number, (double) webElements.size());
    }

    public static void assertNumberOfElementsNot(int number, List<? extends WebElement> webElements) {
        assertIsNot("Number of elements", (double) number, (double) webElements.size());
    }

    public static void assertNumberOfElementsSmallerThan(int number, List<? extends WebElement> webElements) {
        assertIsSmallerThan("Number of elements", (double) number, (double) webElements.size());
    }

    public static void assertNumberOfElementsSmallerThanOrEquals(int number, List<? extends WebElement> webElements) {
        assertIsSmallerThanOrEquals("Number of elements", (double) number, (double) webElements.size());
    }

    public static void assertNumberOfElementsLargerThan(int number, List<? extends WebElement> webElements) {
        assertIsLargerThan("Number of elements", (double) number, (double) webElements.size());
    }

    public static void assertNumberOfElementsLargerThanOrEquals(int number, List<? extends WebElement> webElements) {
        assertIsLargerThanOrEquals("Number of elements", (double) number, (double) webElements.size());
    }

    /* Select Option */
    public static boolean hasOption(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotOption(String text, WebElement webElement) {
        return !hasOption(text, webElement);
    }

    public static boolean isOptionEnabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOptionDisabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOptionSelected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOptionDeselected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAllOptionSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isDeselected(option)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNoOptionSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isSelected(option)) {
                return false;
            }
        }
        return true;
    }

    public static void assertHasOption(String text, WebElement webElement) {
        if (hasNotOption(text, webElement)) {
            Assert.fail(describeTag(webElement) + "has no option \"" + text.trim() + "\"!");
        }
    }

    public static void assertHasNotOption(String text, WebElement webElement) {
        if (hasOption(text, webElement)) {
            Assert.fail(describeTag(webElement) + "has option \"" + text.trim() + "\" when it shouldn't!");
        }
    }

    public static void assertIsOptionEnabled(String text, WebElement webElement) {
        if (isOptionDisabled(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not enabled!");
        }
    }

    public static void assertIsOptionDisabled(String text, WebElement webElement) {
        if (isOptionEnabled(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not disabled!");
        }
    }

    public static void assertIsOptionSelected(String text, WebElement webElement) {
        if (isOptionDeselected(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not selected!");
        }
    }

    public static void assertIsOptionDeselected(String text, WebElement webElement) {
        if (isOptionSelected(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not deselected!");
        }
    }

    public static void assertIsAllOptionSelected(WebElement webElement) {
        if (!isAllOptionSelected(webElement)) {
            Assert.fail("All options are not selected!");
        }
    }

    public static void assertIsNoOptionSelected(WebElement webElement) {
        if (!isNoOptionSelected(webElement)) {
            Assert.fail("All options are not deselected!");
        }
    }

    /* Select Option Value */
    public static boolean hasOptionWithValue(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotOptionWithValue(String value, WebElement webElement) {
        return !hasOptionWithValue(value, webElement);
    }

    public static boolean isOptionWithValueEnabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOptionWithValueDisabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOptionWithValueSelected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOptionWithValueDeselected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }

    public static void assertHasOptionWithValue(String value, WebElement webElement) {
        if (hasNotOptionWithValue(value, webElement)) {
            Assert.fail(describeTag(webElement) + "has no option with value \"" + value.trim() + "\"!");
        }
    }

    public static void assertHasNotOptionWithValue(String value, WebElement webElement) {
        if (hasOptionWithValue(value, webElement)) {
            Assert.fail(describeTag(webElement) + "has option with value \"" + value.trim() + "\" when it shouldn't!");
        }
    }

    public static void assertIsOptionWithValueEnabled(String value, WebElement webElement) {
        if (isOptionWithValueDisabled(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not enabled!");
        }
    }

    public static void assertIsOptionWithValueDisabled(String value, WebElement webElement) {
        if (isOptionWithValueEnabled(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not disabled!");
        }
    }

    public static void assertIsOptionWithValueSelected(String value, WebElement webElement) {
        if (isOptionWithValueDeselected(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not selected!");
        }
    }

    public static void assertIsOptionWithValueDeselected(String value, WebElement webElement) {
        if (isOptionWithValueSelected(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not deselected!");
        }
    }

    /* Select Option Index */
    public static boolean hasOptionWithIndex(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return options.get(index) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean hasNotOptionWithIndex(int index, WebElement webElement) {
        return !hasNotOptionWithIndex(index, webElement);
    }

    public static boolean isOptionWithIndexEnabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isEnabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean isOptionWithIndexDisabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDisabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean isOptionWithIndexSelected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isSelected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean isOptionWithIndexDeselected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDeselected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static void assertHasOptionWithIndex(int index, WebElement webElement) {
        if (hasNotOptionWithIndex(index, webElement)) {
            Assert.fail(describeTag(webElement) + "has no option with index \"" + index + "\"!");
        }
    }

    public static void assertHasNotOptionWithIndex(int index, WebElement webElement) {
        if (hasOptionWithIndex(index, webElement)) {
            Assert.fail(describeTag(webElement) + "has option with index \"" + index + "\" when it shouldn't!");
        }
    }

    public static void assertIsOptionWithIndexEnabled(int index, WebElement webElement) {
        if (isOptionWithIndexDisabled(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not enabled!");
        }
    }

    public static void assertIsOptionWithIndexDisabled(int index, WebElement webElement) {
        if (isOptionWithIndexEnabled(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not disabled!");
        }
    }

    public static void assertIsOptionWithIndexSelected(int index, WebElement webElement) {
        if (isOptionWithIndexDeselected(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not selected!");
        }
    }

    public static void assertIsOptionWithIndexDeselected(int index, WebElement webElement) {
        if (isOptionWithIndexSelected(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not deselected!");
        }
    }
}
