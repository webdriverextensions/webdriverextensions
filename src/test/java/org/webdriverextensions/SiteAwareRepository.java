
package org.webdriverextensions;

import org.webdriverextensions.site.*;
import org.webdriverextensions.page.BotTestPage;
import org.webdriverextensions.page.ExamplesPage;
import org.webdriverextensions.page.HomePage;

public abstract class SiteAwareRepository
    extends WebRepository
{

    public AnotherSite anotherSite;
    public WebDriverExtensionSite site;
    public BotTestPage botTestPage;
    public ExamplesPage examplesPage;
    public HomePage homePage;

}
