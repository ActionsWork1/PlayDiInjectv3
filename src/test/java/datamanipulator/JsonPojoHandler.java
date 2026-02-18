package datamanipulator;


import pojo_data.TestDataObjects;
import pojo_data.TestSuiteObjects;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonPojoHandler {

//    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    static String JSON_FILE = "src/test/resources/testdata1.json";
    // In Jackson 3.x, we use a Builder to set features like Indentation
    private static final JsonMapper mapper = JsonMapper.builder()
                                                       .enable(SerializationFeature.INDENT_OUTPUT)
                                                       .build();
    private static final File file = new File(JSON_FILE).getAbsoluteFile();


    public static void saveOrUpdateTestData(String name, String email, String password)
            throws IOException {
        TestSuiteObjects suite;

        // 1. Read existing file
        if (file.exists() && file.length() > 0) {
            suite = mapper.readValue(file, TestSuiteObjects.class);
        } else {
            suite = new TestSuiteObjects();
        }

        // 2. Logic: Update if exists, Append if new
        boolean found = false;
        for (TestDataObjects data : suite.getTestData()) {
            if (data.getName().equalsIgnoreCase(name)) {
                data.setEmail(email);
                data.setPassword(password);
                found = true;
                break;
            }
        }

        if (!found) {
            suite.getTestData().add(new TestDataObjects(name, email, password));
        }

        // 3. Write back to file
        mapper.writeValue(file, suite);
        System.out.println("File updated successfully.");
    }


    /**
     * Finds a specific test case by Name.
     * Useful for: String email = getTestDataByName("Test1").getEmail();
     */
    public static TestDataObjects getTestDataByName(String testCaseName) throws IOException {
        File file = new File(JSON_FILE);

        // Load the file into the POJO suite
        TestSuiteObjects suite = mapper.readValue(file, TestSuiteObjects.class);

        // Use Java Streams to filter for the specific name
        return suite.getTestData().stream()
                                  .filter(data -> data.getName().equalsIgnoreCase(testCaseName))
                                  .findFirst()
                                  .orElseThrow(() -> new RuntimeException("Test data not found for: " + testCaseName));
    }
}








