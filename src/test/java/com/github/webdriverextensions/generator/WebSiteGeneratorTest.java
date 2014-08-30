package com.github.webdriverextensions.generator;

import static com.github.webdriverextensions.Bot.assertIsDisplayed;
import static com.github.webdriverextensions.Bot.assertIsOpen;
import static com.github.webdriverextensions.Bot.click;
import static com.github.webdriverextensions.Bot.open;
import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.site.WebDriverExtensionSite;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(WebDriverRunner.class)
@Chrome
public class WebSiteGeneratorTest {

    public final String url = "file://" + getClass().getResource("/html/model-test.html").getPath();

    WebDriverExtensionSite webDriverExtensionSite;

    @Test
    public void webPageTest() {
        open(webDriverExtensionSite.examplesPage);
        assertIsOpen(webDriverExtensionSite.examplesPage);
    }

    @Test
    public void webRepositoryTest() {
        open(webDriverExtensionSite.webDriverExtensionRepository.url);
        assertIsOpen(webDriverExtensionSite.webDriverExtensionRepository.examplesPage);
    }

    @Test
    public void webComponentTest() {
        open(url);
        click(webDriverExtensionSite.menu);
        assertIsDisplayed(webDriverExtensionSite.menu.create);
        assertIsDisplayed(webDriverExtensionSite.menu.update);
        assertIsDisplayed(webDriverExtensionSite.menu.delete);
    }
}
