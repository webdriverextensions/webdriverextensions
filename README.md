WebDriver Extensions
===================

WebDriver Extensions is designed to simplify the process of writing Java based Selenium/WebDriver tests. It's built on top of Selenium/WebDriver to make your tests more readable, reusabable and maintainable by encouraging the use of the [Page Object Pattern](https://code.google.com/p/selenium/wiki/PageObjects) and [Bot Pattern](https://code.google.com/p/selenium/wiki/BotStyleTests).

Finally released to the [Maven Central Repository](http://mvnrepository.com/search?q=webdriverextensions)! Latest release is version 1.1.0 which includes selenium-java 2.43.1 as a transative dependency.

What's included in this framework?
- A [Maven Plugin](https://github.com/webdriverextensions/webdriverextensions-maven-plugin#webdriver-extensions-maven-plugin) to manage, download and install drivers
- Annotation based JUnit Runner for running Selenium/WebDriver tests locally or remotely against multiple browsers
- New classes for modeling your website e.g. WebComponent (an extendable WebElement), WebPage, WebSite and WebRepository
- A Bot class providing static methods for interacting, asserting and checking conditions of WebElements, WebComponents, WebPages and WebSites
- A WebSite and WebRepository generator that enables adding WebComponents, WebPages, WebSites and WebRepositories by annotations
- A [Maven Archetype](https://github.com/webdriverextensions/webdriverextensions-archetype-quickstart#webdriver-extension-archetype-quickstart) for creating new projects


### Quick Introduction

##### Use Maven to add WebDriver Extensions
```xml
<dependency>
	<groupId>com.github.webdriverextensions</groupId>
	<artifactId>webdriverextensions</artifactId>
	<version>1.1.0</version>
</dependency>
```

##### Download and manage your drivers with the Maven Plugin
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
                <version>2.43</version>
            </driver>
            <driver>
                <name>chromedriver</name>
                <version>2.10</version>
            </driver>
        </drivers>
    </configuration>
</plugin>
```

##### Cross Browser test your web site with the JUnitRunner

Run your tests locally by using the WebDriverRunner

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

        // ...or configure browsers to test by annotating test methods

    }

    @Test
    @IgnoreInternetExplorer
    public void test3() {

        // ...and use the ignore annotations to ignore specific browsers

    }

    ...

}
```

...or remotely by adding

```java
@RemoteAddress("http://your-remote-url")
```

to the test class

##### Model your website with the [Page Object Pattern](https://code.google.com/p/selenium/wiki/PageObjects)
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

##### Model your page components with the WebComponent (an extendable WebElement)

E.g. model your table rows

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



##### Make your test readable as instructions with the [Bot Pattern](https://code.google.com/p/selenium/wiki/BotStyleTests)

Simply import the static Bot where you want to use it

```java
import static com.github.webdriverextensions.Bot.*;
```

...and start interacting with your WebElements

```java
type("testuser", username);
type("ai78cGsT", password);
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
    // ...handle crossbrowser difference
}
```

If you won't run your tests in the provided JUnitRunner make sure you set the driver in the WebDriverExtensionsContext before using the Bot

```java
WebDriverExtensionsContext.setDriver(yourDriver);
```

##### Create new project with the [Maven Archetype](https://github.com/webdriverextensions/webdriverextensions-archetype-quickstart#webdriver-extension-archetype-quickstart)
Run
```sh
mvn archetype:generate -DarchetypeGroupId=com.github.webdriverextensions -DarchetypeArtifactId=webdriverextensions-archetype-quickstart
```

in your terminal to create
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


## License

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
