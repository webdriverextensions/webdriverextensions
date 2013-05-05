package org.andidev.webdriverextension.bot.fest.presstypes;

import org.andidev.webdriverextension.bot.BotUtils;
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
