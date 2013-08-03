WebDriver Extension
===================

WebDriver Extension is a framework that extends the WebDriver framework with components that makes it easier to apply the PageObject pattern and Bot Style testing pattern

### Under Development
This project is under development and therefore not recomended to use yet, though the development in its final stages. Once the [Milestone 1.0](https://github.com/andidev/webdriver-extension/issues?milestone=1&page=1&sort=created&state=open) is released the framework will be fully functional and ready for community feedback.

### Still Want to Try It?
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
###Start Using the Bot
Import the static Bot methods
```java
import static org.andidev.webdriverextension.Bot.*;
```
...and start interacting with your WebElements
```java
type("andidev", username);
type("mypassword", password);
click(loginButton);
```
...and asserting
```java
assertUrlStartsWith("http://www.andidev.org");
assertTitleEquals("Welcome!");
assertTextEquals("andidev", usernameLabel);
```
...or checking conditions
```java
if(hasClass("active")) {
    ...do something
}
```
If you won't run your tests in the Webdriver Extensions JUnit Runners make sure you set the thread driver before using the Bot
```java
ThreadDriver.setDriver(yourDriver);
```


###Model Your Components
TODO

###Model Your Pages
TODO

###Model Your Site
TODO

###Create Your Tests
TODO

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
