package io.headspin.global_variable;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static io.headspin.basicFunctionalities.PropertyFileReader.getConfigProperty;
import java.util.HashMap;
import java.util.Map;

public class GlobalVariable {
    public static AppiumDriver Appium_Driver;
    public static WebDriverWait wait;
    public static String Access_token;
    public static String UDID = getConfigProperty("UDID");
    public static String Session_id;
    public static String status = "Failed";
    public static Map<String,Object> session_data = new HashMap<>();
    public static String Testname = getConfigProperty("testname");

    public static String KPI_LABEL_CATEGORY = getConfigProperty("CATEGORY");

    public static Map<String, Map<String, Long>> kpiLabels;
    public static Double video_start_timestamp;
    public static String Appium_Url = getConfigProperty("AppiumURL");

}
