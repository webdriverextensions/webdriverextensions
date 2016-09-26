[![Build Status](https://drone.io/github.com/webdriverextensions/webdriverextensions/status.png)](https://drone.io/github.com/webdriverextensions/webdriverextensions/latest)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.webdriverextensions/webdriverextensions.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3Acom.github.webdriverextensions)

WebDriver Extensions
===================

WebDriver Extensions is designed to simplify Java based Selenium/WebDriver tests. It's built on top of Selenium/WebDriver to make your tests more readable, reusabable and maintainable by combining the [Page Object Pattern](https://code.google.com/p/selenium/wiki/PageObjects) and [Bot Pattern](https://code.google.com/p/selenium/wiki/BotStyleTests).

Available through the [Maven Central Repository](http://mvnrepository.com/search?q=webdriverextensions)! Latest release is version 2.8.1 which includes selenium-java 2.53.0 as a transitive dependency.


<br>
### What's included in this framework?
- A [Maven Plugin](https://github.com/webdriverextensions/webdriverextensions-maven-plugin#webdriver-extensions-maven-plugin) to manage, download and install drivers
- [Annotation based JUnit Runner](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/WebDriverRunner.html) for running Selenium/WebDriver tests locally or remotely against multiple browsers
- New classes for modelling your website e.g. [WebComponent](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebComponent.html) (an extendable WebElement), [WebPage](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html), [WebSite](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebSite.html) and [WebRepository](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebRepository.html)
- A [Bot](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/Bot.html) providing static methods for interacting, asserting and checking conditions of WebElements, WebComponents, WebPages and WebSites
- A WebSite and WebRepository [generators](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/generator/package-summary.html) that enables adding WebComponents, WebPages, WebSites and WebRepositories by [annotations](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/generator/annotations/package-summary.html)
- A [Maven Archetype](https://github.com/webdriverextensions/webdriverextensions-archetype-quickstart#webdriver-extension-archetype-quickstart) for creating new projects

<br>
### Want to Contribute?
If you find a bug or have a feature request please [create a new GitHub issue](https://github.com/webdriverextensions/webdriverextensions/issues/new) or even better clone this repository, commit your changes and make a [Pull Request](https://help.github.com/articles/using-pull-requests/).

<br>
### Have any Questions?
If you have question you can [ask them in a GitHub issue](https://github.com/webdriverextensions/webdriverextensions/issues/new).

<br>
# Content
- [Hello World Example](#hello-world-example)
    - [With WebDriver Extensions](#with-webdriver-extensions)
    - [Without WebDriver Extensions](#without-webdriver-extensions)
    - [Further increased readability with Groovy](#further-increased-readability-with-groovy)
- [Getting Started](#getting-started)
    - [Requirements](#requirements)
    - [Use Maven to add WebDriver Extensions](#use-maven-to-add-webdriver-extensions)
    - [Download and manage your drivers with the Maven Plugin](#download-and-manage-your-drivers-with-the-maven-plugin)
    - [Speed up your tests by running them in parallel](#speed-up-your-tests-by-running-them-in-parallel)
    - [Cross Browser test your website with the JUnitRunner](#cross-browser-test-your-website-with-the-junitrunner)
    - [Model your website with the Page Object Pattern](#model-your-website-with-the-page-object-pattern)
    - [Model your page components with the WebComponent](#model-your-page-components-with-the-webcomponent)
    - [Make your test readable as instructions with the Bot Pattern](#make-your-test-readable-as-instructions-with-the-bot-pattern)
    - [Create new projects with the Maven Archetype](#create-new-projects-with-the-maven-archetype)
- [Javadoc](#javadoc)
- [Changelog](#changelog)
- [Contributors](#contributors)
- [License](#license)



<br>
# Hello World Example
Here is an example of how a cross browser test looks like with and without the WebDriver Extensions Framework. The test will run on Firefox, Chrome and Internet Explorer. It will google for "Hello World" and assert that the search result contains the searched text "Hello World".



### With WebDriver Extensions
```java
@RunWith(WebDriverRunner.class)
@Firefox
@Chrome
@InternetExplorer
public class WebDriverExtensionsExampleTest {

    // Model
    @FindBy(name = "q")
    WebElement queryInput;
    @FindBy(name = "btnG")
    WebElement searchButton;
    @FindBy(id = "search")
    WebElement searchResult;

    @Test
    public void searchGoogleForHelloWorldTest() {
        open("http://www.google.com");
        assertCurrentUrlContains("google");

        type("Hello World", queryInput);
        click(searchButton);

        waitFor(3, SECONDS);
        assertTextContains("Hello World", searchResult);
    }
}
```
_<sub>Imports are hidden for the sake of simplicity, for imports and instructions on how to run this example see this [gist](https://gist.github.com/andidev/ad006a454edfd9f0e9e5)</sub>_



<br>
### Without WebDriver Extensions
```java
@RunWith(Parameterized.class)
public class WebDriverExampleTest {
    WebDriver driver;
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"Firefox"}, {"Chrome"}, {"InternetExplorer"}
        });
    }

    public WebDriverTest(String browserName) {
        if (browserName.equals("Firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equals("Chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("InternetExplorer")) {
            driver = new InternetExplorerDriver();
        }
        PageFactory.initElements(driver, this);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    // Model
    @FindBy(name = "q")
    WebElement queryInput;
    @FindBy(name = "btnG")
    WebElement searchButton;
    @FindBy(id = "search")
    WebElement searchResult;

    @Test
    public void searchGoogleForHelloWorldTest() throws InterruptedException {
        driver.get("http://www.google.com");
        assert driver.getCurrentUrl().contains("google");

        queryInput.sendKeys("Hello World");
        searchButton.click();

        SECONDS.sleep(3);
        assert searchResult.getText().contains("Hello World");
    }
}
```
_<sub>Imports are hidden for the sake of simplicity, for imports and instructions on how to run this example see this [gist](https://gist.github.com/andidev/6c5dc8033c019e4c069d)</sub>_


As you can see WebDriver Extensions Framework made the test almost readable as instructions you would give to someone who needs to manually perform this test. This is one of the main points of this framework. It also removed a lot of verbose boilerplate configuration code.

For the sake of simplicity this example does not demonstrate the [Page Object Pattern](https://code.google.com/p/selenium/wiki/PageObjects). Please keep on reading the [Getting Started](#getting-started) section to read more about how to create and use Page Objects.



<br>
### Further increased readability with Groovy
If wanted one could further increase readability by using the Groovy language instead of Java. Then the Hello World example would look like this

```groovy
@Grab(group='com.github.webdriverextensions', module='webdriverextensions', version='2.8.1')
@RunWith(WebDriverRunner)
@Firefox
@Chrome
@InternetExplorer
class WebDriverExtensionsGroovyExampleTest {

    // Model
    @FindBy(name = "q")
    WebElement queryInput;
    @FindBy(name = "btnG")
    WebElement searchButton;
    @FindBy(id = "search")
    WebElement searchResult;

    @Test
    void searchGoogleForHelloWorldTest() {
        open "http://www.google.com"
        assertCurrentUrlContains "google"

        type "Hello World", queryInput
        click searchButton

        waitFor 3, SECONDS
        assertTextContains "Hello World", searchResult
    }
}
```

_<sub>Imports are hidden for the sake of simplicity, for imports and instructions on how to run this example see this [gist](https://gist.github.com/andidev/b182c59a92d5ad66b035)</sub>_

Note that Groovy examples will not be covered by this document.



<br>
# Getting Started

### Requirements
The Selenium project is compiled with Java 7 since version 2.47.0. Therefore WebDriver Extensions also requires you to use Java 7 in version 2.0.0 and above.
Maven is not a requirement but is preferred and refered to in this document.

- Java 7 or above
- Maven 3 or higher



<br>
### Use Maven to add WebDriver Extensions
Add
```xml
<dependency>
	<groupId>com.github.webdriverextensions</groupId>
	<artifactId>webdriverextensions</artifactId>
	<version>2.8.1</version>
</dependency>
```
...as a dependency in your [pom.xml](https://gist.github.com/andidev/ad006a454edfd9f0e9e5#file-pom-xml) file.


<br>
### Download and manage your drivers with the [Maven Plugin](https://github.com/webdriverextensions/webdriverextensions-maven-plugin#webdriver-extensions-maven-plugin)
There is no need to download any drivers manually. Instead use the [WebDriver Extensions Maven Plugin GitHub](https://github.com/webdriverextensions/webdriverextensions-maven-plugin) to download and manage your drivers by adding
```xml
<plugin>
    <groupId>com.github.webdriverextensions</groupId>
    <artifactId>webdriverextensions-maven-plugin</artifactId>
    <version>3.0.2</version>
    <executions>
        <execution>
            <goals>
                <goal>install-drivers</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <drivers>
            <driver>
                <name>internetexplorerdriver</name>
                <version>2.53.1</version>
            </driver>
            <driver>
                <name>chromedriver</name>
                <version>2.22</version>
            </driver>
        </drivers>
    </configuration>
</plugin>
```
...as a plugin in your [pom.xml](https://gist.github.com/andidev/ad006a454edfd9f0e9e5#file-pom-xml) file. Then simply just update the version tag of the driver when a new driver is available and re-run your tests with the `mvn test` command or your preferred IDE.

The plugin will download the most suitable driver for  your OS. The bit of the driver will be 32bit with the exception of running the tests from a linux 64bit OS. If you would like to specify the OS and bit of the drivers to download you can provide them with a `<platform>` and `<bit>`-tag inside each `<driver>`-tag. Platform can be set to `windows`, `mac` or `linux` while the bit can be set to `32` or `64`.

The drivers will placed in a folder called `drivers` in the project root. If you will use the provided [WebDriverRunner](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/WebDriverRunner.html) there is no need for passing driver paths as System Properties since the framework will take care of the for you. If you won't be using it make sure to point the drivers out manually.

If you have configured a proxy in the settings.xml file the first encountered active proxy will be used. To specify a specific proxy to use you can provide the proxy id in the configuration.

If you run your tests from eclipse make sure you've allowed the webdriverextensions-maven-plugin to run the install-drivers goal. You can do this by adding the following to your pom.xml
```xml
<pluginManagement>
    <plugins>
        <!--Eclipse m2e settings needed to install drivers with the webdriverextensions-maven-plugin -->
        <plugin>
            <groupId>org.eclipse.m2e</groupId>
            <artifactId>lifecycle-mapping</artifactId>
            <version>1.0.0</version>
            <configuration>
                <lifecycleMappingMetadata>
                    <pluginExecutions>
                        <pluginExecution>
                            <pluginExecutionFilter>
                                <groupId>com.github.webdriverextensions</groupId>
                                <artifactId>webdriverextensions-maven-plugin</artifactId>
                                <versionRange>[1.0,)</versionRange>
                                <goals>
                                    <goal>install-drivers</goal>
                                </goals>
                            </pluginExecutionFilter>
                            <action>
                                <execute>
                                    <runOnIncremental>true</runOnIncremental>
                                </execute>
                            </action>
                        </pluginExecution>
                    </pluginExecutions>
                </lifecycleMappingMetadata>
            </configuration>
        </plugin>
    </plugins>
</pluginManagement>
```

For more information on configuring the driver please visit the [WebDriver Extensions Maven Plugin GitHub page](https://github.com/webdriverextensions/webdriverextensions-maven-plugin). If the latest drivers are not available yet please create an issue [here](https://github.com/webdriverextensions/webdriverextensions-maven-plugin/issues/new).



<br>
### Speed up your tests by running them in parallel
Run your tests in parallel by adding
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.18.1</version>
    <configuration>
        <parallel>all</parallel>
        <threadCount>10</threadCount>
        <perCoreThreadCount>false</perCoreThreadCount>
    </configuration>
</plugin>
```
...to your pom.xml file.

This configuration will run maximum 10 tests in parallel. For more information
about the configuration please see section [Fork Options and Parallel Test Execution](http://maven.apache.org/surefire/maven-surefire-plugin/examples/fork-options-and-parallel-execution.html) in the documentation of the [Maven Surefire Plugin](http://maven.apache.org/surefire/maven-surefire-plugin/index.html).

Try not to use non final static variables within your tests if you run your tests in parallel.
If you really have to use static variables that are not defined as final make sure to wrap them
in [InheritableThreadLocal](http://docs.oracle.com/javase/7/docs/api/java/lang/InheritableThreadLocal.html)
objects. In this way they will be static within the current thread and child threads (i.e. the current test).

Also before configuring to run your tests in parallel check that your website
allows it. For example problems could occur when logging in with the same user
at the same time (if your website supports a login functionality). There could
also be other reasons not to run tests in parallel.



<br>
### Cross Browser test your website with the JUnitRunner

Run your tests locally by using the [WebDriverRunner](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/WebDriverRunner.html)

```java
import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.*;

@RunWith(WebDriverRunner.class)
@Firefox
@Chrome
@InternetExplorer
@Edge
public class CrossBrowserTest {

    // Add WebElements, WebPages and other supported web models to use in tests

    @Test
    public void test1() {
        // Configure browsers to test by annotating the class
    }

    @Test
    @Safari
    public void test2() {
        // ...or by annotating methods
    }

    @Test
    @IgnoreInternetExplorer
    public void test3() {
        // ...and use the ignore annotations to ignore specific browsers
    }

    ...

}
```

...or remotely by adding the [@RemoteAddress](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/annotations/RemoteAddress.html) annotaion

```java
@RunWith(WebDriverRunner.class)
@RemoteAddress("http://your-remote-url")
@Firefox
@Chrome
@InternetExplorer
@Edge
public class CrossBrowserTest {
	...
}
```

To run your test headless without starting a browser, use the [@HtmlUnit](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/annotations/HtmlUnit.html) annotation. If wanted you can also run your tests against the Safari browser with the [@Safari](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/annotations/Safari.html) annotation (just make sure the chromedriver is installed). Note that there is currently a [WebDriver issue](https://code.google.com/p/selenium/issues/detail?id=7933) with running the SafariDriver on some OSX/Safari versions.

Browser `version` and `platform` settings can be passed as annotation parameters e.g. `@Firefox(version = "35.0", platform = Platform.MAC)`.

The desired capabilities can either be provided in JSON format as a string e.g. `@Chrome(desiredCapabilities = "{ chromeOptions: { args: [''--start-maximized'] }")` or by creating a new class that extends the WebDriver's `DesiredCapabilities` class
```java
public class StartMaximized extends DesiredCapabilities {
    public StartMaximized() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        setCapability(ChromeOptions.CAPABILITY, options);
    }
}
```
...and passing that to the annotation e.g. `@Chrome(desiredCapabilitiesClass = StartMaximized.class)`.

If you want set a custom browser name this can be done by using the [@Browser](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/annotations/Browser.html) annotation e.g. `Browser(browserName = "foo")`.

For larger and more complex test grids the [@Browsers](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/annotations/Browsers.html) annotation can be used. For example to test the Firefox browser on Windows, Mac and Linux
```java
@Browsers(firefox = {
    @Firefox(platform = Platform.WINDOWS),
    @Firefox(platform = Platform.MAC),
    @Firefox(platform = Platform.LINUX)
})
```

If you would like to use a custom driver path annotate the test with the  [@DriverPaths](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/annotations/DriverPaths.html) annotation, e.g.

```java
@DriverPaths(chrome="path/to/chromedriver", internetExplorer ="path/to/internetexplorerdriver")
```

If you want to run your test against 64bit Internet Explorer versions you can specify the path to the 64 bit driver with the [@DriverPaths](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/annotations/DriverPaths.html) annotation like this
```java
@DriverPaths(internetExplorer ="drivers/internetexplorerdriver-windows-64bit.exe")
```
another way to do it is to set the `webdriverextensions.ie.driver.use64Bit` to `true`, e.g. when running the tests with maven: `mvn test -Dwebdriverextensions.ie.driver.use64Bit=true`.

To take screenshots on test failure annotate the test class with the [@TakeScreenshotOnFailure](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/annotations/TakeScreenshotOnFailure.html). The screenshots will be saved into a directory named `screenshots` located in the project root. The path to the screenshots directory can be configured either by annotating the test class with the  [@ScreenshotsPath](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/annotations/ScreenshotsPath.html) annotation or by setting the `webdriverextensions.screenshotspath` property. E.g.

```java
@RunWith(WebDriverRunner.class)
@Firefox
@TakeScreenshotOnFailure
@ScreenshotsPath("path/to/screenshots")
public class SomeTest {
	...
}
```

The implicitly wait for tests can be set by annotating test classes or methods with the [@ImplicitlyWait](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/annotations/ImplicitlyWait.html) annotation. E.g.

```java
@RunWith(WebDriverRunner.class)
@Firefox
@ImplicitlyWait(1)
public class SomeTest {
    @Test
    public void somethingToTest() {
        // Implicittly wait is set to one second
    }
    @Test
    @ImplicitlyWait(value = 1, unit = MINUTES)
    public void somethingElseToTest() {
        // Implicittly wait is set to one minute
    }
}
```

To set other driver specific setting use the JUnit @Before annotation. The driver
can be retreived by using the [driver()](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/Bot.html#driver--) method in the [Bot](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/Bot.html) class. E.g.

```java
@RunWith(WebDriverRunner.class)
@Firefox
public class SomeTest {
    @Before
    public void configure() {
        driver().manage().timeouts().pageLoadTimeout(10, SECONDS);
    }
    ...
}
```

<br>
### Model your website with the [Page Object Pattern](https://code.google.com/p/selenium/wiki/PageObjects)

Model your website pages, e.g. a login page

```html
<html>
    <head>
        <title>Login Page</title>
    </head>
    <body>
        <form>
            <label>Username</label> <input name="username">
            <label>Password</label> <input name="password">
            <input type="checkbox" name="remember-me"> Remember me
            <button id="login-button">Login</button>
        </form>
    </body>
</html>
```

...by extending the [WebPage](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html) class

```java
import com.github.webdriverextensions.WebPage;

public class LoginPage extends WebPage {

    @FindBy(name = "username")
    public WebElement usernameInput;
    @FindBy(name = "password")
    public WebElement passwordInput;
    @FindBy(name = "remember-me")
    public WebElement rememberMeCheckbox;
    @FindBy(id = "login-buttom")
    public WebElement loginButton;

    @Override
    public void open(Object... arguments) {
        // Define how to open this page, e.g.
        open("https://www.your-website-url.com/login");
        assertIsOpen();
    }

    @Override
    public void assertIsOpen(Object... arguments) {
        // Define how to assert that this page is open, e.g.
        assertTitleEquals("Login Page");
        assertIsDisplayed(usernameInput);
        assertIsDisplayed(passwordInput);
        assertIsDisplayed(rememberMeCheckbox);
        assertIsDisplayed(loginButton);
    }
}
```
...and then add and use it in your tests

```java
@RunWith(WebDriverRunner.class)
@Firefox
public class LoginPageTest {

    // Add models to inject into test
    LoginPage loginPage;

    @Test
    public void loginTest() {
        open(loginPage); // Calls the open method defined in LoginPage
        type("foo", loginPage.username);
        type("bar", loginPage.password);
        click(loginButtom);
        assertIsNotOpen(loginPage); // Calls the assertIsNotOpen method in the abstract WebPage class which inverts the assertIsOpen method defined in LoginPage
    }

    ...

}
```

Since the [WebPage](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html) class only implements a part of the the [Openable](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/internal/Openable.html) interface you have to implement the [open(Object... arguments)](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html#open-java.lang.Object...-) and [assertIsOpen(Object... arguments)](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html#assertIsOpen-java.lang.Object...-) methods yourself. As soon as this is done you can also call the [isOpen(Object... arguments)](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html#isOpen-java.lang.Object...-), [isNotOpen(Object... arguments)](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html#isNotOpen-java.lang.Object...-) and the [assertIsNotOpen(Object... arguments)](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html#assertIsNotOpen-java.lang.Object...-) methods inherited from the [WebPage](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html) class.

The [open(Object... arguments)](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html#open-java.lang.Object...-) and [assertIsOpen(Object... arguments)](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html#assertIsOpen-java.lang.Object...-) methods can take any number of arguments and therefore it is possible to pass entity ids or other required data needed to load the page. E.g. a page showing a specific order

```java
public class OrderPage {

    @FindBy(id = "order-number")
    public WebElement orderNumber;
    ...

    @Override
    public void open(Object... arguments) {
        int orderNumberToOpen = (int) arguments[0];
        System.err.println("https://www.your-website-url.com/order?orderid=" + orderNumberToOpen);
        assertIsOpen(orderNumberToOpen);
    }

    @Override
    public void assertIsOpen(Object... arguments) {
        int orderNumberToAssert = (int) arguments[0];
        assertTextEquals(orderNumberToAssert, orderNumber);
        ...
    }
}
```

...and then use it in your test

```java
open(orderPage, 134523); // Calls the open method defined in OrderPage with the order number 134523 as an argument
```

There is also a [WebSite](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebSite.html) class which can be used if you would want to create a Site Object i.e. a model of the complete website. It is actually no difference between the [WebPage](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html) and the [WebSite](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebSite.html) class except the name.

An alternative to using the [WebPage](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html) class is using the [WebRepository](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebRepository.html) class. The only difference is that it does not implement the [Openable](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/internal/Openable.html) interface and therefore there is no need to override and implement the `open(Object... arguments)` and `assertIsOpen(Object... arguments)` methods.

Note that any class extending the [WebPage](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebPage.html), [WebSite](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebSite.html) or [WebRepository](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebRepository.html) class that are added as fields in the test will automatically be injected/instantiated if the [WebDriverRunner](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/WebDriverRunner.html) is used. If you won't run your tests with the [WebDriverRunner](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/WebDriverRunner.html) you can call the Selenium WebDriver `PageFactory.initElements` method and pass the [WebDriverExtensionFieldDecorator](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebDriverExtensionFieldDecorator.html) before running the test, e.g.

```java
PageFactory.initElements(new WebDriverExtensionFieldDecorator(yourDriver), this);
```



<br>
### Model your page components with the [WebComponent](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebComponent.html)

Model repeating html content, e.g. table rows

```html
<table id="playlist">
     <tr>
          <td class="track">Hey Joe</td>
          <td class="artist">Jimi Hendrix</td>
          <td class="time">3:30</td>
          <td class="album">Are You Experienced</td>
     </tr>
     <tr>
          <td class="track">Play with Fire</td>
          <td class="artist">The Rolling Stones</td>
          <td class="time">2:14</td>
          <td class="album">The Last time</td>
     </tr>

     ...

</table>
```

...by extending the [WebComponent](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebComponent.html)

```java
import com.github.webdriverextensions.WebComponent;

public class PlaylistRow extends WebComponent {

    @FindBy(className = "track")
    public WebElement track;
    @FindBy(className = "artist")
    public WebElement artist;
    @FindBy(className = "time")
    public WebElement time;
    @FindBy(className = "album")
    public WebElement album;
}
```

...and then include it as you include a WebElement

```java
@FindBy(css = "#playlist tr")
public List<PlaylistRow> playlist;
```

...and then start using it

```java
assertTextEquals("Hey Joe", playlist.get(0).track); // Use WebElements in WebComponents
click(playlist.get(0));                             // Use WebComponents as WebElements
```

Note that `@FindBy` annotation locators used inside a WebComponent have the WebComponent's html content as the search context. To locate html tags outside the WebComponent you could reset the search context by adding the [@ResetSearchContext](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/annotations/ResetSearchContext.html) annotation.

If you wish to delegate the method calls of a WebComponent to an underlying WebElement you can do so by annotating a WebElement inside the WebComponent with the [@Delegate](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/annotations/Delegate.html) annotation.

If you won't run your tests with the [WebDriverRunner](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/WebDriverRunner.html) you must call the Selenium WebDriver `PageFactory.initElements` method and pass the [WebDriverExtensionFieldDecorator](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebDriverExtensionFieldDecorator.html) before running the test, e.g.
```java
PageFactory.initElements(new WebDriverExtensionFieldDecorator(yourDriver), this);
```


<br>
### Make your test readable as instructions with the [Bot Pattern](https://code.google.com/p/selenium/wiki/BotStyleTests)

Simply import the static [Bot](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/Bot.html) where you want to use it

```java
import static com.github.webdriverextensions.Bot.*;
```

...and start interacting with your web models

```java
open("https://www.your-website-url.com");   // Open urls
type("testuser", usernameInput);            // Type into WebElements referencing text input tags
type("ai78cGsT", passwordInput);
uncheck(rememberMeCheckbox);                // Check and uncheck WebElements referencing checkbox input tags
click(loginButton);                         // Click at WebElements

open(settingsPage)                          // Open WebPages
selectOption("Swedish", languageSelectBox); // Select options in WebElements referencing select tags
```

...and write your asserts

```java
assertIsOpen(homePage);                                        // Assert WebPages are open
assertTextEquals("testuser", currentUser);                     // Assert text in WebElements equals
assertTitleStartsWith("Wikipedia - ");                         // Assert title starts with
assertCurrentUrlMatches("http://[a-z]{2,3}.wikipedia.org/.*"); // Assert current url matches regex
assertHasClass("selected", homeTab);                           // Assert WebElement tags has class
// ...type assert then bring up the list of all supported asserts with your IDE's autocompletion
```

...and conditional statements

```java
if (hasClass("selected", homeTab)) { // Check if WebElement tags has class
    // ...do something
}
if (browserIsInternetExplorer()) {   // Check if browser is Internet Explorer
    // ...handle cross browser difference
}
```

...and wait for specific time and conditions

```java
waitFor(3, MINUTES);                                         // Wait for specific time
waitForElementToDisplay(downloadCompletePopup, 30, SECONDS); // Wait for WebElements to display within specific time
```

...and use the driver

```java
System.out.println(driver().getPageSource());
```

...and take screenshots

```java
takeScreenshots("screenshotfilename") // Save a screenshot to the screenshots directory in the project root
```

For a list of provided [Bot](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/Bot.html) methods take a look at the [javadoc for the Bot class](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/Bot.html) or use the autocompletion tool of your IDE (usally with Ctrl + Space and then start typing).

If you feel that some [Bot](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/Bot.html) methods are missing please describe them in a [new GitHub issue](https://github.com/webdriverextensions/webdriverextensions/issues/new) or even better clone this repository, commit the new methods and create a [Pull Request](https://help.github.com/articles/using-pull-requests/).

If you won't run your tests with the [WebDriverRunner](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/WebDriverRunner.html) make sure you set the driver in the [WebDriverExtensionsContext](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/WebDriverExtensionsContext.html) before using the [Bot](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/Bot.html)

```java
WebDriverExtensionsContext.setDriver(yourDriver);
```

There is now also a VaadinBot that can be used if testing an application using the [Vaadin Framework](https://vaadin.com/home)

<br>
### Create new projects with the [Maven Archetype](https://github.com/webdriverextensions/webdriverextensions-archetype-quickstart#webdriver-extension-archetype-quickstart)
Open your terminal and run
```sh
mvn archetype:generate -DarchetypeGroupId=com.github.webdriverextensions -DarchetypeArtifactId=webdriverextensions-archetype-quickstart
```

...and answer the questions to generate
```
projectname
├── drivers
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── companyname
    │               ├── SiteNameSite.java
    │               ├── SiteNameSiteTest.java
    │               ├── component
    │               │   └── ExampleWebComponent.java
    │               └── page
    │                   └── MainPage.java
    └── test
        ├── java
        │   └── com
        │       └── companyname
        │           └── MainPageTest.java
        └── resources
            └── logback-test.xml
```

No need to add any drivers since the webdriverextensions-maven-plugin is configured to download
them for you!

Simply just run the generated template test by executing
```sh
cd projectname
mvn test
```


<br>
# Javadoc
The Javadoc of this project is available online hosted by javadoc.io. You can find the latest documentation over  [here](http://www.javadoc.io/doc/com.github.webdriverextensions/webdriverextensions). Please note that at the moment the documentation of the java classes and methods are limited (except for this documentation). I will try to add the Javadoc as soon as possible.


<br>
# Changelog
#### 2.8.1 (2016 September 16)
- BUGFIX Fixes invalid screenshot filename on Windows platform ([PR 68](https://github.com/webdriverextensions/webdriverextensions/pull/68) thanks to [@consulbit](https://github.com/consulbit))

#### 2.8.0 (2016 July 2)
- ENHANCEMENT Added support for [@Edge](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/2.8.1/com/github/webdriverextensions/junitrunner/annotations/Edge.html) browser 

#### 2.7.0 (2016 May 24)
- ENHANCEMENT Added doubleClick(WebElement webElement), waitUntil(Predicate<WebDriver> perdicate) and waitUntil(Predicate<WebDriver> perdicate, long secondsToWait) to Bot (thanks to [@dve](https://github.com/dve))
- ENHANCEMENT Added VaadinBot that can be used if testing an application using the [Vaadin Framework](https://vaadin.com/home) (thanks to [@dve](https://github.com/dve))

#### 2.6.2 (2016 April 9)
- ENHANCEMENT Added ISO date and time to screenshot filename

#### 2.6.1 (2016 April 9)
- Made private constructor public again for Bot, BotUtils and WebDriverExtensionsContext classes [fix issue #63](https://github.com/webdriverextensions/webdriverextensions/issues/63)

#### 2.6.0 (2016 Mars 17)
- SELENIUM UPDATE Updated selenium version to 2.53.0
- ENHANCEMENT SonarQube improvements (thanks to [@faisal-hameed](https://github.com/faisal-hameed) at [@DevFactory](https://github.com/DevFactory))

#### 2.5.0 (2016 February 14)
- SELENIUM UPDATE Updated selenium version to 2.52.0
- BUGFIX WebPage.assertIsNotOpen(Openable, Object...) is now working as it should ([PR 57](https://github.com/webdriverextensions/webdriverextensions/pull/57) thanks to [@cplaetzinger](https://github.com/dve))

#### 2.4.0 (2016 February 4)
- SELENIUM UPDATE Updated selenium version to 2.50.1

#### 2.3.0 (2016 January 20)
- SELENIUM UPDATE Updated selenium version to 2.49.0

#### 2.2.0 (2015 November 6)
- JUNIT UPDATE Updated JUnit version to 4.12
- BUGFIX Fixes issue with wrong test description when using JUnit 4.12 and above ([PR 56](https://github.com/webdriverextensions/webdriverextensions/pull/56) thanks to [@cplaetzinger](https://github.com/cplaetzinger))
- BUGFIX Corrected incorrect screenshot file extension from .jpg to .png ([PR 55](https://github.com/webdriverextensions/webdriverextensions/pull/55) thanks to [@cplaetzinger](https://github.com/cplaetzinger))

#### 2.1.2 (2015 November 4)
- BUGFIX Fixed that Chrome Driver path is not set to blank if only Internet Explorer Driver path is set with @DriverPath annoation

#### 2.1.1 (2015 October 14)
- SELENIUM UPDATE Updated selenium version to 2.48.2

#### 2.1.0 (2015 October 9)
- SELENIUM UPDATE Updated selenium version to 2.48.1
- BUGFIX Fixed bug when @TakeScreenshotOnFailure takes more than one screenshot 
of a failing test when more then one test fails in the same test class ([PR 52](https://github.com/webdriverextensions/webdriverextensions/pull/52) thanks to [@gunnee](https://github.com/gunnee))

#### 2.0.1 (2015 September 30)
- SELENIUM UPDATE Updated selenium version to 2.47.2

#### 2.0.0 (2015 September 20)
- JAVA 7 REQUIREMENT Now compiled with java 7 as target since selenium already does that since the 2.47.0 version

#### 1.7.0 (2015 August 11)
- SELENIUM UPDATE Updated selenium version to 2.47.1

#### 1.6.0 (2015 June 9)
- SELENIUM UPDATE Updated selenium version to 2.46.0

#### 1.5.0 (2015 May 12)
- FEATURE Added support for passing WebComponents as generic arguments to other WebComponents, WebPages and WebRepositories [fixes issue #50](https://github.com/webdriverextensions/webdriverextensions/issues/50). E.g.

```java
public class TableComponent<T extends WebComponent> extends WebComponent {
    @FindBy(...)
    public List<T> rowList;
}

public class ASearchResultType extends WebComponent {
    // the model for the search result row
}

@FindBy(...)
TableComponent<ASearchResultType> resultTable;
```

- ENHANCEMENT Added descriptive messages to general field instantiation exceptions thrown by WebDriverExtensions
- BUGFIX Removed driver from ThreadLocal when test finished running or failed

#### 1.4.0 (2015 Mars 23)
- FEATURE Added Bot method waitForElementToDisplay with TimeUnit as parameter
- FEATURE Added @ImplicitlyWait annotation to WebDriverRunner
- FEATURE Added @TakeScreenshotOnFailure and @ScreenshotsPath annotations to WebDriverRunner
- FEATURE Added takeScreenshot method to Bot
- BUGFIX Made @DriverPath and @RemoteAddress annotations only applicable as class annotations

#### 1.3.0 (2015 Mars 12)
- SELENIUM UPDATE Updated selenium version to 2.45.0

#### 1.2.1 (2014 December 3)
- ENHANCEMENT Added descriptive messages to instantiation exceptions thrown by WebDriverExtensions when WebPage, WebSite and WebRepository class is either abstract, has no no args constructor or has no accessible constructor

#### 1.2.0 (2014 October 29)
- SELENIUM UPDATE Updated selenium version to 2.44.0
- BUGFIX Using JUnit @Test timeouts will no longer cause WebDriverExtensionsContext.getDriver() method to throw an exception

#### 1.1.0 (2014 September 15)
- SELENIUM UPDATE Updated selenium version to 2.43.1
- FEATURE Added waitForElementsToDisplay
- FEATURE Added ignore case to text bot methods
- BUGFIX @Delegate annotated field is now allowed to be private

#### 1.0.1 (2014 September 4)
- BUGFIX Swallowing 'No runnable methods' error from BlockJUnit4ClassRunner so empty tests are allowed
- BUGFIX Made created abstract open method in WebPage and SitePage so eclipse wont complain about @Override annotation


#### 1.0.0 (2014 September 2)
- Initial release!



<br>
# Contributors
- Thanks [Sauce Labs](https://saucelabs.com/) and [TestingBot](http://testingbot.com) for supporting this project with a free account for testing the remote setting for the [WebDriverRunner](http://static.javadoc.io/com.github.webdriverextensions/webdriverextensions/1.2.1/com/github/webdriverextensions/junitrunner/WebDriverRunner.html)
- Thanks [Eniro](http://www.eniro.se/) for helping me develop and test this this framework




<br>
# License

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
