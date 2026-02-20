package auto01.uitests2;

import auto01.base2.BaseUiTest2;
import auto01.base2.TestModuleLazyInit;
import auto01.pages.EmployeePage;
import auto01.pages.LoginPage;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import testutil.TestHelper;


@Guice(modules = TestModuleLazyInit.class)
public class EmployeeTest extends BaseUiTest2 {

    @Inject
    LoginPage loginPage;

    @Inject
    EmployeePage empPage;

    @Inject
    TestHelper testHelper;

    @Test(priority = 1, groups = {"ui", "smoke", "grp2"})
    public void newEmployeeTest() {

        System.out.println("Current Date Time: " + TestHelper.currentTimeStamp());
        loginPage.NavigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        empPage.LoginToHRM("Admin", "admin123");
        empPage.createNewEmployee("tipsy0001", "20001");
        testHelper.SleepTime(5000);

    }
}