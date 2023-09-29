package stepdefs;

import Driver.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.PageObjects;
import readers.property.PropertyReaders;

import java.util.Objects;
import java.util.Set;

public class ReememberMeStepdefs extends BaseSteps {
    PageObjects pageObjects;


    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        pageObjects = new PageObjects();
        driver.get(PropertyReaders.read().get("url"));
    }

    @When("the user enters their username as {string} and password as {string}")
    public void theUserEntersTheirUsernameAsAndPasswordAs(String username, String password) {
        sendKeys(pageObjects.loginFormUsername, username);
        sendKeys(pageObjects.loginFormPassword, password);

    }

    @And("the user checks the remember me checkbox")
    public void theUserChecksTheRememberMeCheckbox() {
        if (!Boolean.parseBoolean(pageObjects.checkbox.getAttribute("value")))
        {click(pageObjects.checkbox);
        }
    }

    @And("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        click(pageObjects.loginFormSubmitButton);
    }

    @Then("the user should be logged in")
    public void theUserShouldBeLoggedIn() {
        waitForVisibilty(pageObjects.closeNotification);
        click(pageObjects.closeNotification);
        waitForVisibilty(pageObjects.lSiteDashboard);
    }

    @Then("the user clicks the logout button")
    public void theUserClicksTheLogoutButton() {
        click(pageObjects.logoutMenu);
        click(pageObjects.logoutButton);
        waitForVisibilty(pageObjects.loginFormSubmitButton);
    }

    @Then("the user close the current window")
    public void theUserCloseTheCurrentWindow() {
        driver.switchTo().newWindow(WindowType.TAB);

    }

    @And("the user open the login page")
    public void theUserOpenTheLoginPage() {
        driver.get(PropertyReaders.read().get("url"));
        sleep(1000);
        waitForVisibilty(pageObjects.loginFormSubmitButton);

    }

    @When("the user come on username {string} should be seen")
    public void theUserComeOnUsernameShouldBeSeen(String username) {

        WebElement usernameInput= pageObjects.loginFormUsername;
        String userValue= usernameInput.getAttribute("value") == null ? "" : usernameInput.getAttribute("value");
        Assert.assertEquals(userValue, username);

    }



//    @And("the remember me cookie should be set")
//    public void theRememberMeCookieShouldBeSet() {
//        Cookie rememberMeCookie = driver.manage().getCookieNamed("skilic@mysoly.nl");
//        System.out.println(rememberMeCookie);
//        Assert.assertNotNull(rememberMeCookie);
//    }


}
