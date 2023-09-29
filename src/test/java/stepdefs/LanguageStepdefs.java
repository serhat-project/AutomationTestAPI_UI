package stepdefs;

import Driver.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageLanguage;
import pageObjects.PageLogin;
import pageObjects.PageObjects;
import readers.property.PropertyReaders;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;


public class LanguageStepdefs extends BaseSteps {
    WebDriverWait wait= new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    PageLanguage pageLanguage= new PageLanguage();
    PageLogin pageLogin;
    PageObjects pageObjects = new PageObjects();

    @Given("I am on the portal's website and have logged in successfully")
    public void ıAmOnThePortalSWebsiteAndHaveLoggedInSuccessfully() {

        driver.get(PropertyReaders.read().get("url"));
        sleep(2000);
        sendKeys(pageObjects.loginFormUsername, PropertyReaders.read().get("username"));
        sendKeys(pageObjects.loginFormPassword, PropertyReaders.read().get("password"));
        click(pageObjects.loginFormSubmitButton);

    }

    @When("I click on the language section")
    public void ıClickOnTheLanguageSection() {
        sleep(2000);
        click(pageLanguage.languageMenu);
    }

    @And("I click {string} from the language options")
    public void ıClickFromTheLanguageOptions(String language) {
        WebElement languageOption = driver.findElement(By.xpath("//li[contains(text(), '" + language + "')]"));
        sleep(2000);
        click(languageOption);

    }

    @Then("the dashboard menu should be seen as {string}")
    public void theDashboardMenuShouldBeSeenAs(String dashboardMenu) {
        sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(pageLanguage.dashboardMenu));
        assertEquals(dashboardMenu, pageLanguage.dashboardMenu.getText());
    }


}
