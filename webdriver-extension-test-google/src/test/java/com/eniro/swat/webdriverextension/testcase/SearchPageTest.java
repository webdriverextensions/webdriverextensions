package org.andidev.webdriverextension.testcase;

import static org.andidev.webdriverextension.WebDriverBot.*;
import org.andidev.webdriverextension.site.GoogleSiteTestBase;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchPageTest extends GoogleSiteTestBase {

    public SearchPageTest() {
        super(new FirefoxDriver());
    }

    
    @Test
    public void searchTest() {
        open(searchPage);
        type("Test", searchPage.searchInput);
        click(searchPage.searchButton);
        
        //type("test test test test test", searchPage.searchInput);
    }

}
