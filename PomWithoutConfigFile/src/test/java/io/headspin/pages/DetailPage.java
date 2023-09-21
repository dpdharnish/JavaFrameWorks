package io.headspin.pages;
import static io.headspin.hsapi.GlobalVariable.*;
import static io.headspin.locators.AppiumLocators.playvideo;

public class DetailPage extends BasePage {
    public static void verify_in_detail_page() throws InterruptedException {
        Thread.sleep(2000);
        waitForElementPresent(playvideo);
        status = "Passed";
    }
}
