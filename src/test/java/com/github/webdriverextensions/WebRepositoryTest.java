package com.github.webdriverextensions;

import static com.github.webdriverextensions.Bot.*;
import static com.github.webdriverextensions.ThreadDriver.*;
import com.github.webdriverextensions.page.ExamplesPage;
import com.github.webdriverextensions.page.components.Menu;
import com.github.webdriverextensions.page.components.HtmlComponent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;

public class WebRepositoryTest extends WebRepository {

    Double delayTime = 0.0;
    @FindBy(css = "div.btn-group")
    Menu menu;
    @FindBy(css = "html")
    HtmlComponent html;
    ExamplesPage examplesPage;

    public WebRepositoryTest() {
        ThreadDriver.setDriver(new FirefoxDriver());
        initElements(ThreadDriver.getDriver());
    }

    @Before
    public void before() {
        open("http://webdriverextensions.github.com/webdriverextensions/model-test.html");
        assertCurrentUrlEndsWith("/webdriverextensions/model-test.html");
    }

    @After
    public void after() {
        getDriver().close();
    }

    @Test
    public void webElementsTest() {
        type("What is WebDriver", examplesPage.searchQuery);
        waitFor(delayTime);
        click(examplesPage.search);
        assertSizeEquals(3, examplesPage.rows);
        assertSizeEquals(3, examplesPage.todos);
    }

    @Test
    public void pageFactoryInitTest() {
        click(menu);
        waitFor(delayTime);
        assertIsDisplayed(menu.create);
        assertIsDisplayed(menu.update);
        assertIsDisplayed(menu.delete);
    }
}
