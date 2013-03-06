package org.andidev.webdriverextension.bot.festbot;

import org.andidev.webdriverextension.bot.festbot.asserttypes.AssertTypes;
import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.bot.festbot.asserttypes.IsTypes;
import org.openqa.selenium.WebDriver;

public class Bot {

    private WebDriver driver;
    private WebElement country;
    private WebElement errorMsg;
    private WebElement loginButton;
    private WebElement username;


    public IsTypes is(WebElement webElement) {
        return new IsTypes(driver, webElement);
    }

    public AssertTypes assertThat(WebElement webElement) {
        return new AssertTypes(driver, webElement);
    }

    public Bot(WebDriver driver) {

        is(loginButton).text().equalTo("Login");
        is(loginButton).text().containing("Login");
        is(country).option("Sweden").selected();

        assertThat(errorMsg).text().is("Warning this deletes the content!");
        assertThat(username).value().is("anst07");
        assertThat(country).option("Sweden").selected();

        assertThat(errorMsg).is("Warning this deletes the content!");
        assertThat(username).is("anst07");
        assertThat(country).is("Sweden");
    }


}
