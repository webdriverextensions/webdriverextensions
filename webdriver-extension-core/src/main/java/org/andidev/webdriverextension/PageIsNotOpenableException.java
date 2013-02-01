package org.andidev.webdriverextension;

public class PageIsNotOpenableException extends UnsupportedOperationException {

    private final static String DEFAULT_MESSAGE = "Page is not Openable with the open() method";

    public PageIsNotOpenableException() {
        super(DEFAULT_MESSAGE);
    }

    public PageIsNotOpenableException(String string) {
        super(string);
    }

    public PageIsNotOpenableException(Throwable thrwbl) {
        super(DEFAULT_MESSAGE, thrwbl);
    }

    public PageIsNotOpenableException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
