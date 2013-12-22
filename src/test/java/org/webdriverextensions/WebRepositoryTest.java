package org.webdriverextensions;

import static org.webdriverextensions.Bot.*;
import static org.webdriverextensions.ThreadDriver.*;
import org.webdriverextensions.page.ExamplesPage;
import org.webdriverextensions.page.components.Menu;
import org.webdriverextensions.page.components.HtmlComponent;
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
        open("http://webdriverextensions.github.com/webdriverextensions/index.html");
        assertCurrentUrlEndsWith("/webdriverextensions/index.html");
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
