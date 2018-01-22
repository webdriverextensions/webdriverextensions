package com.github.webdriverextensions;

import static com.github.webdriverextensions.Bot.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.webdriverextensions.model.TestWebRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.junitrunner.annotations.Edge;
import com.github.webdriverextensions.junitrunner.annotations.HtmlUnit;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreSafari;
import com.github.webdriverextensions.junitrunner.annotations.InternetExplorer;
import com.github.webdriverextensions.junitrunner.annotations.PhantomJS;
import com.github.webdriverextensions.junitrunner.annotations.Safari;

@RunWith(WebDriverRunner.class)
@Chrome
//@Firefox
@InternetExplorer
@Edge
@Safari
@PhantomJS
@HtmlUnit
public class BotTest extends TestWebRepository {

    @Before
    public void before() {
        open(botTestPage.url);
    }

    /* Click */
    @Test
    public void clickTest() {
        click(botTestPage.checkbox2);
        assertIsChecked(botTestPage.checkbox2);
    }

    /* Type */
    @Test
    public void typeTest() {
        clear(botTestPage.textInput);
        type("text", botTestPage.textInput);
        assertValueEquals("text", botTestPage.textInput);
        clear(botTestPage.textInput);
        type(42.0, botTestPage.textInput);
        assertValueEquals(42.0, botTestPage.textInput);
    }

    /* Clear */
    @Test
    public void clearTest() {
        clear(botTestPage.textInput);
        assertValueEquals("", botTestPage.textInput);
        clearAndType("text", botTestPage.textInput);
        assertValueEquals("text", botTestPage.textInput);
        clearAndType(42.0, botTestPage.textInput);
        assertValueEquals(42.0, botTestPage.textInput);
    }

    /* Press Keys */
    @Test
    public void pressKeysTest() {
        clear(botTestPage.textInput);
        pressKeys(botTestPage.textInput, "t");
        assertValueEquals("t", botTestPage.textInput);
        pressKeys(botTestPage.textInput, "e");
        assertValueEquals("te", botTestPage.textInput);
        pressKeys(botTestPage.textInput, "x");
        assertValueEquals("tex", botTestPage.textInput);
        pressKeys(botTestPage.textInput, "t");
        assertValueEquals("text", botTestPage.textInput);
    }

    /* Select/Deselect */
    @Test
    @IgnoreSafari // Fails to select on click in Safari
    public void selectDeselectTest() {
        select(botTestPage.multipleSelectOption2);
        assertIsSelected(botTestPage.multipleSelectOption2);
        deselect(botTestPage.multipleSelectOption1);
        assertIsDeselected(botTestPage.multipleSelectOption1);
    }

    /* Check/Uncheck */
    @Test
    public void checkUncheckTest() {
        check(botTestPage.checkbox2);
        assertIsChecked(botTestPage.checkbox2);
        uncheck(botTestPage.checkbox1);
        assertIsUnchecked(botTestPage.checkbox1);
    }

    /* Open */
    @Test
    @IgnoreSafari // throws UnsupportedCommandException from RemoteWebElement.isDisplayed() for some reason
    public void openTest() {
        debug(driver().getPageSource());
        open(botTestPage);
        assertIsOpen(botTestPage);
    }

    /* Wait For */
    @Test
    @IgnoreSafari // throws UnsupportedCommandException from RemoteWebElement.isDisplayed() for some reason
    public void waitForTest() {
        waitForElementToDisplay(botTestPage.firstAppendedSpan);
        assertIsDisplayed(botTestPage.firstAppendedSpan);
        assertIsNotDisplayed(botTestPage.secondAppendedSpan, 0);
        waitFor(0.8);
        waitFor(0.2 / 24 / 60 / 60, TimeUnit.DAYS);
        waitFor(0.2 / 60 / 60, TimeUnit.HOURS);
        waitFor(0.2 / 60, TimeUnit.MINUTES);
        waitFor(0.2, TimeUnit.SECONDS);
        waitFor(0.2 * 1000, TimeUnit.MILLISECONDS);
        waitFor(0.2 * 1000 * 1000, TimeUnit.MICROSECONDS);
        assertIsDisplayed(botTestPage.firstAppendedSpan);
        assertIsDisplayed(botTestPage.secondAppendedSpan);
    }

    @Test
    @IgnoreSafari // throws UnsupportedCommandException from RemoteWebElement.isDisplayed() for some reason
    public void waitForElementsToDisplayTest() {
        waitForElementsToDisplay(botTestPage.appendedSpans);
        assertIsDisplayed(botTestPage.firstAppendedSpan);
        assertIsNotDisplayed(botTestPage.secondAppendedSpan);
    }

    /* Debug */
    @Test
    public void debugTest() {
        debug("Text to debug");
        debug(botTestPage.attributesSpan);
        debug(botTestPage.selectAllOption);
        debug(botTestPage.body);
    }

    /* Is Open */
    @Test
    @IgnoreSafari // throws UnsupportedCommandException from RemoteWebElement.isDisplayed() for some reason
    public void isOpenTest() {
        assertIsOpen(botTestPage);
        assertIsNotOpen(webDriverExtensionSite);
    }

    /* Is Display */
    @Test
    @IgnoreSafari // throws UnsupportedCommandException from RemoteWebElement.isDisplayed() for some reason
    public void isDisplayedTest() {
        assertIsDisplayed(botTestPage.textSpan);
        assertIsDisplayed(botTestPage.firstAppendedSpan, 2);
        assertIsNotDisplayed(botTestPage.secondAppendedSpan);
        assertIsDisplayed(botTestPage.secondAppendedSpan, 2);
    }

    /* Size */
    @Test
    public void sizeTest() {
        assertSizeEquals(3, botTestPage.selectAllOption);
        assertSizeNotEquals(0, botTestPage.selectAllOption);
        assertSizeLessThan(4, botTestPage.selectAllOption);
        assertSizeLessThanOrEquals(3, botTestPage.selectAllOption);
        assertSizeGreaterThan(2, botTestPage.selectAllOption);
        assertSizeGreaterThanOrEquals(3, botTestPage.selectAllOption);
    }

    /* Url */
    @Test
    public void urlTest() {
        if (browserIsHtmlUnit()) {
            assertCurrentUrlEquals(botTestPage.url.replace("file:///", "file:/"));
        } else {
            assertCurrentUrlEquals(botTestPage.url);
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
        assertTagNameEquals("span", botTestPage.attributesSpan);
        assertTagNameNotEquals("xxx", botTestPage.attributesSpan);
    }

    /* Attribute */
    @Test
    public void attributeTest() {
        assertAttributeEquals("id", "prefixidsuffix", botTestPage.attributesSpan);
        assertAttributeNotEquals("id", "xxx", botTestPage.attributesSpan);
        assertAttributeContains("id", "id", botTestPage.attributesSpan);
        assertAttributeNotContains("id", "xxx", botTestPage.attributesSpan);
        assertAttributeStartsWith("id", "prefixid", botTestPage.attributesSpan);
        assertAttributeNotStartsWith("id", "xxx", botTestPage.attributesSpan);
        assertAttributeEndsWith("id", "idsuffix", botTestPage.attributesSpan);
        assertAttributeNotEndsWith("id", "xxx", botTestPage.attributesSpan);
        assertAttributeMatches("id", ".*id.*", botTestPage.attributesSpan);
        assertAttributeNotMatches("id", ".*xxx.*", botTestPage.attributesSpan);
    }

    /* Id */
    @Test
    public void idTest() {
        assertIdEquals("prefixidsuffix", botTestPage.attributesSpan);
        assertIdNotEquals("xxx", botTestPage.attributesSpan);
        assertIdContains("id", botTestPage.attributesSpan);
        assertIdNotContains("xxx", botTestPage.attributesSpan);
        assertIdStartsWith("prefixid", botTestPage.attributesSpan);
        assertIdNotStartsWith("xxx", botTestPage.attributesSpan);
        assertIdEndsWith("idsuffix", botTestPage.attributesSpan);
        assertIdNotEndsWith("xxx", botTestPage.attributesSpan);
        assertIdMatches(".*id.*", botTestPage.attributesSpan);
        assertIdNotMatches(".*xxx.*", botTestPage.attributesSpan);
    }

    /* Name */
    @Test
    public void nameTest() {
        assertNameEquals("prefixnamesuffix", botTestPage.attributesSpan);
        assertNameNotEquals("xxx", botTestPage.attributesSpan);
        assertNameContains("name", botTestPage.attributesSpan);
        assertNameNotContains("xxx", botTestPage.attributesSpan);
        assertNameStartsWith("prefixname", botTestPage.attributesSpan);
        assertNameNotStartsWith("xxx", botTestPage.attributesSpan);
        assertNameEndsWith("namesuffix", botTestPage.attributesSpan);
        assertNameNotEndsWith("xxx", botTestPage.attributesSpan);
        assertNameMatches(".*name.*", botTestPage.attributesSpan);
        assertNameNotMatches(".*xxx.*", botTestPage.attributesSpan);
    }

    /* Class */
    @Test
    public void classTest() {
        assertHasClass("prefixclass1suffix", botTestPage.attributesSpan);
        assertHasClass("prefixclass2suffix", botTestPage.attributesSpan);
        assertHasClass("prefixclass3suffix", botTestPage.attributesSpan);

        Assert.assertThat(classIn(botTestPage.attributesSpan), equalTo(" prefixclass1suffix prefixclass2suffix prefixclass3suffix "));
        List<String> classes = classesIn(botTestPage.attributesSpan);
        Assert.assertThat(classes, hasItem("prefixclass1suffix"));
        Assert.assertThat(classes, hasItem("prefixclass2suffix"));
        Assert.assertThat(classes, hasItem("prefixclass3suffix"));
    }

    /* Value */
    @Test
    public void valueTest() {
        assertValueEquals("prefixvaluesuffix", botTestPage.attributesSpan);
        assertValueNotEquals("xxx", botTestPage.attributesSpan);
        assertValueContains("value", botTestPage.attributesSpan);
        assertValueNotContains("xxx", botTestPage.attributesSpan);
        assertValueStartsWith("prefixvalue", botTestPage.attributesSpan);
        assertValueNotStartsWith("xxx", botTestPage.attributesSpan);
        assertValueEndsWith("valuesuffix", botTestPage.attributesSpan);
        assertValueNotEndsWith("xxx", botTestPage.attributesSpan);
        assertValueMatches(".*value.*", botTestPage.attributesSpan);
        assertValueNotMatches(".*xxx.*", botTestPage.attributesSpan);
    }

    /* Value Number */
    @Test
    public void valueNumberTest() {
        // floatNumberInput
        assertValueIsNumber(botTestPage.floatNumberInput);
        assertValueIsNotNumber(botTestPage.textInput);
        assertValueEquals(42.0, botTestPage.floatNumberInput);
        assertValueNotEquals(43.0, botTestPage.floatNumberInput);
        assertValueLessThan(43.0, botTestPage.floatNumberInput);
        assertValueLessThanOrEquals(42.0, botTestPage.floatNumberInput);
        assertValueGreaterThan(41.0, botTestPage.floatNumberInput);
        assertValueGreaterThanOrEquals(42.0, botTestPage.floatNumberInput);

        // intNumberInput
        assertValueIsNumber(botTestPage.intNumberInput);
        assertValueIsNotNumber(botTestPage.textInput);
        assertValueEquals(42.0, botTestPage.intNumberInput);
        assertValueNotEquals(43.0, botTestPage.intNumberInput);
        assertValueLessThan(43.0, botTestPage.intNumberInput);
        assertValueLessThanOrEquals(42.0, botTestPage.intNumberInput);
        assertValueGreaterThan(41.0, botTestPage.intNumberInput);
        assertValueGreaterThanOrEquals(42.0, botTestPage.intNumberInput);
    }

    /* Href */
    @Test
    public void hrefTest() {
        if (browserIsHtmlUnit()) {
            String filePath = botTestPage.url.replace("file:///", "file:/").replace("bot-test.html", "");
            assertHrefEquals(filePath + "prefixhrefsuffix", botTestPage.attributesSpan);
        } else {
            assertHrefEquals("prefixhrefsuffix", botTestPage.attributesSpan);
        }
        assertHrefNotEquals("xxx", botTestPage.attributesSpan);
        assertHrefContains("href", botTestPage.attributesSpan);
        assertHrefNotContains("xxx", botTestPage.attributesSpan);
        if (browserIsHtmlUnit()) {
            String filePath = botTestPage.url.replace("file:///", "file:/").replace("bot-test.html", "");
            assertHrefStartsWith(filePath + "prefixhref", botTestPage.attributesSpan);
        } else {
            assertHrefStartsWith("prefixhref", botTestPage.attributesSpan);
        }
        assertHrefNotStartsWith("xxx", botTestPage.attributesSpan);
        assertHrefEndsWith("hrefsuffix", botTestPage.attributesSpan);
        assertHrefNotEndsWith("xxx", botTestPage.attributesSpan);
        assertHrefMatches(".*href.*", botTestPage.attributesSpan);
        assertHrefNotMatches(".*xxx.*", botTestPage.attributesSpan);
    }

    /* Text */
    @Test
    public void textTest() {
        assertTextEquals("prefixtextsuffix", botTestPage.textSpan);
        assertTextNotEquals("xxx", botTestPage.textSpan);
        assertTextEqualsIgnoreCase("PREFIXTEXTSUFFIX", botTestPage.textSpan);
        assertTextNotEqualsIgnoreCase("xxx", botTestPage.textSpan);
        assertTextContains("text", botTestPage.textSpan);
        assertTextNotContains("xxx", botTestPage.textSpan);
        assertTextContainsIgnoreCase("TEXT", botTestPage.textSpan);
        assertTextNotContainsIgnoreCase("xxx", botTestPage.textSpan);
        assertTextStartsWith("prefixtext", botTestPage.textSpan);
        assertTextNotStartsWith("xxx", botTestPage.textSpan);
        assertTextStartsWithIgnoreCase("PREFIXTEXT", botTestPage.textSpan);
        assertTextNotStartsWithIgnoreCase("xxx", botTestPage.textSpan);
        assertTextEndsWith("textsuffix", botTestPage.textSpan);
        assertTextNotEndsWith("xxx", botTestPage.textSpan);
        assertTextEndsWithIgnoreCase("TEXTSUFFIX", botTestPage.textSpan);
        assertTextNotEndsWithIgnoreCase("xxx", botTestPage.textSpan);
        assertTextMatches(".*text.*", botTestPage.textSpan);
        assertTextNotMatches(".*xxx.*", botTestPage.textSpan);
    }

    /* Text Number */
    @Test
    public void textNumberTest() {
        // floatNumberSpan
        assertTextIsNumber(botTestPage.floatNumberSpan);
        assertTextIsNotNumber(botTestPage.textSpan);
        assertTextEquals(42.0, botTestPage.floatNumberSpan);
        assertTextNotEquals(43.0, botTestPage.floatNumberSpan);
        assertTextLessThan(43.0, botTestPage.floatNumberSpan);
        assertTextLessThanOrEquals(42.0, botTestPage.floatNumberSpan);
        assertTextGreaterThan(41.0, botTestPage.floatNumberSpan);
        assertTextGreaterThanOrEquals(42.0, botTestPage.floatNumberSpan);

        // intNumberSpan
        assertTextIsNumber(botTestPage.intNumberSpan);
        assertTextIsNotNumber(botTestPage.textSpan);
        assertTextEquals(42.0, botTestPage.intNumberSpan);
        assertTextNotEquals(43.0, botTestPage.intNumberSpan);
        assertTextLessThan(43.0, botTestPage.intNumberSpan);
        assertTextLessThanOrEquals(42.0, botTestPage.intNumberSpan);
        assertTextGreaterThan(41.0, botTestPage.intNumberSpan);
        assertTextGreaterThanOrEquals(42.0, botTestPage.intNumberSpan);
    }

    /* Selected/Deselected */
    @Test
    public void selectedDeselectedTest() {
        assertIsSelected(botTestPage.selectOption1);
        assertIsDeselected(botTestPage.selectOption2);
    }

    /* Checked/Unchecked */
    @Test
    public void checkedUncheckedTest() {
        // checkboxes
        assertIsChecked(botTestPage.checkbox1);
        assertIsUnchecked(botTestPage.checkbox2);

        // radiobuttons
        assertIsChecked(botTestPage.radiobutton1);
        assertIsUnchecked(botTestPage.radiobutton2);
    }

    /* Enabled/Disabled */
    @Test
    public void enabledDisabledTest() {
        assertIsEnabled(botTestPage.selectOption1);
        assertIsEnabled(botTestPage.selectOption2);
        assertIsDisabled(botTestPage.selectOption3);
    }

    /* Option */
    @Test
    public void optionTest() {
        // Selected/Deselected
        assertOptionIsSelected("Option 1", botTestPage.select);
        assertOptionIsDeselected("Option 2", botTestPage.select);
        assertOptionIsDeselected("Option 3", botTestPage.select);

        // Enabled/Disabled
        assertOptionIsEnabled("Option 1", botTestPage.select);
        assertOptionIsEnabled("Option 2", botTestPage.select);
        assertOptionIsDisabled("Option 3", botTestPage.select);
    }

    /* Option Value */
    @Test
    public void optionValueTest() {
        // Selected/Deselected
        assertOptionWithValueIsSelected("option1value", botTestPage.select);
        assertOptionWithValueIsDeselected("option2value", botTestPage.select);
        assertOptionWithValueIsDeselected("option3value", botTestPage.select);

        // Enabled/Disabled
        assertOptionWithValueIsEnabled("option1value", botTestPage.select);
        assertOptionWithValueIsEnabled("option2value", botTestPage.select);
        assertOptionWithValueIsDisabled("option3value", botTestPage.select);
    }

    /* Option Index */
    @Test
    public void optionIndexTest() {
        // Selected/Deselected
        assertOptionWithIndexIsSelected(0, botTestPage.select);
        assertOptionWithIndexIsDeselected(1, botTestPage.select);
        assertOptionWithIndexIsDeselected(2, botTestPage.select);

        // Enabled/Disabled
        assertOptionWithIndexIsEnabled(0, botTestPage.select);
        assertOptionWithIndexIsEnabled(1, botTestPage.select);
        assertOptionWithIndexIsDisabled(2, botTestPage.select);
    }

    @Test
    public void scrollToWebElementTest() {
        waitFor(3, TimeUnit.SECONDS);
        scrollTo(botTestPage.webElementToScrollTo);
        waitFor(3, TimeUnit.SECONDS);
    }

    @Test
    public void scrollToWebComponentTest() {
        waitFor(3, TimeUnit.SECONDS);
        scrollTo(botTestPage.webComponentToScrollTo);
        waitFor(3, TimeUnit.SECONDS);
    }
}
