package com.github.webdriverextensions;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;

public abstract class WebComponent
    implements org.openqa.selenium.WebElement, org.openqa.selenium.WrapsDriver, org.openqa.selenium.interactions.Locatable {

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
        }
    }

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

    public WebElement findElementByLinkText(String using) {
        return wrappedWebElement.findElement(By.linkText(using));
    }

    public List<WebElement> findElementsByLinkText(String using) {
        return wrappedWebElement.findElements(By.linkText(using));
    }

    public WebElement findElementByPartialLinkText(String using) {
        return wrappedWebElement.findElement(By.partialLinkText(using));
    }

    public List<WebElement> findElementsByPartialLinkText(String using) {
        return wrappedWebElement.findElements(By.partialLinkText(using));
    }

    public WebElement findElementById(String using) {
        return wrappedWebElement.findElement(By.id(using));
    }

    public List<WebElement> findElementsById(String using) {
        return wrappedWebElement.findElements(By.id(using));
    }

    public WebElement findElementByName(String using) {
        return wrappedWebElement.findElement(By.name(using));
    }

    public List<WebElement> findElementsByName(String using) {
        return wrappedWebElement.findElements(By.name(using));
    }

    public WebElement findElementByTagName(String using) {
        return wrappedWebElement.findElement(By.tagName(using));
    }

    public List<WebElement> findElementsByTagName(String using) {
        return wrappedWebElement.findElements(By.tagName(using));
    }

    public WebElement findElementByClassName(String using) {
        return wrappedWebElement.findElement(By.className(using));
    }

    public List<WebElement> findElementsByClassName(String using) {
        return wrappedWebElement.findElements(By.className(using));
    }

    public WebElement findElementByCssSelector(String using) {
        return wrappedWebElement.findElement(By.cssSelector(using));
    }

    public List<WebElement> findElementsByCssSelector(String using) {
        return wrappedWebElement.findElements(By.cssSelector(using));
    }

    public WebElement findElementByXPath(String using) {
        return wrappedWebElement.findElement(By.xpath(using));
    }

    public List<WebElement> findElementsByXPath(String using) {
        return wrappedWebElement.findElements(By.xpath(using));
    }

    public WebDriver getWrappedDriver() {
        return ((WrapsDriver) ((WrapsElement) wrappedWebElement).getWrappedElement()).getWrappedDriver();
    }

    @Override
    public Coordinates getCoordinates() {
        if (delegateWebElement != null) {
            return ((org.openqa.selenium.interactions.Locatable) delegateWebElement).getCoordinates();
        } else {
            return ((org.openqa.selenium.interactions.Locatable) wrappedWebElement).getCoordinates();
        }
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> ot) throws WebDriverException {
        return wrappedWebElement.getScreenshotAs(ot);
    }
}
