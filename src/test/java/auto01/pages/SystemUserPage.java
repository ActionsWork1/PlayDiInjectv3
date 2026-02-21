package auto01.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import testutil.WebTable;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;


public class SystemUserPage {

    private final Page page;

    @Inject
    WebTable webTable;

    @Inject
    public SystemUserPage(Page page) {
        this.page = page;

    }

    public void navigateToAdminPage() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Admin")).click();
        webTable.WaitForTableRows();
    }

    public String AddNewSystemUser(String employoeeName,String randomID) {
        String sysUser = employoeeName + "X0" + randomID;
        try {
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" Add")).click();
            assertThat(page.getByText("-- Select --").first()).isVisible();

            page.getByText("-- Select --").first().click();
            assertThat(page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("-- Select --"))).isVisible();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Admin")).click();

            page.getByText("-- Select --").click();
            assertThat(page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("-- Select --"))).isVisible();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Enabled")).click();

            //page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Type for hints...")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Type for hints...")).pressSequentially(employoeeName, new Locator.PressSequentiallyOptions().setDelay(300));
            assertThat(page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(employoeeName))).isVisible();

//       page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Type for hints...")).click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(employoeeName)).click();

            //String sysUser = employoeeName + "X0" + randomID;
            System.out.println("New System User ID:" + sysUser);
            page.getByRole(AriaRole.TEXTBOX).nth(2).click();
            page.getByRole(AriaRole.TEXTBOX).nth(2).fill(sysUser);

            page.getByRole(AriaRole.TEXTBOX).nth(3).click();
            page.getByRole(AriaRole.TEXTBOX).nth(3).fill("test123");

            page.getByRole(AriaRole.TEXTBOX).nth(4).click();
            page.getByRole(AriaRole.TEXTBOX).nth(4).fill("test123");

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
            //page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
            //assertThat(page.getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Sidepanel"))).isVisible();
            webTable.WaitForTableRows();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sysUser;
    }

    public void SearchSystemUser(String systemUserName) {

        navigateToAdminPage();
        page.getByRole(AriaRole.TEXTBOX).nth(1).click();
        page.getByRole(AriaRole.TEXTBOX).nth(1).fill(systemUserName);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
        webTable.WaitForTableRows();
        //page.locator(".oxd-table-card-cell-checkbox > .oxd-checkbox-wrapper > label > .oxd-checkbox-input > .oxd-icon").click();
    }


}



/*
 public class Example {
            public static void main(String[] args) {
                try (Playwright playwright = Playwright.create()) {
                    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                            .setHeadless(false));
                    BrowserContext context = browser.newContext();
                    Page page = context.newPage();
                    page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

       assertThat(page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("company-branding"))).isVisible();

                    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
                    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("Admin");
                    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).press("Tab");
                    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("admin123");
                    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).press("Enter");
                    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
                    assertThat(page.getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Sidepanel"))).isVisible();




 */





