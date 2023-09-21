package io.headspin;

import io.headspin.hsapi.Session_visual_lib;
import io.headspin.hsapi.kpi_names;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;

import static io.headspin.global_variable.GlobalVariable.Appium_Driver;

public class Confest {
    @BeforeTest
    public void SetUp() {
        kpi_names k = new kpi_names();
    }

    @AfterTest
    public void TearDown() {
        Appium_Driver.quit();
        Session_visual_lib Teardown = new Session_visual_lib();
        Teardown.run_record_session_info();
    }
}
