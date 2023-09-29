package pageObjects;

import Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLingoDutch {

    public PageLingoDutch(){
        PageFactory.initElements(Driver.getDriver(), this);}


    @FindBy(xpath = "(//div//button[contains(text(), 'Close')])[1]")
    public WebElement closeNotification;

    @FindBy(xpath = "//button[contains(.,'Login / Register')]")
    public WebElement loginButton;

    @FindBy(xpath = "(//input[@name='username'])[1]")
    public WebElement userEmail;

    @FindBy(xpath = "(//input[@name='password'])[1]")
    public WebElement userPassword;

    @FindBy(xpath = "(//span[@id='submit'])[1]")
    public WebElement signInButton;

    @FindBy(xpath = "//textarea[contains(@rows,'11')]")
    public WebElement textDutchArea;

    @FindBy(xpath = "//textarea[contains(@rows,'5')]")
    public WebElement textCorrectedArea;

    @FindBy(xpath = "(//button[@type='button'])[7]")
    public WebElement queryButton;








}
