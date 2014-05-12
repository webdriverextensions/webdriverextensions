package com.github.webdriverextensions.page;

import static com.github.webdriverextensions.Bot.*;
import static com.github.webdriverextensions.WebDriverExtensionsContext.*;
import com.github.webdriverextensions.WebPage;
import com.github.webdriverextensions.generator.annotations.Generate;
import com.github.webdriverextensions.site.WebDriverExtensionSite;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Generate(name = "homePage")
public class HomePage extends WebPage<WebDriverExtensionSite> {

    @FindBy(css = "nav a.examples")
    public WebElement examples;

    @Override
    public void open() {
        getDriver().get(site.url);
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        assertIsDisplayed(examples);
    }
}
