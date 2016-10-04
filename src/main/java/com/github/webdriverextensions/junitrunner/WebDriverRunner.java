package com.github.webdriverextensions.junitrunner;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import com.github.webdriverextensions.WebDriverExtensionsContext;
import com.github.webdriverextensions.internal.WebDriverExtensionException;
import com.github.webdriverextensions.internal.junitrunner.AnnotationUtils;
import com.github.webdriverextensions.internal.junitrunner.DriverPathLoader;
import com.github.webdriverextensions.internal.junitrunner.ScreenshotsPathLoader;
import com.github.webdriverextensions.internal.junitrunner.TakeScreenshotOnFailureRunListener;
import com.github.webdriverextensions.internal.utils.InstanceUtils;
import com.github.webdriverextensions.internal.utils.OsUtils;
import com.github.webdriverextensions.internal.utils.PropertyUtils;
import com.github.webdriverextensions.junitrunner.annotations.*;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.TestClass;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;

import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.github.webdriverextensions.internal.utils.StringUtils.quote;
import static com.github.webdriverextensions.internal.utils.WebDriverUtils.*;
import static org.openqa.selenium.remote.CapabilityType.*;

public class WebDriverRunner extends BlockJUnit4ClassRunner {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WebDriverRunner.class);

    public static class WebDriverFrameworkMethod extends FrameworkMethod {

        final private BrowserConfiguration browser;

        public WebDriverFrameworkMethod(BrowserConfiguration browser, FrameworkMethod method) {
            super(method.getMethod());
            this.browser = browser;
        }

        @Override
        public String getName() {
            return String.format("%s%s", super.getName(), browser.toString());
        }

        private BrowserConfiguration getBrowser() {
            return browser;
        }
    }
    private static final List<Class> supportedBrowserAnnotations = Arrays.asList(new Class[]{
        Android.class,
        Chrome.class,
        Edge.class,
        Firefox.class,
        HtmlUnit.class,
        IPhone.class,
        IPad.class,
        InternetExplorer.class,
        Opera.class,
        PhantomJS.class,
        Safari.class,
        Browser.class
    });
    private static final List<Class> supportedIgnoreBrowserAnnotations = Arrays.asList(new Class[]{
        IgnoreAndroid.class,
        IgnoreChrome.class,
        IgnoreEdge.class,
        IgnoreFirefox.class,
        IgnoreHtmlUnit.class,
        IgnoreIPhone.class,
        IgnoreIPad.class,
        IgnoreInternetExplorer.class,
        IgnoreOpera.class,
        IgnorePhantomJS.class,
        IgnoreSafari.class,
        IgnoreBrowser.class
    });

    private final Object childrenLock = new Object();
    private volatile Collection<FrameworkMethod> filteredTestAnnotatedMethods = null; // Guarded by childrenLock

    private Collection<FrameworkMethod> getFilteredTestAnnotatedMethods() {
        if (filteredTestAnnotatedMethods == null) {
            synchronized (childrenLock) {
                if (filteredTestAnnotatedMethods == null) {
                    filteredTestAnnotatedMethods = Collections.unmodifiableCollection(getTestAnnotatedMethods());
                }
            }
        }
        return filteredTestAnnotatedMethods;
    }

    public WebDriverRunner(Class<?> klass) throws InitializationError {
        super(klass);
        DriverPathLoader.loadDriverPaths(getTestClass().getJavaClass().getAnnotation(DriverPaths.class));
        ScreenshotsPathLoader.loadScreenshotsPath(getTestClass().getJavaClass().getAnnotation(ScreenshotsPath.class));
    }

    /**
     * @deprecated retained for backward compatibility
     */
    @Deprecated
    @Override
    protected void validateInstanceMethods(List<Throwable> errors) {
        // Instead of calling super.validateInstanceMethods(errors)
        // make the same calls except skip adding the "No runnable methods" error
        validatePublicVoidNoArgMethods(After.class, false, errors);
        validatePublicVoidNoArgMethods(Before.class, false, errors);
        validateTestMethods(errors);
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        List<FrameworkMethod> testMethods = new ArrayList<>();
        for (FrameworkMethod testAnnotatedMethod : getFilteredTestAnnotatedMethods()) {
            TestMethodContext testMethodContext = new TestMethodContext().addBrowsersFromClassAnnotations(getTestClass()).addBrowsersFromMethodAnnotations(testAnnotatedMethod);
            if (!testMethodContext.getBrowsers().isEmpty()) {
                for (BrowserConfiguration browser : testMethodContext.getBrowsers()) {
                    testMethods.add(new WebDriverFrameworkMethod(browser, testAnnotatedMethod));
                }
            } else {
                // Not a WebDriverRunner Annotated test, treat as normal test
                testMethods.add(testAnnotatedMethod);
            }
        }
        return testMethods;
    }

    protected List<FrameworkMethod> getTestAnnotatedMethods() {
        List<FrameworkMethod> testAnnotatedMethods = getTestClass().getAnnotatedMethods(Test.class);
        List<FrameworkMethod> testMethods = new ArrayList<>();
        for (FrameworkMethod testAnnotatedMethod : testAnnotatedMethods) {
            testMethods.add(testAnnotatedMethod);
        }
        return testMethods;
    }

    @Override
    protected Object createTest() throws Exception {
        Object test = super.createTest();
        try {
            PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebDriverExtensionsContext.getDriver()), test);
        } catch (WebDriverExtensionException e) {
            // Swallow "WebDriverExtensionsContext is not set" exceptions to allow running normal JUnit tests
        }
        return test;
    }

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        if (method instanceof WebDriverFrameworkMethod) {
            TestMethodContext testMethodContext = new TestMethodContext().addBrowsersFromClassAnnotations(getTestClass()).addBrowsersFromMethodAnnotations(method);
            BrowserConfiguration browser = ((WebDriverFrameworkMethod) method).getBrowser();
            Description description = describeChild(method);

            // Create test name
            String className = getTestClass().getJavaClass().getSimpleName();
            String methodName = method.getName();
            String testName = String.format("%s.%s", className, methodName);
            boolean hasRemoteAddress = PropertyUtils.propertyExists("webdriverextensions.remoteaddress")
                    || ((RemoteAddress) getTestClass().getJavaClass().getAnnotation(RemoteAddress.class)) != null;

            if (method.getAnnotation(Ignore.class) != null) {
                log.info("Skipping test {} since Test is annotated to be ignored with @Ignore annotation", testName);
                notifier.fireTestIgnored(description);
            } else if (testMethodContext.isBrowserIgnored(browser)) {
                log.info("Skipping test {} since Test is annotated to ignore browser {}", testName,
                        testMethodContext.ignoreBrowsers.toString());
                notifier.fireTestIgnored(description);
            } else if (!hasRemoteAddress && BrowserType.IE.equalsIgnoreCase(browser.getBrowserName()) && !OsUtils.isWindows()
                    || (BrowserType.IEXPLORE.equalsIgnoreCase(browser.getBrowserName()) && !OsUtils.isWindows())) {
                log.info("Skipping test {} since Internet Explorer only runs on Windows platform", testName);
                notifier.fireTestIgnored(description);
            } else if (!hasRemoteAddress && BrowserType.EDGE.equalsIgnoreCase(browser.getBrowserName()) && !OsUtils.isWindows()) {
                log.info("Skipping test {} since Edge only runs on Windows platform", testName);
                notifier.fireTestIgnored(description);
            } else if (!hasRemoteAddress && BrowserType.SAFARI.equalsIgnoreCase(browser.getBrowserName()) && (!OsUtils.isWindows() && !OsUtils.isMac())) {
                log.info("Skipping test {} since Safari only runs on Windows and Mac platform", testName);
                notifier.fireTestIgnored(description);
            } else if (!hasRemoteAddress && !OsUtils.isCurrentPlatform(browser.platform)) {
                log.info("Skipping test {} since current platform is not " + browser.platform, testName);
                notifier.fireTestIgnored(description);
            } else {
                WebDriver driver;
                try {
                    if (hasRemoteAddress) {
                        String remoteAddress;
                        if (PropertyUtils.propertyExists("webdriverextensions.remoteaddress")) {
                            remoteAddress = System.getProperty("webdriverextensions.remoteaddress");
                        } else {
                            remoteAddress = ((RemoteAddress) getTestClass().getJavaClass().getAnnotation(RemoteAddress.class)).value();
                        }
                        driver = browser.createDriver(new URL(remoteAddress));
                        WebDriverExtensionsContext.setDriver(driver);
                    } else {
                        try {
                            driver = browser.createDriver();
                            BrowserConfiguration driverBrowser = new BrowserConfiguration(driver);
                            if (testMethodContext.isBrowserIgnored(driverBrowser)) {
                                driver.quit();
                                WebDriverExtensionsContext.removeDriver();
                                log.info("Skipping test {} since Test is annotated to ignore browser {}", testName,
                                        testMethodContext.ignoreBrowsers.toString());
                                notifier.fireTestIgnored(description);
                                return;
                            }
                            WebDriverExtensionsContext.setDriver(driver);
                        } catch (BrowserNotSupported ex) {
                            log.info("Skipping test {} since browser {} is not supported to run locally", testName, quote(browser.getBrowserName()));
                            notifier.fireTestIgnored(description);
                            return;
                        }
                    }

                    if (hasImplicitlyWaitAnnotation(getTestClass(), method)) {
                        Long implicitlyWaitAnnotationValue = getValueFromImplicitlyWaitAnnotation(getTestClass(), method);
                        TimeUnit implicitlyWaitAnnotationUnit = getUnitFromImplicitlyWaitAnnotation(getTestClass(), method);
                        driver.manage().timeouts().implicitlyWait(implicitlyWaitAnnotationValue, implicitlyWaitAnnotationUnit);
                    }

                    log.info("Running test {}", testName);
                    log.trace("{} threadId = {}", testName, Thread.currentThread().getId());
                    log.trace("Desired Capabilities");
                    log.trace("browserName = " + browser.getBrowserName());
                    log.trace("version = " + browser.getVersion());
                    log.trace("platform = " + browser.getPlatform());
                    log.trace("desiredCapabilities = " + convertToJsonString(browser.getDesiredCapabilities()));
                    log.trace("Capabilities");
                    Capabilities capabilities = ((HasCapabilities) driver).getCapabilities();
                    log.trace("browserName = " + capabilities.getBrowserName());
                    log.trace("version = " + capabilities.getVersion());
                    log.trace("platform = " + capabilities.getCapability(PLATFORM));
                    log.trace("capabilities = " + convertToJsonString(removeCapabilities(capabilities, BROWSER_NAME, VERSION, PLATFORM)));
                } catch (Exception ex) {
                    notifier.fireTestFailure(new Failure(description, ex));
                    return;
                }

                if (hasScreenshotPathAnnotation(getTestClass())) {
                    DriverPathLoader.loadDriverPaths(getTestClass().getJavaClass().getAnnotation(DriverPaths.class));
                }

                boolean hasTakeScreenshotOnFailureAnnotation = hasTakeScreenshotOnFailureAnnotation(getTestClass());
                TakeScreenshotOnFailureRunListener screenshotRunListener = null;

                if (hasTakeScreenshotOnFailureAnnotation) {
                    String fileName = className + "." + methodName + "-" + getCurrentDateAndTime();
                    screenshotRunListener = new TakeScreenshotOnFailureRunListener(log, fileName);
                    notifier.addListener(screenshotRunListener);
                }

                runLeaf(methodBlock(method), description, notifier);
                driver.quit();

                if (hasTakeScreenshotOnFailureAnnotation)
                    notifier.removeListener(screenshotRunListener);

                WebDriverExtensionsContext.removeDriver();
            }
        } else {
            // Not a Selenium Grid Annotated test, treat as normal test
            super.runChild(method, notifier);
        }
    }

    @Override
    protected Description describeChild(FrameworkMethod method) {
        return Description.createTestDescription(getTestClass().getJavaClass(),
                testName(method), method.getAnnotations());
    }

    private class TestMethodContext {

        List<BrowserConfiguration> browsers = new ArrayList<>();
        List<BrowserConfiguration> ignoreBrowsers = new ArrayList<>();

        public List<BrowserConfiguration> getBrowsers() {
            return browsers;
        }

        public List<BrowserConfiguration> getIgnoreBrowsers() {
            return ignoreBrowsers;
        }

        public TestMethodContext addBrowsersFromClassAnnotations(TestClass clazz) {
            addBrowsersFromAnnotations(clazz.getAnnotations());
            return this;
        }

        public TestMethodContext addBrowsersFromMethodAnnotations(FrameworkMethod method) {
            addBrowsersFromAnnotations(method.getAnnotations());
            return this;
        }

        public boolean isBrowserIgnored(BrowserConfiguration browser) {
            for (BrowserConfiguration ignoreBrowser : ignoreBrowsers) {
                if (browser.matches(ignoreBrowser)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }

        private TestMethodContext addBrowsersFromAnnotations(Annotation[] annotations) {
            for (Annotation annotation : annotations) {
                if (supportedBrowserAnnotations.contains(annotation.annotationType())) {
                    addBrowserFromAnnotation(annotation);
                } else if (supportedIgnoreBrowserAnnotations.contains(annotation.annotationType())) {
                    addIgnoreBrowserFromAnnotation(annotation);
                } else if (annotation.annotationType().equals(com.github.webdriverextensions.junitrunner.annotations.Browsers.class)) {
                    com.github.webdriverextensions.junitrunner.annotations.Browsers browsersAnnotation = (com.github.webdriverextensions.junitrunner.annotations.Browsers) annotation;
                    addBrowsersFromAnnotations(browsersAnnotation.android());
                    addBrowsersFromAnnotations(browsersAnnotation.chrome());
                    addBrowsersFromAnnotations(browsersAnnotation.edge());
                    addBrowsersFromAnnotations(browsersAnnotation.firefox());
                    addBrowsersFromAnnotations(browsersAnnotation.htmlUnit());
                    addBrowsersFromAnnotations(browsersAnnotation.iPhone());
                    addBrowsersFromAnnotations(browsersAnnotation.iPad());
                    addBrowsersFromAnnotations(browsersAnnotation.internetExplorer());
                    addBrowsersFromAnnotations(browsersAnnotation.opera());
                    addBrowsersFromAnnotations(browsersAnnotation.phantomJS());
                    addBrowsersFromAnnotations(browsersAnnotation.safari());
                    addBrowsersFromAnnotations(browsersAnnotation.browser());
                } else if (annotation.annotationType().equals(com.github.webdriverextensions.junitrunner.annotations.IgnoreBrowsers.class)) {
                    com.github.webdriverextensions.junitrunner.annotations.IgnoreBrowsers ignoreBrowsersAnnotation = (com.github.webdriverextensions.junitrunner.annotations.IgnoreBrowsers) annotation;
                    addIgnoreBrowsersFromAnnotations(ignoreBrowsersAnnotation.android());
                    addIgnoreBrowsersFromAnnotations(ignoreBrowsersAnnotation.chrome());
                    addIgnoreBrowsersFromAnnotations(ignoreBrowsersAnnotation.edge());
                    addIgnoreBrowsersFromAnnotations(ignoreBrowsersAnnotation.firefox());
                    addIgnoreBrowsersFromAnnotations(ignoreBrowsersAnnotation.htmlUnit());
                    addIgnoreBrowsersFromAnnotations(ignoreBrowsersAnnotation.iPhone());
                    addIgnoreBrowsersFromAnnotations(ignoreBrowsersAnnotation.iPad());
                    addIgnoreBrowsersFromAnnotations(ignoreBrowsersAnnotation.internetExplorer());
                    addIgnoreBrowsersFromAnnotations(ignoreBrowsersAnnotation.opera());
                    addIgnoreBrowsersFromAnnotations(ignoreBrowsersAnnotation.phantomJS());
                    addIgnoreBrowsersFromAnnotations(ignoreBrowsersAnnotation.safari());
                    addIgnoreBrowsersFromAnnotations(ignoreBrowsersAnnotation.browser());
                }
            }
            return this;
        }

        private TestMethodContext addIgnoreBrowsersFromAnnotations(Annotation[] annotations) {
            for (Annotation annotation : annotations) {
                if (supportedIgnoreBrowserAnnotations.contains(annotation.annotationType())) {
                    addIgnoreBrowserFromAnnotation(annotation);
                }
            }
            return this;
        }

        private TestMethodContext addBrowserFromAnnotation(Annotation annotation) {
            browsers.add(new BrowserConfiguration(annotation));
            return this;
        }

        private TestMethodContext addIgnoreBrowserFromAnnotation(Annotation annotation) {
            ignoreBrowsers.add(new BrowserConfiguration(annotation));
            return this;
        }
    }

    @Override
    public void filter(Filter filter) throws NoTestsRemainException {
        synchronized (childrenLock) {
            List<FrameworkMethod> children = new ArrayList<>(getFilteredTestAnnotatedMethods());
            for (Iterator<FrameworkMethod> iter = children.iterator(); iter.hasNext(); ) {
                FrameworkMethod each = iter.next();
                if (filter.shouldRun(describeChild(each))) {
                    try {
                        filter.apply(each);
                    } catch (NoTestsRemainException e) {
                        iter.remove();
                    }
                } else {
                    iter.remove();
                }
            }
            filteredTestAnnotatedMethods = Collections.unmodifiableCollection(children);
            if (filteredTestAnnotatedMethods.isEmpty()) {
                throw new NoTestsRemainException();
            }
        }
    }

    public class BrowserConfiguration {

        private Annotation annotation;
        private String browserName;
        private String version;
        private String platform;
        private Capabilities desiredCapabilities;

        public BrowserConfiguration(WebDriver driver) {
            Capabilities capabilities = ((HasCapabilities) driver).getCapabilities();
            this.browserName = capabilities.getBrowserName();
            this.version = capabilities.getVersion();
            this.platform = capabilities.getPlatform().toString();
            this.desiredCapabilities = removeCapabilities(capabilities, BROWSER_NAME, VERSION, PLATFORM);
        }

        public BrowserConfiguration(Annotation annotation) {

            this.annotation = annotation;

            if (annotation.annotationType().equals(Android.class)
                    || annotation.annotationType().equals(IgnoreAndroid.class)) {
                browserName = BrowserType.ANDROID;
            } else if (annotation.annotationType().equals(Chrome.class)
                    || annotation.annotationType().equals(IgnoreChrome.class)) {
                browserName = BrowserType.CHROME;
            } else if (annotation.annotationType().equals(Edge.class)
                    || annotation.annotationType().equals(IgnoreEdge.class)) {
                browserName = BrowserType.EDGE;
            } else if (annotation.annotationType().equals(Firefox.class)
                    || annotation.annotationType().equals(IgnoreFirefox.class)) {
                browserName = BrowserType.FIREFOX;
            } else if (annotation.annotationType().equals(HtmlUnit.class)
                    || annotation.annotationType().equals(IgnoreHtmlUnit.class)) {
                browserName = BrowserType.HTMLUNIT;
            } else if (annotation.annotationType().equals(IPhone.class)
                    || annotation.annotationType().equals(IgnoreIPhone.class)) {
                browserName = BrowserType.IPHONE;
            } else if (annotation.annotationType().equals(IPad.class)
                    || annotation.annotationType().equals(IgnoreIPad.class)) {
                browserName = BrowserType.IPAD;
            } else if (annotation.annotationType().equals(InternetExplorer.class)
                    || annotation.annotationType().equals(IgnoreInternetExplorer.class)) {
                browserName = BrowserType.IE;
            } else if (annotation.annotationType().equals(Opera.class)
                    || annotation.annotationType().equals(IgnoreOpera.class)) {
                browserName = BrowserType.OPERA;
            } else if (annotation.annotationType().equals(PhantomJS.class)
                    || annotation.annotationType().equals(IgnorePhantomJS.class)) {
                browserName = BrowserType.PHANTOMJS;
            } else if (annotation.annotationType().equals(Safari.class)
                    || annotation.annotationType().equals(IgnoreSafari.class)) {
                browserName = BrowserType.SAFARI;
            } else if (annotation.annotationType().equals(Browser.class)
                    || annotation.annotationType().equals(IgnoreBrowser.class)) {
                browserName = (String) AnnotationUtils.getValue(annotation, "browserName");
            }

            version = (String) AnnotationUtils.getValue(annotation, "version");

            if (annotation.annotationType().equals(Browser.class)
                    || annotation.annotationType().equals(IgnoreBrowser.class)) {
                platform = (String) AnnotationUtils.getValue(annotation, "platform");
            } else {
                platform = ((Platform) AnnotationUtils.getValue(annotation, "platform")).toString();
            }

            Class desiredCapabilitiesClass = (Class) AnnotationUtils.getValue(annotation, "desiredCapabilitiesClass");
            if (desiredCapabilitiesClass != null) {
                desiredCapabilities = InstanceUtils.newInstance(desiredCapabilitiesClass, DesiredCapabilities.class);
            }

            String desiredCapabilitiesJson = (String) AnnotationUtils.getValue(annotation, "desiredCapabilities");
            if (desiredCapabilitiesJson != null) {
                Map<String, Object> desiredCapabilitiesJsonMap = new Gson().fromJson(desiredCapabilitiesJson, Map.class);
                desiredCapabilities = addCapabilities(desiredCapabilities, desiredCapabilitiesJsonMap);
            }

        }

        public String getBrowserName() {
            return browserName;
        }

        public String getVersion() {
            return version;
        }

        public String getPlatform() {
            return platform;
        }

        public Capabilities getDesiredCapabilities() {
            return desiredCapabilities;
        }

        private WebDriver createDriver() throws Exception {
            if (BrowserType.CHROME.equalsIgnoreCase(browserName)
                    || BrowserType.GOOGLECHROME.equalsIgnoreCase(browserName)) {
                return new ChromeDriver(desiredCapabilities);
            }

            if (BrowserType.FIREFOX.equalsIgnoreCase(browserName)) {
                return new FirefoxDriver(desiredCapabilities);
            }

            if (BrowserType.HTMLUNIT.equalsIgnoreCase(browserName)) {
                return new HtmlUnitDriver(desiredCapabilities);
            }

            if (BrowserType.IE.equalsIgnoreCase(browserName)
                    || BrowserType.IEXPLORE.equalsIgnoreCase(browserName)) {
                return new InternetExplorerDriver(desiredCapabilities);
            }

            if (BrowserType.EDGE.equalsIgnoreCase(browserName)) {
                return new EdgeDriver(desiredCapabilities);
            }

            if (BrowserType.SAFARI.equalsIgnoreCase(browserName)) {
                return new SafariDriver(desiredCapabilities);
            }

            if (BrowserType.PHANTOMJS.equalsIgnoreCase(browserName)) {
                DesiredCapabilities newDesiredCapabilities = new DesiredCapabilities(desiredCapabilities);
                newDesiredCapabilities.setJavascriptEnabled(true);
                newDesiredCapabilities.setCapability("takesScreenshot", true);
                return new PhantomJSDriver(newDesiredCapabilities);
            }

            throw new BrowserNotSupported();
        }

        private WebDriver createDriver(URL url) throws Exception {
            DesiredCapabilities finalDesiredCapabilities = new DesiredCapabilities(desiredCapabilities);
            finalDesiredCapabilities.setBrowserName(browserName);
            finalDesiredCapabilities.setVersion(version);
            finalDesiredCapabilities.setCapability(PLATFORM, platform);
            return new RemoteWebDriver(
                    url,
                    finalDesiredCapabilities);
        }

        @Override
        public String toString() {
            if (annotation == null) {
                return "";
            }
            String annotationAsString = "@" + annotation.annotationType().getSimpleName() + "("
                    + (isBrowserNameProvided() && (annotation.annotationType().equals(Browser.class)
                    || annotation.annotationType().equals(IgnoreBrowser.class)) ? "browserName=" + quote(browserName) + ", " : "")
                    + (isVersionProvided() ? "version=" + quote(version) + ", " : "")
                    + (isPlatformProvided() ? "platform=" + quote(platform) + ", " : "")
                    + ")";
            annotationAsString = annotationAsString.replaceFirst(", \\)$", ")"); // Replace last comaseparation
            annotationAsString = annotationAsString.replaceFirst("\\(\\)$", ""); // Replace paranthesis if it's empty
            return annotationAsString;
        }

        private boolean matches(BrowserConfiguration browser) {
            if (browser.isBrowserNameProvided()
                    && !browser.getBrowserName().equalsIgnoreCase(browserName)) {
                return false;
            }
            if (browser.isVersionProvided()
                    && !browser.getVersion().equalsIgnoreCase(version)) {
                return false;
            }
            if (browser.isPlatformProvided()
                    && !browser.getPlatform().equalsIgnoreCase(platform)) {
                return false;
            }
            return true;
        }

        private boolean isBrowserNameProvided() {
            return !StringUtils.isBlank(browserName);
        }

        private boolean isVersionProvided() {
            return !StringUtils.isBlank(version);
        }

        private boolean isPlatformProvided() {
            return !Platform.ANY.toString().equalsIgnoreCase(platform);
        }
    }

    public class BrowserNotSupported extends WebDriverExtensionException {
    }
}
