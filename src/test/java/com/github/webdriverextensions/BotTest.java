package com.github.webdriverextensions;

import static com.github.webdriverextensions.Bot.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.junitrunner.annotations.Edge;
import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.HtmlUnit;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreSafari;
import com.github.webdriverextensions.junitrunner.annotations.InternetExplorer;
import com.github.webdriverextensions.junitrunner.annotations.PhantomJS;
import com.github.webdriverextensions.junitrunner.annotations.Safari;
import com.github.webdriverextensions.model.pages.BotTestPage;

@RunWith(WebDriverRunner.class)
@Chrome
@Firefox
@InternetExplorer
@Edge
@Safari
@PhantomJS
@HtmlUnit
public class BotTest extends BotTestPage {

    @Before
    public void before() {
        open(url);
    }

    /* Click */
    @Test
    public void clickTest() {
        click(checkbox2);
        assertIsChecked(checkbox2);
    }

    /* Type */
    @Test
    public void typeTest() {
        clear(textInput);
        type("text", textInput);
        assertValueEquals("text", textInput);
        clear(textInput);
        type(42.0, textInput);
        assertValueEquals(42.0, textInput);
    }

    /* Clear */
    @Test
    public void clearTest() {
        clear(textInput);
        assertValueEquals("", textInput);
        clearAndType("text", textInput);
        assertValueEquals("text", textInput);
        clearAndType(42.0, textInput);
        assertValueEquals(42.0, textInput);
    }

    /* Press Keys */
    @Test
    public void pressKeysTest() {
        clear(textInput);
        pressKeys(textInput, "t");
        assertValueEquals("t", textInput);
        pressKeys(textInput, "e");
        assertValueEquals("te", textInput);
        pressKeys(textInput, "x");
        assertValueEquals("tex", textInput);
        pressKeys(textInput, "t");
        assertValueEquals("text", textInput);
    }

    /* Select/Deselect */
    @Test
    @IgnoreSafari // Fails to select on click in Safari
    public void selectDeselectTest() {
        select(multipleSelectOption2);
        assertIsSelected(multipleSelectOption2);
        deselect(multipleSelectOption1);
        assertIsDeselected(multipleSelectOption1);
    }

    /* Check/Uncheck */
    @Test
    public void checkUncheckTest() {
        check(checkbox2);
        assertIsChecked(checkbox2);
        uncheck(checkbox1);
        assertIsUnchecked(checkbox1);
    }

    /* Open */
    @Test
    @IgnoreSafari // throws UnsupportedCommandException from RemoteWebElement.isDisplayed() for some reason
    public void openTest() {
        debug(driver().getPageSource());
        //open(botTestPage);
        //assertIsOpen(botTestPage);
    }

    /* Wait For */
    @Test
    @IgnoreSafari // throws UnsupportedCommandException from RemoteWebElement.isDisplayed() for some reason
    public void waitForTest() {
        waitForElementToDisplay(firstAppendedSpan);
        assertIsDisplayed(firstAppendedSpan);
        assertIsNotDisplayed(secondAppendedSpan, 0);
        waitFor(0.8);
        waitFor(0.2 / 24 / 60 / 60, TimeUnit.DAYS);
        waitFor(0.2 / 60 / 60, TimeUnit.HOURS);
        waitFor(0.2 / 60, TimeUnit.MINUTES);
        waitFor(0.2, TimeUnit.SECONDS);
        waitFor(0.2 * 1000, TimeUnit.MILLISECONDS);
        waitFor(0.2 * 1000 * 1000, TimeUnit.MICROSECONDS);
        assertIsDisplayed(firstAppendedSpan);
        assertIsDisplayed(secondAppendedSpan);
    }

    @Test
    @IgnoreSafari // throws UnsupportedCommandException from RemoteWebElement.isDisplayed() for some reason
    public void waitForElementsToDisplayTest() {
        waitForElementsToDisplay(appendedSpans);
        assertIsDisplayed(firstAppendedSpan);
        assertIsNotDisplayed(secondAppendedSpan);
    }

    /* Debug */
    @Test
    public void debugTest() {
        debug("Text to debug");
        debug(attributesSpan);
        debug(selectAllOption);
        debug(body);
    }

    /* Is Open */
    @Test
    @IgnoreSafari // throws UnsupportedCommandException from RemoteWebElement.isDisplayed() for some reason
    public void isOpenTest() {
        //assertIsOpen(botTestPage);
        //assertIsNotOpen(webDriverExtensionSite);
    }

    /* Is Display */
    @Test
    @IgnoreSafari // throws UnsupportedCommandException from RemoteWebElement.isDisplayed() for some reason
    public void isDisplayedTest() {
        assertIsDisplayed(textSpan);
        assertIsDisplayed(firstAppendedSpan, 2);
        assertIsNotDisplayed(secondAppendedSpan);
        assertIsDisplayed(secondAppendedSpan, 2);
    }

    /* Size */
    @Test
    public void sizeTest() {
        assertSizeEquals(3, selectAllOption);
        assertSizeNotEquals(0, selectAllOption);
        assertSizeLessThan(4, selectAllOption);
        assertSizeLessThanOrEquals(3, selectAllOption);
        assertSizeGreaterThan(2, selectAllOption);
        assertSizeGreaterThanOrEquals(3, selectAllOption);
    }

    /* Url */
    @Test
    public void urlTest() {
        if (browserIsHtmlUnit()) {
            assertCurrentUrlEquals(url.replace("file:///", "file:/"));
        } else {
            assertCurrentUrlEquals(url);
        }
        assertCurrentUrlNotEquals("xxx");
        assertCurrentUrlContains("bot-test");
        assertCurrentUrlNotContains("xxx");
        assertCurrentUrlStartsWith("file:/");
        assertCurrentUrlNotStartsWith("xxx");
        assertCurrentUrlEndsWith("/bot-test.html");
        assertCurrentUrlNotEndsWith("xxx");
        assertCurrentUrlMatches(".*bot-test.*");
        assertCurrentUrlNotMatches(".*xxx.*");
    }

    /* Title */
    @Test
    public void titleTest() {
        assertTitleEquals("prefixtitlesuffix");
        assertTitleNotEquals("xxx");
        assertTitleContains("title");
        assertTitleNotContains("xxx");
        assertTitleStartsWith("prefixtitle");
        assertTitleNotStartsWith("xxx");
        assertTitleEndsWith("titlesuffix");
        assertTitleNotEndsWith("xxx");
        assertTitleMatches(".*title.*");
        assertTitleNotMatches(".*xxx.*");
    }

    /* Tag Name */
    @Test
    public void tagNameTest() {
        assertTagNameEquals("span", attributesSpan);
        assertTagNameNotEquals("xxx", attributesSpan);
    }

    /* Attribute */
    @Test
    public void attributeTest() {
        assertAttributeEquals("id", "prefixidsuffix", attributesSpan);
        assertAttributeNotEquals("id", "xxx", attributesSpan);
        assertAttributeContains("id", "id", attributesSpan);
        assertAttributeNotContains("id", "xxx", attributesSpan);
        assertAttributeStartsWith("id", "prefixid", attributesSpan);
        assertAttributeNotStartsWith("id", "xxx", attributesSpan);
        assertAttributeEndsWith("id", "idsuffix", attributesSpan);
        assertAttributeNotEndsWith("id", "xxx", attributesSpan);
        assertAttributeMatches("id", ".*id.*", attributesSpan);
        assertAttributeNotMatches("id", ".*xxx.*", attributesSpan);
    }

    /* Id */
    @Test
    public void idTest() {
        assertIdEquals("prefixidsuffix", attributesSpan);
        assertIdNotEquals("xxx", attributesSpan);
        assertIdContains("id", attributesSpan);
        assertIdNotContains("xxx", attributesSpan);
        assertIdStartsWith("prefixid", attributesSpan);
        assertIdNotStartsWith("xxx", attributesSpan);
        assertIdEndsWith("idsuffix", attributesSpan);
        assertIdNotEndsWith("xxx", attributesSpan);
        assertIdMatches(".*id.*", attributesSpan);
        assertIdNotMatches(".*xxx.*", attributesSpan);
    }

    /* Name */
    @Test
    public void nameTest() {
        assertNameEquals("prefixnamesuffix", attributesSpan);
        assertNameNotEquals("xxx", attributesSpan);
        assertNameContains("name", attributesSpan);
        assertNameNotContains("xxx", attributesSpan);
        assertNameStartsWith("prefixname", attributesSpan);
        assertNameNotStartsWith("xxx", attributesSpan);
        assertNameEndsWith("namesuffix", attributesSpan);
        assertNameNotEndsWith("xxx", attributesSpan);
        assertNameMatches(".*name.*", attributesSpan);
        assertNameNotMatches(".*xxx.*", attributesSpan);
    }

    /* Class */
    @Test
    public void classTest() {
        assertHasClass("prefixclass1suffix", attributesSpan);
        assertHasClass("prefixclass2suffix", attributesSpan);
        assertHasClass("prefixclass3suffix", attributesSpan);

        Assert.assertThat(classIn(attributesSpan), equalTo(" prefixclass1suffix prefixclass2suffix prefixclass3suffix "));
        List<String> classes = classesIn(attributesSpan);
        Assert.assertThat(classes, hasItem("prefixclass1suffix"));
        Assert.assertThat(classes, hasItem("prefixclass2suffix"));
        Assert.assertThat(classes, hasItem("prefixclass3suffix"));
    }

    /* Value */
    @Test
    public void valueTest() {
        assertValueEquals("prefixvaluesuffix", attributesSpan);
        assertValueNotEquals("xxx", attributesSpan);
        assertValueContains("value", attributesSpan);
        assertValueNotContains("xxx", attributesSpan);
        assertValueStartsWith("prefixvalue", attributesSpan);
        assertValueNotStartsWith("xxx", attributesSpan);
        assertValueEndsWith("valuesuffix", attributesSpan);
        assertValueNotEndsWith("xxx", attributesSpan);
        assertValueMatches(".*value.*", attributesSpan);
        assertValueNotMatches(".*xxx.*", attributesSpan);
    }

    /* Value Number */
    @Test
    public void valueNumberTest() {
        // floatNumberInput
        assertValueIsNumber(floatNumberInput);
        assertValueIsNotNumber(textInput);
        assertValueEquals(42.0, floatNumberInput);
        assertValueNotEquals(43.0, floatNumberInput);
        assertValueLessThan(43.0, floatNumberInput);
        assertValueLessThanOrEquals(42.0, floatNumberInput);
        assertValueGreaterThan(41.0, floatNumberInput);
        assertValueGreaterThanOrEquals(42.0, floatNumberInput);

        // intNumberInput
        assertValueIsNumber(intNumberInput);
        assertValueIsNotNumber(textInput);
        assertValueEquals(42.0, intNumberInput);
        assertValueNotEquals(43.0, intNumberInput);
        assertValueLessThan(43.0, intNumberInput);
        assertValueLessThanOrEquals(42.0, intNumberInput);
        assertValueGreaterThan(41.0, intNumberInput);
        assertValueGreaterThanOrEquals(42.0, intNumberInput);
    }

    /* Href */
    @Test
    public void hrefTest() {
        if (browserIsHtmlUnit()) {
            String filePath = url.replace("file:///", "file:/").replace("bot-test.html", "");
            assertHrefEquals(filePath + "prefixhrefsuffix", attributesSpan);
        } else {
            assertHrefEquals("prefixhrefsuffix", attributesSpan);
        }
        assertHrefNotEquals("xxx", attributesSpan);
        assertHrefContains("href", attributesSpan);
        assertHrefNotContains("xxx", attributesSpan);
        if (browserIsHtmlUnit()) {
            String filePath = url.replace("file:///", "file:/").replace("bot-test.html", "");
            assertHrefStartsWith(filePath + "prefixhref", attributesSpan);
        } else {
            assertHrefStartsWith("prefixhref", attributesSpan);
        }
        assertHrefNotStartsWith("xxx", attributesSpan);
        assertHrefEndsWith("hrefsuffix", attributesSpan);
        assertHrefNotEndsWith("xxx", attributesSpan);
        assertHrefMatches(".*href.*", attributesSpan);
        assertHrefNotMatches(".*xxx.*", attributesSpan);
    }

    /* Text */
    @Test
    public void textTest() {
        assertTextEquals("prefixtextsuffix", textSpan);
        assertTextNotEquals("xxx", textSpan);
        assertTextEqualsIgnoreCase("PREFIXTEXTSUFFIX", textSpan);
        assertTextNotEqualsIgnoreCase("xxx", textSpan);
        assertTextContains("text", textSpan);
        assertTextNotContains("xxx", textSpan);
        assertTextContainsIgnoreCase("TEXT", textSpan);
        assertTextNotContainsIgnoreCase("xxx", textSpan);
        assertTextStartsWith("prefixtext", textSpan);
        assertTextNotStartsWith("xxx", textSpan);
        assertTextStartsWithIgnoreCase("PREFIXTEXT", textSpan);
        assertTextNotStartsWithIgnoreCase("xxx", textSpan);
        assertTextEndsWith("textsuffix", textSpan);
        assertTextNotEndsWith("xxx", textSpan);
        assertTextEndsWithIgnoreCase("TEXTSUFFIX", textSpan);
        assertTextNotEndsWithIgnoreCase("xxx", textSpan);
        assertTextMatches(".*text.*", textSpan);
        assertTextNotMatches(".*xxx.*", textSpan);
    }

    /* Text Number */
    @Test
    public void textNumberTest() {
        // floatNumberSpan
        assertTextIsNumber(floatNumberSpan);
        assertTextIsNotNumber(textSpan);
        assertTextEquals(42.0, floatNumberSpan);
        assertTextNotEquals(43.0, floatNumberSpan);
        assertTextLessThan(43.0, floatNumberSpan);
        assertTextLessThanOrEquals(42.0, floatNumberSpan);
        assertTextGreaterThan(41.0, floatNumberSpan);
        assertTextGreaterThanOrEquals(42.0, floatNumberSpan);

        // intNumberSpan
        assertTextIsNumber(intNumberSpan);
        assertTextIsNotNumber(textSpan);
        assertTextEquals(42.0, intNumberSpan);
        assertTextNotEquals(43.0, intNumberSpan);
        assertTextLessThan(43.0, intNumberSpan);
        assertTextLessThanOrEquals(42.0, intNumberSpan);
        assertTextGreaterThan(41.0, intNumberSpan);
        assertTextGreaterThanOrEquals(42.0, intNumberSpan);
    }

    /* Selected/Deselected */
    @Test
    public void selectedDeselectedTest() {
        assertIsSelected(selectOption1);
        assertIsDeselected(selectOption2);
    }

    /* Checked/Unchecked */
    @Test
    public void checkedUncheckedTest() {
        // checkboxes
        assertIsChecked(checkbox1);
        assertIsUnchecked(checkbox2);

        // radiobuttons
        assertIsChecked(radiobutton1);
        assertIsUnchecked(radiobutton2);
    }

    /* Enabled/Disabled */
    @Test
    public void enabledDisabledTest() {
        assertIsEnabled(selectOption1);
        assertIsEnabled(selectOption2);
        assertIsDisabled(selectOption3);
    }

    /* Option */
    @Test
    public void optionTest() {
        // Selected/Deselected
        assertOptionIsSelected("Option 1", select);
        assertOptionIsDeselected("Option 2", select);
        assertOptionIsDeselected("Option 3", select);

        // Enabled/Disabled
        assertOptionIsEnabled("Option 1", select);
        assertOptionIsEnabled("Option 2", select);
        assertOptionIsDisabled("Option 3", select);
    }

    /* Option Value */
    @Test
    public void optionValueTest() {
        // Selected/Deselected
        assertOptionWithValueIsSelected("option1value", select);
        assertOptionWithValueIsDeselected("option2value", select);
        assertOptionWithValueIsDeselected("option3value", select);

        // Enabled/Disabled
        assertOptionWithValueIsEnabled("option1value", select);
        assertOptionWithValueIsEnabled("option2value", select);
        assertOptionWithValueIsDisabled("option3value", select);
    }

    /* Option Index */
    @Test
    public void optionIndexTest() {
        // Selected/Deselected
        assertOptionWithIndexIsSelected(0, select);
        assertOptionWithIndexIsDeselected(1, select);
        assertOptionWithIndexIsDeselected(2, select);

        // Enabled/Disabled
        assertOptionWithIndexIsEnabled(0, select);
        assertOptionWithIndexIsEnabled(1, select);
        assertOptionWithIndexIsDisabled(2, select);
    }
}
