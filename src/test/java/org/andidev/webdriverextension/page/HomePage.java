package org.andidev.webdriverextension.page;

import static org.andidev.webdriverextension.Bot.*;
import static org.andidev.webdriverextension.ThreadDriver.*;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.generator.annotations.Generate;
import org.andidev.webdriverextension.site.WebDriverExtensionSite;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Generate(name = "homePage")
public class HomePage extends WebPage<WebDriverExtensionSite> {

    @FindBy(css = "nav a.examples")
    public WebElement examples;

    @Override
    public void open() {
        getDriver().get("http://andidev.github.com/webdriver-extension/index.html");
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        assertIsDisplayed(examples);
    }
}
