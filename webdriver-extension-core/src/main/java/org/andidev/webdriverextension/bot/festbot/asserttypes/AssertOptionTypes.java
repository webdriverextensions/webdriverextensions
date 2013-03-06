package org.andidev.webdriverextension.bot.festbot.asserttypes;

import org.andidev.webdriverextension.bot.junitbot.BotUtils;
import org.openqa.selenium.WebElement;

public class AssertOptionTypes extends AssertStringTypes {

    private WebElement option;

    public AssertOptionTypes(WebElement option) {
        super(option.getText());
    }

    /* Selected/Deselected */
    public boolean selected() {
        return BotUtils.isSelected(option);
    }

    public boolean deselected() {
        return BotUtils.isDeselected(option);
    }

    /* Enabled/Disabled */
    public boolean enabled() {
        return BotUtils.isEnabled(option);
    }

    public boolean disabled() {
        return BotUtils.isDisabled(option);
    }
}
