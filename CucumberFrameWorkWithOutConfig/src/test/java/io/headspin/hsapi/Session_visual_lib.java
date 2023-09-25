package io.headspin.hsapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static io.headspin.hsapi.GlobalVariable.*;

public class Session_visual_lib {
    hsAPI API;

    public void run_record_session_info() {
        Access_token = get_access_token();
        API = new hsAPI();
        run_add_annotation_data(); // method to add kpi in desired region and
        run_add_session_data();
    }

    public void run_add_annotation_data() {
        get_video_start_timestamp();
        wait_for_session_video_becomes_available();
        add_kpi_labels();
    }

    private void add_kpi_labels() {
        for (String labelKey : kpiLabels.keySet()) {
            System.out.println();
            Map<String, Number> label = kpiLabels.get(labelKey); //label={start:<time>,end:<time>,segment_start:<value>,segment_end:<value>}
            Long label_start_time;
            Long label_end_time;
            if (((long) label.get("start")>0) && ((long) label.get("end")>0)) {
                label_start_time = (long) ((long)label.get("start")-video_start_timestamp);
                if (label_start_time<0) {
                    label_start_time = 0L;
                }
                label_end_time = (long) ((long) label.get("end")-video_start_timestamp);
                API.add_label(labelKey,"desired region",label_start_time,label_end_time);
                System.out.println(labelKey+" : label id add for the desired region");
                // till here its working
                float start_sensitivity = 0.935F;
                float end_sensitivity = 0.975F;
                float video_box = 0;
                if (label.containsKey("start_sensitivity")) {
                    start_sensitivity = Float.valueOf( (float) label.get("start_sensitivity"));
                }
                if (label.containsKey("end_sensitivity")) {
                    end_sensitivity = Float.valueOf( (float) label.get("end_sensitivity"));
                }
                if (label.containsKey("video_box")) {
                    video_box = Float.valueOf( (float) label.get("video_box"));
                }
                long new_label_start_time=label_start_time;
                long new_label_end_time=label_end_time;
                System.out.println("new_label_start_time1 "+label_end_time);
                if ((label.containsKey("segment_start")) && (label.containsKey("segment_end"))) {
                    List screen_change_list = get_screenchange_list_divide(labelKey,label_start_time,label_end_time,start_sensitivity,end_sensitivity,video_box);
                    System.out.println("screen change list "+screen_change_list); //[12334456,]
                    try {
                        if (screen_change_list.size()>0) {
                            //content need to be added
                            int segmentStart = (int) label.get("segment_start");
                            int segmentEnd = (int) label.get("segment_end");
                            Long s = (Long) screen_change_list.get(segmentStart);
                            Long e =  (Long) screen_change_list.get(segmentEnd);
                            new_label_start_time = s.longValue();
                            new_label_end_time = e.longValue();
                        }
                    } catch (Exception e) {
                        //content need to be added
                        e.printStackTrace();
                    }

                }
                else {
                    if ((label.containsKey("start")) && (label.containsKey("end"))) {
                        Map<String, Object> pageload = API.get_pageloadtime(Session_id,labelKey,label_start_time,label_end_time,start_sensitivity,end_sensitivity,video_box);
//                        System.out.println("required :"+pageload);
                        if (pageload.containsKey("page_load_regions")) {
//                            System.out.println("dharnish1");
                            List<Map<String, Object>> pageLoadRegionsList = (List<Map<String, Object>>) pageload.get("page_load_regions");
                            if (!pageLoadRegionsList.isEmpty() && !pageLoadRegionsList.get(0).containsKey("error_msg")) {
//                                System.out.println("dharnish2");
//                                new_label_start_time = pageLoadRegionsList.get(0).get("start_time");
//                                new_label_end_time = pageLoadRegionsList.get(0).get("end_time");
                                Object startTimeObject = pageLoadRegionsList.get(0).get("start_time");
                                Object endTimeObject = pageLoadRegionsList.get(0).get("end_time");
                                if (startTimeObject instanceof Integer) {
                                    new_label_start_time = ((Integer) startTimeObject).longValue();
                                } else if (startTimeObject instanceof Long) {
                                    new_label_start_time = (Long) startTimeObject;
                                }

                                if (endTimeObject instanceof Integer) {
                                    new_label_end_time = ((Integer) endTimeObject).longValue();
                                } else if (endTimeObject instanceof Long) {
                                    new_label_end_time = (Long) endTimeObject;
                                }
//                                System.out.println(new_label_start_time+"  "+new_label_end_time);
                            }
                        }

                    }
                }
//                System.out.println("dharnish3");
                if ((new_label_end_time>0) && (new_label_start_time>0)) {
                    kpiLabels.get(labelKey).put("start", new_label_start_time);
                    kpiLabels.get(labelKey).put("end", new_label_end_time);
                }
                System.out.println("call add_label for KPI_LABEL_CATEGORY");
                API.add_label(labelKey,KPI_LABEL_CATEGORY,new_label_start_time,new_label_end_time);
                System.out.println(labelKey+" Label under all region is been added");
                System.out.println();
                System.out.println();
            }
        }
//        System.out.println(kpiLabels);
    }

    public List get_screenchange_list_divide(String labelKey, Long labelStartTime, Long labelEndTime, float startSensitivity, float endSensitivity, float videoBox) {
        List screen_change_list = new ArrayList<>();
        int sn = 0;
        int snlimit = 10;
        int segmentTimeStep = 100;
        long new_label_start_time = 0, new_label_end_time = 0;
        Map<String,Object> list = API.get_pageloadtime(Session_id,(labelKey+sn),labelStartTime,labelEndTime,startSensitivity,endSensitivity,videoBox);
        System.out.println("output of get_screenchange "+list);
        if (list.containsKey("page_load_regions") && !(list.get("page_load_regions").toString().contains("error_msg"))) {
            while (true) {
                List<Map<String, Object>> pageLoadRegions = (List<Map<String, Object>>) list.get("page_load_regions");
                Integer start_time= (Integer) pageLoadRegions.get(0).get("start_time");
                Integer end_time=(Integer) pageLoadRegions.get(0).get("end_time");
                System.out.println(start_time+" start_time");
                screen_change_list.add(start_time.longValue());
                screen_change_list.add(end_time.longValue());
                sn++;
                if (snlimit < sn) {
                    break;
                }
                Integer starttime= (Integer) pageLoadRegions.get(0).get("start_time") +segmentTimeStep;
                Integer endtime=(Integer) pageLoadRegions.get(0).get("end_time") -segmentTimeStep;
                new_label_start_time = starttime.longValue();
                new_label_end_time = endtime.longValue();
                if (new_label_start_time > new_label_end_time) {
                    break;
                }
                list = API.get_pageloadtime(Session_id, (labelKey + sn),new_label_start_time, new_label_end_time,startSensitivity, endSensitivity,videoBox);
                if (pageLoadRegions.get(0).get("start_time") ==null || pageLoadRegions.get(0).get("end_time") ==null || (list.get("page_load_regions").toString().contains("error_msg")))
                    break;
                if(!list.containsKey("page_load_regions") || "error_msg".equals(list.get("page_load_regions").toString())) {
                    if (list.containsKey("status")) {
                        // Handle status
                        //  "Page Load Error"
                        System.out.println("error at 194");
                    }
                    break;
                }
            }
        }
        else {

        }
        screen_change_list = (List) screen_change_list.stream().distinct().sorted().collect(Collectors.toList());
        System.out.println(labelKey+ "screen_change " + screen_change_list);
        return screen_change_list;
    }

    private void wait_for_session_video_becomes_available() {
        long durationInSeconds = 600; // 600 seconds or 10 minutes
        long endTimeMillis = System.currentTimeMillis() + (durationInSeconds * 1000);
        while (System.currentTimeMillis() < endTimeMillis) {
            Map<String, Object> status = API.get_session_video_metadata();
            if (status != null && status.containsKey("video_duration_ms")) {
                System.out.println("wait_for_session_video_becomes_available: "+status);
                break;
            }
        }
    }

    public void get_video_start_timestamp() {
        Boolean wait_until_capture_complete = true;
        long durationInSeconds = 600; // 600 seconds or 10 minutes
        long endTimeMillis = System.currentTimeMillis() + (durationInSeconds * 1000); //9.12
        if (wait_until_capture_complete) {
            while (System.currentTimeMillis() < endTimeMillis) {
                Map<String, Object> capture_timestamp = API.get_capture_timestamp();
//                System.out.println("get_video_start_timestamp "+capture_timestamp);
                if (capture_timestamp.containsKey("capture-complete")) {
                    video_start_timestamp = (Double) capture_timestamp.get("capture-started")*1000;
                    System.out.println("capture_timestamp: "+capture_timestamp);
                    break;
                }
            }
        }
    }

    public void run_add_session_data() {
        Map<String, Object> session = get_general_session_data();
        String response1 = API.add_session_data(session);
//        System.out.println(response1);
        String description = "";
        List<Map<String, Object>> data = (List<Map<String, Object>>) session.get("data");
        for(Map<String, Object> entry:data) {
            String key = entry.get("key").toString();
            String value = entry.get("value").toString();
            description+= key + " : " + value + ",\n";
        }
//        System.out.println(kpiLabels);
        for(Map.Entry<String, Map<String, Number>> kpi:kpiLabels.entrySet()) {
            String outerKey = kpi.getKey();
            Map<String, Number> innerMap = kpi.getValue();
            long st = (long) innerMap.get("start");
            long et = (long) innerMap.get("end");
            long r = et-st;
            session_data.put(outerKey, String.valueOf(r));
            description+= outerKey + " : " + r + ",\n";
        }
//        System.out.println("description is "+description);
        API.update_session_name_and_description(description);
//        System.out.println("description "+response2);
        API.add_session_tags();
        System.out.println(session_data);
        System.out.println("Tag is been added");
    }

    public Map<String, Object> get_general_session_data() {
        Map<String,Object> session = new HashMap<>();
        session.put("session_id",Session_id);
        session.put("status",status); //{"session_id":<Session_id>,"status":"Passed",}
        List<Map> data = new ArrayList<>(); //data=[]
        Map<String,String> temp1 = new HashMap<>(); //temp1 = {}
        temp1.put("key","status");
        temp1.put("value",status); //temp1 = {"key":"status","values":"Passed"}
        data.add(temp1); //data = [{"key":"status","values":"Passed"}]
        Map<String,String> temp2 = new HashMap<>();
        temp2.put("key","fail_reason"); //temp2 = {"key":"fail_reason","values":<fail_reason>}
        temp2.put("value",status);
        data.add(temp2);//data = [{"key":"status","values":"Passed"},{"key":"fail_reason","values":<fail_reason>}]
        session.put("data",data); //{"session_id":<Session_id>,"status":"Passed",[{"key":"status","values":"Passed"},"data":{"key":"fail_reason","values":<fail_reason>}]}
//        System.out.println(session);
        return session;
    }

    public String get_access_token() {
        String url = Appium_Url;
        int startIndex = url.indexOf("/v0/");

        if (startIndex != -1) {
            // Increment the startIndex to skip "/v0/"
            startIndex += 4;

            // Find the ending position by searching for the next "/"
            int endIndex = url.indexOf("/", startIndex);

            if (endIndex != -1) {
                // Extract the number using substring
                String extractedNumber = url.substring(startIndex, endIndex);

                // Print the extracted number
                // System.out.println("Extracted Number: " + extractedNumber);
                return extractedNumber;
            } else {
                System.out.println("Number not found in the URL.");
            }
        } else {
            System.out.println("Number not found in the URL.");
        }
        return "Number not found in the URL.";
    }

}
