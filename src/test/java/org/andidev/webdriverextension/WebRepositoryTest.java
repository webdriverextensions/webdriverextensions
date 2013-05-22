package org.andidev.webdriverextension;

import org.andidev.webdriverextension.internal.WebDriverExtensionFieldDecorator;
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
import org.openqa.selenium.support.PageFactory;

public class WebRepositoryTest extends WebRepository {

    Double delayTime = 0.0;
    @FindBy(css = "div.btn-group")
    Menu menu;
    @FindBy(css = "html")
    HtmlContainer html;
    ExamplesPage examplesPage;

    public WebRepositoryTest() {
        setDriver(new FirefoxDriver());

        // Instantiate Top Menu Object
        this.menu = new Menu();
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(getDriver(), getDriver()), this);
    }

    @Before
    public void before() {
        open("http://andidev.github.com/webdriver-extension/index.html");
        assertUrlEndsWidth("/webdriver-extension/index.html");
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
        assertNumberOf(3, examplesPage.rows);
        assertNumberOf(3, examplesPage.todos);
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
