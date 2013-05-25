package org.andidev.webdriverextension.area51.festbot;

import java.util.List;
import static org.andidev.webdriverextension.area51.festbot.FestBot.*;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;

@Ignore
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
        textIn(username);
        optionsIn(username);
        url();

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
        is(errorMsg).text().contains("Warning");
//        is(country).option("Sweden").selected();

        // Normal asserts
        assertThat(username).value().equals("anst07");
        assertThat(errorMsg).text().contains("Warning");
//        assertThat(country).option("Sweden").isSelected();

        // Alternatives (Implement theese as well?)
//        assertThat(errorMsg).text().is().contains("Warning");
//        assertThat(country).option("Sweden").is().selected();

        // Intelligent shortcut methods
        select("Sweden").in(country);



//        is(username).equalTo("anst07");
//        is(errorMsg).contains("Warning");
//        is(country).option("Sweden").selected();
//
//        assertThat(errorMsg).is("Warning this deletes the content!");
//        assertThat(orders).is(42);
//        assertThat(totalOrders).is(42);
//        assertThat(username).is("anst07");
//        assertThat(country).is("Sweden");



    /*
     * QUESTIONS:
     * 1) KEEP JAVA CONVENTION VS INTROCUCE NEW READABLE CONVENTION
     *
     *
     * STYLE 1
     * if(isTextIn(usernameLabel).euqalTo("Username: ")) { // Do something };
     * assertTextIn(usernameLabel).euqals("Username: ");
     *
     * VS
     *
     * STYLE 2
     * if(textEquals("Username: ").in(usernameLabel)) { // Do something };
     * assertThatTextIs("Username: ").in(usernameLabel);
     *
     * VS
     *
     * STYLE 3
     * if(text().equals("Username: ").in(usernameLabel)) { // Do something };
     * assertThat().text().is("Username: ").in(usernameLabel);
     *
     */

    /*
     * TEXT
     * textIn(usernameLabel);
     * if(isTextIn(usernameLabel).euqalTo("Username: ")) { // Do something };
     * assertTextIn(usernameLabel).euqals("Username: ");
     *
     * NUMBER
     * numberIn(price);
     * isNumberIn(price).euqalTo(499);
     * assertNumberIn(price).euqals(499);
     *
     * OPTIONS
     * optionsIn(country);
     * isOptionsIn(country).containing({"Sweden", "China"}); // TODO: is or are?
     * assertOptionsIn(country).contains({"Sweden", "China"});
     *
     * URL
     * url();
     * isUrl().endingWith(".se");
     * assertUrl(price).endsWith(".se");
     *
     * ...
     *
     * ATTRIBUTE
     * attribute("class").in(menu);
     * isAttribute("class").in(menu).equalTo("menu");
     * assertAttribute("class").in(menu).equals("menu");
     *
     * CLASS
     * classIn(menu);
     * isClassIn(menu).containing("menu-border");
     * assertClassIn(menu).contains("menu-border");
     *
     * NUMBEROF
     * numberOf(links);
     * isNumberOf(links).equalTo(10);
     * assertNumberOf(links).equals(10);
     *
     * CHECKERS
     * is(price).number()
     * assert(price).isNumber();
     *
     * is(menu).havingClass("menu-border");
     * assert(menu).hasClass("menu-border");
     *
     *
     *
     */
    }
}
