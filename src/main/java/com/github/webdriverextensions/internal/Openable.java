package com.github.webdriverextensions.internal;

public interface Openable {

    public void open(Object... arguments);

    public boolean isOpen(Object... arguments);

    public boolean isNotOpen(Object... arguments);

    public void assertIsOpen(Object... arguments) throws AssertionError;

    public void assertIsNotOpen(Object... arguments) throws AssertionError;
}