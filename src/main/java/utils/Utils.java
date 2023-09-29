package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    // static val
    public static final String ENTER = "\n";





    /**
     * bir elementin resmini kaydetmek icin kullanilir
     *
     * @param fileName filename of the screenshot of an element
     */
    public static void takeScreenShotOfElement(WebElement element, String fileName) {
        fileName = fileName + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
        String filePath = "test-output/screenshots/" + fileName + ".png";
        File srcFile = element.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * alinan screenshot'i byte[] olarak return eder
     *
     * @return screenshot in byte[]
     */


    /**
     * create directory if not exist
     *
     * @param fileWithPath
     */
    public static void createDirectory(String fileWithPath) {
        String[] pathArray = fileWithPath.split("/");
        String path = "";
        if (pathArray.length > 0) {
            for (int i = 0; i < pathArray.length - 1; i++) {
                path += pathArray[i] + "/";
            }
        }
        File theDir = new File(path);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

    /**
     * file exist mi
     * @param fileWithPath file with path
     * @return boolean
     */
    public static boolean isFileExist(String fileWithPath){
        File f = new File(fileWithPath);
        return f.exists() && !f.isDirectory();
    }

    /**
     * java sleep
     *
     * @param millis milliseconds
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * bu method test/resources/Elements.json dosyasinda tanimli elementleri bulur ve
     * locator olarak return eder
     *
     * @param main ana baslik
     * @param sub  alt baslik
     * @return By
     */
    public static By getBy(String main, String sub) {
        try {
            String jsonFile = "src/test/resources/datafiles/Elements.json";

            JSONObject object = null;
            object = (JSONObject) JSONValue.parse(new FileReader(jsonFile));

            JSONObject mainNode = (JSONObject) object.get(main);
            JSONObject subNode = (JSONObject) mainNode.get(sub);

            String type = subNode.get("type").toString();
            String locator = subNode.get("locator").toString();

            switch (type.toLowerCase()) {
                case "xpath":
                    return By.xpath(locator);
                case "css":
                    return By.cssSelector(locator);
                case "id":
                    return By.id(locator);
                case "tagname":
                    return By.tagName(locator);
                case "classname":
                    return By.className(locator);
                case "linktext":
                    return By.linkText(locator);
                case "partiallinktext":
                    return By.partialLinkText(locator);
                default:
                    return null;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elements.json dosya formatinda kayitli veri okur
     * @param main ana baslik
     * @param key alt baslik
     * @return
     */
    public static String getValue(String main, String key) {
        try {
            String jsonFile = "src/test/resources/datafiles/Elements.json";

            JSONObject object = null;
            object = (JSONObject) JSONValue.parse(new FileReader(jsonFile));

            JSONObject mainNode = (JSONObject) object.get(main);
            return mainNode.get(key).toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



    /**
     * özel bir excel dosyasinda yazili gherkin satirlarindan feature file olusturma.
     * @param excelFile gherkin olan excel dosyasi
     * @param featureFile olusturulacak feature dosya adi
     */
    public static void createFeatureFileFromExcel(String excelFile, String featureFile){
        try {
            FileWriter fileWriter = new FileWriter(featureFile);

            // java excel'i okudu
            FileInputStream fileInputStream = new FileInputStream(excelFile);

            // Apache poi excel'i workbook olarak tanidi
            Workbook workbook = WorkbookFactory.create(fileInputStream);

            // ilk sayfa okundu
            Sheet sheet = workbook.getSheetAt(0);

            // row sayisi alindi
            int rowNums = sheet.getPhysicalNumberOfRows();

            String featureLine = "Feature: " + sheet.getRow(1).getCell(0).toString();
            fileWriter.write(featureLine + ENTER);

            String scenarioLine = sheet.getRow(1).getCell(1).toString();
            scenarioLine += ":";
            scenarioLine += sheet.getRow(1).getCell(2).toString();

            fileWriter.write(scenarioLine + ENTER);

            for (int i = 1; i < rowNums; i++) {
                Cell cell = sheet.getRow(i).getCell(3);
                String str = cell == null ? "" : cell.toString();
                fileWriter.write(str + ENTER);
            }
            workbook.close();
            fileInputStream.close();
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class UtilsBaseApi {

        public static final String BASE_URL = "https://api.tijd4.nl";
        public static String ACCESS_TOKEN = null;

        public static void setAccessToken(String accessToken) {
            ACCESS_TOKEN = accessToken;
        }

        public static String getAccessToken() {
            return ACCESS_TOKEN;
        }
    }




}
