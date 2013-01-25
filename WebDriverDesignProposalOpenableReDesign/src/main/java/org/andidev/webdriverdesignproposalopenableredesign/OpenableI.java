package org.andidev.webdriverdesignproposalopenableredesign;

public interface OpenableI<T extends OpenableI> {
    public void open();

    public void isOpen() throws Error;
}
