package auto01.base2;

import auto01.base.TestModule;
import com.google.inject.Inject;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Guice;

@Guice(modules = TestModuleLazyInit.class)
public class BaseUiTest2 {

    @Inject
    protected Page page; // Use this directly in your tests!

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        TestModuleLazyInit.quit();
    }
}
