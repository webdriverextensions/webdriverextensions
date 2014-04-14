package com.github.webdriverextensions.internal;

import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.ThreadDriver;
import static com.github.webdriverextensions.internal.utils.StringUtils.*;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

    public class BotUtils {

    /* Tag */
    public static String tagIn(WebElement webElement) {
        if (webElement == null) {
            return "WebElement";
        }
        return "<" + Bot.tagNameOf(webElement) + prependSpaceIfNotBlank(attributesIn(webElement)) + ">"
                + appendNewLineIfStartsWithNewLine(innerHtmlOf(webElement))
                + "</" + Bot.tagNameOf(webElement) + ">";
    }

    public static String tagInWithoutHtml(WebElement webElement) {
        if (webElement == null) {
            return "WebElement";
        }
        return "<" + Bot.tagNameOf(webElement) + prependSpaceIfNotBlank(attributesIn(webElement)) + ">...</" + Bot.tagNameOf(webElement) + ">";
    }



    /* Inner Html */
    public static String innerHtmlOf(WebElement webElement) {
        return (String) ((JavascriptExecutor) ThreadDriver.getDriver()).executeScript("return arguments[0].innerHTML;", webElement);
    }



    /* Attributes */
    public static String attributesIn(WebElement webElement) {
        return (String) ((JavascriptExecutor) ThreadDriver.getDriver()).executeScript(
                "var attrsString = '';"
                + "for (var attr, i=0, attrs=arguments[0].attributes, l=attrs.length; i<l; i++) {"
                + "    var attr = attrs.item(i);"
                + "    if (i != 0) {"
                + "        attrsString = attrsString + ' ';"
                + "    }"
                + "    attrsString = attrsString + attr.nodeName + '=\"' + attr.nodeValue + '\"';"
                + "}"
                + "return attrsString;", webElement);
    }



    /* String Equals */
    public static boolean equals(String text1, String text2) {
        return StringUtils.equals(text1, text2);
    }

    public static boolean notEquals(String text1, String text2) {
        return !StringUtils.equals(text1, text2);
    }

    public static boolean contains(String searchText, String text) {
        return StringUtils.contains(text, searchText);
    }

    public static boolean notContains(String searchText, String text) {
        return !StringUtils.contains(text, searchText);
    }

    public static boolean startsWith(String prefix, String text) {
        return StringUtils.startsWith(text, prefix);
    }

    public static boolean notStartsWith(String prefix, String text) {
        return !StringUtils.startsWith(text, prefix);
    }

    public static boolean endsWith(String suffix, String text) {
        return StringUtils.endsWith(text, suffix);
    }

    public static boolean notEndsWith(String suffix, String text) {
        return !StringUtils.endsWith(text, suffix);
    }

    public static boolean matches(String regularExpression, String text) {
        if (text == null || regularExpression == null) {
            return false;
        }
        return text.matches(regularExpression);
    }

    public static boolean notMatches(String regularExpression, String text) {
        if (text == null || regularExpression == null) {
            return true;
        }
        return !text.matches(regularExpression);
    }

    public static void assertEquals(String name, String expected, String actual) {
        if (notEquals(expected, actual)) {
            throw new AssertionError(name + ": " + quote(actual) + " is not equal to " + expected + "!");
        }

    }

    public static void assertNotEquals(String name, String notExpected, String actual) {
        if (equals(notExpected, actual)) {
            throw new AssertionError(name + ": " + quote(actual) + " is equal to " + quote(notExpected) + " when it shouldn't!");
        }
    }

    public static void assertMatches(String name, String regExp, String actual) {
        if (notMatches(regExp, actual)) {
            throw new AssertionError(name + ": " + quote(actual) + " is not matching " + quote(regExp) + "!");
        }
    }

    public static void assertNotMatches(String name, String regExp, String actual) {
        if (matches(regExp, actual)) {
            throw new AssertionError(name + ": " + quote(actual) + " is matching " + quote(regExp) + " when it shouldn't!");
        }
    }

    public static void assertContains(String name, String searchText, String actual) {
        if (notContains(searchText, actual)) {
            throw new AssertionError(name + ": " + quote(actual) + " is not containing " + quote(searchText) + "!");
        }
    }

    public static void assertNotContains(String name, String searchText, String actual) {
        if (contains(searchText, actual)) {
            throw new AssertionError(name + ": " + quote(actual) + " is containing " + quote(searchText) + " when it shouldn't!");
        }
    }

    public static void assertStartsWith(String name, String prefix, String actual) {
        if (notStartsWith(prefix, actual)) {
            throw new AssertionError(name + ": " + quote(actual) + " is not starting with " + quote(prefix) + "!");
        }
    }

    public static void assertNotStartsWith(String name, String prefix, String actual) {
        if (startsWith(prefix, actual)) {
            throw new AssertionError(name + ": " + quote(actual) + " is starting with " + quote(prefix) + " when it shouldn't!");
        }
    }

    public static void assertEndsWith(String name, String suffix, String actual) {
        if (notEndsWith(suffix, actual)) {
            throw new AssertionError(name + ": " + quote(actual) + " is not ending with " + quote(suffix) + "!");
        }
    }

    public static void assertNotEndsWith(String name, String suffix, String actual) {
        if (endsWith(suffix, actual)) {
            throw new AssertionError(name + ": " + quote(actual) + " is ending with " + quote(suffix) + " when it shouldn't!");
        }
    }



    /* Double Equals */
    public static boolean equals(double comparedTo, double number) {
        return number == comparedTo;
    }

    public static boolean notEquals(double comparedTo, double number) {
        return number != comparedTo;
    }

    public static boolean lessThan(double comparedTo, double number) {
        return number < comparedTo;
    }

    public static boolean lessThanOrEquals(double comparedTo, double number) {
        return number <= comparedTo;
    }

    public static boolean greaterThan(double comparedTo, double number) {
        return number > comparedTo;
    }

    public static boolean greaterThanOrEquals(double comparedTo, double number) {
        return number >= comparedTo;
    }

    public static void assertEquals(String name, double comparedTo, double number) {
        if (notEquals(comparedTo, number)) {
            throw new AssertionError(name + ": " + quote(number) + " is not equal to " + quote(comparedTo) + "!");
        }
    }

    public static void assertNotEquals(String name, double comparedTo, double number) {
        if (equals(comparedTo, number)) {
            throw new AssertionError(name + ": " + quote(number) + " is equal to " + quote(comparedTo) + " when it shouldn't!");
        }
    }

    public static void assertLessThan(String name, double comparedTo, double number) {
        if (greaterThanOrEquals(comparedTo, number)) {
            throw new AssertionError(name + ": " + quote(number) + " is not less than " + quote(comparedTo) + "!");
        }
    }

    public static void assertLessThanOrEquals(String name, double comparedTo, double number) {
        if (greaterThan(comparedTo, number)) {
            throw new AssertionError(name + ": " + quote(number) + " is not less than or equal to " + quote(comparedTo) + "!");
        }
    }

    public static void assertGreaterThan(String name, double comparedTo, double number) {
        if (lessThanOrEquals(comparedTo, number)) {
            throw new AssertionError(name + ": " + quote(number) + " is not greater than " + quote(comparedTo) + "!");
        }
    }

    public static void assertGreaterThanOrEquals(String name, double comparedTo, double number) {
        if (lessThan(comparedTo, number)) {
            throw new AssertionError(name + ": " + quote(number) + " is not greater than or equal to " + quote(comparedTo) + "!");
        }
    }
}
