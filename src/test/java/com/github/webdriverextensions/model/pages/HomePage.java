package com.github.webdriverextensions.model.pages;

import static com.github.webdriverextensions.Bot.*;
import static com.github.webdriverextensions.WebDriverExtensionsContext.*;
import com.github.webdriverextensions.WebPage;
import com.github.webdriverextensions.model.WebDriverExtensionSite;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends WebPage {

    WebDriverExtensionSite site;

    @FindBy(css = "nav a.examples")
    public WebElement examples;

    @Override
    public void open(Object... arguments) {
        getDriver().get(site.url);
    }

    @Override
    public void assertIsOpen(Object... arguments) throws AssertionError {
        assertIsDisplayed(examples);
    }
}
