package com.eniro.swat.webdriverextension.site;

import com.eniro.swat.webdriverextension.annotation.Site;
import org.openqa.selenium.WebDriver;

@Site
public class GoogleSite extends GoogleSiteBase {

    public GoogleSite(WebDriver driver) {
        super(driver);
    }

}
