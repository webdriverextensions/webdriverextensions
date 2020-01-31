package com.github.webdriverextensions.internal;

import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.exceptions.WebAssertionError;
import static com.github.webdriverextensions.internal.utils.StringUtils.*;
import java.lang.reflect.Proxy;
import java.util.List;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BotUtils {

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

    public static Keys getPlatformControlKey() {
        return Bot.platform().equals(com.sun.jna.Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
    }

    public static String getNewTabHandle(Set<String> oldWindowHandles) {
        Bot.waitForNewTabToOpen(oldWindowHandles);
        Set<String> newWindowHandles = Bot.availableWindowHandles();
        newWindowHandles.removeAll(oldWindowHandles);
        return newWindowHandles.iterator().next();
    }

    public static int renderedPageHeight() {
        // returns full size of rendered page instead of current viewport
        String js = "var body = document.body,\n"
            + "    html = document.documentElement;\n"
            + "return Math.max( body.scrollHeight, body.offsetHeight, \n"
            + "                       html.clientHeight, html.scrollHeight, html.offsetHeight );";

        return (int) (long) Bot.executeJavascript(js);
    }
    
    public static WebElement proxyOf(WebElement element) {
        if(Proxy.isProxyClass(element.getClass())) {
            return element;
        }
        By by = By.xpath(xpathOf(element));
        return getDecoratedElement(Bot.driver(), Bot.driver(), by);
    }
    
    public static String xpathOf(WebElement element) {
//        *not implemented yet*
//        if (isPageObject(element)) {
//            return null;
//        }
        
        String result = (String) Bot.executeJavascript("function getXPath(element, generated) {"
            + "var childTag = element.tagName;"
            + "if (document.documentElement === element) {"
            + "    return '/' + document.documentElement.tagName + generated;"
            + "}"
            + "var parentElement = element.parentElement;"
            + "var childrenElements = parentElement.children;"
            + "var count = 0;"
            + "for (i = 0; i < childrenElements.length; i++) {"
            + "    var childElement = childrenElements[i];"
            + "    var childElementTag = childElement.tagName;"
            + "    if(childTag == childElementTag) {"
            + "        count += 1;"
            + "    }"
            + "    if(element == childElement) {"
            + "        return getXPath(parentElement, '/' + childTag + '[' + count + ']' + generated);"
            + "    }"
            + "}"
            + "return null;"
            + "}"
            + ""
            + "return getXPath(arguments[0], '');", element);
        assertXPathValid(result);
        return result;
    }
    
    /**
     * Returns parent element of the element.
     * For page root (html) returns "Page Object"
     * For "Page Object" returns null
     */
    public static WebElement parentOf(WebElement element) {
        return (WebElement) Bot.executeJavascript("return arguments[0].parentNode;", element);
    }
    
    public static boolean isParentOf(WebElement parent, WebElement child) {
        try {
            return parentOf(child).equals(parent);
        } catch (Exception ex) {
            return false;
        }
    }
    
    public static List<WebElement> childrenOf(WebElement element) {
        return element.findElements(By.xpath("./*"));
    }
    
    public static boolean isDocumentRoot(WebElement elem) {
        try {
            return (boolean) Bot.executeJavascript("return arguments[0] == document.documentElement;", elem);
        } catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean isPageObjectDefined() {
        WebElement documentRoot = Bot.driver().findElement(By.xpath("/*"));
        return parentOf(documentRoot) != null;
    }
    
    public static boolean isPageObject(WebElement elem) {
        try {
            return isPageObjectDefined() && parentOf(elem) == null;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean isDescendantOf(WebElement element, WebElement descendand) {
        if (element == null) {
            return false;
        }
        if (descendand == null) {
            return false;
        }
        if (element == descendand) {
            return false;
        }
        if (element.equals(descendand)) {
            return false;
        }
        if (isPageObject(element)) {
            return true;
        }
        return xpathOf(descendand).startsWith(xpathOf(element));
    }
    
    // internal
    private static String commonXPath(String s1, String s2) {
        StringBuilder result = new StringBuilder("");

        int i = 0;
        while (i < s1.length() && i < s2.length()) {
            if (s1.charAt(i) == '/' && s2.charAt(i) == '/') {
                result.append('/');
                ++i;
            } else {
                StringBuilder tokenBuilder = new StringBuilder("");
                while (i < s1.length() && i < s2.length() && s1.charAt(i) != '/') {
                    if (s1.charAt(i) == s2.charAt(i)) {
                        tokenBuilder.append(s1.charAt(i));
                    } else {
                        return result.toString();
                    }
                    ++i;
                }
                result.append(tokenBuilder.toString());
            }
        }
        return result.toString();
    }
    
    public static String relativeXPath(WebElement from, WebElement to) {
        String fromXPath = xpathOf(from);
        assertXPathValid(fromXPath);
        String toXPath = xpathOf(to);
        assertXPathValid(toXPath);
        String overlap = commonXPath(fromXPath, toXPath);
        int overlapLength = overlap.length();
        String truncatedFromXPath = fromXPath.substring(overlapLength, fromXPath.length());
        String truncatedToXPath = toXPath.substring(overlapLength, toXPath.length());
        StringBuilder result = new StringBuilder("./");
        for (int i = 0; i < StringUtils.countMatches(truncatedFromXPath, "/")
                + /* same line */ (truncatedToXPath.length() > 0 ? 1 : 0); ++i) {
            result.append("../");
        }
        result.append(truncatedToXPath);
        assertXPathValid(result.toString());
        return result.toString();
    }
    
    public static boolean isValidXPath(String xpath) {
        boolean result;
        XPathFactory factory = XPathFactory.newInstance();
        XPath test = factory.newXPath();
        try {
            System.out.println("test.compile(xpath).toString() = " + test.compile(xpath));
            result = true;
        } catch (XPathExpressionException ex) {
            result = false;
        }
        return result;
    }
    
    public static void assertXPathValid(String xpath) {
        if (!isValidXPath(xpath)) {
            throw new WebDriverExtensionException("XPATH \"" + xpath + "\" is not valid!");
        }
    }
    
    public static boolean isSiblingOf(WebElement element, WebElement sibling) {
        if (element == null) {
            return false;
        }
        if (sibling == null) {
            return false;
        }
        if (element == sibling) {
            return false;
        }
        if (element.equals(sibling)) {
            return false;
        }
        return parentOf(element) != null
                && parentOf(sibling) != null
                && parentOf(element).equals(parentOf(sibling));
    }
    
    public static WebElement getDecoratedElement(SearchContext root, WebDriver driver, By by) {
        return new WebDriverExtensionsByDecoratorFactory()
                .create(root, driver)
                .decorate(by);
    }

    public static List<WebElement> getDecoratedElementList(SearchContext root, WebDriver driver, By by) {
        return new WebDriverExtensionsByDecoratorFactory()
                .create(root, driver)
                .decorateList(by);
    }
    
    public static WebElement findByXPath(SearchContext root, String xpath) {
        return getDecoratedElement(root, Bot.driver(), By.xpath(xpath));
    }

    public static WebElement findByXPath(String xpath) {
        return findByXPath(Bot.driver(), xpath);
    }
    
    public static WebElement findByCssSelector(SearchContext root, String cssSelector) {
        return getDecoratedElement(root, Bot.driver(), By.cssSelector(cssSelector));
    }

    public static WebElement findByCssSelector(String cssSelector) {
        return findByCssSelector(Bot.driver(), cssSelector);
    }
    
    public static WebElement findById(SearchContext root, String id) {
        return getDecoratedElement(root, Bot.driver(), By.id(id));
    }

    public static WebElement findById(String id) {
        return findById(Bot.driver(), id);
    }
    
    public static WebElement findByClassName(SearchContext root, String className) {
        return getDecoratedElement(root, Bot.driver(), By.className(className));
    }

    public static WebElement findByClassName(String className) {
        return findByClassName(Bot.driver(), className);
    }
    
    public static WebElement findByLinkText(SearchContext root, String linkText) {
        return getDecoratedElement(root, Bot.driver(), By.linkText(linkText));
    }

    public static WebElement findByLinkText(String linkText) {
        return findByLinkText(Bot.driver(), linkText);
    }
    
    public static WebElement findByName(SearchContext root, String name) {
        return getDecoratedElement(root, Bot.driver(), By.name(name));
    }

    public static WebElement findByName(String name) {
        return findByName(Bot.driver(), name);
    }
    
    public static WebElement findByPartialLinkText(SearchContext root, String partialLinkText) {
        return getDecoratedElement(root, Bot.driver(), By.partialLinkText(partialLinkText));
    }

    public static WebElement findByPartialLinkText(String partialLinkText) {
        return findByPartialLinkText(Bot.driver(), partialLinkText);
    }
    
    public static WebElement findByTagName(SearchContext root, String tagName) {
        return getDecoratedElement(root, Bot.driver(), By.tagName(tagName));
    }

    public static WebElement findByTagName(String tagName) {
        return findByTagName(Bot.driver(), tagName);
    }

    public static List<WebElement> findByXPathMultiple(SearchContext root, String xpath) {
        return getDecoratedElementList(root, Bot.driver(), By.xpath(xpath));
    }
    
    public static List<WebElement> findByXPathMultiple(String xpath) {
        return findByXPathMultiple(Bot.driver(), xpath);
    }

    public static List<WebElement> findByCssSelectorMultiple(SearchContext root, String cssSelector) {
        return getDecoratedElementList(root, Bot.driver(), By.cssSelector(cssSelector));
    }

    public static List<WebElement> findByCssSelectorMultiple(String cssSelector) {
        return findByCssSelectorMultiple(Bot.driver(), cssSelector);
    }
    
    public static List<WebElement> findByIdMultiple(SearchContext root, String id) {
        return getDecoratedElementList(root, Bot.driver(), By.id(id));
    }

    public static List<WebElement> findByIdMultiple(String id) {
        return findByIdMultiple(Bot.driver(), id);
    }
    
    public static List<WebElement> findByClassNameMultiple(SearchContext root, String className) {
        return getDecoratedElementList(root, Bot.driver(), By.className(className));
    }

    public static List<WebElement> findByClassNameMultiple(String className) {
        return findByClassNameMultiple(Bot.driver(), className);
    }
    
    public static List<WebElement> findByLinkTextMultiple(SearchContext root, String linkText) {
        return getDecoratedElementList(root, Bot.driver(), By.linkText(linkText));
    }

    public static List<WebElement> findByLinkTextMultiple(String linkText) {
        return findByLinkTextMultiple(Bot.driver(), linkText);
    }
    
    public static List<WebElement> findByNameMultiple(SearchContext root, String name) {
        return getDecoratedElementList(root, Bot.driver(), By.name(name));
    }

    public static List<WebElement> findByNameMultiple(String name) {
        return findByNameMultiple(Bot.driver(), name);
    }
    
    public static List<WebElement> findByPartialLinkTextMultiple(SearchContext root, String partialLinkText) {
        return getDecoratedElementList(root, Bot.driver(), By.partialLinkText(partialLinkText));
    }

    public static List<WebElement> findByPartialLinkTextMultiple(String partialLinkText) {
        return findByPartialLinkTextMultiple(Bot.driver(), partialLinkText);
    }
    
    public static List<WebElement> findByTagNameMultiple(SearchContext root, String tagName) {
        return getDecoratedElementList(root, Bot.driver(), By.tagName(tagName));
    }

    public static List<WebElement> findByTagNameMultiple(String tagName) {
        return findByTagNameMultiple(Bot.driver(), tagName);
    }
}
