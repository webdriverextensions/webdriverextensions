package com.github.webdriverextensions.internal;

public class WebDriverExtensionException extends RuntimeException {

    public WebDriverExtensionException() {
    }

    public WebDriverExtensionException(String string) {
        super(string);
    }

    public WebDriverExtensionException(Throwable thrwbl) {
        super(thrwbl);
    }

    public WebDriverExtensionException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
