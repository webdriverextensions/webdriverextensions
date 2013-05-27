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
        return BotUtils.lessThan(number, number);
    }

    public boolean isLessThanOrEquals(Double number) {
        return BotUtils.lessThanOrEquals(number, number);
    }

    public boolean isGreaterThan(Double number) {
        return BotUtils.greaterThan(number, number);
    }

    public boolean isGreaterThanOrEquals(Double number) {
        return BotUtils.greaterThanOrEquals(number, number);
    }

}
