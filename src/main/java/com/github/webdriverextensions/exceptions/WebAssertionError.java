package com.github.webdriverextensions.exceptions;

import static com.github.webdriverextensions.internal.BotUtils.htmlOf;
import com.github.webdriverextensions.internal.utils.StringUtils;
import org.openqa.selenium.WebElement;

public class WebAssertionError extends java.lang.AssertionError {

    private static final String indent = "    ";

    public WebAssertionError(WebElement webElement) {
        super(StringUtils.indent("\nElement:\n" + htmlOf(webElement), indent));
    }

    public WebAssertionError(String detailMessage, WebElement webElement) {
        super(detailMessage + StringUtils.indent("\nElement:\n" + htmlOf(webElement), indent));
    }

}
