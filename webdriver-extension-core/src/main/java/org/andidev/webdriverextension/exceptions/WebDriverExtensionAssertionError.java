package org.andidev.webdriverextension.exceptions;

class WebDriverExtensionAssertionError extends AssertionError {

    public WebDriverExtensionAssertionError() {
    }

    public WebDriverExtensionAssertionError(Object o) {
        super(o);
    }

    public WebDriverExtensionAssertionError(boolean bln) {
        super(bln);
    }

    public WebDriverExtensionAssertionError(char c) {
        super(c);
    }

    public WebDriverExtensionAssertionError(int i) {
        super(i);
    }

    public WebDriverExtensionAssertionError(long l) {
        super(l);
    }

    public WebDriverExtensionAssertionError(float f) {
        super(f);
    }

    public WebDriverExtensionAssertionError(double d) {
        super(d);
    }


}
