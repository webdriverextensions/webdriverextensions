package org.andidev.webdriverextension.pagemodels;

import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.WebPage;
import org.openqa.selenium.support.FindBy;

public abstract class HomePageModel extends WebPage {

    @FindBy(css = "nav a.examples")
    public WebElement examples;
}
