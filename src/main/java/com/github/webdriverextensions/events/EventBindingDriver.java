/*
 * 
 */
package com.github.webdriverextensions.events;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

// TODO: Auto-generated Javadoc
/**
 * The Class EventBindingDriver.
 */
public class EventBindingDriver {

    /**
     * Instantiates a new event binding driver.
     */
    private EventBindingDriver() {
    };

    /**
     * Attach event driver.
     *
     * @param driver
     *            the driver
     * @return the web driver
     */
    public static WebDriver attachEventDriver(WebDriver driver) {
        EventFiringWebDriver eventFiringDriver = new EventFiringWebDriver(driver);
        WebEventListener eventListener = new WebEventListener();
        eventFiringDriver.register(eventListener);
        return eventFiringDriver;
    }
}
