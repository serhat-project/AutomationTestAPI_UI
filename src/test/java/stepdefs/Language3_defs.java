package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import pageObjects.PageLanguage;
import pageObjects.PageObjects;
import readers.property.PropertyReaders;
import readers.excel.ExcelReader;
import java.util.List;

public class Language3_defs extends BaseSteps{
    PageLanguage pageLanguage= new PageLanguage();
    PageObjects pageObjects = new PageObjects();

    @Given("the user log in  successfully and is on the website dashboard")
    public void theUserLogInSuccessfullyAndIsOnTheWebsiteDashboard() {
        driver.get(PropertyReaders.read().get("url"));
        sleep(2000);
        sendKeys(pageObjects.loginFormUsername, PropertyReaders.read().get("username"));
        sendKeys(pageObjects.loginFormPassword, PropertyReaders.read().get("password"));
        click(pageObjects.loginFormSubmitButton);

    }

    @When("the user click on language menu")
    public void theUserClickOnLanguageMenu() {
        sleep(3000);
        waitForVisibilty(pageLanguage.tableMenu);
        click(pageLanguage.languageMenu);
    }

    @And("the user click language by one by from the language menu")
    public void theUserClickLanguageByOneByFromTheLanguageMenu() {
        SoftAssert softAssert = new SoftAssert();
        List<List<Object>> expectedSideMenuStringsFromExcel = new ExcelReader("src/test/resources/datafiles/ExcelA.xlsx", "Sayfa1").getData();
        List<WebElement> elements = driver.findElements(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 MuiTypography-noWrap css-1vq8avp']"));

        for (int i = 0; i < 8; i++) {
            click(By.xpath("(//li[@role='menuitem'])[" + (i + 1) + "]"));
            for (int k = 0; k < 11; k++ ) {
                sleep(2000);
                String actualString = driver.findElements(By.xpath("//li[contains(@class, 'MuiListI')]")).get(k).getText();
                String expectedString= expectedSideMenuStringsFromExcel.get(k).get(i).toString();
                softAssert.assertEquals(actualString,expectedString,"Failed at: "+ expectedString );
            }
            click(pageLanguage.languageMenu);
            sleep(2000);
        }

        softAssert.assertAll();
    }

    @Then("all side {int} menu should be seen in selected language")
    public void allSideMenuShouldBeSeenInSelectedLanguage(int arg0) {
    }
}
