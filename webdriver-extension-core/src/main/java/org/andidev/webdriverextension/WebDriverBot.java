package org.andidev.webdriverextension;

import java.util.List;
import mx4j.tools.remote.http.WebContainer;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverBot {

    public static double delayTime = 0.0;
    // public static boolean debugMode = false; Implement later

    public static void click(WebElement webElement) {
        delay();
        webElement.click();
        // DEBUG TEXT: Clicked <tagname ...>
    }

    public static void type(String str, WebElement webElement) {
        if (str == null) {
            return;
        }
        delay();
        webElement.sendKeys(str);
        // DEBUG TEXT: Typed "str" in <input name="">
    }

    public static void pressKeys(WebElement webElement, CharSequence... keysToSend) {
        delay();
        webElement.sendKeys(keysToSend);
        // DEBUG TEXT: Pressed keys "keysToSend" in <input name="">
    }

    public static void pressEnter(WebElement webElement) {
        delay();
        webElement.sendKeys(Keys.ENTER);
        // DEBUG TEXT: Pressed enter key in <input name="">
    }

    public static void clear(WebElement webElement) {
        webElement.clear();
        delay();
        // DEBUG TEXT: Cleared text "textcleared" in <input name="">
    }

    public static void clearAndType(String str, WebElement webElement) {
        if (str == null) {
            return;
        }
        clear(webElement);
        type(str, webElement);
    }

    public static boolean isChecked(WebElement webElement) {
        delay();
        return webElement.isSelected();
    }

    public static boolean isText(String expected, WebElement webElement) {
        delay();
        return webElement.getText().equals(expected);
    }

    public static boolean isTextContaining(String expected, WebElement webElement) {
        delay();
        return webElement.getText().contains(expected);
    }
    
    public static boolean isTextStartingWith(String expected, WebElement webElement) {
        delay();
        return webElement.getText().startsWith(expected);
    }
    
    public static boolean isTextEndingWith(String expected, WebElement webElement) {
        delay();
        return webElement.getText().endsWith(expected);
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

    public static boolean isLoaded(WebPage page) {
        delay();
        boolean isLoaded = true;
        try {
            page.isLoaded();
        } catch (AssertionError e) {
            isLoaded = false;
        }
        return isLoaded;
    }

    public static void check(WebElement webElement) {
        delay();
        if (!isChecked(webElement)) {
            webElement.click();
            // DEBUG TEXT: Checked <input type="checkbox" name="">
        } else {
            // DEBUG TEXT: Checkbox already checked! Skipped checking <input type="checkbox" name="">           
        }
    }

    public static void uncheck(WebElement webElement) {
        delay();
        if (isChecked(webElement)) {
            webElement.click();
            // DEBUG TEXT: Un-checked <input type="checkbox" name="">
        } else {
            // DEBUG TEXT: Checkbox already un-checked! Skipped un-checking <input type="checkbox" name="">           
        }
    }

    public static String read(WebElement webElement) {
        delay();
        return webElement.getText();
    }

    public static Double readAsNumber(WebElement webElement) {
        delay();
        String string = read(webElement);
        if(NumberUtils.isNumber(string)) {
            return NumberUtils.toDouble(string);
        } else {
            return null;
        }
    }

    public static void select(String optionText, WebElement webElement) {
        delay();
        new Select(webElement).selectByVisibleText(optionText);
        // DEBUG TEXT: Selected "optionText" from <select name="">
    }

    public static void open(WebPage page) {
        delay();
        page.get();
    }


    public static void debugNumberOfElements(List<WebElement> webElements) {
        delay();
        System.out.println("Number of " + webElements.get(0).getTagName() + "-tags: " + webElements.size());
    }

    public static void debugText(WebElement webElement) {
        delay();
        System.out.println("Text in " + webElement.getTagName() + "-tag: " + webElement.getText());
    }

    public static void debugText(List<WebElement> webElements) {
        delay();
        for (WebElement webElement : webElements) {
            debugText(webElement);
        }
    }

    public static void waitForVisibilityOfElement(WebElement webElement, WebDriver driver) {
        delay();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(webElement));
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

    public static void delay() {
        delay(delayTime);
    }

    private static void debug(String str) {
        System.out.println(str);
    }
}
