package org.andidev.webdriverextension.onestepbot;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.andidev.webdriverextension.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface BotI {

    /* Driver */
    public WebDriver getDriver();

    /* Click */
    public void click(WebElement webElement);

    /* Read */
    public String read(WebElement webElement);
    public Double readNumber(WebElement webElement);
    public List<String> readOptions(WebElement webElement);
    public String readUrl(WebDriver driver);
    public String readTagName(WebElement webElement);
    public String readAttribute(String name, WebElement webElement);
    public String readId(WebElement webElement);
    public String readName(WebElement webElement);
    public String readClass(WebElement webElement);
    public String readHref(WebElement webElement);

    /* Clear */
    public void clear(WebElement webElement);

    /* Type */
    public void type(String text, WebElement webElement);
    public void typeNumber(Double number, WebElement webElement);
    public void clearAndType(String text, WebElement webElement);
    public void clearAndTypeNumber(Double number, WebElement webElement);

    /* Press */
    public void pressEnter(WebElement webElement);
    public void pressKeys(WebElement webElement, CharSequence... keysToSend); // TODO: Turn around?

    /* Select */
    public void select(WebElement webElement);
    public void deselect(WebElement webElement);

    /* Select Option */
    public void selectOption(String text, WebElement webElement);
    public void deselectOption(String text, WebElement webElement);
    public void selectAllOptions(WebElement webElement);
    public void deselectAllOptions(WebElement webElement);

    /* Select Option Value */
    public void selectOptionWithValue(String value, WebElement webElement);
    public void deselectOptionWithValue(String value, WebElement webElement);

    /* Select Option Index */
    public void selectOptionWithIndex(int index, WebElement webElement);
    public void deselectOptionWithIndex(int index, WebElement webElement);

    /* Check/Uncheck */
    public void check(WebElement webElement);
    public void uncheck(WebElement webElement);

    /* Open */
    public void open(String url, WebDriver driver);
    public void open(Openable openable);

    /* Count */
    public Integer countNumberOfElements(List<? extends WebElement> webElements);

    /* Wait */
    public void delay(double seconds);
    public void delay(long time, TimeUnit unit);
    public void waitForElementToDisplay(WebElement webElement, WebDriver driver);
    public void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds, WebDriver driver);
    public void waitForElementToDisplay(WebElement webElement, long timeOutInSeconds, long sleepInMillis, WebDriver driver);

    /* Debug */
    public void debug(String str);
    public void debug(WebElement webElement);
    public void debug(List<? extends WebElement> webElement);
    public void debugText(WebElement webElement);
    public void debugText(List<? extends WebElement> webElements);
    public void debugNumberOfElements(List<? extends WebElement> webElements);

    /* Tag Name */
    public boolean isTagName(String value, WebElement webElement);
    public boolean isTagNameNot(String value, WebElement webElement);
    public void assertTagName(String value, WebElement webElement);
    public void assertTagNameNot(String value, WebElement webElement);

    /* Attribute */
    public boolean hasAttribute(String name, WebElement webElement);
    public boolean hasNotAttribute(String name, WebElement webElement);
    public boolean isAttribute(String name, String value, WebElement webElement);
    public boolean isAttributeNot(String name, String value, WebElement webElement);
    public boolean isAttributeContaining(String name, String searchText, WebElement webElement);
    public boolean isAttributeNotContaining(String name, String searchText, WebElement webElement);
    public boolean isAttributeStartingWith(String name, String prefix, WebElement webElement);
    public boolean isAttributeNotStartingWith(String name, String prefix, WebElement webElement);
    public boolean isAttributeEndingWith(String name, String suffix, WebElement webElement);
    public boolean isAttributeNotEndingWith(String name, String suffix, WebElement webElement);
    public void assertAttribute(String name, String value, WebElement webElement);
    public void assertAttributeNot(String name, String value, WebElement webElement);
    public void assertAttributeContains(String name, String searchText, WebElement webElement);
    public void assertAttributeNotContains(String name, String searchText, WebElement webElement);
    public void assertAttributeStartsWith(String name, String prefix, WebElement webElement);
    public void assertAttributeNotStartsWith(String name, String prefix, WebElement webElement);
    public void assertAttributeEndsWith(String name, String suffix, WebElement webElement);
    public void assertAttributeNotEndsWith(String name, String suffix, WebElement webElement);

    /* Id */
    public boolean hasId(WebElement webElement);
    public boolean hasNotId(WebElement webElement);
    public boolean isId(String value, WebElement webElement);
    public boolean isIdNot(String value, WebElement webElement);
    public boolean isIdContaining(String searchText, WebElement webElement);
    public boolean isIdNotContaining(String searchText, WebElement webElement);
    public boolean isIdStartingWith(String prefix, WebElement webElement);
    public boolean isIdNotStartingWith(String prefix, WebElement webElement);
    public boolean isIdEndingWith(String suffix, WebElement webElement);
    public boolean isIdNotEndingWith(String suffix, WebElement webElement);
    public void assertId(String value, WebElement webElement);
    public void assertIdNot(String value, WebElement webElement);
    public void assertIdContains(String searchText, WebElement webElement);
    public void assertIdNotContains(String searchText, WebElement webElement);
    public void assertIdStartsWith(String prefix, WebElement webElement);
    public void assertIdNotStartsWith(String prefix, WebElement webElement);
    public void assertIdEndsWith(String suffix, WebElement webElement);
    public void assertIdNotEndsWith(String suffix, WebElement webElement);

    /* Name */
    public boolean hasName(WebElement webElement);
    public boolean hasNotName(WebElement webElement);
    public boolean isName(String value, WebElement webElement);
    public boolean isNameNot(String value, WebElement webElement);
    public boolean isNameContaining(String searchText, WebElement webElement);
    public boolean isNameNotContaining(String searchText, WebElement webElement);
    public boolean isNameStartingWith(String prefix, WebElement webElement);
    public boolean isNameNotStartingWith(String prefix, WebElement webElement);
    public boolean isNameEndingWith(String suffix, WebElement webElement);
    public boolean isNameNotEndingWith(String suffix, WebElement webElement);
    public void assertName(String value, WebElement webElement);
    public void assertNameNot(String value, WebElement webElement);
    public void assertNameContains(String searchText, WebElement webElement);
    public void assertNameNotContains(String searchText, WebElement webElement);
    public void assertNameStartsWith(String prefix, WebElement webElement);
    public void assertNameNotStartsWith(String prefix, WebElement webElement);
    public void assertNameEndsWith(String suffix, WebElement webElement);
    public void assertNameNotEndsWith(String suffix, WebElement webElement);

    /* Class */
    public boolean hasClass(WebElement webElement);
    public boolean hasClass(String className, WebElement webElement);
    public boolean hasNotClass(WebElement webElement);
    public boolean hasNotClass(String className, WebElement webElement);
    public boolean isClass(String value, WebElement webElement);
    public boolean isClassNot(String value, WebElement webElement);
    public boolean isClassContaining(String searchText, WebElement webElement);
    public boolean isClassNotContaining(String searchText, WebElement webElement);
    public boolean isClassStartingWith(String prefix, WebElement webElement);
    public boolean isClassNotStartingWith(String prefix, WebElement webElement);
    public boolean isClassEndingWith(String suffix, WebElement webElement);
    public boolean isClassNotEndingWith(String suffix, WebElement webElement);
    public void assertHasClass(String className, WebElement webElement);
    public void assertHasNotClass(String className, WebElement webElement);
    public void assertClass(String value, WebElement webElement);
    public void assertClassNot(String value, WebElement webElement);
    public void assertClassContains(String searchText, WebElement webElement);
    public void assertClassNotContains(String searchText, WebElement webElement);
    public void assertClassStartsWith(String prefix, WebElement webElement);
    public void assertClassNotStartsWith(String prefix, WebElement webElement);
    public void assertClassEndsWith(String suffix, WebElement webElement);
    public void assertClassNotEndsWith(String suffix, WebElement webElement);

    /* Value */
    public boolean hasValue(WebElement webElement);
    public boolean hasValue(String className, WebElement webElement);
    public boolean hasNotValue(WebElement webElement);
    public boolean hasNotValue(String className, WebElement webElement);
    public boolean isValue(String value, WebElement webElement);
    public boolean isValueNot(String value, WebElement webElement);
    public boolean isValueContaining(String searchText, WebElement webElement);
    public boolean isValueNotContaining(String searchText, WebElement webElement);
    public boolean isValueStartingWith(String prefix, WebElement webElement);
    public boolean isValueNotStartingWith(String prefix, WebElement webElement);
    public boolean isValueEndingWith(String suffix, WebElement webElement);
    public boolean isValueNotEndingWith(String suffix, WebElement webElement);
    public void assertHasValue(String className, WebElement webElement);
    public void assertHasNotValue(String className, WebElement webElement);
    public void assertValue(String value, WebElement webElement);
    public void assertValueNot(String value, WebElement webElement);
    public void assertValueContains(String searchText, WebElement webElement);
    public void assertValueNotContains(String searchText, WebElement webElement);
    public void assertValueStartsWith(String prefix, WebElement webElement);
    public void assertValueNotStartsWith(String prefix, WebElement webElement);
    public void assertValueEndsWith(String suffix, WebElement webElement);
    public void assertValueNotEndsWith(String suffix, WebElement webElement);

    /* Href */
    public boolean hasHref(WebElement webElement);
    public boolean hasNotHref(WebElement webElement);
    public boolean isHref(String value, WebElement webElement);
    public boolean isHrefNot(String value, WebElement webElement);
    public boolean isHrefContaining(String searchText, WebElement webElement);
    public boolean isHrefNotContaining(String searchText, WebElement webElement);
    public boolean isHrefStartingWith(String prefix, WebElement webElement);
    public boolean isHrefNotStartingWith(String prefix, WebElement webElement);
    public boolean isHrefEndingWith(String suffix, WebElement webElement);
    public boolean isHrefNotEndingWith(String suffix, WebElement webElement);
    public void assertHref(String value, WebElement webElement);
    public void assertHrefNot(String value, WebElement webElement);
    public void assertHrefContains(String searchText, WebElement webElement);
    public void assertHrefNotContains(String searchText, WebElement webElement);
    public void assertHrefStartsWith(String prefix, WebElement webElement);
    public void assertHrefNotStartsWith(String prefix, WebElement webElement);
    public void assertHrefEndsWith(String suffix, WebElement webElement);
    public void assertHrefNotEndsWith(String suffix, WebElement webElement);

    /* Text */
    public boolean isText(String text, WebElement webElement);
    public boolean isTextNot(String text, WebElement webElement);
    public boolean isTextContaining(String text, WebElement webElement);
    public boolean isTextNotContaining(String text, WebElement webElement);
    public boolean isTextStartingWith(String prefix, WebElement webElement);
    public boolean isTextNotStartingWith(String prefix, WebElement webElement);
    public boolean isTextEndingWith(String suffix, WebElement webElement);
    public boolean isTextNotEndingWith(String suffix, WebElement webElement);
    public void assertText(String text, WebElement webElement);
    public void assertTextNot(String text, WebElement webElement);
    public void assertTextContains(String searchText, WebElement webElement);
    public void assertTextNotContains(String searchText, WebElement webElement);
    public void assertTextStartsWith(String prefix, WebElement webElement);
    public void assertTextNotStartsWith(String prefix, WebElement webElement);
    public void assertTextEndsWith(String suffix, WebElement webElement);
    public void assertTextNotEndsWith(String suffix, WebElement webElement);

    /* Number */
    public boolean isNumber(Double number, WebElement webElement);
    public boolean isNumberNot(Double number, WebElement webElement);
    public boolean isNumberSmallerThen(Double number, WebElement webElement);
    public boolean isNumberSmallerThenOrEquals(Double number, WebElement webElement);
    public boolean isNumberLargerThen(Double number, WebElement webElement);
    public boolean isNumberLargerThenOrEquals(Double number, WebElement webElement);
    public void assertNumber(Double number, WebElement webElement);
    public void assertNumberNot(Double number, WebElement webElement);
    public void assertNumberSmallerThen(Double number, WebElement webElement);
    public void assertNumberSmallerThenOrEquals(Double number, WebElement webElement);
    public void assertNumberLargerThen(Double number, WebElement webElement);
    public void assertNumberLargerThenOrEquals(Double number, WebElement webElement);

    /* Browser Url */
    public boolean isUrl(String url, WebDriver driver);
    public boolean isUrlNot(String url, WebDriver driver);
    public boolean isUrlMatching(String regExp, WebDriver driver);
    public boolean isUrlNotMatching(String regExp, WebDriver driver);
    public boolean isUrlMatching(Openable openable, WebDriver driver);
    public boolean isUrlNotMatching(Openable openable, WebDriver driver);
    public boolean isUrlContaining(String text, WebDriver driver);
    public boolean isUrlNotContaining(String text, WebDriver driver);
    public boolean isUrlStartingWidth(String prefix, WebDriver driver);
    public boolean isUrlNotStartingWidth(String prefix, WebDriver driver);
    public boolean isUrlEndingWidth(String suffix, WebDriver driver);
    public boolean isUrlNotEndingWidth(String suffix, WebDriver driver);
    public void assertUrl(String url, WebDriver driver);
    public void assertUrlNot(String url, WebDriver driver);
    public void assertUrlMatching(String regExp, WebDriver driver);
    public void assertUrlNotMatching(String regExp, WebDriver driver);
    public void assertUrlMatching(Openable openable, WebDriver driver);
    public void assertUrlNotMatching(Openable openable, WebDriver driver);
    public void assertUrlContains(String searchText, WebDriver driver);
    public void assertUrlNotContains(String searchText, WebDriver driver);
    public void assertUrlStartsWidth(String prefix, WebDriver driver);
    public void assertUrlNotStartsWidth(String prefix, WebDriver driver);
    public void assertUrlEndsWidth(String suffix, WebDriver driver);
    public void assertUrlNotEndsWidth(String suffix, WebDriver driver);

    /* Open */
    public boolean isOpen(Openable openable);
    public boolean isNotOpen(Openable openable);
    public void assertIsOpen(Openable openable);
    public void assertIsNotOpen(Openable openable);

    /* Selected */
    public boolean isSelected(WebElement webElement);
    public boolean isNotSelected(WebElement webElement);
    public void assertIsSelected(WebElement webElement);
    public void assertIsNotSelected(WebElement webElement);

    /* Checked/Unchecked */
    public boolean isChecked(WebElement webElement);
    public boolean isUnchecked(WebElement webElement);
    public void assertIsChecked(WebElement webElement);
    public void assertIsUnchecked(WebElement webElement);

    /* Enabled/Disabled */
    public boolean isEnabled(WebElement webElement);
    public boolean isDisabled(WebElement webElement);
    public void assertIsEnabled(WebElement webElement);
    public void assertIsDisabled(WebElement webElement);

    /* Select Option */
    public boolean hasOption(String text, WebElement webElement);
    public boolean hasNotOption(String text, WebElement webElement);
    public boolean hasOptionEnabled(String text, WebElement webElement);
    public boolean hasOptionDisabled(String text, WebElement webElement);
    public boolean hasOptionSelected(String text, WebElement webElement);
    public boolean hasOptionDeselected(String text, WebElement webElement);
    public boolean isOptionEnabled(String text, WebElement webElement);
    public boolean isOptionDisabled(String text, WebElement webElement);
    public boolean isOptionSelected(String text, WebElement webElement);
    public boolean isOptionDeselected(String text, WebElement webElement);
    public boolean isAllOptionSelected(WebElement webElement);
    public boolean isNoOptionSelected(WebElement webElement);
    public void assertHasOption(String text, WebElement webElement);
    public void assertHasNotOption(String text, WebElement webElement);
    public void assertHasOptionEnabled(String text, WebElement webElement);
    public void assertHasOptionDisabled(String text, WebElement webElement);
    public void assertHasOptionSelected(String text, WebElement webElement);
    public void assertHasOptionDeselected(String text, WebElement webElement);
    public void assertIsOptionEnabled(String text, WebElement webElement);
    public void assertIsOptionDisabled(String text, WebElement webElement);
    public void assertIsOptionSelected(String text, WebElement webElement);
    public void assertIsOptionDeselected(String text, WebElement webElement);
    public void assertIsAllOptionSelected(String text, WebElement webElement);
    public void assertIsNoOptionSelected(String text, WebElement webElement);

    /* Select Option Value */
    public boolean hasOptionWithValue(String value, WebElement webElement);
    public boolean hasNotOptionWithValue(String value, WebElement webElement);
    public boolean hasOptionEnabledWithValue(String value, WebElement webElement);
    public boolean hasOptionDisabledWithValue(String value, WebElement webElement);
    public boolean hasOptionSelectedWithValue(String value, WebElement webElement);
    public boolean hasOptionDeselectedWithValue(String value, WebElement webElement);
    public boolean isOptionEnabledWithValue(String value, WebElement webElement);
    public boolean isOptionDisabledWithValue(String value, WebElement webElement);
    public boolean isOptionSelectedWithValue(String value, WebElement webElement);
    public boolean isOptionDeselectedWithValue(String value, WebElement webElement);
    public void assertHasOptionWithValue(String value, WebElement webElement);
    public void assertHasNotOptionWithValue(String value, WebElement webElement);
    public void assertHasOptionEnabledWithValue(String value, WebElement webElement);
    public void assertHasOptionDisabledWithValue(String value, WebElement webElement);
    public void assertHasOptionSelectedWithValue(String value, WebElement webElement);
    public void assertHasOptionDeselectedWithValue(String value, WebElement webElement);
    public void assertIsOptionEnabledWithValue(String value, WebElement webElement);
    public void assertIsOptionDisabledWithValue(String value, WebElement webElement);
    public void assertIsOptionSelectedWithValue(String value, WebElement webElement);
    public void assertIsOptionDeselectedWithValue(String value, WebElement webElement);

    /* Select Option Index */
    public boolean hasOptionWithIndex(int index, WebElement webElement);
    public boolean hasNotOptionWithIndex(int index, WebElement webElement);
    public boolean hasOptionEnabledWithIndex(int index, WebElement webElement);
    public boolean hasOptionDisabledWithIndex(int index, WebElement webElement);
    public boolean hasOptionSelectedWithIndex(int index, WebElement webElement);
    public boolean hasOptionDeselectedWithIndex(int index, WebElement webElement);
    public boolean isOptionEnabledWithIndex(int index, WebElement webElement);
    public boolean isOptionDisabledWithIndex(int index, WebElement webElement);
    public boolean isOptionSelectedWithIndex(int index, WebElement webElement);
    public boolean isOptionDeselectedWithIndex(int index, WebElement webElement);
    public void assertHasOptionWithIndex(int index, WebElement webElement);
    public void assertHasNotOptionWithIndex(int index, WebElement webElement);
    public void assertHasOptionEnabledWithIndex(int index, WebElement webElement);
    public void assertHasOptionDisabledWithIndex(int index, WebElement webElement);
    public void assertHasOptionSelectedWithIndex(int index, WebElement webElement);
    public void assertHasOptionDeselectedWithIndex(int index, WebElement webElement);
    public void assertIsOptionEnabledWithIndex(int index, WebElement webElement);
    public void assertIsOptionDisabledWithIndex(int index, WebElement webElement);
    public void assertIsOptionSelectedWithIndex(int index, WebElement webElement);
    public void assertIsOptionDeselectedWithIndex(int index, WebElement webElement);

    /* Display */
    public boolean isDisplayed(WebElement webElement);
    public boolean isNotDisplayed(WebElement webElement);
    public void assertIsDisplayed(WebElement webElement);
    public void assertIsNotDisplayed(WebElement webElement);

    /* Number of Elements */
    public boolean isNumberOfElements(int number, List<? extends WebElement> webElements);
    public boolean isNumberOfElementsNot(int number, List<? extends WebElement> webElements);
    public boolean isNumberOfElementsSmallerThen(int number, List<? extends WebElement> webElements);
    public boolean isNumberOfElementsSmallerThenOrEquals(int number, List<? extends WebElement> webElements);
    public boolean isNumberOfElementsLargerThen(int number, List<? extends WebElement> webElements);
    public boolean isNumberOfElementsLargerThenOrEquals(int number, List<? extends WebElement> webElements);
    public void assertNumberOfElements(int number, List<? extends WebElement> webElements);
    public void assertNumberOfElementsNot(int number, List<? extends WebElement> webElements);
    public void assertNumberOfElementsSmallerThen(int number, List<? extends WebElement> webElements);
    public void assertNumberOfElementsSmallerThenOrEquals(int number, List<? extends WebElement> webElements);
    public void assertNumberOfElementsLargerThen(int number, List<? extends WebElement> webElements);
    public void assertNumberOfElementsLargerThenOrEquals(int number, List<? extends WebElement> webElements);
}