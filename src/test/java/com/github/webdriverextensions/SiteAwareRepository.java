
package com.github.webdriverextensions;

import com.github.webdriverextensions.site.*;
import com.github.webdriverextensions.page.BotTestPage;
import com.github.webdriverextensions.page.ExamplesPage;
import com.github.webdriverextensions.page.HomePage;

public abstract class SiteAwareRepository
    extends WebRepository
{

    public AnotherSite anotherSite;
    public WebDriverExtensionSite site;
    public BotTestPage botTestPage;
    public ExamplesPage examplesPage;
    public HomePage homePage;

}
