package org.andidev.webdriverextension;

public interface Openable {
    public void open();

    public boolean isOpen();

    public void assertIsOpen() throws Error;
}
