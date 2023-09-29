package pageObjects;

import Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageAgenda {
    public PageAgenda(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(xpath = "//li/a[@href='/agenda']")
    public WebElement agendaMenu;

    @FindBy(xpath = "//span[contains(.,'New Event')]")
    public WebElement newEventButton;

    @FindBy(xpath = "//input[@id='title']")
    public WebElement titleNewEvent;

    @FindBy(xpath = "//div[@id='type']")
    public WebElement typeNewEvent;

    @FindBy(xpath = "//ul//li[@data-value='result_date']")
    public WebElement resultDate;

    @FindBy(xpath = "//button[@id='submit']")
    public WebElement saveNewEvent;

    @FindBy(xpath = "//input[@placeholder='Search']")
    public WebElement searchEvent;

    @FindBy(xpath = "(//button[@aria-label='edit'])[1]")
    public WebElement editEventButton;

    @FindBy(xpath = "(//button[@aria-label='delete'])[1]")
    public WebElement deleteEventButton;

    @FindBy(xpath = "//button[contains(.,'Agree')]")
    public WebElement deleteAgreeButton;

    @FindBy(css = "#fullWidth")
    public WebElement writeNoteArea;

}
