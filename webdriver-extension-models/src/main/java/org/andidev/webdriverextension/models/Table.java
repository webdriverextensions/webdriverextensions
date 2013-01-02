package org.andidev.webdriverextension.models;

import org.andidev.webdriverextension.HtmlTag;
import static org.andidev.webdriverextension.WebDriverBot.*;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Table extends HtmlTag {

    @FindBy(css = "th")
    public List<HtmlTag> header;
    @FindBy(css = "td")
    public List<HtmlTag> rows;
    
    public Table(WebElement webElement, By by) {
        super(webElement, by);
    }
    
    public Integer getHeader(String header) {
        int i = 0;
        for (HtmlTag htmlTag : this.header) {
            if (isText(header, htmlTag)) {
                return new Integer(i);
            }
            i++;
        }
        return null;
    }
    
    public Integer getHeaderContains(String header) {
        int i = 0;
        for (HtmlTag htmlTag : this.header) {
            if (isTextContaining(header, htmlTag)) {
                return new Integer(i);
            }
            i++;
        }
        return null;
    }
    
    public Integer getHeaderStrartsWith(String header) {
        int i = 0;
        for (HtmlTag htmlTag : this.header) {
            if (isTextStartingWith(header, htmlTag)) {
                return new Integer(i);
            }
            i++;
        }
        return null;
    }
    
    public Integer getHeaderEndsWith(String header) {
        int i = 0;
        for (HtmlTag htmlTag : this.header) {
            if (isTextEndingWith(header, htmlTag)) {
                return new Integer(i);
            }
            i++;
        }
        return null;
    }
    
//    public List<HtmlTag> getRowWhere(String header, String value) {
//        Integer header = ;getHeader(header);
//        List<HtmlTag> header = ;getHeader(header);
//    }
}
