package auto01.base2;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.*;


/**
 * Example of lazy intialization of page object
 * so that user can also directly the page object
 */
public class TestModuleLazyInit extends AbstractModule {
    private static final ThreadLocal<Page> PAGE_THREAD = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> CONTEXT_THREAD = new ThreadLocal<>();

    @Provides
    @Singleton
    public Playwright providePlaywright() { return Playwright.create(); }

    @Provides @Singleton
    public Browser provideBrowser(Playwright playwright) {
        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @Provides
    public Page providePage(Browser browser) {
        if (PAGE_THREAD.get() == null) {
            // This only runs ONCE per test thread
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            CONTEXT_THREAD.set(context);
            PAGE_THREAD.set(page);
        }
        return PAGE_THREAD.get();
    }

    public static void quit() {
        if (PAGE_THREAD.get() != null) {
            PAGE_THREAD.get().close();
            CONTEXT_THREAD.get().close();
            PAGE_THREAD.remove();
            CONTEXT_THREAD.remove();
        }
    }
}