package org.andidev.webdriverextension;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.andidev.webdriverextension.internal.BotUtils;
import org.andidev.webdriverextension.internal.Openable;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebElement;

public class Bot {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Bot.class);

    public static String textIn(WebElement webElement) {
        return BotUtils.textIn(webElement);
    }

    public static Double numberIn(WebElement webElement) {
        return BotUtils.numberIn(webElement);
    }

    public static List<String> optionsIn(WebElement webElement) {
        return BotUtils.optionsIn(webElement);
    }

    public static String url() {
        return BotUtils.url(ThreadDriver.getDriver());
    }

    public static String tagNameOf(WebElement webElement) {
        return BotUtils.tagNameOf(webElement);
    }

    public static String attributeIn(String name, WebElement webElement) {
        return BotUtils.attributeIn(name, webElement);
    }

    public static String idIn(WebElement webElement) {
        return BotUtils.idIn(webElement);
    }

    public static String nameIn(WebElement webElement) {
        return BotUtils.nameIn(webElement);
    }

    public static String classIn(WebElement webElement) {
        return BotUtils.classIn(webElement);
    }

    public static List<String> classesIn(WebElement webElement) {
        return BotUtils.classesIn(webElement);
    }

    public static String valueIn(WebElement webElement) {
        return BotUtils.valueIn(webElement);
    }

    public static String hrefIn(WebElement webElement) {
        return BotUtils.hrefIn(webElement);
    }

    public static Integer numberOf(List<? extends WebElement> webElements) {
        return BotUtils.numberOf(webElements);
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

    public static void open(String url) {
        BotUtils.open(url, ThreadDriver.getDriver());
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

    public static void waitForElementToDisplay(WebElement webElement) {
        BotUtils.waitForElementToDisplay(webElement, ThreadDriver.getDriver());
    }

    public static void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds) {
        BotUtils.waitForElementToDisplay(webElement, timeOutInSeconds, ThreadDriver.getDriver());
    }

    public static void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds, long sleepInMillis) {
        BotUtils.waitForElementToDisplay(webElement, timeOutInSeconds, sleepInMillis, ThreadDriver.getDriver());
    }

    public static void debug(String str) {
        BotUtils.debug(str);
    }

    public static void debug(WebElement webElement) {
        BotUtils.debug(webElement);
    }

    public static void debug(List<? extends WebElement> webElements) {
        BotUtils.debug(webElements);
    }

    public static void debugNumberOf(List<? extends WebElement> webElements) {
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

    public static boolean isAttributeContains(String name, String searchText, WebElement webElement) {
        return BotUtils.isAttributeContains(name, searchText, webElement);
    }

    public static boolean isAttributeNotContains(String name, String searchText, WebElement webElement) {
        return BotUtils.isAttributeNotContains(name, searchText, webElement);
    }

    public static boolean isAttributeStartsWith(String name, String prefix, WebElement webElement) {
        return BotUtils.isAttributeStartsWith(name, prefix, webElement);
    }

    public static boolean isAttributeNotStartsWith(String name, String prefix, WebElement webElement) {
        return BotUtils.isAttributeNotStartsWith(name, prefix, webElement);
    }

    public static boolean isAttributeEndsWith(String name, String suffix, WebElement webElement) {
        return BotUtils.isAttributeEndsWith(name, suffix, webElement);
    }

    public static boolean isAttributeNotEndsWith(String name, String suffix, WebElement webElement) {
        return BotUtils.isAttributeNotEndsWith(name, suffix, webElement);
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

    public static boolean isIdContains(String searchText, WebElement webElement) {
        return BotUtils.isIdContains(searchText, webElement);
    }

    public static boolean isIdNotContains(String searchText, WebElement webElement) {
        return BotUtils.isIdNotContains(searchText, webElement);
    }

    public static boolean isIdStartsWith(String prefix, WebElement webElement) {
        return BotUtils.isIdStartsWith(prefix, webElement);
    }

    public static boolean isIdNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.isIdNotStartsWith(prefix, webElement);
    }

    public static boolean isIdEndsWith(String suffix, WebElement webElement) {
        return BotUtils.isIdEndsWith(suffix, webElement);
    }

    public static boolean isIdNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.isIdNotEndsWith(suffix, webElement);
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

    public static boolean isNameContains(String searchText, WebElement webElement) {
        return BotUtils.isNameContains(searchText, webElement);
    }

    public static boolean isNameNotContains(String searchText, WebElement webElement) {
        return BotUtils.isNameNotContains(searchText, webElement);
    }

    public static boolean isNameStartsWith(String prefix, WebElement webElement) {
        return BotUtils.isNameStartsWith(prefix, webElement);
    }

    public static boolean isNameNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.isNameNotStartsWith(prefix, webElement);
    }

    public static boolean isNameEndsWith(String suffix, WebElement webElement) {
        return BotUtils.isNameEndsWith(suffix, webElement);
    }

    public static boolean isNameNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.isNameNotEndsWith(suffix, webElement);
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

    public static void assertHasClassContaining(String searchText, WebElement webElement) {
        BotUtils.assertHasClassContaining(searchText, webElement);
    }

    public static void assertHasClassNotContaining(String searchText, WebElement webElement) {
        BotUtils.assertHasClassNotContaining(searchText, webElement);
    }

    public static void assertHasClassStartingWith(String prefix, WebElement webElement) {
        BotUtils.assertHasClassStartingWith(prefix, webElement);
    }

    public static void assertHasClassNotStartingWith(String prefix, WebElement webElement) {
        BotUtils.assertHasClassNotStartingWith(prefix, webElement);
    }

    public static void assertHasClassEndingWith(String suffix, WebElement webElement) {
        BotUtils.assertHasClassEndingWith(suffix, webElement);
    }

    public static void assertHasClassNotEndingWith(String suffix, WebElement webElement) {
        BotUtils.assertHasClassNotEndingWith(suffix, webElement);
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

    public static boolean isValueContains(String searchText, WebElement webElement) {
        return BotUtils.isValueContains(searchText, webElement);
    }

    public static boolean isValueNotContains(String searchText, WebElement webElement) {
        return BotUtils.isValueNotContains(searchText, webElement);
    }

    public static boolean isValueStartsWith(String prefix, WebElement webElement) {
        return BotUtils.isValueStartsWith(prefix, webElement);
    }

    public static boolean isValueNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.isValueNotStartsWith(prefix, webElement);
    }

    public static boolean isValueEndsWith(String suffix, WebElement webElement) {
        return BotUtils.isValueEndsWith(suffix, webElement);
    }

    public static boolean isValueNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.isValueNotEndsWith(suffix, webElement);
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

    public static boolean isHrefContains(String searchText, WebElement webElement) {
        return BotUtils.isHrefContains(searchText, webElement);
    }

    public static boolean isHrefNotContains(String searchText, WebElement webElement) {
        return BotUtils.isHrefNotContains(searchText, webElement);
    }

    public static boolean isHrefStartsWith(String prefix, WebElement webElement) {
        return BotUtils.isHrefStartsWith(prefix, webElement);
    }

    public static boolean isHrefNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.isHrefNotStartsWith(prefix, webElement);
    }

    public static boolean isHrefEndsWith(String suffix, WebElement webElement) {
        return BotUtils.isHrefEndsWith(suffix, webElement);
    }

    public static boolean isHrefNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.isHrefNotEndsWith(suffix, webElement);
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

    public static boolean isTextContains(String searchText, WebElement webElement) {
        return BotUtils.isTextContains(searchText, webElement);
    }

    public static boolean isTextNotContains(String searchText, WebElement webElement) {
        return BotUtils.isTextNotContains(searchText, webElement);
    }

    public static boolean isTextStartsWith(String prefix, WebElement webElement) {
        return BotUtils.isTextStartsWith(prefix, webElement);
    }

    public static boolean isTextNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.isTextNotStartsWith(prefix, webElement);
    }

    public static boolean isTextEndsWith(String suffix, WebElement webElement) {
        return BotUtils.isTextEndsWith(suffix, webElement);
    }

    public static boolean isTextNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.isTextNotEndsWith(suffix, webElement);
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

    public static boolean isUrl(String url) {
        return BotUtils.isUrl(url, ThreadDriver.getDriver());
    }

    public static boolean isUrlNot(String url) {
        return BotUtils.isUrlNot(url, ThreadDriver.getDriver());
    }

    public static boolean isUrlMatching(String regExp) {
        return BotUtils.isUrlMatching(regExp, ThreadDriver.getDriver());
    }

    public static boolean isUrlNotMatching(String regExp) {
        return BotUtils.isUrlNotMatching(regExp, ThreadDriver.getDriver());
    }

    public static boolean isUrlMatching(Openable openable) {
        return BotUtils.isUrlMatching(openable, ThreadDriver.getDriver());
    }

    public static boolean isUrlNotMatching(Openable openable) {
        return BotUtils.isUrlNotMatching(openable, ThreadDriver.getDriver());
    }

    public static boolean isUrlContains(String searchText) {
        return BotUtils.isUrlContains(searchText, ThreadDriver.getDriver());
    }

    public static boolean isUrlNotContains(String searchText) {
        return BotUtils.isUrlNotContains(searchText, ThreadDriver.getDriver());
    }

    public static boolean isUrlStartsWith(String prefix) {
        return BotUtils.isUrlStartsWith(prefix, ThreadDriver.getDriver());
    }

    public static boolean isUrlNotStartsWith(String prefix) {
        return BotUtils.isUrlNotStartsWith(prefix, ThreadDriver.getDriver());
    }

    public static boolean isUrlEndsWith(String suffix) {
        return BotUtils.isUrlEndsWith(suffix, ThreadDriver.getDriver());
    }

    public static boolean isUrlNotEndsWith(String suffix) {
        return BotUtils.isUrlNotEndsWith(suffix, ThreadDriver.getDriver());
    }

    public static void assertUrl(String url) {
        BotUtils.assertUrl(url, ThreadDriver.getDriver());
    }

    public static void assertUrlNot(String url) {
        BotUtils.assertUrlNot(url, ThreadDriver.getDriver());
    }

    public static void assertUrlMatching(String regExp) {
        BotUtils.assertUrlMatching(regExp, ThreadDriver.getDriver());
    }

    public static void assertUrlNotMatching(String regExp) {
        BotUtils.assertUrlNotMatching(regExp, ThreadDriver.getDriver());
    }

    public static void assertUrlMatching(Openable openable) {
        BotUtils.assertUrlMatching(openable, ThreadDriver.getDriver());
    }

    public static void assertUrlNotMatching(Openable openable) {
        BotUtils.assertUrlNotMatching(openable, ThreadDriver.getDriver());
    }

    public static void assertUrlContains(String searchText) {
        BotUtils.assertUrlContains(searchText, ThreadDriver.getDriver());
    }

    public static void assertUrlNotContains(String searchText) {
        BotUtils.assertUrlNotContains(searchText, ThreadDriver.getDriver());
    }

    public static void assertUrlStartsWith(String prefix) {
        BotUtils.assertUrlStartsWith(prefix, ThreadDriver.getDriver());
    }

    public static void assertUrlNotStartsWith(String prefix) {
        BotUtils.assertUrlNotStartsWith(prefix, ThreadDriver.getDriver());
    }

    public static void assertUrlEndsWith(String suffix) {
        BotUtils.assertUrlEndsWith(suffix, ThreadDriver.getDriver());
    }

    public static void assertUrlNotEndsWith(String suffix) {
        BotUtils.assertUrlNotEndsWith(suffix, ThreadDriver.getDriver());
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

    public static boolean isDisplayed(WebElement webElement, long secondsToWait) {
        return BotUtils.isDisplayed(webElement, secondsToWait, ThreadDriver.getDriver());
    }

    public static boolean isNotDisplayed(WebElement webElement, long secondsToWait) {
        return BotUtils.isNotDisplayed(webElement, secondsToWait, ThreadDriver.getDriver());
    }

    public static void assertIsDisplayed(WebElement webElement) {
        BotUtils.assertIsDisplayed(webElement);
    }

    public static void assertIsDisplayed(WebElement webElement, long secondsToWait) {
        BotUtils.assertIsDisplayed(webElement, secondsToWait, ThreadDriver.getDriver());
    }

    public static void assertIsNotDisplayed(WebElement webElement, long secondsToWait) {
        BotUtils.assertIsNotDisplayed(webElement, secondsToWait, ThreadDriver.getDriver());
    }

    public static void assertIsNotDisplayed(WebElement webElement) {
        BotUtils.assertIsNotDisplayed(webElement);
    }

    public static boolean isNumberOf(int number, List<? extends WebElement> webElements) {
        return BotUtils.isNumberOf(number, webElements);
    }

    public static boolean isNumberOfNot(int number, List<? extends WebElement> webElements) {
        return BotUtils.isNumberOfNot(number, webElements);
    }

    public static boolean isNumberOfSmallerThan(int number, List<? extends WebElement> webElements) {
        return BotUtils.isNumberOfSmallerThan(number, webElements);
    }

    public static boolean isNumberOfSmallerThanOrEquals(int number, List<? extends WebElement> webElements) {
        return BotUtils.isNumberOfSmallerThanOrEquals(number, webElements);
    }

    public static boolean isNumberOfLargerThan(int number, List<? extends WebElement> webElements) {
        return BotUtils.isNumberOfLargerThan(number, webElements);
    }

    public static boolean isNumberOfLargerThanOrEquals(int number, List<? extends WebElement> webElements) {
        return BotUtils.isNumberOfLargerThanOrEquals(number, webElements);
    }

    public static void assertNumberOf(int number, List<? extends WebElement> webElements) {
        BotUtils.assertNumberOf(number, webElements);
    }

    public static void assertNumberOfNot(int number, List<? extends WebElement> webElements) {
        BotUtils.assertNumberOfNot(number, webElements);
    }

    public static void assertNumberOfSmallerThan(int number, List<? extends WebElement> webElements) {
        BotUtils.assertNumberOfSmallerThan(number, webElements);
    }

    public static void assertNumberOfSmallerThanOrEquals(int number, List<? extends WebElement> webElements) {
        BotUtils.assertNumberOfSmallerThanOrEquals(number, webElements);
    }

    public static void assertNumberOfLargerThan(int number, List<? extends WebElement> webElements) {
        BotUtils.assertNumberOfLargerThan(number, webElements);
    }

    public static void assertNumberOfLargerThanOrEquals(int number, List<? extends WebElement> webElements) {
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
}
