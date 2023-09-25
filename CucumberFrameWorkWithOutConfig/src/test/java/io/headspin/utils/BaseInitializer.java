package io.headspin.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static io.headspin.hsapi.GlobalVariable.*;
public class BaseInitializer {
    public static AppiumDriver driver;

    public static void startDriver(String udid,String AppiumURL) throws MalformedURLException {
        String appPackage = "com.google.android.youtube";
        String appActivity = "com.google.android.apps.youtube.app.watchwhile.WatchWhileActivity";
        String platformName = "android";
        String deviceName = "SM-M526B";
        String testname = "Pom_Youtube_Java";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("automationName","uiautomator2");
        desiredCapabilities.setCapability("udid",udid);
        desiredCapabilities.setCapability("appPackage",appPackage);
        desiredCapabilities.setCapability("appActivity",appActivity);
        desiredCapabilities.setCapability("platformName",platformName);
        desiredCapabilities.setCapability("deviceName",deviceName);
        desiredCapabilities.setCapability("headspin:capture.video",true);
        desiredCapabilities.setCapability("headspin:testName",testname);
        desiredCapabilities.setCapability("noReset", true);
        URL url = new URL(AppiumURL);
        //global variable assignment
        Testname = testname;
        KPI_LABEL_CATEGORY = "kpiYoutube";
        Appium_Url = AppiumURL;
        driver = new AppiumDriver(url, desiredCapabilities);
        UDID = udid;
        Appium_Driver = driver;
        Session_id = String.valueOf(Appium_Driver.getSessionId()); //String class
    }
}
