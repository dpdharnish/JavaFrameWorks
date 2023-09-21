package io.headspin.pages;

import static io.headspin.basicFunctionalities.WaitFunctions.waitForElementPresent;
import static io.headspin.global_variable.GlobalVariable.status;
import static io.headspin.locators.AppiumLocators.playvideo;

public class DetailPage {
    public static void verify_in_detail_page() throws InterruptedException {
        Thread.sleep(2000);
        waitForElementPresent(playvideo);
        status = "Passed";
    }
}
