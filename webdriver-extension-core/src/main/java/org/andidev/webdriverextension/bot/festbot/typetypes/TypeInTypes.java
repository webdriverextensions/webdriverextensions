package org.andidev.webdriverextension.bot.festbot.typetypes;

import org.andidev.webdriverextension.bot.BotUtils;
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
