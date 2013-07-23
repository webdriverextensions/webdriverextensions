package org.andidev.webdriverextension;

import static org.andidev.webdriverextension.Bot.*;
import static org.andidev.webdriverextension.ThreadDriver.*;
import org.andidev.webdriverextension.page.components.UserRow;
import org.andidev.webdriverextension.site.SiteAwareRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExamplesPageTest extends SiteAwareRepository {

    Double delayTime = 0.0;

    public ExamplesPageTest() {
        ThreadDriver.setDriver(new FirefoxDriver());
        initElements(ThreadDriver.getDriver());
    }

    @Before
    public void before() {
        open(site.url);
        open(examplesPage);
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
    public void extendedWebElementsTest() {
        click(examplesPage.menuButtonGroup.menu);
        waitFor(delayTime);
        assertIsDisplayed(examplesPage.menuButtonGroup.create);
        assertIsDisplayed(examplesPage.menuButtonGroup.update);
        assertIsDisplayed(examplesPage.menuButtonGroup.delete);
    }

    @Test
    public void listWithWebElementsTest() {
        waitFor(delayTime);
        for (WebElement todo : examplesPage.todos) {
            assertTextEndsWith("!", todo);
        }
    }

    @Test
    public void listWithExtendedWebElementsTest() {
        UserRow userRow = examplesPage.findUserRowByFirstName("Jacob");
        waitFor(delayTime);
        assertTextEquals("Thornton", userRow.lastName);
    }

    @Test
    public void resetSearchContextTest() {
        // Test Search Context ROOT with WebElement
        waitFor(delayTime);
        assertIsDisplayed(examplesPage.userTableSearchContext.searchQuery);
    }

    @Test
    public void resetSearchContextListTest() {
        waitFor(delayTime);
        assertSizeEquals(3, examplesPage.userTableSearchContext.todos);
    }

    @Test
    public void wrapperTest() {
        click(examplesPage.menu);
        waitFor(delayTime);
        assertIsDisplayed(examplesPage.menu.create);
        assertIsDisplayed(examplesPage.menu.update);
        assertIsDisplayed(examplesPage.menu.delete);
    }

    @Test
    public void doubleWrappedContainers() {
        click(examplesPage.html);
        click(examplesPage.html.menu);
        waitFor(delayTime);
        assertIsDisplayed(examplesPage.html.menu.create);
        assertIsDisplayed(examplesPage.html.menu.update);
        assertIsDisplayed(examplesPage.html.menu.delete);
    }
}
