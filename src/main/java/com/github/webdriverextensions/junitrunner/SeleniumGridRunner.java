package com.github.webdriverextensions.junitrunner;

import com.google.gson.Gson;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.github.webdriverextensions.internal.junitrunner.AnnotationUtils;
import com.github.webdriverextensions.ThreadDriver;
import com.github.webdriverextensions.internal.utils.InstanceUtils;
import static com.github.webdriverextensions.internal.utils.WebDriverUtils.convertToJsonString;
import com.github.webdriverextensions.junitrunner.annotations.RemoteAddress;
import com.github.webdriverextensions.junitrunner.annotations.Android;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.junitrunner.annotations.Browser;
import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.HtmlUnit;
import com.github.webdriverextensions.junitrunner.annotations.IPad;
import com.github.webdriverextensions.junitrunner.annotations.IPhone;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreAndroid;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreChrome;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreBrowser;
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
import org.openqa.selenium.remote.BrowserType;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumGridRunner extends BlockJUnit4ClassRunner {

    private static class SeleniumGridFrameworkMethod extends FrameworkMethod {

        final private BrowserConfiguration browser;

        public SeleniumGridFrameworkMethod(BrowserConfiguration browser, FrameworkMethod method) {
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

    public SeleniumGridRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        List<FrameworkMethod> testAnnotatedMethods = getTestClass().getAnnotatedMethods(Test.class);
        List<FrameworkMethod> testMethods = new ArrayList<FrameworkMethod>();
        for (FrameworkMethod testAnnotatedMethod : testAnnotatedMethods) {
            BrowserConfigurations browserConfigurations = new BrowserConfigurations().addConfigurationsFromClassAnnotations(getTestClass()).addConfigurationsFromMethodAnnotations(testAnnotatedMethod);
            if (!browserConfigurations.getBrowsers().isEmpty()) {
                for (BrowserConfiguration browser : browserConfigurations.getBrowsers()) {
                    testMethods.add(new SeleniumGridFrameworkMethod(browser, testAnnotatedMethod));
                }
            } else {
                // Not a Selenium Grid Annotated test, treat as normal test
                testMethods.add(testAnnotatedMethod);
            }
        }
        return testMethods;
    }

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        if (method instanceof SeleniumGridFrameworkMethod) {
            BrowserConfigurations browserConfigurations = new BrowserConfigurations().addConfigurationsFromClassAnnotations(getTestClass()).addConfigurationsFromMethodAnnotations(method);
            BrowserConfiguration browserConfiguration = ((SeleniumGridFrameworkMethod) method).getBrowser();
            Description description = describeChild(method);
            if (method.getAnnotation(Ignore.class) != null || browserConfigurations.isBrowserIgnored(browserConfiguration)) {
                notifier.fireTestIgnored(description);
            } else {
                log.trace("{}.{} threadId = {}", getName(), method.getName(), Thread.currentThread().getId());
                String remoteAddress = ((RemoteAddress) getTestClass().getJavaClass().getAnnotation(RemoteAddress.class)).value();
                try {
                    ThreadDriver.setDriver(browserConfiguration.createDriver(new URL(remoteAddress)));
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

        List<BrowserConfiguration> browsers = new ArrayList<BrowserConfiguration>();
        List<BrowserConfiguration> ignoreBrowsers = new ArrayList<BrowserConfiguration>();

        public List<BrowserConfiguration> getBrowsers() {
            return browsers;
        }

        public List<BrowserConfiguration> getIgnoreBrowsers() {
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
        private DesiredCapabilities desiredCapabilities;

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
            Class desiredCapabilitiesClass = (Class) AnnotationUtils.getValue(annotation, "desiredCapabilitiesClass");
            if (desiredCapabilitiesClass != null) {
                this.desiredCapabilities = InstanceUtils.newInstance(desiredCapabilitiesClass, DesiredCapabilities.class);
            }
            String desiredCapabilitiesJson = (String) AnnotationUtils.getValue(annotation, "desiredCapabilities");
            if (desiredCapabilitiesJson != null) {
                Map<String, Object> desiredCapabilitiesJsonMap = new Gson().fromJson(desiredCapabilitiesJson, Map.class);
                if (desiredCapabilitiesJsonMap != null) {
                    for (Map.Entry<String, Object> entry : desiredCapabilitiesJsonMap.entrySet()) {
                        this.desiredCapabilities.setCapability(entry.getKey(), entry.getValue());
                    }
                }
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

        public DesiredCapabilities getDesiredCapabilities() {
            return desiredCapabilities;
        }

        private WebDriver createDriver(URL url) throws Exception {
            this.desiredCapabilities.setBrowserName(this.browserName);
            this.desiredCapabilities.setVersion(this.version);
            if (this.platform != null) {
                this.desiredCapabilities.setPlatform(this.platform);
            } else {
                this.desiredCapabilities.setCapability(PLATFORM, this.platformName);
            }
            RemoteWebDriver driver = new RemoteWebDriver(
                    url,
                    this.desiredCapabilities);
            return driver;
        }

        private Object getTestDescriptionSuffix() {
            String browserNameDescription = (browserName != null ? "[" + browserName + "]" : "[ANY]"); // TODO: start using this.browserName or stop using it
            String versionDescription = (version != null ? "[" + version + "]" : "[ANY]");
            String platformDescription;
            if (platformName != null) {
                platformDescription = "[" + platformName + "]";
            } else {
                platformDescription = (platform != Platform.ANY ? "[" + platform.toString() + "]" : "[ANY]");
            }

            return browserNameDescription + versionDescription + platformDescription ;
        }

        @Override
        public String toString() {
            return "Browser{" + "browserName=" + browserName + ", version=" + version + ", platform=" + platform + ", desiredCapabilities=" + convertToJsonString(desiredCapabilities) + '}';
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
            if (this.isDesiredCapabilitiesProvided()
                    && (browser.isDesiredCapabilitiesProvided() && !this.getDesiredCapabilities().equals(browser.getDesiredCapabilities()))) {
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

        private boolean isDesiredCapabilitiesProvided() {
            return desiredCapabilities != null;
        }
    }
}