package com.github.webdriverextensions;

import static com.github.webdriverextensions.Bot.*;
import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.model.components.UserRow;
import com.github.webdriverextensions.generator.GeneratedWebRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;

@RunWith(WebDriverRunner.class)
@Chrome
public class WebDriverExtensionFieldDecoratorTest extends GeneratedWebRepository {

    Double delayTime = 0.0;

    @Before
    public void before() {
        open(webDriverExtensionSite.url);
        open(examplesPage);
        assertCurrentUrlEndsWith("/model-test.html");
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
        assertSizeEquals(3, examplesPage.todos);
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
    public void doubleWrappedComponents() {
        click(examplesPage.html);
        click(examplesPage.html.menu);
        waitFor(delayTime);
        assertIsDisplayed(examplesPage.html.menu.create);
        assertIsDisplayed(examplesPage.html.menu.update);
        assertIsDisplayed(examplesPage.html.menu.delete);
    }
}
