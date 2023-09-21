package io.headspin.hsapi;

import java.util.HashMap;

import static io.headspin.global_variable.GlobalVariable.kpiLabels;

public class kpi_names {
    public kpi_names() {
        kpiLabels =new HashMap<>();


        kpiLabels.put("Launch_time", new HashMap<>());
        kpiLabels.get("Launch_time").put("start", 0L);
        kpiLabels.get("Launch_time").put("end", 0L);


        kpiLabels.put("search_tab_loadtime", new HashMap<>());
        kpiLabels.get("search_tab_loadtime").put("start", 0L);
        kpiLabels.get("search_tab_loadtime").put("end", 0L);


        kpiLabels.put("searchtime", new HashMap<>());
        kpiLabels.get("searchtime").put("start", 0L);
        kpiLabels.get("searchtime").put("end", 0L);


        kpiLabels.put("Detail_page_load_time", new HashMap<>());
        kpiLabels.get("Detail_page_load_time").put("start", 0L);
        kpiLabels.get("Detail_page_load_time").put("end", 0L);
    }
}
