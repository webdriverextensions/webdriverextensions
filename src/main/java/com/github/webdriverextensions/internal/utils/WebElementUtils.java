/*
 * 
 */
package com.github.webdriverextensions.internal.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.WebDriverExtensionsContext;
import com.github.webdriverextensions.internal.BotUtils;
import com.github.webdriverextensions.internal.generator.ElementLocatorType;

// TODO: Auto-generated Javadoc
/**
 * The Class WebElementUtils.
 */
public class WebElementUtils {

	/**
	 * Instantiates a new web element utils.
	 */
	private WebElementUtils() {
	};

	/**
	 * Driver.
	 *
	 * @return the web driver
	 */
	/* Driver */
	public static WebDriver driver() {
		return WebDriverExtensionsContext.getDriver();
	}

	/**
	 * Gets the element text.
	 *
	 * @param element
	 *            the element
	 * @return the element text
	 */
	public static String getElementVisibleText(WebElement element) {
		String elementText = "";
		String[] attributes = new String[] { "text", "name", "id" };
		if (element != null) {
			elementText = element.getText();
			if (elementText == null || elementText.isEmpty()) {
				for (String attribute : attributes) {
					elementText = element.getAttribute(attribute);
					if (elementText != null) {
						break;
					}
				}
			}
			if (elementText != null) {
				elementText = elementText.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
				String elementType = element.getTagName();
				if (!elementType.equalsIgnoreCase("input") && !elementType.equalsIgnoreCase("select")
						&& !elementType.equalsIgnoreCase("img"))
					elementText = BotUtils.htmlOf(element);
			}
		}
		return elementText;
	}

	/**
	 * Gets the label text.
	 *
	 * @param webElement
	 *            the web element
	 * @return the label text
	 */
	public static String getLabelText(WebElement webElement) {
		String labelText = "";
		try {
			if (webElement != null) {
				String elementId = webElement.getAttribute("id");
				JavascriptExecutor jsExecutor = (JavascriptExecutor) Bot.driver();
				labelText = jsExecutor.executeScript("return $(document.getElementById('" + elementId
						+ "')).parent().prev('td').find('label').text();").toString().trim();
				if (labelText.isEmpty()) {
					labelText = jsExecutor.executeScript("return $(document.getElementById('" + elementId
							+ "')).parent().parent().prev('tr').find('label').text();").toString().trim();
				}
			}

		} catch (Exception e) {
		}
		return labelText;
	}

	/**
	 * Gets the label html.
	 *
	 * @param webElement
	 *            the web element
	 * @return the label html
	 */
	public static String getLabelHtml(WebElement webElement) {
		String labelText = "";
		Object labelObject = null;
		try {
			if (webElement != null) {
				String elementId = webElement.getAttribute("id");
				JavascriptExecutor jsExecutor = (JavascriptExecutor) WebDriverExtensionsContext.getDriverDefault();
				labelObject = jsExecutor.executeScript(
						"return $(document.getElementById('" + elementId + "')).parent().prev('td').html();");
				if (labelObject == null) {
					labelText = jsExecutor.executeScript("return $(document.getElementById('" + elementId
							+ "')).parent().parent().prev('tr').html().trim();").toString();
				}
			}

		} catch (Exception e) {
		}
		return labelText;
	}

	/**
	 * Gets the element text.
	 *
	 * @param element
	 *            the element
	 * @return the element text
	 */
	public static String getElementText(WebElement element) {
		String labelText = getLabelText(element);
		if (labelText.isEmpty()) {
			labelText = getElementVisibleText(element);
		}
		return "<b>" + labelText + "</b>";
	}

	/**
	 * Gets the frame element.
	 *
	 * @param nameOrId
	 *            the name or id
	 * @return the frame element
	 */
	public static boolean getFrameElement(String nameOrId) {
		List<WebElement> frames = WebDriverExtensionsContext.getDriverDefault().findElements(By.tagName("iframe"));
		if (frames.size() > 0) {
			try {
				for (WebElement frame : frames) {
					if (frame.getAttribute("id").equals(nameOrId) || frame.getAttribute("name").equals(nameOrId)) {
						WebDriverExtensionsContext.getDriverDefault().switchTo().frame(frame);
						return true;
					}
				}
			} catch (Exception e) {
				// Don't throw the exception
			}
		}
		return false;
	}

	/**
	 * Find web element.
	 *
	 * @param tag
	 *            the tag
	 * @param locator
	 *            the locator
	 * @param identifier
	 *            the identifier
	 * @return the web element
	 */
	public static WebElement findWebElement(HtmlTag tag, String locator, String identifier) {
		WebElement elementFound = null;
		String elementText = "";
		try {
			String elementTag = tag.toString();
			By elementBy = By.tagName(elementTag);
			List<WebElement> webElements = driver().findElements(elementBy);
			if (webElements.size() > 0) {
				for (WebElement element : webElements) {
					if (element != null) {
						if (locator == null || locator == "") {
							elementText = element.getText();
						} else {
							elementText = element.getAttribute(locator);
						}
						if (elementText != null && elementText.equalsIgnoreCase(identifier)) {
							elementFound = element;
							break;
						}
					}
				}
			}
		} catch (Exception nse) {
			WebDriverExtensionsContext.getReporter()
					.fail("Unable to find the Element with this specification.[Html tag=" + tag.toString()
							+ ", locator=" + locator + ", identifier=" + identifier, Bot.takeScreenShot());
		}
		return elementFound;
	}

	/**
	 * Find web element.
	 *
	 * @param tag
	 *            the tag
	 * @param displayText
	 *            the display text
	 * @return the web element
	 */
	public static WebElement findWebElement(HtmlTag tag, String displayText) {
		WebElement elementFound = null;
		try {
			List<WebElement> allWebElements = WebDriverExtensionsContext.getDriver().findElements(By.xpath("//*"));
			for (WebElement allWebElement : allWebElements) {
				if (allWebElements != null) {
					if (allWebElement.getTagName().equalsIgnoreCase(tag.toString())) {
						String elementText = allWebElement.getText().trim();
						if (elementText.equalsIgnoreCase(displayText) && elementText != null) {
							elementFound = allWebElement;
							break;
						}
					}
				}
			}
		} catch (Exception nse) {
			//
		}
		return elementFound;
	}

	/**
	 * Builds the element.
	 *
	 * @param elementLocatorType
	 *            the element locator type
	 * @param elementFinderText
	 *            the element finder text
	 * @return the web element
	 */
	public static WebElement buildElement(ElementLocatorType elementLocatorType, String elementFinderText) {
		WebElement elementFound = null;
		WebElement elementBuilt = null;
		By elementBy = null;
		try {
			switch (elementLocatorType) {
			case NAME:
				elementBy = By.name(elementFinderText);
				break;
			case CLASSNAME:
				elementBy = By.className(elementFinderText);
				break;
			case CSS:
				elementBy = By.cssSelector(elementFinderText);
				break;
			case ID:
				elementBy = By.id(elementFinderText);
				break;
			case LINKTEXT:
				elementBy = By.linkText(elementFinderText);
				break;
			case PARTIALLINKTEXT:
				elementBy = By.partialLinkText(elementFinderText);
				break;
			case XPATH:
				elementBy = By.xpath(elementFinderText);
				break;
			default:
				break;
			}
			elementFound = driver().findElement(elementBy);
		} catch (NoSuchElementException nse) {
			WebDriverExtensionsContext.getReporter()
					.warning("And Then Unable to Find the Element with given locator <b>"
							+ elementLocatorType.toString() + "</b> And Finder Text <b>" + elementFinderText + "</b>");
		}
		if (elementFound != null && Bot.isDisplayed(elementFound))
			elementBuilt = elementFound;
		return elementBuilt;
	}

	/**
	 * Find elements.
	 *
	 * @param elementLocatorType
	 *            the element locator type
	 * @param identifier
	 *            the identifier
	 * @return true, if successful
	 */
	public static boolean findElements(ElementLocatorType elementLocatorType, String identifier) {
		By elementBy = null;

		try {
			switch (elementLocatorType) {
			case NAME:
				elementBy = By.name(identifier);
				break;
			case CLASSNAME:
				elementBy = By.className(identifier);
				break;
			case CSS:
				elementBy = By.cssSelector(identifier);
				break;
			case ID:
				elementBy = By.id(identifier);
				break;
			case LINKTEXT:
				elementBy = By.linkText(identifier);
				break;
			case PARTIALLINKTEXT:
				elementBy = By.partialLinkText(identifier);
				break;
			case XPATH:
				elementBy = By.xpath(identifier);
				break;
			default:
				break;
			}
		} catch (Exception e) {

		}
		return driver().findElements(elementBy).size() > 0;
	}
}
