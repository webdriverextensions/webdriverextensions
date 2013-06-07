package org.andidev.webdriverextension;

import static org.andidev.webdriverextension.Bot.*;
import static org.andidev.webdriverextension.ThreadDriver.*;
import org.andidev.webdriverextension.page.ExamplesPage;
import org.andidev.webdriverextension.page.components.Menu;
import org.andidev.webdriverextension.page.components.HtmlContainer;
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
    HtmlContainer html;
    ExamplesPage examplesPage;

    public WebRepositoryTest() {
        ThreadDriver.setDriver(new FirefoxDriver());
        initElements(ThreadDriver.getDriver());
    }

    @Before
    public void before() {
        open("http://andidev.github.com/webdriver-extension/index.html");
        assertUrlEndsWith("/webdriver-extension/index.html");
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
