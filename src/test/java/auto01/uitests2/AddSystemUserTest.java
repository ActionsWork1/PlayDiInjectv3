package auto01.uitests2;


import auto01.base2.BaseUiTest2;
import auto01.base2.TestModuleLazyInit;
import auto01.pages.LoginPage;
import auto01.pages.SystemUserPage;
import com.github.javafaker.Faker;
import com.google.inject.Inject;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static io.qameta.allure.model.Parameter.Mode.HIDDEN;

@Guice(modules = TestModuleLazyInit.class)
public class AddSystemUserTest extends BaseUiTest2 {


    @Inject
    SystemUserPage systemUserPage;

     @BeforeTest(alwaysRun = true)
     public void setUp() {
         basePage.loginPage.NavigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
     }

     @Test()
    public void testAddSystemUser() {

         Faker faker = new Faker();
         String systemUserName = faker.name().firstName();
         String empName = faker.name().firstName();
         String empID = faker.random().nextInt(3000,9000).toString();

         System.out.println(String.format("SystemUserName: %s,empName: %s, empID: %s",systemUserName,empName,empID));

         loginWithParam("Admin","admin123");;
         AddNewEmployee(empName, empID);
         String newSysUser =AddSysUser(empName, empID);
         basePage.testHelper.SleepTime(3000);
         searchNewSystemUser(newSysUser);
         fLogout();
         loginWithParam(newSysUser,"test123");
         fLogout();


//         basePage.loginPage.HRMLogin("Admin","admin123");
//         AddNewEmployee(empName, empID);
//         systemUserPage.navigateToAdminPage();
//         String newSysUser =systemUserPage.AddNewSystemUser(empName, empID);
//         basePage.testHelper.SleepTime(6000);
//         systemUserPage.SearchSystemUser(newSysUser);
    }

////    @Step("Performing login with username: [{username}] and password: [{password}]")
//    @Step("Performing login")
//    public void loginAdmin(String username, String password) {
//        basePage.loginPage.HRMLogin(username,password);
//    }



    @Step("Performing login with username: [{username}] and password")
    public void loginWithParam(
            @Param("username") String user,
            @Param(name = "password", mode = HIDDEN) String pwd) {
        System.out.println("Attempting to log in with user: " + user);
        basePage.loginPage.HRMLogin(user,pwd);
    }




    @Step("logout from app")
    public void fLogout(){
         basePage.loginPage.appLogOut(LoginPage.Actions.LOGOUT);
    }

    //    @Step("Add New Employee with empname:[{empname}] empID:[{empid}]")
    @Step("Add New Employee")
    public void AddNewEmployee(String userName, String empID) {
       basePage.empPage.createNewEmployee(userName,empID);
    }

//    @Step("Add New System User with UserName:[{emp_name}] and Id:[{empid}]")
    @Step("Add New System User")
    public String AddSysUser(String emp_name,String empid){
        systemUserPage.navigateToAdminPage();
        String newSysUser =systemUserPage.AddNewSystemUser(emp_name, empid);
        return newSysUser;
    }

//    @Step("Search New System User with System User Name:[{newSysUser}]")
    @Step("Search New System User")
    public void searchNewSystemUser(String newSysUser){
        basePage.testHelper.SleepTime(6000);
        systemUserPage.SearchSystemUser(newSysUser);
    }

}
