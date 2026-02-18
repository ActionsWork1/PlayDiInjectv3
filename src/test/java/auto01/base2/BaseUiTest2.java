package auto01.base2;

import auto01.base.TestModule;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Guice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

@Guice(modules = TestModuleLazyInit.class)
public class BaseUiTest2 {

    @Inject
    protected Page page; // Use this directly in your tests!

    @Inject
    protected Browser browser;

    String resultsPath = "./allure-results";

    @BeforeSuite(alwaysRun = true)
    public void setupAllure() throws IOException {

        System.out.println("User Dir: "+System.getProperty("user.dir"));
        Files.createDirectories(Paths.get(resultsPath));
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", System.getProperty("os.name").toUpperCase())
                        .put("Browser","Chrome")
                        .put("Browser.Version",browser.version())
                        .put("URL", "http://testjs.site88.net")
                        .build(),System.getProperty("user.dir")+ "/allure-results/");

        // 2. Categories for failure grouping
        String categoriesJson = "[{\"name\": \"Infrastructure\", \"matchedStatuses\": [\"broken\"], \"messageRegex\": \".*Timeout.*\"}]";
        Files.write(Paths.get(resultsPath, "categories.json"), categoriesJson.getBytes());
    }


    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        TestModuleLazyInit.quit();
    }
}
