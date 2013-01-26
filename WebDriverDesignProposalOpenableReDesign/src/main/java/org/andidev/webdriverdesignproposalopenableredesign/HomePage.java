package org.andidev.webdriverdesignproposalopenableredesign;

import static org.andidev.webdriverdesignproposalopenableredesign.WebDriverAssert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject {
    
    TestSiteObject testSiteObject;
    
    @FindBy(css = "input.query")
    WebElement query;
    @FindBy(css = "button.search")
    WebElement search;
    
    public HomePage(WebDriver driver, TestSiteObject testSiteObject) {
        super(driver);
        this.testSiteObject = testSiteObject;
    }

    public void open() {
        
    }

    public void assertIsOpen() throws Error {
        assertIsDisplayed(query);
        assertIsDisplayed(search);
    }
}
