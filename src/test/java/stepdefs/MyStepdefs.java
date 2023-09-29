package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.PageObjects;
import readers.property.PropertyReaders;

import java.util.Map;

public class MyStepdefs extends BaseSteps{
    PageObjects pageObjects;
    @Given("user on Login page")
    public void userOnLoginPage() {
        PropertyReaders pr = PropertyReaders.read();
        pageObjects = new PageObjects();
        driver.get(pr.get("url"));
    }
    @When("user fill the login form with the following data")
    public void userFillTheLoginFormWithTheFollowingData(DataTable table) {
        Map< String, String> map =table.asMap();
        sendKeys(pageObjects.loginFormUsername, map.get("username"));
        sendKeys(pageObjects.loginFormPassword, map.get("password"));
    }

    @And("user clicks Login button")
    public void userClicksLoginButton() {
        click(pageObjects.loginFormSubmitButton);
    }

//    @Then("login should be successfull")
//    public void loginShouldBeSuccessfull() {
//        click(pageObjects.closeNotification);
//        waitForVisibilty(pageObjects.lSiteDashboard);
//    }
}