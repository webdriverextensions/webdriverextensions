
package temp;

import org.andidev.webdriverextension.WebSite;
import org.andidev.webdriverextension.pagebots.ExamplesPageBot;
import org.andidev.webdriverextension.pagebots.HomePageBot;
import org.andidev.webdriverextension.site.WebDriverExtensionSite;
import org.openqa.selenium.WebDriver;

public abstract class SiteModel
    extends WebSite
{

    public ExamplesPageBot examplesPage;
    public HomePageBot homePage;

    public SiteModel() {
        newPageObjects();
        setSiteObjects();
    }

    public SiteModel(WebDriver driver) {
        newPageObjects();
        setSiteObjects();
        setDriver(driver);
    }

    private void newPageObjects() {
        examplesPage = new ExamplesPageBot();
        homePage = new HomePageBot();
    }

    private void setSiteObjects() {
    }

    @Override
    public void setDriver(WebDriver driver) {
        super.setDriver(driver);
        setPageObjectsDriver(driver);
    }

    private void setPageObjectsDriver(WebDriver driver) {
        examplesPage.setDriver(driver);
        homePage.setDriver(driver);
    }

}
