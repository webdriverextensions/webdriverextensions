package com.github.webdriverextensions;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;

public abstract class WebComponent implements org.openqa.selenium.WebElement, org.openqa.selenium.internal.FindsByLinkText, org.openqa.selenium.internal.FindsById, org.openqa.selenium.internal.FindsByName, org.openqa.selenium.internal.FindsByTagName, org.openqa.selenium.internal.FindsByClassName, org.openqa.selenium.internal.FindsByCssSelector, org.openqa.selenium.internal.FindsByXPath, org.openqa.selenium.internal.WrapsDriver, org.openqa.selenium.internal.Locatable {

    WebElement wrappedWebElement;
    WebElement delegateWebElement;

    public void init(WebElement wrappedWebElement) {
        this.wrappedWebElement = wrappedWebElement;
    }

    public void init(WebElement wrappedWebElement, WebElement delegateWebElement) {
        this.wrappedWebElement = wrappedWebElement;
        this.delegateWebElement = delegateWebElement;
    }

    public WebElement getWrappedWebElement() {
        return wrappedWebElement;
    }

    public WebElement getDelegateWebElement() {
        return delegateWebElement;
    }

    @Override
    public void click() {
        if (delegateWebElement != null) {
            delegateWebElement.click();
        } else {
            wrappedWebElement.click();
        }
    }

    @Override
    public void submit() {
        if (delegateWebElement != null) {
            delegateWebElement.submit();
        } else {
            wrappedWebElement.submit();
        }
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        if (delegateWebElement != null) {
            delegateWebElement.sendKeys(keysToSend);
        } else {
            wrappedWebElement.sendKeys(keysToSend);
        }
    }

    @Override
    public void clear() {
        if (delegateWebElement != null) {
            delegateWebElement.clear();
        } else {
            wrappedWebElement.clear();
        }
    }

    @Override
    public String getTagName() {
        if (delegateWebElement != null) {
            return delegateWebElement.getTagName();
        } else {
            return wrappedWebElement.getTagName();
        }
    }

    @Override
    public String getAttribute(String name) {
        if (delegateWebElement != null) {
            return delegateWebElement.getAttribute(name);
        } else {
            return wrappedWebElement.getAttribute(name);
        }
    }

    @Override
    public boolean isSelected() {
        if (delegateWebElement != null) {
            return delegateWebElement.isSelected();
        } else {
            return wrappedWebElement.isSelected();
        }
    }

    @Override
    public boolean isEnabled() {
        if (delegateWebElement != null) {
            return delegateWebElement.isEnabled();
        } else {
            return wrappedWebElement.isEnabled();
        }
    }

    @Override
    public String getText() {
        if (delegateWebElement != null) {
            return delegateWebElement.getText();
        } else {
            return wrappedWebElement.getText();
        }
    }

    @Override
    public List<WebElement> findElements(By by) {
        return wrappedWebElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return wrappedWebElement.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        if (delegateWebElement != null) {
            return delegateWebElement.isDisplayed();
        } else {
            return wrappedWebElement.isDisplayed();
        }
    }

    @Override
    public Point getLocation() {
        if (delegateWebElement != null) {
            return delegateWebElement.getLocation();
        } else {
            return wrappedWebElement.getLocation();
        }
    }

    @Override
    public Dimension getSize() {
        if (delegateWebElement != null) {
            return delegateWebElement.getSize();
        } else {
            return wrappedWebElement.getSize();
        }
    }

    @Override
    public Rectangle getRect() {
        if (delegateWebElement != null) {
            return delegateWebElement.getRect();
        } else {
            return wrappedWebElement.getRect();
        }    }

    @Override
    public String getCssValue(String propertyName) {
        if (delegateWebElement != null) {
            return delegateWebElement.getCssValue(propertyName);
        } else {
            return wrappedWebElement.getCssValue(propertyName);
        }
    }

    public void click(WebElement webElement) {
        webElement.click();
    }

    public void clear(WebElement webElement) {
        webElement.clear();
    }

    public boolean isDisplayed(WebElement webElement) {
        return webElement.isDisplayed();
    }

    @Override
    public WebElement findElementByLinkText(String using) {
            return ((org.openqa.selenium.internal.FindsByLinkText) wrappedWebElement).findElementByLinkText(using);
    }

    @Override
    public List<WebElement> findElementsByLinkText(String using) {
            return ((org.openqa.selenium.internal.FindsByLinkText) wrappedWebElement).findElementsByLinkText(using);
    }

    @Override
    public WebElement findElementByPartialLinkText(String using) {
            return ((org.openqa.selenium.internal.FindsByLinkText) wrappedWebElement).findElementByPartialLinkText(using);
    }

    @Override
    public List<WebElement> findElementsByPartialLinkText(String using) {
            return ((org.openqa.selenium.internal.FindsByLinkText) wrappedWebElement).findElementsByPartialLinkText(using);
    }

    @Override
    public WebElement findElementById(String using) {
            return ((org.openqa.selenium.internal.FindsById) wrappedWebElement).findElementById(using);
    }

    @Override
    public List<WebElement> findElementsById(String using) {
            return ((org.openqa.selenium.internal.FindsById) wrappedWebElement).findElementsById(using);
    }

    @Override
    public WebElement findElementByName(String using) {
            return ((org.openqa.selenium.internal.FindsByName) wrappedWebElement).findElementByName(using);
    }

    @Override
    public List<WebElement> findElementsByName(String using) {
            return ((org.openqa.selenium.internal.FindsByName) wrappedWebElement).findElementsByName(using);
    }

    @Override
    public WebElement findElementByTagName(String using) {
            return ((org.openqa.selenium.internal.FindsByTagName) wrappedWebElement).findElementByTagName(using);
    }

    @Override
    public List<WebElement> findElementsByTagName(String using) {
            return ((org.openqa.selenium.internal.FindsByTagName) wrappedWebElement).findElementsByTagName(using);
    }

    @Override
    public WebElement findElementByClassName(String using) {
            return ((org.openqa.selenium.internal.FindsByClassName) wrappedWebElement).findElementByClassName(using);
    }

    @Override
    public List<WebElement> findElementsByClassName(String using) {
            return ((org.openqa.selenium.internal.FindsByClassName) wrappedWebElement).findElementsByClassName(using);
    }

    @Override
    public WebElement findElementByCssSelector(String using) {
            return ((org.openqa.selenium.internal.FindsByCssSelector) wrappedWebElement).findElementByCssSelector(using);
    }

    @Override
    public List<WebElement> findElementsByCssSelector(String using) {
            return ((org.openqa.selenium.internal.FindsByCssSelector) wrappedWebElement).findElementsByCssSelector(using);
    }

    @Override
    public WebElement findElementByXPath(String using) {
            return ((org.openqa.selenium.internal.FindsByXPath) wrappedWebElement).findElementByXPath(using);
    }

    @Override
    public List<WebElement> findElementsByXPath(String using) {
        return ((org.openqa.selenium.internal.FindsByXPath) wrappedWebElement).findElementsByXPath(using);
    }

    @Override
    public WebDriver getWrappedDriver() {
        return ((org.openqa.selenium.internal.WrapsDriver) wrappedWebElement).getWrappedDriver();
    }

    @Override
    public Coordinates getCoordinates() {
        if (delegateWebElement != null) {
            return ((org.openqa.selenium.internal.Locatable) delegateWebElement).getCoordinates();
        } else {
            return ((org.openqa.selenium.internal.Locatable) wrappedWebElement).getCoordinates();
        }
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> ot) throws WebDriverException {
        return wrappedWebElement.getScreenshotAs(ot);
    }
}
