package stepdefs;

import Driver.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseSteps {

    public WebDriver driver;
    WebDriverWait wait;

    public BaseSteps() {
        driver = Driver.getDriver();
        wait = Driver.waits.get();
    }

   public void click(WebElement element){
        wait.until(d1->{
            try {
                element.click();
                return true;
            }catch (Exception e){
                try {
                    new Actions(d1).moveToElement(element).click().perform();
                    return true;
                }catch (Exception e2){
                    try {
                        ((JavascriptExecutor)d1).executeScript("arguments[0].click",element);
                        return true;
                    }catch (Exception e3){
                        return false;
                    }
                }
            }
        });

   }
   public void click(By locator){
       WebElement until = wait.until(ExpectedConditions.elementToBeClickable(locator));
       click(until);
   }
   public void sendKeys(WebElement element,String text){
        wait.until(d1->{
            try {
                element.clear();
                element.sendKeys(text);
                return true;
            }catch (Exception e){
                try {
                    element.clear();
                    new Actions(d1).moveToElement(element).sendKeys(text).perform();
                    return true;

            }catch (Exception e2) {
                    try {
                        element.clear();
                        ((JavascriptExecutor) d1).executeScript("arguments[0].value='" + text + "'", element);
                        return true;
                    } catch (Exception e3) {
                        return false;
                    }
                }
            }
        });
   }
   public void sendKeys(By locator,String text){
       WebElement until = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
       sendKeys(until,text);
   }

   public void waitForVisibilty(By locator){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
   }
   public void waitForVisibilty(WebElement element){
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
   }

   public static void takeScreenShot(String filename){
        filename+="_"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
        String patht="test-output/screenshots/"+filename+".png";
       File sourch = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
       File target=new File(patht);
       try {
           FileUtils.copyFile(sourch,target);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }
   public static void takeScreenShot(){
        takeScreenShot("screenshot");
   }

   public static void takeScreenShotOfElement(WebElement element,String filename){
        String path="test-output/screenshot/"+filename+".png";
        filename+="_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
        File srcFile=element.getScreenshotAs(OutputType.FILE);
        File target=new File(path);
       try {
           FileUtils.copyFile(srcFile,target);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

   }
   public void sleep(long milis){
       try {
           Thread.sleep(milis);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
   }

    /**
     *
     * @param locator % ile yazilan locotor i stringe at
     * @param text & yarine ne yazmak istiyorsak
     * @return
     */
   public static By xpath(String locator,String text){
       //String TOP_MENU = "//*[@id='header-wrap']//a[contains(.,'%s')]";
        return By.xpath(String.format(locator,text));
   }

    public String assertColor(WebElement element){
       String befor=element.getCssValue("background");
        Color color = Color.fromString(befor);
        return color.asHex();
    }
    public void hover(WebElement element){
       new Actions(driver)
               .moveToElement(element).perform();
    }
    public WebElement menuLocator(String text){
        WebElement element = driver.findElement(By.xpath("//nav[@class='primary-menu']/ul//li/a/div[contains(text(),'" + text + "')]//ancestor::a"));
        return element;
    }

    public WebElement selectAndWriteDateOnCalendar(String date){
        WebElement dateLocation = driver.findElement(By.xpath("//td[@role='gridcell' and @data-date='" + date + "']"));
        return dateLocation;
    }

}
