package auto01.uitests2;


import auto01.base2.BaseUiTest2;
import auto01.base2.TestModuleLazyInit;
import auto01.pages.LoginPage;
import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.*;
import testutil.TestHelper;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//@Guice(modules = PlaywrightModule.class)

@Guice(modules = TestModuleLazyInit.class)
public class LoginTest2 extends BaseUiTest2 {

    @Inject
    LoginPage loginPage;

    @Inject
    TestHelper testHelper;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        // Print the result
        System.out.println("Current Date Time: " + TestHelper.currentTimeStamp());
        loginPage.NavigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        loginPage.appLogOut(LoginPage.Actions.LOGOUT);
        page.close();
    }

    @BeforeMethod
    public void setUpMethod(Method method) {
        System.out.println("Test Run: " + method.getName()+" Current Date Time: "+TestHelper.currentTimeStamp());
    }

    @AfterMethod
    public void tearMethod(Method method) {
        System.out.println("Test Finish: " + method.getName()+" Current Date Time: "+TestHelper.currentTimeStamp());
    }

    @Test(priority = 1, groups = {"ui", "smoke","grp2"})
    public void loginTest() {
        loginPage.HRMLogin("Admin", "admin123");
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"},groups = {"ui","grp2"})
    public void EmployeePageLinkTest() {
        page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("PIM")).click(new Locator.ClickOptions().setTimeout(10000));

    }

    @Test(priority = 3,dependsOnMethods = {"loginTest"}, groups = {"ui","grp2"})
    public void AdminPageLinkTest() {
        page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Admin")).click(new Locator.ClickOptions().setTimeout(10000));

    }

}

