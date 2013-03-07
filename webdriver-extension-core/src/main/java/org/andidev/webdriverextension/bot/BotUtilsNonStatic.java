package org.andidev.webdriverextension.bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.andidev.webdriverextension.Openable;
import static org.andidev.webdriverextension.bot.festbot.Bot.click;
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

public class BotUtilsNonStatic {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BotUtilsNonStatic.class);

    /* Read */
    public String read(WebElement webElement) {
        return webElement.getText();
    }

    public Double readNumber(WebElement webElement) {
        try {
            return NumberUtils.createDouble(webElement.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public List<String> readOptions(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        List<String> optionTexts = new ArrayList<String>();
        for (WebElement option : options) {
            optionTexts.add(read(option));
        }
        return optionTexts;
    }

    public String readUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String readTagName(WebElement webElement) {
        return webElement.getTagName();
    }

    public String readAttribute(String name, WebElement webElement) {
        return webElement.getAttribute(name);
    }

    public String readId(WebElement webElement) {
        return readAttribute("id", webElement);
    }

    public String readName(WebElement webElement) {
        return readAttribute("name", webElement);
    }

    public String readClass(WebElement webElement) {
        return readAttribute("class", webElement);
    }

    public List<String> readClasses(WebElement webElement) {
        return Arrays.asList(StringUtils.split(readClass(webElement)));
    }

    public String readValue(WebElement webElement) {
        return readAttribute("value", webElement);
    }

    public String readHref(WebElement webElement) {
        return readAttribute("href", webElement);
    }

    /* Clear */
    public void clear(WebElement webElement) {
        webElement.clear();
    }

    /* Type */
    public void type(String text, WebElement webElement) {
        if (text == null) {
            return;
        }
        webElement.sendKeys(text);
    }

    public void typeNumber(Double number, WebElement webElement) {
        if (number == null) {
            return;
        }
        type(number.toString(), webElement);
    }

    public void clearAndType(String text, WebElement webElement) {
        clear(webElement);
        type(text, webElement);
    }

    public void clearAndTypeNumber(Double number, WebElement webElement) {
        clear(webElement);
        typeNumber(number, webElement);
    }

    /* Press */
    public void pressEnter(WebElement webElement) {
        pressKeys(webElement, Keys.ENTER);
    }

    public void pressKeys(WebElement webElement, CharSequence... keys) {
        webElement.sendKeys(keys);
    }

    /* Click */
    public void click(WebElement webElement) {
        webElement.click();
    }

    /* Select */
    public void select(WebElement webElement) {
        if (isDeselected(webElement)) {
            webElement.click();
        }
    }

    public void deselect(WebElement webElement) {
        if (isSelected(webElement)) {
            webElement.click();
        }
    }

    /* Select Option */
    public void selectOption(String text, WebElement webElement) {
        new Select(webElement).selectByVisibleText(text);
    }

    public void deselectOption(String text, WebElement webElement) {
        new Select(webElement).deselectByVisibleText(text);
    }

    public void selectAllOptions(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            select(webElement);
        }
    }

    public void deselectAllOptions(WebElement webElement) {
        new Select(webElement).deselectAll();
    }


    /* Select Option Value */
    public void selectOptionWithValue(String value, WebElement webElement) {
        new Select(webElement).selectByValue(value);
    }

    public void deselectOptionWithValue(String value, WebElement webElement) {
        new Select(webElement).deselectByValue(value);
    }

    /* Select Option Index */
    public void selectOptionWithIndex(int index, WebElement webElement) {
        new Select(webElement).selectByIndex(index);
    }

    public void deselectOptionWithIndex(int index, WebElement webElement) {
        new Select(webElement).selectByIndex(index);
    }

    /* Check */
    public void check(WebElement webElement) {
        if (isUnchecked(webElement)) {
            click(webElement);
        }
    }

    public void uncheck(WebElement webElement) {
        if (isChecked(webElement)) {
            click(webElement);
        }
    }

    /* Open */
    public void open(String url, WebDriver driver) {
        driver.get(url);
    }

    public void open(Openable openable) {
        openable.open();
    }

    /* Count */
    public int count(List<? extends WebElement> webElements) {
        return webElements.size();
    }

    /* Wait */
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

    public void waitForElementToDisplay(WebElement webElement, WebDriver driver) {
        waitForElementToDisplay(webElement, 30, driver);
    }

    public void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds, long sleepInMillis, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, sleepInMillis);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /* Debug */
    public void debug(String str) {
        log.debug(str);
    }

    public void debug(WebElement webElement) {
        log.debug("Tag <{}> has class = \"{}\"", readTagName(webElement), readClass(webElement));
    }

    public void debug(List<? extends WebElement> webElements) {
        for (WebElement webElement : webElements) {
            debug(webElement);
        }
    }

    public void debugText(WebElement webElement) {
        log.debug("Tag <{}> contains text: \"{}\"", readTagName(webElement), read(webElement));
    }

    public void debugText(List<? extends WebElement> webElements) {
        for (WebElement webElement : webElements) {
            debugText(webElement);
        }
    }

    public void debugNumberOfElements(List<? extends WebElement> webElements) {
        log.debug("List contains {} number of tags");
    }

    /* Tag Name */
    public boolean isTagName(String value, WebElement webElement) {
        return is(value, readTagName(webElement));
    }

    public boolean isTagNameNot(String value, WebElement webElement) {
        return isNot(value, readTagName(webElement));
    }

    public void assertTagName(String value, WebElement webElement) {
        assertIs("Tag name", value, readTagName(webElement));
    }

    public void assertTagNameNot(String value, WebElement webElement) {
        assertIsNot("Tag name", value, readTagName(webElement));
    }

    /* Attribute */
    public boolean hasAttribute(String name, WebElement webElement) {
        return webElement.getAttribute(name) != null;
    }

    public boolean hasNotAttribute(String name, WebElement webElement) {
        return !hasAttribute(name, webElement);
    }

    public boolean isAttribute(String name, String value, WebElement webElement) {
        return is(value, readAttribute(name, webElement));
    }

    public boolean isAttributeNot(String name, String value, WebElement webElement) {
        return isNot(value, readAttribute(name, webElement));
    }

    public boolean isAttributeContaining(String name, String searchText, WebElement webElement) {
        return isContaining(searchText, readAttribute(name, webElement));
    }

    public boolean isAttributeNotContaining(String name, String searchText, WebElement webElement) {
        return isNotContaining(searchText, readAttribute(name, webElement));
    }

    public boolean isAttributeStartingWith(String name, String prefix, WebElement webElement) {
        return isStartingWith(prefix, readAttribute(name, webElement));
    }

    public boolean isAttributeNotStartingWith(String name, String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readAttribute(name, webElement));
    }

    public boolean isAttributeEndingWith(String name, String suffix, WebElement webElement) {
        return isEndingWith(suffix, readAttribute(name, webElement));
    }

    public boolean isAttributeNotEndingWith(String name, String suffix, WebElement webElement) {
        return isNotEndingWith(suffix, readAttribute(name, webElement));
    }

    public void assertHasAttribute(String name, WebElement webElement) {
        if (hasNotAttribute(name, webElement)) {
            Assert.fail(describeTag(webElement, name) + " does not have the " + name + " attribute!");
        }
    }

    public void assertHasNotAttribute(String name, WebElement webElement) {
        if (hasAttribute(name, webElement)) {
            Assert.fail(describeTag(webElement, name) + " has the " + name + " attribute when it shouldn't!");
        }
    }

    public void assertAttribute(String name, String value, WebElement webElement) {
        assertIs(name, value, readAttribute(name, webElement));
    }

    public void assertAttributeNot(String name, String value, WebElement webElement) {
        assertIsNot(name, value, readAttribute(name, webElement));
    }

    public void assertAttributeContains(String name, String searchText, WebElement webElement) {
        assertContains(name, searchText, readAttribute(name, webElement));
    }

    public void assertAttributeNotContains(String name, String searchText, WebElement webElement) {
        assertNotContains(name, searchText, readAttribute(name, webElement));
    }

    public void assertAttributeStartsWith(String name, String prefix, WebElement webElement) {
        assertStartsWidth(name, prefix, readAttribute(name, webElement));
    }

    public void assertAttributeNotStartsWith(String name, String prefix, WebElement webElement) {
        assertNotStartsWidth(name, prefix, readAttribute(name, webElement));
    }

    public void assertAttributeEndsWith(String name, String suffix, WebElement webElement) {
        assertEndsWidth(name, suffix, readAttribute(name, webElement));
    }

    public void assertAttributeNotEndsWith(String name, String suffix, WebElement webElement) {
        assertNotEndsWidth(name, suffix, readAttribute(name, webElement));
    }

    /* Id */
    public boolean hasId(WebElement webElement) {
        return hasAttribute("id", webElement);
    }

    public boolean hasNotId(WebElement webElement) {
        return hasNotAttribute("id", webElement);
    }

    public boolean isId(String value, WebElement webElement) {
        return is(value, readId(webElement));
    }

    public boolean isIdNot(String value, WebElement webElement) {
        return isNot(value, readId(webElement));
    }

    public boolean isIdContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readId(webElement));
    }

    public boolean isIdNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readId(webElement));
    }

    public boolean isIdStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, readId(webElement));
    }

    public boolean isIdNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readId(webElement));
    }

    public boolean isIdEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readId(webElement));
    }

    public boolean isIdNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readId(webElement));
    }

    public void assertHasId(WebElement webElement) {
        assertHasAttribute("id", webElement);
    }

    public void assertHasNotId(WebElement webElement) {
        assertHasNotAttribute("id", webElement);
    }

    public void assertId(String value, WebElement webElement) {
        assertIs("id", value, readId(webElement));
    }

    public void assertIdNot(String value, WebElement webElement) {
        assertIsNot("id", value, readId(webElement));
    }

    public void assertIdContains(String searchText, WebElement webElement) {
        assertContains("id", searchText, readId(webElement));
    }

    public void assertIdNotContains(String searchText, WebElement webElement) {
        assertNotContains("id", searchText, readId(webElement));
    }

    public void assertIdStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("id", prefix, readId(webElement));
    }

    public void assertIdNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("id", prefix, readId(webElement));
    }

    public void assertIdEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("id", suffix, readId(webElement));
    }

    public void assertIdNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("id", suffix, readId(webElement));
    }

    /* Name */
    public boolean hasName(WebElement webElement) {
        return hasAttribute("name", webElement);
    }

    public boolean hasNotName(WebElement webElement) {
        return hasNotAttribute("name", webElement);
    }

    public boolean isName(String value, WebElement webElement) {
        return is(value, readName(webElement));
    }

    public boolean isNameNot(String value, WebElement webElement) {
        return isNot(value, readName(webElement));
    }

    public boolean isNameContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readName(webElement));
    }

    public boolean isNameNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readName(webElement));
    }

    public boolean isNameStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, readName(webElement));
    }

    public boolean isNameNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readName(webElement));
    }

    public boolean isNameEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readName(webElement));
    }

    public boolean isNameNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readName(webElement));
    }

    public void assertHasName(WebElement webElement) {
        assertHasAttribute("name", webElement);
    }

    public void assertHasNotName(WebElement webElement) {
        assertHasNotAttribute("name", webElement);
    }

    public void assertName(String value, WebElement webElement) {
        assertIs("name", value, readName(webElement));
    }

    public void assertNameNot(String value, WebElement webElement) {
        assertIsNot("name", value, readName(webElement));
    }

    public void assertNameContains(String searchText, WebElement webElement) {
        assertContains("name", searchText, readName(webElement));
    }

    public void assertNameNotContains(String searchText, WebElement webElement) {
        assertNotContains("name", searchText, readName(webElement));
    }

    public void assertNameStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("name", prefix, readName(webElement));
    }

    public void assertNameNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("name", prefix, readName(webElement));
    }

    public void assertNameEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("name", suffix, readName(webElement));
    }

    public void assertNameNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("name", suffix, readName(webElement));
    }

    /* Class */
    public boolean hasClass(WebElement webElement) {
        return hasAttribute("class", webElement);
    }

    public boolean hasNotClass(WebElement webElement) {
        return hasNotAttribute("class", webElement);
    }

    public boolean hasClass(String className, WebElement webElement) {
        return readClass(webElement).matches("(\\\"|\\s)" + className.trim() + "(\\\"|\\s)");
    }

    public boolean hasNotClass(String className, WebElement webElement) {
        return !hasClass(className, webElement);
    }

    public boolean hasClassContaining(String searchText, WebElement webElement) {
        List<String> classes = readClasses(webElement);
        for (String clazz : classes) {
            if (isContaining(searchText, clazz)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasClassNotContaining(String searchText, WebElement webElement) {
        return !hasClassContaining(searchText, webElement);
    }

    public boolean hasClassStartingWith(String prefix, WebElement webElement) {
        List<String> classes = readClasses(webElement);
        for (String clazz : classes) {
            if (isStartingWith(prefix, clazz)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasClassNotStartingWith(String prefix, WebElement webElement) {
        return !hasClassStartingWith(prefix, webElement);
    }

    public boolean hasClassEndingWith(String suffix, WebElement webElement) {
        List<String> classes = readClasses(webElement);
        for (String clazz : classes) {
            if (isEndingWith(suffix, clazz)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasClassNotEndingWith(String suffix, WebElement webElement) {
        return !hasClassEndingWith(suffix, webElement);
    }

    public void assertHasClass(WebElement webElement) {
        assertHasAttribute("class", webElement);
    }

    public void assertHasNotClass(WebElement webElement) {
        assertHasNotAttribute("class", webElement);
    }

    public void assertHasClass(String className, WebElement webElement) {
        if (hasNotClass(className, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class " + className.trim() + "!");
        }
    }

    public void assertHasNotClass(String className, WebElement webElement) {
        if (hasClass(className, webElement)) {
            Assert.fail(describeTag(webElement) + " has class " + className.trim() + " when it shouldn't!");
        }
    }

    public void assertHasClassContains(String searchText, WebElement webElement) {
        if (hasClassNotContaining(searchText, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class containing text " + searchText.trim() + "!");
        }
    }

    public void assertHasClassNotContains(String searchText, WebElement webElement) {
        if (hasClassContaining(searchText, webElement)) {
            Assert.fail(describeTag(webElement) + " has class containing text " + searchText.trim() + " when it shouldn't!");
        }
    }

    public void assertHasClassStartsWith(String prefix, WebElement webElement) {
        if (hasClassNotStartingWith(prefix, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class containing prefix " + prefix.trim() + "!");
        }
    }

    public void assertHasClassNotStartsWith(String prefix, WebElement webElement) {
        if (hasClassStartingWith(prefix, webElement)) {
            Assert.fail(describeTag(webElement) + " has class containing prefix " + prefix.trim() + " when it shouldn't!");
        }
    }

    public void assertHasClassEndsWith(String suffix, WebElement webElement) {
        if (hasClassNotEndingWith(suffix, webElement)) {
            Assert.fail(describeTag(webElement) + " does not have class containing suffix " + suffix.trim() + "!");
        }
    }

    public void assertHasClassNotEndsWith(String suffix, WebElement webElement) {
        if (hasClassEndingWith(suffix, webElement)) {
            Assert.fail(describeTag(webElement) + " has class containing suffix " + suffix.trim() + " when it shouldn't!");
        }
    }

    /* Value */
    public boolean hasValue(WebElement webElement) {
        return hasAttribute("value", webElement);
    }

    public boolean hasNotValue(WebElement webElement) {
        return hasNotAttribute("value", webElement);
    }

    public boolean isValue(String value, WebElement webElement) {
        return is(value, readValue(webElement));
    }

    public boolean isValueNot(String value, WebElement webElement) {
        return isNot(value, readValue(webElement));
    }

    public boolean isValueContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readValue(webElement));
    }

    public boolean isValueNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readValue(webElement));
    }

    public boolean isValueStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, readValue(webElement));
    }

    public boolean isValueNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readValue(webElement));
    }

    public boolean isValueEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readValue(webElement));
    }

    public boolean isValueNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readValue(webElement));
    }

    public void assertHasValue(WebElement webElement) {
        assertHasAttribute("value", webElement);
    }

    public void assertHasNotValue(WebElement webElement) {
        assertHasNotAttribute("value", webElement);
    }

    public void assertValue(String value, WebElement webElement) {
        assertIs("value", value, readValue(webElement));
    }

    public void assertValueNot(String value, WebElement webElement) {
        assertIsNot("value", value, readValue(webElement));
    }

    public void assertValueContains(String searchText, WebElement webElement) {
        assertContains("value", searchText, readValue(webElement));
    }

    public void assertValueNotContains(String searchText, WebElement webElement) {
        assertNotContains("value", searchText, readValue(webElement));
    }

    public void assertValueStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("value", prefix, readValue(webElement));
    }

    public void assertValueNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("value", prefix, readValue(webElement));
    }

    public void assertValueEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("value", suffix, readValue(webElement));
    }

    public void assertValueNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("value", suffix, readValue(webElement));
    }


    /* Href */
    public boolean hasHref(WebElement webElement) {
        return hasAttribute("href", webElement);
    }

    public boolean hasNotHref(WebElement webElement) {
        return hasNotAttribute("href", webElement);
    }

    public boolean isHref(String value, WebElement webElement) {
        return is(value, readHref(webElement));
    }

    public boolean isHrefNot(String value, WebElement webElement) {
        return isNot(value, readHref(webElement));
    }

    public boolean isHrefContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readHref(webElement));
    }

    public boolean isHrefNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, readHref(webElement));
    }

    public boolean isHrefStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, readHref(webElement));
    }

    public boolean isHrefNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, readHref(webElement));
    }

    public boolean isHrefEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readHref(webElement));
    }

    public boolean isHrefNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, readHref(webElement));
    }

    public void assertHasHref(WebElement webElement) {
        assertHasAttribute("href", webElement);
    }

    public void assertHasNotHref(WebElement webElement) {
        assertHasNotAttribute("href", webElement);
    }

    public void assertHref(String value, WebElement webElement) {
        assertIs("href", value, readHref(webElement));
    }

    public void assertHrefNot(String value, WebElement webElement) {
        assertIsNot("href", value, readHref(webElement));
    }

    public void assertHrefContains(String searchText, WebElement webElement) {
        assertContains("href", searchText, readHref(webElement));
    }

    public void assertHrefNotContains(String searchText, WebElement webElement) {
        assertNotContains("href", searchText, readHref(webElement));
    }

    public void assertHrefStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("href", prefix, readHref(webElement));
    }

    public void assertHrefNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("href", prefix, readHref(webElement));
    }

    public void assertHrefEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("href", suffix, readHref(webElement));
    }

    public void assertHrefNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("href", suffix, readHref(webElement));
    }

    /* Text */
    public boolean isText(String text, WebElement webElement) {
        return is(text, read(webElement));
    }

    public boolean isTextNot(String text, WebElement webElement) {
        return isNot(text, read(webElement));
    }

    public boolean isTextContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, read(webElement));
    }

    public boolean isTextNotContaining(String searchText, WebElement webElement) {
        return isContaining(searchText, read(webElement));
    }

    public boolean isTextStartingWith(String prefix, WebElement webElement) {
        return isStartingWith(prefix, read(webElement));
    }

    public boolean isTextNotStartingWith(String prefix, WebElement webElement) {
        return isNotStartingWith(prefix, read(webElement));
    }

    public boolean isTextEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, read(webElement));
    }

    public boolean isTextNotEndingWith(String suffix, WebElement webElement) {
        return isEndingWith(suffix, read(webElement));
    }

    public void assertText(String text, WebElement webElement) {
        assertIs("Text", text, read(webElement));
    }

    public void assertTextNot(String text, WebElement webElement) {
        assertIsNot("Text", text, read(webElement));
    }

    public void assertTextContains(String searchText, WebElement webElement) {
        assertContains("Text", searchText, read(webElement));
    }

    public void assertTextNotContains(String searchText, WebElement webElement) {
        assertNotContains("Text", searchText, read(webElement));
    }

    public void assertTextStartsWith(String prefix, WebElement webElement) {
        assertStartsWidth("Text", prefix, read(webElement));
    }

    public void assertTextNotStartsWith(String prefix, WebElement webElement) {
        assertNotStartsWidth("Text", prefix, read(webElement));
    }

    public void assertTextEndsWith(String suffix, WebElement webElement) {
        assertEndsWidth("Text", suffix, read(webElement));
    }

    public void assertTextNotEndsWith(String suffix, WebElement webElement) {
        assertNotEndsWidth("Text", suffix, read(webElement));
    }

    /* Number */
    public boolean isNumber(Double number, WebElement webElement) {
        return is(number, readNumber(webElement));
    }

    public boolean isNumberNot(Double number, WebElement webElement) {
        return isNot(number, readNumber(webElement));
    }

    public boolean isNumberSmallerThan(Double number, WebElement webElement) {
        return isSmallerThan(number, readNumber(webElement));
    }

    public boolean isNumberSmallerThanOrEquals(Double number, WebElement webElement) {
        return isSmallerThanOrEquals(number, readNumber(webElement));
    }

    public boolean isNumberLargerThan(Double number, WebElement webElement) {
        return isLargerThan(number, readNumber(webElement));
    }

    public boolean isNumberLargerThanOrEquals(Double number, WebElement webElement) {
        return isLargerThanOrEquals(number, readNumber(webElement));
    }

    public void assertNumber(Double number, WebElement webElement) {
        assertIs("Number", number, readNumber(webElement));
    }

    public void assertNumberNot(Double number, WebElement webElement) {
        assertIsNot("Number", number, readNumber(webElement));
    }

    public void assertNumberSmallerThan(Double number, WebElement webElement) {
        assertIsSmallerThan("Number", number, readNumber(webElement));
    }

    public void assertNumberSmallerThanOrEquals(Double number, WebElement webElement) {
        assertIsSmallerThanOrEquals("Number", number, readNumber(webElement));
    }

    public void assertNumberLargerThan(Double number, WebElement webElement) {
        assertIsLargerThan("Number", number, readNumber(webElement));
    }

    public void assertNumberLargerThanOrEquals(Double number, WebElement webElement) {
        assertIsLargerThanOrEquals("Number", number, readNumber(webElement));
    }

    /* Browser Url */
    public boolean isUrl(String url, WebDriver driver) {
        return is(url, readUrl(driver));
    }

    public boolean isUrlNot(String url, WebDriver driver) {
        return isNot(url, readUrl(driver));
    }

    public boolean isUrlMatching(String regExp, WebDriver driver) {
        return isMatching(regExp, readUrl(driver));
    }

    public boolean isUrlNotMatching(String regExp, WebDriver driver) {
        return isNotMatching(regExp, readUrl(driver));
    }

    public boolean isUrlMatching(Openable openable, WebDriver driver) {
        return isMatching(openable.getUrl(), readUrl(driver));
    }

    public boolean isUrlNotMatching(Openable openable, WebDriver driver) {
        return isNotMatching(openable.getUrl(), readUrl(driver));
    }

    public boolean isUrlContaining(String searchText, WebDriver driver) {
        return isContaining(searchText, readUrl(driver));
    }

    public boolean isUrlNotContaining(String searchText, WebDriver driver) {
        return isNotContaining(searchText, readUrl(driver));
    }

    public boolean isUrlStartingWidth(String prefix, WebDriver driver) {
        return isStartingWith(prefix, readUrl(driver));
    }

    public boolean isUrlNotStartingWidth(String prefix, WebDriver driver) {
        return isNotStartingWith(prefix, readUrl(driver));
    }

    public boolean isUrlEndingWidth(String suffix, WebDriver driver) {
        return isEndingWith(suffix, readUrl(driver));
    }

    public boolean isUrlNotEndingWidth(String suffix, WebDriver driver) {
        return isNotEndingWith(suffix, readUrl(driver));
    }

    public void assertUrl(String url, WebDriver driver) {
        assertIs("Url", url, readUrl(driver));
    }

    public void assertUrlNot(String url, WebDriver driver) {
        assertIsNot("Url", url, readUrl(driver));
    }

    public void assertUrlMatching(String regExp, WebDriver driver) {
        assertIsMatching("Url", regExp, readUrl(driver));
    }

    public void assertUrlNotMatching(String regExp, WebDriver driver) {
        assertIsNotMatching("Url", regExp, readUrl(driver));
    }

    public void assertUrlMatching(Openable openable, WebDriver driver) {
        assertIsMatching("Url", openable.getUrl(), readUrl(driver));
    }

    public void assertUrlNotMatching(Openable openable, WebDriver driver) {
        assertIsNotMatching("Url", openable.getUrl(), readUrl(driver));
    }

    public void assertUrlContains(String searchText, WebDriver driver) {
        assertContains("Url", searchText, readUrl(driver));
    }

    public void assertUrlNotContains(String searchText, WebDriver driver) {
        assertNotContains("Url", searchText, readUrl(driver));
    }

    public void assertUrlStartsWidth(String prefix, WebDriver driver) {
        assertStartsWidth("Url", prefix, readUrl(driver));
    }

    public void assertUrlNotStartsWidth(String prefix, WebDriver driver) {
        assertNotStartsWidth("Url", prefix, readUrl(driver));
    }

    public void assertUrlEndsWidth(String suffix, WebDriver driver) {
        assertEndsWidth("Url", suffix, readUrl(driver));
    }

    public void assertUrlNotEndsWidth(String suffix, WebDriver driver) {
        assertNotEndsWidth("Url", suffix, readUrl(driver));
    }

    /* Open */
    public boolean isOpen(Openable openable) {
        return openable.isOpen();
    }

    public boolean isNotOpen(Openable openable) {
        return openable.isNotOpen();
    }

    public void assertIsOpen(Openable openable) {
        openable.assertIsOpen();
    }

    public void assertIsNotOpen(Openable openable) {
        openable.assertIsNotOpen();
    }

    /* Selected */
    public boolean isSelected(WebElement webElement) {
        return webElement.isSelected();
    }

    public boolean isDeselected(WebElement webElement) {
        return !isSelected(webElement);
    }

    public void assertIsSelected(WebElement webElement) {
        if (isDeselected(webElement)) {
            Assert.fail(describeTag(webElement) + " is not selected!");
        }
    }

    public void assertIsDeselected(WebElement webElement) {
        if (isSelected(webElement)) {
            Assert.fail(describeTag(webElement) + " is not deselected!");
        }
    }

    /* Checked/Unchecked */
    public boolean isChecked(WebElement webElement) {
        return webElement.isSelected();
    }

    public boolean isUnchecked(WebElement webElement) {
        return !isChecked(webElement);
    }

    public void assertIsChecked(WebElement webElement) {
        if (isUnchecked(webElement)) {
            Assert.fail(describeTag(webElement) + " is not checked!");
        }
    }

    public void assertIsUnchecked(WebElement webElement) {
        if (isChecked(webElement)) {
            Assert.fail(describeTag(webElement) + " is not unchecked!");
        }
    }

    /* Enabled/Disabled */
    public boolean isEnabled(WebElement webElement) {
        return webElement.isEnabled();
    }

    public boolean isDisabled(WebElement webElement) {
        return !isEnabled(webElement);
    }

    public void assertIsEnabled(WebElement webElement) {
        if (isUnchecked(webElement)) {
            Assert.fail(describeTag(webElement) + " is not enabled!");
        }
    }

    public void assertIsDisabled(WebElement webElement) {
        if (isChecked(webElement)) {
            Assert.fail(describeTag(webElement) + " is not disabled!");
        }
    }

    /* Display */
    public boolean isDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isNotDisplayed(WebElement webElement) {
        return isDisplayed(webElement);
    }

    public void assertIsDisplayed(WebElement webElement) {
        if (isNotDisplayed(webElement)) {
            Assert.fail("WebElement is not displayed!");
        }
    }

    public void assertIsNotDisplayed(WebElement webElement) {
        if (isDisplayed(webElement)) {
            Assert.fail("WebElement is displayed when it shouldn't!");
        }
    }

    /* Number of Elements */
    public boolean isNumberOfElements(int number, List<? extends WebElement> webElements) {
        return is((double) number, (double) webElements.size());
    }

    public boolean isNumberOfElementsNot(int number, List<? extends WebElement> webElements) {
        return isNot((double) number, (double) webElements.size());
    }

    public boolean isNumberOfElementsSmallerThan(int number, List<? extends WebElement> webElements) {
        return isSmallerThan((double) number, (double) webElements.size());
    }

    public boolean isNumberOfElementsSmallerThanOrEquals(int number, List<? extends WebElement> webElements) {
        return isSmallerThanOrEquals((double) number, (double) webElements.size());
    }

    public boolean isNumberOfElementsLargerThan(int number, List<? extends WebElement> webElements) {
        return isLargerThan((double) number, (double) webElements.size());
    }

    public boolean isNumberOfElementsLargerThanOrEquals(int number, List<? extends WebElement> webElements) {
        return isLargerThanOrEquals((double) number, (double) webElements.size());
    }

    public void assertNumberOfElements(int number, List<? extends WebElement> webElements) {
        assertIs("Number of elements", (double) number, (double) webElements.size());
    }

    public void assertNumberOfElementsNot(int number, List<? extends WebElement> webElements) {
        assertIsNot("Number of elements", (double) number, (double) webElements.size());
    }

    public void assertNumberOfElementsSmallerThan(int number, List<? extends WebElement> webElements) {
        assertIsSmallerThan("Number of elements", (double) number, (double) webElements.size());
    }

    public void assertNumberOfElementsSmallerThanOrEquals(int number, List<? extends WebElement> webElements) {
        assertIsSmallerThanOrEquals("Number of elements", (double) number, (double) webElements.size());
    }

    public void assertNumberOfElementsLargerThan(int number, List<? extends WebElement> webElements) {
        assertIsLargerThan("Number of elements", (double) number, (double) webElements.size());
    }

    public void assertNumberOfElementsLargerThanOrEquals(int number, List<? extends WebElement> webElements) {
        assertIsLargerThanOrEquals("Number of elements", (double) number, (double) webElements.size());
    }

    /* Select Option */
    public boolean hasOption(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNotOption(String text, WebElement webElement) {
        return !hasOption(text, webElement);
    }

    public boolean isOptionEnabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOptionDisabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOptionSelected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOptionDeselected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isText(text, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAllOptionSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isDeselected(option)) {
                return false;
            }
        }
        return true;
    }

    public boolean isNoOptionSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isSelected(option)) {
                return false;
            }
        }
        return true;
    }

    public void assertHasOption(String text, WebElement webElement) {
        if (hasNotOption(text, webElement)) {
            Assert.fail(describeTag(webElement) + "has no option \"" + text.trim() + "\"!");
        }
    }

    public void assertHasNotOption(String text, WebElement webElement) {
        if (hasOption(text, webElement)) {
            Assert.fail(describeTag(webElement) + "has option \"" + text.trim() + "\" when it shouldn't!");
        }
    }

    public void assertIsOptionEnabled(String text, WebElement webElement) {
        if (isOptionDisabled(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not enabled!");
        }
    }

    public void assertIsOptionDisabled(String text, WebElement webElement) {
        if (isOptionEnabled(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not disabled!");
        }
    }

    public void assertIsOptionSelected(String text, WebElement webElement) {
        if (isOptionDeselected(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not selected!");
        }
    }

    public void assertIsOptionDeselected(String text, WebElement webElement) {
        if (isOptionSelected(text, webElement)) {
            Assert.fail("Option \"" + text.trim() + "\" is not deselected!");
        }
    }

    public void assertIsAllOptionSelected(WebElement webElement) {
        if (!isAllOptionSelected(webElement)) {
            Assert.fail("All options are not selected!");
        }
    }

    public void assertIsNoOptionSelected(WebElement webElement) {
        if (!isNoOptionSelected(webElement)) {
            Assert.fail("All options are not deselected!");
        }
    }

    /* Select Option Value */
    public boolean hasOptionWithValue(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNotOptionWithValue(String value, WebElement webElement) {
        return !hasOptionWithValue(value, webElement);
    }

    public boolean isOptionWithValueEnabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOptionWithValueDisabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOptionWithValueSelected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOptionWithValueDeselected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isValue(value, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }

    public void assertHasOptionWithValue(String value, WebElement webElement) {
        if (hasNotOptionWithValue(value, webElement)) {
            Assert.fail(describeTag(webElement) + "has no option with value \"" + value.trim() + "\"!");
        }
    }

    public void assertHasNotOptionWithValue(String value, WebElement webElement) {
        if (hasOptionWithValue(value, webElement)) {
            Assert.fail(describeTag(webElement) + "has option with value \"" + value.trim() + "\" when it shouldn't!");
        }
    }

    public void assertIsOptionWithValueEnabled(String value, WebElement webElement) {
        if (isOptionWithValueDisabled(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not enabled!");
        }
    }

    public void assertIsOptionWithValueDisabled(String value, WebElement webElement) {
        if (isOptionWithValueEnabled(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not disabled!");
        }
    }

    public void assertIsOptionWithValueSelected(String value, WebElement webElement) {
        if (isOptionWithValueDeselected(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not selected!");
        }
    }

    public void assertIsOptionWithValueDeselected(String value, WebElement webElement) {
        if (isOptionWithValueSelected(value, webElement)) {
            Assert.fail("Option with value \"" + value.trim() + "\" is not deselected!");
        }
    }

    /* Select Option Index */
    public boolean hasOptionWithIndex(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return options.get(index) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean hasNotOptionWithIndex(int index, WebElement webElement) {
        return !hasNotOptionWithIndex(index, webElement);
    }

    public boolean isOptionWithIndexEnabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isEnabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean isOptionWithIndexDisabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDisabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean isOptionWithIndexSelected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isSelected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean isOptionWithIndexDeselected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDeselected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public void assertHasOptionWithIndex(int index, WebElement webElement) {
        if (hasNotOptionWithIndex(index, webElement)) {
            Assert.fail(describeTag(webElement) + "has no option with index \"" + index + "\"!");
        }
    }

    public void assertHasNotOptionWithIndex(int index, WebElement webElement) {
        if (hasOptionWithIndex(index, webElement)) {
            Assert.fail(describeTag(webElement) + "has option with index \"" + index + "\" when it shouldn't!");
        }
    }

    public void assertIsOptionWithIndexEnabled(int index, WebElement webElement) {
        if (isOptionWithIndexDisabled(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not enabled!");
        }
    }

    public void assertIsOptionWithIndexDisabled(int index, WebElement webElement) {
        if (isOptionWithIndexEnabled(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not disabled!");
        }
    }

    public void assertIsOptionWithIndexSelected(int index, WebElement webElement) {
        if (isOptionWithIndexDeselected(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not selected!");
        }
    }

    public void assertIsOptionWithIndexDeselected(int index, WebElement webElement) {
        if (isOptionWithIndexSelected(index, webElement)) {
            Assert.fail("Option with index \"" + index + "\" is not deselected!");
        }
    }
    /* Has */
    public boolean has(String name, WebElement webElement) {
        return webElement.getAttribute(name) != null;
    }

    public boolean hasNot(String name, WebElement webElement) {
        return !hasAttribute(name, webElement);
    }

    /* Is */
    public boolean is(String text1, String text2) {
        return StringUtils.equals(text1, text2);
    }

    public boolean isNot(String text1, String text2) {
        return !StringUtils.equals(text1, text2);
    }

    public boolean isContaining(String searchText, String text) {
        return StringUtils.contains(text, searchText);
    }

    public boolean isNotContaining(String searchText, String text) {
        return !StringUtils.contains(text, searchText);
    }

    public boolean isStartingWith(String prefix, String text) {
        return StringUtils.startsWith(text, prefix);
    }

    public boolean isNotStartingWith(String prefix, String text) {
        return !StringUtils.startsWith(text, prefix);
    }

    public boolean isEndingWith(String suffix, String text) {
        return StringUtils.endsWith(text, suffix);
    }

    public boolean isNotEndingWith(String suffix, String text) {
        return !StringUtils.endsWith(text, suffix);
    }

    public boolean isMatching(String regularExpression, String text) {
        if (text == null || regularExpression == null) {
            return false;
        }
        return text.matches(regularExpression);
    }

    public boolean isNotMatching(String regularExpression, String text) {
        if (text == null || regularExpression == null) {
            return true;
        }
        return !text.matches(regularExpression);
    }

    /* Is Any Of */
    public boolean isAnyOf(String[] anyOfTexts, String text) {
        for (String anyOfText : anyOfTexts) {
            if (StringUtils.equals(text, anyOfText)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNotAnyOf(String[] anyOfTexts, String text) {
        return !isAnyOf(anyOfTexts, text);
    }

    public boolean isContainingAnyOf(String[] searchTexts, String text) {
        for (String searchText : searchTexts) {
            if (StringUtils.contains(text, searchText)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNotContainingAnyOf(String[] searchTexts, String text) {
        return !isContainingAnyOf(searchTexts, text);
    }

    public boolean isStartingWithAnyOf(String[] prefixes, String text) {
        return StringUtils.startsWithAny(text, prefixes);
    }

    public boolean isNotStartingWithAnyOf(String[] prefixes, String text) {
        return !isStartingWithAnyOf(prefixes, text);
    }

    public boolean isEndingWithAnyOf(String[] suffix, String text) {
        return StringUtils.endsWithAny(text, suffix);
    }

    public boolean isNotEndingWithAnyOf(String[] suffix, String text) {
        return !isEndingWithAnyOf(suffix, text);
    }

    public boolean isMatchingAnyOf(String[] regularExpressions, String text) {
        for (String regularExpression : regularExpressions) {
            if (isMatching(regularExpression, text)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNotMatchingAnyOf(String[] regularExpressions, String text) {
        return !isMatchingAnyOf(regularExpressions, text);
    }

    /* Is Ignore Case */
    public boolean isIgnoreCase(String text1, String text2) {
        return StringUtils.equalsIgnoreCase(text1, text2);
    }

    public boolean isNotIgnoreCase(String text1, String text2) {
        return !isIgnoreCase(text1, text2);
    }

    public boolean isContainingIgnoreCase(String searchText, String text) {
        return StringUtils.containsIgnoreCase(text, searchText);
    }

    public boolean isNotContainingIgnoreCase(String searchText, String text) {
        return !isContainingIgnoreCase(text, searchText);
    }

    public boolean isStartingWithIgnoreCase(String prefix, String text) {
        return StringUtils.startsWithIgnoreCase(text, prefix);
    }

    public boolean isNotStartingWithIgnoreCase(String prefix, String text) {
        return !isStartingWithIgnoreCase(prefix, text);
    }

    public boolean isEndingWithIgnoreCase(String suffix, String text) {
        return StringUtils.endsWithIgnoreCase(text, suffix);
    }

    public boolean isNotEndingWithIgnoreCase(String suffix, String text) {
        return !isEndingWithIgnoreCase(suffix, text);
    }

    /* Asserts */
    public void assertIs(String name, String expected, String actual) {
        Assert.assertEquals(name + " is not " + expected + "!", expected, actual);
    }

    public void assertIsNot(String name, String notExpected, String actual) {
        Assert.assertNotEquals(name + " is " + notExpected + " when it shouldn't!", notExpected, actual);
    }

    public void assertIsMatching(String name, String regExp, String actual) {
        if (actual.matches(regExp)) {
            Assert.fail(name + ": " + actual + " is not matching " + regExp + "!");
        }
    }

    public void assertIsNotMatching(String name, String regExp, String actual) {
        if (!actual.matches(regExp)) {
            Assert.fail(name + ": " + actual + " is matching " + regExp + " when it shouldn't!");
        }
    }

    public void assertContains(String name, String searchText, String actual) {
        if (!StringUtils.contains(actual, searchText)) {
            Assert.fail(name + ": " + actual + " is not containing " + searchText);
        }
    }

    public void assertNotContains(String name, String searchText, String actual) {
        if (StringUtils.contains(actual, searchText)) {
            Assert.fail(name + ": " + actual + " is containing " + searchText + " when it shouldn't!");
        }
    }

    public void assertStartsWidth(String name, String prefix, String actual) {
        if (!StringUtils.startsWith(actual, prefix)) {
            Assert.fail(name + ": " + actual + " is not starting with " + prefix);
        }
    }

    public void assertNotStartsWidth(String name, String prefix, String actual) {
        if (StringUtils.startsWith(actual, prefix)) {
            Assert.fail(name + ": " + actual + " is starting with " + prefix + " when it shouldn't!");
        }
    }

    public void assertEndsWidth(String name, String suffix, String actual) {
        if (!StringUtils.startsWith(actual, suffix)) {
            Assert.fail(name + ": " + actual + " is not ending with " + suffix);
        }
    }

    public void assertNotEndsWidth(String name, String suffix, String actual) {
        if (StringUtils.endsWith(actual, suffix)) {
            Assert.fail(name + ": " + actual + " is ending with " + suffix + " when it shouldn't!");
        }
    }

    public boolean is(Double comparedTo, Double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean isNot(double comparedTo, double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean isSmallerThan(Double comparedTo, Double number) {
        return number < comparedTo;
    }

    public boolean isSmallerThanOrEquals(double comparedTo, double number) {
        return number <= comparedTo;
    }

    public boolean isLargerThan(double comparedTo, double number) {
        return number > comparedTo;
    }

    public boolean isLargerThanOrEquals(double comparedTo, double number) {
        return number >= comparedTo;
    }

    public void assertIs(String name, double comparedTo, double number) {
        if (isNot(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not " + comparedTo + " !");
        }
    }

    public void assertIsNot(String name, double comparedTo, double number) {
        if (is(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is " + comparedTo + " when it shouldn't!");
        }
    }

    public void assertIsSmallerThan(String name, double comparedTo, double number) {
        if (isLargerThanOrEquals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not smaller then " + comparedTo + " !");
        }
    }

    public void assertIsSmallerThanOrEquals(String name, double comparedTo, double number) {
        if (isLargerThan(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not smaller then or equal " + comparedTo + " !");
        }
    }

    public void assertIsLargerThan(String name, double comparedTo, double number) {
        if (isSmallerThanOrEquals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not larger then " + comparedTo + " !");
        }
    }

    public void assertIsLargerThanOrEquals(String name, double comparedTo, double number) {
        if (isSmallerThan(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not larger then or equal " + comparedTo + " !");
        }
    }

    public String describeTag(WebElement webElement) {
        return describeTag(webElement, null);
    }

    public String describeTag(WebElement webElement, String extraAttribute) {
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

    public String describeAttribute(String attributeName, WebElement webElement) {
        return hasAttribute(attributeName, webElement) ? attributeName + " = '" + readAttribute(attributeName, webElement) + "' " : "";
    }

    public String describeId(WebElement webElement) {
        return hasId(webElement) ? "id = '" + readId(webElement) + "' " : "";
    }

    public String describeName(WebElement webElement) {
        return hasName(webElement) ? "name = '" + readName(webElement) + "' " : "";
    }

    public String describeClass(WebElement webElement) {
        return hasClass(webElement) ? "class = '" + readClass(webElement) + "' " : "";
    }

    public String describeValue(WebElement webElement) {
        return hasValue(webElement) ? "value = '" + readValue(webElement) + "' " : "";
    }

}
