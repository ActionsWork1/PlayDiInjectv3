package auto01.uitests1;

import auto01.base.BaseUiTest;
import auto01.base.TestModule;
import auto01.pages.LoginPage;
import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.*;
import org.testng.Reporter;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static io.qameta.allure.SeverityLevel.CRITICAL;


@Guice(modules = TestModule.class)
public class LoginTest1 extends BaseUiTest {

    @Inject
    LoginPage loginPage;

    @Test(priority = 1,groups = {"ui", "smoke"})
    @Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    @Severity(CRITICAL)
    @Owner("Awana01")
    @Link(name = "Website", url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")
    @Issue("Issue-1")
    @TmsLink("TMS-456")
    @Epic("Web interface")
    @Feature("Essential features")
    @Story("Login Authentication")
    @Step("Login as admin")
    public void loginSmokeTest() {
        // Print the result
        System.out.println("Current Date Time: " + currentTimeStamp());

        loginPage.NavigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.HRMLogin("Admin", "admin123");


    }

    @Test(priority = 2, groups = {"ui"})
    public void EmployeePageTest() {
        System.out.println("Current Date Time: " + currentTimeStamp());
        page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("PIM")).click();
        try { Thread.sleep(5000); }
        catch (InterruptedException e) { throw new RuntimeException(e); }
    }


    public String currentTimeStamp(){
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH);
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;

    }
    public String DataStamp(){
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH).toString();
    }




}

