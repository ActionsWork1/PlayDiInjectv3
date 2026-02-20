package auto01.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;



public class EmployeePage {

    private final Page page;

    // Login Page
    public Locator _UserName_TXT;
    public Locator _Password_TXT;
    public Locator _Login_BTN;
    public Locator _SidePanel_DIV;

    //Emp Page
    public Locator _EmpName_TXT;
    public Locator _EmpSuperviserName_TXT;
    public Locator _EmpID_TXT;
    public Locator _EmpSearch_BTN;


    //New Emp Page
    public Locator _PIM_LINK;
    public Locator _Empfirstname_TXT;
    public Locator _Emplastname_TXT;
    public Locator _EmpNewID_TXT;
    public Locator _EmpEnableDisable_RD;
    public Locator _EmpAdd_BTN;
    public Locator _EmpUserName_TXT;
    public Locator _EmpNewUserPwd_TXT;
    public Locator _EmpSave_BTN;

    @Inject
    public EmployeePage(Page page) {
        this.page = page;
        //Login Page

        _UserName_TXT = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username"));
        _Password_TXT = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
        _Login_BTN = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        _SidePanel_DIV = page.getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Sidepanel"));
// Employee Page
        _PIM_LINK = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("PIM"));
        _EmpAdd_BTN = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" Add"));
        _EmpName_TXT = page.locator("div.oxd-autocomplete-text-input.oxd-autocomplete-text-input>input").nth(0);
        _EmpSuperviserName_TXT= page.locator("div.oxd-autocomplete-text-input.oxd-autocomplete-text-input>input").nth(1);
        _EmpID_TXT = page.locator("input.oxd-input.oxd-input").nth(1);
        _EmpSearch_BTN=page.locator("button[type='submit']");
        // New Employee Page
        _Empfirstname_TXT = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name"));
        _Emplastname_TXT = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name"));
        _EmpNewID_TXT = page.getByRole(AriaRole.TEXTBOX).nth(4);
        _EmpEnableDisable_RD = page.locator(".oxd-switch-input");
        _EmpUserName_TXT = page.getByRole(AriaRole.TEXTBOX).nth(5);
        _EmpNewUserPwd_TXT = page.locator("input[type='password']");
        _EmpSave_BTN = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save"));

    }


    public void LoginToHRM(String username, String password) {
        _UserName_TXT.click();
        _UserName_TXT.fill(username);
        //await UserName_TXT.PressAsync("Tab");
        _Password_TXT.fill(password);
        _Login_BTN.click();
        assertThat(_SidePanel_DIV).isVisible(new LocatorAssertions.IsVisibleOptions()
                .setTimeout(45_000)
                .setVisible(true)
        );

    }


    public void createNewEmployee(String UserName, String empID) {
        _PIM_LINK.click();
        //await this._page.GotoAsync("/pim/viewEmployeeList");
        assertThat(_SidePanel_DIV).isVisible();

        _EmpAdd_BTN.click();
        assertThat(_SidePanel_DIV).isVisible();

        _Empfirstname_TXT.click();
        _Empfirstname_TXT.fill(UserName);
        _Emplastname_TXT.click();
        _Emplastname_TXT.fill("fox");


        _EmpNewID_TXT.fill(empID);
        _EmpSave_BTN.click();
        _EmpEnableDisable_RD.click();

        assertThat(page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Enabled"))).isVisible();

        _EmpUserName_TXT.click();
        _EmpUserName_TXT.fill(UserName+empID);
        _EmpNewUserPwd_TXT.first().click();
        _EmpNewUserPwd_TXT.first().fill("test123");
        _EmpNewUserPwd_TXT.nth(1).click();
        _EmpNewUserPwd_TXT.nth(1).fill("test123");
        _EmpSave_BTN.click();

        page.waitForSelector("div.orangehrm-edit-employee-image",
                                      new Page.WaitForSelectorOptions()
                                                 .setTimeout(15_000)
                                                 .setState(WaitForSelectorState.VISIBLE)
                            );

        page.waitForSelector("form.oxd-form",
                              new Page.WaitForSelectorOptions()
                                            .setTimeout(15_000)
                                            .setState(WaitForSelectorState.VISIBLE)
        );

    }


    public void createNewEmployee(String firstName,String lastName,String UserName,String pwd,String empID) {
        _PIM_LINK.click();
        //await this._page.GotoAsync("/pim/viewEmployeeList");
        assertThat(_SidePanel_DIV).isVisible();

        _EmpAdd_BTN.click();
        assertThat(_SidePanel_DIV).isVisible();

        _Empfirstname_TXT.click();
        _Empfirstname_TXT.fill(firstName);
        _Emplastname_TXT.click();
        _Emplastname_TXT.fill(lastName);


        _EmpNewID_TXT.fill(empID);
        _EmpSave_BTN.click();
        _EmpEnableDisable_RD.click();

        assertThat(page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Enabled"))).isVisible();

        _EmpUserName_TXT.click();
        _EmpUserName_TXT.fill(UserName);
        _EmpNewUserPwd_TXT.first().click();
        _EmpNewUserPwd_TXT.first().fill(pwd);
        _EmpNewUserPwd_TXT.nth(1).click();
        _EmpNewUserPwd_TXT.nth(1).fill(pwd);
        _EmpSave_BTN.click();

        page.waitForSelector("div.orangehrm-edit-employee-image",
                new Page.WaitForSelectorOptions()
                        .setTimeout(15_000)
                        .setState(WaitForSelectorState.VISIBLE)
        );

        page.waitForSelector("form.oxd-form",
                new Page.WaitForSelectorOptions()
                        .setTimeout(15_000)
                        .setState(WaitForSelectorState.VISIBLE)
        );

    }






}

