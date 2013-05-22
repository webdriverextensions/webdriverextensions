package org.andidev.webdriverextension;

import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;

import org.openqa.selenium.WebElement;

public class HamcrestHelpers {

    public static String tagNameOf(WebElement webElement) {
        try {
            return webElement.getTagName();
        } catch (Exception e) {
            return null;
        }
    }

    public static String idIn(WebElement webElement) {
        try {
            return webElement.getAttribute("id");
        } catch (Exception e) {
            return null;
        }
    }

    public static String nameIn(WebElement webElement) {
        try {
            return webElement.getAttribute("name");
        } catch (Exception e) {
            return null;
        }
    }

    public static String classIn(WebElement webElement) {
        try {
            return webElement.getAttribute("class");
        } catch (Exception e) {
            return null;
        }
    }

    public static String valueIn(WebElement webElement) {
        try {
            return webElement.getAttribute("vale");
        } catch (Exception e) {
            return null;
        }
    }

    public static String hrefIn(WebElement webElement) {
        try {
            return webElement.getAttribute("href");
        } catch (Exception e) {
            return null;
        }
    }

    public static String textIn(WebElement webElement) {
        try {
            return webElement.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public static Double numberIn(WebElement webElement) {
        try {
            return NumberUtils.createDouble(webElement.getText());
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer numberOf(List<WebElement> webElements) {
        try {
            return webElements.size();
        } catch (Exception e) {
            return null;
        }
    }

    public static String url() {
        try {
            return ThreadDriver.getDriver().getCurrentUrl();
        } catch (Exception e) {
            return null;
        }
    }
}
