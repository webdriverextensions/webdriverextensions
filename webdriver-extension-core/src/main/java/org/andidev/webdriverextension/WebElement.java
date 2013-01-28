package org.andidev.webdriverextension;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class WebElement implements org.openqa.selenium.WebElement {

    public By by;
    public WebDriver driver;
    public org.openqa.selenium.WebElement wrappedWebElement;
    public org.openqa.selenium.WebElement delegateWebElement;

    public void init(org.openqa.selenium.WebElement wrappedWebElement, By by) {
        this.by = by;
        this.wrappedWebElement = wrappedWebElement;
    }

    public void init(org.openqa.selenium.WebElement wrappedWebElement, By by, org.openqa.selenium.WebElement delegateWebElement) {
        this.by = by;
        this.wrappedWebElement = wrappedWebElement;
        this.delegateWebElement = delegateWebElement;
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
    public List<org.openqa.selenium.WebElement> findElements(By by) {
        return wrappedWebElement.findElements(by);
    }

    @Override
    public org.openqa.selenium.WebElement findElement(By by) {
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
    public String getCssValue(String propertyName) {
        if (delegateWebElement != null) {
            return delegateWebElement.getCssValue(propertyName);
        } else {
            return wrappedWebElement.getCssValue(propertyName);
        }
    }
}