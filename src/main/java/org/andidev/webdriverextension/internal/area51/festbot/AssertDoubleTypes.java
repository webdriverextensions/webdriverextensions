package org.andidev.webdriverextension.internal.area51.festbot;

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
