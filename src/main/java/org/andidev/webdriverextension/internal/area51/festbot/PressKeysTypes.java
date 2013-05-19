package org.andidev.webdriverextension.internal.area51.festbot;

import org.andidev.webdriverextension.internal.BotUtils;
import org.openqa.selenium.WebElement;

public class PressKeysTypes {

    private final CharSequence[] key;

    public PressKeysTypes(CharSequence... key) {
        this.key = key;
    }

    public void in(WebElement webElement) {
        BotUtils.pressKeys(webElement, key);
    }
}
