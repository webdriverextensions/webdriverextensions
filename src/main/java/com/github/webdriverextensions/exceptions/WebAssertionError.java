package com.github.webdriverextensions.exceptions;

import static com.github.webdriverextensions.internal.BotUtils.htmlOf;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.WebDriverExtensionsContext;
import com.github.webdriverextensions.internal.utils.NumberUtils;
import com.github.webdriverextensions.internal.utils.StringUtils;
import com.github.webdriverextensions.reports.Reporter;

public class WebAssertionError extends java.lang.AssertionError {

	private static final long serialVersionUID = -1660104727471354958L;

	private static final String INDENT = "    ";

	public WebAssertionError(WebElement webElement) {
		// super(StringUtils.indent("\nElement:\n" + htmlOf(webElement), INDENT));
		reporter().fail(StringUtils.indent("\nElement:\n" + htmlOf(webElement), INDENT), Bot.takeScreenShot());
	}

	public WebAssertionError(String detailMessage, WebElement webElement) {
		// super(detailMessage + getDebugInfo(webElement));
		String actualMessage = getDebugInfo(webElement);
		reporter().fail(detailMessage + INDENT + " Where actual text is displayed as " + actualMessage,
				Bot.takeScreenShot());
	}

	public WebAssertionError(String detailMessage, String name, String value) {
		// super(detailMessage + StringUtils.indent("\n" + name + ": " + value,
		// INDENT));
		reporter().fail(detailMessage + StringUtils.indent("\n" + name + ": " + value, INDENT), Bot.takeScreenShot());
	}

	public WebAssertionError(String detailMessage, String name, double actual) {
		// super(detailMessage + StringUtils.indent("\n" + name + ": " +
		// NumberUtils.toString(actual), INDENT));
		reporter().fail(detailMessage + StringUtils.indent("\n" + name + ": " + NumberUtils.toString(actual), INDENT),
				Bot.takeScreenShot());
	}

	private static String getDebugInfo(WebElement webElement) {
		if (webElement == null) {
			return "";
		}
		try {
			String actualText = htmlOf(webElement);
			return StringUtils.indent("<font color=\"green\"><b>" + actualText + "</b></font>", INDENT);
		} catch (NoSuchElementException e) {
			return "";
		}
	}

	private Reporter reporter() {
		return WebDriverExtensionsContext.getReporter();
	}

}
