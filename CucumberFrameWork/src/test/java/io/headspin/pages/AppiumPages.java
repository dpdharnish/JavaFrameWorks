package io.headspin.pages;
import org.testng.Assert;
import java.net.MalformedURLException;
import io.headspin.hsapi.Session_visual_lib;
import static io.headspin.basicFunctionalities.PropertyFileReader.getConfigProperty;
import static io.headspin.implementions.AppAccess.*;
import static io.headspin.basicFunctionalities.WaitFunctions.*;
import static io.headspin.locators.AppiumLocators.*;
import static io.headspin.global_variable.GlobalVariable.*;
import io.headspin.hsapi.kpi_names;
public class AppiumPages {
    public void user_open_browser() throws MalformedURLException, InterruptedException {
        kpi_names kpi = new kpi_names();
        status = "Fail to launch";
        AccessApplication();
        Thread.sleep(2000);
        System.out.println("driver launched.........");
        kpiLabels.get("Welcome_Page_load_time").put("start", System.currentTimeMillis());
        Appium_Driver.get(getConfigProperty("URL"));
        waitForElementPresent(form_auth_link);
        kpiLabels.get("Welcome_Page_load_time").put("end", System.currentTimeMillis());
    }
    public void user_enter_credentials() throws InterruptedException {
        status = "Fail to enter credential";
        Thread.sleep(2000);
        System.out.println("entering the login credentials.........");
        kpiLabels.get("Login_page_load_time").put("start", System.currentTimeMillis());
        waitForElementAndClick(form_auth_link);
        waitForElementPresent(username);
        kpiLabels.get("Login_page_load_time").put("end", System.currentTimeMillis());
        waitForElementAndSendKeys(username,getConfigProperty("username"));
        waitForElementAndSendKeys(password,getConfigProperty("password"));
        Thread.sleep(2000);
        System.out.println("its in logout page.........");
        kpiLabels.get("Logout_page_load_time").put("start", System.currentTimeMillis());
        waitForElementAndClick(submitButton);
        waitForElementPresent(logOut);
        kpiLabels.get("Logout_page_load_time").put("end", System.currentTimeMillis());
    }
    public void verify() {
        status = "Fail to verify the login page";
        waitForElementAndClick(logOut);
        System.out.println("Conform weather its in the login page.......");
        waitForElementPresentAndConfirmURL(getConfigProperty("CONFIRM_URL"));
        String text=waitForElementAndGetText(flash);
        Assert.assertTrue(text.contains("logged out"));
        status = "Passed";
        Session_visual_lib teardown = new Session_visual_lib();
        System.out.println(kpiLabels);
        teardown.run_record_session_info();
    }
}
