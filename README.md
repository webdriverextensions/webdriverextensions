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

##### Run your tests on different browsers with the JUnitRunner
```java
import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.*;

@RunWith(WebDriverRunner.class)
@Firefox
@Chrome
@InternetExplorer
public class GoogleTest {

    // Models to initialize goes here...

    @Test
    public void searchTest() {

        // Test goes here...

    }

}
```

##### Model your website with the [Page Object Pattern](https://code.google.com/p/selenium/wiki/PageObjects) to make your test  reusable and maintainable
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
```java
import com.github.webdriverextensions.WebComponent;

public class SearchResult extends WebComponent {

    @FindBy(css = ".r a")
    public WebElement link;
    @FindBy(css = "._Rm")
    public WebElement url;

}
```

##### Make your test readable as instructions with the [Bot Pattern](https://code.google.com/p/selenium/wiki/BotStyleTests) by using the provided static Bot methods
```java
import static com.github.webdriverextensions.Bot.*;

@RunWith(WebDriverRunner.class)
@Firefox
@Chrome
@InternetExplorer
public class GoogleTest {

    GoogleSite googleSite;
    SearchPage searchPage;

    @Test
    public void searchTest() {
        open(googleSite);

        type("WebDriverExtensions Github", searchPage.query);
        click(searchPage.searchButton);
        assertIsOpen(searchPage);

        waitForElementToDisplay(searchPage.resultStats);
        SearchResult firstSearchResult = searchPage.searchResults.get(0);
        assertTextContains("webdriverextensions", firstSearchResult.link);
        assertTextStartsWith("https://github.com", firstSearchResult.url);
    }

}
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
