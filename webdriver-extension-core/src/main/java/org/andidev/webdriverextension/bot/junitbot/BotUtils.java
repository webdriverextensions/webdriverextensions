package org.andidev.webdriverextension.bot.junitbot;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static org.andidev.webdriverextension.bot.junitbot.Bot.*;

public class BotUtils {

    public static String toString(double number) {
        if (number == (int) number) {
            return String.format("%d", (int) number);
        } else {
            return String.format("%s", number);
        }
    }

    /* Is */
    public static boolean is(String text1, String text2) {
        return StringUtils.equals(text1, text2);
    }

    public static boolean isNot(String text1, String text2) {
        return !StringUtils.equals(text1, text2);
    }

    public static boolean isContaining(String searchText, String text) {
        return StringUtils.contains(text, searchText);
    }

    public static boolean isNotContaining(String searchText, String text) {
        return !StringUtils.contains(text, searchText);
    }

    public static boolean isStartingWith(String prefix, String text) {
        return StringUtils.startsWith(text, prefix);
    }

    public static boolean isNotStartingWith(String prefix, String text) {
        return !StringUtils.startsWith(text, prefix);
    }

    public static boolean isEndingWith(String suffix, String text) {
        return StringUtils.endsWith(text, suffix);
    }

    public static boolean isNotEndingWith(String suffix, String text) {
        return !StringUtils.endsWith(text, suffix);
    }

    public static boolean isMatching(String regularExpression, String text) {
        if (text == null || regularExpression == null) {
            return false;
        }
        return text.matches(regularExpression);
    }

    public static boolean isNotMatching(String regularExpression, String text) {
        if (text == null || regularExpression == null) {
            return true;
        }
        return !text.matches(regularExpression);
    }

    /* Is Any Of */
    public static boolean isAnyOf(String[] anyOfTexts, String text) {
        for (String anyOfText : anyOfTexts) {
            if (StringUtils.equals(text, anyOfText)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotAnyOf(String[] anyOfTexts, String text) {
        return !isAnyOf(anyOfTexts, text);
    }

    public static boolean isContainingAnyOf(String[] searchTexts, String text) {
        for (String searchText : searchTexts) {
            if (StringUtils.contains(text, searchText)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotContainingAnyOf(String[] searchTexts, String text) {
        return !isContainingAnyOf(searchTexts, text);
    }

    public static boolean isStartingWithAnyOf(String[] prefixes, String text) {
        return StringUtils.startsWithAny(text, prefixes);
    }

    public static boolean isNotStartingWithAnyOf(String[] prefixes, String text) {
        return !isStartingWithAnyOf(prefixes, text);
    }

    public static boolean isEndingWithAnyOf(String[] suffix, String text) {
        return StringUtils.endsWithAny(text, suffix);
    }

    public static boolean isNotEndingWithAnyOf(String[] suffix, String text) {
        return !isEndingWithAnyOf(suffix, text);
    }

    public static boolean isMatchingAnyOf(String[] regularExpressions, String text) {
        for (String regularExpression : regularExpressions) {
            if (isMatching(regularExpression, text)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotMatchingAnyOf(String[] regularExpressions, String text) {
        return !isMatchingAnyOf(regularExpressions, text);
    }

    /* Is Ignore Case */
    public static boolean isIgnoreCase(String text1, String text2) {
        return StringUtils.equalsIgnoreCase(text1, text2);
    }

    public static boolean isNotIgnoreCase(String text1, String text2) {
        return !isIgnoreCase(text1, text2);
    }

    public static boolean isContainingIgnoreCase(String searchText, String text) {
        return StringUtils.containsIgnoreCase(text, searchText);
    }

    public static boolean isNotContainingIgnoreCase(String searchText, String text) {
        return !isContainingIgnoreCase(text, searchText);
    }

    public static boolean isStartingWithIgnoreCase(String prefix, String text) {
        return StringUtils.startsWithIgnoreCase(text, prefix);
    }

    public static boolean isNotStartingWithIgnoreCase(String prefix, String text) {
        return !isStartingWithIgnoreCase(prefix, text);
    }

    public static boolean isEndingWithIgnoreCase(String suffix, String text) {
        return StringUtils.endsWithIgnoreCase(text, suffix);
    }

    public static boolean isNotEndingWithIgnoreCase(String suffix, String text) {
        return !isEndingWithIgnoreCase(suffix, text);
    }

    /* Asserts */
    public static void assertIs(String name, String expected, String actual) {
        Assert.assertEquals(name + " is not " + expected + "!", expected, actual);
    }

    public static void assertIsNot(String name, String notExpected, String actual) {
        Assert.assertNotEquals(name + " is " + notExpected + " when it shouldn't!", notExpected, actual);
    }

    public static void assertIsMatching(String name, String regExp, String actual) {
        if (actual.matches(regExp)) {
            Assert.fail(name + ": " + actual + " is not matching " + regExp + "!");
        }
    }

    public static void assertIsNotMatching(String name, String regExp, String actual) {
        if (!actual.matches(regExp)) {
            Assert.fail(name + ": " + actual + " is matching " + regExp + " when it shouldn't!");
        }
    }

    public static void assertContains(String name, String searchText, String actual) {
        if (!StringUtils.contains(actual, searchText)) {
            Assert.fail(name + ": " + actual + " is not containing " + searchText);
        }
    }

    public static void assertNotContains(String name, String searchText, String actual) {
        if (StringUtils.contains(actual, searchText)) {
            Assert.fail(name + ": " + actual + " is containing " + searchText + " when it shouldn't!");
        }
    }

    public static void assertStartsWidth(String name, String prefix, String actual) {
        if (!StringUtils.startsWith(actual, prefix)) {
            Assert.fail(name + ": " + actual + " is not starting with " + prefix);
        }
    }

    public static void assertNotStartsWidth(String name, String prefix, String actual) {
        if (StringUtils.startsWith(actual, prefix)) {
            Assert.fail(name + ": " + actual + " is starting with " + prefix + " when it shouldn't!");
        }
    }

    public static void assertEndsWidth(String name, String suffix, String actual) {
        if (!StringUtils.startsWith(actual, suffix)) {
            Assert.fail(name + ": " + actual + " is not ending with " + suffix);
        }
    }

    public static void assertNotEndsWidth(String name, String suffix, String actual) {
        if (StringUtils.endsWith(actual, suffix)) {
            Assert.fail(name + ": " + actual + " is ending with " + suffix + " when it shouldn't!");
        }
    }

    public static boolean is(Double comparedTo, Double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static boolean isNot(double comparedTo, double number) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static boolean isSmallerThan(Double comparedTo, Double number) {
        return number < comparedTo;
    }

    public static boolean isSmallerThanOrEquals(double comparedTo, double number) {
        return number <= comparedTo;
    }

    public static boolean isLargerThan(double comparedTo, double number) {
        return number > comparedTo;
    }

    public static boolean isLargerThanOrEquals(double comparedTo, double number) {
        return number >= comparedTo;
    }

    public static void assertIs(String name, double comparedTo, double number) {
        if (isNot(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not " + comparedTo + " !");
        }
    }

    public static void assertIsNot(String name, double comparedTo, double number) {
        if (is(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is " + comparedTo + " when it shouldn't!");
        }
    }

    public static void assertIsSmallerThan(String name, double comparedTo, double number) {
        if (isLargerThanOrEquals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not smaller then " + comparedTo + " !");
        }
    }

    public static void assertIsSmallerThanOrEquals(String name, double comparedTo, double number) {
        if (isLargerThan(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not smaller then or equal " + comparedTo + " !");
        }
    }

    public static void assertIsLargerThan(String name, double comparedTo, double number) {
        if (isSmallerThanOrEquals(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not larger then " + comparedTo + " !");
        }
    }

    public static void assertIsLargerThanOrEquals(String name, double comparedTo, double number) {
        if (isSmallerThan(comparedTo, number)) {
            Assert.fail(name + ": " + number + " is not larger then or equal " + comparedTo + " !");
        }
    }

    public static String describeTag(WebElement webElement) {
        return describeTag(webElement, null);
    }

    public static String describeTag(WebElement webElement, String extraAttribute) {
        if (webElement == null) {
            return "WebElement";
        }
        boolean printExtraAttribute = extraAttribute != null && !ArrayUtils.contains(new String[]{"id", "name", "name", "class", "value", "disabled", "selected", "checked"}, extraAttribute);
        return "Tag <" + readTagName(webElement)
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
        return hasAttribute(attributeName, webElement) ? attributeName + " = '" + readAttribute(attributeName, webElement) + "' " : "";
    }

    public static String describeId(WebElement webElement) {
        return hasId(webElement) ? "id = '" + readId(webElement) + "' " : "";
    }

    public static String describeName(WebElement webElement) {
        return hasName(webElement) ? "name = '" + readName(webElement) + "' " : "";
    }

    public static String describeClass(WebElement webElement) {
        return hasClass(webElement) ? "class = '" + readClass(webElement) + "' " : "";
    }

    public static String describeValue(WebElement webElement) {
        return hasValue(webElement) ? "value = '" + readValue(webElement) + "' " : "";
    }

    /* Click */

    public static void click(WebElement webElement) {
        webElement.click();
    }

    /* Read */

    public static String read(WebElement webElement) {
        return webElement.getText();
    }


    public static Double readNumber(WebElement webElement) {
        try {
            return NumberUtils.createDouble(webElement.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }


    public static List<String> readOptions(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        List<String> optionTexts = new ArrayList<String>();
        for (WebElement option : options) {
            optionTexts.add(read(option));
        }
        return optionTexts;
    }


    public static String readUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }


    public static String readTagName(WebElement webElement) {
        return webElement.getTagName();
    }


    public static String readAttribute(String name, WebElement webElement) {
        return webElement.getAttribute(name);
    }


    public static String readId(WebElement webElement) {
        return readAttribute("id", webElement);
    }


    public static String readName(WebElement webElement) {
        return readAttribute("name", webElement);
    }


    public static String readClass(WebElement webElement) {
        return readAttribute("class", webElement);
    }


    public static String readValue(WebElement webElement) {
        return readAttribute("value", webElement);
    }


    public static String readHref(WebElement webElement) {
        return readAttribute("href", webElement);
    }


    public static boolean isSelected(WebElement webElement) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static boolean isDeselected(WebElement webElement) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static boolean isEnabled(WebElement webElement) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static boolean isDisabled(WebElement webElement) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
