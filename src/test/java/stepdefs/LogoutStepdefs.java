package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.PageObjects;
import readers.property.PropertyReaders;

public class LogoutStepdefs extends BaseSteps{
    PageObjects pageObjects;
    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        PropertyReaders pr = PropertyReaders.read();
        pageObjects = new PageObjects();
        driver.get(pr.get("url"));
    }

    @When("User enters valid username {string} and valid password {string}")
    public void userEntersValidUsernameAndValidPassword(String username, String password) {
        sendKeys(pageObjects.loginFormUsername, username);
        sendKeys(pageObjects.loginFormPassword, password);
        click(pageObjects.loginFormSubmitButton);
    }

    @And("User logs in")
    public void userLogsIn() {
        waitForVisibilty(pageObjects.frameNotification);
        sleep(1000);
    }

    @And("User closes the notification")
    public void userClosesTheNotification() {
        click(pageObjects.closeNotification);
    }

    @When("User selects the logout menu")
    public void userSelectsTheLogoutMenu() {
        click(pageObjects.logoutMenu);
    }

    @And("User clicks on the logout button")
    public void userClicksOnTheLogoutButton() {
        click(pageObjects.logoutButton);
    }

    @Then("User should be logged out")
    public void userShouldBeLoggedOut() {
        waitForVisibilty(pageObjects.loginFormSubmitButton);
    }
}
