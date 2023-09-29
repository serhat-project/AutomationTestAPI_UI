package pageObjects;

import Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class PageLogin {

    public PageLogin() {PageFactory.initElements(Driver.getDriver(), this);}

    @FindBy(xpath = "//input[@name='username']")
    public WebElement loginFormUsername;

    @FindBy(xpath = "//input[@id=':r1:']")
    public WebElement loginFormPassword;

    @FindBy(xpath = "//span[@id='submit']")
    public WebElement loginFormSubmitButton;

    @FindBy (xpath = "//button[contains(@aria-label,'close')]")
    public WebElement closeNotification;

    @FindBy (xpath = "//span[contains(text(), 'Announcements')]")
    public WebElement frameNotification;



}

