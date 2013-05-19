package org.andidev.webdriverextension.internal.area51.festbot;

import org.andidev.webdriverextension.internal.BotUtils;
import org.openqa.selenium.WebElement;

public class IsOptionTypes extends IsStringTypes {

    private WebElement option;

    public IsOptionTypes(WebElement option) {
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
