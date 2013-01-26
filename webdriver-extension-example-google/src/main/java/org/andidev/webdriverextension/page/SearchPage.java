package org.andidev.webdriverextension.page;

import org.andidev.webdriverextension.HtmlTag;
import org.andidev.webdriverextension.PageObject;
import static org.andidev.webdriverextension.WebDriverAssert.*;
import org.andidev.webdriverextension.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Page
public class SearchPage extends PageObject {

    // Web Elements
    @FindBy(css="[name='q']")
    public HtmlTag searchQuery;
    @FindBy(css="#gbqfba")
    public HtmlTag googleSearch;
    @FindBy(css="#gbqfbb")
    public HtmlTag imFeelingLucky;
    
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
