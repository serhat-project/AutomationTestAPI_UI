package stepdefs;

import Driver.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import pageObjects.PageLingoDutch;
import readers.excel.ExcelReader;
import readers.property.PropertyReaders;
import stepdefs.BaseSteps;


import java.time.Duration;
import java.util.List;

public class LingoStepdefs extends BaseSteps {
    PageLingoDutch pageLingoDutch = new PageLingoDutch();
    ExcelReader reader;
    SoftAssert softAssert = new SoftAssert();

    @Given("I am logged in to {string} with valid credentials")
    public void ıAmLoggedInToWithValidCredentials(String url) {
        driver.get(url);
        sleep(3000);
        click(pageLingoDutch.loginButton);
        sleep(2000);
        sendKeys(pageLingoDutch.userEmail, PropertyReaders.read().get("lingo_email"));
        sendKeys(pageLingoDutch.userPassword, PropertyReaders.read().get("lingo_password"));
        click(pageLingoDutch.signInButton);
    }
    @And("I have an excel file with a list of Dutch sentences")
    public void ıHaveAnExcelFileWithAListOfDutchSentences() {
        String filePath = "src/test/resources/datafiles/lingodutchnl_nlp_test.xlsx";
        reader = new ExcelReader(filePath, "Sheet1");
    }

    @Then("I paste each sentence from the excel input column into text area the corrected sentence should be the same as the result in the excel output")
    public void ıPasteEachSentenceFromTheExcelInputColumnIntoTextAreaTheCorrectedSentenceShouldBeTheSameAsTheResultInTheExcelOutput() {
        List<Object> inputSentences = reader.getColumn("input", 1);
        List<Object> outputSentences = reader.getColumn("output",1);

        for (int i = 0; i < inputSentences.size(); i++) {
            sendKeys(pageLingoDutch.textDutchArea, (String) inputSentences.get(i));
            click(pageLingoDutch.queryButton);
            sleep(3000);
            softAssert.assertEquals(pageLingoDutch.textCorrectedArea.getText(),((String) outputSentences.get(i)));
            sleep(1000);
            pageLingoDutch.textDutchArea.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
            sleep(1000);

        }
        softAssert.assertAll();
    }

}
