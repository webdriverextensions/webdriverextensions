package org.andidev.webdriverextension.onestepbot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.andidev.webdriverextension.Openable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class Bot implements BotI {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Bot.class);

    private String toString(double number) {
        if (number == (int) number) {
            return String.format("%d", (int) number);
        } else {
            return String.format("%s", number);
        }
    }

    private boolean is(String text1, String text2) {
        return StringUtils.equals(text1, text2);
    }

    private boolean isNot(String text1, String text2) {
        return !StringUtils.equals(text1, text2);
    }

    private boolean isMatching(String regExp, String text) {
        return isMatching(regExp, text);
    }

    private boolean isNotMatching(String regExp, String text) {
        return isNotMatching(regExp, text);
    }

    private boolean isContaining(String searchText, String text) {
        return StringUtils.contains(text, searchText);
    }

    private boolean isNotContaining(String searchText, String text) {
        return !StringUtils.contains(text, searchText);
    }

    private boolean isStartingWith(String prefix, String text) {
        return StringUtils.startsWith(text, prefix);
    }

    private boolean isNotStartingWith(String prefix, String text) {
        return !StringUtils.startsWith(text, prefix);
    }

    private boolean isEndingWith(String suffix, String text) {
        return StringUtils.endsWith(text, suffix);
    }

    private boolean isNotEndingWith(String suffix, String text) {
        return !StringUtils.endsWith(text, suffix);
    }

    private void assertIs(String name, String expected, String actual) {
        Assert.assertEquals(name + " is not " + expected + "!", expected, actual);
    }

    private void assertIsNot(String name, String notExpected, String actual) {
        Assert.assertNotEquals(name + " is " + notExpected + " when it shouldn't!", notExpected, actual);
    }

    private void assertIsMatching(String name, String regExp, String actual) {
        if (actual.matches(regExp)) {
            Assert.fail(name + ": " + actual + " is not matching " + regExp + "!");
        }
    }

    private void assertIsNotMatching(String name, String regExp, String actual) {
        if (!actual.matches(regExp)) {
            Assert.fail(name + ": " + actual + " is matching " + regExp + " when it shouldn't!");
        }
    }

    private void assertContains(String name, String searchText, String actual) {
        if (!StringUtils.contains(actual, searchText)) {
            Assert.fail(name + ": " + actual + " is not containing " + searchText);
        }
    }

    private void assertNotContains(String name, String searchText, String actual) {
        if (StringUtils.contains(actual, searchText)) {
            Assert.fail(name + ": " + actual + " is containing " + searchText + " when it shouldn't!");
        }
    }

    private void assertStartsWidth(String name, String prefix, String actual) {
        if (!StringUtils.startsWith(actual, prefix)) {
            Assert.fail(name + ": " + actual + " is not starting with " + prefix);
        }
    }

    private void assertNotStartsWidth(String name, String prefix, String actual) {
        if (StringUtils.startsWith(actual, prefix)) {
            Assert.fail(name + ": " + actual + " is starting with " + prefix + " when it shouldn't!");
        }
    }

    private void assertEndsWidth(String name, String suffix, String actual) {
        if (!StringUtils.startsWith(actual, suffix)) {
            Assert.fail(name + ": " + actual + " is not ending with " + suffix);
        }
    }

    private void assertNotEndsWidth(String name, String suffix, String actual) {
        if (StringUtils.endsWith(actual, suffix)) {
            Assert.fail(name + ": " + actual + " is ending with " + suffix + " when it shouldn't!");
        }
    }

    private boolean is(Double comparedTo, Double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private boolean isNot(double comparedTo, double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private boolean isSmallerThen(Double comparedTo, Double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private boolean isSmallerThenOrEquals(double comparedTo, double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private boolean isLargerThen(double comparedTo, double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private boolean isLargerThenOrEquals(double comparedTo, double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void assertIs(String name, double comparedTo, double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void assertIsNot(String name, double comparedTo, double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void assertIsSmallerThen(String name, double comparedTo, double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void assertIsSmallerThenOrEquals(String name, double comparedTo, double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void assertIsLargerThen(String name, double comparedTo, double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void assertIsLargerThenOrEquals(String name, double comparedTo, double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public WebDriver getDriver() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /* Click */
    @Override
    public void click(WebElement webElement) {
        webElement.click();
    }

    /* Read */
    @Override
    public String read(WebElement webElement) {
        return webElement.getText();
    }

    @Override
    public Double readNumber(WebElement webElement) {
        try {
            return NumberUtils.createDouble(webElement.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String readUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    @Override
    public String readTagName(WebElement webElement) {
        return webElement.getTagName();
    }

    @Override
    public String readAttribute(String name, WebElement webElement) {
        return webElement.getAttribute(name);
    }

    @Override
    public String readId(WebElement webElement) {
        return readAttribute("id", webElement);
    }

    @Override
    public String readName(WebElement webElement) {
        return readAttribute("name", webElement);
    }

    @Override
    public String readClass(WebElement webElement) {
        return readAttribute("class", webElement);
    }

    @Override
    public String readHref(WebElement webElement) {
        return readAttribute("href", webElement);
    }

    /* Clear */
    @Override
    public void clear(WebElement webElement) {
        webElement.clear();
    }

    /* Type */
    @Override
    public void type(String text, WebElement webElement) {
        if (text == null) {
            return;
        }
        webElement.sendKeys(text);
    }

    @Override
    public void typeNumber(Double number, WebElement webElement) {
        if (number == null) {
            return;
        }
        type(number.toString(), webElement);
    }

    @Override
    public void clearAndType(String text, WebElement webElement) {
        clear(webElement);
        type(text, webElement);
    }

    @Override
    public void clearAndTypeNumber(Double number, WebElement webElement) {
        clear(webElement);
        typeNumber(number, webElement);
    }

    /* Press */
    @Override
    public void pressEnter(WebElement webElement) {
        pressKeys(webElement, Keys.ENTER);
    }

    @Override
    public void pressKeys(WebElement webElement, CharSequence... keys) {
        webElement.sendKeys(keys);
    }

    /* Select */

    /* Select Option */

    /* Check/Uncheck */
    @Override
    public void check(WebElement webElement) {
        if (isUnchecked(webElement)) {
            click(webElement);
        }
    }

    @Override
    public void uncheck(WebElement webElement) {
        if (isChecked(webElement)) {
            click(webElement);
        }
    }

    /* Open */
    @Override
    public void open(String url, WebDriver driver) {
        driver.get(url);
    }

    @Override
    public void open(Openable openable) {
        openable.open();
    }

    /* Count */
    @Override
    public Integer countNumberOfElements(List<? extends WebElement> webElements) {
        return webElements.size();
    }

    /* Wait */
    @Override
    public void delay(double seconds) {
        if (seconds > 0) {
            try {
                TimeUnit.MILLISECONDS.sleep((long) seconds * 1000);
            } catch (InterruptedException ex) {
                // Swallow exception
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delay(long time, TimeUnit unit) {
        if (time > 0) {
            try {
                unit.sleep(time);
            } catch (InterruptedException ex) {
                // Swallow exception
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void waitForElementToDisplay(WebElement webElement, WebDriver driver) {
        waitForElementToDisplay(webElement, 30, driver);
    }

    @Override
    public void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    @Override
    public void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds, long sleepInMillis, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, sleepInMillis);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /* Debug */
    @Override
    public void debug(String str) {
        log.debug(str);
    }

    @Override
    public void debug(WebElement webElement) {
        log.debug("Tag <{}> has class = \"{}\"", readTagName(webElement), readClass(webElement));
    }

    @Override
    public void debug(List<? extends WebElement> webElements) {
        for (WebElement webElement : webElements) {
            debug(webElement);
        }
    }

    @Override
    public void debugText(WebElement webElement) {
        log.debug("Tag <{}> contains text: \"{}\"", readTagName(webElement), read(webElement));
    }

    @Override
    public void debugText(List<? extends WebElement> webElements) {
        for (WebElement webElement : webElements) {
            debugText(webElement);
        }
    }

    @Override
    public void debugNumberOfElements(List<? extends WebElement> webElements) {
        log.debug("List contains {} number of tags");
    }

    /* Tag Name */
    @Override
    public boolean isTagName(String value, WebElement webElement) {
        return is(value, readTagName(webElement));
    }

    @Override
    public boolean isTagNameNot(String value, WebElement webElement) {
        return isNot(value, readTagName(webElement));
    }

    @Override
    public void assertTagName(String value, WebElement webElement) {
        assertIs("Tag name", value, readTagName(webElement));
    }

    @Override
    public void assertTagNameNot(String value, WebElement webElement) {
        assertIsNot("Tag name", value, readTagName(webElement));
    }

    /* Attribute */
    @Override
    public boolean isAttribute(String name, String value, WebElement webElement) {
        return is(value, readAttribute(name, webElement));
    }

    @Override
    public boolean isAttributeNot(String name, String value, WebElement webElement) {
        return isNot(value, readAttribute(name, webElement));
    }

    @Override
    public boolean isAttributeContaining(String name, String searchText, WebElement webElement) {
        return isContaining(searchText, readAttribute(name, webElement));
    }

    @Override
    public boolean isAttributeNotContaining(String name, String searchText, WebElement webElement) {
        return isNotContaining(searchText, readAttribute(name, webElement));
    }

    @Override
    public boolean isAttributeStartingWith(String name, String prefix, WebElement webElement) {
        return isStartingWith(prefix, readAttribute(name, webElement));
    }

    @Override
    public boolean isAttributeNotStartingWith(String name, String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readAttribute(name, webElement));
    }

    @Override
    public boolean isAttributeEndingWith(String name, String suffix, WebElement webElement) {
        return isEndingWith(suffix, readAttribute(name, webElement));
    }

    @Override
    public boolean isAttributeNotEndingWith(String name, String suffix, WebElement webElement) {
        return isNotEndingWith(suffix, readAttribute(name, webElement));
    }

    @Override
    public void assertAttribute(String name, String value, WebElement webElement) {
        assertIs(name, value, readAttribute(name, webElement));
    }

    @Override
    public void assertAttributeNot(String name, String value, WebElement webElement) {
        assertIsNot(name, value, readAttribute(name, webElement));
    }

    @Override
    public void assertAttributeContains(String name, String searchText, WebElement webElement) {
        assertContains(name, searchText, readAttribute(name, webElement));
    }

    @Override
    public void assertAttributeNotContains(String name, String searchText, WebElement webElement) {
        assertNotContains(name, searchText, readAttribute(name, webElement));
    }

    @Override
    public void assertAttributeStartsWith(String name, String prefix, WebElement webElement) {
        assertStartsWidth(name, prefix, readAttribute(name, webElement));
    }

    @Override
    public void assertAttributeNotStartsWith(String name, String prefix, WebElement webElement) {
        assertNotStartsWidth(name, prefix, readAttribute(name, webElement));
    }

    @Override
    public void assertAttributeEndsWith(String name, String suffix, WebElement webElement) {
        assertEndsWidth(name, suffix, readAttribute(name, webElement));
    }

    @Override
    public void assertAttributeNotEndsWith(String name, String suffix, WebElement webElement) {
        assertNotEndsWidth(name, suffix, readAttribute(name, webElement));
    }

    /* Id */
    @Override
    public boolean isId(String value, WebElement webElement) {
        return is(value, readId(webElement));
    }

    @Override
    public boolean isIdNot(String value, WebElement webElement) {
        return isNot(value, readId(webElement));
    }

    @Override
    public boolean isIdContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readId(webElement));
    }

    @Override
    public boolean isIdNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readId(webElement));
    }

    @Override
    public boolean isIdStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, readId(webElement));
    }

    @Override
    public boolean isIdNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readId(webElement));
    }

    @Override
    public boolean isIdEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readId(webElement));
    }

    @Override
    public boolean isIdNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readId(webElement));
    }

    @Override
    public void assertId(String value, WebElement webElement) {
        assertIs("Id", value, readId(webElement));
    }

    @Override
    public void assertIdNot(String value, WebElement webElement) {
        assertIsNot("Id", value, readId(webElement));
    }

    @Override
    public void assertIdContains(String searchText, WebElement webElement) {
        assertContains("Id", searchText, readId(webElement));
    }

    @Override
    public void assertIdNotContains(String searchText, WebElement webElement) {
        assertNotContains("Id", searchText, readId(webElement));
    }

    @Override
    public void assertIdStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("Id", prefix, readId(webElement));
    }

    @Override
    public void assertIdNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("Id", prefix, readId(webElement));
    }

    @Override
    public void assertIdEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("Id", suffix, readId(webElement));
    }

    @Override
    public void assertIdNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("Id", suffix, readId(webElement));
    }

    /* Name */
    @Override
    public boolean isName(String value, WebElement webElement) {
        return is(value, readName(webElement));
    }

    @Override
    public boolean isNameNot(String value, WebElement webElement) {
        return isNot(value, readName(webElement));
    }

    @Override
    public boolean isNameContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readName(webElement));
    }

    @Override
    public boolean isNameNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readName(webElement));
    }

    @Override
    public boolean isNameStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, readName(webElement));
    }

    @Override
    public boolean isNameNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readName(webElement));
    }

    @Override
    public boolean isNameEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readName(webElement));
    }

    @Override
    public boolean isNameNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readName(webElement));
    }

    @Override
    public void assertName(String value, WebElement webElement) {
        assertIs("Name", value, readName(webElement));
    }

    @Override
    public void assertNameNot(String value, WebElement webElement) {
        assertIsNot("Name", value, readName(webElement));
    }

    @Override
    public void assertNameContains(String searchText, WebElement webElement) {
        assertContains("Name", searchText, readName(webElement));
    }

    @Override
    public void assertNameNotContains(String searchText, WebElement webElement) {
        assertNotContains("Name", searchText, readName(webElement));
    }

    @Override
    public void assertNameStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("Name", prefix, readName(webElement));
    }

    @Override
    public void assertNameNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("Name", prefix, readName(webElement));
    }

    @Override
    public void assertNameEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("Name", suffix, readName(webElement));
    }

    @Override
    public void assertNameNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("Name", suffix, readName(webElement));
    }

    /* Class */
    @Override
    public boolean hasClass(String className, WebElement webElement) {
        return readClass(webElement).matches("(\\\"|\\s)" + className.trim() + "(\\\"|\\s)");
    }

    @Override
    public boolean hasNotClass(String className, WebElement webElement) {
        return !hasClass(className, webElement);
    }

    @Override
    public boolean isClass(String value, WebElement webElement) {
        return is(value, readClass(webElement));
    }

    @Override
    public boolean isClassNot(String value, WebElement webElement) {
        return isNot(value, readClass(webElement));
    }

    @Override
    public boolean isClassContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readClass(webElement));
    }

    @Override
    public boolean isClassNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readClass(webElement));
    }

    @Override
    public boolean isClassStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, readClass(webElement));
    }

    @Override
    public boolean isClassNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readClass(webElement));
    }

    @Override
    public boolean isClassEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readClass(webElement));
    }

    @Override
    public boolean isClassNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readClass(webElement));
    }

    @Override
    public void assertHasClass(String className, WebElement webElement) {
        if (hasNotClass(className, webElement)) {
            Assert.fail("Class: " + readClass(webElement) + " is not containing " + className.trim() + "!");
        }
    }

    @Override
    public void assertHasNotClass(String className, WebElement webElement) {
        if (hasClass(className, webElement)) {
            Assert.fail("Class: " + readClass(webElement) + " is containing " + className.trim() + " when it shouldn't!");
        }
    }

    @Override
    public void assertClass(String value, WebElement webElement) {
        assertIs("Class", value, readClass(webElement));
    }

    @Override
    public void assertClassNot(String value, WebElement webElement) {
        assertIsNot("Class", value, readClass(webElement));
    }

    @Override
    public void assertClassContains(String searchText, WebElement webElement) {
        assertContains("Class", searchText, readClass(webElement));
    }

    @Override
    public void assertClassNotContains(String searchText, WebElement webElement) {
        assertNotContains("Class", searchText, readClass(webElement));
    }

    @Override
    public void assertClassStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("Class", prefix, readClass(webElement));
    }

    @Override
    public void assertClassNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("Class", prefix, readClass(webElement));
    }

    @Override
    public void assertClassEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("Class", suffix, readClass(webElement));
    }

    @Override
    public void assertClassNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("Class", suffix, readClass(webElement));
    }

    /* Href */
    @Override
    public boolean isHref(String value, WebElement webElement) {
        return is(value, readHref(webElement));
    }

    @Override
    public boolean isHrefNot(String value, WebElement webElement) {
        return isNot(value, readHref(webElement));
    }

    @Override
    public boolean isHrefContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readHref(webElement));
    }

    @Override
    public boolean isHrefNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readHref(webElement));
    }

    @Override
    public boolean isHrefStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, readHref(webElement));
    }

    @Override
    public boolean isHrefNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readHref(webElement));
    }

    @Override
    public boolean isHrefEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readHref(webElement));
    }

    @Override
    public boolean isHrefNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readHref(webElement));
    }

    @Override
    public void assertHref(String value, WebElement webElement) {
        assertIs("Href", value, readHref(webElement));
    }

    @Override
    public void assertHrefNot(String value, WebElement webElement) {
        assertIsNot("Href", value, readHref(webElement));
    }

    @Override
    public void assertHrefContains(String searchText, WebElement webElement) {
        assertContains("Href", searchText, readHref(webElement));
    }

    @Override
    public void assertHrefNotContains(String searchText, WebElement webElement) {
        assertNotContains("Href", searchText, readHref(webElement));
    }

    @Override
    public void assertHrefStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("Href", prefix, readHref(webElement));
    }

    @Override
    public void assertHrefNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("Href", prefix, readHref(webElement));
    }

    @Override
    public void assertHrefEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("Href", suffix, readHref(webElement));
    }

    @Override
    public void assertHrefNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("Href", suffix, readHref(webElement));
    }

    /* Text */
    @Override
    public boolean isText(String text, WebElement webElement) {
        return is(text, read(webElement));
    }

    @Override
    public boolean isTextNot(String text, WebElement webElement) {
        return isNot(text, read(webElement));
    }

    @Override
    public boolean isTextContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, read(webElement));
    }

    @Override
    public boolean isTextNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, read(webElement));
    }

    @Override
    public boolean isTextStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, read(webElement));
    }

    @Override
    public boolean isTextNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, read(webElement));
    }

    @Override
    public boolean isTextEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, read(webElement));
    }

    @Override
    public boolean isTextNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, read(webElement));
    }

    @Override
    public void assertText(String text, WebElement webElement) {
        assertIs("Text", text, read(webElement));
    }

    @Override
    public void assertTextNot(String text, WebElement webElement) {
        assertIsNot("Text", text, read(webElement));
    }

    @Override
    public void assertTextContains(String searchText, WebElement webElement) {
        assertContains("Text", searchText, read(webElement));
    }

    @Override
    public void assertTextNotContains(String searchText, WebElement webElement) {
        assertNotContains("Text", searchText, read(webElement));
    }

    @Override
    public void assertTextStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("Text", prefix, read(webElement));
    }

    @Override
    public void assertTextNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("Text", prefix, read(webElement));
    }

    @Override
    public void assertTextEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("Text", suffix, read(webElement));
    }

    @Override
    public void assertTextNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("Text", suffix, read(webElement));
    }

    /* Number */
    @Override
    public boolean isNumber(Double number, WebElement webElement) {
        return is(number, readNumber(webElement));
    }

    @Override
    public boolean isNumberNot(Double number, WebElement webElement) {
        return isNot(number, readNumber(webElement));
    }

    @Override
    public boolean isNumberSmallerThen(Double number, WebElement webElement) {
        return isSmallerThen(number, readNumber(webElement));
    }

    @Override
    public boolean isNumberSmallerThenOrEquals(Double number, WebElement webElement) {
        return isSmallerThenOrEquals(number, readNumber(webElement));
    }

    @Override
    public boolean isNumberLargerThen(Double number, WebElement webElement) {
        return isLargerThen(number, readNumber(webElement));
    }

    @Override
    public boolean isNumberLargerThenOrEquals(Double number, WebElement webElement) {
        return isLargerThenOrEquals(number, readNumber(webElement));
    }

    @Override
    public void assertNumber(Double number, WebElement webElement) {
        assertIs("Number", number, readNumber(webElement));
    }

    @Override
    public void assertNumberNot(Double number, WebElement webElement) {
        assertIsNot("Number", number, readNumber(webElement));
    }

    @Override
    public void assertNumberSmallerThen(Double number, WebElement webElement) {
        assertIsSmallerThen("Number", number, readNumber(webElement));
    }

    @Override
    public void assertNumberSmallerThenOrEquals(Double number, WebElement webElement) {
        assertIsSmallerThenOrEquals("Number", number, readNumber(webElement));
    }

    @Override
    public void assertNumberLargerThen(Double number, WebElement webElement) {
        assertIsLargerThen("Number", number, readNumber(webElement));
    }

    @Override
    public void assertNumberLargerThenOrEquals(Double number, WebElement webElement) {
        assertIsLargerThenOrEquals("Number", number, readNumber(webElement));
    }

    /* Browser Url */
    @Override
    public boolean isUrl(String url, WebDriver driver) {
        return is(url, readUrl(driver));
    }

    @Override
    public boolean isUrlNot(String url, WebDriver driver) {
        return isNot(url, readUrl(driver));
    }

    @Override
    public boolean isUrlMatching(String regExp, WebDriver driver) {
        return isMatching(regExp, readUrl(driver));
    }

    @Override
    public boolean isUrlNotMatching(String regExp, WebDriver driver) {
        return isNotMatching(regExp, readUrl(driver));
    }

    @Override
    public boolean isUrlMatching(Openable openable, WebDriver driver) {
        return isMatching(openable.getUrl(), readUrl(driver));
    }

    @Override
    public boolean isUrlNotMatching(Openable openable, WebDriver driver) {
        return isNotMatching(openable.getUrl(), readUrl(driver));
    }

    @Override
    public boolean isUrlContaining(String searchText, WebDriver driver) {
        return isContaining(searchText, readUrl(driver));
    }

    @Override
    public boolean isUrlNotContaining(String searchText, WebDriver driver) {
        return isNotContaining(searchText, readUrl(driver));
    }

    @Override
    public boolean isUrlStartingWidth(String prefix, WebDriver driver) {
        return isStartingWith(prefix, readUrl(driver));
    }

    @Override
    public boolean isUrlNotStartingWidth(String prefix, WebDriver driver) {
        return isNotStartingWith(prefix, readUrl(driver));
    }

    @Override
    public boolean isUrlEndingWidth(String suffix, WebDriver driver) {
        return isEndingWith(suffix, readUrl(driver));
    }

    @Override
    public boolean isUrlNotEndingWidth(String suffix, WebDriver driver) {
        return isNotEndingWith(suffix, readUrl(driver));
    }

    @Override
    public void assertUrl(String url, WebDriver driver) {
        assertIs("Url", url, readUrl(driver));
    }

    @Override
    public void assertUrlNot(String url, WebDriver driver) {
        assertIsNot("Url", url, readUrl(driver));
    }

    @Override
    public void assertUrlMatching(String regExp, WebDriver driver) {
        assertIsMatching("Url", regExp, readUrl(driver));
    }

    @Override
    public void assertUrlNotMatching(String regExp, WebDriver driver) {
        assertIsNotMatching("Url", regExp, readUrl(driver));
    }

    @Override
    public void assertUrlMatching(Openable openable, WebDriver driver) {
        assertIsMatching("Url", openable.getUrl(), readUrl(driver));
    }

    @Override
    public void assertUrlNotMatching(Openable openable, WebDriver driver) {
        assertIsNotMatching("Url", openable.getUrl(), readUrl(driver));
    }

    @Override
    public void assertUrlContains(String searchText, WebDriver driver) {
        assertContains("Url", searchText, readUrl(driver));
    }

    @Override
    public void assertUrlNotContains(String searchText, WebDriver driver) {
        assertNotContains("Url", searchText, readUrl(driver));
    }

    @Override
    public void assertUrlStartsWidth(String prefix, WebDriver driver) {
        assertStartsWidth("Url", prefix, readUrl(driver));
    }

    @Override
    public void assertUrlNotStartsWidth(String prefix, WebDriver driver) {
        assertNotStartsWidth("Url", prefix, readUrl(driver));
    }

    @Override
    public void assertUrlEndsWidth(String suffix, WebDriver driver) {
        assertEndsWidth("Url", suffix, readUrl(driver));
    }

    @Override
    public void assertUrlNotEndsWidth(String suffix, WebDriver driver) {
        assertNotEndsWidth("Url", suffix, readUrl(driver));
    }

    /* Open */
    @Override
    public boolean isOpen(Openable openable) {
        return openable.isOpen();
    }

    @Override
    public boolean isNotOpen(Openable openable) {
        return openable.isNotOpen();
    }

    @Override
    public void assertIsOpen(Openable openable) {
        openable.assertIsOpen();
    }

    @Override
    public void assertIsNotOpen(Openable openable) {
        openable.assertIsNotOpen();
    }

    /* Selected */
    @Override
    public boolean isSelected(WebElement webElement) {
        return webElement.isSelected();
    }

    @Override
    public boolean isNotSelected(WebElement webElement) {
        return !isSelected(webElement);
    }

    @Override
    public void assertIsSelected(WebElement webElement) {
        if (isNotSelected(webElement)) {
            Assert.fail(describeTag(webElement) + " is not selected!");
        }
    }

    @Override
    public void assertIsNotSelected(WebElement webElement) {
        if (isSelected(webElement)) {
            Assert.fail(describeTag(webElement) + " is selected when it shouldn't!");
        }
    }

    /* Checked/Unchecked */
    @Override
    public boolean isChecked(WebElement webElement) {
        return webElement.isSelected();
    }

    @Override
    public boolean isUnchecked(WebElement webElement) {
        return !isChecked(webElement);
    }

    @Override
    public void assertIsChecked(WebElement webElement) {
        if (isUnchecked(webElement)) {
            Assert.fail(describeTag(webElement) + " is not checked!");
        }
    }

    @Override
    public void assertIsUnchecked(WebElement webElement) {
        if (isChecked(webElement)) {
            Assert.fail(describeTag(webElement) + " is checked when it shouldn't!");
        }
    }

    /* Enabled/Disabled */
    @Override
    public boolean isEnabled(WebElement webElement) {
        return webElement.isEnabled();
    }

    @Override
    public boolean isDisabled(WebElement webElement) {
        return !isEnabled(webElement);
    }

    @Override
    public void assertIsEnabled(WebElement webElement) {
        if (isUnchecked(webElement)) {
            Assert.fail(describeTag(webElement) + " is not enabled!");
        }
    }

    @Override
    public void assertIsDisabled(WebElement webElement) {
        if (isChecked(webElement)) {
            Assert.fail(describeTag(webElement) + " is enabled when it shouldn't!");
        }
    }

    /* Select Option */

    /* Select Option With Value */

    /* Select Option With Index */

    /* Display */
    @Override
    public boolean isDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean isNotDisplayed(WebElement webElement) {
        return isDisplayed(webElement);
    }

    @Override
    public void assertIsDisplayed(WebElement webElement) {
        if (isNotDisplayed(webElement)) {
            Assert.fail("WebElement is not displayed!");
        }
    }

    @Override
    public void assertIsNotDisplayed(WebElement webElement) {
        if (isDisplayed(webElement)) {
            Assert.fail("WebElement is displayed when it shouldn't!");
        }
    }

    /* Number of Elements */
    @Override
    public boolean isNumberOfElements(int number, List<? extends WebElement> webElements) {
        return is((double) number, (double) webElements.size());
    }

    @Override
    public boolean isNumberOfElementsNot(int number, List<? extends WebElement> webElements) {
        return isNot((double) number, (double) webElements.size());
    }

    @Override
    public boolean isNumberOfElementsSmallerThen(int number, List<? extends WebElement> webElements) {
        return isSmallerThen((double) number, (double) webElements.size());
    }

    @Override
    public boolean isNumberOfElementsSmallerThenOrEquals(int number, List<? extends WebElement> webElements) {
        return isSmallerThenOrEquals((double) number, (double) webElements.size());
    }

    @Override
    public boolean isNumberOfElementsLargerThen(int number, List<? extends WebElement> webElements) {
        return isLargerThen((double) number, (double) webElements.size());
    }

    @Override
    public boolean isNumberOfElementsLargerThenOrEquals(int number, List<? extends WebElement> webElements) {
        return isLargerThenOrEquals((double) number, (double) webElements.size());
    }

    @Override
    public void assertNumberOfElements(int number, List<? extends WebElement> webElements) {
        assertIs("Number of elements", (double) number, (double) webElements.size());
    }

    @Override
    public void assertNumberOfElementsNot(int number, List<? extends WebElement> webElements) {
        assertIsNot("Number of elements", (double) number, (double) webElements.size());
    }

    @Override
    public void assertNumberOfElementsSmallerThen(int number, List<? extends WebElement> webElements) {
        assertIsSmallerThen("Number of elements", (double) number, (double) webElements.size());
    }

    @Override
    public void assertNumberOfElementsSmallerThenOrEquals(int number, List<? extends WebElement> webElements) {
        assertIsSmallerThenOrEquals("Number of elements", (double) number, (double) webElements.size());
    }

    @Override
    public void assertNumberOfElementsLargerThen(int number, List<? extends WebElement> webElements) {
        assertIsLargerThen("Number of elements", (double) number, (double) webElements.size());
    }

    @Override
    public void assertNumberOfElementsLargerThenOrEquals(int number, List<? extends WebElement> webElements) {
        assertIsLargerThenOrEquals("Number of elements", (double) number, (double) webElements.size());
    }


//
//    @Override
//    public void selectOption(String text, WebElement webElement) {
//        new Select(webElement).selectByVisibleText(text);
//    }
//
//    @Override
//    public boolean hasOptionSelected(String text, WebElement webElement) {
//
//        if (isTagName("select", webElement)) {
//            List<org.openqa.selenium.WebElement> selectedOptions = new Select(webElement).getAllSelectedOptions();
//            if (selectedOptions == null || selectedOptions.isEmpty()) {
//                return false;
//            }
//            for (WebElement selectedOption : selectedOptions) {
//                if (isText(text, selectedOption)) {
//                    return true;
//                }
//            }
//            return false;
//        }
//        throw new IllegalArgumentException();
//    }
//
//    @Override
//    public boolean hasOptionNotSelected(String text, WebElement webElement) {
//        return !isSelected(text, webElement);
//    }
//    @Override
//    public void assertHasOptionSelected(String text, WebElement webElement) {
//        if (isNotSelected(text, webElement)) {
//            Assert.fail(readTagName(webElement) + " has no option \"" + text.trim() + "\" selected!");
//        }
//    }
//
//    @Override
//    public void assertHasOptionNotSelected(String text, WebElement webElement) {
//        if (isSelected(text, webElement)) {
//            Assert.fail(readTagName(webElement) + " has option \"" + text.trim() + "\" selected when it shouldn't!");
//        }
//    }

    private String describeTag(WebElement webElement) {
        if (webElement == null) {
            return "WebElement";
        }
        return "Tag <" + readTagName(webElement)
                + describeId(webElement)
                + describeName(webElement)
                + describeClass(webElement)
                + describeValue(webElement)
                + describeAttribute("disabled", webElement)
                + describeAttribute("selected", webElement)
                + describeAttribute("checked", webElement)
                + ">";
    }

    private String describeAttribute(String attributeName, WebElement webElement) {
        return hasAttribute(attributeName, webElement) ? attributeName + " = '" + readAttribute(attributeName, webElement) + "' " : "";
    }

    private String describeId(WebElement webElement) {
        return hasId(webElement) ? "id = '" + readId(webElement) + "' " : "";
    }

    private String describeName(WebElement webElement) {
        return hasName(webElement) ? "name = '" + readName(webElement) + "' " : "";
    }

    private String describeClass(WebElement webElement) {
        return hasClass(webElement) ? "class = '" + readClass(webElement) + "' " : "";
    }

    private String describeValue(WebElement webElement) {
        return hasValue(webElement) ? "value = '" + readValue(webElement) + "' " : "";
    }

}
