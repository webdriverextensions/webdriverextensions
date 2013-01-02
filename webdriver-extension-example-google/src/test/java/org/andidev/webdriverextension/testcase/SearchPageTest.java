package org.andidev.webdriverextension.testcase;

import java.util.concurrent.TimeUnit;
import static org.andidev.webdriverextension.WebDriverBot.*;
import org.andidev.webdriverextension.site.GoogleSiteTestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchPageTest extends GoogleSiteTestBase {

    public SearchPageTest() {
        super(new FirefoxDriver());
    }
    
    @Before
    public void before() {
    }
    
    @After
    public void after() {
    }
    
    @Test
    public void searchTest() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        open(searchPage);
        type("Test", searchPage.searchQuery);
        delay(1);
//        click(searchPage.googleSearch);
        driver.close();        
    }

}
