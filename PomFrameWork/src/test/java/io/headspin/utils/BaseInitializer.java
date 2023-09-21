package io.headspin.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static io.headspin.basicFunctionalities.PropertyFileReader.getConfigProperty;

public class BaseInitializer {
    public static AppiumDriver driver;

    public static AppiumDriver getDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("automationName","uiautomator2");
        desiredCapabilities.setCapability("udid",getConfigProperty("UDID"));
        desiredCapabilities.setCapability("appPackage",getConfigProperty("appPackage"));
        desiredCapabilities.setCapability("appActivity",getConfigProperty("appActivity"));
        desiredCapabilities.setCapability("platformName",getConfigProperty("platformName"));
        desiredCapabilities.setCapability("deviceName",getConfigProperty("deviceName"));
        desiredCapabilities.setCapability("headspin:capture.video",true);
        desiredCapabilities.setCapability("headspin:testName",getConfigProperty("testname"));
        desiredCapabilities.setCapability("noReset", true);
//        desiredCapabilities.setCapability("autoLaunch", false);
        URL url = new URL(getConfigProperty("AppiumURL"));
        driver = new AppiumDriver(url, desiredCapabilities);
        return driver;
    }
}
