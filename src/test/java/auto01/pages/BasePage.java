package auto01.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import testutil.TestHelper;
import testutil.WebTable;

public class BasePage {

    private final Page page;

    @Inject
    public  LoginPage loginPage;

    @Inject
    public SidePanel sidePanel;

    @Inject
    public EmployeePage empPage;

    @Inject
    public TestHelper testHelper;

    @Inject
    public WebTable webTable;


    @Inject
    public BasePage(final Page page) {
        this.page = page;
    }



}
