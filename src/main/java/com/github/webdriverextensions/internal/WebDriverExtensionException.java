package com.github.webdriverextensions.internal;

public class WebDriverExtensionException extends RuntimeException {

	private static final long serialVersionUID = 8194945043825905740L;

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
