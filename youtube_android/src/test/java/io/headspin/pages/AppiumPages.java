package io.headspin.pages;

import io.headspin.hsapi.Session_visual_lib;
import io.headspin.hsapi.kpi_names;

import java.net.MalformedURLException;

import static io.headspin.basicFunctionalities.PropertyFileReader.getConfigProperty;
import static io.headspin.basicFunctionalities.WaitFunctions.*;
import static io.headspin.basicFunctionalities.WaitFunctions.waitForElementPresent;
import static io.headspin.global_variable.GlobalVariable.*;
import static io.headspin.implementions.AppAccess.AccessApplication;
import static io.headspin.locators.AppiumLocators.*;

public class AppiumPages {
    public void launchapp() throws MalformedURLException {
        kpi_names kpi = new kpi_names();
        status = "Fail to launch";
        kpiLabels.get("Launch_time").put("start", System.currentTimeMillis());
        AccessApplication();
        waitForElementPresent(youtubelogo);
        kpiLabels.get("Launch_time").put("end", System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchformovie() {
        kpiLabels.get("search_tab_loadtime").put("start", System.currentTimeMillis());
        waitForElementAndClick(searchicon);
        waitForElementPresent(searchtab);
        kpiLabels.get("search_tab_loadtime").put("end", System.currentTimeMillis());
        waitForElementAndSendKeys(searchtab,"salaar trailer");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        kpiLabels.get("searchtime").put("start", System.currentTimeMillis());
        waitForElementAndClick(searchvideos);
        waitForElementPresent(playvideo);
        kpiLabels.get("searchtime").put("end", System.currentTimeMillis());
    }

    public void choosethemovie() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        kpiLabels.get("Detail_page_load_time").put("start", System.currentTimeMillis());
        waitForElementAndClick(playvideo);
        waitForElementPresent(videoplayer);
        kpiLabels.get("Detail_page_load_time").put("end", System.currentTimeMillis());
//        waitForElementPresent(sharebutton);
    }

    public void endthesession() {
        Appium_Driver.quit();
        status="Passed";
        Session_visual_lib teardown = new Session_visual_lib();
        System.out.println(kpiLabels);
        teardown.run_record_session_info();
    }
}
