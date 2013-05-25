package org.andidev.webdriverextension.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

    public class BotUtils {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BotUtils.class);

    /* Read */
    public static String textIn(WebElement webElement) {
        return webElement.getText();
    }

    public static Double numberIn(WebElement webElement) {
        try {
            return NumberUtils.createDouble(webElement.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static List<String> optionsIn(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        List<String> optionTexts = new ArrayList<String>();
        for (WebElement option : options) {
            optionTexts.add(textIn(option));
        }
        return optionTexts;
    }

    public static String url(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public static String tagNameOf(WebElement webElement) {
        return webElement.getTagName();
    }

    public static String attributeIn(String name, WebElement webElement) {
        return webElement.getAttribute(name);
    }

    public static String idIn(WebElement webElement) {
        return attributeIn("id", webElement);
    }

    public static String nameIn(WebElement webElement) {
        return attributeIn("name", webElement);
    }

    public static String classIn(WebElement webElement) {
        return attributeIn("class", webElement);
    }

    public static List<String> classesIn(WebElement webElement) {
        return Arrays.asList(StringUtils.split(classIn(webElement)));
    }

    public static String valueIn(WebElement webElement) {
        return attributeIn("value", webElement);
    }

    public static String hrefIn(WebElement webElement) {
        return attributeIn("href", webElement);
    }

    /* Count */
    public static int numberOf(List<? extends WebElement> webElements) {
        return webElements.size();
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
        type(toString(number), webElement);
    }

    /* Clear and Type */
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

    /* Click */
    public static void click(WebElement webElement) {
        webElement.click();
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

    /* Check */
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

    /* Wait */
    public static void waitFor(double seconds) {
        long nanos = (long) (seconds * 1000000000);
        if (seconds > 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(nanos);
            } catch (InterruptedException ex) {
                // Swallow exception
                ex.printStackTrace();
            }
        }
    }

    public static void waitFor(double time, TimeUnit unit) {
        long nanos = 0;
        switch (unit) {
            case DAYS:
                nanos = (long) (time * 24 * 60 * 60 * 1000000000);
                break;
            case HOURS:
                nanos = (long) (time * 60 * 60 * 1000000000);
                break;
            case MINUTES:
                nanos = (long) (time * 60 * 1000000000);
                break;
            case SECONDS:
                nanos = (long) (time * 1000000000);
                break;
            case MILLISECONDS:
                nanos = (long) (time * 1000000);
                break;
            case MICROSECONDS:
                nanos = (long) (time * 1000);
                break;
            case NANOSECONDS:
                nanos = (long) (time);
                break;
        }
        if (time > 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(nanos);
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
        log.debug("Tag {} has text = \"{}\"", describeTag(webElement), textIn(webElement));
    }

    public static void debug(List<? extends WebElement> webElements) {
        for (WebElement webElement : webElements) {
            debug(webElement);
        }
    }

    public static void debugNumberOf(List<? extends WebElement> webElements) {
        log.debug("Number of elements are {}", numberOf(webElements));
    }

    /* Tag Name */
    public static boolean isTagName(String value, WebElement webElement) {
        return is(value, tagNameOf(webElement));
    }

    public static boolean isTagNameNot(String value, WebElement webElement) {
        return isNot(value, tagNameOf(webElement));
    }

    public static void assertTagName(String value, WebElement webElement) {
        assertIs("Tag name", value, tagNameOf(webElement));
    }

    public static void assertTagNameNot(String value, WebElement webElement) {
        assertIsNot("Tag name", value, tagNameOf(webElement));
    }

    /* Attribute */
    public static boolean hasAttribute(String name, WebElement webElement) {
        return webElement.getAttribute(name) != null;
    }

    public static boolean hasNotAttribute(String name, WebElement webElement) {
        return !hasAttribute(name, webElement);
    }

    public static boolean isAttribute(String name, String value, WebElement webElement) {
        return is(value, attributeIn(name, webElement));
    }

    public static boolean isAttributeNot(String name, String value, WebElement webElement) {
        return isNot(value, attributeIn(name, webElement));
    }

    public static boolean isAttributeContains(String name, String searchText, WebElement webElement) {
        return isContains(searchText, attributeIn(name, webElement));
    }

    public static boolean isAttributeNotContains(String name, String searchText, WebElement webElement) {
        return isNotContains(searchText, attributeIn(name, webElement));
    }

    public static boolean isAttributeStartsWith(String name, String prefix, WebElement webElement) {
        return isStartsWith(prefix, attributeIn(name, webElement));
    }

    public static boolean isAttributeNotStartsWith(String name, String prefix, WebElement webElement) {
        return isNotStartsWith(prefix, attributeIn(name, webElement));
    }

    public static boolean isAttributeEndsWith(String name, String suffix, WebElement webElement) {
        return isEndsWith(suffix, attributeIn(name, webElement));
    }

    public static boolean isAttributeNotEndsWith(String name, String suffix, WebElement webElement) {
        return isNotEndsWith(suffix, attributeIn(name, webElement));
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
        assertIs(name, value, attributeIn(name, webElement));
    }

    public static void assertAttributeNot(String name, String value, WebElement webElement) {
        assertIsNot(name, value, attributeIn(name, webElement));
    }

    public static void assertAttributeContains(String name, String searchText, WebElement webElement) {
        assertContains(name, searchText, attributeIn(name, webElement));
    }

    public static void assertAttributeNotContains(String name, String searchText, WebElement webElement) {
        assertNotContains(name, searchText, attributeIn(name, webElement));
    }

    public static void assertAttributeStartsWith(String name, String prefix, WebElement webElement) {
        assertStartsWith(name, prefix, attributeIn(name, webElement));
    }

    public static void assertAttributeNotStartsWith(String name, String prefix, WebElement webElement) {
        assertNotStartsWith(name, prefix, attributeIn(name, webElement));
    }

    public static void assertAttributeEndsWith(String name, String suffix, WebElement webElement) {
        assertEndsWith(name, suffix, attributeIn(name, webElement));
    }

    public static void assertAttributeNotEndsWith(String name, String suffix, WebElement webElement) {
        assertNotEndsWith(name, suffix, attributeIn(name, webElement));
    }

    /* Id */
    public static boolean hasId(WebElement webElement) {
        return hasAttribute("id", webElement);
    }

    public static boolean hasNotId(WebElement webElement) {
        return hasNotAttribute("id", webElement);
    }

    public static boolean isId(String value, WebElement webElement) {
        return is(value, idIn(webElement));
    }

    public static boolean isIdNot(String value, WebElement webElement) {
        return isNot(value, idIn(webElement));
    }

    public static boolean isIdContains(String searchText, WebElement webElement) {
        return isContains(searchText, idIn(webElement));
    }

    public static boolean isIdNotContains(String searchText, WebElement webElement) {
        return isContains(searchText, idIn(webElement));
    }

    public static boolean isIdStartsWith(String prefix, WebElement webElement) {
        return isStartsWith(prefix, idIn(webElement));
    }

    public static boolean isIdNotStartsWith(String prefix, WebElement webElement) {
        return isNotStartsWith(prefix, idIn(webElement));
    }

    public static boolean isIdEndsWith(String suffix, WebElement webElement) {
        return isEndsWith(suffix, idIn(webElement));
    }

    public static boolean isIdNotEndsWith(String suffix, WebElement webElement) {
        return isEndsWith(suffix, idIn(webElement));
    }

    public static void assertHasId(WebElement webElement) {
        assertHasAttribute("id", webElement);
    }

    public static void assertHasNotId(WebElement webElement) {
        assertHasNotAttribute("id", webElement);
    }

    public static void assertId(String value, WebElement webElement) {
        assertIs("id", value, idIn(webElement));
    }

    public static void assertIdNot(String value, WebElement webElement) {
        assertIsNot("id", value, idIn(webElement));
    }

    public static void assertIdContains(String searchText, WebElement webElement) {
        assertContains("id", searchText, idIn(webElement));
    }

    public static void assertIdNotContains(String searchText, WebElement webElement) {
        assertNotContains("id", searchText, idIn(webElement));
    }

    public static void assertIdStartsWith(String prefix, WebElement webElement) {
        assertStartsWith("id", prefix, idIn(webElement));
    }

    public static void assertIdNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWith("id", prefix, idIn(webElement));
    }

    public static void assertIdEndsWith(String suffix, WebElement webElement) {
        assertEndsWith("id", suffix, idIn(webElement));
    }

    public static void assertIdNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWith("id", suffix, idIn(webElement));
    }

    /* Name */
    public static boolean hasName(WebElement webElement) {
        return hasAttribute("name", webElement);
    }

    public static boolean hasNotName(WebElement webElement) {
        return hasNotAttribute("name", webElement);
    }

    public static boolean isName(String value, WebElement webElement) {
        return is(value, nameIn(webElement));
    }

    public static boolean isNameNot(String value, WebElement webElement) {
        return isNot(value, nameIn(webElement));
    }

    public static boolean isNameContains(String searchText, WebElement webElement) {
        return isContains(searchText, nameIn(webElement));
    }

    public static boolean isNameNotContains(String searchText, WebElement webElement) {
        return isContains(searchText, nameIn(webElement));
    }

    public static boolean isNameStartsWith(String prefix, WebElement webElement) {
        return isStartsWith(prefix, nameIn(webElement));
    }

    public static boolean isNameNotStartsWith(String prefix, WebElement webElement) {
        return isNotStartsWith(prefix, nameIn(webElement));
    }

    public static boolean isNameEndsWith(String suffix, WebElement webElement) {
        return isEndsWith(suffix, nameIn(webElement));
    }

    public static boolean isNameNotEndsWith(String suffix, WebElement webElement) {
        return isEndsWith(suffix, nameIn(webElement));
    }

    public static void assertHasName(WebElement webElement) {
        assertHasAttribute("name", webElement);
    }

    public static void assertHasNotName(WebElement webElement) {
        assertHasNotAttribute("name", webElement);
    }

    public static void assertName(String value, WebElement webElement) {
        assertIs("name", value, nameIn(webElement));
    }

    public static void assertNameNot(String value, WebElement webElement) {
        assertIsNot("name", value, nameIn(webElement));
    }

    public static void assertNameContains(String searchText, WebElement webElement) {
        assertContains("name", searchText, nameIn(webElement));
    }

    public static void assertNameNotContains(String searchText, WebElement webElement) {
        assertNotContains("name", searchText, nameIn(webElement));
    }

    public static void assertNameStartsWith(String prefix, WebElement webElement) {
        assertStartsWith("name", prefix, nameIn(webElement));
    }

    public static void assertNameNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWith("name", prefix, nameIn(webElement));
    }

    public static void assertNameEndsWith(String suffix, WebElement webElement) {
        assertEndsWith("name", suffix, nameIn(webElement));
    }

    public static void assertNameNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWith("name", suffix, nameIn(webElement));
    }

    /* Class */
    public static boolean hasClass(WebElement webElement) {
        return hasAttribute("class", webElement);
    }

    public static boolean hasNotClass(WebElement webElement) {
        return hasNotAttribute("class", webElement);
    }

    public static boolean hasClass(String className, WebElement webElement) {
        return classIn(webElement).matches("(\\\"|\\s)" + className.trim() + "(\\\"|\\s)");
    }

    public static boolean hasNotClass(String className, WebElement webElement) {
        return !hasClass(className, webElement);
    }

    public static boolean hasClassContaining(String searchText, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (isContains(searchText, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasClassNotContaining(String searchText, WebElement webElement) {
        return !hasClassContaining(searchText, webElement);
    }

    public static boolean hasClassStartingWith(String prefix, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (isStartsWith(prefix, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasClassNotStartingWith(String prefix, WebElement webElement) {
        return !hasClassStartingWith(prefix, webElement);
    }

    public static boolean hasClassEndingWith(String suffix, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (isEndsWith(suffix, clazz)) {
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

    public static void assertHasClassContaining(String searchText, WebElement webElement) {
        if (hasClassNotContaining(searchText, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class containing text " + searchText.trim() + "!");
        }
    }

    public static void assertHasClassNotContaining(String searchText, WebElement webElement) {
        if (hasClassContaining(searchText, webElement)) {
            Assert.fail(describeTag(webElement) + " has class containing text " + searchText.trim() + " when it shouldn't!");
        }
    }

    public static void assertHasClassStartingWith(String prefix, WebElement webElement) {
        if (hasClassNotStartingWith(prefix, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class containing prefix " + prefix.trim() + "!");
        }
    }

    public static void assertHasClassNotStartingWith(String prefix, WebElement webElement) {
        if (hasClassStartingWith(prefix, webElement)) {
            Assert.fail(describeTag(webElement) + " has class containing prefix " + prefix.trim() + " when it shouldn't!");
        }
    }

    public static void assertHasClassEndingWith(String suffix, WebElement webElement) {
        if (hasClassNotEndingWith(suffix, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class containing suffix " + suffix.trim() + "!");
        }
    }

    public static void assertHasClassNotEndingWith(String suffix, WebElement webElement) {
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
        return is(value, valueIn(webElement));
    }

    public static boolean isValueNot(String value, WebElement webElement) {
        return isNot(value, valueIn(webElement));
    }

    public static boolean isValueContains(String searchText, WebElement webElement) {
        return isContains(searchText, valueIn(webElement));
    }

    public static boolean isValueNotContains(String searchText, WebElement webElement) {
        return isContains(searchText, valueIn(webElement));
    }

    public static boolean isValueStartsWith(String prefix, WebElement webElement) {
        return isStartsWith(prefix, valueIn(webElement));
    }

    public static boolean isValueNotStartsWith(String prefix, WebElement webElement) {
        return isNotStartsWith(prefix, valueIn(webElement));
    }

    public static boolean isValueEndsWith(String suffix, WebElement webElement) {
        return isEndsWith(suffix, valueIn(webElement));
    }

    public static boolean isValueNotEndsWith(String suffix, WebElement webElement) {
        return isEndsWith(suffix, valueIn(webElement));
    }

    public static void assertHasValue(WebElement webElement) {
        assertHasAttribute("value", webElement);
    }

    public static void assertHasNotValue(WebElement webElement) {
        assertHasNotAttribute("value", webElement);
    }

    public static void assertValue(String value, WebElement webElement) {
        assertIs("value", value, valueIn(webElement));
    }

    public static void assertValueNot(String value, WebElement webElement) {
        assertIsNot("value", value, valueIn(webElement));
    }

    public static void assertValueContains(String searchText, WebElement webElement) {
        assertContains("value", searchText, valueIn(webElement));
    }

    public static void assertValueNotContains(String searchText, WebElement webElement) {
        assertNotContains("value", searchText, valueIn(webElement));
    }

    public static void assertValueStartsWith(String prefix, WebElement webElement) {
        assertStartsWith("value", prefix, valueIn(webElement));
    }

    public static void assertValueNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWith("value", prefix, valueIn(webElement));
    }

    public static void assertValueEndsWith(String suffix, WebElement webElement) {
        assertEndsWith("value", suffix, valueIn(webElement));
    }

    public static void assertValueNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWith("value", suffix, valueIn(webElement));
    }


    /* Href */
    public static boolean hasHref(WebElement webElement) {
        return hasAttribute("href", webElement);
    }

    public static boolean hasNotHref(WebElement webElement) {
        return hasNotAttribute("href", webElement);
    }

    public static boolean isHref(String value, WebElement webElement) {
        return is(value, hrefIn(webElement));
    }

    public static boolean isHrefNot(String value, WebElement webElement) {
        return isNot(value, hrefIn(webElement));
    }

    public static boolean isHrefContains(String searchText, WebElement webElement) {
        return isContains(searchText, hrefIn(webElement));
    }

    public static boolean isHrefNotContains(String searchText, WebElement webElement) {
        return isContains(searchText, hrefIn(webElement));
    }

    public static boolean isHrefStartsWith(String prefix, WebElement webElement) {
        return isStartsWith(prefix, hrefIn(webElement));
    }

    public static boolean isHrefNotStartsWith(String prefix, WebElement webElement) {
        return isNotStartsWith(prefix, hrefIn(webElement));
    }

    public static boolean isHrefEndsWith(String suffix, WebElement webElement) {
        return isEndsWith(suffix, hrefIn(webElement));
    }

    public static boolean isHrefNotEndsWith(String suffix, WebElement webElement) {
        return isEndsWith(suffix, hrefIn(webElement));
    }

    public static void assertHasHref(WebElement webElement) {
        assertHasAttribute("href", webElement);
    }

    public static void assertHasNotHref(WebElement webElement) {
        assertHasNotAttribute("href", webElement);
    }

    public static void assertHref(String value, WebElement webElement) {
        assertIs("href", value, hrefIn(webElement));
    }

    public static void assertHrefNot(String value, WebElement webElement) {
        assertIsNot("href", value, hrefIn(webElement));
    }

    public static void assertHrefContains(String searchText, WebElement webElement) {
        assertContains("href", searchText, hrefIn(webElement));
    }

    public static void assertHrefNotContains(String searchText, WebElement webElement) {
        assertNotContains("href", searchText, hrefIn(webElement));
    }

    public static void assertHrefStartsWith(String prefix, WebElement webElement) {
        assertStartsWith("href", prefix, hrefIn(webElement));
    }

    public static void assertHrefNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWith("href", prefix, hrefIn(webElement));
    }

    public static void assertHrefEndsWith(String suffix, WebElement webElement) {
        assertEndsWith("href", suffix, hrefIn(webElement));
    }

    public static void assertHrefNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWith("href", suffix, hrefIn(webElement));
    }

    /* Text */
    public static boolean isText(String text, WebElement webElement) {
        return is(text, textIn(webElement));
    }

    public static boolean isTextNot(String text, WebElement webElement) {
        return isNot(text, textIn(webElement));
    }

    public static boolean isTextContains(String searchText, WebElement webElement) {
        return isContains(searchText, textIn(webElement));
    }

    public static boolean isTextNotContains(String searchText, WebElement webElement) {
        return isContains(searchText, textIn(webElement));
    }

    public static boolean isTextStartsWith(String prefix, WebElement webElement) {
        return isStartsWith(prefix, textIn(webElement));
    }

    public static boolean isTextNotStartsWith(String prefix, WebElement webElement) {
        return isNotStartsWith(prefix, textIn(webElement));
    }

    public static boolean isTextEndsWith(String suffix, WebElement webElement) {
        return isEndsWith(suffix, textIn(webElement));
    }

    public static boolean isTextNotEndsWith(String suffix, WebElement webElement) {
        return isEndsWith(suffix, textIn(webElement));
    }

    public static void assertText(String text, WebElement webElement) {
        assertIs("Text", text, textIn(webElement));
    }

    public static void assertTextNot(String text, WebElement webElement) {
        assertIsNot("Text", text, textIn(webElement));
    }

    public static void assertTextContains(String searchText, WebElement webElement) {
        assertContains("Text", searchText, textIn(webElement));
    }

    public static void assertTextNotContains(String searchText, WebElement webElement) {
        assertNotContains("Text", searchText, textIn(webElement));
    }

    public static void assertTextStartsWith(String prefix, WebElement webElement) {
        assertStartsWith("Text", prefix, textIn(webElement));
    }

    public static void assertTextNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWith("Text", prefix, textIn(webElement));
    }

    public static void assertTextEndsWith(String suffix, WebElement webElement) {
        assertEndsWith("Text", suffix, textIn(webElement));
    }

    public static void assertTextNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWith("Text", suffix, textIn(webElement));
    }

    /* Number */
    public static boolean isNumber(Double number, WebElement webElement) {
        return is(number, numberIn(webElement));
    }

    public static boolean isNumberNot(Double number, WebElement webElement) {
        return isNot(number, numberIn(webElement));
    }

    public static boolean isNumberSmallerThan(Double number, WebElement webElement) {
        return isSmallerThan(number, numberIn(webElement));
    }

    public static boolean isNumberSmallerThanOrEquals(Double number, WebElement webElement) {
        return isSmallerThanOrEquals(number, numberIn(webElement));
    }

    public static boolean isNumberLargerThan(Double number, WebElement webElement) {
        return isLargerThan(number, numberIn(webElement));
    }

    public static boolean isNumberLargerThanOrEquals(Double number, WebElement webElement) {
        return isLargerThanOrEquals(number, numberIn(webElement));
    }

    public static void assertNumber(Double number, WebElement webElement) {
        assertIs("Number", number, numberIn(webElement));
    }

    public static void assertNumberNot(Double number, WebElement webElement) {
        assertIsNot("Number", number, numberIn(webElement));
    }

    public static void assertNumberSmallerThan(Double number, WebElement webElement) {
        assertIsSmallerThan("Number", number, numberIn(webElement));
    }

    public static void assertNumberSmallerThanOrEquals(Double number, WebElement webElement) {
        assertIsSmallerThanOrEquals("Number", number, numberIn(webElement));
    }

    public static void assertNumberLargerThan(Double number, WebElement webElement) {
        assertIsLargerThan("Number", number, numberIn(webElement));
    }

    public static void assertNumberLargerThanOrEquals(Double number, WebElement webElement) {
        assertIsLargerThanOrEquals("Number", number, numberIn(webElement));
    }

    /* Browser Url */
    public static boolean isUrl(String url, WebDriver driver) {
        return is(url, url(driver));
    }

    public static boolean isUrlNot(String url, WebDriver driver) {
        return isNot(url, url(driver));
    }

    public static boolean isUrlMatching(String regExp, WebDriver driver) {
        return isMatching(regExp, url(driver));
    }

    public static boolean isUrlNotMatching(String regExp, WebDriver driver) {
        return isNotMatching(regExp, url(driver));
    }

    public static boolean isUrlMatching(Openable openable, WebDriver driver) {
        return isMatching(openable.getUrl(), url(driver));
    }

    public static boolean isUrlNotMatching(Openable openable, WebDriver driver) {
        return isNotMatching(openable.getUrl(), url(driver));
    }

    public static boolean isUrlContains(String searchText, WebDriver driver) {
        return isContains(searchText, url(driver));
    }

    public static boolean isUrlNotContains(String searchText, WebDriver driver) {
        return isNotContains(searchText, url(driver));
    }

    public static boolean isUrlStartsWith(String prefix, WebDriver driver) {
        return isStartsWith(prefix, url(driver));
    }

    public static boolean isUrlNotStartsWith(String prefix, WebDriver driver) {
        return isNotStartsWith(prefix, url(driver));
    }

    public static boolean isUrlEndsWith(String suffix, WebDriver driver) {
        return isEndsWith(suffix, url(driver));
    }

    public static boolean isUrlNotEndsWith(String suffix, WebDriver driver) {
        return isNotEndsWith(suffix, url(driver));
    }

    public static void assertUrl(String url, WebDriver driver) {
        assertIs("Url", url, url(driver));
    }

    public static void assertUrlNot(String url, WebDriver driver) {
        assertIsNot("Url", url, url(driver));
    }

    public static void assertUrlMatching(String regExp, WebDriver driver) {
        assertIsMatching("Url", regExp, url(driver));
    }

    public static void assertUrlNotMatching(String regExp, WebDriver driver) {
        assertIsNotMatching("Url", regExp, url(driver));
    }

    public static void assertUrlMatching(Openable openable, WebDriver driver) {
        assertIsMatching("Url", openable.getUrl(), url(driver));
    }

    public static void assertUrlNotMatching(Openable openable, WebDriver driver) {
        assertIsNotMatching("Url", openable.getUrl(), url(driver));
    }

    public static void assertUrlContains(String searchText, WebDriver driver) {
        assertContains("Url", searchText, url(driver));
    }

    public static void assertUrlNotContains(String searchText, WebDriver driver) {
        assertNotContains("Url", searchText, url(driver));
    }

    public static void assertUrlStartsWith(String prefix, WebDriver driver) {
        assertStartsWith("Url", prefix, url(driver));
    }

    public static void assertUrlNotStartsWith(String prefix, WebDriver driver) {
        assertNotStartsWith("Url", prefix, url(driver));
    }

    public static void assertUrlEndsWith(String suffix, WebDriver driver) {
        assertEndsWith("Url", suffix, url(driver));
    }

    public static void assertUrlNotEndsWith(String suffix, WebDriver driver) {
        assertNotEndsWith("Url", suffix, url(driver));
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

    /* Display */
    public static boolean isDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isNotDisplayed(WebElement webElement) {
        return !isDisplayed(webElement);
    }

    public static boolean isDisplayed(WebElement webElement, long secondsToWait, WebDriver driver) {
        WebElement foundWebElement = new WebDriverWait(driver, secondsToWait).until(ExpectedConditions.visibilityOf(webElement));
        if (foundWebElement != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotDisplayed(WebElement webElement, long secondsToWait, WebDriver driver) {
        return !isDisplayed(webElement, secondsToWait, driver);
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

    public static void assertIsDisplayed(WebElement webElement, long secondsToWait , WebDriver driver) {
        if (isNotDisplayed(webElement, secondsToWait , driver)) {
            Assert.fail("WebElement is not displayed within " + secondsToWait + " seconds!");
        }
    }

    public static void assertIsNotDisplayed(WebElement webElement, long secondsToWait , WebDriver driver) {
        if (isDisplayed(webElement, secondsToWait , driver)) {
            Assert.fail("WebElement is displayed within " + secondsToWait + " seconds when it shouldn't!");
        }
    }

    /* Number of Elements */
    public static boolean isNumberOf(int number, List<? extends WebElement> webElements) {
        return is((double) number, (double) webElements.size());
    }

    public static boolean isNumberOfNot(int number, List<? extends WebElement> webElements) {
        return isNot((double) number, (double) webElements.size());
    }

    public static boolean isNumberOfSmallerThan(int number, List<? extends WebElement> webElements) {
        return isSmallerThan((double) number, (double) webElements.size());
    }

    public static boolean isNumberOfSmallerThanOrEquals(int number, List<? extends WebElement> webElements) {
        return isSmallerThanOrEquals((double) number, (double) webElements.size());
    }

    public static boolean isNumberOfLargerThan(int number, List<? extends WebElement> webElements) {
        return isLargerThan((double) number, (double) webElements.size());
    }

    public static boolean isNumberOfLargerThanOrEquals(int number, List<? extends WebElement> webElements) {
        return isLargerThanOrEquals((double) number, (double) webElements.size());
    }

    public static void assertNumberOf(int number, List<? extends WebElement> webElements) {
        assertIs("Number of elements", (double) number, (double) webElements.size());
    }

    public static void assertNumberOfNot(int number, List<? extends WebElement> webElements) {
        assertIsNot("Number of elements", (double) number, (double) webElements.size());
    }

    public static void assertNumberOfSmallerThan(int number, List<? extends WebElement> webElements) {
        assertIsSmallerThan("Number of elements", (double) number, (double) webElements.size());
    }

    public static void assertNumberOfSmallerThanOrEquals(int number, List<? extends WebElement> webElements) {
        assertIsSmallerThanOrEquals("Number of elements", (double) number, (double) webElements.size());
    }

    public static void assertNumberOfLargerThan(int number, List<? extends WebElement> webElements) {
        assertIsLargerThan("Number of elements", (double) number, (double) webElements.size());
    }

    public static void assertNumberOfLargerThanOrEquals(int number, List<? extends WebElement> webElements) {
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
    /* Has */

    public static boolean has(String name, WebElement webElement) {
        return webElement.getAttribute(name) != null;
    }

    public static boolean hasNot(String name, WebElement webElement) {
        return !hasAttribute(name, webElement);
    }

    /* Is */
    public static boolean is(String text1, String text2) {
        return StringUtils.equals(text1, text2);
    }

    public static boolean isNot(String text1, String text2) {
        return !StringUtils.equals(text1, text2);
    }

    public static boolean isContains(String searchText, String text) {
        return StringUtils.contains(text, searchText);
    }

    public static boolean isNotContains(String searchText, String text) {
        return !StringUtils.contains(text, searchText);
    }

    public static boolean isStartsWith(String prefix, String text) {
        return StringUtils.startsWith(text, prefix);
    }

    public static boolean isNotStartsWith(String prefix, String text) {
        return !StringUtils.startsWith(text, prefix);
    }

    public static boolean isEndsWith(String suffix, String text) {
        return StringUtils.endsWith(text, suffix);
    }

    public static boolean isNotEndsWith(String suffix, String text) {
        return !StringUtils.endsWith(text, suffix);
    }

    public static boolean isMatching(String regularExpression, String text) {
        if (text == null || regularExpression == null) {
            return false;
        }
        return text.matches(regularExpression);
    }

    public static boolean isNotMatching(String regularExpression, String text) {
        if (text == null || regularExpression == null) {
            return true;
        }
        return !text.matches(regularExpression);
    }

    /* Is Any Of */
    public static boolean isAnyOf(String[] anyOfTexts, String text) {
        for (String anyOfText : anyOfTexts) {
            if (StringUtils.equals(text, anyOfText)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotAnyOf(String[] anyOfTexts, String text) {
        return !isAnyOf(anyOfTexts, text);
    }

    public static boolean isContainsAnyOf(String[] searchTexts, String text) {
        for (String searchText : searchTexts) {
            if (StringUtils.contains(text, searchText)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotContainsAnyOf(String[] searchTexts, String text) {
        return !isContainsAnyOf(searchTexts, text);
    }

    public static boolean isStartsWithAnyOf(String[] prefixes, String text) {
        return StringUtils.startsWithAny(text, prefixes);
    }

    public static boolean isNotStartsWithAnyOf(String[] prefixes, String text) {
        return !isStartsWithAnyOf(prefixes, text);
    }

    public static boolean isEndsWithAnyOf(String[] suffix, String text) {
        return StringUtils.endsWithAny(text, suffix);
    }

    public static boolean isNotEndsWithAnyOf(String[] suffix, String text) {
        return !isEndsWithAnyOf(suffix, text);
    }

    public static boolean isMatchingAnyOf(String[] regularExpressions, String text) {
        for (String regularExpression : regularExpressions) {
            if (isMatching(regularExpression, text)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotMatchingAnyOf(String[] regularExpressions, String text) {
        return !isMatchingAnyOf(regularExpressions, text);
    }

    /* Is Ignore Case */
    public static boolean isIgnoreCase(String text1, String text2) {
        return StringUtils.equalsIgnoreCase(text1, text2);
    }

    public static boolean isNotIgnoreCase(String text1, String text2) {
        return !isIgnoreCase(text1, text2);
    }

    public static boolean isContainsIgnoreCase(String searchText, String text) {
        return StringUtils.containsIgnoreCase(text, searchText);
    }

    public static boolean isNotContainsIgnoreCase(String searchText, String text) {
        return !isContainsIgnoreCase(text, searchText);
    }

    public static boolean isStartsWithIgnoreCase(String prefix, String text) {
        return StringUtils.startsWithIgnoreCase(text, prefix);
    }

    public static boolean isNotStartsWithIgnoreCase(String prefix, String text) {
        return !isStartsWithIgnoreCase(prefix, text);
    }

    public static boolean isEndsWithIgnoreCase(String suffix, String text) {
        return StringUtils.endsWithIgnoreCase(text, suffix);
    }

    public static boolean isNotEndsWithIgnoreCase(String suffix, String text) {
        return !isEndsWithIgnoreCase(suffix, text);
    }

    /* Asserts */
    public static void assertIs(String name, String expected, String actual) {
        Assert.assertEquals(name + " is not " + expected + "!", expected, actual);
    }

    public static void assertIsNot(String name, String notExpected, String actual) {
        Assert.assertNotEquals(name + " is " + notExpected + " when it shouldn't!", notExpected, actual);
    }

    public static void assertIsMatching(String name, String regExp, String actual) {
        if (isNotMatching(regExp, actual)) {
            Assert.fail(name + ": " + actual + " is not matching " + regExp + "!");
        }
    }

    public static void assertIsNotMatching(String name, String regExp, String actual) {
        if (isMatching(regExp, actual)) {
            Assert.fail(name + ": " + actual + " is matching " + regExp + " when it shouldn't!");
        }
    }

    public static void assertContains(String name, String searchText, String actual) {
        if (isNotContains(searchText, actual)) {
            Assert.fail(name + ": " + actual + " is not containing " + searchText);
        }
    }

    public static void assertNotContains(String name, String searchText, String actual) {
        if (isContains(searchText, actual)) {
            Assert.fail(name + ": " + actual + " is containing " + searchText + " when it shouldn't!");
        }
    }

    public static void assertStartsWith(String name, String prefix, String actual) {
        if (isNotStartsWith(prefix, actual)) {
            Assert.fail(name + ": " + actual + " is not starting with " + prefix);
        }
    }

    public static void assertNotStartsWith(String name, String prefix, String actual) {
        if (isStartsWith(prefix, actual)) {
            Assert.fail(name + ": " + actual + " is starting with " + prefix + " when it shouldn't!");
        }
    }

    public static void assertEndsWith(String name, String suffix, String actual) {
        if (isNotEndsWith(suffix, actual)) {
            Assert.fail(name + ": " + actual + " is not ending with " + suffix);
        }
    }

    public static void assertNotEndsWith(String name, String suffix, String actual) {
        if (isEndsWith(suffix, actual)) {
            Assert.fail(name + ": " + actual + " is ending with " + suffix + " when it shouldn't!");
        }
    }

    public static boolean is(Double comparedTo, Double number) {
        return number == comparedTo;
    }

    public static boolean isNot(double comparedTo, double number) {
        return number != comparedTo;
    }

    public static boolean isSmallerThan(Double comparedTo, Double number) {
        return number < comparedTo;
    }

    public static boolean isSmallerThanOrEquals(double comparedTo, double number) {
        return number <= comparedTo;
    }

    public static boolean isLargerThan(double comparedTo, double number) {
        return number > comparedTo;
    }

    public static boolean isLargerThanOrEquals(double comparedTo, double number) {
        return number >= comparedTo;
    }

    public static void assertIs(String name, double comparedTo, double number) {
        if (isNot(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not " + comparedTo + " !");
        }
    }

    public static void assertIsNot(String name, double comparedTo, double number) {
        if (is(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is " + comparedTo + " when it shouldn't!");
        }
    }

    public static void assertIsSmallerThan(String name, double comparedTo, double number) {
        if (isLargerThanOrEquals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not smaller then " + comparedTo + " !");
        }
    }

    public static void assertIsSmallerThanOrEquals(String name, double comparedTo, double number) {
        if (isLargerThan(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not smaller then or equal " + comparedTo + " !");
        }
    }

    public static void assertIsLargerThan(String name, double comparedTo, double number) {
        if (isSmallerThanOrEquals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not larger then " + comparedTo + " !");
        }
    }

    public static void assertIsLargerThanOrEquals(String name, double comparedTo, double number) {
        if (isSmallerThan(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not larger then or equal " + comparedTo + " !");
        }
    }

    public static String describeTag(WebElement webElement) {
        return describeTag(webElement, null);
    }

    public static String describeTag(WebElement webElement, String extraAttribute) {
        if (webElement == null) {
            return "WebElement";
        }
        boolean printExtraAttribute = extraAttribute != null && !ArrayUtils.contains(new String[]{"id", "name", "name", "class", "value", "disabled", "selected", "checked"}, extraAttribute);
        return "Tag <" + tagNameOf(webElement)
                + describeId(webElement)
                + describeName(webElement)
                + describeClass(webElement)
                + describeValue(webElement)
                + describeAttribute("disabled", webElement)
                + describeAttribute("selected", webElement)
                + describeAttribute("checked", webElement)
                + (printExtraAttribute ? describeAttribute(extraAttribute, webElement) : "")
                + ">";
    }

    public static String describeAttribute(String attributeName, WebElement webElement) {
        return hasAttribute(attributeName, webElement) ? attributeName + " = '" + attributeIn(attributeName, webElement) + "' " : "";
    }

    public static String describeId(WebElement webElement) {
        return hasId(webElement) ? "id = '" + idIn(webElement) + "' " : "";
    }

    public static String describeName(WebElement webElement) {
        return hasName(webElement) ? "name = '" + nameIn(webElement) + "' " : "";
    }

    public static String describeClass(WebElement webElement) {
        return hasClass(webElement) ? "class = '" + classIn(webElement) + "' " : "";
    }

    public static String describeValue(WebElement webElement) {
        return hasValue(webElement) ? "value = '" + valueIn(webElement) + "' " : "";
    }

    public static String toString(double number) {
        if (number == (int) number) {
            return String.format("%d", (int) number);
        } else {
            return String.format("%s", number);
        }
    }
}
