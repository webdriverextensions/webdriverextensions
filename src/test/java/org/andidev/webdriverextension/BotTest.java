package org.andidev.webdriverextension;



import org.andidev.webdriverextension.internal.WebDriverExtensionFieldDecorator;
import static org.andidev.webdriverextension.Bot.*;
import static org.andidev.webdriverextension.ThreadDriver.*;
import org.andidev.webdriverextension.site.SiteAwareRepository;
import org.junit.After;
import org.junit.Assert;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BotTest extends SiteAwareRepository {

    public BotTest() {
        ThreadDriver.setDriver(new FirefoxDriver());
        initElements(ThreadDriver.getDriver());
    }

    @Before
    public void before() {
        open(site);
        open(botTestPage);
    }

    @After
    public void after() {
        getDriver().close();
    }

    @Test
    public void firstTest() {
        Assert.assertThat(textIn(botTestPage.attributesSpan), equalTo("prefixtextsuffix"));
    }
}
