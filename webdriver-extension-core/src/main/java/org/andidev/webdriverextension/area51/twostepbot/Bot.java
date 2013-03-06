package org.andidev.webdriverextension.area51.twostepbot;

import org.andidev.webdriverextension.area51.twostepbot.asserters.SelectBoxAsserter;
import org.andidev.webdriverextension.Openable;
import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.area51.twostepbot.asserters.AttributeAsserter;
import org.andidev.webdriverextension.area51.twostepbot.asserters.IsOpenAsserter;
import org.andidev.webdriverextension.area51.twostepbot.asserters.NumberAsserter;
import org.andidev.webdriverextension.area51.twostepbot.asserters.StringIsContainsAsserter;
import org.andidev.webdriverextension.area51.twostepbot.asserters.StringIsContainsIsSetAsserter;
import org.andidev.webdriverextension.area51.twostepbot.asserters.StringIsAsserter;
import org.hamcrest.core.StringContains;
import org.openqa.selenium.WebDriver;

public class Bot {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public StringIsAsserter assertTagNameOf(WebElement webElement) {
        return new StringIsAsserter(webElement.getText());
    }

    public AttributeAsserter assertAttribute(String attributeName) {
        return new AttributeAsserter(attributeName);
    }

    public StringIsContainsIsSetAsserter assertIdIn(WebElement webElement) {
        return new StringIsContainsIsSetAsserter(webElement.getAttribute("id"));
    }

    public StringIsContainsIsSetAsserter assertNameIn(WebElement webElement) {
        return new StringIsContainsIsSetAsserter(webElement.getAttribute("name"));
    }

    public StringIsContainsIsSetAsserter assertClassIn(WebElement webElement) {
        return new StringIsContainsIsSetAsserter(webElement.getAttribute("class"));
    }

    public StringIsContainsIsSetAsserter assertHrefIn(WebElement webElement) {
        return new StringIsContainsIsSetAsserter(webElement.getAttribute("href"));
    }

    public StringIsContainsAsserter assertTextIn(WebElement webElement) {
        return new StringIsContainsAsserter(webElement.getText());
    }

    public NumberAsserter assertNumberIn(WebElement webElement) {
        return new NumberAsserter(webElement.getText());
    }

    public StringIsContainsAsserter assertUrl() {
        return new StringIsContainsAsserter(getDriver().getCurrentUrl());
    }

    public IsOpenAsserter assertThat(WebElement webElement) {
        return new IsOpenAsserter(getDriver().getCurrentUrl());
    }

    public IsOpenAsserter assertThat(Openable openable) {
        return new IsOpenAsserter(getDriver().getCurrentUrl());
    }

    public SelectBoxAsserter assertSelectBox(WebElement webElement) {
        return new SelectBoxAsserter(webElement);
    }

}
