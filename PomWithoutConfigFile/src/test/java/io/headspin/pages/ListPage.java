package io.headspin.pages;
import static io.headspin.locators.AppiumLocators.playvideo;
import static io.headspin.locators.AppiumLocators.videoplayer;
import static io.headspin.hsapi.GlobalVariable.*;
public class ListPage extends BasePage {
    public static void select_the_trailer() {
        status = "Failed select_the_trailer";
        waitForElementAndClick(playvideo);
        kpiLabels.get("Detail_page_load_time").put("start", System.currentTimeMillis()-60);
        waitForElementPresent(videoplayer);
        kpiLabels.get("Detail_page_load_time").put("end", System.currentTimeMillis()+600);
    }
}
