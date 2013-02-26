package org.andidev.webdriverextension.twostepbot.asserters;

import org.andidev.webdriverextension.Openable;

public class IsOpenAsserter extends StringIsContainsAsserter {

    public IsOpenAsserter(String actualText) {
        super(actualText);
    }

    public void isOpen(Openable openable) {
        openable.assertIsOpen();
    }

    public void isNotOpen(Openable openable) {
        openable.isOpen();
    }
}
