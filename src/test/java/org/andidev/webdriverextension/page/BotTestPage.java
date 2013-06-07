package org.andidev.webdriverextension.page;

import static org.andidev.webdriverextension.ThreadDriver.*;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.generator.annotations.Generate;
import org.andidev.webdriverextension.site.WebDriverExtensionSite;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Generate
public class BotTestPage extends WebPage<WebDriverExtensionSite> {

    // Attributes
    @FindBy(css = "#prefixidsuffix")
    public WebElement attributesSpan;

    // Texts
    @FindBy(css = "#text-span")
    public WebElement textSpan;
    @FindBy(css = "#text-input")
    public WebElement textInput;

    // Integers
    @FindBy(css = "#intnumber-span")
    public WebElement intNumberSpan;
    @FindBy(css = "#intnumber-input")
    public WebElement intNumberInput;

    // Floats
    @FindBy(css = "#floatnumber-span")
    public WebElement floatNumberSpan;
    @FindBy(css = "#floatnumber-input")
    public WebElement floatNumberInput;

    // Selects
    @FindBy(css = "#select")
    public WebElement select;

    // Checkboxes
    @FindBy(css = "#checkbox1")
    public WebElement checkbox1;
    @FindBy(css = "#checkbox2")
    public WebElement checkbox2;
    @FindBy(css = "#checkbox3")
    public WebElement checkbox3;

    // Radiobuttons
    @FindBy(css = "#radiobutton1")
    public WebElement radiobutton1;
    @FindBy(css = "#radiobutton2")
    public WebElement radiobutton2;
    @FindBy(css = "#radiobutton3")
    public WebElement radiobutton3;

    @Override
    public void open() {
        getDriver().get("http://andidev.github.com/webdriver-extension/bot-test.html");
    }

    @Override
    public void assertIsOpen() throws Error {
    }
}
