package Driver;

import org.openqa.selenium.WebDriver;
import static Driver.DriverFactory.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {

    public static ThreadLocal<WebDriver> drivers=new ThreadLocal<>();
    public static ThreadLocal<WebDriverWait> waits=new ThreadLocal<>();

    public static WebDriver getDriver(Browser browser){
        if (drivers.get()==null){
            switch (browser){
                case FIREFOX -> drivers.set(createFireFox());
                case EDGE -> drivers.set(createEdge());
                case SAFARI -> drivers.set(createSafari());
                default -> drivers.set(createChrome());

            }
        }
        waits.set(new WebDriverWait(drivers.get(), Duration.ofSeconds(10)));
        return drivers.get();
    }
    public static WebDriver getDriver(){
        return getDriver(Browser.CHOREME);
    }
    public static void quitDriver(){
        if (drivers.get() != null) {
            drivers.get().quit();
            drivers.set(null);
            waits.set(null);
        }
    }

}
