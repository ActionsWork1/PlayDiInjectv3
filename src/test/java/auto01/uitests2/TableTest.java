package auto01.uitests2;

import auto01.base2.BaseUiTest2;
import auto01.base2.TestModuleLazyInit;
import auto01.pages.LoginPage;
import auto01.pages.SidePanel;
import com.google.inject.Inject;
import org.testng.annotations.*;
import testutil.GenericTable;
import testutil.TestHelper;
import testutil.WebTable;

import java.lang.reflect.Method;

@Guice(modules = TestModuleLazyInit.class)
public class TableTest extends BaseUiTest2 {

    @Inject
    LoginPage loginPage;

    @Inject
    SidePanel sidePanelPage;

    @Inject
    TestHelper testHelper;

    @Inject
    GenericTable genericTable;

    @Inject
    WebTable webTable;


    @BeforeTest(alwaysRun = true)
    public void setUp() {
        // Print the result
        System.out.println("Current Date Time: " + TestHelper.currentTimeStamp());
        loginPage.NavigateTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        loginPage.appLogOut(LoginPage.Actions.LOGOUT);

    }

    @BeforeMethod
    public void setUpMethod(Method method) {
        System.out.println("Test Run: " + method.getName()+" Current Date Time: "+TestHelper.currentTimeStamp());
    }

    @AfterMethod
    public void tearMethod(Method method) {
        System.out.println("Test Finish: " + method.getName()+" Current Date Time: "+TestHelper.currentTimeStamp());
    }


    @Test(priority = 1,groups = {"ui", "smoke","grp2"})
    public void OrangeTableTest1() {

        loginPage.HRMLogin("Admin", "admin123");

        sidePanelPage.clickSideLink("Admin");
        //testHelper.SleepTime(12000);

        int colIndex1 = genericTable.getColumnIndex("Username");
        int colIndex2 = genericTable.getColumnIndex("User Role");
        int colIndex3 = genericTable.getColumnIndex("Employee Name");
        int colIndex4 = genericTable.getColumnIndex("Status" );
        int colIndex5 = genericTable.getColumnIndex("Actions");

        System.out.println("username      -> col num: "+colIndex1);
        System.out.println("User Role     -> col num: "+colIndex2);
        System.out.println("Employee Name -> col num: "+colIndex3);
        System.out.println("Status        -> col num: "+colIndex4);
        System.out.println("Actions       -> col num: "+colIndex5);
    }

    @Test(priority = 2,groups = {"ui", "smoke","grp2"})
    public void OrangeTableTest2() {

        System.out.println("Total Header Count: " + webTable.getAllHeaderNames().size());
        System.out.println("Total Row Count: " + webTable.getRowCount());
        System.out.println("Total Header Names: " + webTable.getAllHeaderNames());
        System.out.println("cell Value User Names: " + webTable.getCellValue(1,1));


        // 4. Select a user by their name
        webTable.performActionOnCell(3,0);
        int x = webTable.searchDataInColoumn("Username","Kiran.Patel");
        System.out.println("Row Num: "+x);

        int x1 = webTable.searchDataInColoumn("Username","FMLName1");
        System.out.println("Row Num: "+x1);

        int x2 = webTable.searchDataInColoumn("Username","Danika.Nguyen");
        System.out.println("Row Num: "+x2);
    }

}








