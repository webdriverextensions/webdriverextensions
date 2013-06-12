package org.andidev.webdriverextension;

import java.util.List;
import static org.andidev.webdriverextension.Bot.*;
import static org.andidev.webdriverextension.ThreadDriver.*;
import org.junit.After;
import org.junit.Assert;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BotTest extends SiteAwareRepository {

    public BotTest() {
        ThreadDriver.setDriver(new FirefoxDriver());
        initElements(ThreadDriver.getDriver());
    }

    @Before
    public void before() {
       open(botTestPage);
    }

    @After
    public void after() {
        getDriver().close();
    }


//    /* Click */
//    @Test
//    public void clickTest() {
//    }
//
//    /* Type */
//    @Test
//    public void typeTest() {
//    }
//
//    /* Clear */
//    @Test
//    public void clearTest() {
//    }
//
//    /* Press Keys */
//    @Test
//    public void pressKeysTest() {
//    }
//
//    /* Select/Deselect */
//    @Test
//    public void selectDeselectTest() {
//    }
//
//    /* Check/Uncheck */
//    @Test
//    public void checkUncheckTest() {
//    }
//
//    /* Open */
//    @Test
//    public void openTest() {
//    }
//
//    /* Wait For */
//    @Test
//    public void waitForTest() {
//    }
//
//    /* Debug */
//    @Test
//    public void debugTest() {
//    }
//
//    /* Is Open */
//    @Test
//    public void isOpenTest() {
//    }
//
//    /* Is Display */
//    @Test
//    public void isDisplayedTest() {
//    }
//
//    /* Size */
//    @Test
//    public void sizeTest() {
//    }

    /* Url */
    @Test
    public void urlTest() {
        assertUrlEquals("http://andidev.github.io/webdriver-extension/bot-test.html");
        assertUrlNotEquals("xxx");
        assertUrlContains("webdriver-extension");
        assertUrlNotContains("xxx");
        assertUrlStartsWith("http://andidev.github.io");
        assertUrlNotStartsWith("xxx");
        assertUrlEndsWith("webdriver-extension/bot-test.html");
        assertUrlNotEndsWith("xxx");
        assertUrlMatches(".*webdriver-extension.*");
        assertUrlNotMatches(".*xxx.*");
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
        assertValueNotEquals(42.0, botTestPage.floatNumberInput);
        assertValueLessThan(43.0, botTestPage.floatNumberInput);
        assertValueLessThanOrEquals(42.0, botTestPage.floatNumberInput);
        assertValueGreaterThan(41.0, botTestPage.floatNumberInput);
        assertValueGreaterThanOrEquals(42.0, botTestPage.floatNumberInput);

        // intNumberInput
        assertValueIsNumber(botTestPage.intNumberInput);
        assertValueIsNotNumber(botTestPage.textInput);
        assertValueEquals(42.0, botTestPage.intNumberInput);
        assertValueNotEquals(42.0, botTestPage.intNumberInput);
        assertValueLessThan(43.0, botTestPage.intNumberInput);
        assertValueLessThanOrEquals(42.0, botTestPage.intNumberInput);
        assertValueGreaterThan(41.0, botTestPage.intNumberInput);
        assertValueGreaterThanOrEquals(42.0, botTestPage.intNumberInput);
    }

    /* Href */
    @Test
    public void hrefTest() {
        assertHrefEquals("prefixhrefsuffix", botTestPage.attributesSpan);
        assertHrefNotEquals("xxx", botTestPage.attributesSpan);
        assertHrefContains("href", botTestPage.attributesSpan);
        assertHrefNotContains("xxx", botTestPage.attributesSpan);
        assertHrefStartsWith("prefixhref", botTestPage.attributesSpan);
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
        assertTextContains("text", botTestPage.textSpan);
        assertTextNotContains("xxx", botTestPage.textSpan);
        assertTextStartsWith("prefixtext", botTestPage.textSpan);
        assertTextNotStartsWith("xxx", botTestPage.textSpan);
        assertTextEndsWith("textsuffix", botTestPage.textSpan);
        assertTextNotEndsWith("xxx", botTestPage.textSpan);
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
        assertTextNotEquals(42.0, botTestPage.floatNumberSpan);
        assertTextLessThan(43.0, botTestPage.floatNumberSpan);
        assertTextLessThanOrEquals(42.0, botTestPage.floatNumberSpan);
        assertTextGreaterThan(41.0, botTestPage.floatNumberSpan);
        assertTextGreaterThanOrEquals(42.0, botTestPage.floatNumberSpan);

        // intNumberSpan
        assertTextIsNumber(botTestPage.intNumberSpan);
        assertTextIsNotNumber(botTestPage.textSpan);
        assertTextEquals(42.0, botTestPage.intNumberSpan);
        assertTextNotEquals(42.0, botTestPage.intNumberSpan);
        assertTextLessThan(43.0, botTestPage.intNumberSpan);
        assertTextLessThanOrEquals(42.0, botTestPage.intNumberSpan);
        assertTextGreaterThan(41.0, botTestPage.intNumberSpan);
        assertTextGreaterThanOrEquals(42.0, botTestPage.intNumberSpan);
    }

//    /* Selected/Deselected */
//    @Test
//    public void selectedDeselectedTest() {
//    }
//
//    /* Checked/Unchecked */
//    @Test
//    public void checkedUncheckedTest() {
//    }
//
//    /* Enabled/Disabled */
//    @Test
//    public void enabledDisabledTest() {
//    }
//
//    /* Option */
//    @Test
//    public void optionTest() {
//    }
//
//    /* Option Value */
//    @Test
//    public void optionValueTest() {
//    }
//
//    /* Option Index */
//    @Test
//    public void optionIndexTest() {
//    }
}
