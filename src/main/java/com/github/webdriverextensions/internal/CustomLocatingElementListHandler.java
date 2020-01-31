
package com.github.webdriverextensions.internal;

import static com.github.webdriverextensions.internal.BotUtils.proxyOf;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CustomLocatingElementListHandler implements InvocationHandler {
  private final ElementLocator locator;

  public CustomLocatingElementListHandler(ElementLocator locator) {
    this.locator = locator;
  }

  public Object invoke(Object object, Method method, Object[] objects) throws Throwable {

    // selenium doesn't do this for field locators
    List<WebElement> trueProxy = new ArrayList<>();
    locator.findElements().forEach((element) -> {
        trueProxy.add(proxyOf(element));
    });
    
    try {
      return method.invoke(trueProxy, objects);
    } catch (InvocationTargetException e) {
      // Unwrap the underlying exception
      throw e.getCause();
    }
  }
}
