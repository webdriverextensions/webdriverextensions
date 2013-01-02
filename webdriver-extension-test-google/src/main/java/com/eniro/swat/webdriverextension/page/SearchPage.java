package org.andidev.webdriverextension.page;

import org.andidev.webdriverextension.DefaultHtmlTagFieldDecorator;
import org.andidev.webdriverextension.HtmlTag;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Page
public class SearchPage extends WebPage<SearchPage> {

    // Web Elements
    @FindBy(name="q")
    public HtmlTag searchInput;
    @FindBy(id="gbqfsa")
    public HtmlTag searchOnGoogle;
    
    
            
    public SearchPage(WebDriver driver) {
        super(driver);
        // Init WebElements
        PageFactory.initElements(new DefaultHtmlTagFieldDecorator(driver), this);
    }

    @Override
    public void load() {
        getDriver().get("http://www.google.se");
    }

    @Override
    public void isLoaded() throws Error {
    
    }
}
