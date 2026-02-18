package auto01.base;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.*;
import org.aeonbits.owner.ConfigFactory;



//public class TestModule extends AbstractModule {
//    @Override
//    protected void configure() {
//        bind(FrameworkConfig.class).toInstance(ConfigFactory.create(FrameworkConfig.class));
//    }
//
//    @Provides
//    @Singleton
//    public Playwright providePlaywright() {
//        return Playwright.create();
//    }
//
//    @Provides
//    @Singleton
//    public Browser provideBrowser(Playwright playwright) {
//        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//    }
//
//    // Note: We let BaseTest handle the Page and Context creation
//    // to keep TestNG's thread safety intact.
//}

//public class TestModule extends AbstractModule {
//
////    @Override
////    protected void configure() {
////        // Bind the Config so it can be injected anywhere
////        bind(FrameworkConfig.class).toInstance(ConfigFactory.create(FrameworkConfig.class));
////    }
////
////    @Provides
////    @Singleton
////    public Playwright providePlaywright() {
////        return Playwright.create();
////    }
////
////    @Provides
////    @Singleton
////    public Browser provideBrowser(Playwright playwright) {
////        // You can use config here if you want to pull browser type from properties
////        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
////    }
////
////    @Provides
////    @Singleton
////    public BrowserContext provideContext(Browser browser) {
////        return browser.newContext();
////    }
////
////    @Provides
////    public Page providePage(BrowserContext context) {
////        // This is what Guice was missing!
////        return context.newPage();
////    }
//
//
//    // ThreadLocal ensures each TestNG thread has its own Page
//    private static final ThreadLocal<Page> PAGE_THREAD_LOCAL = new ThreadLocal<>();
//
//    @Provides
//    @Singleton
//    public Playwright providePlaywright() {
//        return Playwright.create();
//    }
//
//    @Provides
//    @Singleton
//    public Browser provideBrowser(Playwright playwright) {
//        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//    }
//
//    @Provides
//    public Page providePage(Browser browser) {
//        if (PAGE_THREAD_LOCAL.get() == null) {
//            // Only create the page if it doesn't exist for this thread
//            PAGE_THREAD_LOCAL.set(browser.newPage());
//        }
//        return PAGE_THREAD_LOCAL.get();
//    }
//
//    // Static helper to clean up after the test
//    public static void quit() {
//        if (PAGE_THREAD_LOCAL.get() != null) {
//            PAGE_THREAD_LOCAL.get().context().close();
//            PAGE_THREAD_LOCAL.remove();
//        }
//    }
//}

//public class TestModule extends AbstractModule {
//    private static final ThreadLocal<Page> PAGE_THREAD_LOCAL = new ThreadLocal<>();
//
//    @Override
//    protected void configure() {
//        // Bind the Owner Config
//        bind(FrameworkConfig.class).toInstance(ConfigFactory.create(FrameworkConfig.class));
//    }
//
//    @Provides
//    @Singleton
//    public Playwright providePlaywright() {
//        return Playwright.create();
//    }
//
//    @Provides
//    @Singleton
//    public Browser provideBrowser(Playwright playwright) {
//        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//    }
//
//    @Provides
//    public Page providePage() {
//        Page page = PAGE_THREAD_LOCAL.get();
//        if (page == null) {
//            throw new RuntimeException("Page not initialized! Make sure BaseTest @BeforeMethod runs.");
//        }
//        return page;
//    }
//
//    public static void setPage(Page page) {
//        PAGE_THREAD_LOCAL.set(page);
//    }
//
//    public static void cleanUp() {
//        PAGE_THREAD_LOCAL.remove();
//    }
//}



public class TestModule extends AbstractModule {
    // These hold the instances for the current thread
    private static final ThreadLocal<BrowserContext> CONTEXT_THREAD = new ThreadLocal<>();
    private static final ThreadLocal<Page> PAGE_THREAD = new ThreadLocal<>();

    @Override
    protected void configure() {
        bind(FrameworkConfig.class).toInstance(ConfigFactory.create(FrameworkConfig.class));
    }

    @Provides @Singleton
    public Playwright providePlaywright() { return Playwright.create(); }

    @Provides @Singleton
    public Browser provideBrowser(Playwright playwright) {
        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }

    @Provides
    public Page providePage(Browser browser) {
        if (PAGE_THREAD.get() == null) {
            // Create context and page only when requested
            BrowserContext context = browser.newContext();
            CONTEXT_THREAD.set(context);
            PAGE_THREAD.set(context.newPage());
        }
        return PAGE_THREAD.get();
    }

    public static void unload() {
        if (PAGE_THREAD.get() != null) {
            PAGE_THREAD.get().close();
            CONTEXT_THREAD.get().close();
        }
        PAGE_THREAD.remove();
        CONTEXT_THREAD.remove();
    }
}









