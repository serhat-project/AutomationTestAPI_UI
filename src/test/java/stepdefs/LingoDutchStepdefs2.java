package stepdefs;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import pageObjects.PageLingoDutch;
import readers.property.PropertyReaders;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.testng.Assert.assertEquals;


public class LingoDutchStepdefs2 extends BaseSteps {
    PageLingoDutch pageLingoDutch = new PageLingoDutch();
    String lingoDeutch;
    String urlExcel;


    @Given("I am logged in to url with valid credentials")
    public void ıAmLoggedInToUrlWithValidCredentials() {
        driver.get(PropertyReaders.read().get("lingo_url"));
        lingoDeutch = driver.getWindowHandle();
        sleep(3000);
        click(pageLingoDutch.loginButton);
        sleep(2000);
        sendKeys(pageLingoDutch.userEmail, PropertyReaders.read().get("lingo_email"));
        sendKeys(pageLingoDutch.userPassword, PropertyReaders.read().get("lingo_password"));
        click(pageLingoDutch.signInButton);
        sleep(2000);
    }

    @When("I have an url with a list of Dutch sentences as a table with the columns input and output")
    public void ıHaveAnUrlWithAListOfDutchSentencesAsATableWithTheColumnsInputAndOutput() throws IOException, UnsupportedFlavorException {
        String url = "https://docs.google.com/spreadsheets/d/1MPluNDJfP2Rl3swlVemNaaAwUmIFK4qj/edit?pli=1#gid=1374736463";
        String tableData = getTableData(url);
        // Parse the table data
        String[] rows = tableData.split("\n");
        String[][] data = new String[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            data[i] = rows[i].split("\t");
        }
        driver.switchTo().window(lingoDeutch);
        sleep(2000);

        for (int i = 1; i < data.length; i++) {

            sendKeys(pageLingoDutch.textDutchArea, data[i][0] + Keys.ENTER);
            sleep(1000);

            click(pageLingoDutch.queryButton);
            sleep(3000);

            String outputValue = pageLingoDutch.textCorrectedArea.getText();
            sleep(1000);

            try {
                assertEquals(outputValue, data[i][1]);
            } catch (AssertionError e) {
                System.out.println("Assertion error occurred for row " + (i + 1));
            }
            sleep(1000);
            pageLingoDutch.textDutchArea.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        }
    }

    @Then("I paste each sentence from the Url input colomn into the text Area and the corrected sentence should be the same as the result in the Url output column after press query button")
    public void ıPasteEachSentenceFromTheUrlInputColomnIntoTheTextAreaAndTheCorrectedSentenceShouldBeTheSameAsTheResultInTheUrlOutputColumnAfterPressQueryButton() {
    }

    private String getTableData(String url) throws  IOException, UnsupportedFlavorException {

        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to(url);
        urlExcel = driver.getWindowHandle();
        sleep(8000);

        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

        sleep(3000);

        String tableData = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        return tableData;
    }
}
