WebDriver Extension
===================

WebDriver Extension is a framework that extends the WebDriver framework with components that makes it easier to apply the PageObject pattern and Bot Style testing pattern

### Under Development
This project is under development and therefore not recomended to use yet, though the development is in its final stages. Once the [Milestone 1.0](https://github.com/andidev/webdriver-extension/issues?milestone=1&page=1&sort=created&state=open) is released the framework will be fully functional and ready for community feedback.

### Want to Try It?
Add the Sonatype OSS Snapshot Repository
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
type("frank", username);
type("bobbybrown", password);
check(keepMeLoggedInCheckbox);
click(loginButton);
```
...and write your asserts
```java
assertUrlStartsWith("http://en.wikipedia.org/wiki");
assertTitleEquals("Wikipedia, the free encyclopedia");
assertTextEquals("frank", currentUser);
```
...and conditional statements
```java
if(hasClass("selected", mainPageTab)) {
    ...do something
}
```
If you won't run your tests in the Webdriver Extensions JUnit Runners make sure you set the thread driver before using the Bot
```java
ThreadDriver.setDriver(yourDriver);
```

###Model Your Components
TOWRITE
```java
public class Menu extends WebComponent {
    @Delegate
    @FindBy(css = "#menu")
    public WebElement menu;
    
    @FindBy(css = "#menu-create")
    public WebElement create;
    
    @FindBy(css = "#menu-update")
    public WebElement update;
    
    @FindBy(css = "#menu-delete")
    public WebElement delete;
}
```

###Model Your Pages
TOWRITE
```java
public class LoginPage extends WebPage<WikipediaSite> {

    // Url
    public static String url = WikipediaSite.url + "/login";

    // WebElements
    @FindBy(css = "input[name='wpName']")
    public WebElement username;
    @FindBy(css = "input[name='wpPassword']")
    public WebElement password;
    @FindBy(css = "input[name='wpRemember']")
    public WebElement keepMeLoggedInCheckbox;    
    @FindBy(css = "input#wpLoginAttempt")
    public WebElement loginButton;

    @Override
    public void open() {
        open(url);
        assertIsOpen();
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        assertCurrentUrlEquals(url);
        assertIsDisplayed(username);
        assertIsDisplayed(password);
        assertIsDisplayed(keepMeLoggedInCheckbox);
        assertIsDisplayed(loginButton);
    }

    public void login(String username, String password, boolean keepMeLoggedIn) {
        clearAndType(username, this.username);
        clearAndType(password, this.password);
        if (keepMeLoggedIn) {
            check(this.keepMeLoggedInCheckbox);
        } else {
            uncheck(this.keepMeLoggedInCheckbox);
        }
        click(this.loginButton);
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
    public LoginPage loginPage;
    public MainPage mainPage;

    @Override
    public void open() {
        open(url);
    }

    @Override
    public void assertIsOpen() throws Error {
        assertCurrentUrlContains(url);
    }

    public void login(String username, String password, boolean keepMeLoggedIn) {
        open(loginPage);
        loginPage.login(username, password, keepMeLoggedIn);
        assertIsOpen(mainPage);
    }

    public void logout() {
        click(mainPage.logoutLink);
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
