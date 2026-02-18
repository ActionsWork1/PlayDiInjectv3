package auto01.uitests2;


import auto01.base2.BaseUiTest2;
import auto01.base2.TestModuleLazyInit;
import auto01.pages.LoginPage;
import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
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


    @Test(priority = 1,groups = {"ui", "smoke","grp2"})
    public void loginSmokeTest() {
        // Print the result
        System.out.println("Current Date Time: " + currentTimeStamp());
        loginPage.NavigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.HRMLogin("Admin", "admin123");
    }

    @Test(priority = 2, groups = {"ui","grp2"})
    public void EmployeePageTest() {
        System.out.println("Current Date Time: " + currentTimeStamp());
        page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("PIM")).click();
        try { Thread.sleep(5000); }
        catch (InterruptedException e) { throw new RuntimeException(e); }
    }

    @Test(priority = 3, groups = {"ui","grp2"})
    public void AdminPageTest() {
        System.out.println("Current Date Time: " + currentTimeStamp());
        page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Admin")).click();
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

