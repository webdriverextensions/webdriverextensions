package org.andidev.webdriverextension.internal;

import org.andidev.webdriverextension.Bot;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

    public class BotUtils {

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
        Assert.assertEquals(name + " is not equal to " + expected + "!", expected, actual);
    }

    public static void assertNotEquals(String name, String notExpected, String actual) {
        Assert.assertNotEquals(name + " is equal to" + notExpected + " when it shouldn't!", notExpected, actual);
    }

    public static void assertMatches(String name, String regExp, String actual) {
        if (notMatches(regExp, actual)) {
            Assert.fail(name + ": " + actual + " is not matching " + regExp + "!");
        }
    }

    public static void assertNotMatches(String name, String regExp, String actual) {
        if (matches(regExp, actual)) {
            Assert.fail(name + ": " + actual + " is matching " + regExp + " when it shouldn't!");
        }
    }

    public static void assertContains(String name, String searchText, String actual) {
        if (notContains(searchText, actual)) {
            Assert.fail(name + ": " + actual + " is not containing " + searchText);
        }
    }

    public static void assertNotContains(String name, String searchText, String actual) {
        if (contains(searchText, actual)) {
            Assert.fail(name + ": " + actual + " is containing " + searchText + " when it shouldn't!");
        }
    }

    public static void assertStartsWith(String name, String prefix, String actual) {
        if (notStartsWith(prefix, actual)) {
            Assert.fail(name + ": " + actual + " is not starting with " + prefix);
        }
    }

    public static void assertNotStartsWith(String name, String prefix, String actual) {
        if (startsWith(prefix, actual)) {
            Assert.fail(name + ": " + actual + " is starting with " + prefix + " when it shouldn't!");
        }
    }

    public static void assertEndsWith(String name, String suffix, String actual) {
        if (notEndsWith(suffix, actual)) {
            Assert.fail(name + ": " + actual + " is not ending with " + suffix);
        }
    }

    public static void assertNotEndsWith(String name, String suffix, String actual) {
        if (endsWith(suffix, actual)) {
            Assert.fail(name + ": " + actual + " is ending with " + suffix + " when it shouldn't!");
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
            Assert.fail(name + ": " + number + " is not equal to " + comparedTo + " !");
        }
    }

    public static void assertNotEequals(String name, double comparedTo, double number) {
        if (equals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is equal to " + comparedTo + " when it shouldn't!");
        }
    }

    public static void assertLessThan(String name, double comparedTo, double number) {
        if (greaterThanOrEquals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not less than " + comparedTo + " !");
        }
    }

    public static void assertLessThanOrEquals(String name, double comparedTo, double number) {
        if (greaterThan(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not less than or equal to " + comparedTo + " !");
        }
    }

    public static void assertGreaterThan(String name, double comparedTo, double number) {
        if (lessThanOrEquals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not greater than " + comparedTo + " !");
        }
    }

    public static void assertGreaterThanOrEquals(String name, double comparedTo, double number) {
        if (lessThan(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not greater than or equal to " + comparedTo + " !");
        }
    }



    /* Describe */
    public static String describeTag(WebElement webElement) {
        return describeTag(webElement, null);
    }

    public static String describeTag(WebElement webElement, String extraAttribute) {
        if (webElement == null) {
            return "WebElement";
        }
        boolean printExtraAttribute = extraAttribute != null && !ArrayUtils.contains(new String[]{"id", "name", "name", "class", "value", "disabled", "selected", "checked"}, extraAttribute);
        return "Tag <" + Bot.tagNameOf(webElement)
                + describeId(webElement)
                + describeName(webElement)
                + describeClass(webElement)
                + describeValue(webElement)
                + describeAttribute("disabled", webElement)
                + describeAttribute("selected", webElement)
                + describeAttribute("checked", webElement)
                + (printExtraAttribute ? describeAttribute(extraAttribute, webElement) : "")
                + ">";
    }

    public static String describeAttribute(String attributeName, WebElement webElement) {
        return Bot.hasAttribute(attributeName, webElement) ? attributeName + " = '" + Bot.attributeIn(attributeName, webElement) + "' " : "";
    }

    public static String describeId(WebElement webElement) {
        return Bot.hasId(webElement) ? "id = '" + Bot.idIn(webElement) + "' " : "";
    }

    public static String describeName(WebElement webElement) {
        return Bot.hasName(webElement) ? "name = '" + Bot.nameIn(webElement) + "' " : "";
    }

    public static String describeClass(WebElement webElement) {
        return Bot.hasClass(webElement) ? "class = '" + Bot.classIn(webElement) + "' " : "";
    }

    public static String describeValue(WebElement webElement) {
        return Bot.hasValue(webElement) ? "value = '" + Bot.valueIn(webElement) + "' " : "";
    }

    public static String toString(double number) {
        if (number == (int) number) {
            return String.format("%d", (int) number);
        } else {
            return String.format("%s", number);
        }
    }
}
