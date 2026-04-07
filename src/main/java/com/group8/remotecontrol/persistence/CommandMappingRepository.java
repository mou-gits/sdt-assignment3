package com.group8.remotecontrol.persistence;

import org.json.JSONObject;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CommandMappingRepository {

    private static final String FILE_PATH = "src/main/java/com/group8/remotecontrol/persistence/mapping/mapping.json";

    public HashMap<Integer, String> loadMappings() {
        HashMap<Integer, String> map = new HashMap<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
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

            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(json.toString(4)); // pretty print
            writer.close();

            System.out.println("Mappings saved.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
