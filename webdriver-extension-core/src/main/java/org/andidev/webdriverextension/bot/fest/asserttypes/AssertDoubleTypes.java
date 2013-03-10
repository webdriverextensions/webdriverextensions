package org.andidev.webdriverextension.bot.fest.asserttypes;

import org.apache.commons.lang3.math.NumberUtils;

public class AssertDoubleTypes {

    private Double number;

    public AssertDoubleTypes(String number) {
        this(NumberUtils.createDouble(number));
    }
    public AssertDoubleTypes(Double number) {
        this.number = number;
    }

    

}
