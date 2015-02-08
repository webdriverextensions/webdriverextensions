WebDriver Extensions
===================

WebDriver Extensions is designed to simplify the process of writing Java based Selenium/WebDriver tests. It's built on top of Selenium/WebDriver to make your tests more readable, reusabable and maintainable by encouraging the use of the [Page Object Pattern](https://code.google.com/p/selenium/wiki/PageObjects) and [Bot Pattern](https://code.google.com/p/selenium/wiki/BotStyleTests).

Finally released to the [Maven Central Repository](http://mvnrepository.com/search?q=webdriverextensions)! Latest release is version 1.2.1 which includes selenium-java 2.44.0 as a transative dependency.

What's included in this framework?
- A [Maven Plugin](https://github.com/webdriverextensions/webdriverextensions-maven-plugin#webdriver-extensions-maven-plugin) to manage, download and install drivers
- Annotation based JUnit Runner for running Selenium/WebDriver tests locally or remotely against multiple browsers
- New classes for modeling your website e.g. WebComponent (an extendable WebElement), WebPage, WebSite and WebRepository
- A Bot class providing static methods for interacting, asserting and checking conditions of WebElements, WebComponents, WebPages and WebSites
- A WebSite and WebRepository generator that enables adding WebComponents, WebPages, WebSites and WebRepositories by annotations
- A [Maven Archetype](https://github.com/webdriverextensions/webdriverextensions-archetype-quickstart#webdriver-extension-archetype-quickstart) for creating new projects



<br>
# Hello World Example
Here is an example of how a cross browser test is configured and written with and without the WebDriver Extensions Framework. The test will run on Firefox, Chrome and Internet Explorer. It will google for "Hello World" and assert that the first result contains the searched text "Hello World".

### With WebDriver Extensions
```java
@RunWith(WebDriverRunner.class)
@Firefox
@Chrome
@InternetExplorer
public class WebDriverExtensionsExampleTest {

    @FindBy(css = "input[name='q']")
    WebElement queryInput;
    @FindBy(css = "button[name='btnG']")
    WebElement searchButton;
    @FindBy(css = "#search")
    WebElement searchResults;

    @Test
    public void searchGoogleForHelloWorldTest() {
        open("http://www.google.com");
        assertCurrentUrlContains("google");

        type("Hello World", queryInput);
        click(searchButton);

        waitFor(3, SECONDS);
        assertTextContains("Hello World", searchResults);
    }
}
```
_<sub>Imports are hidden for the sake of simplicity, for imports and instructions on how to run this example see this [gist](https://gist.github.com/andidev/ad006a454edfd9f0e9e5)</sub>_


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

    @FindBy(css = "input[name='q']")
    WebElement queryInput;
    @FindBy(css = "button[name='btnG']")
    WebElement searchButton;
    @FindBy(css = "#search")
    WebElement searchResults;

    @Test
    public void searchGoogleForHelloWorldTest() throws InterruptedException {
        driver.get("http://www.google.com");
        assert driver.getCurrentUrl().contains("google");

        queryInput.sendKeys("Hello World");
        searchButton.click();

        SECONDS.sleep(3);
        assert searchResults.getText().contains("Hello World");
    }
}
```
_<sub>Imports are hidden for the sake of simplicity, for imports and instructions on how to run this example see this [gist](https://gist.github.com/andidev/6c5dc8033c019e4c069d)</sub>_


As you can see WebDriver Extensions Framework made the test almost readable as instructions you would give to someone who needs to manually perform this test. This is one of the main punchlines of this framework. The other one is the use of the [Page Object Pattern](https://code.google.com/p/selenium/wiki/PageObjects) which is unfortunately not demonstrated by this example for simplicity reasons.

### Further increased readability with Groovy
If wanted one could further increase readability by using the Groovy language instead of Java. Then the Hello World example would look like this

```groovy
@Grab(group='com.github.webdriverextensions', module='webdriverextensions', version='1.2.1')
@RunWith(WebDriverRunner)
@Firefox
@Chrome
@InternetExplorer
class WebDriverExtensionsGroovyExampleTest {

    @FindBy(css = "input[name='q']")
    WebElement queryInput;
    @FindBy(css = "button[name='btnG']")
    WebElement searchButton;
    @FindBy(css = "#search")
    WebElement searchResults;

    @Test
    void searchGoogleForHelloWorldTest() {
        open "http://www.google.com"
        assertCurrentUrlContains "google"

        type "Hello World", queryInput
        click searchButton

        waitFor 3, SECONDS
        assertTextContains "Hello World", searchResults
    }
}
```

_<sub>Imports are hidden for the sake of simplicity, for imports and instructions on how to run this example see this [gist](https://gist.github.com/andidev/b182c59a92d5ad66b035)</sub>_



<br>
# Getting Started

### Use Maven to add WebDriver Extensions
Add
```xml
<dependency>
	<groupId>com.github.webdriverextensions</groupId>
	<artifactId>webdriverextensions</artifactId>
	<version>1.2.1</version>
</dependency>
```
inside the `<dependencies>`-tag in your pom.xml file


<br>
### Download and manage your drivers with the Maven Plugin
Add
```xml
<plugin>
    <groupId>com.github.webdriverextensions</groupId>
    <artifactId>webdriverextensions-maven-plugin</artifactId>
    <version>1.0.1</version>
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
                <version>2.44</version>
            </driver>
            <driver>
                <name>chromedriver</name>
                <version>2.12</version>
            </driver>
        </drivers>
    </configuration>
</plugin>
```
inside the `<plugins>`-tag within the `<build>`-tag


<br>
### Cross Browser test your web site with the JUnitRunner

Run your tests locally by using the `WebDriverRunner`

```java
import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.*;

@RunWith(WebDriverRunner.class)
@Firefox
@Chrome
@InternetExplorer
public class CrossBrowserTest {

    // Add WebElements, WebPages and other supported web models to use in tests

    @Test
    public void test1() {
        // Configure browsers to test by annotating the test class
    }

    @Test
    @Safari
    public void test2() {
        // ...or by annotating test methods
    }

    @Test
    @IgnoreInternetExplorer
    public void test3() {
        // ...and use the ignore annotations to ignore specific browsers
    }

    ...

}
```

...or remotely by adding the `@RemoteAddress` annotaion

```java
@RunWith(WebDriverRunner.class)
@RemoteAddress("http://your-remote-url")
@Firefox
@Chrome
@InternetExplorer
public class CrossBrowserTest {
	...
}
```

To run your test headless you can use the `@HtmlUnit` annotation. If wanted you can also run your tests agains the Safari browser with the `@Safari` annotation (just make sure the chromedriver is installed).

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

If you want set a custom browser name this can be done by using the `@Browser` annotation e.g. `Browser(browserName = "foo")`.

For larger and more complex test grids the `@Browsers` annotation can be used. For example to test the Firefox browser on Windows, Mac and Linux
```java
@Browsers(firefox = {
    @Firefox(platform = Platform.WINDOWS),
    @Firefox(platform = Platform.MAC),
    @Firefox(platform = Platform.LINUX)
})
```



<br>
### Model your website with the [Page Object Pattern](https://code.google.com/p/selenium/wiki/PageObjects)
```java
import com.github.webdriverextensions.WebPage;

public class SearchPage extends WebPage {

    @FindBy(name = "q")
    public WebElement query;

    @FindBy(name = "btnG")
    public WebElement searchButton;

    @FindBy(id = "resultStats")
    public WebElement resultStats;

    @FindBy(css = ".rc")
    public List<SearchResult> searchResults;

    @Override
    public void open(Object... arguments) {
        open(GoogleSite.url);
        assertIsOpen();
    }

    @Override
    public void assertIsOpen(Object... arguments) {
        assertIsDisplayed(query);
        assertIsDisplayed(searchButton);
    }

}
```



<br>
### Model your page components with the WebComponent

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

...by extending the WebComponent

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

...and then include your new WebComponent as you include a WebElement

```java
@FindBy(css = "#playlist tr")
public List<PlaylistRow> playlist;
```

...and then use your new WebComponent in your tests

```java
assertTextEquals("Hey Joe", playlist.get(0).track);
```



<br>
### Make your test readable as instructions with the [Bot Pattern](https://code.google.com/p/selenium/wiki/BotStyleTests)

Simply import the static Bot where you want to use it

```java
import static com.github.webdriverextensions.Bot.*;
```

...and start interacting with your WebElements

```java
type("testuser", usernameInput);
type("ai78cGsT", passwordInput);
uncheck(rememberMeCheckbox);
click(loginButton);
```

...and write your asserts

```java
assertTextEquals("testuser", currentUser);
assertTitleStartsWith("Wikipedia - ");
assertCurrentUrlMatches("http://[a-z]{2,3}.wikipedia.org/.*");
assertHasClass("selected", mainPageTab);
// ...type assert then bring up the list of all supported asserts with your IDE's autocompletion
```

...and conditional statements

```java
if (hasClass("selected", mainPageTab)) {
    // ...do something
}
if (browserIsInternetExplorer()) {
    // ...handle cross browser difference
}
```

If you won't run your tests in the provided JUnitRunner make sure you set the driver in the WebDriverExtensionsContext before using the Bot

```java
WebDriverExtensionsContext.setDriver(yourDriver);
```

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

### <a href="http://testingbot.com" target="_blank">TestingBot</a> is now supporting this project by giving it a Free account!



<br>
# Changelog

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
