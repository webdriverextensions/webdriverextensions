package org.andidev.webdriverextension;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.andidev.webdriverextension.internal.BotUtils;
import org.andidev.webdriverextension.internal.Openable;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebElement;

public class Bot {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Bot.class);

    /* Click */
    public static void click(WebElement webElement) {
        BotUtils.click(webElement);
    }



    /* Type */
    public static void type(String text, WebElement webElement) {
        BotUtils.type(text, webElement);
    }

    public static void type(Double number, WebElement webElement) {
        BotUtils.type(number, webElement);
    }



    /* Clear */
    public static void clear(WebElement webElement) {
        BotUtils.clear(webElement);
    }

    public static void clearAndType(String text, WebElement webElement) {
        BotUtils.clearAndType(text, webElement);
    }

    public static void clearAndType(Double number, WebElement webElement) {
        BotUtils.clearAndType(number, webElement);
    }



    /* Press Keys */
    public static void pressEnter(WebElement webElement) {
        BotUtils.pressEnter(webElement);
    }

    public static void pressKeys(WebElement webElement, CharSequence... keys) {
        BotUtils.pressKeys(webElement, keys);
    }



    /* Select/Deselect */
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



    /* Check/Uncheck */
    public static void check(WebElement webElement) {
        BotUtils.check(webElement);
    }

    public static void uncheck(WebElement webElement) {
        BotUtils.uncheck(webElement);
    }



    /* Open */
    public static void open(String url) {
        BotUtils.open(url, ThreadDriver.getDriver());
    }

    public static void open(Openable openable) {
        BotUtils.open(openable);
    }



    /* Wait For */
    public static void waitFor(double seconds) {
        BotUtils.waitFor(seconds);
    }

    public static void waitFor(double time, TimeUnit unit) {
        BotUtils.waitFor(time, unit);
    }

    public static void waitForElementToDisplay(WebElement webElement) {
        BotUtils.waitForElementToDisplay(webElement, ThreadDriver.getDriver());
    }

    public static void waitForElementToDisplay(WebElement webElement, long secondsToWait) {
        BotUtils.waitForElementToDisplay(webElement, secondsToWait, ThreadDriver.getDriver());
    }

    public static void waitForElementToDisplay(WebElement webElement, long secondsToWait, long sleepInMillis) {
        BotUtils.waitForElementToDisplay(webElement, secondsToWait, sleepInMillis, ThreadDriver.getDriver());
    }



    /* Debug */
    public static void debug(String str) {
        BotUtils.debug(str);
    }

    public static void debug(WebElement webElement) {
        BotUtils.debug(webElement);
    }

    public static void debug(List<? extends WebElement> webElements) {
        BotUtils.debug(webElements);
    }



    /* Is Open */
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



    /* Is Displayed */
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

    public static void assertIsNotDisplayed(WebElement webElement) {
        BotUtils.assertIsNotDisplayed(webElement);
    }

    public static void assertIsDisplayed(WebElement webElement, long secondsToWait) {
        BotUtils.assertIsDisplayed(webElement, secondsToWait, ThreadDriver.getDriver());
    }

    public static void assertIsNotDisplayed(WebElement webElement, long secondsToWait) {
        BotUtils.assertIsNotDisplayed(webElement, secondsToWait, ThreadDriver.getDriver());
    }



    /* Size */
    public static Integer sizeOf(Collection collection) {
        return BotUtils.sizeOf(collection);
    }

    public static boolean sizeEquals(int number, Collection collection) {
        return BotUtils.sizeEquals(number, collection);
    }

    public static boolean sizeNotEquals(int number, Collection collection) {
        return BotUtils.sizeNotEquals(number, collection);
    }

    public static boolean sizeLessThan(int number, Collection collection) {
        return BotUtils.sizeLessThan(number, collection);
    }

    public static boolean sizeLessThanOrEquals(int number, Collection collection) {
        return BotUtils.sizeLessThanOrEquals(number, collection);
    }

    public static boolean sizeGreaterThan(int number, Collection collection) {
        return BotUtils.sizeGreaterThan(number, collection);
    }

    public static boolean sizeGreaterThanOrEquals(int number, Collection collection) {
        return BotUtils.sizeGreaterThanOrEquals(number, collection);
    }

    public static void assertSizeEquals(int number, Collection collection) {
        BotUtils.assertSizeEquals(number, collection);
    }

    public static void assertSizeNotEquals(int number, Collection collection) {
        BotUtils.assertSizeNotEquals(number, collection);
    }

    public static void assertSizeLessThan(int number, Collection collection) {
        BotUtils.assertSizeLessThan(number, collection);
    }

    public static void assertSizeLessThanOrEquals(int number, Collection collection) {
        BotUtils.assertSizeLessThanOrEquals(number, collection);
    }

    public static void assertSizeGreaterThan(int number, Collection collection) {
        BotUtils.assertSizeGreaterThan(number, collection);
    }

    public static void assertSizeGreaterThanOrEquals(int number, Collection collection) {
        BotUtils.assertSizeGreaterThanOrEquals(number, collection);
    }



    /* Url */
    public static String url() {
        return BotUtils.url(ThreadDriver.getDriver());
    }

    public static boolean urlEquals(String url) {
        return BotUtils.urlEquals(url, ThreadDriver.getDriver());
    }

    public static boolean urlNotEquals(String url) {
        return BotUtils.urlNotEquals(url, ThreadDriver.getDriver());
    }

    public static boolean urlContains(String searchText) {
        return BotUtils.urlContains(searchText, ThreadDriver.getDriver());
    }

    public static boolean urlNotContains(String searchText) {
        return BotUtils.urlNotContains(searchText, ThreadDriver.getDriver());
    }

    public static boolean urlStartsWith(String prefix) {
        return BotUtils.urlStartsWith(prefix, ThreadDriver.getDriver());
    }

    public static boolean urlNotStartsWith(String prefix) {
        return BotUtils.urlNotStartsWith(prefix, ThreadDriver.getDriver());
    }

    public static boolean urlEndsWith(String suffix) {
        return BotUtils.urlEndsWith(suffix, ThreadDriver.getDriver());
    }

    public static boolean urlNotEndsWith(String suffix) {
        return BotUtils.urlNotEndsWith(suffix, ThreadDriver.getDriver());
    }

    public static boolean urlMatches(String regExp) {
        return BotUtils.urlMatches(regExp, ThreadDriver.getDriver());
    }

    public static boolean urlNotMatches(String regExp) {
        return BotUtils.urlNotMatches(regExp, ThreadDriver.getDriver());
    }

    public static void assertUrlEquals(String url) {
        BotUtils.assertUrlEquals(url, ThreadDriver.getDriver());
    }

    public static void assertUrlNotEquals(String url) {
        BotUtils.assertUrlNotEquals(url, ThreadDriver.getDriver());
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

    public static void assertUrlMatches(String regExp) {
        BotUtils.assertUrlMatches(regExp, ThreadDriver.getDriver());
    }

    public static void assertUrlNotMatches(String regExp) {
        BotUtils.assertUrlNotMatches(regExp, ThreadDriver.getDriver());
    }



    /* Title */
    public static String title() {
        return BotUtils.title(ThreadDriver.getDriver());
    }

    public static boolean titleEquals(String title) {
        return BotUtils.titleEquals(title, ThreadDriver.getDriver());
    }

    public static boolean titleNotEquals(String title) {
        return BotUtils.titleNotEquals(title, ThreadDriver.getDriver());
    }

    public static boolean titleContains(String searchText) {
        return BotUtils.titleContains(searchText, ThreadDriver.getDriver());
    }

    public static boolean titleNotContains(String searchText) {
        return BotUtils.titleNotContains(searchText, ThreadDriver.getDriver());
    }

    public static boolean titleStartsWith(String prefix) {
        return BotUtils.titleStartsWith(prefix, ThreadDriver.getDriver());
    }

    public static boolean titleNotStartsWith(String prefix) {
        return BotUtils.titleNotStartsWith(prefix, ThreadDriver.getDriver());
    }

    public static boolean titleEndsWith(String suffix) {
        return BotUtils.titleEndsWith(suffix, ThreadDriver.getDriver());
    }

    public static boolean titleNotEndsWith(String suffix) {
        return BotUtils.titleNotEndsWith(suffix, ThreadDriver.getDriver());
    }

    public static boolean titleMatches(String regExp) {
        return BotUtils.titleMatches(regExp, ThreadDriver.getDriver());
    }

    public static boolean titleNotMatches(String regExp) {
        return BotUtils.titleNotMatches(regExp, ThreadDriver.getDriver());
    }

    public static void assertTitleEquals(String title) {
        BotUtils.assertTitleEquals(title, ThreadDriver.getDriver());
    }

    public static void assertTitleNotEquals(String title) {
        BotUtils.assertTitleNotEquals(title, ThreadDriver.getDriver());
    }

    public static void assertTitleContains(String searchText) {
        BotUtils.assertTitleContains(searchText, ThreadDriver.getDriver());
    }

    public static void assertTitleNotContains(String searchText) {
        BotUtils.assertTitleNotContains(searchText, ThreadDriver.getDriver());
    }

    public static void assertTitleStartsWith(String prefix) {
        BotUtils.assertTitleStartsWith(prefix, ThreadDriver.getDriver());
    }

    public static void assertTitleNotStartsWith(String prefix) {
        BotUtils.assertTitleNotStartsWith(prefix, ThreadDriver.getDriver());
    }

    public static void assertTitleEndsWith(String suffix) {
        BotUtils.assertTitleEndsWith(suffix, ThreadDriver.getDriver());
    }

    public static void assertTitleNotEndsWith(String suffix) {
        BotUtils.assertTitleNotEndsWith(suffix, ThreadDriver.getDriver());
    }

    public static void assertTitleMatches(String regExp) {
        BotUtils.assertTitleMatches(regExp, ThreadDriver.getDriver());
    }

    public static void assertTitleNotMatches(String regExp) {
        BotUtils.assertTitleNotMatches(regExp, ThreadDriver.getDriver());
    }



    /* Tag Name */
    public static String tagNameOf(WebElement webElement) {
        return BotUtils.tagNameOf(webElement);
    }

    public static boolean tagNameEquals(String value, WebElement webElement) {
        return BotUtils.tagNameEquals(value, webElement);
    }

    public static boolean tagNameNotEquals(String value, WebElement webElement) {
        return BotUtils.tagNameNotEquals(value, webElement);
    }

    public static void assertTagNameEquals(String value, WebElement webElement) {
        BotUtils.assertTagNameEquals(value, webElement);
    }

    public static void assertTagNameNotEquals(String value, WebElement webElement) {
        BotUtils.assertTagNameNotEquals(value, webElement);
    }



    /* Attribute */
    public static String attributeIn(String name, WebElement webElement) {
        return BotUtils.attributeIn(name, webElement);
    }

    public static boolean hasAttribute(String name, WebElement webElement) {
        return BotUtils.hasAttribute(name, webElement);
    }

    public static boolean hasNotAttribute(String name, WebElement webElement) {
        return BotUtils.hasNotAttribute(name, webElement);
    }

    public static boolean attributeEquals(String name, String value, WebElement webElement) {
        return BotUtils.attributeEquals(name, value, webElement);
    }

    public static boolean attributeNotEquals(String name, String value, WebElement webElement) {
        return BotUtils.attributeNotEquals(name, value, webElement);
    }

    public static boolean attributeContains(String name, String searchText, WebElement webElement) {
        return BotUtils.attributeContains(name, searchText, webElement);
    }

    public static boolean attributeNotContains(String name, String searchText, WebElement webElement) {
        return BotUtils.attributeNotContains(name, searchText, webElement);
    }

    public static boolean attributeStartsWith(String name, String prefix, WebElement webElement) {
        return BotUtils.attributeStartsWith(name, prefix, webElement);
    }

    public static boolean attributeNotStartsWith(String name, String prefix, WebElement webElement) {
        return BotUtils.attributeNotStartsWith(name, prefix, webElement);
    }

    public static boolean attributeEndsWith(String name, String suffix, WebElement webElement) {
        return BotUtils.attributeEndsWith(name, suffix, webElement);
    }

    public static boolean attributeNotEndsWith(String name, String suffix, WebElement webElement) {
        return BotUtils.attributeNotEndsWith(name, suffix, webElement);
    }

    public static boolean attributeMatches(String name, String regExp, WebElement webElement) {
        return BotUtils.attributeMatches(name, regExp, webElement);
    }

    public static boolean attributeNotMatches(String name, String regExp, WebElement webElement) {
        return BotUtils.attributeNotMatches(name, regExp, webElement);
    }

    public static void assertHasAttribute(String name, WebElement webElement) {
        BotUtils.assertHasAttribute(name, webElement);
    }

    public static void assertHasNotAttribute(String name, WebElement webElement) {
        BotUtils.assertHasNotAttribute(name, webElement);
    }

    public static void assertAttributeEquals(String name, String value, WebElement webElement) {
        BotUtils.assertAttributeEquals(name, value, webElement);
    }

    public static void assertAttributeNotEquals(String name, String value, WebElement webElement) {
        BotUtils.assertAttributeNotEquals(name, value, webElement);
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

    public static void assertAttributeMatches(String name, String regExp, WebElement webElement) {
        BotUtils.assertAttributeMatches(name, regExp, webElement);
    }

    public static void assertAttributeNotMatches(String name, String regExp, WebElement webElement) {
        BotUtils.assertAttributeNotMatches(name, regExp, webElement);
    }



    /* Id */
    public static String idIn(WebElement webElement) {
        return BotUtils.idIn(webElement);
    }

    public static boolean hasId(WebElement webElement) {
        return BotUtils.hasId(webElement);
    }

    public static boolean hasNotId(WebElement webElement) {
        return BotUtils.hasNotId(webElement);
    }

    public static boolean idEquals(String value, WebElement webElement) {
        return BotUtils.idEquals(value, webElement);
    }

    public static boolean idNotEquals(String value, WebElement webElement) {
        return BotUtils.idNotEquals(value, webElement);
    }

    public static boolean idContains(String searchText, WebElement webElement) {
        return BotUtils.idContains(searchText, webElement);
    }

    public static boolean idNotContains(String searchText, WebElement webElement) {
        return BotUtils.idNotContains(searchText, webElement);
    }

    public static boolean idStartsWith(String prefix, WebElement webElement) {
        return BotUtils.idStartsWith(prefix, webElement);
    }

    public static boolean idNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.idNotStartsWith(prefix, webElement);
    }

    public static boolean idEndsWith(String suffix, WebElement webElement) {
        return BotUtils.idEndsWith(suffix, webElement);
    }

    public static boolean idNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.idNotEndsWith(suffix, webElement);
    }

    public static boolean idMatches(String regExp, WebElement webElement) {
        return BotUtils.idMatches(regExp, webElement);
    }

    public static boolean idNotMatches(String regExp, WebElement webElement) {
        return BotUtils.idNotMatches(regExp, webElement);
    }

    public static void assertHasId(WebElement webElement) {
        BotUtils.assertHasId(webElement);
    }

    public static void assertHasNotId(WebElement webElement) {
        BotUtils.assertHasNotId(webElement);
    }

    public static void assertIdEquals(String value, WebElement webElement) {
        BotUtils.assertIdEquals(value, webElement);
    }

    public static void assertIdNotEquals(String value, WebElement webElement) {
        BotUtils.assertIdNotEquals(value, webElement);
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

    public static void assertIdMatches(String regExp, WebElement webElement) {
        BotUtils.assertIdMatches(regExp, webElement);
    }

    public static void assertIdNotMatches(String regExp, WebElement webElement) {
        BotUtils.assertIdNotMatches(regExp, webElement);
    }



    /* Name */
    public static String nameIn(WebElement webElement) {
        return BotUtils.nameIn(webElement);
    }

    public static boolean hasName(WebElement webElement) {
        return BotUtils.hasName(webElement);
    }

    public static boolean hasNotName(WebElement webElement) {
        return BotUtils.hasNotName(webElement);
    }

    public static boolean nameEquals(String value, WebElement webElement) {
        return BotUtils.nameEquals(value, webElement);
    }

    public static boolean nameNotEquals(String value, WebElement webElement) {
        return BotUtils.nameNotEquals(value, webElement);
    }

    public static boolean nameContains(String searchText, WebElement webElement) {
        return BotUtils.nameContains(searchText, webElement);
    }

    public static boolean nameNotContains(String searchText, WebElement webElement) {
        return BotUtils.nameNotContains(searchText, webElement);
    }

    public static boolean nameStartsWith(String prefix, WebElement webElement) {
        return BotUtils.nameStartsWith(prefix, webElement);
    }

    public static boolean nameNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.nameNotStartsWith(prefix, webElement);
    }

    public static boolean nameEndsWith(String suffix, WebElement webElement) {
        return BotUtils.nameEndsWith(suffix, webElement);
    }

    public static boolean nameNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.nameNotEndsWith(suffix, webElement);
    }

    public static boolean nameMatches(String regExp, WebElement webElement) {
        return BotUtils.nameMatches(regExp, webElement);
    }

    public static boolean nameNotMatches(String regExp, WebElement webElement) {
        return BotUtils.nameNotMatches(regExp, webElement);
    }

    public static void assertHasName(WebElement webElement) {
        BotUtils.assertHasName(webElement);
    }

    public static void assertHasNotName(WebElement webElement) {
        BotUtils.assertHasNotName(webElement);
    }

    public static void assertNameEquals(String value, WebElement webElement) {
        BotUtils.assertNameEquals(value, webElement);
    }

    public static void assertNameNotEquals(String value, WebElement webElement) {
        BotUtils.assertNameNotEquals(value, webElement);
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

    public static void assertNameMatches(String regExp, WebElement webElement) {
        BotUtils.assertNameMatches(regExp, webElement);
    }

    public static void assertNameNotMatches(String regExp, WebElement webElement) {
        BotUtils.assertNameNotMatches(regExp, webElement);
    }



    /* Class */
    public static String classIn(WebElement webElement) {
        return BotUtils.classIn(webElement);
    }

    public static List<String> classesIn(WebElement webElement) {
        return BotUtils.classesIn(webElement);
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

    public static boolean hasNotClassContaining(String searchText, WebElement webElement) {
        return BotUtils.hasNotClassContaining(searchText, webElement);
    }

    public static boolean hasClassStartingWith(String prefix, WebElement webElement) {
        return BotUtils.hasClassStartingWith(prefix, webElement);
    }

    public static boolean hasNotClassStartingWith(String prefix, WebElement webElement) {
        return BotUtils.hasNotClassStartingWith(prefix, webElement);
    }

    public static boolean hasClassEndingWith(String suffix, WebElement webElement) {
        return BotUtils.hasClassEndingWith(suffix, webElement);
    }

    public static boolean hasNotClassEndingWith(String suffix, WebElement webElement) {
        return BotUtils.hasNotClassEndingWith(suffix, webElement);
    }

    public static boolean hasClassMatching(String regExp, WebElement webElement) {
        return BotUtils.hasClassMatching(regExp, webElement);
    }

    public static boolean hasNotClassMatching(String regExp, WebElement webElement) {
        return BotUtils.hasNotClassMatching(regExp, webElement);
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

    public static void assertHasNotClassContaining(String searchText, WebElement webElement) {
        BotUtils.assertHasNotClassContaining(searchText, webElement);
    }

    public static void assertHasClassStartingWith(String prefix, WebElement webElement) {
        BotUtils.assertHasClassStartingWith(prefix, webElement);
    }

    public static void assertHasNotClassStartingWith(String prefix, WebElement webElement) {
        BotUtils.assertHasNotClassStartingWith(prefix, webElement);
    }

    public static void assertHasClassEndingWith(String suffix, WebElement webElement) {
        BotUtils.assertHasClassEndingWith(suffix, webElement);
    }

    public static void assertHasNotClassEndingWith(String suffix, WebElement webElement) {
        BotUtils.assertHasNotClassEndingWith(suffix, webElement);
    }

    public static void assertHasClassMatching(String regExp, WebElement webElement) {
        BotUtils.assertHasClassMatching(regExp, webElement);
    }

    public static void assertHasNotClassMatching(String regExp, WebElement webElement) {
        BotUtils.assertHasNotClassMatching(regExp, webElement);
    }



    /* Value */
    public static String valueIn(WebElement webElement) {
        return BotUtils.valueIn(webElement);
    }

    public static boolean hasValue(WebElement webElement) {
        return BotUtils.hasValue(webElement);
    }

    public static boolean hasNotValue(WebElement webElement) {
        return BotUtils.hasNotValue(webElement);
    }

    public static boolean valueEquals(String value, WebElement webElement) {
        return BotUtils.valueEquals(value, webElement);
    }

    public static boolean valueNotEquals(String value, WebElement webElement) {
        return BotUtils.valueNotEquals(value, webElement);
    }

    public static boolean valueContains(String searchText, WebElement webElement) {
        return BotUtils.valueContains(searchText, webElement);
    }

    public static boolean valueNotContains(String searchText, WebElement webElement) {
        return BotUtils.valueNotContains(searchText, webElement);
    }

    public static boolean valueStartsWith(String prefix, WebElement webElement) {
        return BotUtils.valueStartsWith(prefix, webElement);
    }

    public static boolean valueNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.valueNotStartsWith(prefix, webElement);
    }

    public static boolean valueEndsWith(String suffix, WebElement webElement) {
        return BotUtils.valueEndsWith(suffix, webElement);
    }

    public static boolean valueNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.valueNotEndsWith(suffix, webElement);
    }

    public static boolean valueMatches(String regExp, WebElement webElement) {
        return BotUtils.valueMatches(regExp, webElement);
    }

    public static boolean valueNotMatches(String regExp, WebElement webElement) {
        return BotUtils.valueNotMatches(regExp, webElement);
    }

    public static void assertHasValue(WebElement webElement) {
        BotUtils.assertHasValue(webElement);
    }

    public static void assertHasNotValue(WebElement webElement) {
        BotUtils.assertHasNotValue(webElement);
    }

    public static void assertValueEquals(String value, WebElement webElement) {
        BotUtils.assertValueEquals(value, webElement);
    }

    public static void assertValueNotEquals(String value, WebElement webElement) {
        BotUtils.assertValueNotEquals(value, webElement);
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

    public static void assertValueMatches(String regExp, WebElement webElement) {
        BotUtils.assertValueMatches(regExp, webElement);
    }

    public static void assertValueNotMatches(String regExp, WebElement webElement) {
        BotUtils.assertValueNotMatches(regExp, webElement);
    }



    /* Value Number */
    public static Double valueInAsNumber(WebElement webElement) {
        return BotUtils.valueInAsNumber(webElement);
    }

    public static boolean valueIsNumber(WebElement webElement) {
        return BotUtils.valueIsNumber(webElement);
    }

    public static boolean valueIsNotNumber(WebElement webElement) {
        return BotUtils.valueIsNotNumber(webElement);
    }

    public static boolean valueEquals(Double number, WebElement webElement) {
        return BotUtils.valueEquals(number, webElement);
    }

    public static boolean valueNotEquals(Double number, WebElement webElement) {
        return BotUtils.valueNotEquals(number, webElement);
    }

    public static boolean valueLessThan(Double number, WebElement webElement) {
        return BotUtils.valueLessThan(number, webElement);
    }

    public static boolean valueLessThanOrEquals(Double number, WebElement webElement) {
        return BotUtils.valueLessThanOrEquals(number, webElement);
    }

    public static boolean valueGreaterThan(Double number, WebElement webElement) {
        return BotUtils.valueGreaterThan(number, webElement);
    }

    public static boolean valueGreaterThanOrEquals(Double number, WebElement webElement) {
        return BotUtils.valueGreaterThanOrEquals(number, webElement);
    }

    public static void assertValueIsNumber(WebElement webElement) {
        BotUtils.assertValueIsNumber(webElement);
    }

    public static void assertValueIsNotNumber(WebElement webElement) {
        BotUtils.assertValueIsNotNumber(webElement);
    }

    public static void assertValueEquals(Double number, WebElement webElement) {
        BotUtils.assertValueEquals(number, webElement);
    }

    public static void assertValueNotEquals(Double number, WebElement webElement) {
        BotUtils.assertValueNotEquals(number, webElement);
    }

    public static void assertValueLessThan(Double number, WebElement webElement) {
        BotUtils.assertValueLessThan(number, webElement);
    }

    public static void assertValueLessThanOrEquals(Double number, WebElement webElement) {
        BotUtils.assertValueLessThanOrEquals(number, webElement);
    }

    public static void assertValueGreaterThan(Double number, WebElement webElement) {
        BotUtils.assertValueGreaterThan(number, webElement);
    }

    public static void assertValueGreaterThanOrEquals(Double number, WebElement webElement) {
        BotUtils.assertValueGreaterThanOrEquals(number, webElement);
    }



    /* Href */
    public static String hrefIn(WebElement webElement) {
        return BotUtils.hrefIn(webElement);
    }

    public static boolean hasHref(WebElement webElement) {
        return BotUtils.hasHref(webElement);
    }

    public static boolean hasNotHref(WebElement webElement) {
        return BotUtils.hasNotHref(webElement);
    }

    public static boolean hrefEquals(String value, WebElement webElement) {
        return BotUtils.hrefEquals(value, webElement);
    }

    public static boolean hrefNotEquals(String value, WebElement webElement) {
        return BotUtils.hrefNotEquals(value, webElement);
    }

    public static boolean hrefContains(String searchText, WebElement webElement) {
        return BotUtils.hrefContains(searchText, webElement);
    }

    public static boolean hrefNotContains(String searchText, WebElement webElement) {
        return BotUtils.hrefNotContains(searchText, webElement);
    }

    public static boolean hrefStartsWith(String prefix, WebElement webElement) {
        return BotUtils.hrefStartsWith(prefix, webElement);
    }

    public static boolean hrefNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.hrefNotStartsWith(prefix, webElement);
    }

    public static boolean hrefEndsWith(String suffix, WebElement webElement) {
        return BotUtils.hrefEndsWith(suffix, webElement);
    }

    public static boolean hrefNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.hrefNotEndsWith(suffix, webElement);
    }

    public static boolean hrefMatches(String regExp, WebElement webElement) {
        return BotUtils.hrefMatches(regExp, webElement);
    }

    public static boolean hrefNotMatches(String regExp, WebElement webElement) {
        return BotUtils.hrefNotMatches(regExp, webElement);
    }

    public static void assertHasHref(WebElement webElement) {
        BotUtils.assertHasHref(webElement);
    }

    public static void assertHasNotHref(WebElement webElement) {
        BotUtils.assertHasNotHref(webElement);
    }

    public static void assertHrefEquals(String value, WebElement webElement) {
        BotUtils.assertHrefEquals(value, webElement);
    }

    public static void assertHrefNotEquals(String value, WebElement webElement) {
        BotUtils.assertHrefNotEquals(value, webElement);
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

    public static void assertHrefMatches(String regExp, WebElement webElement) {
        BotUtils.assertHrefMatches(regExp, webElement);
    }

    public static void assertHrefNotMatches(String regExp, WebElement webElement) {
        BotUtils.assertHrefNotMatches(regExp, webElement);
    }



    /* Text */
    /**
     * Reads the visible text in a {@link org.openqa.selenium.WebElement}.
     *
     * <p>All spaces and other control characters are removed from the beginning
     * and the end of the {@link org.openqa.selenium.WebElement}'s text.
     * If the {@link org.openqa.selenium.WebElement}
     * does not exist in page {@code null} will be returned. The text is read using
     * {@link org.openqa.selenium.WebElement#getText()}.</p>
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
     * textIn(span) = null}</pre>
     * </p>
     * @param   webElement the {@link org.openqa.selenium.WebElement} that contains the text to read
     * @return  the read text
     *
     * @see  org.openqa.selenium.WebElement#getText()
     *
     */
    public static String textIn(WebElement webElement) {
        return BotUtils.textIn(webElement);
    }

    /**
     * Checks if there is any visible text in a {@link org.openqa.selenium.WebElement}.
     *
     * @param   webElement
     * @return  true if the webElement has any text in it, otherwise false
     */
    public static boolean hasText(WebElement webElement) {
        return BotUtils.hasText(webElement);
    }

    /**
     * Checks if there is no visible text in a {@link org.openqa.selenium.WebElement}.
     *
     * @param   webElement
     * @return  true if the webElement has any text in it, otherwise false
     */
    public static boolean hasNotText(WebElement webElement) {
        return BotUtils.hasNotText(webElement);
    }

    /**
     *  Checks if the text in a {@link org.openqa.selenium.WebElement} equals a text.
     *
     * @param text
     * @param webElement
     * @return
     */
    public static boolean textEquals(String text, WebElement webElement) {
        return BotUtils.textEquals(text, webElement);
    }

    /**
     *
     * @param text
     * @param webElement
     * @return
     */
    public static boolean textNotEquals(String text, WebElement webElement) {
        return BotUtils.textNotEquals(text, webElement);
    }

    /**
     *
     * @param searchText
     * @param webElement
     * @return
     */
    public static boolean textContains(String searchText, WebElement webElement) {
        return BotUtils.textContains(searchText, webElement);
    }

    /**
     *
     * @param searchText
     * @param webElement
     * @return
     */
    public static boolean textNotContains(String searchText, WebElement webElement) {
        return BotUtils.textNotContains(searchText, webElement);
    }

    /**
     *
     * @param prefix
     * @param webElement
     * @return
     */
    public static boolean textStartsWith(String prefix, WebElement webElement) {
        return BotUtils.textStartsWith(prefix, webElement);
    }

    /**
     *
     * @param prefix
     * @param webElement
     * @return
     */
    public static boolean textNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.textNotStartsWith(prefix, webElement);
    }

    /**
     *
     * @param suffix
     * @param webElement
     * @return
     */
    public static boolean textEndsWith(String suffix, WebElement webElement) {
        return BotUtils.textEndsWith(suffix, webElement);
    }

    /**
     *
     * @param suffix
     * @param webElement
     * @return
     */
    public static boolean textNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.textNotEndsWith(suffix, webElement);
    }

    /**
     *
     * @param regExp
     * @param webElement
     * @return
     */
    public static boolean textMatches(String regExp, WebElement webElement) {
        return BotUtils.textMatches(regExp, webElement);
    }

    /**
     *
     * @param regExp
     * @param webElement
     * @return
     */
    public static boolean textNotMatches(String regExp, WebElement webElement) {
        return BotUtils.textNotMatches(regExp, webElement);
    }

    /**
     *
     * @param webElement
     */
    public static void assertHasText(WebElement webElement) {
        BotUtils.assertHasText(webElement);
    }

    /**
     *
     * @param webElement
     */
    public static void assertHasNotText(WebElement webElement) {
        BotUtils.assertHasNotText(webElement);
    }

    /**
     *
     * @param text
     * @param webElement
     */
    public static void assertTextEquals(String text, WebElement webElement) {
        BotUtils.assertTextEquals(text, webElement);
    }

    /**
     *
     * @param text
     * @param webElement
     */
    public static void assertTextNotEquals(String text, WebElement webElement) {
        BotUtils.assertTextNotEquals(text, webElement);
    }

    /**
     *
     * @param searchText
     * @param webElement
     */
    public static void assertTextContains(String searchText, WebElement webElement) {
        BotUtils.assertTextContains(searchText, webElement);
    }

    /**
     *
     * @param searchText
     * @param webElement
     */
    public static void assertTextNotContains(String searchText, WebElement webElement) {
        BotUtils.assertTextNotContains(searchText, webElement);
    }

    /**
     *
     * @param prefix
     * @param webElement
     */
    public static void assertTextStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertTextStartsWith(prefix, webElement);
    }

    /**
     *
     * @param prefix
     * @param webElement
     */
    public static void assertTextNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertTextNotStartsWith(prefix, webElement);
    }

    /**
     *
     * @param suffix
     * @param webElement
     */
    public static void assertTextEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertTextEndsWith(suffix, webElement);
    }

    /**
     *
     * @param suffix
     * @param webElement
     */
    public static void assertTextNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertTextNotEndsWith(suffix, webElement);
    }

    /**
     *
     * @param regExp
     * @param webElement
     */
    public static void assertTextMatches(String regExp, WebElement webElement) {
        BotUtils.assertTextMatches(regExp, webElement);
    }

    /**
     *
     * @param regExp
     * @param webElement
     */
    public static void assertTextNotMatches(String regExp, WebElement webElement) {
        BotUtils.assertTextNotMatches(regExp, webElement);
    }



    /* Text Number */
    public static Double textInAsNumber(WebElement webElement) {
        return BotUtils.textInAsNumber(webElement);
    }

    public static boolean textIsNumber(WebElement webElement) {
        return BotUtils.textIsNumber(webElement);
    }

    public static boolean textIsNotNumber(WebElement webElement) {
        return BotUtils.textIsNotNumber(webElement);
    }

    public static boolean textEquals(Double number, WebElement webElement) {
        return BotUtils.textEquals(number, webElement);
    }

    public static boolean textNotEquals(Double number, WebElement webElement) {
        return BotUtils.textNotEquals(number, webElement);
    }

    public static boolean textLessThan(Double number, WebElement webElement) {
        return BotUtils.textLessThan(number, webElement);
    }

    public static boolean textLessThanOrEquals(Double number, WebElement webElement) {
        return BotUtils.textLessThanOrEquals(number, webElement);
    }

    public static boolean textGreaterThan(Double number, WebElement webElement) {
        return BotUtils.textGreaterThan(number, webElement);
    }

    public static boolean textGreaterThanOrEquals(Double number, WebElement webElement) {
        return BotUtils.textGreaterThanOrEquals(number, webElement);
    }

    public static void assertTextIsNumber(WebElement webElement) {
        BotUtils.assertTextIsNumber(webElement);
    }

    public static void assertTextIsNotNumber(WebElement webElement) {
        BotUtils.assertTextIsNotNumber(webElement);
    }

    public static void assertTextEquals(Double number, WebElement webElement) {
        BotUtils.assertTextEquals(number, webElement);
    }

    public static void assertTextNotEquals(Double number, WebElement webElement) {
        BotUtils.assertTextNotEquals(number, webElement);
    }

    public static void assertTextLessThan(Double number, WebElement webElement) {
        BotUtils.assertTextLessThan(number, webElement);
    }

    public static void assertTextLessThanOrEquals(Double number, WebElement webElement) {
        BotUtils.assertTextLessThanOrEquals(number, webElement);
    }

    public static void assertTextGreaterThan(Double number, WebElement webElement) {
        BotUtils.assertTextGreaterThan(number, webElement);
    }

    public static void assertTextGreaterThanOrEquals(Double number, WebElement webElement) {
        BotUtils.assertTextGreaterThanOrEquals(number, webElement);
    }



    /* Selected/Deselected */
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



    /* Checked/Unchecked */
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



    /* Enabled/Disabled */
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



    /* Option */
    public static boolean hasOption(String text, WebElement webElement) {
        return BotUtils.hasOption(text, webElement);
    }

    public static boolean hasNotOption(String text, WebElement webElement) {
        return BotUtils.hasNotOption(text, webElement);
    }

    public static boolean optionIsEnabled(String text, WebElement webElement) {
        return BotUtils.optionIsEnabled(text, webElement);
    }

    public static boolean optionIsDisabled(String text, WebElement webElement) {
        return BotUtils.optionIsDisabled(text, webElement);
    }

    public static boolean optionIsSelected(String text, WebElement webElement) {
        return BotUtils.optionIsSelected(text, webElement);
    }

    public static boolean optionIsDeselected(String text, WebElement webElement) {
        return BotUtils.optionIsDeselected(text, webElement);
    }

    public static boolean allOptionsAreSelected(WebElement webElement) {
        return BotUtils.allOptionsAreSelected(webElement);
    }

    public static boolean noOptionIsSelected(WebElement webElement) {
        return BotUtils.noOptionIsSelected(webElement);
    }

    public static void assertHasOption(String text, WebElement webElement) {
        BotUtils.assertHasOption(text, webElement);
    }

    public static void assertHasNotOption(String text, WebElement webElement) {
        BotUtils.assertHasNotOption(text, webElement);
    }

    public static void assertOptionIsEnabled(String text, WebElement webElement) {
        BotUtils.assertOptionIsEnabled(text, webElement);
    }

    public static void assertOptionIsDisabled(String text, WebElement webElement) {
        BotUtils.assertOptionIsDisabled(text, webElement);
    }

    public static void assertOptionIsSelected(String text, WebElement webElement) {
        BotUtils.assertOptionIsSelected(text, webElement);
    }

    public static void assertOptionIsDeselected(String text, WebElement webElement) {
        BotUtils.assertOptionIsDeselected(text, webElement);
    }

    public static void assertAllOptionsAreSelected(WebElement webElement) {
        BotUtils.assertAllOptionsAreSelected(webElement);
    }

    public static void assertNoOptionIsSelected(WebElement webElement) {
        BotUtils.assertNoOptionIsSelected(webElement);
    }



    /* Option Value */
    public static boolean hasOptionWithValue(String value, WebElement webElement) {
        return BotUtils.hasOptionWithValue(value, webElement);
    }

    public static boolean hasNotOptionWithValue(String value, WebElement webElement) {
        return BotUtils.hasNotOptionWithValue(value, webElement);
    }

    public static boolean optionWithValueIsEnabled(String value, WebElement webElement) {
        return BotUtils.optionWithValueIsEnabled(value, webElement);
    }

    public static boolean optionWithValueIsDisabled(String value, WebElement webElement) {
        return BotUtils.optionWithValueIsDisabled(value, webElement);
    }

    public static boolean optionWithValueIsSelected(String value, WebElement webElement) {
        return BotUtils.optionWithValueIsSelected(value, webElement);
    }

    public static boolean optionWithValueIsDeselected(String value, WebElement webElement) {
        return BotUtils.optionWithValueIsDeselected(value, webElement);
    }

    public static void assertHasOptionWithValue(String value, WebElement webElement) {
        BotUtils.assertHasOptionWithValue(value, webElement);
    }

    public static void assertHasNotOptionWithValue(String value, WebElement webElement) {
        BotUtils.assertHasNotOptionWithValue(value, webElement);
    }

    public static void assertOptionWithValueIsEnabled(String value, WebElement webElement) {
        BotUtils.assertOptionWithValueIsEnabled(value, webElement);
    }

    public static void assertOptionWithValueIsDisabled(String value, WebElement webElement) {
        BotUtils.assertOptionWithValueIsDisabled(value, webElement);
    }

    public static void assertOptionWithValueIsSelected(String value, WebElement webElement) {
        BotUtils.assertOptionWithValueIsSelected(value, webElement);
    }

    public static void assertOptionWithValueIsDeselected(String value, WebElement webElement) {
        BotUtils.assertOptionWithValueIsDeselected(value, webElement);
    }



    /* Option Index */
    public static boolean hasOptionWithIndex(int index, WebElement webElement) {
        return BotUtils.hasOptionWithIndex(index, webElement);
    }

    public static boolean hasNotOptionWithIndex(int index, WebElement webElement) {
        return BotUtils.hasNotOptionWithIndex(index, webElement);
    }

    public static boolean optionWithIndexIsEnabled(int index, WebElement webElement) {
        return BotUtils.optionWithIndexIsEnabled(index, webElement);
    }

    public static boolean optionWithIndexIsDisabled(int index, WebElement webElement) {
        return BotUtils.optionWithIndexIsDisabled(index, webElement);
    }

    public static boolean optionWithIndexIsSelected(int index, WebElement webElement) {
        return BotUtils.optionWithIndexIsSelected(index, webElement);
    }

    public static boolean optionWithIndexIsDeselected(int index, WebElement webElement) {
        return BotUtils.optionWithIndexIsDeselected(index, webElement);
    }

    public static void assertHasOptionWithIndex(int index, WebElement webElement) {
        BotUtils.assertHasOptionWithIndex(index, webElement);
    }

    public static void assertHasNotOptionWithIndex(int index, WebElement webElement) {
        BotUtils.assertHasNotOptionWithIndex(index, webElement);
    }

    public static void assertOptionWithIndexIsEnabled(int index, WebElement webElement) {
        BotUtils.assertOptionWithIndexIsEnabled(index, webElement);
    }

    public static void assertOptionWithIndexIsDisabled(int index, WebElement webElement) {
        BotUtils.assertOptionWithIndexIsDisabled(index, webElement);
    }

    public static void assertOptionWithIndexIsSelected(int index, WebElement webElement) {
        BotUtils.assertOptionWithIndexIsSelected(index, webElement);
    }

    public static void assertOptionWithIndexIsDeselected(int index, WebElement webElement) {
        BotUtils.assertOptionWithIndexIsDeselected(index, webElement);
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
