package org.andidev.webdriverextension.site;

import static org.andidev.webdriverextension.WebDriverBot.*;
import org.andidev.webdriverextension.annotation.Site;
import org.openqa.selenium.WebDriver;

@Site
public class WebDriverExtensionSite extends WebDriverExtensionSiteBase {
    
    public WebDriverExtensionSite(WebDriver driver) {
        super(driver);
    }

}
