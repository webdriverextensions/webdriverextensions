package org.andidev.webdriverextension.internal;

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

    /* Click */
    public static void click(WebElement webElement) {
        webElement.click();
    }



    /* Type */
    public static void type(String text, WebElement webElement) {
        if (text == null) {
            return;
        }
        webElement.sendKeys(text);
    }

    public static void type(Double number, WebElement webElement) {
        if (number == null) {
            return;
        }
        type(toString(number), webElement);
    }



    /* Clear */
    public static void clear(WebElement webElement) {
        webElement.clear();
    }

    public static void clearAndType(String text, WebElement webElement) {
        clear(webElement);
        type(text, webElement);
    }

    public static void clearAndType(Double number, WebElement webElement) {
        clear(webElement);
        type(number, webElement);
    }



    /* Press Keys */
    public static void pressEnter(WebElement webElement) {
        pressKeys(webElement, Keys.ENTER);
    }

    public static void pressKeys(WebElement webElement, CharSequence... keys) {
        webElement.sendKeys(keys);
    }



    /* Select/Deselect */
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

    public static void selectOptionWithValue(String value, WebElement webElement) {
        new Select(webElement).selectByValue(value);
    }

    public static void deselectOptionWithValue(String value, WebElement webElement) {
        new Select(webElement).deselectByValue(value);
    }

    public static void selectOptionWithIndex(int index, WebElement webElement) {
        new Select(webElement).selectByIndex(index);
    }

    public static void deselectOptionWithIndex(int index, WebElement webElement) {
        new Select(webElement).selectByIndex(index);
    }



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



    /* Wait For */
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

    public static void waitForElementToDisplay(WebElement webElement, long secondsToWait, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementToDisplay(WebElement webElement, long secondsToWait, long sleepInMillis, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, secondsToWait, sleepInMillis);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }



    /* Debug */
    public static void debug(String str) {
        log.debug(str);
    }

    public static void debug(WebElement webElement) {
        log.debug("{} has text = \"{}\"", describeTag(webElement), textIn(webElement));
    }

    public static void debug(List<? extends WebElement> webElements) {
        log.debug("List contains the following {} tags", sizeOf(webElements));
        for (WebElement webElement : webElements) {
            debug(webElement);
        }
    }



    /* Is Open */
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



    /* Is Displayed */
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



    /* Size */
    public static int sizeOf(Collection collection) {
        return collection.size();
    }

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


    /* Url */
    public static String url(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public static boolean urlEquals(String url, WebDriver driver) {
        return equals(url, url(driver));
    }

    public static boolean urlNotEquals(String url, WebDriver driver) {
        return notEquals(url, url(driver));
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

    public static boolean urlMatches(String regExp, WebDriver driver) {
        return matches(regExp, url(driver));
    }

    public static boolean urlNotMatches(String regExp, WebDriver driver) {
        return notMatches(regExp, url(driver));
    }

    public static void assertUrlEquals(String url, WebDriver driver) {
        assertEquals("Url", url, url(driver));
    }

    public static void assertUrlNotEquals(String url, WebDriver driver) {
        assertNotEquals("Url", url, url(driver));
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

    public static void assertUrlMatches(String regExp, WebDriver driver) {
        assertMatches("Url", regExp, url(driver));
    }

    public static void assertUrlNotMatches(String regExp, WebDriver driver) {
        assertNotMatches("Url", regExp, url(driver));
    }



    /* Title */
    public static String title(WebDriver driver) {
        return driver.getTitle();
    }

    public static boolean titleEquals(String title, WebDriver driver) {
        return equals(title, title(driver));
    }

    public static boolean titleNotEquals(String title, WebDriver driver) {
        return notEquals(title, title(driver));
    }

    public static boolean titleContains(String searchText, WebDriver driver) {
        return contains(searchText, title(driver));
    }

    public static boolean titleNotContains(String searchText, WebDriver driver) {
        return notContains(searchText, title(driver));
    }

    public static boolean titleStartsWith(String prefix, WebDriver driver) {
        return startsWith(prefix, title(driver));
    }

    public static boolean titleNotStartsWith(String prefix, WebDriver driver) {
        return notStartsWith(prefix, title(driver));
    }

    public static boolean titleEndsWith(String suffix, WebDriver driver) {
        return endsWith(suffix, title(driver));
    }

    public static boolean titleNotEndsWith(String suffix, WebDriver driver) {
        return notEndsWith(suffix, title(driver));
    }

    public static boolean titleMatches(String regExp, WebDriver driver) {
        return matches(regExp, title(driver));
    }

    public static boolean titleNotMatches(String regExp, WebDriver driver) {
        return notMatches(regExp, title(driver));
    }

    public static void assertTitleEquals(String title, WebDriver driver) {
        assertEquals("Title", title, title(driver));
    }

    public static void assertTitleNotEquals(String title, WebDriver driver) {
        assertNotEquals("Title", title, title(driver));
    }

    public static void assertTitleContains(String searchText, WebDriver driver) {
        assertContains("Title", searchText, title(driver));
    }

    public static void assertTitleNotContains(String searchText, WebDriver driver) {
        assertNotContains("Title", searchText, title(driver));
    }

    public static void assertTitleStartsWith(String prefix, WebDriver driver) {
        assertStartsWith("Title", prefix, title(driver));
    }

    public static void assertTitleNotStartsWith(String prefix, WebDriver driver) {
        assertNotStartsWith("Title", prefix, title(driver));
    }

    public static void assertTitleEndsWith(String suffix, WebDriver driver) {
        assertEndsWith("Title", suffix, title(driver));
    }

    public static void assertTitleNotEndsWith(String suffix, WebDriver driver) {
        assertNotEndsWith("Title", suffix, title(driver));
    }

    public static void assertTitleMatches(String regExp, WebDriver driver) {
        assertMatches("Title", regExp, title(driver));
    }

    public static void assertTitleNotMatches(String regExp, WebDriver driver) {
        assertNotMatches("Title", regExp, title(driver));
    }



    /* Tag Name */
    public static String tagNameOf(WebElement webElement) {
        return webElement.getTagName();
    }

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
    public static String attributeIn(String name, WebElement webElement) {
        return webElement.getAttribute(name);
    }

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

    public static boolean attributeMatches(String name, String regExp, WebElement webElement) {
        return matches(regExp, attributeIn(name, webElement));
    }

    public static boolean attributeNotMatches(String name, String regExp, WebElement webElement) {
        return notMatches(regExp, attributeIn(name, webElement));
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

    public static void assertAttributeMatches(String name, String regExp, WebElement webElement) {
        assertMatches(name, regExp, attributeIn(name, webElement));
    }

    public static void assertAttributeNotMatches(String name, String regExp, WebElement webElement) {
        assertNotMatches(name, regExp, attributeIn(name, webElement));
    }



    /* Id */
    public static String idIn(WebElement webElement) {
        return attributeIn("id", webElement);
    }

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
        return notEndsWith(suffix, idIn(webElement));
    }

    public static boolean idMatches(String regExp, WebElement webElement) {
        return matches(regExp, idIn(webElement));
    }

    public static boolean idNotMatches(String regExp, WebElement webElement) {
        return notMatches(regExp, idIn(webElement));
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

    public static void assertIdMatches(String regExp, WebElement webElement) {
        assertMatches("id", regExp, idIn(webElement));
    }

    public static void assertIdNotMatches(String regExp, WebElement webElement) {
        assertNotMatches("id", regExp, idIn(webElement));
    }



    /* Name */
    public static String nameIn(WebElement webElement) {
        return attributeIn("name", webElement);
    }

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
        return notEndsWith(suffix, nameIn(webElement));
    }

    public static boolean nameMatches(String regExp, WebElement webElement) {
        return matches(regExp, nameIn(webElement));
    }

    public static boolean nameNotMatches(String regExp, WebElement webElement) {
        return notMatches(regExp, nameIn(webElement));
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

    public static void assertNameMatches(String regExp, WebElement webElement) {
        assertMatches("name", regExp, nameIn(webElement));
    }

    public static void assertNameNotMatches(String regExp, WebElement webElement) {
        assertNotMatches("name", regExp, nameIn(webElement));
    }



    /* Class */
    public static String classIn(WebElement webElement) {
        return attributeIn("class", webElement);
    }

    public static List<String> classesIn(WebElement webElement) {
        return Arrays.asList(StringUtils.split(classIn(webElement)));
    }

    public static boolean hasClass(WebElement webElement) {
        return hasAttribute("class", webElement);
    }

    public static boolean hasNotClass(WebElement webElement) {
        return hasNotAttribute("class", webElement);
    }

    public static boolean hasClass(String className, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (equals(className, clazz)) {
                return true;
            }
        }
        return false;
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

    public static boolean hasClassMatching(String regExp, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (matches(regExp, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClassMatching(String regExp, WebElement webElement) {
        return !hasClassMatching(regExp, webElement);
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

    public static void assertHasClassMatching(String regExp, WebElement webElement) {
        if (hasNotClassMatching(regExp, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class matching regExp " + regExp.trim() + "!");
        }
    }

    public static void assertHasNotClassMatching(String regExp, WebElement webElement) {
        if (hasClassMatching(regExp, webElement)) {
            Assert.fail(describeTag(webElement) + " has class matching regExp " + regExp.trim() + " when it shouldn't!");
        }
    }



    /* Value */
    public static String valueIn(WebElement webElement) {
        return attributeIn("value", webElement);
    }

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
        return notEndsWith(suffix, valueIn(webElement));
    }

    public static boolean valueMatches(String regExp, WebElement webElement) {
        return matches(regExp, valueIn(webElement));
    }

    public static boolean valueNotMatches(String regExp, WebElement webElement) {
        return notMatches(regExp, valueIn(webElement));
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

    public static void assertValueMatches(String regExp, WebElement webElement) {
        assertMatches("value", regExp, valueIn(webElement));
    }

    public static void assertValueNotMatches(String regExp, WebElement webElement) {
        assertNotMatches("value", regExp, valueIn(webElement));
    }



    /* Value Number */
    public static Double valueInAsNumber(WebElement webElement) {
        try {
            return NumberUtils.createDouble(valueIn(webElement));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static boolean valueIsNumber(WebElement webElement) {
        return valueInAsNumber(webElement) != null;
    }

    public static boolean valueIsNotNumber(WebElement webElement) {
        return valueInAsNumber(webElement) == null;
    }

    public static boolean valueEquals(Double number, WebElement webElement) {
        return equals(number, valueInAsNumber(webElement));
    }

    public static boolean valueNotEquals(Double number, WebElement webElement) {
        return notEquals(number, valueInAsNumber(webElement));
    }

    public static boolean valueLessThan(Double number, WebElement webElement) {
        return lessThan(number, valueInAsNumber(webElement));
    }

    public static boolean valueLessThanOrEquals(Double number, WebElement webElement) {
        return lessThanOrEquals(number, valueInAsNumber(webElement));
    }

    public static boolean valueGreaterThan(Double number, WebElement webElement) {
        return greaterThan(number, valueInAsNumber(webElement));
    }

    public static boolean valueGreaterThanOrEquals(Double number, WebElement webElement) {
        return greaterThanOrEquals(number, valueInAsNumber(webElement));
    }

    public static void assertValueIsNumber(WebElement webElement) {
        if (valueIsNotNumber(webElement)) {
            Assert.fail("value: " + valueInAsNumber(webElement) + " is no number!");
        }
    }

    public static void assertValueIsNotNumber(WebElement webElement) {
        if (valueIsNumber(webElement)) {
            Assert.fail("value: " + valueInAsNumber(webElement) + " is number when it shouldn't!");
        }
    }

    public static void assertValueEquals(Double number, WebElement webElement) {
        assertEquals("value", number, valueInAsNumber(webElement));
    }

    public static void assertValueNotEquals(Double number, WebElement webElement) {
        assertNotEequals("value", number, valueInAsNumber(webElement));
    }

    public static void assertValueLessThan(Double number, WebElement webElement) {
        assertLessThan("value", number, valueInAsNumber(webElement));
    }

    public static void assertValueLessThanOrEquals(Double number, WebElement webElement) {
        assertLessThanOrEquals("value", number, valueInAsNumber(webElement));
    }

    public static void assertValueGreaterThan(Double number, WebElement webElement) {
        assertGreaterThan("value", number, valueInAsNumber(webElement));
    }

    public static void assertValueGreaterThanOrEquals(Double number, WebElement webElement) {
        assertGreaterThanOrEquals("value", number, valueInAsNumber(webElement));
    }



    /* Href */
    public static String hrefIn(WebElement webElement) {
        return attributeIn("href", webElement);
    }

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
        return notEndsWith(suffix, hrefIn(webElement));
    }

    public static boolean hrefMatches(String regExp, WebElement webElement) {
        return matches(regExp, hrefIn(webElement));
    }

    public static boolean hrefNotMatches(String regExp, WebElement webElement) {
        return notMatches(regExp, hrefIn(webElement));
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

    public static void assertHrefMatches(String regExp, WebElement webElement) {
        assertMatches("href", regExp, hrefIn(webElement));
    }

    public static void assertHrefNotMatches(String regExp, WebElement webElement) {
        assertNotMatches("href", regExp, hrefIn(webElement));
    }



    /* Text */
    public static String textIn(WebElement webElement) {
        return webElement.getText();
    }

    public static boolean hasText(WebElement webElement) {
        return notEquals("", textIn(webElement));
    }

    public static boolean hasNotText(WebElement webElement) {
        return equals("", textIn(webElement));
    }

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
        return notEndsWith(suffix, textIn(webElement));
    }

    public static boolean textMatches(String regExp, WebElement webElement) {
        return matches(regExp, textIn(webElement));
    }

    public static boolean textNotMatches(String regExp, WebElement webElement) {
        return notMatches(regExp, textIn(webElement));
    }

    public static void assertHasText(WebElement webElement) {
        if (hasNotText(webElement)) {
            Assert.fail(describeTag(webElement) + " has no text!");
        }
    }

    public static void assertHasNotText(WebElement webElement) {
        if (hasText(webElement)) {
            Assert.fail(describeTag(webElement) + " has text \"" + textIn(webElement) + "\" when it shouldn't!");
        }
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

    public static void assertTextMatches(String regExp, WebElement webElement) {
        assertMatches("Text", regExp, textIn(webElement));
    }

    public static void assertTextNotMatches(String regExp, WebElement webElement) {
        assertNotMatches("Text", regExp, textIn(webElement));
    }



    /* Text Number */
    public static Double textInAsNumber(WebElement webElement) {
        try {
            return NumberUtils.createDouble(webElement.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static boolean textIsNumber(WebElement webElement) {
        return textInAsNumber(webElement) != null;
    }

    public static boolean textIsNotNumber(WebElement webElement) {
        return textInAsNumber(webElement) == null;
    }

    public static boolean textEquals(Double number, WebElement webElement) {
        return equals(number, textInAsNumber(webElement));
    }

    public static boolean textNotEquals(Double number, WebElement webElement) {
        return notEquals(number, textInAsNumber(webElement));
    }

    public static boolean textLessThan(Double number, WebElement webElement) {
        return lessThan(number, textInAsNumber(webElement));
    }

    public static boolean textLessThanOrEquals(Double number, WebElement webElement) {
        return lessThanOrEquals(number, textInAsNumber(webElement));
    }

    public static boolean textGreaterThan(Double number, WebElement webElement) {
        return greaterThan(number, textInAsNumber(webElement));
    }

    public static boolean textGreaterThanOrEquals(Double number, WebElement webElement) {
        return greaterThanOrEquals(number, textInAsNumber(webElement));
    }

    public static void assertTextIsNumber(WebElement webElement) {
        if (textIsNotNumber(webElement)) {
            Assert.fail("Text: " + textInAsNumber(webElement) + " is no number!");
        }
    }

    public static void assertTextIsNotNumber(WebElement webElement) {
        if (textIsNumber(webElement)) {
            Assert.fail("Text: " + textInAsNumber(webElement) + " is number when it shouldn't!");
        }
    }

    public static void assertTextEquals(Double number, WebElement webElement) {
        assertEquals("Text", number, textInAsNumber(webElement));
    }

    public static void assertTextNotEquals(Double number, WebElement webElement) {
        assertNotEequals("Text", number, textInAsNumber(webElement));
    }

    public static void assertTextLessThan(Double number, WebElement webElement) {
        assertLessThan("Text", number, textInAsNumber(webElement));
    }

    public static void assertTextLessThanOrEquals(Double number, WebElement webElement) {
        assertLessThanOrEquals("Text", number, textInAsNumber(webElement));
    }

    public static void assertTextGreaterThan(Double number, WebElement webElement) {
        assertGreaterThan("Text", number, textInAsNumber(webElement));
    }

    public static void assertTextGreaterThanOrEquals(Double number, WebElement webElement) {
        assertGreaterThanOrEquals("Text", number, textInAsNumber(webElement));
    }



    /* Selected/Deselected */
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
        if (isDisabled(webElement)) {
            Assert.fail(describeTag(webElement) + " is not enabled!");
        }
    }

    public static void assertIsDisabled(WebElement webElement) {
        if (isEnabled(webElement)) {
            Assert.fail(describeTag(webElement) + " is not disabled!");
        }
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

    public static boolean optionIsEnabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionIsDisabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionIsSelected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionIsDeselected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean allOptionsAreSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isDeselected(option)) {
                return false;
            }
        }
        return true;
    }

    public static boolean noOptionIsSelected(WebElement webElement) {
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
            Assert.fail(describeTag(webElement) + " has no option \"" + text.trim() + "\"!");
        }
    }

    public static void assertHasNotOption(String text, WebElement webElement) {
        if (hasOption(text, webElement)) {
            Assert.fail(describeTag(webElement) + " has option \"" + text.trim() + "\" when it shouldn't!");
        }
    }

    public static void assertOptionIsEnabled(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsDisabled(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not enabled!");
        }
    }

    public static void assertOptionIsDisabled(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsEnabled(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not disabled!");
        }
    }

    public static void assertOptionIsSelected(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsDeselected(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not selected!");
        }
    }

    public static void assertOptionIsDeselected(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsSelected(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not deselected!");
        }
    }

    public static void assertAllOptionsAreSelected(WebElement webElement) {
        if (!allOptionsAreSelected(webElement)) {
            Assert.fail("All options are not selected!");
        }
    }

    public static void assertNoOptionIsSelected(WebElement webElement) {
        if (!noOptionIsSelected(webElement)) {
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

    public static boolean optionWithValueIsEnabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionWithValueIsDisabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionWithValueIsSelected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionWithValueIsDeselected(String value, WebElement webElement) {
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
            Assert.fail(describeTag(webElement) + " has no option with value \"" + value.trim() + "\"!");
        }
    }

    public static void assertHasNotOptionWithValue(String value, WebElement webElement) {
        if (hasOptionWithValue(value, webElement)) {
            Assert.fail(describeTag(webElement) + " has option with value \"" + value.trim() + "\" when it shouldn't!");
        }
    }

    public static void assertOptionWithValueIsEnabled(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsDisabled(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not enabled!");
        }
    }

    public static void assertOptionWithValueIsDisabled(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsEnabled(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not disabled!");
        }
    }

    public static void assertOptionWithValueIsSelected(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsDeselected(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not selected!");
        }
    }

    public static void assertOptionWithValueIsDeselected(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsSelected(value, webElement)) {
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
        return !hasOptionWithIndex(index, webElement);
    }

    public static boolean optionWithIndexIsEnabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isEnabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean optionWithIndexIsDisabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDisabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean optionWithIndexIsSelected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isSelected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean optionWithIndexIsDeselected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDeselected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static void assertHasOptionWithIndex(int index, WebElement webElement) {
        if (hasNotOptionWithIndex(index, webElement)) {
            Assert.fail(describeTag(webElement) + " has no option with index \"" + index + "\"!");
        }
    }

    public static void assertHasNotOptionWithIndex(int index, WebElement webElement) {
        if (hasOptionWithIndex(index, webElement)) {
            Assert.fail(describeTag(webElement) + " has option with index \"" + index + "\" when it shouldn't!");
        }
    }

    public static void assertOptionWithIndexIsEnabled(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsDisabled(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not enabled!");
        }
    }

    public static void assertOptionWithIndexIsDisabled(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsEnabled(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not disabled!");
        }
    }

    public static void assertOptionWithIndexIsSelected(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsDeselected(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not selected!");
        }
    }

    public static void assertOptionWithIndexIsDeselected(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsSelected(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not deselected!");
        }
    }



    /* String Equals */
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



    /* Double Equals */
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



    /* Describe */
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
