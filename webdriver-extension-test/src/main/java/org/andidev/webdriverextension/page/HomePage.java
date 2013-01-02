package org.andidev.webdriverextension.page;

import org.andidev.webdriverextension.DefaultHtmlTagFieldDecorator;
import org.andidev.webdriverextension.HtmlTag;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.annotation.Page;
import static org.junit.Assert.fail;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Page
public class HomePage extends WebPage<HomePage> {

    @FindBy(css = "nav a.examples")
    HtmlTag examples;
    
    public HomePage(WebDriver driver) {
        super(driver);
        // Init page elements
        PageFactory.initElements(new DefaultHtmlTagFieldDecorator(driver), this);
    }

    @Override
    public void load() {
    	getDriver().get("http://andidevelopment.github.com/webdriver-extension/index.html");
    }

    @Override
    public void isLoaded() throws Error {
        try {
            getDriver().findElement(By.cssSelector("body.webdriverextension-site"));
        } catch (NoSuchElementException e) {
            fail("Cannot locate HomePage");
        }
    }
}
