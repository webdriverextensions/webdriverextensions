package org.andidev.webdriverextension.internal.area51.festbot;

public class SelectOptionTypes {

    public SelectOptionTypes() {
    }

    public SelectOptionInTypes option(String value) {
        return new SelectOptionInTypes(value);
    }

    public SelectOptionInTypes optionWithValue(String value) {
        return new SelectOptionInTypes(value, SelectOptionInTypes.WITH_VALUE);
    }

    public SelectOptionInTypes optionWithIndex(int index) {
        return new SelectOptionInTypes(Integer.toString(index), SelectOptionInTypes.WITH_INDEX);
    }

}
