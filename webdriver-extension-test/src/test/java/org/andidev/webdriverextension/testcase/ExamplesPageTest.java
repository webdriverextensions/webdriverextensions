package org.andidev.webdriverextension.testcase;

import org.andidev.webdriverextension.HtmlTag;
import static org.andidev.webdriverextension.WebDriverAssert.*;
import static org.andidev.webdriverextension.WebDriverBot.*;
import org.andidev.webdriverextension.models.UserRow;
import org.andidev.webdriverextension.site.WebDriverExtensionSiteTestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExamplesPageTest extends WebDriverExtensionSiteTestBase {

    public ExamplesPageTest() {
        super(new FirefoxDriver());
    }
    
    Double delayTime = 0.0;
    
    @Before
    public void before() {
        open(webDriverExtensionSite);
        open(examplesPage);
    }
    
    @After
    public void after() {
        driver.close();        
    }
    
    
    @Test
    public void webElementsTest() {
        type("What is WebDriver", examplesPage.searchQuery);
        delay(delayTime);
        click(examplesPage.search);
    }

    @Test
    public void extendedWebElementsTest() {
        click(examplesPage.menuButtonGroup.menu);
        delay(delayTime);
        assertIsDisplayed(examplesPage.menuButtonGroup.create);
        assertIsDisplayed(examplesPage.menuButtonGroup.update);
        assertIsDisplayed(examplesPage.menuButtonGroup.delete);
    }

    @Test
    public void listWithWebElementsTest() {
        delay(delayTime);
        for (HtmlTag todo : examplesPage.todoList) {
            assertTextEndsWith("!", todo);
        }
    }

    @Test
    public void listWithExtendedWebElementsTest() {
        UserRow userRow = examplesPage.findUserRowByFirstName("Jacob");
        delay(delayTime);
        assertText("Thornton", userRow.lastName);
    }

    @Test
    public void resetSearchContextTest() {
        // Test Search Context ROOT with WebElement
        delay(delayTime);
        assertIsDisplayed(examplesPage.userTableSearchContext.searchQuery);
    }

    @Test
    public void resetSearchContextListTest() {
        delay(delayTime);
        assertNumberOfElements(3, examplesPage.userTableSearchContext.todoList);
    }

    @Test
    public void wrapperTest() {
        click(examplesPage.menu);
        delay(delayTime);
        assertIsDisplayed(examplesPage.menu.create);
        assertIsDisplayed(examplesPage.menu.update);
        assertIsDisplayed(examplesPage.menu.delete);
    }
}
