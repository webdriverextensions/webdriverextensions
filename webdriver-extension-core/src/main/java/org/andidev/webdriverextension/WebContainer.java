package org.andidev.webdriverextension;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;

public class WebContainer implements org.openqa.selenium.WebElement, org.openqa.selenium.internal.FindsByLinkText, org.openqa.selenium.internal.FindsById, org.openqa.selenium.internal.FindsByName, org.openqa.selenium.internal.FindsByTagName, org.openqa.selenium.internal.FindsByClassName, org.openqa.selenium.internal.FindsByCssSelector, org.openqa.selenium.internal.FindsByXPath, org.openqa.selenium.internal.WrapsDriver, org.openqa.selenium.internal.Locatable {

    public WebElement wrappedWebElement;
    public WebElement delegateWebElement;

    public void init(WebElement wrappedWebElement) {
        this.wrappedWebElement = wrappedWebElement;
    }

    public void init(WebElement wrappedWebElement, WebElement delegateWebElement) {
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
    public String getCssValue(String propertyName) {
        if (delegateWebElement != null) {
            return delegateWebElement.getCssValue(propertyName);
        } else {
            return wrappedWebElement.getCssValue(propertyName);
        }
    }

    public void click(WebContainer webElement) {
        webElement.click();
    }

    public void clear(WebContainer webElement) {
        webElement.clear();
    }

    public boolean isDisplayed(WebContainer webElement) {
        return webElement.isDisplayed();
    }

    @Override
    public WebElement findElementByLinkText(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WebElement> findElementsByLinkText(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WebElement findElementByPartialLinkText(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WebElement> findElementsByPartialLinkText(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WebElement findElementById(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WebElement> findElementsById(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WebElement findElementByName(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WebElement> findElementsByName(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WebElement findElementByTagName(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WebElement> findElementsByTagName(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WebElement findElementByClassName(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WebElement> findElementsByClassName(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WebElement findElementByCssSelector(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WebElement> findElementsByCssSelector(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WebElement findElementByXPath(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WebElement> findElementsByXPath(String using) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WebDriver getWrappedDriver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Point getLocationOnScreenOnceScrolledIntoView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Coordinates getCoordinates() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}