package auto01.uitests2;

import auto01.base2.BaseUiTest2;
import auto01.base2.TestModuleLazyInit;
import auto01.pages.EmployeePage;
import auto01.pages.LoginPage;
import auto01.pages.SidePanel;
import com.github.javafaker.Faker;
import com.google.inject.Inject;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import testutil.TestHelper;
import testutil.WebTable;


@Guice(modules = TestModuleLazyInit.class)
public class EmployeeTest2 extends BaseUiTest2 {

    @Inject
    LoginPage loginPage;

    @Inject
    SidePanel sidePanel;

    @Inject
    EmployeePage empPage;

    @Inject
    TestHelper testHelper;

    @Inject
    WebTable webTable;


    @BeforeTest(alwaysRun = true)
    public void setUp() {
       System.out.println("Current Date Time: " + TestHelper.currentTimeStamp());
       loginPage.NavigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
       empPage.LoginToHRM("Admin", "admin123");
    }


    @Test(priority = 1, groups = {"ui", "smoke", "grp2"})
    public void Employee_E2E_Test1() {

        AddNewEmployee();
        searchEmployeeTest3();

    }

    @Step("add new employee")
    public void AddNewEmployee() {
        Faker faker = new Faker();
        String empID =faker.random().nextInt(3000,7000).toString();
        empPage.createNewEmployee("hank"+empID, empID);
        testHelper.SleepTime(5000);
        testHelper.AttachScreenShot();
        testHelper.consoleMessage("Add new employee successfully");

    }


    public void search_new_employee() {

        System.out.println("Current Date Time: " + TestHelper.currentTimeStamp());


        sidePanel.clickSideLink("PIM");
        webTable.WaitForTableRows();

        System.out.println("Total Header Count: " + webTable.getAllHeaderNames().size());
        System.out.println("Total Row Count: " + webTable.getRowCount());
        System.out.println("Total Header Names: " + webTable.getAllHeaderNames());
        System.out.println("cell Value User Names: " + webTable.getCellValue(1, 1));

//        webTable.performActionOnCell(3,0);

        int x2 = webTable.searchDataInColoumn("First (& Middle) Name", "hank");
        System.out.println("Row Num: " + x2);

    }

    @Step("search new employee by its name")
    public void searchEmployeeTest3() {

        System.out.println("Current Date Time: " + TestHelper.currentTimeStamp());

        sidePanel.clickSideLink("PIM");
        webTable.WaitForTableRows();

        empPage._EmpName_TXT.click();
        empPage._EmpName_TXT.fill("hank");
        empPage._EmpSearch_BTN.click();
        webTable.WaitForTableRows();

        testHelper.SleepTime(15000);


        int x2 = webTable.searchDataInColoumn("First (& Middle) Name", "hank");
        System.out.println("Row Num: " + x2);

        webTable.performActionOnCell(x2,0);
        testHelper.SleepTime(5000);
        testHelper.AttachScreenShot();

    }



}
