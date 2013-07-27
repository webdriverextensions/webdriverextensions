package org.andidev.webdriverextension;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.andidev.webdriverextension.internal.BotUtils;
import org.andidev.webdriverextension.internal.Openable;
import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.math.NumberUtils.*;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.andidev.webdriverextension.internal.utils.StringUtils.*;
import org.andidev.webdriverextension.internal.utils.NumberUtils;

public class Bot {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Bot.class);

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

    public static void type(double number, WebElement webElement) {
        type(NumberUtils.toString(number), webElement);
    }



    /* Clear */
    public static void clear(WebElement webElement) {
        webElement.clear();
    }

    public static void clearAndType(String text, WebElement webElement) {
        clear(webElement);
        type(text, webElement);
    }

    public static void clearAndType(double number, WebElement webElement) {
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
    public static void open(String url) {
        ThreadDriver.getDriver().get(url);
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

    public static void waitForElementToDisplay(WebElement webElement) {
        waitForElementToDisplay(webElement, 30);
    }

    public static void waitForElementToDisplay(WebElement webElement, long secondsToWait) {
        WebDriverWait wait = new WebDriverWait(ThreadDriver.getDriver(), secondsToWait);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementToDisplay(WebElement webElement, long secondsToWait, long sleepInMillis) {
        WebDriverWait wait = new WebDriverWait(ThreadDriver.getDriver(), secondsToWait, sleepInMillis);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }



    /* Debug */
    public static void debug(String str) {
        log.debug(str);
    }

    public static void debug(WebElement webElement) {
        String tag = BotUtils.tagIn(webElement);
        log.debug("Tag:" + appendNewLineIfContainsNewLine(BotUtils.tagIn(webElement)));
    }

    public static void debug(List<? extends WebElement> webElements) {
        log.debug("List contains the following {} tags:", sizeOf(webElements));
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

    public static boolean isDisplayed(WebElement webElement, long secondsToWait) {
        try {
            WebElement foundWebElement = new WebDriverWait(ThreadDriver.getDriver(), secondsToWait).until(ExpectedConditions.visibilityOf(webElement));
            if (foundWebElement != null) {
                return true;
            } else {
                return false;
            }
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static boolean isNotDisplayed(WebElement webElement, long secondsToWait) {
        return !isDisplayed(webElement, secondsToWait);
    }

    public static void assertIsDisplayed(WebElement webElement) {
        if (isNotDisplayed(webElement)) {
            throw new AssertionError("WebElement is not displayed!");
        }
    }

    public static void assertIsNotDisplayed(WebElement webElement) {
        if (isDisplayed(webElement)) {
            throw new AssertionError("WebElement is displayed when it shouldn't!");
        }
    }

    public static void assertIsDisplayed(WebElement webElement, long secondsToWait) {
        if (isNotDisplayed(webElement, secondsToWait)) {
            throw new AssertionError("WebElement is not displayed within " + secondsToWait + " seconds!");
        }
    }

    public static void assertIsNotDisplayed(WebElement webElement, long secondsToWait) {
        if (isDisplayed(webElement, secondsToWait)) {
            throw new AssertionError("WebElement is displayed within " + secondsToWait + " seconds when it shouldn't!");
        }
    }



    /* Size */
    public static int sizeOf(Collection collection) {
        return collection.size();
    }

    public static boolean sizeEquals(int number, Collection collection) {
        return BotUtils.equals((double) number, (double) collection.size());
    }

    public static boolean sizeNotEquals(int number, Collection collection) {
        return BotUtils.notEquals((double) number, (double) collection.size());
    }

    public static boolean sizeLessThan(int number, Collection collection) {
        return BotUtils.lessThan((double) number, (double) collection.size());
    }

    public static boolean sizeLessThanOrEquals(int number, Collection collection) {
        return BotUtils.lessThanOrEquals((double) number, (double) collection.size());
    }

    public static boolean sizeGreaterThan(int number, Collection collection) {
        return BotUtils.greaterThan((double) number, (double) collection.size());
    }

    public static boolean sizeGreaterThanOrEquals(int number, Collection collection) {
        return BotUtils.greaterThanOrEquals((double) number, (double) collection.size());
    }

    public static void assertSizeEquals(int number, Collection collection) {
        BotUtils.assertEquals("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeNotEquals(int number, Collection collection) {
        BotUtils.assertNotEquals("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeLessThan(int number, Collection collection) {
        BotUtils.assertLessThan("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeLessThanOrEquals(int number, Collection collection) {
        BotUtils.assertLessThanOrEquals("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeGreaterThan(int number, Collection collection) {
        BotUtils.assertGreaterThan("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeGreaterThanOrEquals(int number, Collection collection) {
        BotUtils.assertGreaterThanOrEquals("Size", (double) number, (double) collection.size());
    }


    /* Current Url */
    public static String currentUrl() {
        return ThreadDriver.getDriver().getCurrentUrl();
    }

    public static boolean currentUrlEquals(String url) {
        return BotUtils.equals(url, currentUrl());
    }

    public static boolean currentUrlNotEquals(String url) {
        return BotUtils.notEquals(url, currentUrl());
    }

    public static boolean currentUrlContains(String searchText) {
        return BotUtils.contains(searchText, currentUrl());
    }

    public static boolean currentUrlNotContains(String searchText) {
        return BotUtils.notContains(searchText, currentUrl());
    }

    public static boolean currentUrlStartsWith(String prefix) {
        return BotUtils.startsWith(prefix, currentUrl());
    }

    public static boolean currentUrlNotStartsWith(String prefix) {
        return BotUtils.notStartsWith(prefix, currentUrl());
    }

    public static boolean currentUrlEndsWith(String suffix) {
        return BotUtils.endsWith(suffix, currentUrl());
    }

    public static boolean currentUrlNotEndsWith(String suffix) {
        return BotUtils.notEndsWith(suffix, currentUrl());
    }

    public static boolean currentUrlMatches(String regExp) {
        return BotUtils.matches(regExp, currentUrl());
    }

    public static boolean currentUrlNotMatches(String regExp) {
        return BotUtils.notMatches(regExp, currentUrl());
    }

    public static void assertCurrentUrlEquals(String url) {
        BotUtils.assertEquals("Current url", url, currentUrl());
    }

    public static void assertCurrentUrlNotEquals(String url) {
        BotUtils.assertNotEquals("Current url", url, currentUrl());
    }

    public static void assertCurrentUrlContains(String searchText) {
        BotUtils.assertContains("Current url", searchText, currentUrl());
    }

    public static void assertCurrentUrlNotContains(String searchText) {
        BotUtils.assertNotContains("Current url", searchText, currentUrl());
    }

    public static void assertCurrentUrlStartsWith(String prefix) {
        BotUtils.assertStartsWith("Current url", prefix, currentUrl());
    }

    public static void assertCurrentUrlNotStartsWith(String prefix) {
        BotUtils.assertNotStartsWith("Current url", prefix, currentUrl());
    }

    public static void assertCurrentUrlEndsWith(String suffix) {
        BotUtils.assertEndsWith("Current url", suffix, currentUrl());
    }

    public static void assertCurrentUrlNotEndsWith(String suffix) {
        BotUtils.assertNotEndsWith("Current url", suffix, currentUrl());
    }

    public static void assertCurrentUrlMatches(String regExp) {
        BotUtils.assertMatches("Current url", regExp, currentUrl());
    }

    public static void assertCurrentUrlNotMatches(String regExp) {
        BotUtils.assertNotMatches("Current url", regExp, currentUrl());
    }



    /* Title */
    public static String title() {
        return ThreadDriver.getDriver().getTitle();
    }

    public static boolean titleEquals(String title) {
        return BotUtils.equals(title, title());
    }

    public static boolean titleNotEquals(String title) {
        return BotUtils.notEquals(title, title());
    }

    public static boolean titleContains(String searchText) {
        return BotUtils.contains(searchText, title());
    }

    public static boolean titleNotContains(String searchText) {
        return BotUtils.notContains(searchText, title());
    }

    public static boolean titleStartsWith(String prefix) {
        return BotUtils.startsWith(prefix, title());
    }

    public static boolean titleNotStartsWith(String prefix) {
        return BotUtils.notStartsWith(prefix, title());
    }

    public static boolean titleEndsWith(String suffix) {
        return BotUtils.endsWith(suffix, title());
    }

    public static boolean titleNotEndsWith(String suffix) {
        return BotUtils.notEndsWith(suffix, title());
    }

    public static boolean titleMatches(String regExp) {
        return BotUtils.matches(regExp, title());
    }

    public static boolean titleNotMatches(String regExp) {
        return BotUtils.notMatches(regExp, title());
    }

    public static void assertTitleEquals(String title) {
        BotUtils.assertEquals("Title", title, title());
    }

    public static void assertTitleNotEquals(String title) {
        BotUtils.assertNotEquals("Title", title, title());
    }

    public static void assertTitleContains(String searchText) {
        BotUtils.assertContains("Title", searchText, title());
    }

    public static void assertTitleNotContains(String searchText) {
        BotUtils.assertNotContains("Title", searchText, title());
    }

    public static void assertTitleStartsWith(String prefix) {
        BotUtils.assertStartsWith("Title", prefix, title());
    }

    public static void assertTitleNotStartsWith(String prefix) {
        BotUtils.assertNotStartsWith("Title", prefix, title());
    }

    public static void assertTitleEndsWith(String suffix) {
        BotUtils.assertEndsWith("Title", suffix, title());
    }

    public static void assertTitleNotEndsWith(String suffix) {
        BotUtils.assertNotEndsWith("Title", suffix, title());
    }

    public static void assertTitleMatches(String regExp) {
        BotUtils.assertMatches("Title", regExp, title());
    }

    public static void assertTitleNotMatches(String regExp) {
        BotUtils.assertNotMatches("Title", regExp, title());
    }



    /* Tag Name */
    public static String tagNameOf(WebElement webElement) {
        return webElement.getTagName();
    }

    public static boolean tagNameEquals(String value, WebElement webElement) {
        return BotUtils.equals(value, tagNameOf(webElement));
    }

    public static boolean tagNameNotEquals(String value, WebElement webElement) {
        return BotUtils.notEquals(value, tagNameOf(webElement));
    }

    public static void assertTagNameEquals(String value, WebElement webElement) {
        BotUtils.assertEquals("Tag name", value, tagNameOf(webElement));
    }

    public static void assertTagNameNotEquals(String value, WebElement webElement) {
        BotUtils.assertNotEquals("Tag name", value, tagNameOf(webElement));
    }



    /* Attribute */
    /**
     * Returns a {@link org.openqa.selenium.WebElement} attribute value.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input title="Some title"/>
     * attributeIn("title", input) = "Some title"
     *
     * no input in html
     * attributeIn("title", "input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * an attribute to return
     * @return the id attribute
     */
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
        return BotUtils.equals(value, attributeIn(name, webElement));
    }

    public static boolean attributeNotEquals(String name, String value, WebElement webElement) {
        return BotUtils.notEquals(value, attributeIn(name, webElement));
    }

    public static boolean attributeContains(String name, String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, attributeIn(name, webElement));
    }

    public static boolean attributeNotContains(String name, String searchText, WebElement webElement) {
        return BotUtils.notContains(searchText, attributeIn(name, webElement));
    }

    public static boolean attributeStartsWith(String name, String prefix, WebElement webElement) {
        return BotUtils.startsWith(prefix, attributeIn(name, webElement));
    }

    public static boolean attributeNotStartsWith(String name, String prefix, WebElement webElement) {
        return BotUtils.notStartsWith(prefix, attributeIn(name, webElement));
    }

    public static boolean attributeEndsWith(String name, String suffix, WebElement webElement) {
        return BotUtils.endsWith(suffix, attributeIn(name, webElement));
    }

    public static boolean attributeNotEndsWith(String name, String suffix, WebElement webElement) {
        return BotUtils.notEndsWith(suffix, attributeIn(name, webElement));
    }

    public static boolean attributeMatches(String name, String regExp, WebElement webElement) {
        return BotUtils.matches(regExp, attributeIn(name, webElement));
    }

    public static boolean attributeNotMatches(String name, String regExp, WebElement webElement) {
        return BotUtils.notMatches(regExp, attributeIn(name, webElement));
    }

    public static void assertHasAttribute(String name, WebElement webElement) {
        if (hasNotAttribute(name, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " does not have the " + name + " attribute!");
        }
    }

    public static void assertHasNotAttribute(String name, WebElement webElement) {
        if (hasAttribute(name, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " has the " + name + " attribute when it shouldn't!");
        }
    }

    public static void assertAttributeEquals(String name, String value, WebElement webElement) {
        BotUtils.assertEquals(name, value, attributeIn(name, webElement));
    }

    public static void assertAttributeNotEquals(String name, String value, WebElement webElement) {
        BotUtils.assertNotEquals(name, value, attributeIn(name, webElement));
    }

    public static void assertAttributeContains(String name, String searchText, WebElement webElement) {
        BotUtils.assertContains(name, searchText, attributeIn(name, webElement));
    }

    public static void assertAttributeNotContains(String name, String searchText, WebElement webElement) {
        BotUtils.assertNotContains(name, searchText, attributeIn(name, webElement));
    }

    public static void assertAttributeStartsWith(String name, String prefix, WebElement webElement) {
        BotUtils.assertStartsWith(name, prefix, attributeIn(name, webElement));
    }

    public static void assertAttributeNotStartsWith(String name, String prefix, WebElement webElement) {
        BotUtils.assertNotStartsWith(name, prefix, attributeIn(name, webElement));
    }

    public static void assertAttributeEndsWith(String name, String suffix, WebElement webElement) {
        BotUtils.assertEndsWith(name, suffix, attributeIn(name, webElement));
    }

    public static void assertAttributeNotEndsWith(String name, String suffix, WebElement webElement) {
        BotUtils.assertNotEndsWith(name, suffix, attributeIn(name, webElement));
    }

    public static void assertAttributeMatches(String name, String regExp, WebElement webElement) {
        BotUtils.assertMatches(name, regExp, attributeIn(name, webElement));
    }

    public static void assertAttributeNotMatches(String name, String regExp, WebElement webElement) {
        BotUtils.assertNotMatches(name, regExp, attributeIn(name, webElement));
    }



    /* Id */
    /**
     * Returns the {@link org.openqa.selenium.WebElement} id attribute.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input id="some-id"/>
     * idIn(input) = "some-id"
     *
     * no input in html
     * idIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * an id attribute
     * @return the id attribute
     */
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
        return BotUtils.equals(value, idIn(webElement));
    }

    public static boolean idNotEquals(String value, WebElement webElement) {
        return BotUtils.notEquals(value, idIn(webElement));
    }

    public static boolean idContains(String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, idIn(webElement));
    }

    public static boolean idNotContains(String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, idIn(webElement));
    }

    public static boolean idStartsWith(String prefix, WebElement webElement) {
        return BotUtils.startsWith(prefix, idIn(webElement));
    }

    public static boolean idNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.notStartsWith(prefix, idIn(webElement));
    }

    public static boolean idEndsWith(String suffix, WebElement webElement) {
        return BotUtils.endsWith(suffix, idIn(webElement));
    }

    public static boolean idNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.notEndsWith(suffix, idIn(webElement));
    }

    public static boolean idMatches(String regExp, WebElement webElement) {
        return BotUtils.matches(regExp, idIn(webElement));
    }

    public static boolean idNotMatches(String regExp, WebElement webElement) {
        return BotUtils.notMatches(regExp, idIn(webElement));
    }

    public static void assertHasId(WebElement webElement) {
        assertHasAttribute("id", webElement);
    }

    public static void assertHasNotId(WebElement webElement) {
        assertHasNotAttribute("id", webElement);
    }

    public static void assertIdEquals(String value, WebElement webElement) {
        BotUtils.assertEquals("id", value, idIn(webElement));
    }

    public static void assertIdNotEquals(String value, WebElement webElement) {
        BotUtils.assertNotEquals("id", value, idIn(webElement));
    }

    public static void assertIdContains(String searchText, WebElement webElement) {
        BotUtils.assertContains("id", searchText, idIn(webElement));
    }

    public static void assertIdNotContains(String searchText, WebElement webElement) {
        BotUtils.assertNotContains("id", searchText, idIn(webElement));
    }

    public static void assertIdStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertStartsWith("id", prefix, idIn(webElement));
    }

    public static void assertIdNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertNotStartsWith("id", prefix, idIn(webElement));
    }

    public static void assertIdEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertEndsWith("id", suffix, idIn(webElement));
    }

    public static void assertIdNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertNotEndsWith("id", suffix, idIn(webElement));
    }

    public static void assertIdMatches(String regExp, WebElement webElement) {
        BotUtils.assertMatches("id", regExp, idIn(webElement));
    }

    public static void assertIdNotMatches(String regExp, WebElement webElement) {
        BotUtils.assertNotMatches("id", regExp, idIn(webElement));
    }



    /* Name */
    /**
     * Returns the {@link org.openqa.selenium.WebElement} name attribute.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input name="some-name"/>
     * nameIn(input) = "some-name"
     *
     * no input in html
     * nameIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a name attribute
     * @return the name attribute
     */
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
        return BotUtils.equals(value, nameIn(webElement));
    }

    public static boolean nameNotEquals(String value, WebElement webElement) {
        return BotUtils.notEquals(value, nameIn(webElement));
    }

    public static boolean nameContains(String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, nameIn(webElement));
    }

    public static boolean nameNotContains(String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, nameIn(webElement));
    }

    public static boolean nameStartsWith(String prefix, WebElement webElement) {
        return BotUtils.startsWith(prefix, nameIn(webElement));
    }

    public static boolean nameNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.notStartsWith(prefix, nameIn(webElement));
    }

    public static boolean nameEndsWith(String suffix, WebElement webElement) {
        return BotUtils.endsWith(suffix, nameIn(webElement));
    }

    public static boolean nameNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.notEndsWith(suffix, nameIn(webElement));
    }

    public static boolean nameMatches(String regExp, WebElement webElement) {
        return BotUtils.matches(regExp, nameIn(webElement));
    }

    public static boolean nameNotMatches(String regExp, WebElement webElement) {
        return BotUtils.notMatches(regExp, nameIn(webElement));
    }

    public static void assertHasName(WebElement webElement) {
        assertHasAttribute("name", webElement);
    }

    public static void assertHasNotName(WebElement webElement) {
        assertHasNotAttribute("name", webElement);
    }

    public static void assertNameEquals(String value, WebElement webElement) {
        BotUtils.assertEquals("name", value, nameIn(webElement));
    }

    public static void assertNameNotEquals(String value, WebElement webElement) {
        BotUtils.assertNotEquals("name", value, nameIn(webElement));
    }

    public static void assertNameContains(String searchText, WebElement webElement) {
        BotUtils.assertContains("name", searchText, nameIn(webElement));
    }

    public static void assertNameNotContains(String searchText, WebElement webElement) {
        BotUtils.assertNotContains("name", searchText, nameIn(webElement));
    }

    public static void assertNameStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertStartsWith("name", prefix, nameIn(webElement));
    }

    public static void assertNameNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertNotStartsWith("name", prefix, nameIn(webElement));
    }

    public static void assertNameEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertEndsWith("name", suffix, nameIn(webElement));
    }

    public static void assertNameNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertNotEndsWith("name", suffix, nameIn(webElement));
    }

    public static void assertNameMatches(String regExp, WebElement webElement) {
        BotUtils.assertMatches("name", regExp, nameIn(webElement));
    }

    public static void assertNameNotMatches(String regExp, WebElement webElement) {
        BotUtils.assertNotMatches("name", regExp, nameIn(webElement));
    }



    /* Class */
    /**
     * Returns the {@link org.openqa.selenium.WebElement} class attribute.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input class="a-class another-class"/>
     * classIn(input) = "a-class another-class"
     *
     * no input in html
     * classIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a class attribute
     * @return the class attribute
     */
    public static String classIn(WebElement webElement) {
        return attributeIn("class", webElement);
    }

    /**
     * Returns the classes in the {@link org.openqa.selenium.WebElement} class
     * attribute.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input class=" a-class   another-class "/>
     * classesIn(input) = "a-class", "another-class"
     *
     * no input in html
     * classIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a class attribute
     * @return the classes in the class attribute
     */
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
            if (BotUtils.equals(className, clazz)) {
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
            if (BotUtils.contains(searchText, clazz)) {
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
            if (BotUtils.startsWith(prefix, clazz)) {
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
            if (BotUtils.endsWith(suffix, clazz)) {
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
            if (BotUtils.matches(regExp, clazz)) {
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
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " does not have class " + className.trim() + "!");
        }
    }

    public static void assertHasNotClass(String className, WebElement webElement) {
        if (hasClass(className, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " has class " + className.trim() + " when it shouldn't!");
        }
    }

    public static void assertHasClassContaining(String searchText, WebElement webElement) {
        if (hasNotClassContaining(searchText, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " does not have class containing text " + searchText.trim() + "!");
        }
    }

    public static void assertHasNotClassContaining(String searchText, WebElement webElement) {
        if (hasClassContaining(searchText, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " has class containing text " + searchText.trim() + " when it shouldn't!");
        }
    }

    public static void assertHasClassStartingWith(String prefix, WebElement webElement) {
        if (hasNotClassStartingWith(prefix, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " does not have class containing prefix " + prefix.trim() + "!");
        }
    }

    public static void assertHasNotClassStartingWith(String prefix, WebElement webElement) {
        if (hasClassStartingWith(prefix, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " has class containing prefix " + prefix.trim() + " when it shouldn't!");
        }
    }

    public static void assertHasClassEndingWith(String suffix, WebElement webElement) {
        if (hasNotClassEndingWith(suffix, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " does not have class containing suffix " + suffix.trim() + "!");
        }
    }

    public static void assertHasNotClassEndingWith(String suffix, WebElement webElement) {
        if (hasClassEndingWith(suffix, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " has class containing suffix " + suffix.trim() + " when it shouldn't!");
        }
    }

    public static void assertHasClassMatching(String regExp, WebElement webElement) {
        if (hasNotClassMatching(regExp, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " does not have class matching regExp " + regExp.trim() + "!");
        }
    }

    public static void assertHasNotClassMatching(String regExp, WebElement webElement) {
        if (hasClassMatching(regExp, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " has class matching regExp " + regExp.trim() + " when it shouldn't!");
        }
    }



    /* Value */
    /**
     * Returns the {@link org.openqa.selenium.WebElement} value attribute.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input value="Some value"/>
     * valueIn(input) = "Some value"
     *
     * no input in html
     * valueIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a value attribute
     * @return the value attribute
     */
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
        return BotUtils.equals(value, valueIn(webElement));
    }

    public static boolean valueNotEquals(String value, WebElement webElement) {
        return BotUtils.notEquals(value, valueIn(webElement));
    }

    public static boolean valueContains(String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, valueIn(webElement));
    }

    public static boolean valueNotContains(String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, valueIn(webElement));
    }

    public static boolean valueStartsWith(String prefix, WebElement webElement) {
        return BotUtils.startsWith(prefix, valueIn(webElement));
    }

    public static boolean valueNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.notStartsWith(prefix, valueIn(webElement));
    }

    public static boolean valueEndsWith(String suffix, WebElement webElement) {
        return BotUtils.endsWith(suffix, valueIn(webElement));
    }

    public static boolean valueNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.notEndsWith(suffix, valueIn(webElement));
    }

    public static boolean valueMatches(String regExp, WebElement webElement) {
        return BotUtils.matches(regExp, valueIn(webElement));
    }

    public static boolean valueNotMatches(String regExp, WebElement webElement) {
        return BotUtils.notMatches(regExp, valueIn(webElement));
    }

    public static void assertHasValue(WebElement webElement) {
        assertHasAttribute("value", webElement);
    }

    public static void assertHasNotValue(WebElement webElement) {
        assertHasNotAttribute("value", webElement);
    }

    public static void assertValueEquals(String value, WebElement webElement) {
        BotUtils.assertEquals("value", value, valueIn(webElement));
    }

    public static void assertValueNotEquals(String value, WebElement webElement) {
        BotUtils.assertNotEquals("value", value, valueIn(webElement));
    }

    public static void assertValueContains(String searchText, WebElement webElement) {
        BotUtils.assertContains("value", searchText, valueIn(webElement));
    }

    public static void assertValueNotContains(String searchText, WebElement webElement) {
        BotUtils.assertNotContains("value", searchText, valueIn(webElement));
    }

    public static void assertValueStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertStartsWith("value", prefix, valueIn(webElement));
    }

    public static void assertValueNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertNotStartsWith("value", prefix, valueIn(webElement));
    }

    public static void assertValueEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertEndsWith("value", suffix, valueIn(webElement));
    }

    public static void assertValueNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertNotEndsWith("value", suffix, valueIn(webElement));
    }

    public static void assertValueMatches(String regExp, WebElement webElement) {
        BotUtils.assertMatches("value", regExp, valueIn(webElement));
    }

    public static void assertValueNotMatches(String regExp, WebElement webElement) {
        BotUtils.assertNotMatches("value", regExp, valueIn(webElement));
    }



    /* Value as Number */
    /**
     * Returns the {@link org.openqa.selenium.WebElement} value attribute as a
     * number.
     *
     * <p>If the value attribute in the {@link org.openqa.selenium.WebElement}
     * does not contain a valid number a {@code java.util.NumberFormatException}
     * will be thrown. If the {@link org.openqa.selenium.WebElement} does not
     * exist in the html a {@code org.openqa.selenium.NoSuchElementException}
     * will be thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input value="42"/>
     * valueInAsNumber(input) = 42.0
     *
     * <input value="Some value"/>
     * valueInAsNumber(input) throws java.util.NumberFormatException
     *
     * <input value=""/>
     * valueInAsNumber(input) throws java.util.NumberFormatException
     *
     * no input in html
     * valueInAsNumber(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a value attribute with a number
     * @return the value attribute as a number
     */
    public static double valueInAsNumber(WebElement webElement) {
        return createDouble(valueIn(webElement));
    }

    public static boolean valueIsNumber(WebElement webElement) {
        try {
            valueInAsNumber(webElement);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean valueIsNotNumber(WebElement webElement) {
        return !valueIsNumber(webElement);
    }

    public static boolean valueEquals(double number, WebElement webElement) {
        return BotUtils.equals(number, valueInAsNumber(webElement));
    }

    public static boolean valueNotEquals(double number, WebElement webElement) {
        return BotUtils.notEquals(number, valueInAsNumber(webElement));
    }

    public static boolean valueLessThan(double number, WebElement webElement) {
        return BotUtils.lessThan(number, valueInAsNumber(webElement));
    }

    public static boolean valueLessThanOrEquals(double number, WebElement webElement) {
        return BotUtils.lessThanOrEquals(number, valueInAsNumber(webElement));
    }

    public static boolean valueGreaterThan(double number, WebElement webElement) {
        return BotUtils.greaterThan(number, valueInAsNumber(webElement));
    }

    public static boolean valueGreaterThanOrEquals(double number, WebElement webElement) {
        return BotUtils.greaterThanOrEquals(number, valueInAsNumber(webElement));
    }

    public static void assertValueIsNumber(WebElement webElement) {
        if (valueIsNotNumber(webElement)) {
            throw new AssertionError("value: " + valueInAsNumber(webElement) + " is no number!");
        }
    }

    public static void assertValueIsNotNumber(WebElement webElement) {
        if (valueIsNumber(webElement)) {
            throw new AssertionError("value: " + valueInAsNumber(webElement) + " is number when it shouldn't!");
        }
    }

    public static void assertValueEquals(double number, WebElement webElement) {
        BotUtils.assertEquals("value", number, valueInAsNumber(webElement));
    }

    public static void assertValueNotEquals(double number, WebElement webElement) {
        BotUtils.assertNotEquals("value", number, valueInAsNumber(webElement));
    }

    public static void assertValueLessThan(double number, WebElement webElement) {
        BotUtils.assertLessThan("value", number, valueInAsNumber(webElement));
    }

    public static void assertValueLessThanOrEquals(double number, WebElement webElement) {
        BotUtils.assertLessThanOrEquals("value", number, valueInAsNumber(webElement));
    }

    public static void assertValueGreaterThan(double number, WebElement webElement) {
        BotUtils.assertGreaterThan("value", number, valueInAsNumber(webElement));
    }

    public static void assertValueGreaterThanOrEquals(double number, WebElement webElement) {
        BotUtils.assertGreaterThanOrEquals("value", number, valueInAsNumber(webElement));
    }



    /* Href */
    /**
     * Returns the {@link org.openqa.selenium.WebElement} href attribute.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input href="www.href.com"/>
     * hrefIn(input) = "www.href.com"
     *
     * no input in html
     * hrefIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a href attribute
     * @return the href attribute
     */
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
        return BotUtils.equals(value, hrefIn(webElement));
    }

    public static boolean hrefNotEquals(String value, WebElement webElement) {
        return BotUtils.notEquals(value, hrefIn(webElement));
    }

    public static boolean hrefContains(String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, hrefIn(webElement));
    }

    public static boolean hrefNotContains(String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, hrefIn(webElement));
    }

    public static boolean hrefStartsWith(String prefix, WebElement webElement) {
        return BotUtils.startsWith(prefix, hrefIn(webElement));
    }

    public static boolean hrefNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.notStartsWith(prefix, hrefIn(webElement));
    }

    public static boolean hrefEndsWith(String suffix, WebElement webElement) {
        return BotUtils.endsWith(suffix, hrefIn(webElement));
    }

    public static boolean hrefNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.notEndsWith(suffix, hrefIn(webElement));
    }

    public static boolean hrefMatches(String regExp, WebElement webElement) {
        return BotUtils.matches(regExp, hrefIn(webElement));
    }

    public static boolean hrefNotMatches(String regExp, WebElement webElement) {
        return BotUtils.notMatches(regExp, hrefIn(webElement));
    }

    public static void assertHasHref(WebElement webElement) {
        assertHasAttribute("href", webElement);
    }

    public static void assertHasNotHref(WebElement webElement) {
        assertHasNotAttribute("href", webElement);
    }

    public static void assertHrefEquals(String value, WebElement webElement) {
        BotUtils.assertEquals("href", value, hrefIn(webElement));
    }

    public static void assertHrefNotEquals(String value, WebElement webElement) {
        BotUtils.assertNotEquals("href", value, hrefIn(webElement));
    }

    public static void assertHrefContains(String searchText, WebElement webElement) {
        BotUtils.assertContains("href", searchText, hrefIn(webElement));
    }

    public static void assertHrefNotContains(String searchText, WebElement webElement) {
        BotUtils.assertNotContains("href", searchText, hrefIn(webElement));
    }

    public static void assertHrefStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertStartsWith("href", prefix, hrefIn(webElement));
    }

    public static void assertHrefNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertNotStartsWith("href", prefix, hrefIn(webElement));
    }

    public static void assertHrefEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertEndsWith("href", suffix, hrefIn(webElement));
    }

    public static void assertHrefNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertNotEndsWith("href", suffix, hrefIn(webElement));
    }

    public static void assertHrefMatches(String regExp, WebElement webElement) {
        BotUtils.assertMatches("href", regExp, hrefIn(webElement));
    }

    public static void assertHrefNotMatches(String regExp, WebElement webElement) {
        BotUtils.assertNotMatches("href", regExp, hrefIn(webElement));
    }



    /* Text */
    /**
     * Returns the visible text in a {@link org.openqa.selenium.WebElement}.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <span>Some text</span>
     * textIn(span) = "Some text"
     *
     * <span>
     *     Some text containing <b>html</b>
     * </span>
     * textIn(span) = "Some text containing html"
     *
     * <span style="display: none">Some invisible text</span>
     * textIn(span) = ""
     *
     * no span in html
     * textIn(span) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * the text
     * @return the visible text
     */
    public static String textIn(WebElement webElement) {
        return webElement.getText();
    }

    public static boolean hasText(WebElement webElement) {
        return BotUtils.notEquals("", textIn(webElement));
    }

    public static boolean hasNotText(WebElement webElement) {
        return BotUtils.equals("", textIn(webElement));
    }

    public static boolean textEquals(String text, WebElement webElement) {
        return BotUtils.equals(text, textIn(webElement));
    }

    public static boolean textNotEquals(String text, WebElement webElement) {
        return BotUtils.notEquals(text, textIn(webElement));
    }

    public static boolean textContains(String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, textIn(webElement));
    }

    public static boolean textNotContains(String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, textIn(webElement));
    }

    public static boolean textStartsWith(String prefix, WebElement webElement) {
        return BotUtils.startsWith(prefix, textIn(webElement));
    }

    public static boolean textNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.notStartsWith(prefix, textIn(webElement));
    }

    public static boolean textEndsWith(String suffix, WebElement webElement) {
        return BotUtils.endsWith(suffix, textIn(webElement));
    }

    public static boolean textNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.notEndsWith(suffix, textIn(webElement));
    }

    public static boolean textMatches(String regExp, WebElement webElement) {
        return BotUtils.matches(regExp, textIn(webElement));
    }

    public static boolean textNotMatches(String regExp, WebElement webElement) {
        return BotUtils.notMatches(regExp, textIn(webElement));
    }

    public static void assertHasText(WebElement webElement) {
        if (hasNotText(webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagIn(webElement) + " has no text!");
        }
    }

    public static void assertHasNotText(WebElement webElement) {
        if (hasText(webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagIn(webElement) + " has text \"" + textIn(webElement) + "\" when it shouldn't!");
        }
    }

    public static void assertTextEquals(String text, WebElement webElement) {
        BotUtils.assertEquals("Text", text, textIn(webElement));
    }

    public static void assertTextNotEquals(String text, WebElement webElement) {
        BotUtils.assertNotEquals("Text", text, textIn(webElement));
    }

    public static void assertTextContains(String searchText, WebElement webElement) {
        BotUtils.assertContains("Text", searchText, textIn(webElement));
    }

    public static void assertTextNotContains(String searchText, WebElement webElement) {
        BotUtils.assertNotContains("Text", searchText, textIn(webElement));
    }

    public static void assertTextStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertStartsWith("Text", prefix, textIn(webElement));
    }

    public static void assertTextNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertNotStartsWith("Text", prefix, textIn(webElement));
    }

    public static void assertTextEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertEndsWith("Text", suffix, textIn(webElement));
    }

    public static void assertTextNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertNotEndsWith("Text", suffix, textIn(webElement));
    }

    public static void assertTextMatches(String regExp, WebElement webElement) {
        BotUtils.assertMatches("Text", regExp, textIn(webElement));
    }

    public static void assertTextNotMatches(String regExp, WebElement webElement) {
        BotUtils.assertNotMatches("Text", regExp, textIn(webElement));
    }



    /* Text as Number */
    /**
     * Returns the visible text in a {@link org.openqa.selenium.WebElement} as a
     * number.
     *
     * <p>If the text in the {@link org.openqa.selenium.WebElement} does not
     * contain a valid number a {@code java.util.NumberFormatException} will be
     * thrown. If the {@link org.openqa.selenium.WebElement} does not exist in
     * the html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <span>42</span>
     * textInAsNumber(span) = 42.0
     *
     * <span>
     *     42
     * </span>
     * textInAsNumber(span) = 42.0
     *
     * <span>Some text</span>
     * textInAsNumber(span) throws java.util.NumberFormatException
     *
     * <span style="display: none">42</span>
     * textInAsNumber(span) throws java.util.NumberFormatException
     *
     * no span in html
     * textInAsNumber(span) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a text with a number
     * @return the visible text as a number
     */
     public static double textInAsNumber(WebElement webElement) {
        return createDouble(webElement.getText());
    }

    public static boolean textIsNumber(WebElement webElement) {
        try {
            textInAsNumber(webElement);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean textIsNotNumber(WebElement webElement) {
        return !textIsNumber(webElement);
    }

    public static boolean textEquals(double number, WebElement webElement) {
        return BotUtils.equals(number, textInAsNumber(webElement));
    }

    public static boolean textNotEquals(double number, WebElement webElement) {
        return BotUtils.notEquals(number, textInAsNumber(webElement));
    }

    public static boolean textLessThan(double number, WebElement webElement) {
        return BotUtils.lessThan(number, textInAsNumber(webElement));
    }

    public static boolean textLessThanOrEquals(double number, WebElement webElement) {
        return BotUtils.lessThanOrEquals(number, textInAsNumber(webElement));
    }

    public static boolean textGreaterThan(double number, WebElement webElement) {
        return BotUtils.greaterThan(number, textInAsNumber(webElement));
    }

    public static boolean textGreaterThanOrEquals(double number, WebElement webElement) {
        return BotUtils.greaterThanOrEquals(number, textInAsNumber(webElement));
    }

    public static void assertTextIsNumber(WebElement webElement) {
        if (textIsNotNumber(webElement)) {
            throw new AssertionError("Text: " + textInAsNumber(webElement) + " is no number!");
        }
    }

    public static void assertTextIsNotNumber(WebElement webElement) {
        if (textIsNumber(webElement)) {
            throw new AssertionError("Text: " + textInAsNumber(webElement) + " is number when it shouldn't!");
        }
    }

    public static void assertTextEquals(double number, WebElement webElement) {
        BotUtils.assertEquals("Text", number, textInAsNumber(webElement));
    }

    public static void assertTextNotEquals(double number, WebElement webElement) {
        BotUtils.assertNotEquals("Text", number, textInAsNumber(webElement));
    }

    public static void assertTextLessThan(double number, WebElement webElement) {
        BotUtils.assertLessThan("Text", number, textInAsNumber(webElement));
    }

    public static void assertTextLessThanOrEquals(double number, WebElement webElement) {
        BotUtils.assertLessThanOrEquals("Text", number, textInAsNumber(webElement));
    }

    public static void assertTextGreaterThan(double number, WebElement webElement) {
        BotUtils.assertGreaterThan("Text", number, textInAsNumber(webElement));
    }

    public static void assertTextGreaterThanOrEquals(double number, WebElement webElement) {
        BotUtils.assertGreaterThanOrEquals("Text", number, textInAsNumber(webElement));
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
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " is not selected!");
        }
    }

    public static void assertIsDeselected(WebElement webElement) {
        if (isSelected(webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " is not deselected!");
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
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " is not checked!");
        }
    }

    public static void assertIsUnchecked(WebElement webElement) {
        if (isChecked(webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " is not unchecked!");
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
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " is not enabled!");
        }
    }

    public static void assertIsDisabled(WebElement webElement) {
        if (isEnabled(webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagInWithoutHtml(webElement) + " is not disabled!");
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
            throw new AssertionError("Tag " + BotUtils.tagIn(webElement) + " has no option \"" + text.trim() + "\"!");
        }
    }

    public static void assertHasNotOption(String text, WebElement webElement) {
        if (hasOption(text, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagIn(webElement) + " has option \"" + text.trim() + "\" when it shouldn't!");
        }
    }

    public static void assertOptionIsEnabled(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsDisabled(text, webElement)) {
            throw new AssertionError("Option \"" + text.trim() + "\" is not enabled!");
        }
    }

    public static void assertOptionIsDisabled(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsEnabled(text, webElement)) {
            throw new AssertionError("Option \"" + text.trim() + "\" is not disabled!");
        }
    }

    public static void assertOptionIsSelected(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsDeselected(text, webElement)) {
            throw new AssertionError("Option \"" + text.trim() + "\" is not selected!");
        }
    }

    public static void assertOptionIsDeselected(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsSelected(text, webElement)) {
            throw new AssertionError("Option \"" + text.trim() + "\" is not deselected!");
        }
    }

    public static void assertAllOptionsAreSelected(WebElement webElement) {
        if (!allOptionsAreSelected(webElement)) {
            throw new AssertionError("All options are not selected!");
        }
    }

    public static void assertNoOptionIsSelected(WebElement webElement) {
        if (!noOptionIsSelected(webElement)) {
            throw new AssertionError("All options are not deselected!");
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
            throw new AssertionError("Tag " + BotUtils.tagIn(webElement) + " has no option with value \"" + value.trim() + "\"!");
        }
    }

    public static void assertHasNotOptionWithValue(String value, WebElement webElement) {
        if (hasOptionWithValue(value, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagIn(webElement) + " has option with value \"" + value.trim() + "\" when it shouldn't!");
        }
    }

    public static void assertOptionWithValueIsEnabled(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsDisabled(value, webElement)) {
            throw new AssertionError("Option with value \"" + value.trim() + "\" is not enabled!");
        }
    }

    public static void assertOptionWithValueIsDisabled(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsEnabled(value, webElement)) {
            throw new AssertionError("Option with value \"" + value.trim() + "\" is not disabled!");
        }
    }

    public static void assertOptionWithValueIsSelected(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsDeselected(value, webElement)) {
            throw new AssertionError("Option with value \"" + value.trim() + "\" is not selected!");
        }
    }

    public static void assertOptionWithValueIsDeselected(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsSelected(value, webElement)) {
            throw new AssertionError("Option with value \"" + value.trim() + "\" is not deselected!");
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
            throw new AssertionError("Tag " + BotUtils.tagIn(webElement) + " has no option with index \"" + index + "\"!");
        }
    }

    public static void assertHasNotOptionWithIndex(int index, WebElement webElement) {
        if (hasOptionWithIndex(index, webElement)) {
            throw new AssertionError("Tag " + BotUtils.tagIn(webElement) + " has option with index \"" + index + "\" when it shouldn't!");
        }
    }

    public static void assertOptionWithIndexIsEnabled(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsDisabled(index, webElement)) {
            throw new AssertionError("Option with index \"" + index + "\" is not enabled!");
        }
    }

    public static void assertOptionWithIndexIsDisabled(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsEnabled(index, webElement)) {
            throw new AssertionError("Option with index \"" + index + "\" is not disabled!");
        }
    }

    public static void assertOptionWithIndexIsSelected(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsDeselected(index, webElement)) {
            throw new AssertionError("Option with index \"" + index + "\" is not selected!");
        }
    }

    public static void assertOptionWithIndexIsDeselected(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsSelected(index, webElement)) {
            throw new AssertionError("Option with index \"" + index + "\" is not deselected!");
        }
    }



    /* Hamcrest */
    public static <T extends Object> boolean is(T actual, Matcher<? super T> matcher) {
        try {
            assertThat(actual, matcher);
            return true;
        } catch (AssertionError ae) {
            return false;
        }
    }

    public static <T extends Object> void assertThat(T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat(actual, matcher);
    }

    public static <T extends Object> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat(reason, actual, matcher);
    }
}
