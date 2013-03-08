package org.andidev.webdriverextension.pagetests;

import lombok.extern.slf4j.Slf4j;
import static org.andidev.webdriverextension.WebDriverBot.*;
import org.andidev.webdriverextension.site.SiteAwareDriverAware;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

@Slf4j
public class ExamplesPageTest extends SiteAwareDriverAware {

    Double delayTime = 0.0;

    public ExamplesPageTest() {
        setDriver(new FirefoxDriver());
    }

    @Before
    public void before() {
        open(site);
        open(examplesPage);
    }

    @After
    public void after() {
        getDriver().close();
    }

    @Test
    public void webElementsTest() {
        type("What is WebDriver", examplesPage.searchQuery);
        delay(delayTime);
        click(examplesPage.search);
        assertNumberOfElements(3, examplesPage.rows);
        assertNumberOfElements(3, examplesPage.todoList);

    }
//
//    @Test
//    public void extendedWebElementsTest() {
//        click(examplesPage.menuButtonGroup.menu);
//        delay(delayTime);
//        assertIsDisplayed(examplesPage.menuButtonGroup.create);
//        assertIsDisplayed(examplesPage.menuButtonGroup.update);
//        assertIsDisplayed(examplesPage.menuButtonGroup.delete);
//    }
//
//    @Test
//    public void listWithWebElementsTest() {
//        delay(delayTime);
//        for (WebElement todo : examplesPage.todoList) {
//            assertTextEndsWith("!", todo);
//        }
//    }
//
//    @Test
//    public void listWithExtendedWebElementsTest() {
//        UserRow userRow = examplesPage.findUserRowByFirstName("Jacob");
//        delay(delayTime);
//        assertText("Thornton", userRow.lastName);
//    }
//
//    @Test
//    public void resetSearchContextTest() {
//        // Test Search Context ROOT with WebElement
//        delay(delayTime);
//        assertIsDisplayed(examplesPage.userTableSearchContext.searchQuery);
//    }
//
//    @Test
//    public void resetSearchContextListTest() {
//        delay(delayTime);
//        assertNumberOfElements(3, examplesPage.userTableSearchContext.todoList);
//    }
//
//    @Test
//    public void wrapperTest() {
//        click(examplesPage.menu);
//        delay(delayTime);
//        assertIsDisplayed(examplesPage.menu.create);
//        assertIsDisplayed(examplesPage.menu.update);
//        assertIsDisplayed(examplesPage.menu.delete);
//    }
}
