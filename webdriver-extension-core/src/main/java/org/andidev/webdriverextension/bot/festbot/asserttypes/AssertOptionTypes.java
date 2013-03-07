package org.andidev.webdriverextension.bot.festbot.asserttypes;

import org.andidev.webdriverextension.bot.BotUtils;
import org.openqa.selenium.WebElement;

public class AssertOptionTypes extends AssertStringTypes {

    private WebElement option;

    public AssertOptionTypes(WebElement option) {
        super(option.getText());
    }

    /* Selected/Deselected */
    public boolean isSelected() {
        return false;
//        return BotUtils.assertSelected(option);
    }

    public boolean isDeselected() {
        return false;
//        return BotUtils.assertDeselected(option);
    }

    /* Enabled/Disabled */
    public boolean isEnabled() {
        return false;
//        return BotUtils.assertEnabled(option);
    }

    public boolean isDisabled() {
        return false;
//        return BotUtils.assertDisabled(option);
    }
}
