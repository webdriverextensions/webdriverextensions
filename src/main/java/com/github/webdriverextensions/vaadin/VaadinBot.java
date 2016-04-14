/*
 * Copyright 2016 Daniel Nordhoff-Vergien
 *  
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.webdriverextensions.vaadin;

import org.openqa.selenium.WebElement;

import com.github.webdriverextensions.Bot;

/**
 * {@link Bot} extensions to be used with the vaadin web framework.
 * 
 * @author Daniel Nordhoff-Vergien
 * @see <a href="https://www.vaadin.com"> Vaadin</a>
 */
public class VaadinBot extends Bot {
    private static final int DEFUALT_WAIT_TIMEOUT = 30;

    public static void openAndWait(String url) {
	open(url);
	waitForVaadin();
    }

    public static void clickAndWait(WebElement webElement) {
	clickAndWait(webElement, DEFUALT_WAIT_TIMEOUT);
    }

    public static void clickAndWait(WebElement webElement, long secontsToWait) {
	Bot.click(webElement);
	waitForVaadin(secontsToWait);
    }

    public static void doubleClickAndWait(WebElement webElement) {
	doubleClickAndWait(webElement, DEFUALT_WAIT_TIMEOUT);
    }

    public static void doubleClickAndWait(WebElement webElement, long secondsToWait) {
	doubleClick(webElement);
	waitForVaadin(secondsToWait);
    }

    public static void waitForVaadin() {
	waitForVaadin(DEFUALT_WAIT_TIMEOUT);
    }

    public static void waitForVaadin(long secondsToWait) {
	waitUntil(VaadinConditions.ajaxCallsCompleted(), secondsToWait);
    }
}
