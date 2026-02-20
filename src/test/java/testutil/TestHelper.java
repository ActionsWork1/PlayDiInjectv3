package testutil;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeoutException;
//import java.util.concurrent.TimeoutException;

public class TestHelper {

    private final Page page;

    @Inject
    public TestHelper(Page page) {
       this.page = page;
    }

    /***
     *
     * @return
     */
    public static String currentTimeStamp(){

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH);
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }

    /***
     *
     * @param page
     * @param screenshotName
     * @return
     */
    public static byte[] CaptureScreenshotBytes(Page page, String screenshotName)
    {

        byte[] screenshotBytes =  page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        return screenshotBytes;
    }

    /***
     *
     * @param page
     * @param title
     * @return
     */
    public static List<Page> SwitchTab(Page page, String title)
    {
        List<Page> Tabs = page.context().pages();
        return Tabs;
    }

    /***
     *
     * @param page
     * @param tabIndex
     * @return
     */
    public static Page SwitchTab(Page page, int tabIndex)
    {
        List<Page> Tabs = page.context().pages();
        return Tabs.get(tabIndex);
    }

    /***
     *
     * @param page
     * @return
     */
    public static Page OpenNewTab(Page page)
    {
        return page.context().newPage();
    }


    /***
     *
     * @param page
     * @param url
     * @param timeoutMilliseconds
     */
    public static void SafeNavigate(Page page, String url, int timeoutMilliseconds)
    {
        try
        {
            page.navigate(url,new Page.NavigateOptions().setTimeout(timeoutMilliseconds));
        }
        catch (Exception ex)
        {
            // The C# code timed out, but the browser is still trying to load.
            // This JS call forces the browser to stop immediately.
            page.evaluate("window.stop();");
            System.out.println("Navigation timed out: Browser loading forced to stop.");
        }
    }

    /***
     *
     * @param page
     * @param url
     * @param TimeInMilliSeconds
     */
    public static void ScriptedBrowserLoading(Page page,String url,int TimeInMilliSeconds)
    {
        // This script will wait for X ms and then stop the window
        String stopScript = "(timeout) => {setTimeout(() => { window.stop();}, timeout);}";

        // Start the stop-timer in the background (no 'await' on the timer itself if it's internal)
        page.evaluate(stopScript, 5000);
        // Proceed with navigation
        page.navigate(url);

    }

    /***
     *
     * @param TimeInMilliSeconds
     */
    public void SleepTime(int TimeInMilliSeconds){
        try {
            Thread.sleep(TimeInMilliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}


//public static void RegisterLog(string logtext)
//{
//    TestContext.Out.WriteLine(logtext);
//    Log.Information(logtext);
//}





