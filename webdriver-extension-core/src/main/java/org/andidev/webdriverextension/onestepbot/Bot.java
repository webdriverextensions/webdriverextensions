package org.andidev.webdriverextension.onestepbot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.andidev.webdriverextension.Openable;
import org.apache.commons.lang3.ArrayUtils;
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

public abstract class Bot implements BotI {

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
        return number < comparedTo;
    }

    private boolean isSmallerThenOrEquals(double comparedTo, double number) {
        return number <= comparedTo;
    }

    private boolean isLargerThen(double comparedTo, double number) {
        return number > comparedTo;
    }

    private boolean isLargerThenOrEquals(double comparedTo, double number) {
        return number >= comparedTo;
    }

    private void assertIs(String name, double comparedTo, double number) {
        if (isNot(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not " + comparedTo + " !");
        }
    }

    private void assertIsNot(String name, double comparedTo, double number) {
        if (is(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is " + comparedTo + " when it shouldn't!");
        }
    }

    private void assertIsSmallerThen(String name, double comparedTo, double number) {
        if (isLargerThenOrEquals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not smaller then " + comparedTo + " !");
        }
    }

    private void assertIsSmallerThenOrEquals(String name, double comparedTo, double number) {
        if (isLargerThen(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not smaller then or equal " + comparedTo + " !");
        }
    }

    private void assertIsLargerThen(String name, double comparedTo, double number) {
        if (isSmallerThenOrEquals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not larger then " + comparedTo + " !");
        }
    }

    private void assertIsLargerThenOrEquals(String name, double comparedTo, double number) {
        if (isSmallerThen(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not larger then or equal " + comparedTo + " !");
        }
    }

    private String describeTag(WebElement webElement) {
        return describeTag(webElement, null);
    }

    private String describeTag(WebElement webElement, String extraAttribute) {
        if (webElement == null) {
            return "WebElement";
        }
        boolean printExtraAttribute = extraAttribute != null && !ArrayUtils.contains(new String[]{"id", "name", "name", "class", "value", "disabled", "selected", "checked"}, extraAttribute);
        return "Tag <" + readTagName(webElement)
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
    public List<String> readOptions(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        List<String> optionTexts = new ArrayList<String>();
        for (WebElement option : options) {
            optionTexts.add(read(option));
        }
        return optionTexts;
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
    public String readValue(WebElement webElement) {
        return readAttribute("value", webElement);
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
    public int count(List<? extends WebElement> webElements) {
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
    public boolean hasAttribute(String name, WebElement webElement) {
        return webElement.getAttribute(name) != null;
    }

    @Override
    public boolean hasNotAttribute(String name, WebElement webElement) {
        return !hasAttribute(name, webElement);
    }

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
    public void assertHasAttribute(String name, WebElement webElement) {
        if (hasNotAttribute(name, webElement)) {
            Assert.fail(describeTag(webElement, name) + " does not have the " + name + " attribute!");
        }
    }

    @Override
    public void assertHasNotAttribute(String name, WebElement webElement) {
        if (hasAttribute(name, webElement)) {
            Assert.fail(describeTag(webElement, name) + " has the " + name + " attribute when it shouldn't!");
        }
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
    public boolean hasId(WebElement webElement) {
        return hasAttribute("id", webElement);
    }

    @Override
    public boolean hasNotId(WebElement webElement) {
        return hasNotAttribute("id", webElement);
    }

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
    public void assertHasId(WebElement webElement) {
        assertHasAttribute("id", webElement);
    }

    @Override
    public void assertHasNotId(WebElement webElement) {
        assertHasNotAttribute("id", webElement);
    }

    @Override
    public void assertId(String value, WebElement webElement) {
        assertIs("id", value, readId(webElement));
    }

    @Override
    public void assertIdNot(String value, WebElement webElement) {
        assertIsNot("id", value, readId(webElement));
    }

    @Override
    public void assertIdContains(String searchText, WebElement webElement) {
        assertContains("id", searchText, readId(webElement));
    }

    @Override
    public void assertIdNotContains(String searchText, WebElement webElement) {
        assertNotContains("id", searchText, readId(webElement));
    }

    @Override
    public void assertIdStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("id", prefix, readId(webElement));
    }

    @Override
    public void assertIdNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("id", prefix, readId(webElement));
    }

    @Override
    public void assertIdEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("id", suffix, readId(webElement));
    }

    @Override
    public void assertIdNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("id", suffix, readId(webElement));
    }

    /* Name */
    @Override
    public boolean hasName(WebElement webElement) {
        return hasAttribute("name", webElement);
    }

    @Override
    public boolean hasNotName(WebElement webElement) {
        return hasNotAttribute("name", webElement);
    }

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
    public void assertHasName(WebElement webElement) {
        assertHasAttribute("name", webElement);
    }

    @Override
    public void assertHasNotName(WebElement webElement) {
        assertHasNotAttribute("name", webElement);
    }

    @Override
    public void assertName(String value, WebElement webElement) {
        assertIs("name", value, readName(webElement));
    }

    @Override
    public void assertNameNot(String value, WebElement webElement) {
        assertIsNot("name", value, readName(webElement));
    }

    @Override
    public void assertNameContains(String searchText, WebElement webElement) {
        assertContains("name", searchText, readName(webElement));
    }

    @Override
    public void assertNameNotContains(String searchText, WebElement webElement) {
        assertNotContains("name", searchText, readName(webElement));
    }

    @Override
    public void assertNameStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("name", prefix, readName(webElement));
    }

    @Override
    public void assertNameNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("name", prefix, readName(webElement));
    }

    @Override
    public void assertNameEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("name", suffix, readName(webElement));
    }

    @Override
    public void assertNameNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("name", suffix, readName(webElement));
    }

    /* Class */
    @Override
    public boolean hasClass(WebElement webElement) {
        return hasAttribute("class", webElement);
    }

    @Override
    public boolean hasClass(String className, WebElement webElement) {
        return readClass(webElement).matches("(\\\"|\\s)" + className.trim() + "(\\\"|\\s)");
    }

    @Override
    public boolean hasNotClass(WebElement webElement) {
        return hasNotAttribute("class", webElement);
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
    public void assertHasClass(WebElement webElement) {
        assertHasAttribute("class", webElement);
    }

    @Override
    public void assertHasClass(String className, WebElement webElement) {
        if (hasNotClass(className, webElement)) {
            Assert.fail("class: " + readClass(webElement) + " is not containing " + className.trim() + "!");
        }
    }

    @Override
    public void assertHasNotClass(WebElement webElement) {
        assertHasNotAttribute("class", webElement);
    }

    @Override
    public void assertHasNotClass(String className, WebElement webElement) {
        if (hasClass(className, webElement)) {
            Assert.fail("class: " + readClass(webElement) + " is containing " + className.trim() + " when it shouldn't!");
        }
    }

    @Override
    public void assertClass(String value, WebElement webElement) {
        assertIs("class", value, readClass(webElement));
    }

    @Override
    public void assertClassNot(String value, WebElement webElement) {
        assertIsNot("class", value, readClass(webElement));
    }

    @Override
    public void assertClassContains(String searchText, WebElement webElement) {
        assertContains("class", searchText, readClass(webElement));
    }

    @Override
    public void assertClassNotContains(String searchText, WebElement webElement) {
        assertNotContains("class", searchText, readClass(webElement));
    }

    @Override
    public void assertClassStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("class", prefix, readClass(webElement));
    }

    @Override
    public void assertClassNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("class", prefix, readClass(webElement));
    }

    @Override
    public void assertClassEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("class", suffix, readClass(webElement));
    }

    @Override
    public void assertClassNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("class", suffix, readClass(webElement));
    }

    /* Value */
    @Override
    public boolean hasValue(WebElement webElement) {
        return hasAttribute("value", webElement);
    }

    @Override
    public boolean hasNotValue(WebElement webElement) {
        return hasNotAttribute("value", webElement);
    }

    @Override
    public boolean isValue(String value, WebElement webElement) {
        return is(value, readValue(webElement));
    }

    @Override
    public boolean isValueNot(String value, WebElement webElement) {
        return isNot(value, readValue(webElement));
    }

    @Override
    public boolean isValueContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readValue(webElement));
    }

    @Override
    public boolean isValueNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readValue(webElement));
    }

    @Override
    public boolean isValueStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, readValue(webElement));
    }

    @Override
    public boolean isValueNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readValue(webElement));
    }

    @Override
    public boolean isValueEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readValue(webElement));
    }

    @Override
    public boolean isValueNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readValue(webElement));
    }

    @Override
    public void assertHasValue(WebElement webElement) {
        assertHasAttribute("value", webElement);
    }

    @Override
    public void assertHasNotValue(WebElement webElement) {
        assertHasNotAttribute("value", webElement);
    }

    @Override
    public void assertValue(String value, WebElement webElement) {
        assertIs("value", value, readValue(webElement));
    }

    @Override
    public void assertValueNot(String value, WebElement webElement) {
        assertIsNot("value", value, readValue(webElement));
    }

    @Override
    public void assertValueContains(String searchText, WebElement webElement) {
        assertContains("value", searchText, readValue(webElement));
    }

    @Override
    public void assertValueNotContains(String searchText, WebElement webElement) {
        assertNotContains("value", searchText, readValue(webElement));
    }

    @Override
    public void assertValueStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("value", prefix, readValue(webElement));
    }

    @Override
    public void assertValueNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("value", prefix, readValue(webElement));
    }

    @Override
    public void assertValueEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("value", suffix, readValue(webElement));
    }

    @Override
    public void assertValueNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("value", suffix, readValue(webElement));
    }


    /* Href */
    @Override
    public boolean hasHref(WebElement webElement) {
        return hasAttribute("href", webElement);
    }

    @Override
    public boolean hasNotHref(WebElement webElement) {
        return hasNotAttribute("href", webElement);
    }

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
    public void assertHasHref(WebElement webElement) {
        assertHasAttribute("href", webElement);
    }

    @Override
    public void assertHasNotHref(WebElement webElement) {
        assertHasNotAttribute("href", webElement);
    }

    @Override
    public void assertHref(String value, WebElement webElement) {
        assertIs("href", value, readHref(webElement));
    }

    @Override
    public void assertHrefNot(String value, WebElement webElement) {
        assertIsNot("href", value, readHref(webElement));
    }

    @Override
    public void assertHrefContains(String searchText, WebElement webElement) {
        assertContains("href", searchText, readHref(webElement));
    }

    @Override
    public void assertHrefNotContains(String searchText, WebElement webElement) {
        assertNotContains("href", searchText, readHref(webElement));
    }

    @Override
    public void assertHrefStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("href", prefix, readHref(webElement));
    }

    @Override
    public void assertHrefNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("href", prefix, readHref(webElement));
    }

    @Override
    public void assertHrefEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("href", suffix, readHref(webElement));
    }

    @Override
    public void assertHrefNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("href", suffix, readHref(webElement));
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
    public boolean isDeselected(WebElement webElement) {
        return !isSelected(webElement);
    }

    @Override
    public void assertIsSelected(WebElement webElement) {
        if (isDeselected(webElement)) {
            Assert.fail(describeTag(webElement) + " is not selected!");
        }
    }

    @Override
    public void assertIsDeselected(WebElement webElement) {
        if (isSelected(webElement)) {
            Assert.fail(describeTag(webElement) + " is not deselected!");
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
            Assert.fail(describeTag(webElement) + " is not unchecked!");
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
            Assert.fail(describeTag(webElement) + " is not disabled!");
        }
    }

    /* Select */
    @Override
    public void select(WebElement webElement) {
        if (isDeselected(webElement)) {
            webElement.click();
        }
    }

    @Override
    public void deselect(WebElement webElement) {
        if (isSelected(webElement)) {
            webElement.click();
        }
    }

    /* Select Option */
    @Override
    public void selectOption(String text, WebElement webElement) {
        new Select(webElement).selectByVisibleText(text);
    }

    @Override
    public void deselectOption(String text, WebElement webElement) {
        new Select(webElement).deselectByVisibleText(text);
    }

    @Override
    public void selectAllOptions(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            select(webElement);
        }
    }

    @Override
    public void deselectAllOptions(WebElement webElement) {
        new Select(webElement).deselectAll();
    }


    /* Select Option Value */
    @Override
    public void selectOptionWithValue(String value, WebElement webElement) {
        new Select(webElement).selectByValue(value);
    }

    @Override
    public void deselectOptionWithValue(String value, WebElement webElement) {
        new Select(webElement).deselectByValue(value);
    }

    /* Select Option Index */
    @Override
    public void selectOptionWithIndex(int index, WebElement webElement) {
        new Select(webElement).selectByIndex(index);
    }

    @Override
    public void deselectOptionWithIndex(int index, WebElement webElement) {
        new Select(webElement).selectByIndex(index);
    }

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

    /* Select Option */
    @Override
    public boolean hasOption(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasNotOption(String text, WebElement webElement) {
        return !hasOption(text, webElement);
    }

    @Override
    public boolean isOptionEnabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOptionDisabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOptionSelected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOptionDeselected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAllOptionSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isDeselected(option)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isNoOptionSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isSelected(option)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void assertHasOption(String text, WebElement webElement) {
        if (hasNotOption(text, webElement)) {
            Assert.fail(describeTag(webElement) + "has no option \"" + text.trim() + "\"!");
        }
    }

    @Override
    public void assertHasNotOption(String text, WebElement webElement) {
        if (hasOption(text, webElement)) {
            Assert.fail(describeTag(webElement) + "has option \"" + text.trim() + "\" when it shouldn't!");
        }
    }

    @Override
    public void assertIsOptionEnabled(String text, WebElement webElement) {
        if (isOptionDisabled(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not enabled!");
        }
    }

    @Override
    public void assertIsOptionDisabled(String text, WebElement webElement) {
        if (isOptionEnabled(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not disabled!");
        }
    }

    @Override
    public void assertIsOptionSelected(String text, WebElement webElement) {
        if (isOptionDeselected(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not selected!");
        }
    }

    @Override
    public void assertIsOptionDeselected(String text, WebElement webElement) {
        if (isOptionSelected(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not deselected!");
        }
    }

    @Override
    public void assertIsAllOptionSelected(WebElement webElement) {
        if (!isAllOptionSelected(webElement)) {
            Assert.fail("All options are not selected!");
        }
    }

    @Override
    public void assertIsNoOptionSelected(WebElement webElement) {
        if (!isNoOptionSelected(webElement)) {
            Assert.fail("All options are not deselected!");
        }
    }

    /* Select Option Value */
    @Override
    public boolean hasOptionWithValue(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasNotOptionWithValue(String value, WebElement webElement) {
        return !hasOptionWithValue(value, webElement);
    }

    @Override
    public boolean isOptionWithValueEnabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOptionWithValueDisabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOptionWithValueSelected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOptionWithValueDeselected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void assertHasOptionWithValue(String value, WebElement webElement) {
        if (hasNotOptionWithValue(value, webElement)) {
            Assert.fail(describeTag(webElement) + "has no option with value \"" + value.trim() + "\"!");
        }
    }

    @Override
    public void assertHasNotOptionWithValue(String value, WebElement webElement) {
        if (hasOptionWithValue(value, webElement)) {
            Assert.fail(describeTag(webElement) + "has option with value \"" + value.trim() + "\" when it shouldn't!");
        }
    }

    @Override
    public void assertIsOptionWithValueEnabled(String value, WebElement webElement) {
        if (isOptionWithValueDisabled(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not enabled!");
        }
    }

    @Override
    public void assertIsOptionWithValueDisabled(String value, WebElement webElement) {
        if (isOptionWithValueEnabled(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not disabled!");
        }
    }

    @Override
    public void assertIsOptionWithValueSelected(String value, WebElement webElement) {
        if (isOptionWithValueDeselected(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not selected!");
        }
    }

    @Override
    public void assertIsOptionWithValueDeselected(String value, WebElement webElement) {
        if (isOptionWithValueSelected(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not deselected!");
        }
    }

    /* Select Option Index */
    @Override
    public boolean hasOptionWithIndex(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return options.get(index) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public boolean hasNotOptionWithIndex(int index, WebElement webElement) {
        return !hasNotOptionWithIndex(index, webElement);
    }

    @Override
    public boolean isOptionWithIndexEnabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isEnabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public boolean isOptionWithIndexDisabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDisabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public boolean isOptionWithIndexSelected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isSelected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public boolean isOptionWithIndexDeselected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDeselected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public void assertHasOptionWithIndex(int index, WebElement webElement) {
        if (hasNotOptionWithIndex(index, webElement)) {
            Assert.fail(describeTag(webElement) + "has no option with index \"" + index + "\"!");
        }
    }

    @Override
    public void assertHasNotOptionWithIndex(int index, WebElement webElement) {
        if (hasOptionWithIndex(index, webElement)) {
            Assert.fail(describeTag(webElement) + "has option with index \"" + index + "\" when it shouldn't!");
        }
    }

    @Override
    public void assertIsOptionWithIndexEnabled(int index, WebElement webElement) {
        if (isOptionWithIndexDisabled(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not enabled!");
        }
    }

    @Override
    public void assertIsOptionWithIndexDisabled(int index, WebElement webElement) {
        if (isOptionWithIndexEnabled(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not disabled!");
        }
    }

    @Override
    public void assertIsOptionWithIndexSelected(int index, WebElement webElement) {
        if (isOptionWithIndexDeselected(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not selected!");
        }
    }

    @Override
    public void assertIsOptionWithIndexDeselected(int index, WebElement webElement) {
        if (isOptionWithIndexSelected(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not deselected!");
        }
    }
}
