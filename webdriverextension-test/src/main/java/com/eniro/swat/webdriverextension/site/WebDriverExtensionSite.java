package com.eniro.swat.webdriverextension.site;

import static com.eniro.swat.webdriverextension.WebDriverBot.*;
import com.eniro.swat.webdriverextension.annotation.Site;
import org.openqa.selenium.WebDriver;

@Site
public class WebDriverExtensionSite extends WebDriverExtensionSiteBase {
    
    public WebDriverExtensionSite(WebDriver driver) {
        super(driver);
    }

}
