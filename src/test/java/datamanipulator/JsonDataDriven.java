package datamanipulator;


import org.testng.annotations.DataProvider;
import pojo_data.TestDataObjects;
import pojo_data.TestSuiteObjects;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;



public class JsonDataDriven {

    private static final JsonMapper mapper = JsonMapper.builder()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .build();

    @DataProvider(name = "userDataProvider")
    public static Object[][] getUserData() throws IOException {
        File file = new File("./src/test/resources/testdata1.json");

        // 1. Read the JSON into our POJO

        TestSuiteObjects suite = mapper.readValue(file, TestSuiteObjects.class);
        List<TestDataObjects> list = suite.getTestData();

        // 2. Convert List<TestData> to Object[][]
        Object[][] data = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i); // Each row contains one TestData object
        }
        return data;
    }
}
