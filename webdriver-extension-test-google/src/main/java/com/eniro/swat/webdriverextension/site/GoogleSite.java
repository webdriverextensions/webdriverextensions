package org.andidev.webdriverextension.site;

import org.andidev.webdriverextension.annotation.Site;
import org.openqa.selenium.WebDriver;

@Site
public class GoogleSite extends GoogleSiteBase {

    public GoogleSite(WebDriver driver) {
        super(driver);
    }

}
