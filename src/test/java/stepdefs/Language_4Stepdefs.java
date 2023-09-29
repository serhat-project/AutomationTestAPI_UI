package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pageObjects.PageLanguage;
import pageObjects.PageObjects;
import readers.property.PropertyReaders;
import stepdefs.BaseSteps;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import pageObjects.PageLanguage;
import pageObjects.PageObjects;
import readers.property.PropertyReaders;
import readers.excel.ExcelReader;
import java.util.List;

public class Language_4Stepdefs extends BaseSteps {
    PageLanguage pageLanguage= new PageLanguage();
    PageObjects pageObjects = new PageObjects();
    ExcelReader reader;
    @Given("user logs in  successfully and is on the website dashboard")
    public void userLogsInSuccessfullyAndIsOnTheWebsiteDashboard() {
        driver.get(PropertyReaders.read().get("url"));
        sleep(2000);
        sendKeys(pageObjects.loginFormUsername, PropertyReaders.read().get("username"));
        sendKeys(pageObjects.loginFormPassword, PropertyReaders.read().get("password"));
        click(pageObjects.loginFormSubmitButton);
    }

    @When("user clicks on language menu")
    public void userClicksOnLanguageMenu() {
        sleep(3000);
        waitForVisibilty(pageLanguage.tableMenu);
        click(pageLanguage.languageMenu);
    }

    @When("user opens the Excel file {string}")
    public void userOpensTheExcelFile(String fileName) {
        String filePath = "src/test/resources/datafiles/" + fileName;
        reader = new ExcelReader(filePath, "Sayfa1");
    }

    @Then("the expected values for the all {string} should be loaded from the Excel file {int} and assert with actual side menus")
    public void theExpectedValuesForTheAllShouldBeLoadedFromTheExcelFileColumn_numAndAssertWithActualSideMenus(String language,int columnNum) {
        SoftAssert softAssert = new SoftAssert();
        click(By.xpath("//li[contains(.,'" + language + "')]"));
        sleep(2000);
        List<Object> expectedValues = reader.getColumn(columnNum, 1);

        for (int i = 0; i < expectedValues.size(); i++) {
            String actualString = driver.findElements(By.xpath("//li[contains(@class, 'MuiListI')]")).get(i).getText();
            String expectedString= expectedValues.get(i).toString();
            softAssert.assertEquals(actualString, expectedString, "Failed at: "+ expectedString);
        }
        softAssert.assertAll();
        sleep(2000);
//        click(pageObjects.logoutMenu);
//        click(pageObjects.logoutButton);
    }
}
