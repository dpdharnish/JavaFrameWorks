package io.headspin;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import static io.headspin.global_variable.GlobalVariable.Appium_Driver;
import static io.headspin.pages.DetailPage.*;
import static io.headspin.pages.HomePage.*;
import static io.headspin.pages.ListPage.*;
import static io.headspin.pages.SearchPage.*;
import io.headspin.hsapi.Session_visual_lib;
import io.headspin.hsapi.kpi_names;

public class Youtube {
    @Test
    public void YoutubeTest() throws MalformedURLException, InterruptedException {
        System.out.println("App is launching....");
        launch_the_app();
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
