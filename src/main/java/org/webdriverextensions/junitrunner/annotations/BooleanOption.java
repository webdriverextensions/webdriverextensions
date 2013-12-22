package org.webdriverextensions.junitrunner.annotations;

public enum BooleanOption {
    TRUE,
    FALSE,
    ANY;

    public Boolean getValue() {
        if (this == TRUE) {
            return true;
        } else if (this == FALSE) {
            return false;
        }
        return null;
    }

}