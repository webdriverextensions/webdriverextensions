package org.andidev.webdriverextension.bot.junitbot;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.andidev.webdriverextension.Openable;
import org.andidev.webdriverextension.bot.BotUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Bot {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Bot.class);

    public static String read(WebElement webElement) {
        return BotUtils.read(webElement);
    }

    public static Double readNumber(WebElement webElement) {
        return BotUtils.readNumber(webElement);
    }

    public static List<String> readOptions(WebElement webElement) {
        return BotUtils.readOptions(webElement);
    }

    public static String readUrl(WebDriver driver) {
        return BotUtils.readUrl(driver);
    }

    public static String readTagName(WebElement webElement) {
        return BotUtils.readTagName(webElement);
    }

    public static String readAttribute(String name, WebElement webElement) {
        return BotUtils.readAttribute(name, webElement);
    }

    public static String readId(WebElement webElement) {
        return BotUtils.readId(webElement);
    }

    public static String readName(WebElement webElement) {
        return BotUtils.readName(webElement);
    }

    public static String readClass(WebElement webElement) {
        return BotUtils.readClass(webElement);
    }

    public static List<String> readClasses(WebElement webElement) {
        return BotUtils.readClasses(webElement);
    }

    public static String readValue(WebElement webElement) {
        return BotUtils.readValue(webElement);
    }

    public static String readHref(WebElement webElement) {
        return BotUtils.readHref(webElement);
    }

    public static int count(List<WebElement> webElements) {
        return BotUtils.count(webElements);
    }

    public static void clear(WebElement webElement) {
        BotUtils.clear(webElement);
    }

    public static void type(String text, WebElement webElement) {
        BotUtils.type(text, webElement);
    }

    public static void typeNumber(Double number, WebElement webElement) {
        BotUtils.typeNumber(number, webElement);
    }

    public static void clearAndType(String text, WebElement webElement) {
        BotUtils.clearAndType(text, webElement);
    }

    public static void clearAndTypeNumber(Double number, WebElement webElement) {
        BotUtils.clearAndTypeNumber(number, webElement);
    }

    public static void pressEnter(WebElement webElement) {
        BotUtils.pressEnter(webElement);
    }

    public static void pressKeys(WebElement webElement, CharSequence... keys) {
        BotUtils.pressKeys(webElement, keys);
    }

    public static void click(WebElement webElement) {
        BotUtils.click(webElement);
    }

    public static void select(WebElement webElement) {
        BotUtils.select(webElement);
    }

    public static void deselect(WebElement webElement) {
        BotUtils.deselect(webElement);
    }

    public static void selectOption(String text, WebElement webElement) {
        BotUtils.selectOption(text, webElement);
    }

    public static void deselectOption(String text, WebElement webElement) {
        BotUtils.deselectOption(text, webElement);
    }

    public static void selectAllOptions(WebElement webElement) {
        BotUtils.selectAllOptions(webElement);
    }

    public static void deselectAllOptions(WebElement webElement) {
        BotUtils.deselectAllOptions(webElement);
    }

    public static void selectOptionWithValue(String value, WebElement webElement) {
        BotUtils.selectOptionWithValue(value, webElement);
    }

    public static void deselectOptionWithValue(String value, WebElement webElement) {
        BotUtils.deselectOptionWithValue(value, webElement);
    }

    public static void selectOptionWithIndex(int index, WebElement webElement) {
        BotUtils.selectOptionWithIndex(index, webElement);
    }

    public static void deselectOptionWithIndex(int index, WebElement webElement) {
        BotUtils.deselectOptionWithIndex(index, webElement);
    }

    public static void check(WebElement webElement) {
        BotUtils.check(webElement);
    }

    public static void uncheck(WebElement webElement) {
        BotUtils.uncheck(webElement);
    }

    public static void open(String url, WebDriver driver) {
        BotUtils.open(url, driver);
    }

    public static void open(Openable openable) {
        BotUtils.open(openable);
    }

    public static void waitFor(double seconds) {
        BotUtils.waitFor(seconds);
    }

    public static void waitFor(double time, TimeUnit unit) {
        BotUtils.waitFor(time, unit);
    }

    public static void waitForElementToDisplay(WebElement webElement, WebDriver driver) {
        BotUtils.waitForElementToDisplay(webElement, driver);
    }

    public static void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds, WebDriver driver) {
        BotUtils.waitForElementToDisplay(webElement, timeOutInSeconds, driver);
    }

    public static void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds, long sleepInMillis, WebDriver driver) {
        BotUtils.waitForElementToDisplay(webElement, timeOutInSeconds, sleepInMillis, driver);
    }

    public static void debug(String str) {
        BotUtils.debug(str);
    }

    public static void debug(WebElement webElement) {
        BotUtils.debug(webElement);
    }

    public static void debug(List<WebElement> webElements) {
        BotUtils.debug(webElements);
    }

    public static void debugText(WebElement webElement) {
        BotUtils.debugText(webElement);
    }

    public static void debugText(List<WebElement> webElements) {
        BotUtils.debugText(webElements);
    }

    public static void debugNumberOf(List<WebElement> webElements) {
        BotUtils.debugNumberOf(webElements);
    }

    public static boolean isTagName(String value, WebElement webElement) {
        return BotUtils.isTagName(value, webElement);
    }

    public static boolean isTagNameNot(String value, WebElement webElement) {
        return BotUtils.isTagNameNot(value, webElement);
    }

    public static void assertTagName(String value, WebElement webElement) {
        BotUtils.assertTagName(value, webElement);
    }

    public static void assertTagNameNot(String value, WebElement webElement) {
        BotUtils.assertTagNameNot(value, webElement);
    }

    public static boolean hasAttribute(String name, WebElement webElement) {
        return BotUtils.hasAttribute(name, webElement);
    }

    public static boolean hasNotAttribute(String name, WebElement webElement) {
        return BotUtils.hasNotAttribute(name, webElement);
    }

    public static boolean isAttribute(String name, String value, WebElement webElement) {
        return BotUtils.isAttribute(name, value, webElement);
    }

    public static boolean isAttributeNot(String name, String value, WebElement webElement) {
        return BotUtils.isAttributeNot(name, value, webElement);
    }

    public static boolean isAttributeContaining(String name, String searchText, WebElement webElement) {
        return BotUtils.isAttributeContaining(name, searchText, webElement);
    }

    public static boolean isAttributeNotContaining(String name, String searchText, WebElement webElement) {
        return BotUtils.isAttributeNotContaining(name, searchText, webElement);
    }

    public static boolean isAttributeStartingWith(String name, String prefix, WebElement webElement) {
        return BotUtils.isAttributeStartingWith(name, prefix, webElement);
    }

    public static boolean isAttributeNotStartingWith(String name, String prefix, WebElement webElement) {
        return BotUtils.isAttributeNotStartingWith(name, prefix, webElement);
    }

    public static boolean isAttributeEndingWith(String name, String suffix, WebElement webElement) {
        return BotUtils.isAttributeEndingWith(name, suffix, webElement);
    }

    public static boolean isAttributeNotEndingWith(String name, String suffix, WebElement webElement) {
        return BotUtils.isAttributeNotEndingWith(name, suffix, webElement);
    }

    public static void assertHasAttribute(String name, WebElement webElement) {
        BotUtils.assertHasAttribute(name, webElement);
    }

    public static void assertHasNotAttribute(String name, WebElement webElement) {
        BotUtils.assertHasNotAttribute(name, webElement);
    }

    public static void assertAttribute(String name, String value, WebElement webElement) {
        BotUtils.assertAttribute(name, value, webElement);
    }

    public static void assertAttributeNot(String name, String value, WebElement webElement) {
        BotUtils.assertAttributeNot(name, value, webElement);
    }

    public static void assertAttributeContains(String name, String searchText, WebElement webElement) {
        BotUtils.assertAttributeContains(name, searchText, webElement);
    }

    public static void assertAttributeNotContains(String name, String searchText, WebElement webElement) {
        BotUtils.assertAttributeNotContains(name, searchText, webElement);
    }

    public static void assertAttributeStartsWith(String name, String prefix, WebElement webElement) {
        BotUtils.assertAttributeStartsWith(name, prefix, webElement);
    }

    public static void assertAttributeNotStartsWith(String name, String prefix, WebElement webElement) {
        BotUtils.assertAttributeNotStartsWith(name, prefix, webElement);
    }

    public static void assertAttributeEndsWith(String name, String suffix, WebElement webElement) {
        BotUtils.assertAttributeEndsWith(name, suffix, webElement);
    }

    public static void assertAttributeNotEndsWith(String name, String suffix, WebElement webElement) {
        BotUtils.assertAttributeNotEndsWith(name, suffix, webElement);
    }

    public static boolean hasId(WebElement webElement) {
        return BotUtils.hasId(webElement);
    }

    public static boolean hasNotId(WebElement webElement) {
        return BotUtils.hasNotId(webElement);
    }

    public static boolean isId(String value, WebElement webElement) {
        return BotUtils.isId(value, webElement);
    }

    public static boolean isIdNot(String value, WebElement webElement) {
        return BotUtils.isIdNot(value, webElement);
    }

    public static boolean isIdContaining(String searchText, WebElement webElement) {
        return BotUtils.isIdContaining(searchText, webElement);
    }

    public static boolean isIdNotContaining(String searchText, WebElement webElement) {
        return BotUtils.isIdNotContaining(searchText, webElement);
    }

    public static boolean isIdStartingWith(String prefix, WebElement webElement) {
        return BotUtils.isIdStartingWith(prefix, webElement);
    }

    public static boolean isIdNotStartingWith(String prefix, WebElement webElement) {
        return BotUtils.isIdNotStartingWith(prefix, webElement);
    }

    public static boolean isIdEndingWith(String suffix, WebElement webElement) {
        return BotUtils.isIdEndingWith(suffix, webElement);
    }

    public static boolean isIdNotEndingWith(String suffix, WebElement webElement) {
        return BotUtils.isIdNotEndingWith(suffix, webElement);
    }

    public static void assertHasId(WebElement webElement) {
        BotUtils.assertHasId(webElement);
    }

    public static void assertHasNotId(WebElement webElement) {
        BotUtils.assertHasNotId(webElement);
    }

    public static void assertId(String value, WebElement webElement) {
        BotUtils.assertId(value, webElement);
    }

    public static void assertIdNot(String value, WebElement webElement) {
        BotUtils.assertIdNot(value, webElement);
    }

    public static void assertIdContains(String searchText, WebElement webElement) {
        BotUtils.assertIdContains(searchText, webElement);
    }

    public static void assertIdNotContains(String searchText, WebElement webElement) {
        BotUtils.assertIdNotContains(searchText, webElement);
    }

    public static void assertIdStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertIdStartsWith(prefix, webElement);
    }

    public static void assertIdNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertIdNotStartsWith(prefix, webElement);
    }

    public static void assertIdEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertIdEndsWith(suffix, webElement);
    }

    public static void assertIdNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertIdNotEndsWith(suffix, webElement);
    }

    public static boolean hasName(WebElement webElement) {
        return BotUtils.hasName(webElement);
    }

    public static boolean hasNotName(WebElement webElement) {
        return BotUtils.hasNotName(webElement);
    }

    public static boolean isName(String value, WebElement webElement) {
        return BotUtils.isName(value, webElement);
    }

    public static boolean isNameNot(String value, WebElement webElement) {
        return BotUtils.isNameNot(value, webElement);
    }

    public static boolean isNameContaining(String searchText, WebElement webElement) {
        return BotUtils.isNameContaining(searchText, webElement);
    }

    public static boolean isNameNotContaining(String searchText, WebElement webElement) {
        return BotUtils.isNameNotContaining(searchText, webElement);
    }

    public static boolean isNameStartingWith(String prefix, WebElement webElement) {
        return BotUtils.isNameStartingWith(prefix, webElement);
    }

    public static boolean isNameNotStartingWith(String prefix, WebElement webElement) {
        return BotUtils.isNameNotStartingWith(prefix, webElement);
    }

    public static boolean isNameEndingWith(String suffix, WebElement webElement) {
        return BotUtils.isNameEndingWith(suffix, webElement);
    }

    public static boolean isNameNotEndingWith(String suffix, WebElement webElement) {
        return BotUtils.isNameNotEndingWith(suffix, webElement);
    }

    public static void assertHasName(WebElement webElement) {
        BotUtils.assertHasName(webElement);
    }

    public static void assertHasNotName(WebElement webElement) {
        BotUtils.assertHasNotName(webElement);
    }

    public static void assertName(String value, WebElement webElement) {
        BotUtils.assertName(value, webElement);
    }

    public static void assertNameNot(String value, WebElement webElement) {
        BotUtils.assertNameNot(value, webElement);
    }

    public static void assertNameContains(String searchText, WebElement webElement) {
        BotUtils.assertNameContains(searchText, webElement);
    }

    public static void assertNameNotContains(String searchText, WebElement webElement) {
        BotUtils.assertNameNotContains(searchText, webElement);
    }

    public static void assertNameStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertNameStartsWith(prefix, webElement);
    }

    public static void assertNameNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertNameNotStartsWith(prefix, webElement);
    }

    public static void assertNameEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertNameEndsWith(suffix, webElement);
    }

    public static void assertNameNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertNameNotEndsWith(suffix, webElement);
    }

    public static boolean hasClass(WebElement webElement) {
        return BotUtils.hasClass(webElement);
    }

    public static boolean hasNotClass(WebElement webElement) {
        return BotUtils.hasNotClass(webElement);
    }

    public static boolean hasClass(String className, WebElement webElement) {
        return BotUtils.hasClass(className, webElement);
    }

    public static boolean hasNotClass(String className, WebElement webElement) {
        return BotUtils.hasNotClass(className, webElement);
    }

    public static boolean hasClassContaining(String searchText, WebElement webElement) {
        return BotUtils.hasClassContaining(searchText, webElement);
    }

    public static boolean hasClassNotContaining(String searchText, WebElement webElement) {
        return BotUtils.hasClassNotContaining(searchText, webElement);
    }

    public static boolean hasClassStartingWith(String prefix, WebElement webElement) {
        return BotUtils.hasClassStartingWith(prefix, webElement);
    }

    public static boolean hasClassNotStartingWith(String prefix, WebElement webElement) {
        return BotUtils.hasClassNotStartingWith(prefix, webElement);
    }

    public static boolean hasClassEndingWith(String suffix, WebElement webElement) {
        return BotUtils.hasClassEndingWith(suffix, webElement);
    }

    public static boolean hasClassNotEndingWith(String suffix, WebElement webElement) {
        return BotUtils.hasClassNotEndingWith(suffix, webElement);
    }

    public static void assertHasClass(WebElement webElement) {
        BotUtils.assertHasClass(webElement);
    }

    public static void assertHasNotClass(WebElement webElement) {
        BotUtils.assertHasNotClass(webElement);
    }

    public static void assertHasClass(String className, WebElement webElement) {
        BotUtils.assertHasClass(className, webElement);
    }

    public static void assertHasNotClass(String className, WebElement webElement) {
        BotUtils.assertHasNotClass(className, webElement);
    }

    public static void assertHasClassContains(String searchText, WebElement webElement) {
        BotUtils.assertHasClassContains(searchText, webElement);
    }

    public static void assertHasClassNotContains(String searchText, WebElement webElement) {
        BotUtils.assertHasClassNotContains(searchText, webElement);
    }

    public static void assertHasClassStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertHasClassStartsWith(prefix, webElement);
    }

    public static void assertHasClassNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertHasClassNotStartsWith(prefix, webElement);
    }

    public static void assertHasClassEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertHasClassEndsWith(suffix, webElement);
    }

    public static void assertHasClassNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertHasClassNotEndsWith(suffix, webElement);
    }

    public static boolean hasValue(WebElement webElement) {
        return BotUtils.hasValue(webElement);
    }

    public static boolean hasNotValue(WebElement webElement) {
        return BotUtils.hasNotValue(webElement);
    }

    public static boolean isValue(String value, WebElement webElement) {
        return BotUtils.isValue(value, webElement);
    }

    public static boolean isValueNot(String value, WebElement webElement) {
        return BotUtils.isValueNot(value, webElement);
    }

    public static boolean isValueContaining(String searchText, WebElement webElement) {
        return BotUtils.isValueContaining(searchText, webElement);
    }

    public static boolean isValueNotContaining(String searchText, WebElement webElement) {
        return BotUtils.isValueNotContaining(searchText, webElement);
    }

    public static boolean isValueStartingWith(String prefix, WebElement webElement) {
        return BotUtils.isValueStartingWith(prefix, webElement);
    }

    public static boolean isValueNotStartingWith(String prefix, WebElement webElement) {
        return BotUtils.isValueNotStartingWith(prefix, webElement);
    }

    public static boolean isValueEndingWith(String suffix, WebElement webElement) {
        return BotUtils.isValueEndingWith(suffix, webElement);
    }

    public static boolean isValueNotEndingWith(String suffix, WebElement webElement) {
        return BotUtils.isValueNotEndingWith(suffix, webElement);
    }

    public static void assertHasValue(WebElement webElement) {
        BotUtils.assertHasValue(webElement);
    }

    public static void assertHasNotValue(WebElement webElement) {
        BotUtils.assertHasNotValue(webElement);
    }

    public static void assertValue(String value, WebElement webElement) {
        BotUtils.assertValue(value, webElement);
    }

    public static void assertValueNot(String value, WebElement webElement) {
        BotUtils.assertValueNot(value, webElement);
    }

    public static void assertValueContains(String searchText, WebElement webElement) {
        BotUtils.assertValueContains(searchText, webElement);
    }

    public static void assertValueNotContains(String searchText, WebElement webElement) {
        BotUtils.assertValueNotContains(searchText, webElement);
    }

    public static void assertValueStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertValueStartsWith(prefix, webElement);
    }

    public static void assertValueNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertValueNotStartsWith(prefix, webElement);
    }

    public static void assertValueEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertValueEndsWith(suffix, webElement);
    }

    public static void assertValueNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertValueNotEndsWith(suffix, webElement);
    }

    public static boolean hasHref(WebElement webElement) {
        return BotUtils.hasHref(webElement);
    }

    public static boolean hasNotHref(WebElement webElement) {
        return BotUtils.hasNotHref(webElement);
    }

    public static boolean isHref(String value, WebElement webElement) {
        return BotUtils.isHref(value, webElement);
    }

    public static boolean isHrefNot(String value, WebElement webElement) {
        return BotUtils.isHrefNot(value, webElement);
    }

    public static boolean isHrefContaining(String searchText, WebElement webElement) {
        return BotUtils.isHrefContaining(searchText, webElement);
    }

    public static boolean isHrefNotContaining(String searchText, WebElement webElement) {
        return BotUtils.isHrefNotContaining(searchText, webElement);
    }

    public static boolean isHrefStartingWith(String prefix, WebElement webElement) {
        return BotUtils.isHrefStartingWith(prefix, webElement);
    }

    public static boolean isHrefNotStartingWith(String prefix, WebElement webElement) {
        return BotUtils.isHrefNotStartingWith(prefix, webElement);
    }

    public static boolean isHrefEndingWith(String suffix, WebElement webElement) {
        return BotUtils.isHrefEndingWith(suffix, webElement);
    }

    public static boolean isHrefNotEndingWith(String suffix, WebElement webElement) {
        return BotUtils.isHrefNotEndingWith(suffix, webElement);
    }

    public static void assertHasHref(WebElement webElement) {
        BotUtils.assertHasHref(webElement);
    }

    public static void assertHasNotHref(WebElement webElement) {
        BotUtils.assertHasNotHref(webElement);
    }

    public static void assertHref(String value, WebElement webElement) {
        BotUtils.assertHref(value, webElement);
    }

    public static void assertHrefNot(String value, WebElement webElement) {
        BotUtils.assertHrefNot(value, webElement);
    }

    public static void assertHrefContains(String searchText, WebElement webElement) {
        BotUtils.assertHrefContains(searchText, webElement);
    }

    public static void assertHrefNotContains(String searchText, WebElement webElement) {
        BotUtils.assertHrefNotContains(searchText, webElement);
    }

    public static void assertHrefStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertHrefStartsWith(prefix, webElement);
    }

    public static void assertHrefNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertHrefNotStartsWith(prefix, webElement);
    }

    public static void assertHrefEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertHrefEndsWith(suffix, webElement);
    }

    public static void assertHrefNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertHrefNotEndsWith(suffix, webElement);
    }

    public static boolean isText(String text, WebElement webElement) {
        return BotUtils.isText(text, webElement);
    }

    public static boolean isTextNot(String text, WebElement webElement) {
        return BotUtils.isTextNot(text, webElement);
    }

    public static boolean isTextContaining(String searchText, WebElement webElement) {
        return BotUtils.isTextContaining(searchText, webElement);
    }

    public static boolean isTextNotContaining(String searchText, WebElement webElement) {
        return BotUtils.isTextNotContaining(searchText, webElement);
    }

    public static boolean isTextStartingWith(String prefix, WebElement webElement) {
        return BotUtils.isTextStartingWith(prefix, webElement);
    }

    public static boolean isTextNotStartingWith(String prefix, WebElement webElement) {
        return BotUtils.isTextNotStartingWith(prefix, webElement);
    }

    public static boolean isTextEndingWith(String suffix, WebElement webElement) {
        return BotUtils.isTextEndingWith(suffix, webElement);
    }

    public static boolean isTextNotEndingWith(String suffix, WebElement webElement) {
        return BotUtils.isTextNotEndingWith(suffix, webElement);
    }

    public static void assertText(String text, WebElement webElement) {
        BotUtils.assertText(text, webElement);
    }

    public static void assertTextNot(String text, WebElement webElement) {
        BotUtils.assertTextNot(text, webElement);
    }

    public static void assertTextContains(String searchText, WebElement webElement) {
        BotUtils.assertTextContains(searchText, webElement);
    }

    public static void assertTextNotContains(String searchText, WebElement webElement) {
        BotUtils.assertTextNotContains(searchText, webElement);
    }

    public static void assertTextStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertTextStartsWith(prefix, webElement);
    }

    public static void assertTextNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertTextNotStartsWith(prefix, webElement);
    }

    public static void assertTextEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertTextEndsWith(suffix, webElement);
    }

    public static void assertTextNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertTextNotEndsWith(suffix, webElement);
    }

    public static boolean isNumber(Double number, WebElement webElement) {
        return BotUtils.isNumber(number, webElement);
    }

    public static boolean isNumberNot(Double number, WebElement webElement) {
        return BotUtils.isNumberNot(number, webElement);
    }

    public static boolean isNumberSmallerThan(Double number, WebElement webElement) {
        return BotUtils.isNumberSmallerThan(number, webElement);
    }

    public static boolean isNumberSmallerThanOrEquals(Double number, WebElement webElement) {
        return BotUtils.isNumberSmallerThanOrEquals(number, webElement);
    }

    public static boolean isNumberLargerThan(Double number, WebElement webElement) {
        return BotUtils.isNumberLargerThan(number, webElement);
    }

    public static boolean isNumberLargerThanOrEquals(Double number, WebElement webElement) {
        return BotUtils.isNumberLargerThanOrEquals(number, webElement);
    }

    public static void assertNumber(Double number, WebElement webElement) {
        BotUtils.assertNumber(number, webElement);
    }

    public static void assertNumberNot(Double number, WebElement webElement) {
        BotUtils.assertNumberNot(number, webElement);
    }

    public static void assertNumberSmallerThan(Double number, WebElement webElement) {
        BotUtils.assertNumberSmallerThan(number, webElement);
    }

    public static void assertNumberSmallerThanOrEquals(Double number, WebElement webElement) {
        BotUtils.assertNumberSmallerThanOrEquals(number, webElement);
    }

    public static void assertNumberLargerThan(Double number, WebElement webElement) {
        BotUtils.assertNumberLargerThan(number, webElement);
    }

    public static void assertNumberLargerThanOrEquals(Double number, WebElement webElement) {
        BotUtils.assertNumberLargerThanOrEquals(number, webElement);
    }

    public static boolean isUrl(String url, WebDriver driver) {
        return BotUtils.isUrl(url, driver);
    }

    public static boolean isUrlNot(String url, WebDriver driver) {
        return BotUtils.isUrlNot(url, driver);
    }

    public static boolean isUrlMatching(String regExp, WebDriver driver) {
        return BotUtils.isUrlMatching(regExp, driver);
    }

    public static boolean isUrlNotMatching(String regExp, WebDriver driver) {
        return BotUtils.isUrlNotMatching(regExp, driver);
    }

    public static boolean isUrlMatching(Openable openable, WebDriver driver) {
        return BotUtils.isUrlMatching(openable, driver);
    }

    public static boolean isUrlNotMatching(Openable openable, WebDriver driver) {
        return BotUtils.isUrlNotMatching(openable, driver);
    }

    public static boolean isUrlContaining(String searchText, WebDriver driver) {
        return BotUtils.isUrlContaining(searchText, driver);
    }

    public static boolean isUrlNotContaining(String searchText, WebDriver driver) {
        return BotUtils.isUrlNotContaining(searchText, driver);
    }

    public static boolean isUrlStartingWidth(String prefix, WebDriver driver) {
        return BotUtils.isUrlStartingWidth(prefix, driver);
    }

    public static boolean isUrlNotStartingWidth(String prefix, WebDriver driver) {
        return BotUtils.isUrlNotStartingWidth(prefix, driver);
    }

    public static boolean isUrlEndingWidth(String suffix, WebDriver driver) {
        return BotUtils.isUrlEndingWidth(suffix, driver);
    }

    public static boolean isUrlNotEndingWidth(String suffix, WebDriver driver) {
        return BotUtils.isUrlNotEndingWidth(suffix, driver);
    }

    public static void assertUrl(String url, WebDriver driver) {
        BotUtils.assertUrl(url, driver);
    }

    public static void assertUrlNot(String url, WebDriver driver) {
        BotUtils.assertUrlNot(url, driver);
    }

    public static void assertUrlMatching(String regExp, WebDriver driver) {
        BotUtils.assertUrlMatching(regExp, driver);
    }

    public static void assertUrlNotMatching(String regExp, WebDriver driver) {
        BotUtils.assertUrlNotMatching(regExp, driver);
    }

    public static void assertUrlMatching(Openable openable, WebDriver driver) {
        BotUtils.assertUrlMatching(openable, driver);
    }

    public static void assertUrlNotMatching(Openable openable, WebDriver driver) {
        BotUtils.assertUrlNotMatching(openable, driver);
    }

    public static void assertUrlContains(String searchText, WebDriver driver) {
        BotUtils.assertUrlContains(searchText, driver);
    }

    public static void assertUrlNotContains(String searchText, WebDriver driver) {
        BotUtils.assertUrlNotContains(searchText, driver);
    }

    public static void assertUrlStartsWidth(String prefix, WebDriver driver) {
        BotUtils.assertUrlStartsWidth(prefix, driver);
    }

    public static void assertUrlNotStartsWidth(String prefix, WebDriver driver) {
        BotUtils.assertUrlNotStartsWidth(prefix, driver);
    }

    public static void assertUrlEndsWidth(String suffix, WebDriver driver) {
        BotUtils.assertUrlEndsWidth(suffix, driver);
    }

    public static void assertUrlNotEndsWidth(String suffix, WebDriver driver) {
        BotUtils.assertUrlNotEndsWidth(suffix, driver);
    }

    public static boolean isOpen(Openable openable) {
        return BotUtils.isOpen(openable);
    }

    public static boolean isNotOpen(Openable openable) {
        return BotUtils.isNotOpen(openable);
    }

    public static void assertIsOpen(Openable openable) {
        BotUtils.assertIsOpen(openable);
    }

    public static void assertIsNotOpen(Openable openable) {
        BotUtils.assertIsNotOpen(openable);
    }

    public static boolean isSelected(WebElement webElement) {
        return BotUtils.isSelected(webElement);
    }

    public static boolean isDeselected(WebElement webElement) {
        return BotUtils.isDeselected(webElement);
    }

    public static void assertIsSelected(WebElement webElement) {
        BotUtils.assertIsSelected(webElement);
    }

    public static void assertIsDeselected(WebElement webElement) {
        BotUtils.assertIsDeselected(webElement);
    }

    public static boolean isChecked(WebElement webElement) {
        return BotUtils.isChecked(webElement);
    }

    public static boolean isUnchecked(WebElement webElement) {
        return BotUtils.isUnchecked(webElement);
    }

    public static void assertIsChecked(WebElement webElement) {
        BotUtils.assertIsChecked(webElement);
    }

    public static void assertIsUnchecked(WebElement webElement) {
        BotUtils.assertIsUnchecked(webElement);
    }

    public static boolean isEnabled(WebElement webElement) {
        return BotUtils.isEnabled(webElement);
    }

    public static boolean isDisabled(WebElement webElement) {
        return BotUtils.isDisabled(webElement);
    }

    public static void assertIsEnabled(WebElement webElement) {
        BotUtils.assertIsEnabled(webElement);
    }

    public static void assertIsDisabled(WebElement webElement) {
        BotUtils.assertIsDisabled(webElement);
    }

    public static boolean isDisplayed(WebElement webElement) {
        return BotUtils.isDisplayed(webElement);
    }

    public static boolean isNotDisplayed(WebElement webElement) {
        return BotUtils.isNotDisplayed(webElement);
    }

    public static void assertIsDisplayed(WebElement webElement) {
        BotUtils.assertIsDisplayed(webElement);
    }

    public static void assertIsNotDisplayed(WebElement webElement) {
        BotUtils.assertIsNotDisplayed(webElement);
    }

    public static boolean isNumberOf(int number, List<WebElement> webElements) {
        return BotUtils.isNumberOf(number, webElements);
    }

    public static boolean isNumberOfNot(int number, List<WebElement> webElements) {
        return BotUtils.isNumberOfNot(number, webElements);
    }

    public static boolean isNumberOfSmallerThan(int number, List<WebElement> webElements) {
        return BotUtils.isNumberOfSmallerThan(number, webElements);
    }

    public static boolean isNumberOfSmallerThanOrEquals(int number, List<WebElement> webElements) {
        return BotUtils.isNumberOfSmallerThanOrEquals(number, webElements);
    }

    public static boolean isNumberOfLargerThan(int number, List<WebElement> webElements) {
        return BotUtils.isNumberOfLargerThan(number, webElements);
    }

    public static boolean isNumberOfLargerThanOrEquals(int number, List<WebElement> webElements) {
        return BotUtils.isNumberOfLargerThanOrEquals(number, webElements);
    }

    public static void assertNumberOf(int number, List<WebElement> webElements) {
        BotUtils.assertNumberOf(number, webElements);
    }

    public static void assertNumberOfNot(int number, List<WebElement> webElements) {
        BotUtils.assertNumberOfNot(number, webElements);
    }

    public static void assertNumberOfSmallerThan(int number, List<WebElement> webElements) {
        BotUtils.assertNumberOfSmallerThan(number, webElements);
    }

    public static void assertNumberOfSmallerThanOrEquals(int number, List<WebElement> webElements) {
        BotUtils.assertNumberOfSmallerThanOrEquals(number, webElements);
    }

    public static void assertNumberOfLargerThan(int number, List<WebElement> webElements) {
        BotUtils.assertNumberOfLargerThan(number, webElements);
    }

    public static void assertNumberOfLargerThanOrEquals(int number, List<WebElement> webElements) {
        BotUtils.assertNumberOfLargerThanOrEquals(number, webElements);
    }

    public static boolean hasOption(String text, WebElement webElement) {
        return BotUtils.hasOption(text, webElement);
    }

    public static boolean hasNotOption(String text, WebElement webElement) {
        return BotUtils.hasNotOption(text, webElement);
    }

    public static boolean isOptionEnabled(String text, WebElement webElement) {
        return BotUtils.isOptionEnabled(text, webElement);
    }

    public static boolean isOptionDisabled(String text, WebElement webElement) {
        return BotUtils.isOptionDisabled(text, webElement);
    }

    public static boolean isOptionSelected(String text, WebElement webElement) {
        return BotUtils.isOptionSelected(text, webElement);
    }

    public static boolean isOptionDeselected(String text, WebElement webElement) {
        return BotUtils.isOptionDeselected(text, webElement);
    }

    public static boolean isAllOptionSelected(WebElement webElement) {
        return BotUtils.isAllOptionSelected(webElement);
    }

    public static boolean isNoOptionSelected(WebElement webElement) {
        return BotUtils.isNoOptionSelected(webElement);
    }

    public static void assertHasOption(String text, WebElement webElement) {
        BotUtils.assertHasOption(text, webElement);
    }

    public static void assertHasNotOption(String text, WebElement webElement) {
        BotUtils.assertHasNotOption(text, webElement);
    }

    public static void assertIsOptionEnabled(String text, WebElement webElement) {
        BotUtils.assertIsOptionEnabled(text, webElement);
    }

    public static void assertIsOptionDisabled(String text, WebElement webElement) {
        BotUtils.assertIsOptionDisabled(text, webElement);
    }

    public static void assertIsOptionSelected(String text, WebElement webElement) {
        BotUtils.assertIsOptionSelected(text, webElement);
    }

    public static void assertIsOptionDeselected(String text, WebElement webElement) {
        BotUtils.assertIsOptionDeselected(text, webElement);
    }

    public static void assertIsAllOptionSelected(WebElement webElement) {
        BotUtils.assertIsAllOptionSelected(webElement);
    }

    public static void assertIsNoOptionSelected(WebElement webElement) {
        BotUtils.assertIsNoOptionSelected(webElement);
    }

    public static boolean hasOptionWithValue(String value, WebElement webElement) {
        return BotUtils.hasOptionWithValue(value, webElement);
    }

    public static boolean hasNotOptionWithValue(String value, WebElement webElement) {
        return BotUtils.hasNotOptionWithValue(value, webElement);
    }

    public static boolean isOptionWithValueEnabled(String value, WebElement webElement) {
        return BotUtils.isOptionWithValueEnabled(value, webElement);
    }

    public static boolean isOptionWithValueDisabled(String value, WebElement webElement) {
        return BotUtils.isOptionWithValueDisabled(value, webElement);
    }

    public static boolean isOptionWithValueSelected(String value, WebElement webElement) {
        return BotUtils.isOptionWithValueSelected(value, webElement);
    }

    public static boolean isOptionWithValueDeselected(String value, WebElement webElement) {
        return BotUtils.isOptionWithValueDeselected(value, webElement);
    }

    public static void assertHasOptionWithValue(String value, WebElement webElement) {
        BotUtils.assertHasOptionWithValue(value, webElement);
    }

    public static void assertHasNotOptionWithValue(String value, WebElement webElement) {
        BotUtils.assertHasNotOptionWithValue(value, webElement);
    }

    public static void assertIsOptionWithValueEnabled(String value, WebElement webElement) {
        BotUtils.assertIsOptionWithValueEnabled(value, webElement);
    }

    public static void assertIsOptionWithValueDisabled(String value, WebElement webElement) {
        BotUtils.assertIsOptionWithValueDisabled(value, webElement);
    }

    public static void assertIsOptionWithValueSelected(String value, WebElement webElement) {
        BotUtils.assertIsOptionWithValueSelected(value, webElement);
    }

    public static void assertIsOptionWithValueDeselected(String value, WebElement webElement) {
        BotUtils.assertIsOptionWithValueDeselected(value, webElement);
    }

    public static boolean hasOptionWithIndex(int index, WebElement webElement) {
        return BotUtils.hasOptionWithIndex(index, webElement);
    }

    public static boolean hasNotOptionWithIndex(int index, WebElement webElement) {
        return BotUtils.hasNotOptionWithIndex(index, webElement);
    }

    public static boolean isOptionWithIndexEnabled(int index, WebElement webElement) {
        return BotUtils.isOptionWithIndexEnabled(index, webElement);
    }

    public static boolean isOptionWithIndexDisabled(int index, WebElement webElement) {
        return BotUtils.isOptionWithIndexDisabled(index, webElement);
    }

    public static boolean isOptionWithIndexSelected(int index, WebElement webElement) {
        return BotUtils.isOptionWithIndexSelected(index, webElement);
    }

    public static boolean isOptionWithIndexDeselected(int index, WebElement webElement) {
        return BotUtils.isOptionWithIndexDeselected(index, webElement);
    }

    public static void assertHasOptionWithIndex(int index, WebElement webElement) {
        BotUtils.assertHasOptionWithIndex(index, webElement);
    }

    public static void assertHasNotOptionWithIndex(int index, WebElement webElement) {
        BotUtils.assertHasNotOptionWithIndex(index, webElement);
    }

    public static void assertIsOptionWithIndexEnabled(int index, WebElement webElement) {
        BotUtils.assertIsOptionWithIndexEnabled(index, webElement);
    }

    public static void assertIsOptionWithIndexDisabled(int index, WebElement webElement) {
        BotUtils.assertIsOptionWithIndexDisabled(index, webElement);
    }

    public static void assertIsOptionWithIndexSelected(int index, WebElement webElement) {
        BotUtils.assertIsOptionWithIndexSelected(index, webElement);
    }

    public static void assertIsOptionWithIndexDeselected(int index, WebElement webElement) {
        BotUtils.assertIsOptionWithIndexDeselected(index, webElement);
    }
}
