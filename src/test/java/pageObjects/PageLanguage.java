package pageObjects;
import Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLanguage {
    public PageLanguage() {
        PageFactory.initElements(Driver.getDriver(), this);}

    @FindBy(xpath = "(//button[@type='button'])[3]")
    public WebElement languageMenu;

    @FindBy(xpath = "((//a[@href='/overview'])//div)[2]")
    public WebElement dashboardMenu;

    @FindBy (xpath = "//p[contains(.,'My Dictionary')]")
    public WebElement dictionaryMenu;

    @FindBy(xpath = "(//div[@class='MuiCardHeader-content css-11qjisw']/span)[2]")
    public WebElement tableMenu;
}

