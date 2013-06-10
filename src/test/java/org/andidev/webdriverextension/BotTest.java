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
        open(site);
        open(botTestPage);
    }

    @After
    public void after() {
        getDriver().close();
    }

    @Test
    public void tagNameTest() {
        assertTagNameEquals("span", botTestPage.attributesSpan);
        assertTagNameNotEquals("xxx", botTestPage.attributesSpan);
    }

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
}
