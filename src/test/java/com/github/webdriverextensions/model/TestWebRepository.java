
package com.github.webdriverextensions.model;

import com.github.webdriverextensions.WebRepository;
import com.github.webdriverextensions.model.components.Menu;
import com.github.webdriverextensions.model.pages.BotTestPage;
import com.github.webdriverextensions.model.pages.ExamplesPage;
import com.github.webdriverextensions.model.pages.HomePage;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TestWebRepository extends WebRepository {

    public WebDriverExtensionSite webDriverExtensionSite;
    public HomePage homePage;
    public BotTestPage botTestPage;
    public ExamplesPage examplesPage;
    public WebDriverExtensionRepository webDriverExtensionRepository;
    @FindBy(how = How.CSS, using = ".btn-group")
    public Menu menu;

}
