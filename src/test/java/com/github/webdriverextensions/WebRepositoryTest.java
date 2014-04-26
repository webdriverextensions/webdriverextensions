package com.github.webdriverextensions;

import static com.github.webdriverextensions.Bot.*;
import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.page.ExamplesPage;
import com.github.webdriverextensions.page.components.Menu;
import com.github.webdriverextensions.page.components.HtmlComponent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.FindBy;

@RunWith(WebDriverRunner.class)
@Chrome
public class WebRepositoryTest extends WebRepository {

    public final String url = "file://" + getClass().getResource("/html/model-test.html").getPath();

    Double delayTime = 0.0;
    @FindBy(css = "div.btn-group")
    Menu menu;
    @FindBy(css = "html")
    HtmlComponent html;
    ExamplesPage examplesPage;

    public WebRepositoryTest() {
        initElements();
    }

    @Before
    public void before() {
        open(url);
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
    public void pageFactoryInitTest() {
        click(menu);
        waitFor(delayTime);
        assertIsDisplayed(menu.create);
        assertIsDisplayed(menu.update);
        assertIsDisplayed(menu.delete);
    }
}
