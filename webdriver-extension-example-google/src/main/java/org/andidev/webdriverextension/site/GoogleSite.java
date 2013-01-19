package org.andidev.webdriverextension.site;

import org.andidev.webdriverextension.annotation.Site;
import static org.junit.Assert.fail;
import org.openqa.selenium.WebDriver;

@Site
public class GoogleSite extends GoogleSiteBase<GoogleSite> {

    public GoogleSite(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getUrl() {
        return "https://www.google.com/";
    }

    @Override
    public void isLoaded() throws Error {
       try {
            searchPage.isLoaded();
        } catch (AssertionError e) {
            fail(this.getClass().getSimpleName() + " is not loaded");
        }
    }

}
