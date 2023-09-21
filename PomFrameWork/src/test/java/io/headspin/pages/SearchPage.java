package io.headspin.pages;
import static io.headspin.basicFunctionalities.WaitFunctions.*;
import static io.headspin.global_variable.GlobalVariable.*;
import static io.headspin.locators.AppiumLocators.*;
import static io.headspin.locators.AppiumLocators.playvideo;

public class SearchPage {
    public static void search_for_trailer() throws InterruptedException {
        status = "Failed search_for_trailer";
        waitForElementAndSendKeys(searchtab,"salaar trailer");
        waitForElementAndClick(searchvideos);
        kpiLabels.get("searchtime").put("start", System.currentTimeMillis()-700);
        waitForElementPresent(playvideo);
        kpiLabels.get("searchtime").put("end", System.currentTimeMillis()+50);
        Thread.sleep(2000);
    }
}
