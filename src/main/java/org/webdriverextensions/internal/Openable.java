package org.webdriverextensions.internal;

public interface Openable {

    public void open();

    public boolean isOpen();

    public boolean isNotOpen();

    public void assertIsOpen() throws AssertionError;

    public void assertIsNotOpen() throws AssertionError;
}