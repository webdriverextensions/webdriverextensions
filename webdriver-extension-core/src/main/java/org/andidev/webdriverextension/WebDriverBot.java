package org.andidev.webdriverextension;

import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverBot extends WebDriverAssert {

    public static double delayTime = 0.0;

    public static void check(WebElement webElement) {
        delay();
        if (!isChecked(webElement)) {
            webElement.click();
        }
    }

    public static void clear(WebElement webElement) {
        webElement.clear();
        delay();
    }

    public static void clearAndType(String str, WebElement webElement) {
        if (str == null) {
            return;
        }
        clear(webElement);
        type(str, webElement);
    }

    public static void click(WebElement webElement) {
        delay();
        webElement.click();
    }

    private static void debug(String str) {
        System.out.println(str);
    }

    public static void debugNumberOfElements(List<WebElement> webElements) {
        System.out.println("Number of " + webElements.get(0).getTagName() + "-tags: " + webElements.size());
    }

    public static void debugText(List<WebElement> webElements) {
        for (WebElement webElement : webElements) {
            debugText(webElement);
        }
    }

    public static void debugText(WebElement webElement) {
        System.out.println("Text in " + webElement.getTagName() + "-tag: " + webElement.getText());
    }

    public static void delay() {
        delay(delayTime);
    }

    public static void delay(double seconds) {
        if (seconds > 0) {
            try {
                Thread.sleep((long) (seconds * 1000));
            } catch (InterruptedException ex) {
                // Swallow exception
                ex.printStackTrace();
            }

        }
    }

    public static boolean isChecked(WebElement webElement) {
        delay();
        return webElement.isSelected();
    }

    public static boolean isDisplayed(WebElement webElement) {
        delay();
        boolean isDisplayed;
        try {
            isDisplayed = webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            isDisplayed = false;
        }
        return isDisplayed;
    }

    public static boolean isOpen(Openable openable) {
        delay();
        return openable.isOpen();
    }

    public static boolean isText(String expected, WebElement webElement) {
        delay();
        return webElement.getText().equals(expected);
    }

    public static boolean isTextContaining(String expected, WebElement webElement) {
        delay();
        return webElement.getText().contains(expected);
    }

    public static boolean isTextEndingWith(String expected, WebElement webElement) {
        delay();
        return webElement.getText().endsWith(expected);
    }

    public static boolean isTextStartingWith(String expected, WebElement webElement) {
        delay();
        return webElement.getText().startsWith(expected);
    }

    public static void open(Openable openable) {
        delay();
        openable.open();
    }

    public static void open(String url, WebDriver driver) {
        delay();
        driver.get(url);
    }

    public static void pressEnter(WebElement webElement) {
        delay();
        webElement.sendKeys(Keys.ENTER);
    }

    public static void pressKeys(WebElement webElement, CharSequence... keysToSend) {
        delay();
        webElement.sendKeys(keysToSend);
    }

    public static String read(WebElement webElement) {
        delay();
        return webElement.getText();
    }

    public static Double readAsNumber(WebElement webElement) {
        delay();
        String string = read(webElement);
        if (NumberUtils.isNumber(string)) {
            return NumberUtils.toDouble(string);
        } else {
            return null;
        }
    }

    public static void select(String optionText, WebElement webElement) {
        delay();
        new Select(webElement).selectByVisibleText(optionText);
    }

    public static void type(String str, WebElement webElement) {
        if (str == null) {
            return;
        }
        delay();
        webElement.sendKeys(str);
    }

    public static void uncheck(WebElement webElement) {
        delay();
        if (isChecked(webElement)) {
            webElement.click();
        }
    }

    public static void waitForHtmlToDisplay(WebElement webElement, WebDriver driver) {
        delay();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}