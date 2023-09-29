package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import pageObjects.PageLanguage;
import pageObjects.PageObjects;
import readers.property.PropertyReaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class Language_2_defs extends BaseSteps{
    PageLanguage pageLanguage= new PageLanguage();
    PageObjects pageObjects = new PageObjects();

     List<String> expectedDashboardMenus = Arrays.asList(
            "My Dashboard",
            "Gösterge Tablom",
            "Mijn dashboard",
            "Mein Dashboard",
            "Mon tableau de bord",
            "Mi panel de control",
            "Mój pulpit nawigacyjny",
            "Моя інформаційна панель"
    );

    List<String> expectedDictionaryMenus = Arrays.asList(
            "My Dictionary",
            "Benim Sözlüğüm",
            "Mijn Woordenboek",
            "Mein Wörterbuch",
            "Mon Dictionnaire",
            "Mi Diccionario",
            "Moje Słowniki",
            "Мій Cловник"
    );


    @Given("the user logs in  successfully and is on the website dashboard")
    public void theUserLogsInSuccessfullyAndIsOnTheWebsiteDashboard() {
        driver.get(PropertyReaders.read().get("url"));
        sleep(2000);
        sendKeys(pageObjects.loginFormUsername, PropertyReaders.read().get("username"));
        sendKeys(pageObjects.loginFormPassword, PropertyReaders.read().get("password"));
        click(pageObjects.loginFormSubmitButton);

    }

    @When("the user clicks on language menu")
    public void theUserClicksOnLanguageMenu() {
        sleep(3000);
        click(pageLanguage.languageMenu);
    }

    @And("the user clicks language by one by from the language menu")
    public void theUserClicksLanguageByOneByFromTheLanguageMenu() {
        SoftAssert softAssert = new SoftAssert();

        for (int i = 1; i <= expectedDashboardMenus.size(); i++) {
            click(By.xpath("(//li[@role='menuitem'])[" + i + "]"));
            sleep(1000);
            waitForVisibilty(pageLanguage.dashboardMenu);

            click(pageLanguage.languageMenu);
            sleep(1000);

            String expectedDashboardMenu = expectedDashboardMenus.get(i-1);
            String expectedDictionaryMenu = expectedDictionaryMenus.get(i-1);

            String actualDashboardMenu = pageLanguage.dashboardMenu.getText();
            String actualDictionaryMenu = pageLanguage.dictionaryMenu.getText();

            softAssert.assertEquals(actualDashboardMenu, expectedDashboardMenu, "error");
            softAssert.assertEquals(actualDictionaryMenu, expectedDictionaryMenu, "error");
        }

        softAssert.assertAll();
    }

    @Then("the dashboard menu should be seen as selected language")
    public void theDashboardMenuShouldBeSeenAsSelectedLanguage() {

    }
}
