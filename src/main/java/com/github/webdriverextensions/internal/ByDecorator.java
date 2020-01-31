package com.github.webdriverextensions.internal;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface ByDecorator {
    public abstract WebElement decorate(By by);
    public abstract List<WebElement> decorateList(By by);
}
