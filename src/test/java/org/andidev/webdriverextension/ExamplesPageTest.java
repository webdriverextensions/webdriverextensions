package org.andidev.webdriverextension;

import org.andidev.webdriverextension.internal.WebDriverExtensionFieldDecorator;
import static org.andidev.webdriverextension.JUnitBot.*;
import org.andidev.webdriverextension.page.ExamplesPage;
import org.andidev.webdriverextension.page.components.Menu;
import org.andidev.webdriverextension.page.components.HtmlContainer;
import org.andidev.webdriverextension.page.components.UserRow;
import org.andidev.webdriverextension.site.WebDriverExtensionSite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExamplesPageTest extends WebDriverExtensionSite {

    Double delayTime = 0.0;
    @FindBy(css = "div.btn-group")
    Menu menu;
    @FindBy(css = "html")
    HtmlContainer html;
    ExamplesPage examplesPage2;

    public ExamplesPageTest() {
        setDriver(new FirefoxDriver());

        // Instantiate Top Menu Object
        this.menu = new Menu();
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(getDriver(), getDriver()), this);
    }

    @Before
    public void before() {
        JUnitBot.setDriver(getDriver());
//        open(site);
        open(examplesPage);
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
//
//    @Test
//    public void extendedWebElementsTest() {
//        click(examplesPage.menuButtonGroup.menu);
//        waitFor(delayTime);
//        assertIsDisplayed(examplesPage.menuButtonGroup.create);
//        assertIsDisplayed(examplesPage.menuButtonGroup.update);
//        assertIsDisplayed(examplesPage.menuButtonGroup.delete);
//        assertIsDisplayed(examplesPage2.menuButtonGroup.create);
//        assertIsDisplayed(examplesPage2.menuButtonGroup.update);
//        assertIsDisplayed(examplesPage2.menuButtonGroup.delete);
//    }
//
//    @Test
//    public void listWithWebElementsTest() {
//        waitFor(delayTime);
//        for (WebElement todo : examplesPage.todos) {
//            assertTextEndsWith("!", todo);
//        }
//    }
//
//    @Test
//    public void listWithExtendedWebElementsTest() {
//        UserRow userRow = examplesPage.findUserRowByFirstName("Jacob");
//        waitFor(delayTime);
//        assertText("Thornton", userRow.lastName);
//    }
//
//    @Test
//    public void resetSearchContextTest() {
//        // Test Search Context ROOT with WebElement
//        waitFor(delayTime);
//        assertIsDisplayed(examplesPage.userTableSearchContext.searchQuery);
//    }
//
//    @Test
//    public void resetSearchContextListTest() {
//        waitFor(delayTime);
//        assertNumberOf(3, examplesPage.userTableSearchContext.todos);
//    }
//
//    @Test
//    public void wrapperTest() {
//        click(examplesPage.menu);
//        waitFor(delayTime);
//        assertIsDisplayed(examplesPage.menu.create);
//        assertIsDisplayed(examplesPage.menu.update);
//        assertIsDisplayed(examplesPage.menu.delete);
//    }
//
//    @Test
//    public void pageFactoryInitTest() {
//        click(menu);
//        waitFor(delayTime);
//        assertIsDisplayed(menu.create);
//        assertIsDisplayed(menu.update);
//        assertIsDisplayed(menu.delete);
//    }
//
//    @Test
//    public void doubleWrappedContainers() {
//        click(examplesPage.html);
//        click(examplesPage.html.menu);
//        waitFor(delayTime);
//        assertIsDisplayed(examplesPage.html.menu.create);
//        assertIsDisplayed(examplesPage.html.menu.update);
//        assertIsDisplayed(examplesPage.html.menu.delete);
//    }
}
