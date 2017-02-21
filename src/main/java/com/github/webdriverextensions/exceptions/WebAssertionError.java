package com.github.webdriverextensions.exceptions;

import com.github.webdriverextensions.internal.utils.NumberUtils;
import com.github.webdriverextensions.internal.utils.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static com.github.webdriverextensions.internal.BotUtils.htmlOf;

public class WebAssertionError extends java.lang.AssertionError {

    private static final String INDENT = "    ";

    public WebAssertionError(WebElement webElement) {
        super(StringUtils.indent("\nElement:\n" + htmlOf(webElement), INDENT));
    }

    public WebAssertionError(String detailMessage, WebElement webElement) {
        super(detailMessage + getDebugInfo(webElement));
    }

    public WebAssertionError(String detailMessage, String name, String value) {
        super(detailMessage + StringUtils.indent("\n" + name + ": " + value, INDENT));
    }

    public WebAssertionError(String detailMessage, String name, double actual) {
        super(detailMessage + StringUtils.indent("\n" + name + ": " + NumberUtils.toString(actual), INDENT));
    }

    private static String getDebugInfo(WebElement webElement) {
        if (webElement == null) {
            return "";
        }
        try {
            return StringUtils.indent("\nElement: " + htmlOf(webElement), INDENT);
        } catch (NoSuchElementException e) {
            return "";
        }
    }

}
