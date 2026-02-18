package auto01.uitests2;


import auto01.base2.BaseUiTest2;
import auto01.base2.TestModuleLazyInit;
import auto01.pages.LoginPage;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//@Guice(modules = PlaywrightModule.class)

@Guice(modules = TestModuleLazyInit.class)
public class LoginTest2 extends BaseUiTest2 {

    @Inject
    LoginPage loginPage;


    @Test(groups = {"ui", "smoke"})
    public void loginSmokeTest() {
        // Print the result
        System.out.println("Current Date Time: " + currentTimeStamp());
        loginPage.NavigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.HRMLogin("Admin", "admin123");
    }

    @Test(groups = {"ui"})
    public void EmployeePageTest() {
        System.out.println("Current Date Time: " + currentTimeStamp());
        page.getByText("PIM").click();
        try { Thread.sleep(5000); }
        catch (InterruptedException e) { throw new RuntimeException(e); }


    }

    @Test(groups = {"ui"})
    public void AdminPageTest() {
        System.out.println("Current Date Time: " + currentTimeStamp());

        page.getByText("Admin").click();
        try { Thread.sleep(5000); }
        catch (InterruptedException e) { throw new RuntimeException(e); }


    }


    public String currentTimeStamp(){
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define the desired format pattern with "hh" for 12-hour format and "a" for AM/PM marker
        // Using Locale.ENGLISH ensures the "AM/PM" string is consistently English
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH);

        // Format the current date and time
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;

    }




}

