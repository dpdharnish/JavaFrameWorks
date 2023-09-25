package io.headspin;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import static io.headspin.pages.DetailPage.verify_in_detail_page;
import static io.headspin.pages.HomePage.direct_to_Search_Page;
import static io.headspin.pages.HomePage.launch_the_app;
import static io.headspin.pages.ListPage.select_the_trailer;
import static io.headspin.pages.SearchPage.search_for_trailer;
public class YoutubeTest extends ConfestTest {
    @Parameters({"udid","AppiumURL"})
    @Test
    public void Youtube1(String udid,String AppiumURL) throws MalformedURLException, InterruptedException {
        System.out.println("App is launching....");
        launch_the_app(udid,AppiumURL);
        System.out.println("directing to search page...");
        direct_to_Search_Page();
        System.out.println("search for trailer...");
        search_for_trailer();
        System.out.println("select the trailer...");
        select_the_trailer();
        System.out.println("verify the detail page...");
        verify_in_detail_page();
    }
}
