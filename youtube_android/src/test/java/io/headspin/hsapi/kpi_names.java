package io.headspin.hsapi;

import java.util.HashMap;

import static io.headspin.global_variable.GlobalVariable.kpiLabels;

public class kpi_names {
    public kpi_names() {
        kpiLabels =new HashMap<>();
        //Launch time kpi
        kpiLabels.put("Launch_time", new HashMap<>());
        kpiLabels.get("Launch_time").put("start", 0L);
        kpiLabels.get("Launch_time").put("end", 0L);
        kpiLabels.get("Launch_time").put("segment_start", 0);
        kpiLabels.get("Launch_time").put("segment_end", 2);
        kpiLabels.get("Launch_time").put("start_sensitivity", 0.954f);
        kpiLabels.get("Launch_time").put("end_sensitivity", 0.972f);
        // search_tab_loadtime
        kpiLabels.put("search_tab_loadtime", new HashMap<>());
        kpiLabels.get("search_tab_loadtime").put("start", 0L);
        kpiLabels.get("search_tab_loadtime").put("end", 0L);
        //Login_page_load_time kpi
        kpiLabels.put("searchtime", new HashMap<>());
        kpiLabels.get("searchtime").put("start", 0L);
        kpiLabels.get("searchtime").put("end", 0L);
        //Logout_page_load_time kpi
        kpiLabels.put("Detail_page_load_time", new HashMap<>());
        kpiLabels.get("Detail_page_load_time").put("start", 0L);
        kpiLabels.get("Detail_page_load_time").put("end", 0L);
    }
}
