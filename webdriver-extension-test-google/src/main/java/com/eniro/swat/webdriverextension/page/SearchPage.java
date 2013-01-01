package com.eniro.swat.webdriverextension.page;

import com.eniro.swat.webdriverextension.DefaultHtmlTagFieldDecorator;
import com.eniro.swat.webdriverextension.HtmlTag;
import com.eniro.swat.webdriverextension.WebPage;
import com.eniro.swat.webdriverextension.annotation.Page;
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
