package io.headspin.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static io.headspin.basicFunctionalities.PropertyFileReader.getConfigProperty;
import static io.headspin.global_variable.GlobalVariable.kpiLabels;

public class BaseInitializer {
    public static AppiumDriver driver;

    public static AppiumDriver getDriver() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, getConfigProperty("PLATFORM_NAME"));
        //  cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.4");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "SM-M317F");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Uiautomator2");
        cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        cap.setCapability(MobileCapabilityType.UDID, getConfigProperty("UDID"));
        cap.setCapability("headspin:capture.video",true);
        cap.setCapability("headspin:testName","Java");
        URL url = new URL(getConfigProperty("AppiumURL"));
        kpiLabels.get("Launch_time").put("start", System.currentTimeMillis());
        driver = new AppiumDriver(url, cap);
        return driver;
    }
}
