package org.andidev.webdriverextension.page;

import org.andidev.webdriverextension.WebPage;
import static org.andidev.webdriverextension.WebDriverAssert.*;
import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Page
public class SearchPage extends WebPage {

    // Web Elements
    @FindBy(css = "[name='q']")
    public WebElement searchQuery;
    @FindBy(css = "#gbqfba")
    public WebElement googleSearch;
    @FindBy(css = "#gbqfbb")
    public WebElement imFeelingLucky;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        getDriver().get("http://www.google.com");
    }

    @Override
    public void assertIsOpen() throws Error {
        assertIsDisplayed(searchQuery);
        assertIsDisplayed(googleSearch);
        assertIsDisplayed(imFeelingLucky);
    }
}
