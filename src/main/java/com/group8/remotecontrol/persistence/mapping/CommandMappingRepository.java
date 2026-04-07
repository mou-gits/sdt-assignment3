package com.group8.remotecontrol.persistence.mapping;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CommandMappingRepository {

    InputStream is = getClass().getResourceAsStream("/com/group8/remotecontrol/persistence/mapping/mapping.json");

    public Map<Integer, String> loadFactoryMappings() {
        HashMap<Integer, String> map = new HashMap<>();

        try (InputStream is = getClass().getResourceAsStream(
                "/com/group8/remotecontrol/persistence/mapping/factory.json")) {

            if (is == null) {
                throw new FileNotFoundException("factory.json not found");
            }

            String content = new String(is.readAllBytes());
            JSONObject json = new JSONObject(content);

            for (int i = 1; i <= 12; i++) {
                String key = "button" + i;
                String value = json.optString(key, "None");
                map.put(i, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }


    public HashMap<Integer, String> loadMappings() {
        HashMap<Integer, String> map = new HashMap<>();

        try (InputStream is = getClass().getResourceAsStream(
                "/com/group8/remotecontrol/persistence/mapping/mapping.json")) {

            if (is == null) {
                throw new FileNotFoundException("mapping.json not found in resources");
            }

            String content = new String(is.readAllBytes());
            JSONObject json = new JSONObject(content);

            for (int i = 1; i <= 12; i++) {
                String key = "button" + i;
                String value = json.optString(key, "None");
                map.put(i, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public void saveMappings(Map<Integer, String> mappings) {
        try {
            JSONObject json = new JSONObject();

            for (int i = 1; i <= 12; i++) {
                json.put("button" + i, mappings.get(i));
            }

            // Write back to the resources folder (development only)
            Path path = Paths.get("src/main/resources/com/group8/remotecontrol/persistence/mapping/mapping.json");
            Files.write(path, json.toString(4).getBytes());

            System.out.println("Mappings saved.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
