package auto01.uitests2;


import auto01.base2.BaseUiTest2;
import auto01.base2.TestModuleLazyInit;
import auto01.pages.LoginPage;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.testng.annotations.*;
import pojo_data.Employee;
import testutil.TestHelper;

import java.lang.reflect.Method;
import java.util.Locale;

@Guice(modules = TestModuleLazyInit.class)
public class AddMultiEmployeeTest extends BaseUiTest2 {

    int dataITR=2;

    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
        System.out.println("Current Date Time: " + TestHelper.currentTimeStamp());
        basePage.loginPage.NavigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        //basePage.testHelper.SleepTime(5000);
        //basePage.loginPage.appLogOut(LoginPage.Actions.LOGOUT);
    }

    @BeforeMethod
    public void setUpMethod(Method method) {
        System.out.println("Test Run: " + method.getName()+" Current Date Time: "+TestHelper.currentTimeStamp());
    }

    @AfterMethod
    public void tearMethod(Method method) {
        System.out.println("Test Finish: " + method.getName()+" Current Date Time: "+TestHelper.currentTimeStamp());
    }



    @Test(dataProvider = "EmpData",groups = {"grp2"})
    public void testEmployee_e2e(Employee emp) {

        String userName = emp.getFirstName()+emp.getEmployeeID();
        System.out.println("First Name: "+ emp.getFirstName()+"| empID: "+emp.getEmployeeID());
        basePage.loginPage.HRMLogin("Admin","admin123");
        AddNewEmployee(emp.getFirstName(), emp.getEmployeeID());
        searchEmployeeTest3(emp.getFirstName());
        basePage.testHelper.SleepTime(3000);
        logoutToApp();
        loginToApp(userName, "test123");
        logoutToApp();

    }



    @DataProvider(name = "EmpData")
    public Object[][] getUserData() {

        Faker faker = new Faker(Locale.US);
        Object[][] data = new Object[dataITR][1];

        for (int i = 0; i < dataITR; i++) {
            Employee emp = new Employee(
                    faker.name().firstName(),
                    faker.random().nextInt(2000,6000).toString()
            );
            data[i][0] = emp;
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










/*
@DataProvider(name = "loginData")
    public Object[][] provideData() {
        Faker faker = new Faker();
        int rowCount = 5; // Define how many test sets you want
        Object[][] data = new Object[rowCount][2];

        for (int i = 0; i < rowCount; i++) {
            data[i][0] = faker.internet().emailAddress(); // Generates a random email
            data[i][1] = faker.internet().password(8, 16); // Generates a password between 8-16 chars
        }

        return data;
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        System.out.println("Testing login with Username: " + username + " | Password: " + password);

    }

 */
