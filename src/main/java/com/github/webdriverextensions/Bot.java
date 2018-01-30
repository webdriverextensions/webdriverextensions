package com.github.webdriverextensions;

import com.github.webdriverextensions.exceptions.WebAssertionError;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.github.webdriverextensions.internal.BotUtils;
import static com.github.webdriverextensions.internal.BotUtils.asNanos;
import com.github.webdriverextensions.internal.Openable;
import com.github.webdriverextensions.internal.WebDriverExtensionException;
import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.math.NumberUtils.*;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.github.webdriverextensions.internal.utils.StringUtils.*;
import com.github.webdriverextensions.internal.utils.NumberUtils;

import static com.github.webdriverextensions.internal.utils.WebDriverUtils.getScreenshotFilePath;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;

public class Bot {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Bot.class);

    /* Driver */
    public static WebDriver driver() {
        return WebDriverExtensionsContext.getDriver();
    }



    /* Click */
    public static void click(WebElement webElement) {
        webElement.click();
    }



    /* Double Click */
    public static void doubleClick(WebElement webElement) {
	Actions action = new Actions(driver());
	action.doubleClick(webElement).perform();
    }



    /* Type */
    public static void type(String text, WebElement webElement) {
        if (text == null) {
            return;
        }
        webElement.sendKeys(text);
    }

    public static void type(double number, WebElement webElement) {
        type(NumberUtils.toString(number), webElement);
    }



    /* Clear */
    public static void clear(WebElement webElement) {
        webElement.clear();
    }

    public static void clearAndType(String text, WebElement webElement) {
        clear(webElement);
        type(text, webElement);
    }

    public static void clearAndType(double number, WebElement webElement) {
        clear(webElement);
        type(number, webElement);
    }



    /* Press Keys */
    public static void pressEnter(WebElement webElement) {
        pressKeys(webElement, Keys.ENTER);
    }

    public static void pressKeys(WebElement webElement, CharSequence... keys) {
        webElement.sendKeys(keys);
    }



    /* Select/Deselect */
    public static void select(WebElement webElement) {
        if (isDeselected(webElement)) {
            webElement.click();
        }
    }

    public static void deselect(WebElement webElement) {
        if (isSelected(webElement)) {
            webElement.click();
        }
    }

    public static void selectOption(String text, WebElement webElement) {
        new Select(webElement).selectByVisibleText(text);
    }

    public static void deselectOption(String text, WebElement webElement) {
        new Select(webElement).deselectByVisibleText(text);
    }

    public static void selectAllOptions(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            select(webElement);
        }
    }

    public static void deselectAllOptions(WebElement webElement) {
        new Select(webElement).deselectAll();
    }

    public static void selectOptionWithValue(String value, WebElement webElement) {
        new Select(webElement).selectByValue(value);
    }

    public static void deselectOptionWithValue(String value, WebElement webElement) {
        new Select(webElement).deselectByValue(value);
    }

    public static void selectOptionWithIndex(int index, WebElement webElement) {
        new Select(webElement).selectByIndex(index);
    }

    public static void deselectOptionWithIndex(int index, WebElement webElement) {
        new Select(webElement).selectByIndex(index);
    }



    /* Check/Uncheck */
    public static void check(WebElement webElement) {
        if (isUnchecked(webElement)) {
            click(webElement);
        }
    }

    public static void uncheck(WebElement webElement) {
        if (isChecked(webElement)) {
            click(webElement);
        }
    }



    /* Open */
    public static void open(String url) {
        driver().get(url);
    }

    public static void open(Openable openable, Object... arguments) {
        openable.open(arguments);
    }

    /* Navigation */
    public static void navigateBack() {
        driver().navigate().back();
    }
    public static void navigateForward() {
        driver().navigate().forward();
    }
    public static void navigateRefresh() {
        driver().navigate().refresh();
    }



    /* Wait For */
    public static void waitFor(double seconds) {
        long nanos = (long) (seconds * 1000000000);
        if (seconds > 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(nanos);
            } catch (InterruptedException ex) {
                // Swallow exception
                ex.printStackTrace();
            }
        }
    }

    public static void waitFor(double time, TimeUnit unit) {
        if (time <= 0) {
            return;
        }
        try {
            TimeUnit.NANOSECONDS.sleep(asNanos(time, unit));
        } catch (InterruptedException ex) {
            // Swallow exception
            ex.printStackTrace();
        }
    }

    public static void waitForElementToDisplay(WebElement webElement) {
        waitForElementToDisplay(webElement, 30);
    }

    public static void waitForElementToDisplay(WebElement webElement, long secondsToWait) {
        WebDriverWait wait = new WebDriverWait(driver(), secondsToWait);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementToDisplay(WebElement webElement, double timeToWait, TimeUnit unit) {
        WebDriverWait wait = new WebDriverWait(driver(), asNanos(timeToWait, unit));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementToDisplay(WebElement webElement, long secondsToWait, long sleepInMillis) {
        WebDriverWait wait = new WebDriverWait(driver(), secondsToWait, sleepInMillis);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementToDisplay(WebElement webElement, double timeToWait, TimeUnit unit, long sleepInMillis) {
        WebDriverWait wait = new WebDriverWait(driver(), asNanos(timeToWait, unit), sleepInMillis);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementsToDisplay(List<? extends WebElement> webElements) {
        waitForElementsToDisplay((List<WebElement>) webElements, 30);
    }

    public static void waitForElementsToDisplay(List<? extends WebElement> webElements, long secondsToWait) {
        WebDriverWait wait = new WebDriverWait(driver(), secondsToWait);
        wait.until(ExpectedConditions.visibilityOfAllElements((List<WebElement>) webElements));
    }

    public static void waitForElementsToDisplay(List<? extends WebElement> webElements, long secondsToWait, long sleepInMillis) {
        WebDriverWait wait = new WebDriverWait(driver(), secondsToWait, sleepInMillis);
        wait.until(ExpectedConditions.visibilityOfAllElements((List<WebElement>) webElements));
    }



    /* Wait Until */
    public static void waitUntil(Predicate<WebDriver> predicate) {
        waitUntil(predicate, 30);
    }

    public static void waitUntil(Predicate<WebDriver> predicate, long secondsToWait) {
        new WebDriverWait(driver(), secondsToWait).until(webDriver -> predicate.test(webDriver));
    }



    /* Scrolling */
    public static Object scrollTo(WebElement webElement) {
        if (webElement instanceof WebComponent) {
            return executeJavascript("arguments[0].scrollIntoView(true);", ((WebComponent) webElement).getWrappedWebElement());
        }
        return executeJavascript("arguments[0].scrollIntoView(true);", webElement);
    }

    /* Tabs support */
    public static void openInNewTab(WebElement element) {
        type(Keys.chord(BotUtils.getPlatformControlKey(), Keys.RETURN), element);
    }

    public static String openInNewTabAndFocus(WebElement element) {
        String oldHandle = currentWindowHandle(); // handle for navigating back to old tab
        openInNewTab(element);
        switchToWindow(BotUtils.getNewTabHandle(availableWindowHandles()));
        waitForPageToLoad();
        return oldHandle;
    }

    public static Set<String> availableWindowHandles() {
        return driver().getWindowHandles();
    }

    public static String currentWindowHandle() {
        return driver().getWindowHandle();
    }

    public static void switchToWindow(String handle) {
        driver().switchTo().window(handle);
    }

    public static void waitForNewTabToOpen(Set<String> oldWindowHandles) {
        waitForNewTabToOpen(oldWindowHandles, 10);
    }

    public static void waitForNewTabToOpen(Set<String> oldWindowHandles, int seconds) {
        new WebDriverWait(driver(), seconds).until((WebDriver) -> {
            return availableWindowHandles().size() > oldWindowHandles.size();
        });
    }

    public static void waitForPageToLoad() {
        waitForPageToLoad(10);
    }

    public static void waitForPageToLoad(int seconds) {
        try {
            new WebDriverWait(driver(), seconds).until((WebDriver) -> {
                return String.valueOf(executeJavascript("return document.readyState"))
                        .equals("complete");
            });
        } catch (TimeoutException ex) {
            // don't throw if page is still loading. Some pages never
            // archive readyState == complete, but are functionaly correct
        }
    }

    public static void executeForLink(WebElement link, Runnable function) {
        // Open link in new tab and execute code when in this tab
        String oldWindowHandle = openInNewTabAndFocus(link);
        function.run();
        driver().close();
        driver().switchTo().window(oldWindowHandle);
    }

    public static void executeForLinks(Collection<WebElement> links, Runnable function) {
        for (WebElement link : links) {
            executeForLink(link, function);
        }
    }

    /* Execute Javascript */
    public static Object executeJavascript(String script, Object... arguments) {
        return ((JavascriptExecutor) driver()).executeScript(script, arguments);
    }

    public static Object executeJavascriptAsynchronously(String script, Object... arguments) {
        return ((JavascriptExecutor) driver()).executeAsyncScript(script, arguments);
    }



    /* Browser */
    public static String browser() {
        return ((HasCapabilities) driver()).getCapabilities().getBrowserName();
    }

    public static boolean browserIs(String browserName) {
        return StringUtils.equalsIgnoreCase(browser(), browserName);
    }

    public static boolean browserIsNot(String browserName) {
        return !browserIs(browserName);
    }

    public static boolean browserIsAndroid() {
        return browserIs(BrowserType.ANDROID);
    }

    public static boolean browserIsNotAndroid() {
        return !browserIsAndroid();
    }

    public static boolean browserIsChrome() {
        return browserIs(BrowserType.CHROME) || browserIs(BrowserType.GOOGLECHROME);
    }

    public static boolean browserIsNotChrome() {
        return !browserIsChrome();
    }

    public static boolean browserIsEdge() {
        return browserIs(BrowserType.EDGE);
    }

    public static boolean browserIsNotEdge() {
        return !browserIsEdge();
    }

    public static boolean browserIsFirefox() {
        return browserIs(BrowserType.FIREFOX);
    }

    public static boolean browserIsNotFirefox() {
        return !browserIsFirefox();
    }

    public static boolean browserIsHtmlUnit() {
        return browserIs(BrowserType.HTMLUNIT);
    }

    public static boolean browserIsNotHtmlUnit() {
        return !browserIsHtmlUnit();
    }

    public static boolean browserIsIPad() {
        return browserIs(BrowserType.IPAD);
    }

    public static boolean browserIsNotIPad() {
        return !browserIsIPad();
    }

    public static boolean browserIsIPhone() {
        return browserIs(BrowserType.IPHONE);
    }

    public static boolean browserIsNotIPhone() {
        return !browserIsIPhone();
    }

    public static boolean browserIsInternetExplorer() {
        return browserIs(BrowserType.IE) || browserIs(BrowserType.IEXPLORE);
    }

    public static boolean browserIsNotInternetExplorer() {
        return !browserIsInternetExplorer();
    }

    public static boolean browserIsOpera() {
        return browserIs(BrowserType.OPERA);
    }

    public static boolean browserIsNotOpera() {
        return !browserIsOpera();
    }

    public static boolean browserIsPhantomJS() {
        return browserIs(BrowserType.PHANTOMJS);
    }

    public static boolean browserIsNotPhantomJS() {
        return !browserIsPhantomJS();
    }

    public static boolean browserIsSafari() {
        return browserIs(BrowserType.SAFARI);
    }

    public static boolean browserIsNotSafari() {
        return !browserIsSafari();
    }



    /* Version */
    public static String version() {
        return ((HasCapabilities) driver()).getCapabilities().getVersion();
    }

    public static boolean versionIs(String version) {
        return StringUtils.equalsIgnoreCase(version(), version);
    }

    public static boolean versionIsNot(String version) {
        return !versionIs(version);
    }



    /* Platform */
    public static Platform platform() {
        return ((HasCapabilities) driver()).getCapabilities().getPlatform();
    }

    public static boolean platformIs(Platform platform) {
        return platform().is(platform);
    }

    public static boolean platformIsNot(Platform platform) {
        return !platformIs(platform);
    }

    public static boolean platformIsAndroid() {
        return platformIs(Platform.ANDROID);
    }

    public static boolean platformIsNotAndroid() {
        return !platformIsAndroid();
    }

    public static boolean platformIsLinux() {
        return platformIs(Platform.LINUX);
    }

    public static boolean platformIsNotLinux() {
        return !platformIsLinux();
    }

    public static boolean platformIsMac() {
        return platformIs(Platform.MAC);
    }

    public static boolean platformIsNotMac() {
        return !platformIsMac();
    }

    public static boolean platformIsUnix() {
        return platformIs(Platform.UNIX);
    }

    public static boolean platformIsNotUnix() {
        return !platformIsUnix();
    }

    public static boolean platformIsWindows() {
        return platformIs(Platform.WINDOWS);
    }

    public static boolean platformIsNotWindows() {
        return !platformIsWindows();
    }

    public static boolean platformIsWin8() {
        return platformIs(Platform.WIN8);
    }

    public static boolean platformIsNotWin8() {
        return !platformIsWin8();
    }

    public static boolean platformIsWin8_1() {
        return platformIs(Platform.WIN8_1);
    }

    public static boolean platformIsNotWin8_1() {
        return !platformIsWin8_1();
    }

    public static boolean platformIsWin10() {
        return platformIs(Platform.WIN10);
    }

    public static boolean platformIsNotWin10() {
        return !platformIsWin10();
    }

    public static boolean platformIsVista() {
        return platformIs(Platform.VISTA);
    }

    public static boolean platformIsNotVista() {
        return !platformIsVista();
    }

    public static boolean platformIsXP() {
        return platformIs(Platform.XP);
    }

    public static boolean platformIsNotXP() {
        return !platformIsXP();
    }



    /* Take Screenshot */
    /**
     * Takes a screenshot and saves it as a file in a directory called {@code screenshots}.
     * A custom path to the directory where the screenshots are saved can be set by setting
     * the system property {@code webdriverextensions.screenshotspath}.
     *
     * @param fileName the filename of the screenshot file without the file extension
     */
    public static void takeScreenshot(String fileName) {
        File screenshotFile = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.FILE);
        String filePath = getScreenshotFilePath(fileName);
        try {
            FileUtils.copyFile(screenshotFile, new File(filePath));
        } catch (IOException ex) {
            throw new WebDriverExtensionException("Failed to save screenshot to " + quote(filePath), ex);
        }
    }

    private static int renderedPageHeight() {
        // returns full size of rendered page instead of current viewport
        String js = "var body = document.body,\n"
                + "    html = document.documentElement;\n"
                + "return Math.max( body.scrollHeight, body.offsetHeight, \n"
                + "                       html.clientHeight, html.scrollHeight, html.offsetHeight );";

        return (int) (long) executeJavascript(js);
    }

    /* Take Full-Page Screenshot */
    /**
     * Takes a full-page screenshot and saves it as a file in a directory called {@code screenshots}.
     * A custom path to the directory where the screenshots are saved can be set by setting
     * the system property {@code webdriverextensions.screenshotspath}.
     *
     * @param fileName the filename of the screenshot file without the file extension
     */
    public static void takeFullPageScreenshot(String fileName) {
        // get inner window size
        // driver().manage().window().getSize() doesn't work, because it returns
        // whole window size, with tab bars, scrolls etc.
        Dimension windowSize = new Dimension((int)(long)executeJavascript("return window.innerWidth;"), 
                (int)(long)executeJavascript("return window.innerHeight;"));

        
        // scroll through whole page to render all it's content
        int lastHeight = 0;
        while (lastHeight != renderedPageHeight()) {      
            lastHeight = (int) renderedPageHeight();
            executeJavascript("window.scrollBy(0," + windowSize.height + ")");
            waitForPageToLoad(); // doesn't wait for rendering for some reason
            waitFor(0.5, TimeUnit.SECONDS); // simple hard coded wait will work in most cases
        }
        
        int totalPageHeight = lastHeight;
        
        // go back to the top of window
        executeJavascript("window.scrollBy(0,-999999999)");
        
        List<BufferedImage> imageParts = new ArrayList<>();
        
        // make screenshot of current viewport, then scroll, then make
        // scrennshot etc. until the of the rendered page
        int lastImageEnd = 0;
        while(lastImageEnd < totalPageHeight) {
            byte[] bytes = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.BYTES);       
            try {
                imageParts.add(ImageIO.read(new ByteArrayInputStream(bytes)));
            } catch (IOException ex) {
                // bytes must be well formet at this point
            }
            // scroll down by exactly one screenshot height
            executeJavascript("window.scrollBy(0," + windowSize.height + ")");
            lastImageEnd += windowSize.height;
        }
        
        // at the bottom of the page, there may (and most likely will) be some 
        // overlapping of last 2 screenshots 
        // this section fixes that overlapping
        
        // final image that will be stored in file
        BufferedImage finalImage = new BufferedImage(
                windowSize.width, 
                totalPageHeight, 
                BufferedImage.TYPE_INT_RGB);
        
        // merge subImages into one. 
        // omit last image that may overlap
        for(int i=0; i<imageParts.size() - 1; ++i) {
            finalImage.createGraphics().drawImage(imageParts.get(i), 0, i*windowSize.height, null);
        }
        
        // make an offset for last image
        BufferedImage lastImage = imageParts.get(imageParts.size()-1);
        finalImage.createGraphics().drawImage(lastImage, 0, finalImage.getHeight() - windowSize.height, null);
        
        String filePath = getScreenshotFilePath(fileName);
        File file = new File(filePath);
        file.mkdirs(); // make target direcory tree if doesn't exists
        try {
            ImageIO.write(finalImage, "png", file);
        } catch (IOException ex) {
            throw new WebDriverExtensionException("Failed to save full-page screenshot to " + quote(filePath), ex);
        }
    }

    /* Debug */
    public static void debug(String str) {
        log.debug(str);
    }

    public static void debug(WebElement webElement) {
        log.debug("Element: " + BotUtils.htmlOf(webElement));
    }

    public static void debug(List<? extends WebElement> webElements) {
        log.debug("Size: ", sizeOf(webElements));
        for (WebElement webElement : webElements) {
            debug(webElement);
        }
    }



    /* Is Open */
    public static boolean isOpen(Openable openable, Object... arguments) {
        return openable.isOpen(arguments);
    }

    public static boolean isNotOpen(Openable openable, Object... arguments) {
        return openable.isNotOpen(arguments);
    }

    public static void assertIsOpen(Openable openable, Object... arguments) {
        openable.assertIsOpen(arguments);
    }

    public static void assertIsNotOpen(Openable openable, Object... arguments) {
        openable.assertIsNotOpen(arguments);
    }



    /* Is Displayed */
    public static boolean isDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isNotDisplayed(WebElement webElement) {
        return !isDisplayed(webElement);
    }

    public static boolean isDisplayed(WebElement webElement, long secondsToWait) {
        try {
            WebElement foundWebElement = new WebDriverWait(driver(), secondsToWait).until(ExpectedConditions.visibilityOf(webElement));

            return foundWebElement != null;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static boolean isNotDisplayed(WebElement webElement, long secondsToWait) {
        return !isDisplayed(webElement, secondsToWait);
    }

    public static void assertIsDisplayed(WebElement webElement) {
        if (isNotDisplayed(webElement)) {
            throw new WebAssertionError("Element is not displayed", webElement);
        }
    }

    public static void assertIsNotDisplayed(WebElement webElement) {
        if (isDisplayed(webElement)) {
            throw new WebAssertionError("Element is displayed when it shouldn't", webElement);
        }
    }

    public static void assertIsDisplayed(WebElement webElement, long secondsToWait) {
        if (isNotDisplayed(webElement, secondsToWait)) {
            throw new WebAssertionError("Element is not displayed within " + secondsToWait + " seconds", webElement);
        }
    }

    public static void assertIsNotDisplayed(WebElement webElement, long secondsToWait) {
        if (isDisplayed(webElement, secondsToWait)) {
            throw new WebAssertionError("Element is displayed within " + secondsToWait + " seconds when it shouldn't", webElement);
        }
    }



    /* Size */
    public static int sizeOf(Collection collection) {
        return collection.size();
    }

    public static boolean sizeEquals(int number, Collection collection) {
        return BotUtils.isEqual((double) number, (double) collection.size());
    }

    public static boolean sizeNotEquals(int number, Collection collection) {
        return BotUtils.notEquals((double) number, (double) collection.size());
    }

    public static boolean sizeLessThan(int number, Collection collection) {
        return BotUtils.lessThan((double) number, (double) collection.size());
    }

    public static boolean sizeLessThanOrEquals(int number, Collection collection) {
        return BotUtils.lessThanOrEquals((double) number, (double) collection.size());
    }

    public static boolean sizeGreaterThan(int number, Collection collection) {
        return BotUtils.greaterThan((double) number, (double) collection.size());
    }

    public static boolean sizeGreaterThanOrEquals(int number, Collection collection) {
        return BotUtils.greaterThanOrEquals((double) number, (double) collection.size());
    }

    public static void assertSizeEquals(int number, Collection collection) {
        BotUtils.assertEquals("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeNotEquals(int number, Collection collection) {
        BotUtils.assertNotEquals("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeLessThan(int number, Collection collection) {
        BotUtils.assertLessThan("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeLessThanOrEquals(int number, Collection collection) {
        BotUtils.assertLessThanOrEquals("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeGreaterThan(int number, Collection collection) {
        BotUtils.assertGreaterThan("Size", (double) number, (double) collection.size());
    }

    public static void assertSizeGreaterThanOrEquals(int number, Collection collection) {
        BotUtils.assertGreaterThanOrEquals("Size", (double) number, (double) collection.size());
    }


    /* Current Url */
    public static String currentUrl() {
        return driver().getCurrentUrl();
    }

    public static boolean currentUrlEquals(String url) {
        return BotUtils.isEqual(url, currentUrl());
    }

    public static boolean currentUrlNotEquals(String url) {
        return BotUtils.notEquals(url, currentUrl());
    }

    public static boolean currentUrlContains(String searchText) {
        return BotUtils.contains(searchText, currentUrl());
    }

    public static boolean currentUrlNotContains(String searchText) {
        return BotUtils.notContains(searchText, currentUrl());
    }

    public static boolean currentUrlStartsWith(String prefix) {
        return BotUtils.startsWith(prefix, currentUrl());
    }

    public static boolean currentUrlNotStartsWith(String prefix) {
        return BotUtils.notStartsWith(prefix, currentUrl());
    }

    public static boolean currentUrlEndsWith(String suffix) {
        return BotUtils.endsWith(suffix, currentUrl());
    }

    public static boolean currentUrlNotEndsWith(String suffix) {
        return BotUtils.notEndsWith(suffix, currentUrl());
    }

    public static boolean currentUrlMatches(String regExp) {
        return BotUtils.matches(regExp, currentUrl());
    }

    public static boolean currentUrlNotMatches(String regExp) {
        return BotUtils.notMatches(regExp, currentUrl());
    }

    public static void assertCurrentUrlEquals(String url) {
        BotUtils.assertEquals("Current url", url, currentUrl());
    }

    public static void assertCurrentUrlNotEquals(String url) {
        BotUtils.assertNotEquals("Current url", url, currentUrl());
    }

    public static void assertCurrentUrlContains(String searchText) {
        BotUtils.assertContains("Current url", searchText, currentUrl());
    }

    public static void assertCurrentUrlNotContains(String searchText) {
        BotUtils.assertNotContains("Current url", searchText, currentUrl());
    }

    public static void assertCurrentUrlStartsWith(String prefix) {
        BotUtils.assertStartsWith("Current url", prefix, currentUrl());
    }

    public static void assertCurrentUrlNotStartsWith(String prefix) {
        BotUtils.assertNotStartsWith("Current url", prefix, currentUrl());
    }

    public static void assertCurrentUrlEndsWith(String suffix) {
        BotUtils.assertEndsWith("Current url", suffix, currentUrl());
    }

    public static void assertCurrentUrlNotEndsWith(String suffix) {
        BotUtils.assertNotEndsWith("Current url", suffix, currentUrl());
    }

    public static void assertCurrentUrlMatches(String regExp) {
        BotUtils.assertMatches("Current url", regExp, currentUrl());
    }

    public static void assertCurrentUrlNotMatches(String regExp) {
        BotUtils.assertNotMatches("Current url", regExp, currentUrl());
    }



    /* Title */
    public static String title() {
        return driver().getTitle();
    }

    public static boolean titleEquals(String title) {
        return BotUtils.isEqual(title, title());
    }

    public static boolean titleNotEquals(String title) {
        return BotUtils.notEquals(title, title());
    }

    public static boolean titleContains(String searchText) {
        return BotUtils.contains(searchText, title());
    }

    public static boolean titleNotContains(String searchText) {
        return BotUtils.notContains(searchText, title());
    }

    public static boolean titleStartsWith(String prefix) {
        return BotUtils.startsWith(prefix, title());
    }

    public static boolean titleNotStartsWith(String prefix) {
        return BotUtils.notStartsWith(prefix, title());
    }

    public static boolean titleEndsWith(String suffix) {
        return BotUtils.endsWith(suffix, title());
    }

    public static boolean titleNotEndsWith(String suffix) {
        return BotUtils.notEndsWith(suffix, title());
    }

    public static boolean titleMatches(String regExp) {
        return BotUtils.matches(regExp, title());
    }

    public static boolean titleNotMatches(String regExp) {
        return BotUtils.notMatches(regExp, title());
    }

    public static void assertTitleEquals(String title) {
        BotUtils.assertEquals("Title", title, title());
    }

    public static void assertTitleNotEquals(String title) {
        BotUtils.assertNotEquals("Title", title, title());
    }

    public static void assertTitleContains(String searchText) {
        BotUtils.assertContains("Title", searchText, title());
    }

    public static void assertTitleNotContains(String searchText) {
        BotUtils.assertNotContains("Title", searchText, title());
    }

    public static void assertTitleStartsWith(String prefix) {
        BotUtils.assertStartsWith("Title", prefix, title());
    }

    public static void assertTitleNotStartsWith(String prefix) {
        BotUtils.assertNotStartsWith("Title", prefix, title());
    }

    public static void assertTitleEndsWith(String suffix) {
        BotUtils.assertEndsWith("Title", suffix, title());
    }

    public static void assertTitleNotEndsWith(String suffix) {
        BotUtils.assertNotEndsWith("Title", suffix, title());
    }

    public static void assertTitleMatches(String regExp) {
        BotUtils.assertMatches("Title", regExp, title());
    }

    public static void assertTitleNotMatches(String regExp) {
        BotUtils.assertNotMatches("Title", regExp, title());
    }



    /* Tag Name */
    public static String tagNameOf(WebElement webElement) {
        return webElement.getTagName();
    }

    public static boolean tagNameEquals(String value, WebElement webElement) {
        return BotUtils.isEqual(value, tagNameOf(webElement));
    }

    public static boolean tagNameNotEquals(String value, WebElement webElement) {
        return BotUtils.notEquals(value, tagNameOf(webElement));
    }

    public static void assertTagNameEquals(String value, WebElement webElement) {
        BotUtils.assertEquals("Tag name", value, tagNameOf(webElement), webElement);
    }

    public static void assertTagNameNotEquals(String value, WebElement webElement) {
        BotUtils.assertNotEquals("Tag name", value, tagNameOf(webElement), webElement);
    }



    /* Attribute */
    /**
     * Returns a {@link org.openqa.selenium.WebElement} attribute value.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input title="Some title"/>
     * attributeIn("title", input) = "Some title"
     *
     * no input in html
     * attributeIn("title", "input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * an attribute to return
     * @return the id attribute
     */
    public static String attributeIn(String name, WebElement webElement) {
        return webElement.getAttribute(name);
    }

    public static boolean hasAttribute(String name, WebElement webElement) {
        return webElement.getAttribute(name) != null;
    }

    public static boolean hasNotAttribute(String name, WebElement webElement) {
        return !hasAttribute(name, webElement);
    }

    public static boolean attributeEquals(String name, String value, WebElement webElement) {
        return BotUtils.isEqual(value, attributeIn(name, webElement));
    }

    public static boolean attributeNotEquals(String name, String value, WebElement webElement) {
        return BotUtils.notEquals(value, attributeIn(name, webElement));
    }

    public static boolean attributeContains(String name, String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, attributeIn(name, webElement));
    }

    public static boolean attributeNotContains(String name, String searchText, WebElement webElement) {
        return BotUtils.notContains(searchText, attributeIn(name, webElement));
    }

    public static boolean attributeStartsWith(String name, String prefix, WebElement webElement) {
        return BotUtils.startsWith(prefix, attributeIn(name, webElement));
    }

    public static boolean attributeNotStartsWith(String name, String prefix, WebElement webElement) {
        return BotUtils.notStartsWith(prefix, attributeIn(name, webElement));
    }

    public static boolean attributeEndsWith(String name, String suffix, WebElement webElement) {
        return BotUtils.endsWith(suffix, attributeIn(name, webElement));
    }

    public static boolean attributeNotEndsWith(String name, String suffix, WebElement webElement) {
        return BotUtils.notEndsWith(suffix, attributeIn(name, webElement));
    }

    public static boolean attributeMatches(String name, String regExp, WebElement webElement) {
        return BotUtils.matches(regExp, attributeIn(name, webElement));
    }

    public static boolean attributeNotMatches(String name, String regExp, WebElement webElement) {
        return BotUtils.notMatches(regExp, attributeIn(name, webElement));
    }

    public static void assertHasAttribute(String name, WebElement webElement) {
        if (hasNotAttribute(name, webElement)) {
            throw new WebAssertionError("Element does not have attribute " + quote(name), webElement);
        }
    }

    public static void assertHasNotAttribute(String name, WebElement webElement) {
        if (hasAttribute(name, webElement)) {
            throw new WebAssertionError("Element has attribute " + quote(name) + " when it shouldn't", webElement);
        }
    }

    public static void assertAttributeEquals(String name, String value, WebElement webElement) {
        BotUtils.assertEquals("Element attribute " + name, value, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeNotEquals(String name, String value, WebElement webElement) {
        BotUtils.assertNotEquals("Element attribute " + name, value, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeContains(String name, String searchText, WebElement webElement) {
        BotUtils.assertContains("Element attribute " + name, searchText, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeNotContains(String name, String searchText, WebElement webElement) {
        BotUtils.assertNotContains("Element attribute " + name, searchText, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeStartsWith(String name, String prefix, WebElement webElement) {
        BotUtils.assertStartsWith("Element attribute " + name, prefix, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeNotStartsWith(String name, String prefix, WebElement webElement) {
        BotUtils.assertNotStartsWith("Element attribute " + name, prefix, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeEndsWith(String name, String suffix, WebElement webElement) {
        BotUtils.assertEndsWith("Element attribute " + name, suffix, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeNotEndsWith(String name, String suffix, WebElement webElement) {
        BotUtils.assertNotEndsWith("Element attribute " + name, suffix, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeMatches(String name, String regExp, WebElement webElement) {
        BotUtils.assertMatches("Element attribute " + name, regExp, attributeIn(name, webElement), webElement);
    }

    public static void assertAttributeNotMatches(String name, String regExp, WebElement webElement) {
        BotUtils.assertNotMatches("Element attribute " + name, regExp, attributeIn(name, webElement), webElement);
    }



    /* Attribute as Number */
    public static double attributeInAsNumber(String name, WebElement webElement) {
        return createDouble(attributeIn(name, webElement));
    }

    public static boolean attributeIsNumber(String name, WebElement webElement) {
        try {
            attributeInAsNumber(name, webElement);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean attributeIsNotNumber(String name, WebElement webElement) {
        return !attributeIsNumber(name, webElement);
    }

    public static boolean attributeEquals(String name, double number, WebElement webElement) {
        return BotUtils.isEqual(number, attributeInAsNumber(name, webElement));
    }

    public static boolean attributeNotEquals(String name, double number, WebElement webElement) {
        return BotUtils.notEquals(number, attributeInAsNumber(name, webElement));
    }

    public static boolean attributeLessThan(String name, double number, WebElement webElement) {
        return BotUtils.lessThan(number, attributeInAsNumber(name, webElement));
    }

    public static boolean attributeLessThanOrEquals(String name, double number, WebElement webElement) {
        return BotUtils.lessThanOrEquals(number, attributeInAsNumber(name, webElement));
    }

    public static boolean attributeGreaterThan(String name, double number, WebElement webElement) {
        return BotUtils.greaterThan(number, attributeInAsNumber(name, webElement));
    }

    public static boolean attributeGreaterThanOrEquals(String name, double number, WebElement webElement) {
        return BotUtils.greaterThanOrEquals(number, attributeInAsNumber(name, webElement));
    }

    public static void assertAttributeIsNumber(String name, WebElement webElement) {
        if (attributeIsNotNumber(name, webElement)) {
            throw new WebAssertionError("Element attribute " + name + " is not a number", webElement);
        }
    }

    public static void assertAttributeIsNotNumber(String name, WebElement webElement) {
        if (attributeIsNumber(name, webElement)) {
            throw new WebAssertionError("Element attribute " + name + " is a number when it shouldn't", webElement);
        }
    }

    public static void assertAttributeEquals(String name, double number, WebElement webElement) {
        BotUtils.assertEquals(name, number, attributeInAsNumber(name, webElement), webElement);
    }

    public static void assertAttributeNotEquals(String name, double number, WebElement webElement) {
        BotUtils.assertNotEquals(name, number, attributeInAsNumber(name, webElement), webElement);
    }

    public static void assertAttributeLessThan(String name, double number, WebElement webElement) {
        BotUtils.assertLessThan(name, number, attributeInAsNumber(name, webElement), webElement);
    }

    public static void assertAttributeLessThanOrEquals(String name, double number, WebElement webElement) {
        BotUtils.assertLessThanOrEquals(name, number, attributeInAsNumber(name, webElement), webElement);
    }

    public static void assertAttributeGreaterThan(String name, double number, WebElement webElement) {
        BotUtils.assertGreaterThan(name, number, attributeInAsNumber(name, webElement), webElement);
    }

    public static void assertAttributeGreaterThanOrEquals(String name, double number, WebElement webElement) {
        BotUtils.assertGreaterThanOrEquals(name, number, attributeInAsNumber(name, webElement), webElement);
    }



    /* Id */
    /**
     * Returns the {@link org.openqa.selenium.WebElement} id attribute.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input id="some-id"/>
     * idIn(input) = "some-id"
     *
     * no input in html
     * idIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * an id attribute
     * @return the id attribute
     */
    public static String idIn(WebElement webElement) {
        return attributeIn("id", webElement);
    }

    public static boolean hasId(WebElement webElement) {
        return hasAttribute("id", webElement);
    }

    public static boolean hasNotId(WebElement webElement) {
        return hasNotAttribute("id", webElement);
    }

    public static boolean idEquals(String value, WebElement webElement) {
        return attributeEquals("id", value, webElement);
    }

    public static boolean idNotEquals(String value, WebElement webElement) {
        return attributeNotEquals("id", value, webElement);
    }

    public static boolean idContains(String searchText, WebElement webElement) {
        return attributeContains("id", searchText, webElement);
    }

    public static boolean idNotContains(String searchText, WebElement webElement) {
        return attributeNotContains("id", searchText, webElement);
    }

    public static boolean idStartsWith(String prefix, WebElement webElement) {
        return attributeStartsWith("id", prefix, webElement);
    }

    public static boolean idNotStartsWith(String prefix, WebElement webElement) {
        return attributeNotStartsWith("id", prefix, webElement);
    }

    public static boolean idEndsWith(String suffix, WebElement webElement) {
        return attributeEndsWith("id", suffix, webElement);
    }

    public static boolean idNotEndsWith(String suffix, WebElement webElement) {
        return attributeNotEndsWith("id", suffix, webElement);
    }

    public static boolean idMatches(String regExp, WebElement webElement) {
        return attributeMatches("id", regExp, webElement);
    }

    public static boolean idNotMatches(String regExp, WebElement webElement) {
        return attributeNotMatches("id", regExp, webElement);
    }

    public static void assertHasId(WebElement webElement) {
        assertHasAttribute("id", webElement);
    }

    public static void assertHasNotId(WebElement webElement) {
        assertHasNotAttribute("id", webElement);
    }

    public static void assertIdEquals(String value, WebElement webElement) {
        assertAttributeEquals("id", value, webElement);
    }

    public static void assertIdNotEquals(String value, WebElement webElement) {
        assertAttributeNotEquals("id", value, webElement);
    }

    public static void assertIdContains(String searchText, WebElement webElement) {
        assertAttributeContains("id", searchText, webElement);
    }

    public static void assertIdNotContains(String searchText, WebElement webElement) {
        assertAttributeNotContains("id", searchText, webElement);
    }

    public static void assertIdStartsWith(String prefix, WebElement webElement) {
        assertAttributeStartsWith("id", prefix, webElement);
    }

    public static void assertIdNotStartsWith(String prefix, WebElement webElement) {
        assertAttributeNotStartsWith("id", prefix, webElement);
    }

    public static void assertIdEndsWith(String suffix, WebElement webElement) {
        assertAttributeEndsWith("id", suffix, webElement);
    }

    public static void assertIdNotEndsWith(String suffix, WebElement webElement) {
        assertAttributeNotEndsWith("id", suffix, webElement);
    }

    public static void assertIdMatches(String regExp, WebElement webElement) {
        assertAttributeMatches("id", regExp, webElement);
    }

    public static void assertIdNotMatches(String regExp, WebElement webElement) {
        assertAttributeNotMatches("id", regExp, webElement);
    }



    /* Name */
    /**
     * Returns the {@link org.openqa.selenium.WebElement} name attribute.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input name="some-name"/>
     * nameIn(input) = "some-name"
     *
     * no input in html
     * nameIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a name attribute
     * @return the name attribute
     */
    public static String nameIn(WebElement webElement) {
        return attributeIn("name", webElement);
    }

    public static boolean hasName(WebElement webElement) {
        return hasAttribute("name", webElement);
    }

    public static boolean hasNotName(WebElement webElement) {
        return hasNotAttribute("name", webElement);
    }

    public static boolean nameEquals(String value, WebElement webElement) {
        return attributeEquals("name", value, webElement);
    }

    public static boolean nameNotEquals(String value, WebElement webElement) {
        return attributeNotEquals("name", value, webElement);
    }

    public static boolean nameContains(String searchText, WebElement webElement) {
        return attributeContains("name", searchText, webElement);
    }

    public static boolean nameNotContains(String searchText, WebElement webElement) {
        return attributeNotContains("name", searchText, webElement);
    }

    public static boolean nameStartsWith(String prefix, WebElement webElement) {
        return attributeStartsWith("name", prefix, webElement);
    }

    public static boolean nameNotStartsWith(String prefix, WebElement webElement) {
        return attributeNotStartsWith("name", prefix, webElement);
    }

    public static boolean nameEndsWith(String suffix, WebElement webElement) {
        return attributeEndsWith("name", suffix, webElement);
    }

    public static boolean nameNotEndsWith(String suffix, WebElement webElement) {
        return attributeNotEndsWith("name", suffix, webElement);
    }

    public static boolean nameMatches(String regExp, WebElement webElement) {
        return attributeMatches("name", regExp, webElement);
    }

    public static boolean nameNotMatches(String regExp, WebElement webElement) {
        return attributeNotMatches("name", regExp, webElement);
    }

    public static void assertHasName(WebElement webElement) {
        assertHasAttribute("name", webElement);
    }

    public static void assertHasNotName(WebElement webElement) {
        assertHasNotAttribute("name", webElement);
    }

    public static void assertNameEquals(String value, WebElement webElement) {
        assertAttributeEquals("name", value, webElement);
    }

    public static void assertNameNotEquals(String value, WebElement webElement) {
        assertAttributeNotEquals("name", value, webElement);
    }

    public static void assertNameContains(String searchText, WebElement webElement) {
        assertAttributeContains("name", searchText, webElement);
    }

    public static void assertNameNotContains(String searchText, WebElement webElement) {
        assertAttributeNotContains("name", searchText, webElement);
    }

    public static void assertNameStartsWith(String prefix, WebElement webElement) {
        assertAttributeStartsWith("name", prefix, webElement);
    }

    public static void assertNameNotStartsWith(String prefix, WebElement webElement) {
        assertAttributeNotStartsWith("name", prefix, webElement);
    }

    public static void assertNameEndsWith(String suffix, WebElement webElement) {
        assertAttributeEndsWith("name", suffix, webElement);
    }

    public static void assertNameNotEndsWith(String suffix, WebElement webElement) {
        assertAttributeNotEndsWith("name", suffix, webElement);
    }

    public static void assertNameMatches(String regExp, WebElement webElement) {
        assertAttributeMatches("name", regExp, webElement);
    }

    public static void assertNameNotMatches(String regExp, WebElement webElement) {
        assertAttributeNotMatches("name", regExp, webElement);
    }



    /* Class */
    /**
     * Returns the {@link org.openqa.selenium.WebElement} class attribute.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input class="a-class another-class"/>
     * classIn(input) = "a-class another-class"
     *
     * no input in html
     * classIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a class attribute
     * @return the class attribute
     */
    public static String classIn(WebElement webElement) {
        return attributeIn("class", webElement);
    }

    /**
     * Returns the classes in the {@link org.openqa.selenium.WebElement} class
     * attribute.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input class=" a-class   another-class "/>
     * classesIn(input) = "a-class", "another-class"
     *
     * no input in html
     * classIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a class attribute
     * @return the classes in the class attribute
     */
    public static List<String> classesIn(WebElement webElement) {
        return Arrays.asList(StringUtils.split(classIn(webElement)));
    }

    public static boolean hasClass(WebElement webElement) {
        return hasAttribute("class", webElement);
    }

    public static boolean hasNotClass(WebElement webElement) {
        return hasNotAttribute("class", webElement);
    }

    public static boolean hasClass(String className, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.isEqual(className, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClass(String className, WebElement webElement) {
        return !hasClass(className, webElement);
    }

    public static boolean hasClassContaining(String searchText, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.contains(searchText, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClassContaining(String searchText, WebElement webElement) {
        return !hasClassContaining(searchText, webElement);
    }

    public static boolean hasClassStartingWith(String prefix, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.startsWith(prefix, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClassStartingWith(String prefix, WebElement webElement) {
        return !hasClassStartingWith(prefix, webElement);
    }

    public static boolean hasClassEndingWith(String suffix, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.endsWith(suffix, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClassEndingWith(String suffix, WebElement webElement) {
        return !hasClassEndingWith(suffix, webElement);
    }

    public static boolean hasClassMatching(String regExp, WebElement webElement) {
        List<String> classes = classesIn(webElement);
        for (String clazz : classes) {
            if (BotUtils.matches(regExp, clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotClassMatching(String regExp, WebElement webElement) {
        return !hasClassMatching(regExp, webElement);
    }

    public static void assertHasClass(WebElement webElement) {
        assertHasAttribute("class", webElement);
    }

    public static void assertHasNotClass(WebElement webElement) {
        assertHasNotAttribute("class", webElement);
    }

    public static void assertHasClass(String className, WebElement webElement) {
        if (hasNotClass(className, webElement)) {
            throw new WebAssertionError("Element does not have class " + quote(className.trim()), webElement);
        }
    }

    public static void assertHasNotClass(String className, WebElement webElement) {
        if (hasClass(className, webElement)) {
            throw new WebAssertionError("Element has class " + quote(className.trim()) + " when it shouldn't", webElement);
        }
    }

    public static void assertHasClassContaining(String searchText, WebElement webElement) {
        if (hasNotClassContaining(searchText, webElement)) {
            throw new WebAssertionError("Element does not have class containing text " + quote(searchText.trim()), webElement);
        }
    }

    public static void assertHasNotClassContaining(String searchText, WebElement webElement) {
        if (hasClassContaining(searchText, webElement)) {
            throw new WebAssertionError("Element has class containing text " + quote(searchText.trim()) + " when it shouldn't", webElement);
        }
    }

    public static void assertHasClassStartingWith(String prefix, WebElement webElement) {
        if (hasNotClassStartingWith(prefix, webElement)) {
            throw new WebAssertionError("Element does not have class containing prefix " + quote(prefix.trim()), webElement);
        }
    }

    public static void assertHasNotClassStartingWith(String prefix, WebElement webElement) {
        if (hasClassStartingWith(prefix, webElement)) {
            throw new WebAssertionError("Element has class containing prefix " + quote(prefix.trim()) + " when it shouldn't", webElement);
        }
    }

    public static void assertHasClassEndingWith(String suffix, WebElement webElement) {
        if (hasNotClassEndingWith(suffix, webElement)) {
            throw new WebAssertionError("Element does not have class containing suffix " + quote(suffix.trim()), webElement);
        }
    }

    public static void assertHasNotClassEndingWith(String suffix, WebElement webElement) {
        if (hasClassEndingWith(suffix, webElement)) {
            throw new WebAssertionError("Element has class containing suffix " + quote(suffix.trim()) + " when it shouldn't", webElement);
        }
    }

    public static void assertHasClassMatching(String regExp, WebElement webElement) {
        if (hasNotClassMatching(regExp, webElement)) {
            throw new WebAssertionError("Element does not have class matching regExp " + quote(regExp.trim()), webElement);
        }
    }

    public static void assertHasNotClassMatching(String regExp, WebElement webElement) {
        if (hasClassMatching(regExp, webElement)) {
            throw new WebAssertionError("Element has class matching regExp " + quote(regExp.trim()) + " when it shouldn't", webElement);
        }
    }



    /* Value */
    /**
     * Returns the {@link org.openqa.selenium.WebElement} value attribute.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input value="Some value"/>
     * valueIn(input) = "Some value"
     *
     * no input in html
     * valueIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a value attribute
     * @return the value attribute
     */
    public static String valueIn(WebElement webElement) {
        return attributeIn("value", webElement);
    }

    public static boolean hasValue(WebElement webElement) {
        return hasAttribute("value", webElement);
    }

    public static boolean hasNotValue(WebElement webElement) {
        return hasNotAttribute("value", webElement);
    }

    public static boolean valueEquals(String value, WebElement webElement) {
        return attributeEquals("value", value, webElement);
    }

    public static boolean valueNotEquals(String value, WebElement webElement) {
        return attributeNotEquals("value", value, webElement);
    }

    public static boolean valueContains(String searchText, WebElement webElement) {
        return attributeContains("value", searchText, webElement);
    }

    public static boolean valueNotContains(String searchText, WebElement webElement) {
        return attributeNotContains("value", searchText, webElement);
    }

    public static boolean valueStartsWith(String prefix, WebElement webElement) {
        return attributeStartsWith("value", prefix, webElement);
    }

    public static boolean valueNotStartsWith(String prefix, WebElement webElement) {
        return attributeNotStartsWith("value", prefix, webElement);
    }

    public static boolean valueEndsWith(String suffix, WebElement webElement) {
        return attributeEndsWith("value", suffix, webElement);
    }

    public static boolean valueNotEndsWith(String suffix, WebElement webElement) {
        return attributeNotEndsWith("value", suffix, webElement);
    }

    public static boolean valueMatches(String regExp, WebElement webElement) {
        return attributeMatches("value", regExp, webElement);
    }

    public static boolean valueNotMatches(String regExp, WebElement webElement) {
        return attributeNotMatches("value", regExp, webElement);
    }

    public static void assertHasValue(WebElement webElement) {
        assertHasAttribute("value", webElement);
    }

    public static void assertHasNotValue(WebElement webElement) {
        assertHasNotAttribute("value", webElement);
    }

    public static void assertValueEquals(String value, WebElement webElement) {
        assertAttributeEquals("value", value, webElement);
    }

    public static void assertValueNotEquals(String value, WebElement webElement) {
        assertAttributeNotEquals("value", value, webElement);
    }

    public static void assertValueContains(String searchText, WebElement webElement) {
        assertAttributeContains("value", searchText, webElement);
    }

    public static void assertValueNotContains(String searchText, WebElement webElement) {
        assertAttributeNotContains("value", searchText, webElement);
    }

    public static void assertValueStartsWith(String prefix, WebElement webElement) {
        assertAttributeStartsWith("value", prefix, webElement);
    }

    public static void assertValueNotStartsWith(String prefix, WebElement webElement) {
        assertAttributeNotStartsWith("value", prefix, webElement);
    }

    public static void assertValueEndsWith(String suffix, WebElement webElement) {
        assertAttributeEndsWith("value", suffix, webElement);
    }

    public static void assertValueNotEndsWith(String suffix, WebElement webElement) {
        assertAttributeNotEndsWith("value", suffix, webElement);
    }

    public static void assertValueMatches(String regExp, WebElement webElement) {
        assertAttributeMatches("value", regExp, webElement);
    }

    public static void assertValueNotMatches(String regExp, WebElement webElement) {
        assertAttributeNotMatches("value", regExp, webElement);
    }



    /* Value as Number */
    /**
     * Returns the {@link org.openqa.selenium.WebElement} value attribute as a
     * number.
     *
     * <p>If the value attribute in the {@link org.openqa.selenium.WebElement}
     * does not contain a valid number a {@code java.util.NumberFormatException}
     * will be thrown. If the {@link org.openqa.selenium.WebElement} does not
     * exist in the html a {@code org.openqa.selenium.NoSuchElementException}
     * will be thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input value="42"/>
     * valueInAsNumber(input) = 42.0
     *
     * <input value="Some value"/>
     * valueInAsNumber(input) throws java.util.NumberFormatException
     *
     * <input value=""/>
     * valueInAsNumber(input) throws java.util.NumberFormatException
     *
     * no input in html
     * valueInAsNumber(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a value attribute with a number
     * @return the value attribute as a number
     */
    public static double valueInAsNumber(WebElement webElement) {
        return attributeInAsNumber("value", webElement);
    }

    public static boolean valueIsNumber(WebElement webElement) {
        return attributeIsNumber("value", webElement);
    }

    public static boolean valueIsNotNumber(WebElement webElement) {
        return attributeIsNotNumber("value", webElement);
    }

    public static boolean valueEquals(double number, WebElement webElement) {
        return attributeEquals("value", number, webElement);
    }

    public static boolean valueNotEquals(double number, WebElement webElement) {
        return attributeNotEquals("value", number, webElement);
    }

    public static boolean valueLessThan(double number, WebElement webElement) {
        return attributeLessThan("value", number, webElement);
    }

    public static boolean valueLessThanOrEquals(double number, WebElement webElement) {
        return attributeLessThanOrEquals("value", number, webElement);
    }

    public static boolean valueGreaterThan(double number, WebElement webElement) {
        return attributeGreaterThan("value", number, webElement);
    }

    public static boolean valueGreaterThanOrEquals(double number, WebElement webElement) {
        return attributeGreaterThanOrEquals("value", number, webElement);
    }

    public static void assertValueIsNumber(WebElement webElement) {
        assertAttributeIsNumber("value", webElement);
    }

    public static void assertValueIsNotNumber(WebElement webElement) {
        assertAttributeIsNotNumber("value", webElement);
    }

    public static void assertValueEquals(double number, WebElement webElement) {
        assertAttributeEquals("value", number, webElement);
    }

    public static void assertValueNotEquals(double number, WebElement webElement) {
        assertAttributeNotEquals("value", number, webElement);
    }

    public static void assertValueLessThan(double number, WebElement webElement) {
        assertAttributeLessThan("value", number, webElement);
    }

    public static void assertValueLessThanOrEquals(double number, WebElement webElement) {
        assertAttributeLessThanOrEquals("value", number, webElement);
    }

    public static void assertValueGreaterThan(double number, WebElement webElement) {
        assertAttributeGreaterThan("value", number, webElement);
    }

    public static void assertValueGreaterThanOrEquals(double number, WebElement webElement) {
        assertAttributeGreaterThanOrEquals("value", number, webElement);
    }



    /* Href */
    /**
     * Returns the {@link org.openqa.selenium.WebElement} href attribute.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <input href="www.href.com"/>
     * hrefIn(input) = "www.href.com"
     *
     * no input in html
     * hrefIn(input) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a href attribute
     * @return the href attribute
     */
    public static String hrefIn(WebElement webElement) {
        return attributeIn("href", webElement);
    }

    public static boolean hasHref(WebElement webElement) {
        return hasAttribute("href", webElement);
    }

    public static boolean hasNotHref(WebElement webElement) {
        return hasNotAttribute("href", webElement);
    }

    public static boolean hrefEquals(String value, WebElement webElement) {
        return attributeEquals("href", value, webElement);
    }

    public static boolean hrefNotEquals(String value, WebElement webElement) {
        return attributeNotEquals("href", value, webElement);
    }

    public static boolean hrefContains(String searchText, WebElement webElement) {
        return attributeContains("href", searchText, webElement);
    }

    public static boolean hrefNotContains(String searchText, WebElement webElement) {
        return attributeNotContains("href", searchText, webElement);
    }

    public static boolean hrefStartsWith(String prefix, WebElement webElement) {
        return attributeStartsWith("href", prefix, webElement);
    }

    public static boolean hrefNotStartsWith(String prefix, WebElement webElement) {
        return attributeNotStartsWith("href", prefix, webElement);
    }

    public static boolean hrefEndsWith(String suffix, WebElement webElement) {
        return attributeEndsWith("href", suffix, webElement);
    }

    public static boolean hrefNotEndsWith(String suffix, WebElement webElement) {
        return attributeNotEndsWith("href", suffix, webElement);
    }

    public static boolean hrefMatches(String regExp, WebElement webElement) {
        return attributeMatches("href", regExp, webElement);
    }

    public static boolean hrefNotMatches(String regExp, WebElement webElement) {
        return attributeNotMatches("href", regExp, webElement);
    }

    public static void assertHasHref(WebElement webElement) {
        assertHasAttribute("href", webElement);
    }

    public static void assertHasNotHref(WebElement webElement) {
        assertHasNotAttribute("href", webElement);
    }

    public static void assertHrefEquals(String value, WebElement webElement) {
        assertAttributeEquals("href", value, webElement);
    }

    public static void assertHrefNotEquals(String value, WebElement webElement) {
        assertAttributeNotEquals("href", value, webElement);
    }

    public static void assertHrefContains(String searchText, WebElement webElement) {
        assertAttributeContains("href", searchText, webElement);
    }

    public static void assertHrefNotContains(String searchText, WebElement webElement) {
        assertAttributeNotContains("href", searchText, webElement);
    }

    public static void assertHrefStartsWith(String prefix, WebElement webElement) {
        assertAttributeStartsWith("href", prefix, webElement);
    }

    public static void assertHrefNotStartsWith(String prefix, WebElement webElement) {
        assertAttributeNotStartsWith("href", prefix, webElement);
    }

    public static void assertHrefEndsWith(String suffix, WebElement webElement) {
        assertAttributeEndsWith("href", suffix, webElement);
    }

    public static void assertHrefNotEndsWith(String suffix, WebElement webElement) {
        assertAttributeNotEndsWith("href", suffix, webElement);
    }

    public static void assertHrefMatches(String regExp, WebElement webElement) {
        assertAttributeMatches("href", regExp, webElement);
    }

    public static void assertHrefNotMatches(String regExp, WebElement webElement) {
        assertAttributeNotMatches("href", regExp, webElement);
    }



    /* Text */
    /**
     * Returns the visible text in a {@link org.openqa.selenium.WebElement}.
     *
     * <p>If the {@link org.openqa.selenium.WebElement} does not exist in the
     * html a {@code org.openqa.selenium.NoSuchElementException} will be thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <span>Some text</span>
     * textIn(span) = "Some text"
     *
     * <span>
     *     Some text containing <b>html</b>
     * </span>
     * textIn(span) = "Some text containing html"
     *
     * <span style="display: none">Some invisible text</span>
     * textIn(span) = ""
     *
     * no span in html
     * textIn(span) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * the text
     * @return the visible text
     */
    public static String textIn(WebElement webElement) {
        // Text is trimmed to normalize behavior since Chrome and PhantomJS driver incorrectly returns spaces around the text (Not according the the WebElement tetText docs), see bug report https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/7473 remove this when bug is solved!
        return StringUtils.trim(webElement.getText());
    }

    public static boolean hasText(WebElement webElement) {
        return BotUtils.notEquals("", textIn(webElement));
    }

    public static boolean hasNotText(WebElement webElement) {
        return BotUtils.isEqual("", textIn(webElement));
    }

    public static boolean textEquals(String text, WebElement webElement) {
        return BotUtils.isEqual(text, textIn(webElement));
    }

    public static boolean textNotEquals(String text, WebElement webElement) {
        return BotUtils.notEquals(text, textIn(webElement));
    }

    public static boolean textEqualsIgnoreCase(String text, WebElement webElement) {
        return BotUtils.equalsIgnoreCase(text, textIn(webElement));
    }

    public static boolean textNotEqualsIgnoreCase(String text, WebElement webElement) {
        return BotUtils.notEqualsIgnoreCase(text, textIn(webElement));
    }

    public static boolean textContains(String searchText, WebElement webElement) {
        return BotUtils.contains(searchText, textIn(webElement));
    }

    public static boolean textNotContains(String searchText, WebElement webElement) {
        return BotUtils.notContains(searchText, textIn(webElement));
    }

    public static boolean textContainsIgnoreCase(String searchText, WebElement webElement) {
        return BotUtils.containsIgnoreCase(searchText, textIn(webElement));
    }

    public static boolean textNotContainsIgnoreCase(String searchText, WebElement webElement) {
        return BotUtils.notContainsIgnoreCase(searchText, textIn(webElement));
    }

    public static boolean textStartsWith(String prefix, WebElement webElement) {
        return BotUtils.startsWith(prefix, textIn(webElement));
    }

    public static boolean textNotStartsWith(String prefix, WebElement webElement) {
        return BotUtils.notStartsWith(prefix, textIn(webElement));
    }

    public static boolean textStartsWithIgnoreCase(String prefix, WebElement webElement) {
        return BotUtils.startsWithIgnoreCase(prefix, textIn(webElement));
    }

    public static boolean textNotStartsWithIgnoreCase(String prefix, WebElement webElement) {
        return BotUtils.notStartsWithIgnoreCase(prefix, textIn(webElement));
    }

    public static boolean textEndsWith(String suffix, WebElement webElement) {
        return BotUtils.endsWith(suffix, textIn(webElement));
    }

    public static boolean textNotEndsWith(String suffix, WebElement webElement) {
        return BotUtils.notEndsWith(suffix, textIn(webElement));
    }

    public static boolean textEndsWithIgnoreCase(String suffix, WebElement webElement) {
        return BotUtils.endsWithIgnoreCase(suffix, textIn(webElement));
    }

    public static boolean textNotEndsWithIgnoreCase(String suffix, WebElement webElement) {
        return BotUtils.notEndsWithIgnoreCase(suffix, textIn(webElement));
    }

    public static boolean textMatches(String regExp, WebElement webElement) {
        return BotUtils.matches(regExp, textIn(webElement));
    }

    public static boolean textNotMatches(String regExp, WebElement webElement) {
        return BotUtils.notMatches(regExp, textIn(webElement));
    }

    public static void assertHasText(WebElement webElement) {
        if (hasNotText(webElement)) {
            throw new WebAssertionError("Element has no text", webElement);
        }
    }

    public static void assertHasNotText(WebElement webElement) {
        if (hasText(webElement)) {
            throw new WebAssertionError("Element has text " + quote(textIn(webElement)) + " when it shouldn't", webElement);
        }
    }

    public static void assertTextEquals(String text, WebElement webElement) {
        BotUtils.assertEquals("Text", text, textIn(webElement), webElement);
    }

    public static void assertTextNotEquals(String text, WebElement webElement) {
        BotUtils.assertNotEquals("Text", text, textIn(webElement), webElement);
    }

    public static void assertTextEqualsIgnoreCase(String text, WebElement webElement) {
        BotUtils.assertEqualsIgnoreCase("Text", text, textIn(webElement), webElement);
    }

    public static void assertTextNotEqualsIgnoreCase(String text, WebElement webElement) {
        BotUtils.assertNotEqualsIgnoreCase("Text", text, textIn(webElement), webElement);
    }

    public static void assertTextContains(String searchText, WebElement webElement) {
        BotUtils.assertContains("Text", searchText, textIn(webElement), webElement);
    }

    public static void assertTextNotContains(String searchText, WebElement webElement) {
        BotUtils.assertNotContains("Text", searchText, textIn(webElement), webElement);
    }

    public static void assertTextContainsIgnoreCase(String searchText, WebElement webElement) {
        BotUtils.assertContainsIgnoreCase("Text", searchText, textIn(webElement), webElement);
    }

    public static void assertTextNotContainsIgnoreCase(String searchText, WebElement webElement) {
        BotUtils.assertNotContainsIgnoreCase("Text", searchText, textIn(webElement), webElement);
    }

    public static void assertTextStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertStartsWith("Text", prefix, textIn(webElement), webElement);
    }

    public static void assertTextNotStartsWith(String prefix, WebElement webElement) {
        BotUtils.assertNotStartsWith("Text", prefix, textIn(webElement), webElement);
    }

    public static void assertTextStartsWithIgnoreCase(String prefix, WebElement webElement) {
        BotUtils.assertStartsWithIgnoreCase("Text", prefix, textIn(webElement), webElement);
    }

    public static void assertTextNotStartsWithIgnoreCase(String prefix, WebElement webElement) {
        BotUtils.assertNotStartsWithIgnoreCase("Text", prefix, textIn(webElement), webElement);
    }

    public static void assertTextEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertEndsWith("Text", suffix, textIn(webElement), webElement);
    }

    public static void assertTextNotEndsWith(String suffix, WebElement webElement) {
        BotUtils.assertNotEndsWith("Text", suffix, textIn(webElement), webElement);
    }

    public static void assertTextEndsWithIgnoreCase(String suffix, WebElement webElement) {
        BotUtils.assertEndsWithIgnoreCase("Text", suffix, textIn(webElement), webElement);
    }

    public static void assertTextNotEndsWithIgnoreCase(String suffix, WebElement webElement) {
        BotUtils.assertNotEndsWithIgnoreCase("Text", suffix, textIn(webElement), webElement);
    }

    public static void assertTextMatches(String regExp, WebElement webElement) {
        BotUtils.assertMatches("Text", regExp, textIn(webElement), webElement);
    }

    public static void assertTextNotMatches(String regExp, WebElement webElement) {
        BotUtils.assertNotMatches("Text", regExp, textIn(webElement), webElement);
    }



    /* Text as Number */
    /**
     * Returns the visible text in a {@link org.openqa.selenium.WebElement} as a
     * number.
     *
     * <p>If the text in the {@link org.openqa.selenium.WebElement} does not
     * contain a valid number a {@code java.util.NumberFormatException} will be
     * thrown. If the {@link org.openqa.selenium.WebElement} does not exist in
     * the html a {@code org.openqa.selenium.NoSuchElementException} will be
     * thrown.</p>
     *
     * <p>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * <span>42</span>
     * textInAsNumber(span) = 42.0
     *
     * <span>
     *     42
     * </span>
     * textInAsNumber(span) = 42.0
     *
     * <span>Some text</span>
     * textInAsNumber(span) throws java.util.NumberFormatException
     *
     * <span style="display: none">42</span>
     * textInAsNumber(span) throws java.util.NumberFormatException
     *
     * no span in html
     * textInAsNumber(span) throws org.openqa.selenium.NoSuchElementException}</pre>
     * </p>
     *
     * @param webElement the {@link org.openqa.selenium.WebElement} containing
     * a text with a number
     * @return the visible text as a number
     */
     public static double textInAsNumber(WebElement webElement) {
        return createDouble(textIn(webElement));
    }

    public static boolean textIsNumber(WebElement webElement) {
        try {
            textInAsNumber(webElement);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean textIsNotNumber(WebElement webElement) {
        return !textIsNumber(webElement);
    }

    public static boolean textEquals(double number, WebElement webElement) {
        return BotUtils.isEqual(number, textInAsNumber(webElement));
    }

    public static boolean textNotEquals(double number, WebElement webElement) {
        return BotUtils.notEquals(number, textInAsNumber(webElement));
    }

    public static boolean textLessThan(double number, WebElement webElement) {
        return BotUtils.lessThan(number, textInAsNumber(webElement));
    }

    public static boolean textLessThanOrEquals(double number, WebElement webElement) {
        return BotUtils.lessThanOrEquals(number, textInAsNumber(webElement));
    }

    public static boolean textGreaterThan(double number, WebElement webElement) {
        return BotUtils.greaterThan(number, textInAsNumber(webElement));
    }

    public static boolean textGreaterThanOrEquals(double number, WebElement webElement) {
        return BotUtils.greaterThanOrEquals(number, textInAsNumber(webElement));
    }

    public static void assertTextIsNumber(WebElement webElement) {
        if (textIsNotNumber(webElement)) {
            throw new WebAssertionError("Element text is not a number", webElement);
        }
    }

    public static void assertTextIsNotNumber(WebElement webElement) {
        if (textIsNumber(webElement)) {
            throw new WebAssertionError("Element text is a number when it shouldn't", webElement);
        }
    }

    public static void assertTextEquals(double number, WebElement webElement) {
        BotUtils.assertEquals("Text", number, textInAsNumber(webElement), webElement);
    }

    public static void assertTextNotEquals(double number, WebElement webElement) {
        BotUtils.assertNotEquals("Text", number, textInAsNumber(webElement), webElement);
    }

    public static void assertTextLessThan(double number, WebElement webElement) {
        BotUtils.assertLessThan("Text", number, textInAsNumber(webElement), webElement);
    }

    public static void assertTextLessThanOrEquals(double number, WebElement webElement) {
        BotUtils.assertLessThanOrEquals("Text", number, textInAsNumber(webElement), webElement);
    }

    public static void assertTextGreaterThan(double number, WebElement webElement) {
        BotUtils.assertGreaterThan("Text", number, textInAsNumber(webElement), webElement);
    }

    public static void assertTextGreaterThanOrEquals(double number, WebElement webElement) {
        BotUtils.assertGreaterThanOrEquals("Text", number, textInAsNumber(webElement), webElement);
    }



    /* Selected/Deselected */
    public static boolean isSelected(WebElement webElement) {
        return webElement.isSelected();
    }

    public static boolean isDeselected(WebElement webElement) {
        return !isSelected(webElement);
    }

    public static void assertIsSelected(WebElement webElement) {
        if (isDeselected(webElement)) {
            throw new WebAssertionError("Element is not selected", webElement);
        }
    }

    public static void assertIsDeselected(WebElement webElement) {
        if (isSelected(webElement)) {
            throw new WebAssertionError("Element is not deselected", webElement);
        }
    }




    /* Checked/Unchecked */
    public static boolean isChecked(WebElement webElement) {
        return webElement.isSelected();
    }

    public static boolean isUnchecked(WebElement webElement) {
        return !isChecked(webElement);
    }

    public static void assertIsChecked(WebElement webElement) {
        if (isUnchecked(webElement)) {
            throw new WebAssertionError("Element is not checked", webElement);
        }
    }

    public static void assertIsUnchecked(WebElement webElement) {
        if (isChecked(webElement)) {
            throw new WebAssertionError("Element is not unchecked", webElement);
        }
    }



    /* Enabled/Disabled */
    public static boolean isEnabled(WebElement webElement) {
        return webElement.isEnabled();
    }

    public static boolean isDisabled(WebElement webElement) {
        return !isEnabled(webElement);
    }

    public static void assertIsEnabled(WebElement webElement) {
        if (isDisabled(webElement)) {
            throw new WebAssertionError("Element is not enabled", webElement);
        }
    }

    public static void assertIsDisabled(WebElement webElement) {
        if (isEnabled(webElement)) {
            throw new WebAssertionError("Element is not disabled", webElement);
        }
    }



    /* Option */
    public static boolean hasOption(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotOption(String text, WebElement webElement) {
        return !hasOption(text, webElement);
    }

    public static boolean optionIsEnabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionIsDisabled(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionIsSelected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionIsDeselected(String text, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (textEquals(text, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean allOptionsAreSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isDeselected(option)) {
                return false;
            }
        }
        return true;
    }

    public static boolean noOptionIsSelected(WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (isSelected(option)) {
                return false;
            }
        }
        return true;
    }

    public static void assertHasOption(String text, WebElement webElement) {
        if (hasNotOption(text, webElement)) {
            throw new WebAssertionError("Element has no option " + quote(text.trim()), webElement);
        }
    }

    public static void assertHasNotOption(String text, WebElement webElement) {
        if (hasOption(text, webElement)) {
            throw new WebAssertionError("Element has option " + quote(text.trim()) + " when it shouldn't", webElement);
        }
    }

    public static void assertOptionIsEnabled(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsDisabled(text, webElement)) {
            throw new WebAssertionError("Option " + quote(text.trim()) + " is not enabled", webElement);
        }
    }

    public static void assertOptionIsDisabled(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsEnabled(text, webElement)) {
            throw new WebAssertionError("Option " + quote(text.trim()) + " is not disabled", webElement);
        }
    }

    public static void assertOptionIsSelected(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsDeselected(text, webElement)) {
            throw new WebAssertionError("Option " + quote(text.trim()) + " is not selected", webElement);
        }
    }

    public static void assertOptionIsDeselected(String text, WebElement webElement) {
        assertHasOption(text, webElement);
        if (optionIsSelected(text, webElement)) {
            throw new WebAssertionError("Option " + quote(text.trim()) + " is not deselected", webElement);
        }
    }

    public static void assertAllOptionsAreSelected(WebElement webElement) {
        if (!allOptionsAreSelected(webElement)) {
            throw new WebAssertionError("All options are not selected", webElement);
        }
    }

    public static void assertNoOptionIsSelected(WebElement webElement) {
        if (!noOptionIsSelected(webElement)) {
            throw new WebAssertionError("All options are not deselected", webElement);
        }
    }



    /* Option Value */
    public static boolean hasOptionWithValue(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNotOptionWithValue(String value, WebElement webElement) {
        return !hasOptionWithValue(value, webElement);
    }

    public static boolean optionWithValueIsEnabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isEnabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionWithValueIsDisabled(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isDisabled(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionWithValueIsSelected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isSelected(option)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionWithValueIsDeselected(String value, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        for (WebElement option : options) {
            if (valueEquals(value, option) && isDeselected(option)) {
                return true;
            }
        }
        return false;
    }

    public static void assertHasOptionWithValue(String value, WebElement webElement) {
        if (hasNotOptionWithValue(value, webElement)) {
            throw new WebAssertionError("Element has no option with value " + quote(value.trim()), webElement);
        }
    }

    public static void assertHasNotOptionWithValue(String value, WebElement webElement) {
        if (hasOptionWithValue(value, webElement)) {
            throw new WebAssertionError("Element has option with value " + quote(value.trim()) + " when it shouldn't", webElement);
        }
    }

    public static void assertOptionWithValueIsEnabled(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsDisabled(value, webElement)) {
            throw new WebAssertionError("Option with value " + quote(value.trim()) + " is not enabled", webElement);
        }
    }

    public static void assertOptionWithValueIsDisabled(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsEnabled(value, webElement)) {
            throw new WebAssertionError("Option with value " + quote(value.trim()) + " is not disabled", webElement);
        }
    }

    public static void assertOptionWithValueIsSelected(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsDeselected(value, webElement)) {
            throw new WebAssertionError("Option with value " + quote(value.trim()) + " is not selected", webElement);
        }
    }

    public static void assertOptionWithValueIsDeselected(String value, WebElement webElement) {
        assertHasOptionWithValue(value, webElement);
        if (optionWithValueIsSelected(value, webElement)) {
            throw new WebAssertionError("Option with value " + quote(value.trim()) + " is not deselected", webElement);
        }
    }



    /* Option Index */
    public static boolean hasOptionWithIndex(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return options.get(index) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean hasNotOptionWithIndex(int index, WebElement webElement) {
        return !hasOptionWithIndex(index, webElement);
    }

    public static boolean optionWithIndexIsEnabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isEnabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean optionWithIndexIsDisabled(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDisabled(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean optionWithIndexIsSelected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isSelected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean optionWithIndexIsDeselected(int index, WebElement webElement) {
        List<WebElement> options = new Select(webElement).getOptions();
        try {
            return isDeselected(options.get(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static void assertHasOptionWithIndex(int index, WebElement webElement) {
        if (hasNotOptionWithIndex(index, webElement)) {
            throw new WebAssertionError("Element  has no option with index " + quote(index), webElement);
        }
    }

    public static void assertHasNotOptionWithIndex(int index, WebElement webElement) {
        if (hasOptionWithIndex(index, webElement)) {
            throw new WebAssertionError("Element has option with index " + quote(index) + " when it shouldn't", webElement);
        }
    }

    public static void assertOptionWithIndexIsEnabled(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsDisabled(index, webElement)) {
            throw new WebAssertionError("Option with index " + quote(index) + " is not enabled", webElement);
        }
    }

    public static void assertOptionWithIndexIsDisabled(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsEnabled(index, webElement)) {
            throw new WebAssertionError("Option with index " + quote(index) + " is not disabled", webElement);
        }
    }

    public static void assertOptionWithIndexIsSelected(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsDeselected(index, webElement)) {
            throw new WebAssertionError("Option with index " + quote(index) + " is not selected", webElement);
        }
    }

    public static void assertOptionWithIndexIsDeselected(int index, WebElement webElement) {
        assertHasOptionWithIndex(index, webElement);
        if (optionWithIndexIsSelected(index, webElement)) {
            throw new WebAssertionError("Option with index " + quote(index) + " is not deselected", webElement);
        }
    }



    /* Hamcrest */
    public static <T extends Object> boolean is(T actual, Matcher<? super T> matcher) {
        try {
            assertThat(actual, matcher);
            return true;
        } catch (AssertionError ae) {
            return false;
        }
    }

    public static <T extends Object> void assertThat(T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat(actual, matcher);
    }

    public static <T extends Object> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat(reason, actual, matcher);
    }
}
