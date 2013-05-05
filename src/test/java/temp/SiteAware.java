package temp;

import org.andidev.webdriverextension.pagebots.ExamplesPageBot;
import org.andidev.webdriverextension.pagebots.HomePageBot;
import org.andidev.webdriverextension.site.WebDriverExtensionSite;
import org.openqa.selenium.WebDriver;

public abstract class SiteAware {

    private WebDriver driver;
    public WebDriverExtensionSite site;
    public ExamplesPageBot examplesPage;
    public HomePageBot homePage;

    public SiteAware() {
        site = new WebDriverExtensionSite();
        setPageObjects(site);
    }

    public SiteAware(WebDriver driver) {
        site = new WebDriverExtensionSite();
        setPageObjects(site);
        setDriver(driver);
    }

    private void setPageObjects(WebDriverExtensionSite site) {
        examplesPage = site.examplesPage;
        homePage = site.homePage;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
        site.setDriver(driver);
        setPageObjectsDriver(driver);
    }

    private void setPageObjectsDriver(WebDriver driver) {
        examplesPage.setDriver(driver);
        homePage.setDriver(driver);
    }

}
