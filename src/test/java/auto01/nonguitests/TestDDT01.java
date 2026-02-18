package auto01.nonguitests;

import datamanipulator.TestDataProvider;
import org.testng.annotations.Test;
import java.util.Map;

public class TestDDT01 {

    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    public void testMethod(Map<String, String> data) {

        System.out.println("Test Started at: "+NoneGUIBaseTest.currentTimeStamp());

        String testID = data.get("TestID");
        String field1 = data.get("Field1");
        String field2 = data.get("Field2");
        String field3 = data.get("Field3");

        System.out.println("Running Test: " + testID);
        System.out.println("Field1: " + field1);
        System.out.println("Field2: " + field2);
        System.out.println("Field3: " + field3);

        // Add your test logic here
    }
}
