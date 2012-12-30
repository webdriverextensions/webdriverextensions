package com.eniro.swat.webdriverextension;

import static com.eniro.swat.webdriverextension.WebDriverBot.*;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class WebDriverAssert {


    public static void assertIsLoaded(WebPage page) {
        delay();
        page.isLoaded();
    }

    public static void assertIsDisplayed(WebElement webElement) {
        delay();
        Assert.assertTrue(webElement.isDisplayed());
    }

    public static void assertText(String expected, WebElement webElement) {
        delay();
        Assert.assertEquals(expected, webElement.getText());
        // DEBUG TEXT: Asserted text "expected" in <tagname ...>
    }

    public static void assertTextContains(String expected, WebElement webElement) {
        delay();
        Assert.assertTrue(webElement.getText().contains(expected));
    }

    public static void assertTextEndsWith(String expected, WebElement webElement) {
        delay();
        Assert.assertTrue(webElement.getText().endsWith(expected));
    }

    public static void assertLinkUrl(String expected, WebElement webElement) {
        delay();
        Assert.assertEquals(expected, webElement.getAttribute("href"));
    }

    public static void assertUrl(WebPage page) {
        delay();
        System.out.println(page.getUrl());
        System.out.println(page.getDriver().getCurrentUrl());
        System.out.println(page.getDriver().getCurrentUrl().matches(page.getUrl()));        
        boolean urlEquals = page.getDriver().getCurrentUrl().matches(page.getUrl()); 
        Assert.assertTrue(urlEquals);
    }

    public static void assertUrlContains(String expected, WebPage page) {
        delay();
        Assert.assertTrue(page.getDriver().getCurrentUrl().contains(expected));
    }

    public static void assertHrefContains(String expected, WebElement webElement) {
        delay();
        Assert.assertTrue(webElement.getAttribute("href").contains(expected));
    }

    public static void assertHrefEndsWith(String expected, WebElement webElement) {
        delay();
        Assert.assertTrue(webElement.getAttribute("href").endsWith(expected));
    }

 //   public static void assertNumberOfElements(int numberOfElementsExpected, WebContainerList webContainerList) {

    public static void assertNumberOfElements(int numberOfElementsExpected, List<? extends WebElement> webElementList) {

        delay();
        Assert.assertEquals(numberOfElementsExpected, webElementList.size());
    }

}
