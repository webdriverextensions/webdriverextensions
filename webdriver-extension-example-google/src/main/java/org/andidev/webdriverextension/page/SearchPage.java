package org.andidev.webdriverextension.page;

import org.andidev.webdriverextension.DefaultHtmlTagFieldDecorator;
import org.andidev.webdriverextension.HtmlTag;
import static org.andidev.webdriverextension.WebDriverAssert.*;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.annotation.Page;
import static org.junit.Assert.fail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Page
public class SearchPage extends WebPage<SearchPage> {

    // Web Elements
    @FindBy(css="[name='qsss']")
    public HtmlTag searchQuery;
    @FindBy(css="#gbqfba")
    public HtmlTag googleSearch;
    @FindBy(css="#gbqfbb")
    public HtmlTag imFeelingLucky;
    
    public SearchPage(WebDriver driver) {
        super(driver);
        // Init page elements
        PageFactory.initElements(new DefaultHtmlTagFieldDecorator(driver), this);
    }

    @Override
    public void load() {
        getDriver().get("http://www.google.com");
    }

    @Override
    public void isLoaded() throws Error {
        try {
            assertIsDisplayed(searchQuery);
            assertIsDisplayed(googleSearch);
            assertIsDisplayed(imFeelingLucky);
        } catch (AssertionError e) {
            fail(this.getClass().getSimpleName() + " is not loaded");
        }
    }
}
