package org.webdriverextensions.page;

import static org.webdriverextensions.Bot.*;
import static org.webdriverextensions.ThreadDriver.*;
import org.webdriverextensions.WebPage;
import org.webdriverextensions.generator.annotations.Generate;
import org.webdriverextensions.site.WebDriverExtensionSite;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Generate(name = "homePage")
public class HomePage extends WebPage<WebDriverExtensionSite> {

    @FindBy(css = "nav a.examples")
    public WebElement examples;

    @Override
    public void open() {
        getDriver().get("http://webdriverextensions.github.com/webdriverextensions/index.html");
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        assertIsDisplayed(examples);
    }
}
