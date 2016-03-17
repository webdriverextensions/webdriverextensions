package com.github.webdriverextensions.internal;

import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.exceptions.WebAssertionError;
import static com.github.webdriverextensions.internal.utils.StringUtils.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

public class BotUtils {

    private BotUtils() {}
    
    /* Html */
    public static String htmlOf(WebElement webElement) {
        if (webElement == null) {
            return "Element is null";
        }
        String innerHtml = innerHtmlOf(webElement);
        if (StringUtils.isBlank(innerHtml)) {
            return "<" + Bot.tagNameOf(webElement) + prependSpaceIfNotBlank(attributesIn(webElement)) + " />";
        }
        return "<" + Bot.tagNameOf(webElement) + prependSpaceIfNotBlank(attributesIn(webElement)) + ">"
                + surroundNewLinesIfContainsNewLine(innerHtml)
                + "</" + Bot.tagNameOf(webElement) + ">";

    }

    public static String htmlOfWithoutInnerHtml(WebElement webElement) {
        if (webElement == null) {
            return "Element is null";
        }
        String innerHtml = innerHtmlOf(webElement);
        if (StringUtils.isBlank(innerHtml)) {
            return "<" + Bot.tagNameOf(webElement) + prependSpaceIfNotBlank(attributesIn(webElement)) + " />";
        }
        return "<" + Bot.tagNameOf(webElement) + prependSpaceIfNotBlank(attributesIn(webElement)) + ">...</" + Bot.tagNameOf(webElement) + ">";
    }

    public static String innerHtmlOf(WebElement webElement) {
        return (String) Bot.executeJavascript("return arguments[0].innerHTML;", webElement);
    }



    /* Attributes */
    public static String attributesIn(WebElement webElement) {
        return (String) Bot.executeJavascript(
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
    public static boolean isEqual(String text1, String text2) {
        return StringUtils.equals(text1, text2);
    }

    public static boolean notEquals(String text1, String text2) {
        return !StringUtils.equals(text1, text2);
    }

    public static boolean equalsIgnoreCase(String text1, String text2) {
        return StringUtils.equalsIgnoreCase(text1, text2);
    }

    public static boolean notEqualsIgnoreCase(String text1, String text2) {
        return !StringUtils.equalsIgnoreCase(text1, text2);
    }

    public static boolean contains(String searchText, String text) {
        return StringUtils.contains(text, searchText);
    }

    public static boolean notContains(String searchText, String text) {
        return !StringUtils.contains(text, searchText);
    }

    public static boolean containsIgnoreCase(String searchText, String text) {
        return StringUtils.containsIgnoreCase(text, searchText);
    }

    public static boolean notContainsIgnoreCase(String searchText, String text) {
        return !StringUtils.containsIgnoreCase(text, searchText);
    }

    public static boolean startsWith(String prefix, String text) {
        return StringUtils.startsWith(text, prefix);
    }

    public static boolean notStartsWith(String prefix, String text) {
        return !StringUtils.startsWith(text, prefix);
    }

    public static boolean startsWithIgnoreCase(String prefix, String text) {
        return StringUtils.startsWithIgnoreCase(text, prefix);
    }

    public static boolean notStartsWithIgnoreCase(String prefix, String text) {
        return !StringUtils.startsWithIgnoreCase(text, prefix);
    }

    public static boolean endsWith(String suffix, String text) {
        return StringUtils.endsWith(text, suffix);
    }

    public static boolean notEndsWith(String suffix, String text) {
        return !StringUtils.endsWith(text, suffix);
    }

    public static boolean endsWithIgnoreCase(String suffix, String text) {
        return StringUtils.endsWithIgnoreCase(text, suffix);
    }

    public static boolean notEndsWithIgnoreCase(String suffix, String text) {
        return !StringUtils.endsWithIgnoreCase(text, suffix);
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
            throw new WebAssertionError(name + " is not equal to " + quote(expected), name, actual);
        }

    }

    public static void assertNotEquals(String name, String notExpected, String actual) {
        if (isEqual(notExpected, actual)) {
            throw new WebAssertionError(name + " is equal to " + quote(notExpected) + " when it shouldn't", name, actual);
        }
    }

    public static void assertMatches(String name, String regExp, String actual) {
        if (notMatches(regExp, actual)) {
            throw new WebAssertionError(name + " is not matching " + quote(regExp), name, actual);
        }
    }

    public static void assertNotMatches(String name, String regExp, String actual) {
        if (matches(regExp, actual)) {
            throw new WebAssertionError(name + " is matching " + quote(regExp) + " when it shouldn't", name, actual);
        }
    }

    public static void assertContains(String name, String searchText, String actual) {
        if (notContains(searchText, actual)) {
            throw new WebAssertionError(name + " is not containing " + quote(searchText), name, actual);
        }
    }

    public static void assertNotContains(String name, String searchText, String actual) {
        if (contains(searchText, actual)) {
            throw new WebAssertionError(name + " is containing " + quote(searchText) + " when it shouldn't", name, actual);
        }
    }

    public static void assertStartsWith(String name, String prefix, String actual) {
        if (notStartsWith(prefix, actual)) {
            throw new WebAssertionError(name + " is not starting with " + quote(prefix), name, actual);
        }
    }

    public static void assertNotStartsWith(String name, String prefix, String actual) {
        if (startsWith(prefix, actual)) {
            throw new WebAssertionError(name + " is starting with " + quote(prefix) + " when it shouldn't", name, actual);
        }
    }

    public static void assertEndsWith(String name, String suffix, String actual) {
        if (notEndsWith(suffix, actual)) {
            throw new WebAssertionError(name + " is not ending with " + quote(suffix), name, actual);
        }
    }

    public static void assertNotEndsWith(String name, String suffix, String actual) {
        if (endsWith(suffix, actual)) {
            throw new WebAssertionError(name + " is ending with " + quote(suffix) + " when it shouldn't", name, actual);
        }
    }

    public static void assertEquals(String name, String expected, String actual, WebElement webElement) {
        if (notEquals(expected, actual)) {
            throw new WebAssertionError(name + " is not equal to " + quote(expected), webElement);
        }
    }

    public static void assertNotEquals(String name, String notExpected, String actual, WebElement webElement) {
        if (isEqual(notExpected, actual)) {
            throw new WebAssertionError(name + " is equal to " + quote(notExpected) + " when it shouldn't", webElement);
        }
    }

    public static void assertEqualsIgnoreCase(String name, String expected, String actual, WebElement webElement) {
        if (notEqualsIgnoreCase(expected, actual)) {
            throw new WebAssertionError(name + " is not equal to " + quote(expected), webElement);
        }
    }

    public static void assertNotEqualsIgnoreCase(String name, String notExpected, String actual, WebElement webElement) {
        if (equalsIgnoreCase(notExpected, actual)) {
            throw new WebAssertionError(name + " is equal to " + quote(notExpected) + " when it shouldn't", webElement);
        }
    }

    public static void assertMatches(String name, String regExp, String actual, WebElement webElement) {
        if (notMatches(regExp, actual)) {
            throw new WebAssertionError(name + " is not matching " + quote(regExp), webElement);
        }
    }

    public static void assertNotMatches(String name, String regExp, String actual, WebElement webElement) {
        if (matches(regExp, actual)) {
            throw new WebAssertionError(name + " is matching " + quote(regExp) + " when it shouldn't", webElement);
        }
    }

    public static void assertContains(String name, String searchText, String actual, WebElement webElement) {
        if (notContains(searchText, actual)) {
            throw new WebAssertionError(name + " is not containing " + quote(searchText), webElement);
        }
    }

    public static void assertNotContains(String name, String searchText, String actual, WebElement webElement) {
        if (contains(searchText, actual)) {
            throw new WebAssertionError(name + " is containing " + quote(searchText) + " when it shouldn't", webElement);
        }
    }

    public static void assertContainsIgnoreCase(String name, String searchText, String actual, WebElement webElement) {
        if (notContainsIgnoreCase(searchText, actual)) {
            throw new WebAssertionError(name + " is not containing " + quote(searchText), webElement);
        }
    }

    public static void assertNotContainsIgnoreCase(String name, String searchText, String actual, WebElement webElement) {
        if (containsIgnoreCase(searchText, actual)) {
            throw new WebAssertionError(name + " is containing " + quote(searchText) + " when it shouldn't", webElement);
        }
    }

    public static void assertStartsWith(String name, String prefix, String actual, WebElement webElement) {
        if (notStartsWith(prefix, actual)) {
            throw new WebAssertionError(name + " is not starting with " + quote(prefix), webElement);
        }
    }

    public static void assertNotStartsWith(String name, String prefix, String actual, WebElement webElement) {
        if (startsWith(prefix, actual)) {
            throw new WebAssertionError(name + " is starting with " + quote(prefix) + " when it shouldn't", webElement);
        }
    }

    public static void assertStartsWithIgnoreCase(String name, String prefix, String actual, WebElement webElement) {
        if (notStartsWithIgnoreCase(prefix, actual)) {
            throw new WebAssertionError(name + " is not starting with " + quote(prefix), webElement);
        }
    }

    public static void assertNotStartsWithIgnoreCase(String name, String prefix, String actual, WebElement webElement) {
        if (startsWithIgnoreCase(prefix, actual)) {
            throw new WebAssertionError(name + " is starting with " + quote(prefix) + " when it shouldn't", webElement);
        }
    }

    public static void assertEndsWith(String name, String suffix, String actual, WebElement webElement) {
        if (notEndsWith(suffix, actual)) {
            throw new WebAssertionError(name + " is not ending with " + quote(suffix), webElement);
        }
    }

    public static void assertNotEndsWith(String name, String suffix, String actual, WebElement webElement) {
        if (endsWith(suffix, actual)) {
            throw new WebAssertionError(name + " is ending with " + quote(suffix) + " when it shouldn't", webElement);
        }
    }

    public static void assertEndsWithIgnoreCase(String name, String suffix, String actual, WebElement webElement) {
        if (notEndsWithIgnoreCase(suffix, actual)) {
            throw new WebAssertionError(name + " is not ending with " + quote(suffix), webElement);
        }
    }

    public static void assertNotEndsWithIgnoreCase(String name, String suffix, String actual, WebElement webElement) {
        if (endsWithIgnoreCase(suffix, actual)) {
            throw new WebAssertionError(name + " is ending with " + quote(suffix) + " when it shouldn't", webElement);
        }
    }



    /* Double Equals */
    public static boolean isEqual(double number, double actual) {
        return actual == number;
    }

    public static boolean notEquals(double number, double actual) {
        return actual != number;
    }

    public static boolean lessThan(double number, double actual) {
        return actual < number;
    }

    public static boolean lessThanOrEquals(double number, double actual) {
        return actual <= number;
    }

    public static boolean greaterThan(double number, double actual) {
        return actual > number;
    }

    public static boolean greaterThanOrEquals(double number, double actual) {
        return actual >= number;
    }

    public static void assertEquals(String name, double number, double actual) {
        if (notEquals(number, actual)) {
            throw new WebAssertionError(name + " is not equal to " + quote(number), name, actual);
        }
    }

    public static void assertNotEquals(String name, double number, double actual) {
        if (isEqual(number, actual)) {
            throw new WebAssertionError(name + " is equal to " + quote(number) + " when it shouldn't", name, actual);
        }
    }

    public static void assertLessThan(String name, double number, double actual) {
        if (greaterThanOrEquals(number, actual)) {
            throw new WebAssertionError(name + " is not less than " + quote(number), name, actual);
        }
    }

    public static void assertLessThanOrEquals(String name, double number, double actual) {
        if (greaterThan(number, actual)) {
            throw new WebAssertionError(name + " is not less than or equal to " + quote(number), name, actual);
        }
    }

    public static void assertGreaterThan(String name, double number, double actual) {
        if (lessThanOrEquals(number, actual)) {
            throw new WebAssertionError(name + " is not greater than " + quote(number), name, actual);
        }
    }

    public static void assertGreaterThanOrEquals(String name, double number, double actual) {
        if (lessThan(number, actual)) {
            throw new WebAssertionError(name + " is not greater than or equal to " + quote(number), name, actual);
        }
    }

    public static void assertEquals(String name, double number, double actual, WebElement webElement) {
        if (notEquals(number, actual)) {
            throw new WebAssertionError(name + " is not equal to " + quote(number), webElement);
        }
    }

    public static void assertNotEquals(String name, double number, double actual, WebElement webElement) {
        if (isEqual(number, actual)) {
            throw new WebAssertionError(name + " is equal to " + quote(number) + " when it shouldn't", webElement);
        }
    }

    public static void assertLessThan(String name, double number, double actual, WebElement webElement) {
        if (greaterThanOrEquals(number, actual)) {
            throw new WebAssertionError(name + " is not less than " + quote(number), webElement);
        }
    }

    public static void assertLessThanOrEquals(String name, double number, double actual, WebElement webElement) {
        if (greaterThan(number, actual)) {
            throw new WebAssertionError(name + " is not less than or equal to " + quote(number), webElement);
        }
    }

    public static void assertGreaterThan(String name, double number, double actual, WebElement webElement) {
        if (lessThanOrEquals(number, actual)) {
            throw new WebAssertionError(name + " is not greater than " + quote(number), webElement);
        }
    }

    public static void assertGreaterThanOrEquals(String name, double number, double actual, WebElement webElement) {
        if (lessThan(number, actual)) {
            throw new WebAssertionError(name + " is not greater than or equal to " + quote(number), webElement);
        }
    }

    public static long asNanos(double time, TimeUnit unit) {
        long nanos = 0;
        switch (unit) {
            case DAYS:
                nanos = (long) (time * 24 * 60 * 60 * 1000000000);
                break;
            case HOURS:
                nanos = (long) (time * 60 * 60 * 1000000000);
                break;
            case MINUTES:
                nanos = (long) (time * 60 * 1000000000);
                break;
            case SECONDS:
                nanos = (long) (time * 1000000000);
                break;
            case MILLISECONDS:
                nanos = (long) (time * 1000000);
                break;
            case MICROSECONDS:
                nanos = (long) (time * 1000);
                break;
            case NANOSECONDS:
                nanos = (long) (time);
                break;
        }
        return nanos;
    }
}
