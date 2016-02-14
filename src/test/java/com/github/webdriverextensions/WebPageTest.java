package com.github.webdriverextensions;

import static org.junit.Assert.*;
import org.junit.Test;

public class WebPageTest {
    private WebPage opendPage = new WebPage() {

	@Override
	public void open(Object... arguments) {
	    // NoOp - just unit test
	}

	@Override
	public void assertIsOpen(Object... arguments) throws AssertionError {
	    // NoOP - page is open
	}

    };
    private WebPage notOpendPage = new WebPage() {

	@Override
	public void open(Object... arguments) {
	    // NoOp - just unit test
	}

	@Override
	public void assertIsOpen(Object... arguments) throws AssertionError {
	    throw new AssertionError();
	}
    };

    @Test
    public void testAssertIsNotOpen_notOpendPage() {
	try {
	    notOpendPage.assertIsNotOpen();
	} catch (AssertionError e) {
	    fail("No AssertionError expected");
	}
    }

    @Test(expected = AssertionError.class)
    public void testAssertIsNotOpen_opendPage() {
	opendPage.assertIsNotOpen();
    }

    @Test(expected = AssertionError.class)
    public void testAssertIsOpen_notOpendPage() {
	notOpendPage.assertIsOpen();
    }

    @Test
    public void testAssertIsOpen_opendPage() {
	try {
	    opendPage.assertIsOpen();
	} catch (AssertionError e) {
	    fail("No AssertionError expected");
	}
    }
}
