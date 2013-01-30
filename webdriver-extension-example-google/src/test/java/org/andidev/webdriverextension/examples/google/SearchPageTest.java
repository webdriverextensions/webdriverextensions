package org.andidev.webdriverextension.examples.google;

import java.util.concurrent.TimeUnit;
import static org.andidev.webdriverextension.WebDriverBot.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchPageTest extends PageAware {

    public SearchPageTest() {
        setDriver(new FirefoxDriver());
    }

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void searchTest() {
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        open(searchPage);
        type("Test", searchPage.searchQuery);
        delay(1);
//        click(searchPage.googleSearch);
        getDriver().close();
    }
}
