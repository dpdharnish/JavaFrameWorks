package io.headspin.hsapi;
import java.util.HashMap;
import java.util.Map;

import static io.headspin.global_variable.GlobalVariable.*;
public class kpi_names {
    public kpi_names() {
        kpiLabels =new HashMap<>();
        //Launch time kpi
        kpiLabels.put("Launch_time", new HashMap<>());
        kpiLabels.get("Launch_time").put("start", 0L);
        kpiLabels.get("Launch_time").put("end", 0L);
        // Welcome_Page_load_time kpi
        kpiLabels.put("Welcome_Page_load_time", new HashMap<>());
        kpiLabels.get("Welcome_Page_load_time").put("start", 0L);
        kpiLabels.get("Welcome_Page_load_time").put("end", 0L);
        //Login_page_load_time kpi
        kpiLabels.put("Login_page_load_time", new HashMap<>());
        kpiLabels.get("Login_page_load_time").put("start", 0L);
        kpiLabels.get("Login_page_load_time").put("end", 0L);
        //Logout_page_load_time kpi
        kpiLabels.put("Logout_page_load_time", new HashMap<>());
        kpiLabels.get("Logout_page_load_time").put("start", 0L);
        kpiLabels.get("Logout_page_load_time").put("end", 0L);
    }
}
