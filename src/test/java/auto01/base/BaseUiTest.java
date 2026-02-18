package auto01.base;

import com.github.javafaker.Faker;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;


//@Guice(modules = TestModule.class)
//public abstract class BaseUiTest {


//        @Inject
//        protected Page page;
//
//        @Inject
//        protected Browser browser;
//
//        @Inject
//        protected FrameworkConfig config;
//
//        protected BrowserContext context;
//
//        @BeforeMethod(alwaysRun = true)
//        public void setupContext() {
//            // Create a new context for every test for total isolation
//            context = browser.newContext(new Browser.NewContextOptions()
//                    .setBaseURL(config.baseUrl())
//                    .setViewportSize(1280, 720));
//
//            // Start Tracing
//            context.tracing().start(new Tracing.StartOptions()
//                    .setScreenshots(true)
//                    .setSnapshots(true)
//                    .setSources(true));
//
//            page = context.newPage();
//        }
//
//
//
////    @Inject
////    protected Page page; // Guice provides the ThreadLocal page here
//
//    @AfterMethod(alwaysRun = true)
//    public void tearDown() {
//        // Clean up the ThreadLocal instance
//        TestModule.quit();
//    }

    //        @AfterMethod(alwaysRun = true)
//        public void tearDown(ITestResult result) {
//            // Stop Tracing and save if the test fails
//            String tracePath = "target/traces/" + result.getName() + ".zip";
//            context.tracing().stop(new Tracing.StopOptions()
//                    .setPath(Paths.get(tracePath)));
//
//            context.close();
//        }


//@Guice(modules = TestModule.class)
//    public class BaseUiTest {
//
//        @Inject
//        protected Browser browser;
//
//        @Inject
//        protected FrameworkConfig config;
//
//        protected BrowserContext context;
//        protected Page page;
//
//        @BeforeMethod(alwaysRun = true)
//        public void setup() {
//            context = browser.newContext();
//            page = context.newPage();
//
//            // Push this specific page into our Guice ThreadLocal
//            TestModule.setPage(page);
//        }
//
//        @AfterMethod(alwaysRun = true)
//        public void tearDown() {
//            if (context != null) {
//                context.close();
//            }
//            TestModule.cleanUp();
//        }
//    }

@Guice(modules = TestModule.class)
public class BaseUiTest {

    @Inject
    protected Page page; // Guice will now successfully provide this on demand

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
        // This closes the page/context for the current thread
        TestModule.unload();
    }
}