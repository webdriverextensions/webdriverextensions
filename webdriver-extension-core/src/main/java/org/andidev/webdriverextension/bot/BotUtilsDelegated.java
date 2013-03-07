package org.andidev.webdriverextension.bot;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.andidev.webdriverextension.Openable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BotUtilsDelegated {

    BotUtilsNonStatic bot;

    public BotUtilsDelegated() {
    }

    public String read(WebElement webElement) {
        return bot.read(webElement);
    }

    public Double readNumber(WebElement webElement) {
        return bot.readNumber(webElement);
    }

    public List<String> readOptions(WebElement webElement) {
        return bot.readOptions(webElement);
    }

    public String readUrl(WebDriver driver) {
        return bot.readUrl(driver);
    }

    public String readTagName(WebElement webElement) {
        return bot.readTagName(webElement);
    }

    public String readAttribute(String name, WebElement webElement) {
        return bot.readAttribute(name, webElement);
    }

    public String readId(WebElement webElement) {
        return bot.readId(webElement);
    }

    public String readName(WebElement webElement) {
        return bot.readName(webElement);
    }

    public String readClass(WebElement webElement) {
        return bot.readClass(webElement);
    }

    public List<String> readClasses(WebElement webElement) {
        return bot.readClasses(webElement);
    }

    public String readValue(WebElement webElement) {
        return bot.readValue(webElement);
    }

    public String readHref(WebElement webElement) {
        return bot.readHref(webElement);
    }

    public void clear(WebElement webElement) {
        bot.clear(webElement);
    }

    public void type(String text, WebElement webElement) {
        bot.type(text, webElement);
    }

    public void typeNumber(Double number, WebElement webElement) {
        bot.typeNumber(number, webElement);
    }

    public void clearAndType(String text, WebElement webElement) {
        bot.clearAndType(text, webElement);
    }

    public void clearAndTypeNumber(Double number, WebElement webElement) {
        bot.clearAndTypeNumber(number, webElement);
    }

    public void pressEnter(WebElement webElement) {
        bot.pressEnter(webElement);
    }

    public void pressKeys(WebElement webElement, CharSequence... keys) {
        bot.pressKeys(webElement, keys);
    }

    public void click(WebElement webElement) {
        bot.click(webElement);
    }

    public void select(WebElement webElement) {
        bot.select(webElement);
    }

    public void deselect(WebElement webElement) {
        bot.deselect(webElement);
    }

    public void selectOption(String text, WebElement webElement) {
        bot.selectOption(text, webElement);
    }

    public void deselectOption(String text, WebElement webElement) {
        bot.deselectOption(text, webElement);
    }

    public void selectAllOptions(WebElement webElement) {
        bot.selectAllOptions(webElement);
    }

    public void deselectAllOptions(WebElement webElement) {
        bot.deselectAllOptions(webElement);
    }

    public void selectOptionWithValue(String value, WebElement webElement) {
        bot.selectOptionWithValue(value, webElement);
    }

    public void deselectOptionWithValue(String value, WebElement webElement) {
        bot.deselectOptionWithValue(value, webElement);
    }

    public void selectOptionWithIndex(int index, WebElement webElement) {
        bot.selectOptionWithIndex(index, webElement);
    }

    public void deselectOptionWithIndex(int index, WebElement webElement) {
        bot.deselectOptionWithIndex(index, webElement);
    }

    public void check(WebElement webElement) {
        bot.check(webElement);
    }

    public void uncheck(WebElement webElement) {
        bot.uncheck(webElement);
    }

    public void open(String url, WebDriver driver) {
        bot.open(url, driver);
    }

    public void open(Openable openable) {
        bot.open(openable);
    }

    public int count(List<? extends WebElement> webElements) {
        return bot.count(webElements);
    }

    public void delay(double seconds) {
        bot.delay(seconds);
    }

    public void delay(long time, TimeUnit unit) {
        bot.delay(time, unit);
    }

    public void waitForElementToDisplay(WebElement webElement, WebDriver driver) {
        bot.waitForElementToDisplay(webElement, driver);
    }

    public void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds, WebDriver driver) {
        bot.waitForElementToDisplay(webElement, timeOutInSeconds, driver);
    }

    public void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds, long sleepInMillis, WebDriver driver) {
        bot.waitForElementToDisplay(webElement, timeOutInSeconds, sleepInMillis, driver);
    }

    public void debug(String str) {
        bot.debug(str);
    }

    public void debug(WebElement webElement) {
        bot.debug(webElement);
    }

    public void debug(List<? extends WebElement> webElements) {
        bot.debug(webElements);
    }

    public void debugText(WebElement webElement) {
        bot.debugText(webElement);
    }

    public void debugText(List<? extends WebElement> webElements) {
        bot.debugText(webElements);
    }

    public void debugNumberOfElements(List<? extends WebElement> webElements) {
        bot.debugNumberOfElements(webElements);
    }

    public boolean isTagName(String value, WebElement webElement) {
        return bot.isTagName(value, webElement);
    }

    public boolean isTagNameNot(String value, WebElement webElement) {
        return bot.isTagNameNot(value, webElement);
    }

    public void assertTagName(String value, WebElement webElement) {
        bot.assertTagName(value, webElement);
    }

    public void assertTagNameNot(String value, WebElement webElement) {
        bot.assertTagNameNot(value, webElement);
    }

    public boolean hasAttribute(String name, WebElement webElement) {
        return bot.hasAttribute(name, webElement);
    }

    public boolean hasNotAttribute(String name, WebElement webElement) {
        return bot.hasNotAttribute(name, webElement);
    }

    public boolean isAttribute(String name, String value, WebElement webElement) {
        return bot.isAttribute(name, value, webElement);
    }

    public boolean isAttributeNot(String name, String value, WebElement webElement) {
        return bot.isAttributeNot(name, value, webElement);
    }

    public boolean isAttributeContaining(String name, String searchText, WebElement webElement) {
        return bot.isAttributeContaining(name, searchText, webElement);
    }

    public boolean isAttributeNotContaining(String name, String searchText, WebElement webElement) {
        return bot.isAttributeNotContaining(name, searchText, webElement);
    }

    public boolean isAttributeStartingWith(String name, String prefix, WebElement webElement) {
        return bot.isAttributeStartingWith(name, prefix, webElement);
    }

    public boolean isAttributeNotStartingWith(String name, String prefix, WebElement webElement) {
        return bot.isAttributeNotStartingWith(name, prefix, webElement);
    }

    public boolean isAttributeEndingWith(String name, String suffix, WebElement webElement) {
        return bot.isAttributeEndingWith(name, suffix, webElement);
    }

    public boolean isAttributeNotEndingWith(String name, String suffix, WebElement webElement) {
        return bot.isAttributeNotEndingWith(name, suffix, webElement);
    }

    public void assertHasAttribute(String name, WebElement webElement) {
        bot.assertHasAttribute(name, webElement);
    }

    public void assertHasNotAttribute(String name, WebElement webElement) {
        bot.assertHasNotAttribute(name, webElement);
    }

    public void assertAttribute(String name, String value, WebElement webElement) {
        bot.assertAttribute(name, value, webElement);
    }

    public void assertAttributeNot(String name, String value, WebElement webElement) {
        bot.assertAttributeNot(name, value, webElement);
    }

    public void assertAttributeContains(String name, String searchText, WebElement webElement) {
        bot.assertAttributeContains(name, searchText, webElement);
    }

    public void assertAttributeNotContains(String name, String searchText, WebElement webElement) {
        bot.assertAttributeNotContains(name, searchText, webElement);
    }

    public void assertAttributeStartsWith(String name, String prefix, WebElement webElement) {
        bot.assertAttributeStartsWith(name, prefix, webElement);
    }

    public void assertAttributeNotStartsWith(String name, String prefix, WebElement webElement) {
        bot.assertAttributeNotStartsWith(name, prefix, webElement);
    }

    public void assertAttributeEndsWith(String name, String suffix, WebElement webElement) {
        bot.assertAttributeEndsWith(name, suffix, webElement);
    }

    public void assertAttributeNotEndsWith(String name, String suffix, WebElement webElement) {
        bot.assertAttributeNotEndsWith(name, suffix, webElement);
    }

    public boolean hasId(WebElement webElement) {
        return bot.hasId(webElement);
    }

    public boolean hasNotId(WebElement webElement) {
        return bot.hasNotId(webElement);
    }

    public boolean isId(String value, WebElement webElement) {
        return bot.isId(value, webElement);
    }

    public boolean isIdNot(String value, WebElement webElement) {
        return bot.isIdNot(value, webElement);
    }

    public boolean isIdContaining(String searchText, WebElement webElement) {
        return bot.isIdContaining(searchText, webElement);
    }

    public boolean isIdNotContaining(String searchText, WebElement webElement) {
        return bot.isIdNotContaining(searchText, webElement);
    }

    public boolean isIdStartingWith(String prefix, WebElement webElement) {
        return bot.isIdStartingWith(prefix, webElement);
    }

    public boolean isIdNotStartingWith(String prefix, WebElement webElement) {
        return bot.isIdNotStartingWith(prefix, webElement);
    }

    public boolean isIdEndingWith(String suffix, WebElement webElement) {
        return bot.isIdEndingWith(suffix, webElement);
    }

    public boolean isIdNotEndingWith(String suffix, WebElement webElement) {
        return bot.isIdNotEndingWith(suffix, webElement);
    }

    public void assertHasId(WebElement webElement) {
        bot.assertHasId(webElement);
    }

    public void assertHasNotId(WebElement webElement) {
        bot.assertHasNotId(webElement);
    }

    public void assertId(String value, WebElement webElement) {
        bot.assertId(value, webElement);
    }

    public void assertIdNot(String value, WebElement webElement) {
        bot.assertIdNot(value, webElement);
    }

    public void assertIdContains(String searchText, WebElement webElement) {
        bot.assertIdContains(searchText, webElement);
    }

    public void assertIdNotContains(String searchText, WebElement webElement) {
        bot.assertIdNotContains(searchText, webElement);
    }

    public void assertIdStartsWith(String prefix, WebElement webElement) {
        bot.assertIdStartsWith(prefix, webElement);
    }

    public void assertIdNotStartsWith(String prefix, WebElement webElement) {
        bot.assertIdNotStartsWith(prefix, webElement);
    }

    public void assertIdEndsWith(String suffix, WebElement webElement) {
        bot.assertIdEndsWith(suffix, webElement);
    }

    public void assertIdNotEndsWith(String suffix, WebElement webElement) {
        bot.assertIdNotEndsWith(suffix, webElement);
    }

    public boolean hasName(WebElement webElement) {
        return bot.hasName(webElement);
    }

    public boolean hasNotName(WebElement webElement) {
        return bot.hasNotName(webElement);
    }

    public boolean isName(String value, WebElement webElement) {
        return bot.isName(value, webElement);
    }

    public boolean isNameNot(String value, WebElement webElement) {
        return bot.isNameNot(value, webElement);
    }

    public boolean isNameContaining(String searchText, WebElement webElement) {
        return bot.isNameContaining(searchText, webElement);
    }

    public boolean isNameNotContaining(String searchText, WebElement webElement) {
        return bot.isNameNotContaining(searchText, webElement);
    }

    public boolean isNameStartingWith(String prefix, WebElement webElement) {
        return bot.isNameStartingWith(prefix, webElement);
    }

    public boolean isNameNotStartingWith(String prefix, WebElement webElement) {
        return bot.isNameNotStartingWith(prefix, webElement);
    }

    public boolean isNameEndingWith(String suffix, WebElement webElement) {
        return bot.isNameEndingWith(suffix, webElement);
    }

    public boolean isNameNotEndingWith(String suffix, WebElement webElement) {
        return bot.isNameNotEndingWith(suffix, webElement);
    }

    public void assertHasName(WebElement webElement) {
        bot.assertHasName(webElement);
    }

    public void assertHasNotName(WebElement webElement) {
        bot.assertHasNotName(webElement);
    }

    public void assertName(String value, WebElement webElement) {
        bot.assertName(value, webElement);
    }

    public void assertNameNot(String value, WebElement webElement) {
        bot.assertNameNot(value, webElement);
    }

    public void assertNameContains(String searchText, WebElement webElement) {
        bot.assertNameContains(searchText, webElement);
    }

    public void assertNameNotContains(String searchText, WebElement webElement) {
        bot.assertNameNotContains(searchText, webElement);
    }

    public void assertNameStartsWith(String prefix, WebElement webElement) {
        bot.assertNameStartsWith(prefix, webElement);
    }

    public void assertNameNotStartsWith(String prefix, WebElement webElement) {
        bot.assertNameNotStartsWith(prefix, webElement);
    }

    public void assertNameEndsWith(String suffix, WebElement webElement) {
        bot.assertNameEndsWith(suffix, webElement);
    }

    public void assertNameNotEndsWith(String suffix, WebElement webElement) {
        bot.assertNameNotEndsWith(suffix, webElement);
    }

    public boolean hasClass(WebElement webElement) {
        return bot.hasClass(webElement);
    }

    public boolean hasNotClass(WebElement webElement) {
        return bot.hasNotClass(webElement);
    }

    public boolean hasClass(String className, WebElement webElement) {
        return bot.hasClass(className, webElement);
    }

    public boolean hasNotClass(String className, WebElement webElement) {
        return bot.hasNotClass(className, webElement);
    }

    public boolean hasClassContaining(String searchText, WebElement webElement) {
        return bot.hasClassContaining(searchText, webElement);
    }

    public boolean hasClassNotContaining(String searchText, WebElement webElement) {
        return bot.hasClassNotContaining(searchText, webElement);
    }

    public boolean hasClassStartingWith(String prefix, WebElement webElement) {
        return bot.hasClassStartingWith(prefix, webElement);
    }

    public boolean hasClassNotStartingWith(String prefix, WebElement webElement) {
        return bot.hasClassNotStartingWith(prefix, webElement);
    }

    public boolean hasClassEndingWith(String suffix, WebElement webElement) {
        return bot.hasClassEndingWith(suffix, webElement);
    }

    public boolean hasClassNotEndingWith(String suffix, WebElement webElement) {
        return bot.hasClassNotEndingWith(suffix, webElement);
    }

    public void assertHasClass(WebElement webElement) {
        bot.assertHasClass(webElement);
    }

    public void assertHasNotClass(WebElement webElement) {
        bot.assertHasNotClass(webElement);
    }

    public void assertHasClass(String className, WebElement webElement) {
        bot.assertHasClass(className, webElement);
    }

    public void assertHasNotClass(String className, WebElement webElement) {
        bot.assertHasNotClass(className, webElement);
    }

    public void assertHasClassContains(String searchText, WebElement webElement) {
        bot.assertHasClassContains(searchText, webElement);
    }

    public void assertHasClassNotContains(String searchText, WebElement webElement) {
        bot.assertHasClassNotContains(searchText, webElement);
    }

    public void assertHasClassStartsWith(String prefix, WebElement webElement) {
        bot.assertHasClassStartsWith(prefix, webElement);
    }

    public void assertHasClassNotStartsWith(String prefix, WebElement webElement) {
        bot.assertHasClassNotStartsWith(prefix, webElement);
    }

    public void assertHasClassEndsWith(String suffix, WebElement webElement) {
        bot.assertHasClassEndsWith(suffix, webElement);
    }

    public void assertHasClassNotEndsWith(String suffix, WebElement webElement) {
        bot.assertHasClassNotEndsWith(suffix, webElement);
    }

    public boolean hasValue(WebElement webElement) {
        return bot.hasValue(webElement);
    }

    public boolean hasNotValue(WebElement webElement) {
        return bot.hasNotValue(webElement);
    }

    public boolean isValue(String value, WebElement webElement) {
        return bot.isValue(value, webElement);
    }

    public boolean isValueNot(String value, WebElement webElement) {
        return bot.isValueNot(value, webElement);
    }

    public boolean isValueContaining(String searchText, WebElement webElement) {
        return bot.isValueContaining(searchText, webElement);
    }

    public boolean isValueNotContaining(String searchText, WebElement webElement) {
        return bot.isValueNotContaining(searchText, webElement);
    }

    public boolean isValueStartingWith(String prefix, WebElement webElement) {
        return bot.isValueStartingWith(prefix, webElement);
    }

    public boolean isValueNotStartingWith(String prefix, WebElement webElement) {
        return bot.isValueNotStartingWith(prefix, webElement);
    }

    public boolean isValueEndingWith(String suffix, WebElement webElement) {
        return bot.isValueEndingWith(suffix, webElement);
    }

    public boolean isValueNotEndingWith(String suffix, WebElement webElement) {
        return bot.isValueNotEndingWith(suffix, webElement);
    }

    public void assertHasValue(WebElement webElement) {
        bot.assertHasValue(webElement);
    }

    public void assertHasNotValue(WebElement webElement) {
        bot.assertHasNotValue(webElement);
    }

    public void assertValue(String value, WebElement webElement) {
        bot.assertValue(value, webElement);
    }

    public void assertValueNot(String value, WebElement webElement) {
        bot.assertValueNot(value, webElement);
    }

    public void assertValueContains(String searchText, WebElement webElement) {
        bot.assertValueContains(searchText, webElement);
    }

    public void assertValueNotContains(String searchText, WebElement webElement) {
        bot.assertValueNotContains(searchText, webElement);
    }

    public void assertValueStartsWith(String prefix, WebElement webElement) {
        bot.assertValueStartsWith(prefix, webElement);
    }

    public void assertValueNotStartsWith(String prefix, WebElement webElement) {
        bot.assertValueNotStartsWith(prefix, webElement);
    }

    public void assertValueEndsWith(String suffix, WebElement webElement) {
        bot.assertValueEndsWith(suffix, webElement);
    }

    public void assertValueNotEndsWith(String suffix, WebElement webElement) {
        bot.assertValueNotEndsWith(suffix, webElement);
    }

    public boolean hasHref(WebElement webElement) {
        return bot.hasHref(webElement);
    }

    public boolean hasNotHref(WebElement webElement) {
        return bot.hasNotHref(webElement);
    }

    public boolean isHref(String value, WebElement webElement) {
        return bot.isHref(value, webElement);
    }

    public boolean isHrefNot(String value, WebElement webElement) {
        return bot.isHrefNot(value, webElement);
    }

    public boolean isHrefContaining(String searchText, WebElement webElement) {
        return bot.isHrefContaining(searchText, webElement);
    }

    public boolean isHrefNotContaining(String searchText, WebElement webElement) {
        return bot.isHrefNotContaining(searchText, webElement);
    }

    public boolean isHrefStartingWith(String prefix, WebElement webElement) {
        return bot.isHrefStartingWith(prefix, webElement);
    }

    public boolean isHrefNotStartingWith(String prefix, WebElement webElement) {
        return bot.isHrefNotStartingWith(prefix, webElement);
    }

    public boolean isHrefEndingWith(String suffix, WebElement webElement) {
        return bot.isHrefEndingWith(suffix, webElement);
    }

    public boolean isHrefNotEndingWith(String suffix, WebElement webElement) {
        return bot.isHrefNotEndingWith(suffix, webElement);
    }

    public void assertHasHref(WebElement webElement) {
        bot.assertHasHref(webElement);
    }

    public void assertHasNotHref(WebElement webElement) {
        bot.assertHasNotHref(webElement);
    }

    public void assertHref(String value, WebElement webElement) {
        bot.assertHref(value, webElement);
    }

    public void assertHrefNot(String value, WebElement webElement) {
        bot.assertHrefNot(value, webElement);
    }

    public void assertHrefContains(String searchText, WebElement webElement) {
        bot.assertHrefContains(searchText, webElement);
    }

    public void assertHrefNotContains(String searchText, WebElement webElement) {
        bot.assertHrefNotContains(searchText, webElement);
    }

    public void assertHrefStartsWith(String prefix, WebElement webElement) {
        bot.assertHrefStartsWith(prefix, webElement);
    }

    public void assertHrefNotStartsWith(String prefix, WebElement webElement) {
        bot.assertHrefNotStartsWith(prefix, webElement);
    }

    public void assertHrefEndsWith(String suffix, WebElement webElement) {
        bot.assertHrefEndsWith(suffix, webElement);
    }

    public void assertHrefNotEndsWith(String suffix, WebElement webElement) {
        bot.assertHrefNotEndsWith(suffix, webElement);
    }

    public boolean isText(String text, WebElement webElement) {
        return bot.isText(text, webElement);
    }

    public boolean isTextNot(String text, WebElement webElement) {
        return bot.isTextNot(text, webElement);
    }

    public boolean isTextContaining(String searchText, WebElement webElement) {
        return bot.isTextContaining(searchText, webElement);
    }

    public boolean isTextNotContaining(String searchText, WebElement webElement) {
        return bot.isTextNotContaining(searchText, webElement);
    }

    public boolean isTextStartingWith(String prefix, WebElement webElement) {
        return bot.isTextStartingWith(prefix, webElement);
    }

    public boolean isTextNotStartingWith(String prefix, WebElement webElement) {
        return bot.isTextNotStartingWith(prefix, webElement);
    }

    public boolean isTextEndingWith(String suffix, WebElement webElement) {
        return bot.isTextEndingWith(suffix, webElement);
    }

    public boolean isTextNotEndingWith(String suffix, WebElement webElement) {
        return bot.isTextNotEndingWith(suffix, webElement);
    }

    public void assertText(String text, WebElement webElement) {
        bot.assertText(text, webElement);
    }

    public void assertTextNot(String text, WebElement webElement) {
        bot.assertTextNot(text, webElement);
    }

    public void assertTextContains(String searchText, WebElement webElement) {
        bot.assertTextContains(searchText, webElement);
    }

    public void assertTextNotContains(String searchText, WebElement webElement) {
        bot.assertTextNotContains(searchText, webElement);
    }

    public void assertTextStartsWith(String prefix, WebElement webElement) {
        bot.assertTextStartsWith(prefix, webElement);
    }

    public void assertTextNotStartsWith(String prefix, WebElement webElement) {
        bot.assertTextNotStartsWith(prefix, webElement);
    }

    public void assertTextEndsWith(String suffix, WebElement webElement) {
        bot.assertTextEndsWith(suffix, webElement);
    }

    public void assertTextNotEndsWith(String suffix, WebElement webElement) {
        bot.assertTextNotEndsWith(suffix, webElement);
    }

    public boolean isNumber(Double number, WebElement webElement) {
        return bot.isNumber(number, webElement);
    }

    public boolean isNumberNot(Double number, WebElement webElement) {
        return bot.isNumberNot(number, webElement);
    }

    public boolean isNumberSmallerThan(Double number, WebElement webElement) {
        return bot.isNumberSmallerThan(number, webElement);
    }

    public boolean isNumberSmallerThanOrEquals(Double number, WebElement webElement) {
        return bot.isNumberSmallerThanOrEquals(number, webElement);
    }

    public boolean isNumberLargerThan(Double number, WebElement webElement) {
        return bot.isNumberLargerThan(number, webElement);
    }

    public boolean isNumberLargerThanOrEquals(Double number, WebElement webElement) {
        return bot.isNumberLargerThanOrEquals(number, webElement);
    }

    public void assertNumber(Double number, WebElement webElement) {
        bot.assertNumber(number, webElement);
    }

    public void assertNumberNot(Double number, WebElement webElement) {
        bot.assertNumberNot(number, webElement);
    }

    public void assertNumberSmallerThan(Double number, WebElement webElement) {
        bot.assertNumberSmallerThan(number, webElement);
    }

    public void assertNumberSmallerThanOrEquals(Double number, WebElement webElement) {
        bot.assertNumberSmallerThanOrEquals(number, webElement);
    }

    public void assertNumberLargerThan(Double number, WebElement webElement) {
        bot.assertNumberLargerThan(number, webElement);
    }

    public void assertNumberLargerThanOrEquals(Double number, WebElement webElement) {
        bot.assertNumberLargerThanOrEquals(number, webElement);
    }

    public boolean isUrl(String url, WebDriver driver) {
        return bot.isUrl(url, driver);
    }

    public boolean isUrlNot(String url, WebDriver driver) {
        return bot.isUrlNot(url, driver);
    }

    public boolean isUrlMatching(String regExp, WebDriver driver) {
        return bot.isUrlMatching(regExp, driver);
    }

    public boolean isUrlNotMatching(String regExp, WebDriver driver) {
        return bot.isUrlNotMatching(regExp, driver);
    }

    public boolean isUrlMatching(Openable openable, WebDriver driver) {
        return bot.isUrlMatching(openable, driver);
    }

    public boolean isUrlNotMatching(Openable openable, WebDriver driver) {
        return bot.isUrlNotMatching(openable, driver);
    }

    public boolean isUrlContaining(String searchText, WebDriver driver) {
        return bot.isUrlContaining(searchText, driver);
    }

    public boolean isUrlNotContaining(String searchText, WebDriver driver) {
        return bot.isUrlNotContaining(searchText, driver);
    }

    public boolean isUrlStartingWidth(String prefix, WebDriver driver) {
        return bot.isUrlStartingWidth(prefix, driver);
    }

    public boolean isUrlNotStartingWidth(String prefix, WebDriver driver) {
        return bot.isUrlNotStartingWidth(prefix, driver);
    }

    public boolean isUrlEndingWidth(String suffix, WebDriver driver) {
        return bot.isUrlEndingWidth(suffix, driver);
    }

    public boolean isUrlNotEndingWidth(String suffix, WebDriver driver) {
        return bot.isUrlNotEndingWidth(suffix, driver);
    }

    public void assertUrl(String url, WebDriver driver) {
        bot.assertUrl(url, driver);
    }

    public void assertUrlNot(String url, WebDriver driver) {
        bot.assertUrlNot(url, driver);
    }

    public void assertUrlMatching(String regExp, WebDriver driver) {
        bot.assertUrlMatching(regExp, driver);
    }

    public void assertUrlNotMatching(String regExp, WebDriver driver) {
        bot.assertUrlNotMatching(regExp, driver);
    }

    public void assertUrlMatching(Openable openable, WebDriver driver) {
        bot.assertUrlMatching(openable, driver);
    }

    public void assertUrlNotMatching(Openable openable, WebDriver driver) {
        bot.assertUrlNotMatching(openable, driver);
    }

    public void assertUrlContains(String searchText, WebDriver driver) {
        bot.assertUrlContains(searchText, driver);
    }

    public void assertUrlNotContains(String searchText, WebDriver driver) {
        bot.assertUrlNotContains(searchText, driver);
    }

    public void assertUrlStartsWidth(String prefix, WebDriver driver) {
        bot.assertUrlStartsWidth(prefix, driver);
    }

    public void assertUrlNotStartsWidth(String prefix, WebDriver driver) {
        bot.assertUrlNotStartsWidth(prefix, driver);
    }

    public void assertUrlEndsWidth(String suffix, WebDriver driver) {
        bot.assertUrlEndsWidth(suffix, driver);
    }

    public void assertUrlNotEndsWidth(String suffix, WebDriver driver) {
        bot.assertUrlNotEndsWidth(suffix, driver);
    }

    public boolean isOpen(Openable openable) {
        return bot.isOpen(openable);
    }

    public boolean isNotOpen(Openable openable) {
        return bot.isNotOpen(openable);
    }

    public void assertIsOpen(Openable openable) {
        bot.assertIsOpen(openable);
    }

    public void assertIsNotOpen(Openable openable) {
        bot.assertIsNotOpen(openable);
    }

    public boolean isSelected(WebElement webElement) {
        return bot.isSelected(webElement);
    }

    public boolean isDeselected(WebElement webElement) {
        return bot.isDeselected(webElement);
    }

    public void assertIsSelected(WebElement webElement) {
        bot.assertIsSelected(webElement);
    }

    public void assertIsDeselected(WebElement webElement) {
        bot.assertIsDeselected(webElement);
    }

    public boolean isChecked(WebElement webElement) {
        return bot.isChecked(webElement);
    }

    public boolean isUnchecked(WebElement webElement) {
        return bot.isUnchecked(webElement);
    }

    public void assertIsChecked(WebElement webElement) {
        bot.assertIsChecked(webElement);
    }

    public void assertIsUnchecked(WebElement webElement) {
        bot.assertIsUnchecked(webElement);
    }

    public boolean isEnabled(WebElement webElement) {
        return bot.isEnabled(webElement);
    }

    public boolean isDisabled(WebElement webElement) {
        return bot.isDisabled(webElement);
    }

    public void assertIsEnabled(WebElement webElement) {
        bot.assertIsEnabled(webElement);
    }

    public void assertIsDisabled(WebElement webElement) {
        bot.assertIsDisabled(webElement);
    }

    public boolean isDisplayed(WebElement webElement) {
        return bot.isDisplayed(webElement);
    }

    public boolean isNotDisplayed(WebElement webElement) {
        return bot.isNotDisplayed(webElement);
    }

    public void assertIsDisplayed(WebElement webElement) {
        bot.assertIsDisplayed(webElement);
    }

    public void assertIsNotDisplayed(WebElement webElement) {
        bot.assertIsNotDisplayed(webElement);
    }

    public boolean isNumberOfElements(int number, List<? extends WebElement> webElements) {
        return bot.isNumberOfElements(number, webElements);
    }

    public boolean isNumberOfElementsNot(int number, List<? extends WebElement> webElements) {
        return bot.isNumberOfElementsNot(number, webElements);
    }

    public boolean isNumberOfElementsSmallerThan(int number, List<? extends WebElement> webElements) {
        return bot.isNumberOfElementsSmallerThan(number, webElements);
    }

    public boolean isNumberOfElementsSmallerThanOrEquals(int number, List<? extends WebElement> webElements) {
        return bot.isNumberOfElementsSmallerThanOrEquals(number, webElements);
    }

    public boolean isNumberOfElementsLargerThan(int number, List<? extends WebElement> webElements) {
        return bot.isNumberOfElementsLargerThan(number, webElements);
    }

    public boolean isNumberOfElementsLargerThanOrEquals(int number, List<? extends WebElement> webElements) {
        return bot.isNumberOfElementsLargerThanOrEquals(number, webElements);
    }

    public void assertNumberOfElements(int number, List<? extends WebElement> webElements) {
        bot.assertNumberOfElements(number, webElements);
    }

    public void assertNumberOfElementsNot(int number, List<? extends WebElement> webElements) {
        bot.assertNumberOfElementsNot(number, webElements);
    }

    public void assertNumberOfElementsSmallerThan(int number, List<? extends WebElement> webElements) {
        bot.assertNumberOfElementsSmallerThan(number, webElements);
    }

    public void assertNumberOfElementsSmallerThanOrEquals(int number, List<? extends WebElement> webElements) {
        bot.assertNumberOfElementsSmallerThanOrEquals(number, webElements);
    }

    public void assertNumberOfElementsLargerThan(int number, List<? extends WebElement> webElements) {
        bot.assertNumberOfElementsLargerThan(number, webElements);
    }

    public void assertNumberOfElementsLargerThanOrEquals(int number, List<? extends WebElement> webElements) {
        bot.assertNumberOfElementsLargerThanOrEquals(number, webElements);
    }

    public boolean hasOption(String text, WebElement webElement) {
        return bot.hasOption(text, webElement);
    }

    public boolean hasNotOption(String text, WebElement webElement) {
        return bot.hasNotOption(text, webElement);
    }

    public boolean isOptionEnabled(String text, WebElement webElement) {
        return bot.isOptionEnabled(text, webElement);
    }

    public boolean isOptionDisabled(String text, WebElement webElement) {
        return bot.isOptionDisabled(text, webElement);
    }

    public boolean isOptionSelected(String text, WebElement webElement) {
        return bot.isOptionSelected(text, webElement);
    }

    public boolean isOptionDeselected(String text, WebElement webElement) {
        return bot.isOptionDeselected(text, webElement);
    }

    public boolean isAllOptionSelected(WebElement webElement) {
        return bot.isAllOptionSelected(webElement);
    }

    public boolean isNoOptionSelected(WebElement webElement) {
        return bot.isNoOptionSelected(webElement);
    }

    public void assertHasOption(String text, WebElement webElement) {
        bot.assertHasOption(text, webElement);
    }

    public void assertHasNotOption(String text, WebElement webElement) {
        bot.assertHasNotOption(text, webElement);
    }

    public void assertIsOptionEnabled(String text, WebElement webElement) {
        bot.assertIsOptionEnabled(text, webElement);
    }

    public void assertIsOptionDisabled(String text, WebElement webElement) {
        bot.assertIsOptionDisabled(text, webElement);
    }

    public void assertIsOptionSelected(String text, WebElement webElement) {
        bot.assertIsOptionSelected(text, webElement);
    }

    public void assertIsOptionDeselected(String text, WebElement webElement) {
        bot.assertIsOptionDeselected(text, webElement);
    }

    public void assertIsAllOptionSelected(WebElement webElement) {
        bot.assertIsAllOptionSelected(webElement);
    }

    public void assertIsNoOptionSelected(WebElement webElement) {
        bot.assertIsNoOptionSelected(webElement);
    }

    public boolean hasOptionWithValue(String value, WebElement webElement) {
        return bot.hasOptionWithValue(value, webElement);
    }

    public boolean hasNotOptionWithValue(String value, WebElement webElement) {
        return bot.hasNotOptionWithValue(value, webElement);
    }

    public boolean isOptionWithValueEnabled(String value, WebElement webElement) {
        return bot.isOptionWithValueEnabled(value, webElement);
    }

    public boolean isOptionWithValueDisabled(String value, WebElement webElement) {
        return bot.isOptionWithValueDisabled(value, webElement);
    }

    public boolean isOptionWithValueSelected(String value, WebElement webElement) {
        return bot.isOptionWithValueSelected(value, webElement);
    }

    public boolean isOptionWithValueDeselected(String value, WebElement webElement) {
        return bot.isOptionWithValueDeselected(value, webElement);
    }

    public void assertHasOptionWithValue(String value, WebElement webElement) {
        bot.assertHasOptionWithValue(value, webElement);
    }

    public void assertHasNotOptionWithValue(String value, WebElement webElement) {
        bot.assertHasNotOptionWithValue(value, webElement);
    }

    public void assertIsOptionWithValueEnabled(String value, WebElement webElement) {
        bot.assertIsOptionWithValueEnabled(value, webElement);
    }

    public void assertIsOptionWithValueDisabled(String value, WebElement webElement) {
        bot.assertIsOptionWithValueDisabled(value, webElement);
    }

    public void assertIsOptionWithValueSelected(String value, WebElement webElement) {
        bot.assertIsOptionWithValueSelected(value, webElement);
    }

    public void assertIsOptionWithValueDeselected(String value, WebElement webElement) {
        bot.assertIsOptionWithValueDeselected(value, webElement);
    }

    public boolean hasOptionWithIndex(int index, WebElement webElement) {
        return bot.hasOptionWithIndex(index, webElement);
    }

    public boolean hasNotOptionWithIndex(int index, WebElement webElement) {
        return bot.hasNotOptionWithIndex(index, webElement);
    }

    public boolean isOptionWithIndexEnabled(int index, WebElement webElement) {
        return bot.isOptionWithIndexEnabled(index, webElement);
    }

    public boolean isOptionWithIndexDisabled(int index, WebElement webElement) {
        return bot.isOptionWithIndexDisabled(index, webElement);
    }

    public boolean isOptionWithIndexSelected(int index, WebElement webElement) {
        return bot.isOptionWithIndexSelected(index, webElement);
    }

    public boolean isOptionWithIndexDeselected(int index, WebElement webElement) {
        return bot.isOptionWithIndexDeselected(index, webElement);
    }

    public void assertHasOptionWithIndex(int index, WebElement webElement) {
        bot.assertHasOptionWithIndex(index, webElement);
    }

    public void assertHasNotOptionWithIndex(int index, WebElement webElement) {
        bot.assertHasNotOptionWithIndex(index, webElement);
    }

    public void assertIsOptionWithIndexEnabled(int index, WebElement webElement) {
        bot.assertIsOptionWithIndexEnabled(index, webElement);
    }

    public void assertIsOptionWithIndexDisabled(int index, WebElement webElement) {
        bot.assertIsOptionWithIndexDisabled(index, webElement);
    }

    public void assertIsOptionWithIndexSelected(int index, WebElement webElement) {
        bot.assertIsOptionWithIndexSelected(index, webElement);
    }

    public void assertIsOptionWithIndexDeselected(int index, WebElement webElement) {
        bot.assertIsOptionWithIndexDeselected(index, webElement);
    }

}
