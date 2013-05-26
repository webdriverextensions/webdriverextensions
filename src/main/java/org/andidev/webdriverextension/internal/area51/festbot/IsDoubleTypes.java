package org.andidev.webdriverextension.internal.area51.festbot;

import org.andidev.webdriverextension.internal.BotUtils;

public class IsDoubleTypes {

    private final Double number;

    public IsDoubleTypes(Double number) {
        this.number = number;
    }

    /* Is */
    public boolean is(Double number) {
        return BotUtils.equals(number, number);
    }

    public boolean isNot(Double number) {
        return BotUtils.notEquals(number, number);
    }

    public boolean isLessThan(Double number) {
        return BotUtils.isLessThan(number, number);
    }

    public boolean isLessThanOrEquals(Double number) {
        return BotUtils.isLessThanOrEquals(number, number);
    }

    public boolean isGreaterThan(Double number) {
        return BotUtils.isGreaterThan(number, number);
    }

    public boolean isGreaterThanOrEquals(Double number) {
        return BotUtils.isGreaterThanOrEquals(number, number);
    }

}
