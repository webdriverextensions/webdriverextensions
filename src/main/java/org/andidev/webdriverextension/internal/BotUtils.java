package org.andidev.webdriverextension.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
    public static int sizeOf(Collection collection) {
        return collection.size();
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
        log.debug("List contains the following {} tags", sizeOf(webElements));
        for (WebElement webElement : webElements) {
            debug(webElement);
        }
    }

    /* Tag Name */
    public static boolean tagNameEquals(String value, WebElement webElement) {
        return equals(value, tagNameOf(webElement));
    }

    public static boolean tagNameNotEquals(String value, WebElement webElement) {
        return notEquals(value, tagNameOf(webElement));
    }

    public static void assertTagNameEquals(String value, WebElement webElement) {
        assertEquals("Tag name", value, tagNameOf(webElement));
    }

    public static void assertTagNameNotEquals(String value, WebElement webElement) {
        assertNotEquals("Tag name", value, tagNameOf(webElement));
    }

    /* Attribute */
    public static boolean hasAttribute(String name, WebElement webElement) {
        return webElement.getAttribute(name) != null;
    }

    public static boolean hasNotAttribute(String name, WebElement webElement) {
        return !hasAttribute(name, webElement);
    }

    public static boolean attributeEquals(String name, String value, WebElement webElement) {
        return equals(value, attributeIn(name, webElement));
    }

    public static boolean attributeNotEquals(String name, String value, WebElement webElement) {
        return notEquals(value, attributeIn(name, webElement));
    }

    public static boolean attributeContains(String name, String searchText, WebElement webElement) {
        return contains(searchText, attributeIn(name, webElement));
    }

    public static boolean attributeNotContains(String name, String searchText, WebElement webElement) {
        return notContains(searchText, attributeIn(name, webElement));
    }

    public static boolean attributeStartsWith(String name, String prefix, WebElement webElement) {
        return startsWith(prefix, attributeIn(name, webElement));
    }

    public static boolean attributeNotStartsWith(String name, String prefix, WebElement webElement) {
        return notStartsWith(prefix, attributeIn(name, webElement));
    }

    public static boolean attributeEndsWith(String name, String suffix, WebElement webElement) {
        return endsWith(suffix, attributeIn(name, webElement));
    }

    public static boolean attributeNotEndsWith(String name, String suffix, WebElement webElement) {
        return notEndsWith(suffix, attributeIn(name, webElement));
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

    public static void assertAttributeEquals(String name, String value, WebElement webElement) {
        assertEquals(name, value, attributeIn(name, webElement));
    }

    public static void assertAttributeNotEquals(String name, String value, WebElement webElement) {
        assertNotEquals(name, value, attributeIn(name, webElement));
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

    public static boolean idEquals(String value, WebElement webElement) {
        return equals(value, idIn(webElement));
    }

    public static boolean idNotEquals(String value, WebElement webElement) {
        return notEquals(value, idIn(webElement));
    }

    public static boolean idContains(String searchText, WebElement webElement) {
        return contains(searchText, idIn(webElement));
    }

    public static boolean idNotContains(String searchText, WebElement webElement) {
        return contains(searchText, idIn(webElement));
    }

    public static boolean idStartsWith(String prefix, WebElement webElement) {
        return startsWith(prefix, idIn(webElement));
    }

    public static boolean idNotStartsWith(String prefix, WebElement webElement) {
        return notStartsWith(prefix, idIn(webElement));
    }

    public static boolean idEndsWith(String suffix, WebElement webElement) {
        return endsWith(suffix, idIn(webElement));
    }

    public static boolean idNotEndsWith(String suffix, WebElement webElement) {
        return endsWith(suffix, idIn(webElement));
    }

    public static void assertHasId(WebElement webElement) {
        assertHasAttribute("id", webElement);
    }

    public static void assertHasNotId(WebElement webElement) {
        assertHasNotAttribute("id", webElement);
    }

    public static void assertIdEquals(String value, WebElement webElement) {
        assertEquals("id", value, idIn(webElement));
    }

    public static void assertIdNotEquals(String value, WebElement webElement) {
        assertNotEquals("id", value, idIn(webElement));
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

    public static boolean nameEquals(String value, WebElement webElement) {
        return equals(value, nameIn(webElement));
    }

    public static boolean nameNotEquals(String value, WebElement webElement) {
        return notEquals(value, nameIn(webElement));
    }

    public static boolean nameContains(String searchText, WebElement webElement) {
        return contains(searchText, nameIn(webElement));
    }

    public static boolean nameNotContains(String searchText, WebElement webElement) {
        return contains(searchText, nameIn(webElement));
    }

    public static boolean nameStartsWith(String prefix, WebElement webElement) {
        return startsWith(prefix, nameIn(webElement));
    }

    public static boolean nameNotStartsWith(String prefix, WebElement webElement) {
        return notStartsWith(prefix, nameIn(webElement));
    }

    public static boolean nameEndsWith(String suffix, WebElement webElement) {
        return endsWith(suffix, nameIn(webElement));
    }

    public static boolean nameNotEndsWith(String suffix, WebElement webElement) {
        return endsWith(suffix, nameIn(webElement));
    }

    public static void assertHasName(WebElement webElement) {
        assertHasAttribute("name", webElement);
    }

    public static void assertHasNotName(WebElement webElement) {
        assertHasNotAttribute("name", webElement);
    }

    public static void assertNameEquals(String value, WebElement webElement) {
        assertEquals("name", value, nameIn(webElement));
    }

    public static void assertNameNotEquals(String value, WebElement webElement) {
        assertNotEquals("name", value, nameIn(webElement));
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
            if (contains(searchText, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClassContaining(String searchText, WebElement webElement) {
        return !hasClassContaining(searchText, webElement);
    }

    public static boolean hasClassStartingWith(String prefix, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (startsWith(prefix, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClassStartingWith(String prefix, WebElement webElement) {
        return !hasClassStartingWith(prefix, webElement);
    }

    public static boolean hasClassEndingWith(String suffix, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (endsWith(suffix, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClassEndingWith(String suffix, WebElement webElement) {
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
        if (hasNotClassContaining(searchText, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class containing text " + searchText.trim() + "!");
        }
    }

    public static void assertHasNotClassContaining(String searchText, WebElement webElement) {
        if (hasClassContaining(searchText, webElement)) {
            Assert.fail(describeTag(webElement) + " has class containing text " + searchText.trim() + " when it shouldn't!");
        }
    }

    public static void assertHasClassStartingWith(String prefix, WebElement webElement) {
        if (hasNotClassStartingWith(prefix, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class containing prefix " + prefix.trim() + "!");
        }
    }

    public static void assertHasNotClassStartingWith(String prefix, WebElement webElement) {
        if (hasClassStartingWith(prefix, webElement)) {
            Assert.fail(describeTag(webElement) + " has class containing prefix " + prefix.trim() + " when it shouldn't!");
        }
    }

    public static void assertHasClassEndingWith(String suffix, WebElement webElement) {
        if (hasNotClassEndingWith(suffix, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class containing suffix " + suffix.trim() + "!");
        }
    }

    public static void assertHasNotClassEndingWith(String suffix, WebElement webElement) {
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

    public static boolean valueEquals(String value, WebElement webElement) {
        return equals(value, valueIn(webElement));
    }

    public static boolean valueNotEquals(String value, WebElement webElement) {
        return notEquals(value, valueIn(webElement));
    }

    public static boolean valueContains(String searchText, WebElement webElement) {
        return contains(searchText, valueIn(webElement));
    }

    public static boolean valueNotContains(String searchText, WebElement webElement) {
        return contains(searchText, valueIn(webElement));
    }

    public static boolean valueStartsWith(String prefix, WebElement webElement) {
        return startsWith(prefix, valueIn(webElement));
    }

    public static boolean valueNotStartsWith(String prefix, WebElement webElement) {
        return notStartsWith(prefix, valueIn(webElement));
    }

    public static boolean valueEndsWith(String suffix, WebElement webElement) {
        return endsWith(suffix, valueIn(webElement));
    }

    public static boolean valueNotEndsWith(String suffix, WebElement webElement) {
        return endsWith(suffix, valueIn(webElement));
    }

    public static void assertHasValue(WebElement webElement) {
        assertHasAttribute("value", webElement);
    }

    public static void assertHasNotValue(WebElement webElement) {
        assertHasNotAttribute("value", webElement);
    }

    public static void assertValueEquals(String value, WebElement webElement) {
        assertEquals("value", value, valueIn(webElement));
    }

    public static void assertValueNotEquals(String value, WebElement webElement) {
        assertNotEquals("value", value, valueIn(webElement));
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

    public static boolean hrefEquals(String value, WebElement webElement) {
        return equals(value, hrefIn(webElement));
    }

    public static boolean hrefNotEquals(String value, WebElement webElement) {
        return notEquals(value, hrefIn(webElement));
    }

    public static boolean hrefContains(String searchText, WebElement webElement) {
        return contains(searchText, hrefIn(webElement));
    }

    public static boolean hrefNotContains(String searchText, WebElement webElement) {
        return contains(searchText, hrefIn(webElement));
    }

    public static boolean hrefStartsWith(String prefix, WebElement webElement) {
        return startsWith(prefix, hrefIn(webElement));
    }

    public static boolean hrefNotStartsWith(String prefix, WebElement webElement) {
        return notStartsWith(prefix, hrefIn(webElement));
    }

    public static boolean hrefEndsWith(String suffix, WebElement webElement) {
        return endsWith(suffix, hrefIn(webElement));
    }

    public static boolean hrefNotEndsWith(String suffix, WebElement webElement) {
        return endsWith(suffix, hrefIn(webElement));
    }

    public static void assertHasHref(WebElement webElement) {
        assertHasAttribute("href", webElement);
    }

    public static void assertHasNotHref(WebElement webElement) {
        assertHasNotAttribute("href", webElement);
    }

    public static void assertHrefEquals(String value, WebElement webElement) {
        assertEquals("href", value, hrefIn(webElement));
    }

    public static void assertHrefNotEquals(String value, WebElement webElement) {
        assertNotEquals("href", value, hrefIn(webElement));
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
    public static boolean textEquals(String text, WebElement webElement) {
        return equals(text, textIn(webElement));
    }

    public static boolean textNotEquals(String text, WebElement webElement) {
        return notEquals(text, textIn(webElement));
    }

    public static boolean textContains(String searchText, WebElement webElement) {
        return contains(searchText, textIn(webElement));
    }

    public static boolean textNotContains(String searchText, WebElement webElement) {
        return contains(searchText, textIn(webElement));
    }

    public static boolean textStartsWith(String prefix, WebElement webElement) {
        return startsWith(prefix, textIn(webElement));
    }

    public static boolean textNotStartsWith(String prefix, WebElement webElement) {
        return notStartsWith(prefix, textIn(webElement));
    }

    public static boolean textEndsWith(String suffix, WebElement webElement) {
        return endsWith(suffix, textIn(webElement));
    }

    public static boolean textNotEndsWith(String suffix, WebElement webElement) {
        return endsWith(suffix, textIn(webElement));
    }

    public static void assertTextEquals(String text, WebElement webElement) {
        assertEquals("Text", text, textIn(webElement));
    }

    public static void assertTextNotEquals(String text, WebElement webElement) {
        assertNotEquals("Text", text, textIn(webElement));
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
    public static boolean numberEquals(Double number, WebElement webElement) {
        return equals(number, numberIn(webElement));
    }

    public static boolean numberNotEquals(Double number, WebElement webElement) {
        return notEquals(number, numberIn(webElement));
    }

    public static boolean numberLessThan(Double number, WebElement webElement) {
        return lessThan(number, numberIn(webElement));
    }

    public static boolean numberLessThanOrEquals(Double number, WebElement webElement) {
        return lessThanOrEquals(number, numberIn(webElement));
    }

    public static boolean numberGreaterThan(Double number, WebElement webElement) {
        return greaterThan(number, numberIn(webElement));
    }

    public static boolean numberGreaterThanOrEquals(Double number, WebElement webElement) {
        return greaterThanOrEquals(number, numberIn(webElement));
    }

    public static void assertNumberEquals(Double number, WebElement webElement) {
        assertEquals("Number", number, numberIn(webElement));
    }

    public static void assertNumberNotEquals(Double number, WebElement webElement) {
        assertNotEequals("Number", number, numberIn(webElement));
    }

    public static void assertNumberLessThan(Double number, WebElement webElement) {
        assertLessThan("Number", number, numberIn(webElement));
    }

    public static void assertNumberLessThanOrEquals(Double number, WebElement webElement) {
        assertLessThanOrEquals("Number", number, numberIn(webElement));
    }

    public static void assertNumberGreaterThan(Double number, WebElement webElement) {
        assertGreaterThan("Number", number, numberIn(webElement));
    }

    public static void assertNumberGreaterThanOrEquals(Double number, WebElement webElement) {
        assertGreaterThanOrEquals("Number", number, numberIn(webElement));
    }

    /* Url */
    public static boolean urlEquals(String url, WebDriver driver) {
        return equals(url, url(driver));
    }

    public static boolean urlNotEquals(String url, WebDriver driver) {
        return notEquals(url, url(driver));
    }

    public static boolean urlMatches(String regExp, WebDriver driver) {
        return matches(regExp, url(driver));
    }

    public static boolean urlNotMatches(String regExp, WebDriver driver) {
        return notMatches(regExp, url(driver));
    }

    public static boolean urlMatches(Openable openable, WebDriver driver) {
        return matches(openable.getUrl(), url(driver));
    }

    public static boolean urlNotMatches(Openable openable, WebDriver driver) {
        return notMatches(openable.getUrl(), url(driver));
    }

    public static boolean urlContains(String searchText, WebDriver driver) {
        return contains(searchText, url(driver));
    }

    public static boolean urlNotContains(String searchText, WebDriver driver) {
        return notContains(searchText, url(driver));
    }

    public static boolean urlStartsWith(String prefix, WebDriver driver) {
        return startsWith(prefix, url(driver));
    }

    public static boolean urlNotStartsWith(String prefix, WebDriver driver) {
        return notStartsWith(prefix, url(driver));
    }

    public static boolean urlEndsWith(String suffix, WebDriver driver) {
        return endsWith(suffix, url(driver));
    }

    public static boolean urlNotEndsWith(String suffix, WebDriver driver) {
        return notEndsWith(suffix, url(driver));
    }

    public static void assertUrlEquals(String url, WebDriver driver) {
        assertEquals("Url", url, url(driver));
    }

    public static void assertUrlNotEquals(String url, WebDriver driver) {
        assertNotEquals("Url", url, url(driver));
    }

    public static void assertUrlMatches(String regExp, WebDriver driver) {
        assertMatches("Url", regExp, url(driver));
    }

    public static void assertUrlNotMatches(String regExp, WebDriver driver) {
        assertNotMatches("Url", regExp, url(driver));
    }

    public static void assertUrlMatches(Openable openable, WebDriver driver) {
        assertMatches("Url", openable.getUrl(), url(driver));
    }

    public static void assertUrlNotMatches(Openable openable, WebDriver driver) {
        assertNotMatches("Url", openable.getUrl(), url(driver));
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

    /* Number of */
    public static boolean sizeEquals(int number, Collection collection) {
        return equals((double) number, (double) collection.size());
    }

    public static boolean sizeNotEquals(int number, Collection collection) {
        return notEquals((double) number, (double) collection.size());
    }

    public static boolean sizeLessThan(int number, Collection collection) {
        return lessThan((double) number, (double) collection.size());
    }

    public static boolean sizeLessThanOrEquals(int number, Collection collection) {
        return lessThanOrEquals((double) number, (double) collection.size());
    }

    public static boolean sizeGreaterThan(int number, Collection collection) {
        return greaterThan((double) number, (double) collection.size());
    }

    public static boolean sizeGreaterThanOrEquals(int number, Collection collection) {
        return greaterThanOrEquals((double) number, (double) collection.size());
    }

    public static void assertSizeEquals(int number, Collection collection) {
        assertEquals("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeNotEquals(int number, Collection collection) {
        assertNotEequals("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeLessThan(int number, Collection collection) {
        assertLessThan("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeLessThanOrEquals(int number, Collection collection) {
        assertLessThanOrEquals("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeGreaterThan(int number, Collection collection) {
        assertGreaterThan("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeGreaterThanOrEquals(int number, Collection collection) {
        assertGreaterThanOrEquals("Size", (double) number, (double) collection.size());
    }

    /* Option */
    public static boolean hasOption(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option)) {
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
            if (textEquals(text, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOptionDisabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOptionSelected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOptionDeselected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAllOptionsSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isDeselected(option)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNoOptionsSelected(WebElement webElement) {
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

    public static void assertIsAllOptionsSelected(WebElement webElement) {
        if (!isAllOptionsSelected(webElement)) {
            Assert.fail("All options are not selected!");
        }
    }

    public static void assertIsNoOptionsSelected(WebElement webElement) {
        if (!isNoOptionsSelected(webElement)) {
            Assert.fail("All options are not deselected!");
        }
    }

    /* Option Value */
    public static boolean hasOptionWithValue(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option)) {
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
            if (valueEquals(value, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOptionWithValueDisabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOptionWithValueSelected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOptionWithValueDeselected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isDeselected(option)) {
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

    /* Option Index */
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
    public static boolean equals(String text1, String text2) {
        return StringUtils.equals(text1, text2);
    }

    public static boolean notEquals(String text1, String text2) {
        return !StringUtils.equals(text1, text2);
    }

    public static boolean contains(String searchText, String text) {
        return StringUtils.contains(text, searchText);
    }

    public static boolean notContains(String searchText, String text) {
        return !StringUtils.contains(text, searchText);
    }

    public static boolean startsWith(String prefix, String text) {
        return StringUtils.startsWith(text, prefix);
    }

    public static boolean notStartsWith(String prefix, String text) {
        return !StringUtils.startsWith(text, prefix);
    }

    public static boolean endsWith(String suffix, String text) {
        return StringUtils.endsWith(text, suffix);
    }

    public static boolean notEndsWith(String suffix, String text) {
        return !StringUtils.endsWith(text, suffix);
    }

    public static boolean matches(String regularExpression, String text) {
        if (text == null || regularExpression == null) {
            return false;
        }
        return text.matches(regularExpression);
    }

    public static boolean notMatches(String regularExpression, String text) {
        if (text == null || regularExpression == null) {
            return true;
        }
        return !text.matches(regularExpression);
    }

    /* Is Any Of */
    public static boolean equalsAnyOf(String[] anyOfTexts, String text) {
        for (String anyOfText : anyOfTexts) {
            if (StringUtils.equals(text, anyOfText)) {
                return true;
            }
        }
        return false;
    }

    public static boolean notEqualsAnyOf(String[] anyOfTexts, String text) {
        return !equalsAnyOf(anyOfTexts, text);
    }

    public static boolean containsAnyOf(String[] searchTexts, String text) {
        for (String searchText : searchTexts) {
            if (StringUtils.contains(text, searchText)) {
                return true;
            }
        }
        return false;
    }

    public static boolean notContainsAnyOf(String[] searchTexts, String text) {
        return !containsAnyOf(searchTexts, text);
    }

    public static boolean startsWithAnyOf(String[] prefixes, String text) {
        return StringUtils.startsWithAny(text, prefixes);
    }

    public static boolean notStartsWithAnyOf(String[] prefixes, String text) {
        return !startsWithAnyOf(prefixes, text);
    }

    public static boolean endsWithAnyOf(String[] suffix, String text) {
        return StringUtils.endsWithAny(text, suffix);
    }

    public static boolean notEndsWithAnyOf(String[] suffix, String text) {
        return !endsWithAnyOf(suffix, text);
    }

    public static boolean matchingAnyOf(String[] regularExpressions, String text) {
        for (String regularExpression : regularExpressions) {
            if (matches(regularExpression, text)) {
                return true;
            }
        }
        return false;
    }

    public static boolean notMatchingAnyOf(String[] regularExpressions, String text) {
        return !matchingAnyOf(regularExpressions, text);
    }

    /* Is Ignore Case */
    public static boolean equalsIgnoreCase(String text1, String text2) {
        return StringUtils.equalsIgnoreCase(text1, text2);
    }

    public static boolean notEqualsIgnoreCase(String text1, String text2) {
        return !equalsIgnoreCase(text1, text2);
    }

    public static boolean containsIgnoreCase(String searchText, String text) {
        return StringUtils.containsIgnoreCase(text, searchText);
    }

    public static boolean notContainsIgnoreCase(String searchText, String text) {
        return !containsIgnoreCase(text, searchText);
    }

    public static boolean startsWithIgnoreCase(String prefix, String text) {
        return StringUtils.startsWithIgnoreCase(text, prefix);
    }

    public static boolean notStartsWithIgnoreCase(String prefix, String text) {
        return !startsWithIgnoreCase(prefix, text);
    }

    public static boolean endsWithIgnoreCase(String suffix, String text) {
        return StringUtils.endsWithIgnoreCase(text, suffix);
    }

    public static boolean notEndsWithIgnoreCase(String suffix, String text) {
        return !endsWithIgnoreCase(suffix, text);
    }

    /* Asserts */
    public static void assertEquals(String name, String expected, String actual) {
        Assert.assertEquals(name + " is not equal to " + expected + "!", expected, actual);
    }

    public static void assertNotEquals(String name, String notExpected, String actual) {
        Assert.assertNotEquals(name + " is equal to" + notExpected + " when it shouldn't!", notExpected, actual);
    }

    public static void assertMatches(String name, String regExp, String actual) {
        if (notMatches(regExp, actual)) {
            Assert.fail(name + ": " + actual + " is not matching " + regExp + "!");
        }
    }

    public static void assertNotMatches(String name, String regExp, String actual) {
        if (matches(regExp, actual)) {
            Assert.fail(name + ": " + actual + " is matching " + regExp + " when it shouldn't!");
        }
    }

    public static void assertContains(String name, String searchText, String actual) {
        if (notContains(searchText, actual)) {
            Assert.fail(name + ": " + actual + " is not containing " + searchText);
        }
    }

    public static void assertNotContains(String name, String searchText, String actual) {
        if (contains(searchText, actual)) {
            Assert.fail(name + ": " + actual + " is containing " + searchText + " when it shouldn't!");
        }
    }

    public static void assertStartsWith(String name, String prefix, String actual) {
        if (notStartsWith(prefix, actual)) {
            Assert.fail(name + ": " + actual + " is not starting with " + prefix);
        }
    }

    public static void assertNotStartsWith(String name, String prefix, String actual) {
        if (startsWith(prefix, actual)) {
            Assert.fail(name + ": " + actual + " is starting with " + prefix + " when it shouldn't!");
        }
    }

    public static void assertEndsWith(String name, String suffix, String actual) {
        if (notEndsWith(suffix, actual)) {
            Assert.fail(name + ": " + actual + " is not ending with " + suffix);
        }
    }

    public static void assertNotEndsWith(String name, String suffix, String actual) {
        if (endsWith(suffix, actual)) {
            Assert.fail(name + ": " + actual + " is ending with " + suffix + " when it shouldn't!");
        }
    }

    public static boolean equals(Double comparedTo, Double number) {
        return number == comparedTo;
    }

    public static boolean notEquals(double comparedTo, double number) {
        return number != comparedTo;
    }

    public static boolean lessThan(Double comparedTo, Double number) {
        return number < comparedTo;
    }

    public static boolean lessThanOrEquals(double comparedTo, double number) {
        return number <= comparedTo;
    }

    public static boolean greaterThan(double comparedTo, double number) {
        return number > comparedTo;
    }

    public static boolean greaterThanOrEquals(double comparedTo, double number) {
        return number >= comparedTo;
    }

    public static void assertEquals(String name, double comparedTo, double number) {
        if (notEquals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not equal to " + comparedTo + " !");
        }
    }

    public static void assertNotEequals(String name, double comparedTo, double number) {
        if (equals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is equal to " + comparedTo + " when it shouldn't!");
        }
    }

    public static void assertLessThan(String name, double comparedTo, double number) {
        if (greaterThanOrEquals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not less than " + comparedTo + " !");
        }
    }

    public static void assertLessThanOrEquals(String name, double comparedTo, double number) {
        if (greaterThan(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not less than or equal to " + comparedTo + " !");
        }
    }

    public static void assertGreaterThan(String name, double comparedTo, double number) {
        if (lessThanOrEquals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not greater than " + comparedTo + " !");
        }
    }

    public static void assertGreaterThanOrEquals(String name, double comparedTo, double number) {
        if (lessThan(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not greater than or equal to " + comparedTo + " !");
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
