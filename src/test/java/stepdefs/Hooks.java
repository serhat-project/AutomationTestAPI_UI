package stepdefs;

import Driver.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import readers.property.PropertyReaders;
import stepdefs.UpdateExtentProperties;

import static io.restassured.RestAssured.baseURI;

public class Hooks {

    @Before (value = "@api")
    public void setupScenarioForLogins() {
        baseURI = PropertyReaders.read().get("baseUrl");
    }
//    public void befor(){
//        Driver.getDriver();

//    }

//    @After
//    public void after() {
//        Driver.quitDriver();
//
//   }

    @After(value = "@lang4")
    public void close(){
        Driver.quitDriver();
    }


//    @After(order = 1)
//    public void after1(Scenario scenario) {
//        if (scenario.isFailed()) {
//            BaseSteps.takeScreenShot();
//        }
//    }
}
