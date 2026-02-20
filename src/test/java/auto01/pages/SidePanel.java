package auto01.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SidePanel {

    private final Page page;

    @Inject
    public SidePanel(Page page) {
        this.page = page;
    }

    public void clickSideLink(String sideLink){
       page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName(sideLink)).click();
    }


}
