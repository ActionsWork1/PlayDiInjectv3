package auto01.uitests2;


import auto01.base2.BaseUiTest2;
import auto01.base2.TestModuleLazyInit;
import auto01.pages.EmployeePage;
import auto01.pages.LoginPage;
import auto01.pages.SidePanel;
import com.github.javafaker.Faker;
import com.google.inject.Inject;
import org.testng.annotations.*;
import pojo_data.Employee;
import pojo_data.User;
import testutil.TestHelper;
import testutil.WebTable;

import java.util.Locale;

@Guice(modules = TestModuleLazyInit.class)
public class AddMultiEmployeeTest extends BaseUiTest2 {

    int dataITR=2;

    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
        System.out.println("Current Date Time: " + TestHelper.currentTimeStamp());
        basePage.loginPage.NavigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        basePage.empPage.LoginToHRM("Admin", "admin123");
    }


    @Test(dataProvider = "EmpData")
    public void testRegister(Employee emp) {

        System.out.println("First Name: "+ emp.getFirstName()+"| Last Name: "+ emp.getLastName()+"| EmpID: "+ emp.getEmployeeID());
        basePage.empPage.createNewEmployee("hank"+emp.getEmployeeID(),emp.getEmployeeID());

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        basePage.testHelper.SleepTime(5000);
        basePage.loginPage.appLogOut(LoginPage.Actions.LOGOUT);
    }

    @DataProvider(name = "EmpData")
    public Object[][] getUserData() {

        Faker faker = new Faker(Locale.US);
        Object[][] data = new Object[dataITR][1];

        for (int i = 0; i < dataITR; i++) {
            Employee emp = new Employee(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.random().nextInt(2000,6000).toString()
            );
            data[i][0] = emp;
        }
        return data;
    }


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











}