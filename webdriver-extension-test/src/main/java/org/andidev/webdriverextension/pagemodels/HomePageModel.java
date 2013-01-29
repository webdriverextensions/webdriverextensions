package org.andidev.webdriverextension.pagemodels;

import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public abstract class HomePageModel extends WebPage {

    public HomePageModel(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "nav a.examples")
    public WebElement examples;
}
