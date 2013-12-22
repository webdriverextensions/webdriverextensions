package org.webdriverextensions.junitrunner;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.webdriverextensions.internal.junitrunner.AnnotationUtils;
import org.webdriverextensions.ThreadDriver;
import org.webdriverextensions.junitrunner.annotations.Android;
import org.webdriverextensions.junitrunner.annotations.Chrome;
import org.webdriverextensions.junitrunner.annotations.Browser;
import org.webdriverextensions.junitrunner.annotations.Firefox;
import org.webdriverextensions.junitrunner.annotations.HtmlUnit;
import org.webdriverextensions.junitrunner.annotations.IPad;
import org.webdriverextensions.junitrunner.annotations.IPhone;
import org.webdriverextensions.junitrunner.annotations.IgnoreAndroid;
import org.webdriverextensions.junitrunner.annotations.IgnoreChrome;
import org.webdriverextensions.junitrunner.annotations.IgnoreBrowser;
import org.webdriverextensions.junitrunner.annotations.IgnoreFirefox;
import org.webdriverextensions.junitrunner.annotations.IgnoreHtmlUnit;
import org.webdriverextensions.junitrunner.annotations.IgnoreIPad;
import org.webdriverextensions.junitrunner.annotations.IgnoreIPhone;
import org.webdriverextensions.junitrunner.annotations.IgnoreInternetExplorer;
import org.webdriverextensions.junitrunner.annotations.IgnoreOpera;
import org.webdriverextensions.junitrunner.annotations.IgnorePhantomJS;
import org.webdriverextensions.junitrunner.annotations.IgnoreSafari;
import org.webdriverextensions.junitrunner.annotations.InternetExplorer;
import org.webdriverextensions.junitrunner.annotations.Opera;
import org.webdriverextensions.junitrunner.annotations.PhantomJS;
import org.webdriverextensions.junitrunner.annotations.Safari;
import org.webdriverextensions.internal.WebDriverExtensionException;
import org.webdriverextensions.internal.junitrunner.DriverPathLoader;
import org.webdriverextensions.internal.utils.OsUtils;
import org.webdriverextensions.junitrunner.annotations.BooleanOption;
import org.webdriverextensions.junitrunner.annotations.DriverPaths;
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
import org.junit.runners.model.TestClass;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverRunner extends BlockJUnit4ClassRunner {

    private static class WebDriverFrameworkMethod extends FrameworkMethod {

        final private BrowserConfiguration browser;

        public WebDriverFrameworkMethod(BrowserConfiguration browser, FrameworkMethod method) {
            super(method.getMethod());
            this.browser = browser;
        }

        @Override
        public String getName() {
            return String.format("%s%s", super.getName(), browser.getTestDescriptionSuffix());
        }

        private BrowserConfiguration getBrowser() {
            return browser;
        }
    }
    private static List<Class> supportedBrowserAnnotations = Arrays.asList(new Class[]{
        Chrome.class,
        Firefox.class,
        HtmlUnit.class,
        InternetExplorer.class,
        Safari.class,
        Browser.class
    });
    private static List<Class> supportedIgnoreBrowserAnnotations = Arrays.asList(new Class[]{
        IgnoreChrome.class,
        IgnoreFirefox.class,
        IgnoreHtmlUnit.class,
        IgnoreInternetExplorer.class,
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
            BrowserConfigurations browserConfigurations = new BrowserConfigurations().addConfigurationsFromClassAnnotations(getTestClass()).addConfigurationsFromMethodAnnotations(testAnnotatedMethod);
            if (!browserConfigurations.getBrowsers().isEmpty()) {
                for (BrowserConfiguration browser : browserConfigurations.getBrowsers()) {
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
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        if (method instanceof WebDriverFrameworkMethod) {
            BrowserConfigurations browserConfigurations = new BrowserConfigurations().addConfigurationsFromClassAnnotations(getTestClass()).addConfigurationsFromMethodAnnotations(method);
            BrowserConfiguration browserConfiguration = ((WebDriverFrameworkMethod) method).getBrowser();
            Description description = describeChild(method);
            if (method.getAnnotation(Ignore.class) != null
                    || browserConfigurations.isBrowserIgnored(browserConfiguration)
                    || (BrowserType.IE.equals(browserConfiguration.getBrowserName()) && !OsUtils.isWindows())
                    || (BrowserType.IEXPLORE.equals(browserConfiguration.getBrowserName()) && !OsUtils.isWindows())
                    || (BrowserType.SAFARI.equals(browserConfiguration.getBrowserName()) && (!OsUtils.isWindows() && !OsUtils.isMac()))) {
                notifier.fireTestIgnored(description);
            } else {
                long threadId = Thread.currentThread().getId();
                try {
                    ThreadDriver.setDriver(browserConfiguration.createDriver());
                } catch (Exception ex) {
                    notifier.fireTestFailure(new Failure(description, ex));
                    return;
                }
                runLeaf(methodBlock(method), description, notifier);
                ThreadDriver.getDriver().quit();
            }
        } else {
            // Not a Selenium Grid Anotated test, treat as normal test
            super.runChild(method, notifier);
        }
    }

    private class BrowserConfigurations {

        Set<BrowserConfiguration> browsers = new LinkedHashSet<BrowserConfiguration>();
        Set<BrowserConfiguration> ignoreBrowsers = new LinkedHashSet<BrowserConfiguration>();

        public Set<BrowserConfiguration> getBrowsers() {
            return browsers;
        }

        public Set<BrowserConfiguration> getIgnoreBrowsers() {
            return ignoreBrowsers;
        }

        public BrowserConfigurations addConfigurationsFromClassAnnotations(TestClass clazz) {
            addBrowsersFromAnnotations(clazz.getAnnotations());
            return this;
        }

        public BrowserConfigurations addConfigurationsFromMethodAnnotations(FrameworkMethod method) {
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

        private BrowserConfigurations addBrowsersFromAnnotations(Annotation[] annotations) {
            for (Annotation annotation : annotations) {
                if (supportedBrowserAnnotations.contains(annotation.annotationType())) {
                    addBrowserFromAnnotation(annotation);
                } else if (supportedIgnoreBrowserAnnotations.contains(annotation.annotationType())) {
                    addIgnoreBrowserFromAnnotation(annotation);
                } else if (annotation.annotationType().equals(org.webdriverextensions.junitrunner.annotations.Browsers.class)) {
                    org.webdriverextensions.junitrunner.annotations.Browsers browsersAnnotation = (org.webdriverextensions.junitrunner.annotations.Browsers) annotation;
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
                } else if (annotation.annotationType().equals(org.webdriverextensions.junitrunner.annotations.IgnoreBrowsers.class)) {
                    org.webdriverextensions.junitrunner.annotations.IgnoreBrowsers ignoreBrowsersAnnotation = (org.webdriverextensions.junitrunner.annotations.IgnoreBrowsers) annotation;
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

        private BrowserConfigurations addIgnoreBrowsersFromAnnotations(Annotation[] annotations) {
            for (Annotation annotation : annotations) {
                if (supportedBrowserAnnotations.contains(annotation.annotationType())) {
                    addIgnoreBrowserFromAnnotation(annotation);
                }
            }
            return this;
        }

        private BrowserConfigurations addBrowserFromAnnotation(Annotation annotation) {
            browsers.add(new BrowserConfiguration(annotation));
            return this;
        }

        private BrowserConfigurations addIgnoreBrowserFromAnnotation(Annotation annotation) {
            ignoreBrowsers.add(new BrowserConfiguration(annotation));
            return this;
        }
    }

    public class BrowserConfiguration {

        private String browserName;
        private String version;
        private Platform platform;
        private String platformName;
        private BooleanOption javascriptEnabled;
        private Map<String, Object> desiredCapabilities;

        public BrowserConfiguration(Annotation annotation) {

            if (annotation.annotationType().equals(Android.class)
                    || annotation.annotationType().equals(IgnoreAndroid.class)) {
                this.browserName = BrowserType.ANDROID;
            } else if (annotation.annotationType().equals(Chrome.class)
                    || annotation.annotationType().equals(IgnoreChrome.class)) {
                this.browserName = BrowserType.CHROME;
            } else if (annotation.annotationType().equals(Firefox.class)
                    || annotation.annotationType().equals(IgnoreFirefox.class)) {
                this.browserName = BrowserType.FIREFOX;
            } else if (annotation.annotationType().equals(HtmlUnit.class)
                    || annotation.annotationType().equals(IgnoreHtmlUnit.class)) {
                this.browserName = BrowserType.HTMLUNIT;
            } else if (annotation.annotationType().equals(IPhone.class)
                    || annotation.annotationType().equals(IgnoreIPhone.class)) {
                this.browserName = BrowserType.IPHONE;
            } else if (annotation.annotationType().equals(IPad.class)
                    || annotation.annotationType().equals(IgnoreIPad.class)) {
                this.browserName = BrowserType.IPAD;
            } else if (annotation.annotationType().equals(InternetExplorer.class)
                    || annotation.annotationType().equals(IgnoreInternetExplorer.class)) {
                this.browserName = BrowserType.IE;
            } else if (annotation.annotationType().equals(Opera.class)
                    || annotation.annotationType().equals(IgnoreOpera.class)) {
                this.browserName = BrowserType.OPERA;
            } else if (annotation.annotationType().equals(PhantomJS.class)
                    || annotation.annotationType().equals(IgnorePhantomJS.class)) {
                this.browserName = BrowserType.PHANTOMJS;
            } else if (annotation.annotationType().equals(Safari.class)
                    || annotation.annotationType().equals(IgnoreSafari.class)) {
                this.browserName = BrowserType.SAFARI;
            } else if (annotation.annotationType().equals(Browser.class)
                    || annotation.annotationType().equals(IgnoreBrowser.class)) {
                this.browserName = (String) AnnotationUtils.getValue(annotation, "browserName");
            }
            this.version = (String) AnnotationUtils.getValue(annotation, "version");
            if (AnnotationUtils.getValue(annotation, "platform") instanceof String) {
                this.platformName = (String) AnnotationUtils.getValue(annotation, "platform");
            } else if (AnnotationUtils.getValue(annotation, "platform") instanceof Platform) {
                this.platform = (Platform) AnnotationUtils.getValue(annotation, "platform");
            }
            this.javascriptEnabled = (BooleanOption) AnnotationUtils.getValue(annotation, "javascriptEnabled");
            String desiredCapabilitiesString = (String) AnnotationUtils.getValue(annotation, "desiredCapabilities");
            if (desiredCapabilitiesString != null) {
                this.desiredCapabilities = new Gson().fromJson(desiredCapabilitiesString, Map.class);
            }
        }

        public String getBrowserName() {
            return browserName;
        }

        public String getVersion() {
            return version;
        }

        public Platform getPlatform() {
            return platform;
        }

        public String getPlatformName() {
            return platform.toString();
        }

        public BooleanOption getJavascriptEnabled() {
            return javascriptEnabled;
        }

        public void setJavascriptEnabled(BooleanOption javascriptEnabled) {
            this.javascriptEnabled = javascriptEnabled;
        }

        public Map<String, Object> getDesiredCapabilitiesMap() {
            return desiredCapabilities;
        }

        public void setDesiredCapabilitiesMap(Map<String, Object> desiredCapabilitiesMap) {
            this.desiredCapabilities = desiredCapabilitiesMap;
        }

        private WebDriver createDriver() throws Exception {
            if (BrowserType.CHROME.equals(browserName)) {
                return new ChromeDriver();
            }

            if (BrowserType.FIREFOX.equals(browserName)) {
                return new FirefoxDriver();
            }

            if (BrowserType.HTMLUNIT.equals(browserName)) {
                return new HtmlUnitDriver();
            }

            if (BrowserType.IE.equals(browserName)) {
                return new InternetExplorerDriver();
            }

            if (BrowserType.SAFARI.equals(browserName)) {
                return new SafariDriver();
            }

            throw new WebDriverExtensionException("Could not find any known driver for " + toString());
        }

        private Object getTestDescriptionSuffix() {
            String browserNameDescription = (browserName != null ? "[" + browserName + "]" : "[ANY]");
            String javascriptEnabledDescription = (javascriptEnabled != BooleanOption.ANY ? "[" + javascriptEnabled.getValue() + "]" : "[ANY]");
            String desiredCapabilitiesDescription = (desiredCapabilities != null ? "[" + desiredCapabilities + "]" : "[NONE]");

            return browserNameDescription + javascriptEnabledDescription + desiredCapabilitiesDescription;
        }

        @Override
        public String toString() {
            return "Browser{" + "browserName=" + browserName + ", version=" + version + ", platform=" + platform + ", javascriptEnabled=" + javascriptEnabled + ", desiredCapabilitiesMap=" + desiredCapabilities + '}';
        }

        private boolean matches(BrowserConfiguration browser) {
            if (!this.getBrowserName().equals(browser.getBrowserName())) {
                return false;
            }
            if (this.isVersionProvided()
                    && (browser.isVersionProvided() && !this.getVersion().equals(browser.getVersion()))) {
                return false;
            }
            if (this.isPlatformProvided()
                    && (browser.isPlatformProvided() && !this.getPlatform().equals(browser.getPlatform()))) {
                return false;
            }
            if (this.isJavascriptEnabledProvided()
                    && (browser.isJavascriptEnabledProvided() && !this.getJavascriptEnabled().equals(browser.getJavascriptEnabled()))) {
                return false;
            }
            if (this.isDesiredCapabilitiesProvided()
                    && (browser.isDesiredCapabilitiesProvided() && !this.getDesiredCapabilitiesMap().equals(browser.getDesiredCapabilitiesMap()))) {
                return false;
            }
            return true;
        }

        private boolean isVersionProvided() {
            return !StringUtils.isBlank(version);
        }

        private boolean isPlatformProvided() {
            return !Platform.ANY.equals(platform);
        }

        private boolean isJavascriptEnabledProvided() {
            return !BooleanOption.ANY.equals(javascriptEnabled);
        }

        private boolean isDesiredCapabilitiesProvided() {
            return desiredCapabilities != null;
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof BrowserConfiguration) {
                final BrowserConfiguration browser = (BrowserConfiguration) object;
                if (!this.getBrowserName().equals(browser.getBrowserName())) {
                    return false;
                }
                if (this.isJavascriptEnabledProvided()
                        && (browser.isJavascriptEnabledProvided() && !this.getJavascriptEnabled().equals(browser.getJavascriptEnabled()))) {
                    return false;
                }
                if (this.isDesiredCapabilitiesProvided()
                        && (browser.isDesiredCapabilitiesProvided() && !this.getDesiredCapabilitiesMap().equals(browser.getDesiredCapabilitiesMap()))) {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(browserName, javascriptEnabled, desiredCapabilities);
        }
    }
}