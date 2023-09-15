package io.headspin.hsapi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.headspin.global_variable.GlobalVariable.*;
// make sure Access_token,Session_id,status,Testname,KPI_LABEL_CATEGORY,kpiLabels,video_start_timestamp,session_data,Appium_Url
public class hsAPI {
    String device_list_url = "https://api-dev.headspin.io/v0/devices";
    String get_auto_config = "https://api-dev.headspin.io/v0/devices/automation-config";
    String url_root = "https://api-dev.headspin.io/v0/";
    Map<String,String> headers = new HashMap<>();

    //hsAPI constuctor
//**********************************************************************************************************************
    public hsAPI() {
        headers.put("Authorization", "Bearer " + Access_token); //{"Authorization":"Bearer <access token>"}
    }


    //add_session_data method
//**********************************************************************************************************************
    public String add_session_data(Map<String, Object> session) {
        String request_url = url_root + "perftests/upload";
        try {
            // Create the URL for the POST request
            URL url = new URL(request_url);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            // Set the request headers
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // Enable input and output streams for the connection
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Set the request body as JSON
            connection.setRequestProperty("Content-Type", "application/json");

            // Convert the session map to JSON and write it to the request body
            String jsonBody = convertMapToJson(session);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            String responseBody = connection.getResponseMessage();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read and process the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                connection.disconnect();
                System.out.println("general session tag is been added");
                return responseBody;
            } else {
                // Handle non-OK response codes or errors
                // You can check responseCode and take appropriate action
                // Close the connection
                connection.disconnect();

                return null; // Or handle the error appropriately
            }

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
            return null; // Or handle the error appropriately
        }
    }

    //update_session_name_and_description
//**********************************************************************************************************************
    public String update_session_name_and_description(String description) {
        String request_url = url_root + "sessions/" + Session_id + "/description";
        try {
            URL url = new URL(request_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            Map<String, Object> dataPayload = new HashMap<>();
            dataPayload.put("name", Testname);
            dataPayload.put("description", description);
            String jsonBody = convertMapToJson(dataPayload);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }
            int responseCode = connection.getResponseCode();
            String responseBody = connection.getResponseMessage();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read and process the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                connection.disconnect();
                System.out.println("Description is been added");
                return responseBody;
            } else {
                connection.disconnect();
                return null; // Or handle the error appropriately
            }


        } catch (IOException e) {
            e.printStackTrace();
            return null; // Or handle the error appropriately
        }
    }
    //get_capture_timestamp
//**********************************************************************************************************************
    public Map<String, Object> get_capture_timestamp() {
        String requestUrl = url_root + "sessions/" + Session_id + "/timestamps";
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            int responseCode = connection.getResponseCode();
            String responseBody = connection.getResponseMessage();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read and parse the JSON response into a Map
                ObjectMapper objectMapper = new ObjectMapper();
                TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};
                Map<String, Object> responseMap = objectMapper.readValue(connection.getInputStream(), typeRef);
                return responseMap;
            } else {
                connection.disconnect();
                return null; // Or handle the error appropriately
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null; // Or handle the error appropriately
        }
    }

    //get_capture_timestamp
//**********************************************************************************************************************
    public Map<String, Object> get_session_video_metadata() {
        String requestUrl = "https://api-dev.headspin.io/v0/sessions/"+Session_id+"/video/metadata";
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            int responseCode = connection.getResponseCode();
            String responseBody = connection.getResponseMessage();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read and parse the JSON response into a Map
                ObjectMapper objectMapper = new ObjectMapper();
                TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};
                Map<String, Object> responseMap = objectMapper.readValue(connection.getInputStream(), typeRef);
                System.out.println("get_session_video_metadata responce: "+responseMap);
                return responseMap;
            } else {
                connection.disconnect();
                return null; // Or handle the error appropriately
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null; // Or handle the error appropriately
        }

    }

    //add_label
//**********************************************************************************************************************
    public String add_label(String labelKey, String desiredRegion, long start_time, long end_time) {
        String request_url = url_root + "sessions/" + Session_id + "/label/add";
        Map<String,Object> data_payload = new HashMap<>();
        data_payload.put("name",labelKey);
        data_payload.put("category",desiredRegion);
        data_payload.put("start_time",Long.toString(start_time));
        data_payload.put("end_time",Long.toString(end_time));
        data_payload.put("data", null);
        data_payload.put("pinned",false);
        data_payload.put("label_type","user");
//        System.out.println("add_label: "+data_payload);
        try {
            URL url = new URL(request_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            String jsonBody = convertMapToJson(data_payload);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }
            int responseCode = connection.getResponseCode();
            String responseBody = connection.getResponseMessage();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read and process the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                connection.disconnect();
                return responseBody;
            } else {
                connection.disconnect();
                return null; // Or handle the error appropriately
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
            return null; // Or handle the error appropriately
        }

    }

    //get_pageloadtime
//**********************************************************************************************************************
    public Map<String, Object> get_pageloadtime(String sessionId, String labelKey, Long labelStartTime, Long labelEndTime, Long startSensitivity, Long endSensitivity, Long videoBox) {
        String request_url = url_root + "sessions/analysis/pageloadtime/" + Session_id;
//        HashMap<String, List<Map<String,Object>>> data_payload = new HashMap<>();
        HashMap<String, Object> data_payload = new HashMap<>();
        List<HashMap<String, Object>> region_times = new ArrayList<>();
        HashMap<String,Object> start_end = new HashMap<>();
        start_end.put("start_time",Long.toString(labelStartTime));
        start_end.put("end_time",Long.toString(labelEndTime));
        start_end.put("name",labelKey);
        region_times.add(start_end);
        data_payload.put("regions",region_times);
        data_payload.put("start_sensitivity",startSensitivity);
        data_payload.put("end_sensitivity",endSensitivity);
        data_payload.put("video_box",videoBox);
        try {
            URL url = new URL(request_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            String jsonBody = convertMapToJson(data_payload);
            System.out.println(jsonBody);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }
            int responseCode = connection.getResponseCode();
            String responseBody = connection.getResponseMessage();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read and process the response
                // Read and parse the JSON response into a Map
                ObjectMapper objectMapper = new ObjectMapper();
                TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};
                Map<String, Object> responseMap = objectMapper.readValue(connection.getInputStream(), typeRef);
                return responseMap;
            } else {
                connection.disconnect();
                return null; // Or handle the error appropriately
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
            return null; // Or handle the error appropriately
        }
    }



    //convertMapToJson
//**********************************************************************************************************************
    private String convertMapToJson(Map<String, Object> map) {
        // Convert the map to JSON using your preferred JSON library
        // Here's a basic example using Jackson ObjectMapper
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Handle the exception
            return null; // Or handle the error appropriately
        }
    }
}
