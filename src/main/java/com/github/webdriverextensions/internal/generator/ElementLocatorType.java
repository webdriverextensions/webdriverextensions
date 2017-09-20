package com.github.webdriverextensions.internal.generator;

// TODO: Auto-generated Javadoc
/**
 * The Enum ElementLocatorType.
 */
public enum ElementLocatorType {

    /** The id. */
    ID("id"),
    /** The name. */
    NAME("name"),
    /** The xpath. */
    XPATH("xpath"),
    /** The css. */
    CSS("css"),
    /** The classname. */
    CLASSNAME("className"),
    /** The linktext. */
    LINKTEXT("linkText"),
    /** The partiallinktext. */
    PARTIALLINKTEXT("partialLinkText");

    /** The locator type. */
    private final String locatorType;

    /**
     * Instantiates a new element locator type.
     *
     * @param givenLocator
     *            the given locator
     */
    private ElementLocatorType(String givenLocator) {
        locatorType = givenLocator;
    }

    /**
     * Equals name.
     *
     * @param locatorActual
     *            the locator actual
     * @return true, if successful
     */
    public boolean equalsName(String locatorActual) {
        return locatorType.equals(locatorActual);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return this.locatorType;
    }
}
