package org.andidev.webdriverextension.bot;

import java.util.List;
import static org.andidev.webdriverextension.bot.FestBot.*;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class FestBotTest {

    private WebElement country;
    private WebElement swedenOption;
    private WebElement errorMsg;
    private WebElement loginButton;
    private WebElement username;
    private List<WebElement> orders;
    private WebElement totalOrders;
    private WebElement rememberMe;

    @Test
    public void testAssertThat() {
        // Normal reads
        read(username);
        read(username).options();
        read().url();

        // Normal clear
        clear(username);

        // Normal types
        type("anst07").in(username);
        type(42).in(totalOrders);

        // Normal click
        click(loginButton);

        // Normal selects
        select(swedenOption);
        select().option("Sweden").in(country);
        select().optionWithValue("SWEDEN").in(country);
        select().optionWithIndex(3).in(country);

        // Normal checks
        check(rememberMe);
        uncheck(rememberMe);

        // Normal ises
        is(username).text().equalTo("anst07");
        is(errorMsg).text().containing("Warning");
        is(country).option("Sweden").selected();

        // Normal asserts
        assertThat(username).value().equals("anst07");
        assertThat(errorMsg).text().contains("Warning");
        assertThat(country).option("Sweden").isSelected();

        // Alternatives (Implement theese as well?)
//        assertThat(errorMsg).text().is().containing("Warning");
//        assertThat(country).option("Sweden").is().selected();

        // Intelligent shortcut methods
        select("Sweden").in(country);



        is(username).equalTo("anst07");
        is(errorMsg).containing("Warning");
        is(country).option("Sweden").selected();

        assertThat(errorMsg).is("Warning this deletes the content!");
        assertThat(orders).is(42);
        assertThat(totalOrders).is(42);
        assertThat(username).is("anst07");
        assertThat(country).is("Sweden");
    }
}
