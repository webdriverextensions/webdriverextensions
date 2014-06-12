package com.github.webdriverextensions.junitrunner;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import com.github.webdriverextensions.WebDriverExtensionsContext;
import com.github.webdriverextensions.internal.WebDriverExtensionException;
import com.github.webdriverextensions.internal.junitrunner.AnnotationUtils;
import com.github.webdriverextensions.internal.junitrunner.DriverPathLoader;
import com.github.webdriverextensions.internal.utils.InstanceUtils;
import com.github.webdriverextensions.internal.utils.OsUtils;
import static com.github.webdriverextensions.internal.utils.StringUtils.quote;
import static com.github.webdriverextensions.internal.utils.WebDriverUtils.addCapabilities;
import static com.github.webdriverextensions.internal.utils.WebDriverUtils.convertToJsonString;
import static com.github.webdriverextensions.internal.utils.WebDriverUtils.removeCapabilities;
import com.github.webdriverextensions.junitrunner.annotations.Android;
import com.github.webdriverextensions.junitrunner.annotations.Browser;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.junitrunner.annotations.DriverPaths;
import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.HtmlUnit;
import com.github.webdriverextensions.junitrunner.annotations.IPad;
import com.github.webdriverextensions.junitrunner.annotations.IPhone;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreAndroid;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreBrowser;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreChrome;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreFirefox;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreHtmlUnit;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreIPad;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreIPhone;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreInternetExplorer;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreOpera;
import com.github.webdriverextensions.junitrunner.annotations.IgnorePhantomJS;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreSafari;
import com.github.webdriverextensions.junitrunner.annotations.InternetExplorer;
import com.github.webdriverextensions.junitrunner.annotations.Opera;
import com.github.webdriverextensions.junitrunner.annotations.PhantomJS;
import com.github.webdriverextensions.junitrunner.annotations.Safari;
import com.google.gson.Gson;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM;
import static org.openqa.selenium.remote.CapabilityType.VERSION;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;

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
    private static List<Class> supportedBrowserAnnotations = Arrays.asList(new Class[]{
        Android.class,
        Chrome.class,
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
    private static List<Class> supportedIgnoreBrowserAnnotations = Arrays.asList(new Class[]{
        IgnoreAndroid.class,
        IgnoreChrome.class,
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

    public WebDriverRunner(Class<?> klass) throws InitializationError {
        super(klass);
        DriverPathLoader.loadDriverPaths(getTestClass().getJavaClass().getAnnotation(DriverPaths.class));
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        List<FrameworkMethod> testAnnotatedMethods = getTestClass().getAnnotatedMethods(Test.class);
        List<FrameworkMethod> testMethods = new ArrayList<FrameworkMethod>();
        for (FrameworkMethod testAnnotatedMethod : testAnnotatedMethods) {
            TestMethodContext testMethodContext = new TestMethodContext().addBrowsersFromClassAnnotations(getTestClass()).addBrowsersFromMethodAnnotations(testAnnotatedMethod);
            if (!testMethodContext.getBrowsers().isEmpty()) {
                for (BrowserConfiguration browser : testMethodContext.getBrowsers()) {
                    testMethods.add(new WebDriverFrameworkMethod(browser, testAnnotatedMethod));
                }
            } else {
                // Not a WebDriver Annotated test, treat as normal test
                testMethods.add(testAnnotatedMethod);
            }
        }
        return testMethods;
    }

    @Override
    protected Object createTest() throws Exception {
        Object test = super.createTest();
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebDriverExtensionsContext.getDriver()), test);
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

            if (method.getAnnotation(Ignore.class) != null) {
                log.trace("Skipping test {}. Test is annotated to be ignored with @Ignore annotation", testName);
                notifier.fireTestIgnored(description);
            } else if (testMethodContext.isBrowserIgnored(browser)) {
                log.trace("Skipping test {}. Test is annotated to be ignored, ignore annotations = {}.", testName,
                        testMethodContext.ignoreBrowsers.toString());
                notifier.fireTestIgnored(description);
            } else if (BrowserType.IE.equalsIgnoreCase(browser.getBrowserName()) && !OsUtils.isWindows()
                    || (BrowserType.IEXPLORE.equalsIgnoreCase(browser.getBrowserName()) && !OsUtils.isWindows())) {
                log.trace("Skipping test {}. Internet Explorer is only supported on Windows platforms.", testName);
                notifier.fireTestIgnored(description);
            } else if (BrowserType.SAFARI.equalsIgnoreCase(browser.getBrowserName()) && (!OsUtils.isWindows() && !OsUtils.isMac())) {
                log.trace("Skipping test {}. Safari is only supported on Windows and Mac platforms.", testName);
                notifier.fireTestIgnored(description);
            } else {
                try {
                    try {
                        WebDriver driver = browser.createDriver();
                        BrowserConfiguration driverBrowser = new BrowserConfiguration(driver);
                        if (testMethodContext.isBrowserIgnored(driverBrowser)) {
                            driver.quit();
                            log.trace("Skipping test {}. Test is annotated to be ignored, ignore annotations = {}.", testName,
                                    testMethodContext.ignoreBrowsers.toString());
                            notifier.fireTestIgnored(description);
                            return;
                        }
                        WebDriverExtensionsContext.setDriver(driver);
                    } catch (BrowserNotSupported ex) {
                        log.trace("Skipping test {}. {} has no driver for running tests in browser {}.", testName,
                                WebDriverRunner.class.getSimpleName(), quote(browser.getBrowserName()));
                        notifier.fireTestIgnored(description);
                        return;
                    }
                    log.info("Running test {}", testName);
                    log.trace("{} threadId = {}", testName, Thread.currentThread().getId());
                    log.trace("Desired Capabilities");
                    log.trace("browserName = " + browser.getBrowserName());
                    log.trace("version = " + browser.getVersion());
                    log.trace("platform = " + browser.getPlatform());
                    log.trace("desiredCapabilities = " + convertToJsonString(browser.getDesiredCapabilities()));
                    log.trace("Capabilities");
                    Capabilities capabilities = ((HasCapabilities) WebDriverExtensionsContext.getDriver()).getCapabilities();
                    log.trace("browserName = " + capabilities.getBrowserName());
                    log.trace("version = " + capabilities.getVersion());
                    log.trace("platform = " + capabilities.getCapability(PLATFORM));
                    log.trace("capabilities = " + convertToJsonString(removeCapabilities(capabilities, BROWSER_NAME, VERSION, PLATFORM)));
                } catch (Exception ex) {
                    notifier.fireTestFailure(new Failure(description, ex));
                    return;
                }
                runLeaf(methodBlock(method), description, notifier);
                WebDriverExtensionsContext.getDriver().quit();
            }
        } else {
            // Not a Selenium Grid Anotated test, treat as normal test
            super.runChild(method, notifier);
        }
    }

    private class TestMethodContext {

        List<BrowserConfiguration> browsers = new ArrayList<BrowserConfiguration>();
        List<BrowserConfiguration> ignoreBrowsers = new ArrayList<BrowserConfiguration>();

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

            if (BrowserType.SAFARI.equalsIgnoreCase(browserName)) {
                return new SafariDriver(desiredCapabilities);
            }

            throw new BrowserNotSupported();
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
