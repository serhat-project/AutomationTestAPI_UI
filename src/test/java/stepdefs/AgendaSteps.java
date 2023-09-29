package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.PageAgenda;
import pageObjects.PageObjects;
import readers.property.PropertyReaders;
import com.github.javafaker.Faker;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AgendaSteps extends BaseSteps{
    PageObjects pageObjects = new PageObjects();
    PageAgenda pageAgenda = new PageAgenda();
    LocalDate date = LocalDate.now();
    Faker faker = new Faker();
    String title2 = faker.book().title();
    String title3= faker.book().title();
    String title4= faker.book().title();
    @Given("the admin logs in with valid credentials")
    public void theAdminLogsInWithValidCredentials() {
        driver.get(PropertyReaders.read().get("url"));
        sleep(2000);
        sendKeys(pageObjects.loginFormUsername, PropertyReaders.read().get("username"));
        sendKeys(pageObjects.loginFormPassword, PropertyReaders.read().get("password"));
        click(pageObjects.loginFormSubmitButton);

    }

    @When("the admin clicks on Agenda")
    public void theAdminClicksOnAgenda() {
        waitForVisibilty(pageAgenda.agendaMenu);
        click(pageAgenda.agendaMenu);
    }

    @And("the admin adds a new event on Agenda")
    public void theAdminAddsANewEventOnAgenda() {


        waitForVisibilty(pageAgenda.newEventButton);
        click(pageAgenda.newEventButton);
        sendKeys(pageAgenda.titleNewEvent,title2 );
        click(pageAgenda.typeNewEvent);
        click(pageAgenda.resultDate);
        click(pageAgenda.saveNewEvent);
        sleep(1000);
    }

    @And("the admin searches and asserts for the newly created event")
    public void theAdminSearchesAndAssertsForTheNewlyCreatedEvent() {
        waitForVisibilty(pageAgenda.newEventButton);
        sleep(2000);
        sendKeys(pageAgenda.searchEvent, title2);
        sleep(1000);
        WebElement row = driver.findElement(By.xpath("(//div//div[@role='row']//p)[3]"));
        Assert.assertEquals(title2, row.getText());
        sleep(1000);
    }
    @And("the admin edit event and again search and assert editted event")
    public void theAdminEditEventAndAgainSearchAndAssertEdittedEvent() {
        pageAgenda.searchEvent.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        click(pageAgenda.editEventButton);
        pageAgenda.titleNewEvent.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        sendKeys(pageAgenda.titleNewEvent, title3);
        click(pageAgenda.saveNewEvent);
        waitForVisibilty(pageAgenda.newEventButton);
        sendKeys(pageAgenda.searchEvent, title3);
        sleep(1000);
        WebElement row = driver.findElement(By.xpath("(//div//div[@role='row']//p)[3]"));
        Assert.assertEquals(title3, row.getText());
        sleep(2000);

    }

    @And("the admin logs out")
    public void theAdminLogsOut() {
        click(pageObjects.logoutMenu);
        click(pageObjects.logoutButton);
        sleep(2000);

    }

    @Then("the User logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        driver.get(PropertyReaders.read().get("url"));
        sleep(2000);
        sendKeys(pageObjects.loginFormUsername, PropertyReaders.read().get("student_username"));
        sendKeys(pageObjects.loginFormPassword, PropertyReaders.read().get("student_password"));
        click(pageObjects.loginFormSubmitButton);
        waitForVisibilty(pageObjects.lSiteDashboard);
        sleep(2000);
    }

    @And("the User asserts that the new event is visible on the calendar")
    public void theUserAssertsThatTheNewEventIsVisibleOnTheCalendar() {
        waitForVisibilty(pageObjects.lSiteDashboard);
        sleep(2000);
        List<WebElement> calendarEventList = driver.findElements(By.xpath("//tbody[@role='presentation']/tr//h6"));
        boolean flag = false;
        for (WebElement event : calendarEventList) {
            if (event.getText().equalsIgnoreCase(title3)) {
                flag = true;
                break;
            }
        }
        Assert.assertTrue(flag);
        sleep(2000);
    }



    @And("the User logs out")
    public void theUserLogsOut() {
        click(pageObjects.logoutMenu);
        click(pageObjects.logoutButton);
        waitForVisibilty(pageObjects.loginFormSubmitButton);
    }

    @Then("the admin logs in again and go on Agenda")
    public void theAdminLogsInAgainAndGoOnAgenda() {
        driver.get(PropertyReaders.read().get("url"));
        sleep(2000);
        sendKeys(pageObjects.loginFormUsername, PropertyReaders.read().get("username"));
        sendKeys(pageObjects.loginFormPassword, PropertyReaders.read().get("password"));
        click(pageObjects.loginFormSubmitButton);
        waitForVisibilty(pageAgenda.agendaMenu);
        click(pageAgenda.agendaMenu);

    }

    @And("the admin deletes last added events")
    public void theAdminDeletesLastAddedEvents() {
        waitForVisibilty(pageAgenda.newEventButton);
        sleep(2000);
        sendKeys(pageAgenda.searchEvent, title3);
        sleep(1000);
        click(pageAgenda.deleteEventButton);
        sleep(1000);
        click(pageAgenda.deleteAgreeButton);
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath("//div//div[@role='row']//p"), 1));


    }

    @When("the User adds an event on Calender")
    public void theUserAddsAnEventOnCalender() {
        sleep(2000);
        click(selectAndWriteDateOnCalendar(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        click(pageAgenda.writeNoteArea);
        sendKeys(pageAgenda.writeNoteArea,title4);
        click(pageAgenda.saveNewEvent);

    }

    @And("the admin searches for user event on Agenda and assert not seen by admin")
    public void theAdminSearchesForUserEventOnAgendaAndAssertNotSeenByAdmin() {
        waitForVisibilty(pageAgenda.newEventButton);
        sendKeys(pageAgenda.searchEvent, title4);
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath("//div//div[@role='row']//p"), 1));

    }

}

