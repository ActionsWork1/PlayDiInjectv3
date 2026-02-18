package datamanipulator;

import auto01.nonguitests.NoneGUIBaseTest;
import com.google.inject.Inject;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestDataProvider { //extends NoneGUIBaseTest {




    @DataProvider(name = "testData")
    public Object[][] getSampleTestData() throws IOException {
        System.out.println(NoneGUIBaseTest.getExlLocation());
        String filePath = NoneGUIBaseTest.getExlLocation()+"/data1.xlsx";
//        String filePath = "src/test/resources/data1.xlsx"; // Update path if needed
        String sheetName = "Sheet1";

        List<Map<String, String>> data = ExcelUtil.getTestData(filePath, sheetName);

        // Convert List<Map<String, String>> to Object[][]
        Object[][] dataArray = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i][0] = data.get(i); // Each test method will receive a Map
        }
        return dataArray;
    }
}