package auto01.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;



public class EmployeePage {

    private final Page page;

    // Login Page
    public Locator _UserName_TXT;
    public Locator _Password_TXT;
    public Locator _Login_BTN;
    public Locator _SidePanel_DIV;

    //Emp Page
    public Locator _PIM_LINK;
    public Locator _Empfirstname_TXT;
    public Locator _Emplastname_TXT;
    public Locator _EmpID_TXT;
    public Locator _EmpEnableDisable_RD;
    public Locator _EmpAdd_BTN;
    public Locator _EmpUserName_TXT;
    public Locator _EmpNewUserPwd_TXT;
    public Locator _EmpSave_BTN;

    @Inject
    public EmployeePage(Page page)
    {
        this.page = page;
        //Login Page

        _UserName_TXT = page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("Username"));
        _Password_TXT = page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("Password" ));
        _Login_BTN = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Login"));
        _SidePanel_DIV = page.getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Sidepanel" ));

        //Employee Page
        _PIM_LINK = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("PIM"));
        _EmpAdd_BTN= page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName(" Add"));
//
      _Empfirstname_TXT=page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("First Name"));
      _Emplastname_TXT = page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("Last Name"));
      _EmpID_TXT = page.getByRole(AriaRole.TEXTBOX).nth(4);
      _EmpEnableDisable_RD = page.locator(".oxd-switch-input");
      _EmpUserName_TXT = page.getByRole(AriaRole.TEXTBOX).nth(5);
      _EmpNewUserPwd_TXT = page.locator("input[type='password']");
      _EmpSave_BTN = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Save"));

    }


    public void LoginToHRM(String username, String password)
    {
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





    public void createNewEmployee(String UserName,String empID)
    {
        _PIM_LINK.click();
        //await this._page.GotoAsync("/pim/viewEmployeeList");
        assertThat(_SidePanel_DIV).isVisible();

        _EmpAdd_BTN.click();
        assertThat(_SidePanel_DIV).isVisible();

        _Empfirstname_TXT.click();
        _Empfirstname_TXT.fill("hank");
        _Emplastname_TXT.click();
        _Emplastname_TXT.fill("churchil");


        _EmpID_TXT.fill(empID);
        _EmpSave_BTN.click();
        _EmpEnableDisable_RD.click();

        assertThat(page.getByRole(AriaRole.RADIO,new Page.GetByRoleOptions().setName("Enabled"))).isVisible();
//
//        await _EmpUserName_TXT.ClickAsync();
//        await _EmpUserName_TXT.FillAsync(UserName);
//        await _EmpNewUserPwd_TXT.First.ClickAsync();
//        await _EmpNewUserPwd_TXT.First.FillAsync("Test1234");
//        await _EmpNewUserPwd_TXT.Nth(1).ClickAsync();
//        await _EmpNewUserPwd_TXT.Nth(1).FillAsync("Test1234");
//        await _EmpSave_BTN.ClickAsync();
//
//        //await Expect(Page.Locator(".oxd-loading.spinner")).Not.ToBeVisibleAsync(
//        //          new LocatorAssertionsToBeVisibleOptions()
//        //          {
//        //              Visible = true,
//        //              Timeout = 35_000
//        //          });
//        await Assertions.Expect(this._page.Locator("div.orangehrm-edit-employee-navigation")).ToBeVisibleAsync(
//            new()
//        {
//            Visible = true,
//                    Timeout = 45_000
//        });
//        Console.WriteLine($"Credentials: {UserName}/Test1234");
    }
//
//    public async Task HRMLogout()
//    {
//        //await Page.GotoAsync("/pim/viewPersonalDetails/empNumber/223",new PageGotoOptions() { Timeout=35_000});
//        await Assertions.Expect(_SidePanel_DIV).ToBeVisibleAsync(new LocatorAssertionsToBeVisibleOptions() { Visible = true, Timeout = 25_000 });
//        //await Page.GetByRole(AriaRole.Listitem).Filter(new() { HasText = "Haleema user" }).Locator("i").ClickAsync();
//        await _page.Locator("span.oxd-userdropdown-tab").ClickAsync();
//        //await _page.Locator("p.oxd-userdropdown-name").ClickAsync();
//        await Assertions.Expect(_page.GetByRole(AriaRole.Menuitem, new() { Name = "About" })).ToBeVisibleAsync(new LocatorAssertionsToBeVisibleOptions() { Visible = true, Timeout = 25_000 });
//        await _page.GetByRole(AriaRole.Menuitem, new() { Name = "Logout" }).ClickAsync();
//    }
//
}


//await Page.GotoAsync("/dashboard/index");