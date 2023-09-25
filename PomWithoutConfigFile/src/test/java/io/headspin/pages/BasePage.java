package io.headspin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static io.headspin.hsapi.GlobalVariable.*;
import java.time.Duration;

public class BasePage {
    public static WebDriverWait webdriverWait() {
        wait = new WebDriverWait(Appium_Driver, Duration.ofSeconds(30));
        return wait;
    }
    public static boolean waitForElementPresent(By elementToWaitFor) {
        try
        {
            webdriverWait().until(ExpectedConditions.presenceOfElementLocated(elementToWaitFor));
            return true;
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            System.out.println("No Such Element Present");
            return false;
        }

    }
    public static void waitForElementAndClick(By elementToWaitFor) {
            webdriverWait().until(ExpectedConditions.presenceOfElementLocated(elementToWaitFor)).click();
    }
    public static void waitForElementAndSendKeys(By elementToWaitFor, String message) {
        webdriverWait().until(ExpectedConditions.presenceOfElementLocated(elementToWaitFor)).sendKeys(message);
    }
    public static String waitForElementAndGetText(By elementToWaitFor) {
        String output_message = null;
        output_message = webdriverWait().until(ExpectedConditions.presenceOfElementLocated(elementToWaitFor)).getText();
        return output_message;
    }
    public static void waitForElementPresentAndConfirmURL(String url) {
            webdriverWait().until(ExpectedConditions.urlToBe(url));
    }
    public static void waitForSpecificTime(long numOfSeconds) {
        try
        {
            Thread.sleep(numOfSeconds*1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            System.err.println("Exception caught while waiting for " + numOfSeconds + "seconds");
        }
    }
}
