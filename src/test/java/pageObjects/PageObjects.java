package pageObjects;

import Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjects {


    public PageObjects(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@name='username']")
    public WebElement loginFormUsername;

    @FindBy(xpath = "//input[@id=':r1:']")
    public WebElement loginFormPassword;

    @FindBy(xpath = "//span[@id='submit']")
    public WebElement loginFormSubmitButton;

    @FindBy(xpath = "//p[contains(.,'My Dashboard')]")
    public WebElement lSiteDashboard;

    @FindBy(xpath = "//div[@class='MuiAlert-message css-1xsto0d'][contains(.,'User not found')]")
    public WebElement eInvalidCredential;

    @FindBy (xpath = "//span[@class='MuiBadge-root css-1w651zy']/div")
    public WebElement logoutMenu;

    @FindBy (xpath = "//li[contains(.,'Logout')]")
    public WebElement logoutButton;

    @FindBy (xpath = "//button[@aria-label='close']")
    public WebElement closeNotification;

    @FindBy (xpath = "(//span[contains(text(), 'Announcements')])[2]")
    public WebElement frameNotification;

    @FindBy (xpath = "//input[@name='remember']")
    public WebElement checkbox;


}