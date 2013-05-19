package org.andidev.webdriverextension.internal.area51.festbot;

import org.andidev.webdriverextension.internal.BotUtils;
import org.openqa.selenium.WebElement;

public class SelectOptionInTypes {

    static final int WITH_TEXT = 0;
    static final int WITH_VALUE = 1;
    static final int WITH_INDEX = 2;

    private final String text;
    private final int type;

    public SelectOptionInTypes(String text) {
        this.text = text;
        this.type = WITH_TEXT;
    }

    public SelectOptionInTypes(String text, int type) {
        this.text = text;
        this.type = type;
    }

    public void in(WebElement webElement) {
        BotUtils.type(text, webElement);
    }

}
