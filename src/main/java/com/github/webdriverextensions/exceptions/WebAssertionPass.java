/*
 * 
 */
package com.github.webdriverextensions.exceptions;

import static com.github.webdriverextensions.internal.BotUtils.htmlOf;
import static com.github.webdriverextensions.internal.BotUtils.innerHtmlOf;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.github.webdriverextensions.WebDriverExtensionsContext;
import com.github.webdriverextensions.internal.utils.NumberUtils;
import com.github.webdriverextensions.internal.utils.StringUtils;
import com.github.webdriverextensions.reports.Reporter;

/**
 * The Class WebAssertionPass.
 */
public class WebAssertionPass extends java.lang.AssertionError {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1660104727471354958L;

	/** The Constant INDENT. */
	private static final String INDENT = "    ";

	/**
	 * Instantiates a new web assertion pass.
	 *
	 * @param webElement
	 *            the web element
	 */
	public WebAssertionPass(WebElement webElement) {
		super(StringUtils.indent("\nElement:\n" + htmlOf(webElement), INDENT));
		reporter().pass(StringUtils.indent("\nElement:\n" + htmlOf(webElement), INDENT));
	}

	/**
	 * Instantiates a new web assertion pass.
	 *
	 * @param detailMessage
	 *            the detail message
	 */
	public WebAssertionPass(String detailMessage) {
		super(StringUtils.indent("\nAlert:\n" + detailMessage, INDENT));
		reporter().pass(StringUtils.indent("\nAlert:\n" + detailMessage, INDENT));
	}

	/**
	 * Instantiates a new web assertion pass.
	 *
	 * @param detailMessage
	 *            the detail message
	 * @param webElement
	 *            the web element
	 */
	public WebAssertionPass(String detailMessage, WebElement webElement) {
		super(detailMessage + getDebugInfo(webElement));
		reporter().pass(detailMessage + detailMessage + getDebugInfo(webElement));
	}

	/**
	 * Instantiates a new web assertion pass.
	 *
	 * @param detailMessage
	 *            the detail message
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	public WebAssertionPass(String detailMessage, String name, String value) {
		super(detailMessage + StringUtils.indent("\n" + name + ": " + value, INDENT));
		reporter().pass(detailMessage + StringUtils.indent("\n" + name + ": " + value, INDENT));
	}

	/**
	 * Instantiates a new web assertion pass.
	 *
	 * @param detailMessage
	 *            the detail message
	 * @param name
	 *            the name
	 * @param actual
	 *            the actual
	 */
	public WebAssertionPass(String detailMessage, String name, double actual) {
		super(detailMessage + StringUtils.indent("\n" + name + ": " + NumberUtils.toString(actual), INDENT));
		reporter().pass(detailMessage + StringUtils.indent("\n" + name + ": " + NumberUtils.toString(actual), INDENT));
	}

	/**
	 * Gets the debug info.
	 *
	 * @param webElement
	 *            the web element
	 * @return the debug info
	 */
	private static String getDebugInfo(WebElement webElement) {
		if (webElement == null) {
			return "";
		}
		try {
			String elementType = webElement.getTagName();
			if (elementType.toLowerCase() == "input")
				return StringUtils.indent("\nElement: " + innerHtmlOf(webElement), INDENT);
			else
				return StringUtils.indent("\nElement: " + htmlOf(webElement), INDENT);
		} catch (NoSuchElementException e) {
			return "";
		}
	}

	private Reporter reporter() {
		return WebDriverExtensionsContext.getReporter();
	}
}
