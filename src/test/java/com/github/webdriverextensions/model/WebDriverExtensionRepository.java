package com.github.webdriverextensions.model;

import com.github.webdriverextensions.WebRepository;
import com.github.webdriverextensions.generator.annotations.AddToRepository;
import com.github.webdriverextensions.generator.annotations.AddToWebSite;
import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.model.pages.ExamplesPage;
import com.github.webdriverextensions.model.components.Menu;
import com.github.webdriverextensions.model.components.HtmlComponent;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.FindBy;

@RunWith(WebDriverRunner.class)
@Chrome
@AddToRepository
@AddToWebSite
public class WebDriverExtensionRepository extends WebRepository {

    public final String url = "file://" + getClass().getResource("/html/model-test.html").getPath();

    Double delayTime = 0.0;
    @FindBy(css = "div.btn-group")
    Menu menu;
    @FindBy(css = "html")
    HtmlComponent html;
    public ExamplesPage examplesPage;

}
