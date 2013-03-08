package org.andidev.webdriverextension.area51;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public enum Browser {
    FIREFOX, CHROME;

    URL hubAddress;

    public WebDriver getDriver() {
        try {
            hubAddress = new URL("http://");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, ex);
        }
       if(this == Browser.FIREFOX) {
           return new RemoteWebDriver(hubAddress, DesiredCapabilities.firefox());  //alternatively could be new FirefoxDriver()
       }
       else {
           return new RemoteWebDriver(hubAddress, DesiredCapabilities.chrome());
       }

    }
}

