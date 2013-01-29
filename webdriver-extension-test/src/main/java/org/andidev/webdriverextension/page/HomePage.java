package org.andidev.webdriverextension.page;

import org.andidev.webdriverextension.WebPage;
import static org.andidev.webdriverextension.WebDriverAssert.*;
import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.annotation.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@PageObject
public class HomePage extends WebPage {

    @FindBy(css = "nav a.examples")
    WebElement examples;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        getDriver().get("http://andidev.github.com/webdriver-extension/index.html");
    }

    @Override
    public void assertIsOpen() throws Error {
        assertIsDisplayed(examples);
    }
}
