package org.andidev.webdriverextension.bot.hamcrestbot;

import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.*;
import org.hamcrest.MatcherAssert;

import org.openqa.selenium.WebElement;

public class Bot {

    WebElement username;
    WebElement password;
    WebElement rememberMe;
    WebElement loginButton;
    WebElement message;

    public static void assertThat(WebElement actual, Matcher<? super WebElement> matcher) {
//        MatcherAssert.assertThat(actual, matcher);
    }
//
//    public static void assertThatAny(WebElement... actual, Matcher<? super WebElement> matcher) {
////        MatcherAssert.assertThat(actual, matcher);
//    }
//
//    // multistep
//    type("anst07").in(userName);
//    type("bad password").in(password);
//
//    click(loginButton);
//
//    assertThat(message).is("Bad username or password. Please try again!");
//
//    type("correct password").in(password);
//    click(loginButton);
//
//    assertThat(message).contains("Welcome!")
//
//
//    // multistep
//    type("anst07").in(userName);
//    type("bad password").in(password);
//
//    click(loginButton);
//
//    assertThat(message, is("Bad username or password. Please try again!"));
//
//    type("correct password", in(password));
//    click(loginButton);
//
//    assertThat(message).contains("Welcome!")
}
