package org.andidev.webdriverextension.bot.festbot.istypes;

import org.andidev.webdriverextension.bot.BotUtils;

public class IsDoubleTypes {

    private final Double number;

    public IsDoubleTypes(Double number) {
        this.number = number;
    }

    /* Is */
    public boolean is(Double number) {
        return BotUtils.is(number, number);
    }

    public boolean isNot(Double number) {
        return BotUtils.isNot(number, number);
    }

    public boolean isSmallerThan(Double number) {
        return BotUtils.isSmallerThan(number, number);
    }

    public boolean isSmallerThanOrEquals(Double number) {
        return BotUtils.isSmallerThanOrEquals(number, number);
    }

    public boolean isLargerThan(Double number) {
        return BotUtils.isLargerThan(number, number);
    }

    public boolean isLargerThanOrEquals(Double number) {
        return BotUtils.isLargerThanOrEquals(number, number);
    }

}
