package org.andidev.webdriverextension;

import java.util.List;
import org.andidev.webdriverextension.internal.ThreadDriver;
import org.apache.commons.lang3.math.NumberUtils;

import org.openqa.selenium.WebElement;

public class HamcrestHelpers {

    public static String tagNameOf(WebElement webElement) {
        return webElement.getTagName();
    }

    public static String idIn(WebElement webElement) {
        return webElement.getAttribute("id");
    }

    public static String nameIn(WebElement webElement) {
        return webElement.getAttribute("name");
    }

    public static String classIn(WebElement webElement) {
        return webElement.getAttribute("class");
    }

    public static String valueIn(WebElement webElement) {
        return webElement.getAttribute("vale");
    }

    public static String hrefIn(WebElement webElement) {
        return webElement.getAttribute("href");
    }

    public static String textIn(WebElement webElement) {
        return webElement.getText();
    }

    public static Double numberIn(WebElement webElement) {
        return NumberUtils.createDouble(webElement.getText());
    }

    public static int numberOf(List<WebElement> webElements) {
        return webElements.size();
    }

    public static String url() {
        return ThreadDriver.getDriver().getCurrentUrl();
    }
}
