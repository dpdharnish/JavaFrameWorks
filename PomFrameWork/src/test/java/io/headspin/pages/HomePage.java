package io.headspin.pages;
import io.headspin.pages.SearchPage;

import java.net.MalformedURLException;
import java.util.Stack;

import static io.headspin.basicFunctionalities.WaitFunctions.*;
import static io.headspin.global_variable.GlobalVariable.*;
import static io.headspin.implementions.AppAccess.AccessApplication;
import static io.headspin.locators.AppiumLocators.*;

public class HomePage {
    public static void launch_the_app() throws MalformedURLException, InterruptedException {
        status = "Fail to launch";
        kpiLabels.get("Launch_time").put("start", System.currentTimeMillis());
        AccessApplication();
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
