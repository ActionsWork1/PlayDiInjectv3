package auto01.pages;

import auto01.base.FrameworkConfig;
import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.Reporter;

public class LoginPage {


//    private final Page page;
//    private final FrameworkConfig config;
//
//    @Inject
//    public LoginPage(Page page,FrameworkConfig Iconfig)
//    {
//         this.page = page;
//         this.config = Iconfig;
//    }

    private final Page page;

    private final Locator Username_TXT;
    private final Locator Password_TXT;
    private final Locator LoginButton_BTN;

    @Inject
    public LoginPage(Page page) {
        this.page = page;
        Username_TXT =page.locator("input[name='username']");
        Password_TXT=page.locator("input[name='password']");
        LoginButton_BTN=page.locator("button[type='submit']");
    }

    /***
     *
     * @param url
     */
    public void NavigateTo(String url){
        Reporter.log("Navigate to " + url);
        page.navigate(url,new Page.NavigateOptions()
                                 .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
                                 .setTimeout(45000)
                     );
    }

    /***
     *
     * @param uname
     * @param pwd
     */
    public void HRMLogin(String uname,String pwd){
        Username_TXT.fill(uname);
        Password_TXT.fill(pwd);
        LoginButton_BTN.click();
        Reporter.log("user logged in with: "+uname+"and pwd: "+pwd);
        page.waitForSelector("text='Admin'",new Page.WaitForSelectorOptions().setTimeout(45000));
    }

    /***
     *
     * @return
     */
    public Page getPage(){
        return page;
    }


}
