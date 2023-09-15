package io.headspin.implementions;
import java.net.MalformedURLException;

import static io.headspin.global_variable.GlobalVariable.*;
import static io.headspin.utils.BaseInitializer.getDriver;

public class AppAccess {
    public static void AccessApplication() throws MalformedURLException
    {
        if(Appium_Driver == null || Appium_Driver.toString().contains("null"))
        {
            Appium_Driver = getDriver();
            kpiLabels.get("Launch_time").put("end", System.currentTimeMillis());
            Session_id = String.valueOf(Appium_Driver.getSessionId());
            System.out.println(Session_id);
        }
    }
}
