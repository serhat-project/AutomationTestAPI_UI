package Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import readers.property.PropertyReaders;

public class DriverFactory {

    public static WebDriver createEdge() {
//        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        PropertyReaders pr = PropertyReaders.read();
        for (String str : pr.get("edge.options").split(",")) {
            options.addArguments(str.trim());
        }

        return new EdgeDriver(options);
    }

    public static WebDriver createFireFox() {
//        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        PropertyReaders pr = PropertyReaders.read();
        for (String str : pr.get("firefox.options").split(",")) {
            options.addArguments(str.trim());
        }
        return new FirefoxDriver(options);
    }

    public static WebDriver createChrome() {
//        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        PropertyReaders pr = PropertyReaders.read();

        for (String str : pr.get("chrome.options").split(",")) {
            options.addArguments(str.trim());
        }
        options.addArguments("--lang=en");
        options.addArguments("--headless");
        return new ChromeDriver(options);
    }

    public static WebDriver createSafari(){
        if (!System.getProperty("os.name").toLowerCase().contains("mac"))

            throw new WebDriverException("Your OS doesn't support Safari");

//        WebDriverManager.safaridriver().setup();
        SafariOptions options = new SafariOptions();
        PropertyReaders pr = PropertyReaders.read();
        options.setCapability("safari.cleanSession", true);
        options.setAutomaticInspection(true);
        options.getUseTechnologyPreview();
        return new SafariDriver(options);
    }


}
