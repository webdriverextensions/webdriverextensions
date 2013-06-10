
package org.andidev.webdriverextension;

import org.andidev.webdriverextension.site.*;
import org.andidev.webdriverextension.WebRepository;
import org.andidev.webdriverextension.page.BotTestPage;
import org.andidev.webdriverextension.page.ExamplesPage;
import org.andidev.webdriverextension.page.HomePage;

public abstract class SiteAwareRepository
    extends WebRepository
{

    public AnotherSite anotherSite;
    public WebDriverExtensionSite site;
    public BotTestPage botTestPage;
    public ExamplesPage examplesPage;
    public HomePage homePage;

}
