package auto01.uitests1;

import auto01.base.BaseUiTest;
import auto01.base.TestModule;
import auto01.pages.LoginPage;
import com.google.inject.Inject;
import com.microsoft.playwright.Page;
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

    @Test(groups = {"ui", "smoke"})
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

    @Test(groups = {"ui"})
    public void nonGUITest() {
        System.out.println("Current Date Time: " + currentTimeStamp());
        System.out.println("Non gui tests");

        loginPage.getPage().getByText("PIM").click();
        Reporter.log("user clicks in PIM link");
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

