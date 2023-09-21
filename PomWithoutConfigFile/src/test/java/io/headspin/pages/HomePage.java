package io.headspin.pages;
import java.net.MalformedURLException;
import static io.headspin.hsapi.GlobalVariable.*;
import static io.headspin.locators.AppiumLocators.*;
import static io.headspin.utils.BaseInitializer.startDriver;

public class HomePage extends BasePage {
    public static void launch_the_app(String udid,String AppiumURL) throws MalformedURLException, InterruptedException {
        status = "Fail to launch";
        kpiLabels.get("Launch_time").put("start", System.currentTimeMillis());
        startDriver(udid,AppiumURL); //fixed for all
        waitForElementPresent(youtubelogo);
        kpiLabels.get("Launch_time").put("end", System.currentTimeMillis()-100);
        Thread.sleep(2000);
    }
    public static void direct_to_Search_Page() throws InterruptedException {
        status = "Failed direct_to_Search_Page";
        waitForElementAndClick(searchicon);
        kpiLabels.get("search_tab_loadtime").put("start", System.currentTimeMillis()-60);
        waitForElementPresent(searchtab);
        kpiLabels.get("search_tab_loadtime").put("end", System.currentTimeMillis());
        Thread.sleep(2000);
    }
}
