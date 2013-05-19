package org.andidev.webdriverextension.internal.area51.festbot;

import org.andidev.webdriverextension.internal.BotUtils;
import org.openqa.selenium.WebElement;

public class TypeInTypes {

    private final String text;

    public TypeInTypes(String text) {
        this.text = text;
    }

    public void in(WebElement webElement) {
        BotUtils.type(text, webElement);
    }

}
