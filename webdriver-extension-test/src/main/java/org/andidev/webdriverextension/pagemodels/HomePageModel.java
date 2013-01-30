package org.andidev.webdriverextension.pagemodels;

import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.site.WebDriverExtensionSite;
import org.openqa.selenium.support.FindBy;

public abstract class HomePageModel extends WebPage<WebDriverExtensionSite> {

    @FindBy(css = "nav a.examples")
    public WebElement examples;
}
