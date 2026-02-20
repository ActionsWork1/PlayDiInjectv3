package auto01.uitests2;

import auto01.base2.BaseUiTest2;
import auto01.base2.TestModuleLazyInit;
import auto01.pages.EmployeePage;
import auto01.pages.LoginPage;
import auto01.pages.SidePanel;
import com.github.javafaker.Faker;
import com.google.inject.Inject;

import io.qameta.allure.Step;
import org.testng.annotations.*;
import pojo_data.Employee;
import pojo_data.User;
import testutil.TestHelper;
import testutil.WebTable;

import java.util.Locale;

@Guice(modules = TestModuleLazyInit.class)
public class AddMultiEmployeeTest2 extends BaseUiTest2 {

    int dataITR=2;

    @BeforeTest()
//    @BeforeTest(alwaysRun = true)
    public void setUp() {
        System.out.println("Current Date Time: " + TestHelper.currentTimeStamp());
        basePage.loginPage.NavigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        //basePage.empPage.LoginToHRM("Admin", "admin123");
    }


    @Test(groups={"employee_e2e"},dataProvider = "EmployeeData")
    public void testRegister(String firstName, String empID) {
        String userName = firstName+empID;
        System.out.println("First Name: "+ firstName+"| empID: "+empID);
        loginToApp("Admin", "admin123");
        AddNewEmployee(firstName, empID);
        searchEmployeeTest3(firstName);
        basePage.testHelper.SleepTime(3000);
        logoutToApp();
        loginToApp(userName, "test123");
        logoutToApp();



    }

    @DataProvider(name = "EmployeeData", parallel = true)
    public Object[][] provideData() {
        Faker faker = new Faker();
        int rowCount = 2; // Define how many test sets you want
        Object[][] data = new Object[rowCount][2];

        for (int i = 0; i < rowCount; i++) {
            data[i][0] = faker.name().firstName();
            data[i][1] = faker.random().nextInt(2000,6000).toString();
        }
        return data;
    }


    @Step("user login to app")
    public void loginToApp(String userName, String pwd) {
        basePage.empPage.LoginToHRM(userName, pwd);
    }

    @Step("user logout from app")
    public void logoutToApp() {
        basePage.loginPage.appLogOut(LoginPage.Actions.LOGOUT);
    }

    @Step("Add New Employee")
    public void AddNewEmployee(String userName, String empID) {
        basePage.empPage.createNewEmployee(userName,empID);
    }


    @Step("search new employee by its name")
    public void searchEmployeeTest3(String userName) {

        System.out.println("Current Date Time: " + TestHelper.currentTimeStamp());

        basePage.sidePanel.clickSideLink("PIM");
        basePage.webTable.WaitForTableRows();

        basePage.empPage._EmpName_TXT.click();
        basePage.empPage._EmpName_TXT.fill(userName);
        basePage.empPage._EmpSearch_BTN.click();
        basePage.webTable.WaitForTableRows();

        basePage.testHelper.SleepTime(3000);


        int x2 = basePage.webTable.searchDataInColoumn("First (& Middle) Name", userName);
        System.out.println("Row Num: " + x2);

        basePage.webTable.performActionOnCell(x2,0);
        basePage.testHelper.SleepTime(5000);
        basePage.testHelper.AttachScreenShot();

    }

}