package auto01.nonguitests;


import datamanipulator.JsonDataDriven;
import org.testng.annotations.Test;
import pojo_data.TestDataObjects;

import java.io.IOException;

import static datamanipulator.JsonPojoHandler.saveOrUpdateTestData;


public class CRUDJsonTest {


    @Test
    public void testCRUDJson() throws IOException {
        System.out.println("Test Started at: "+NoneGUIBaseTest.currentTimeStamp());
        saveOrUpdateTestData("Test51", "updated_test2@example.com", "111");
        saveOrUpdateTestData("Test0", "new_user@example.com", "222");
    }


    @Test(dataProvider = "userDataProvider", dataProviderClass = JsonDataDriven.class)
    public void loginTest(TestDataObjects user) {
        System.out.println("Test Started at: "+NoneGUIBaseTest.currentTimeStamp());
        System.out.println("--- Starting Test for: " + user.getName() + " ---");

        System.out.println("Testing with Email: " + user.getName());
        System.out.println("Testing with Email: " + user.getPassword());
        System.out.println("Testing with Email: " + user.getEmail());
    }




}
