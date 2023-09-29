package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.PageObjects;
import readers.property.PropertyReaders;

public class NegativeLoginstepdefs extends BaseSteps{
    PageObjects pageObjects;
    @Given("I am on the login page")
    public void ıAmOnTheLoginPage() {
        PropertyReaders pr = PropertyReaders.read();
        pageObjects = new PageObjects();
        driver.get(pr.get("url"));
    }

    @When("I enter invalid username {string} and invalid password {string}")
    public void ıEnterInvalidUsernameAndInvalidPassword(String username, String password) {
        sendKeys(pageObjects.loginFormUsername, username);
        sendKeys(pageObjects.loginFormPassword, password);
        click(pageObjects.loginFormSubmitButton);
    }

    @Then("I should see an error message")
    public void ıShouldSeeAnErrorMessage() {
        waitForVisibilty(pageObjects.eInvalidCredential);
    }

    @And("I should remain on the login page")
    public void ıShouldRemainOnTheLoginPage() {
        waitForVisibilty(pageObjects.loginFormSubmitButton);
    }
}
