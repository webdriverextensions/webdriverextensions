WebDriver Extension
===================

WebDriver Extension is a framework that extends the WebDriver framework with components that makes it easier to apply the PageObject pattern and Bot Style testing pattern

### Under Development
This project is under development and therefore not recomended to use yet, though the development is in its final stages. Once the [Milestone 1.0](https://github.com/andidev/webdriver-extension/issues?milestone=1&page=1&sort=created&state=open) is released the framework will be fully functional and ready for community feedback.

### Want to Try It?
Use the archetype to create a new project
```sh
mvn archetype:generate                                                                             \
  -DgroupId=com.github                                                                             \
  -DartifactId=github                                                                              \
  -Dversion=1.0-SNAPSHOT                                                                           \
  -DsiteName=GitHub                                                                                \
  -DsiteUrl=https://github.com                                                                     \
  -DarchetypeGroupId=org.andidev                                                                   \
  -DarchetypeArtifactId=webdriver-extension-archetype-quickstart                                   \
  -DarchetypeVersion=1.0-SNAPSHOT                                                                  \
  -DarchetypeCatalog=https://oss.sonatype.org/content/repositories/snapshots/archetype-catalog.xml
```

... and download the latest drivers
```sh
mvn archetype:generate                                                                             \
  -DarchetypeGroupId=org.andidev                                                                   \
  -DarchetypeArtifactId=webdriver-extension-archetype-drivers                                      \
  -DarchetypeVersion=1.0-SNAPSHOT                                                                  \
  -DarchetypeCatalog=https://oss.sonatype.org/content/repositories/snapshots/archetype-catalog.xml
```

Or if you want to use it in a current project add the Sonatype OSS Snapshot Repository
```xml
<repository>
    <id>sonatype-nexus-snapshots</id>
    <name>Sonatype Nexus Snapshots</name>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
</repository>
```
...and the Webdriver Extension Snapshot Dependency to your pom.xml
```xml
<dependency>
    <groupId>org.andidev</groupId>
    <artifactId>webdriver-extension</artifactId>
    <version>1.0.M1-SNAPSHOT</version>
</dependency>
```

###Start Using the Bot methods
Just import the static Bot
```java
import static org.andidev.webdriverextension.Bot.*;
```
...and start interacting with your WebElements
```java
type("testuser", username);
type("ai78cGsT", password);
uncheck(keepMeLoggedInCheckbox);
click(loginButton);
```
...and write your asserts
```java
assertTextEquals("testuser", currentUser);
assertTitleEndsWith(" - Wikipedia, the free encyclopedia");
assertCurrentUrlMatches("http://[a-z]{2,3}.wikipedia.org/.*");
assertHasClass("selected", mainPageTab);
// ...type assert then bring up the list of all supported asserts with your IDE's autocompletion
```
...and conditional statements
```java
if (hasClass("selected", mainPageTab)) {
    // ...do something
}
```
If you won't run your tests in the Webdriver Extensions JUnit Runners make sure you set the thread driver before using the Bot
```java
ThreadDriver.setDriver(yourDriver);
```

###Model Your Components
TOWRITE
```java
public class Interaction extends WebComponent {
    @FindBy(css = "h3 a")
    @Delegate
    private WebElement interactionDelegate;
    
    @FindBy(css = "#n-help a")
    public WebElement help;
    
    @FindBy(css = "#n-aboutsite a")
    public WebElement aboutWikipedia;
    
    @FindBy(css = "#n-portal a")
    public WebElement communityPortal;
    
    @FindBy(css = "#n-recentchanges a")
    public WebElement recentChanges;

    @FindBy(css = "#n-contactpage a")
    public WebElement contactPage;
}
```
```java
public class RepositoriesSearchResult extends WebComponent {

}
```

###Model Your Pages
TOWRITE
```java
public class MainPage extends WebPage<WikipediaSite> {
    // Top Menu
    // ...
    @FindBy(css = "#pt-logout a")
    public WebElement logout;

    // Search
    @FindBy(css = "input#searchInput")
    public WebElement search;

    @FindBy(css = "input#searchButton")
    public WebElement searchButton;

    // Side Menu
    @FindBy(css = "#n-mainpage-description a")
    public WebElement mainPage;
    
    @FindBy(css = "#n-contents a")
    public WebElement contents;
    
    @FindBy(css = "#n-featuredcontent a")
    public WebElement featuredContent;    
    
    @FindBy(css = "#n-currentevents a")
    public WebElement currentEvents;
    
    @FindBy(css = "#n-randompage a")
    public WebElement randomArticle;
    
    @FindBy(css = "#n-sitesupport a")
    public WebElement donateToWikipedia;
    
    @FindBy(css = "#p-interaction")
    public Interaction interaction;
    
    // ...other components

    @Override
    public void open() {
        if (isNotOpen()) {        
            open("http://en.wikipedia.org/wiki/Main_Page");
            assertIsOpen();
        }    
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        assertIsOpen(site);

        // Assert Search is displayed
        assertIsDisplayed(search);
        assertIsDisplayed(searchButton);

        // Assert Site Menu is displayed
        assertIsDisplayed(mainPage);
        assertIsDisplayed(contents);
        assertIsDisplayed(featuredContent);
        assertIsDisplayed(currentEvents);
        assertIsDisplayed(randomArticle);
        assertIsDisplayed(donateToWikipedia);
        assertIsDisplayed(interaction);
        // ...other asserts
    }

    public void search(String query) {
        clearAndType(query, search);
        click(searchButton);
    }

    public void logout() {
        click(logoutButton);
    }
}
```

###Model Your Site
TOWRITE
```java
public class WikipediaSite extends WebSite {

    // Url
    public static String url = "http://www.wikipedia.org/";

    // WebPages
    public WelcomePage welcomePage;
    public MainPage mainPage;
    public SearchResultPage searchResultPage;
    public LoginPage loginPage;
    // ...other pages

    @Override
    public void open() {
        open(url);
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        assertCurrentUrlMatches("http://[a-z]{2,3}.wikipedia.org/.*");
    }

    public List<SearchResultRow> search(String query) {
        open(mainPage);
        mainPage.search(query);
        assertIsOpen(searchResultPage);
        return searchResultPage.getSearchResultRows();
    }

    public void login(String username, String password, boolean keepMeLoggedIn) {
        open(loginPage);
        loginPage.login(username, password, keepMeLoggedIn);
url    }

    public void logout() {
        open(mainPage);
        mainPage.logout();
    }
}
```

###Create Your Tests
TOWRITE
```java
ThreadDriver.setDriver(yourDriver);
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
