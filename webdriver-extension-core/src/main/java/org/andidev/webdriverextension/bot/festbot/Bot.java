package org.andidev.webdriverextension.bot.festbot;

import java.util.List;
import org.andidev.webdriverextension.Openable;
import org.andidev.webdriverextension.bot.festbot.asserttypes.AssertTypes;
import org.andidev.webdriverextension.bot.BotUtils;
import org.andidev.webdriverextension.bot.festbot.istypes.IsTypes;
import org.andidev.webdriverextension.bot.festbot.readtypes.ReadDriverTypes;
import org.andidev.webdriverextension.bot.festbot.readtypes.ReadTypes;
import org.andidev.webdriverextension.bot.festbot.selectoptiontypes.SelectOptionInTypes;
import org.andidev.webdriverextension.bot.festbot.selectoptiontypes.SelectOptionTypes;
import org.andidev.webdriverextension.bot.festbot.typetypes.TypeInTypes;
import org.andidev.webdriverextension.bot.festbot.typetypes.TypeTypes;
import org.andidev.webdriverextension.bot.festbot.waitfortypes.WaitForTimeTypes;
import org.andidev.webdriverextension.bot.festbot.waitfortypes.WaitForWebElementTypes;
import static org.andidev.webdriverextension.bot.junitbot.Bot.open;
import static org.andidev.webdriverextension.bot.junitbot.Bot.pressEnter;
import static org.andidev.webdriverextension.bot.junitbot.Bot.pressKeys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Bot {

    private WebDriver driver;
    private WebElement country;
    private WebElement swedenOption;
    private WebElement errorMsg;
    private WebElement loginButton;
    private WebElement username;
    private List<WebElement> orders;
    private WebElement totalOrders;
    private WebElement rememberMe;


    /* Read */
    public ReadTypes read(WebElement webElement) {
        return new ReadTypes(driver, webElement);
    }

    public ReadDriverTypes read(WebDriver driver) {
        return new ReadDriverTypes(driver);
    }

    /* Count */
    public static int count(List<WebElement> webElements) {
        return BotUtils.count(webElements);
    }

    /* Clear */
    public static void clear(WebElement webElement) {
        BotUtils.clear(webElement);
    }

    /* Type */
    public TypeTypes type() {
        return new TypeTypes();
    }

    public TypeInTypes type(String text) {
        return new TypeInTypes(text);
    }

    public TypeInTypes type(double number) {
        return new TypeInTypes(BotUtils.toString(number));
    }

    /* Clear and Type */
    public static void clearAndType(String text, WebElement webElement) {
        BotUtils.clearAndType(text, webElement);
    }

    public static void clearAndType(double number, WebElement webElement) {
        BotUtils.clearAndTypeNumber(number, webElement);
    }

    /* Press */
    public static void pressEnter(WebElement webElement) { // TODO: rethink
        BotUtils.pressEnter(webElement);
    }

    public static void pressKeys(WebElement webElement, CharSequence... keys) {  // TODO: rethink
        BotUtils.pressKeys(webElement, keys);
    }

    /* Click */
    public static void click(WebElement webElement) {
        BotUtils.click(webElement);
    }

    /* Select */
    public void select(WebElement webElement) {
        BotUtils.select(webElement);
    }

    public SelectOptionTypes select() {
        return new SelectOptionTypes();
    }

    public SelectOptionInTypes select(String text) {
        return new SelectOptionInTypes(text);
    }

    public void deselect(WebElement webElement) {
        BotUtils.deselect(webElement);
    }

    public DeselectOptionTypes deselect() {
        return new DeselectOptionTypes();
    }

    public DeselectOptionInTypes deselect(String text) {
        return new DeselectOptionInTypes(text);
    }

    /* Check */
    public static void check(WebElement webElement) {
        BotUtils.check(webElement);
    }

    public static void uncheck(WebElement webElement) {
        BotUtils.uncheck(webElement);
    }

    /* Open */
    public static void open(String url, WebDriver driver) {
        BotUtils.open(url, driver);
    }

    public static void open(Openable openable) {
        BotUtils.open(openable);
    }

    /* Wait */
    public static WaitForTimeTypes waitFor(double time) {
        return new WaitForTimeTypes(time);
    }

    public static WaitForWebElementTypes waitFor(WebElement webElement) {
        return new WaitForWebElementTypes(webElement);
    }

    /* Debug */

    /* Has */

    /* Is */
    public IsTypes is(WebElement webElement) {
        return new IsTypes(driver, webElement);
    }

    /* Select */
    public AssertTypes assertThat(WebElement webElement) {
        return new AssertTypes(driver, webElement);
    }

    public Bot(WebDriver driver) {
        // Normal reads
        read(username);
        read(username).options();
        read(driver).url();

        // Normal clear
        clear(username);

        // Normal types
        type("anst07").in(username);
//        type(42).in(totalOrders);

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
//        is(country).option("Sweden").selected();

        // Normal asserts
        assertThat(username).value().equals("anst07");
        assertThat(errorMsg).text().contains("Warning");
        assertThat(country).option("Sweden").isSelected();

        // Alternatives (Implement theese as well?)
//        assertThat(errorMsg).textIs().containing("Warning");
//        assertThat(errorMsg).text().is().containing("Warning");
//        assertThat(country).option("Sweden").is().selected();
//        assertThat(country).option("Sweden").isSelected();
//        assertThat(country).option("Sweden").textIs().containing();

        // Intelligent shortcut methods
        select("Sweden").in(country);

//        is(username).equalTo("anst07");
//        is(errorMsg).containing("Warning");
//        is(country).option("Sweden").selected();

        assertThat(errorMsg).is("Warning this deletes the content!");
//        assertThat(orders).is(42);
//        assertThat(totalOrders).is(42);
        assertThat(username).is("anst07");
        assertThat(country).is("Sweden");


        // Another alternative
//        is(Text.in(username)).equalTo("anst07");
//        is(Id.in(username)).equalTo("anst07");
//        assertThat(Id.in(username)).equalsTo("anst07");
//        assertThat(SelectedOption.in(country)).equalsTo("anst07");
    }
}
