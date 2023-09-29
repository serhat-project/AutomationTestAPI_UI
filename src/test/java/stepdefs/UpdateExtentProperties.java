package stepdefs;

import readers.property.PropertyReaders;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateExtentProperties {

    public static void updateFileName() {
        // Create a new instance of the PropertyReaders class, passing in the extent.properties file as the parameter.
        PropertyReaders propertyReaders = PropertyReaders.read("extent");

        // Get the current date and time.
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        // Create a new string variable for the filename.
        String filename = "TestAutomation-" + date + "-" + time + ".html";

        // Set the extent.reporter.html.out property in the extent.properties file to the new filename.
        propertyReaders.set("extent.reporter.html.out", "test-output/ExtentReport/" + filename);

        // Save the extent.properties file.
        propertyReaders.save();
    }
}

