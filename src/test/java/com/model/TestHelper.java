package com.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestHelper {
    
    public static void corruptFile(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            content = content.substring(0, content.length() / 2); // Cut the file in half
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public static void writeIncompleteUsersFile(String filePath) {
    try {
        JSONParser parser = new JSONParser();
        JSONArray usersArray = (JSONArray) parser.parse(new FileReader(filePath));

        JSONObject user1 = (JSONObject) usersArray.get(0);
        user1.remove("username");
        user1.remove("password");

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(usersArray.toJSONString());
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

public static void writeIncompleteSongsFile(String filePath) {
    try {
        JSONParser parser = new JSONParser();
        JSONArray songsArray = (JSONArray) parser.parse(new FileReader(filePath));

        JSONObject song1 = (JSONObject) songsArray.get(0);
        song1.remove("title");
        song1.remove("artist");

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(songsArray.toJSONString());
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

public static void writeIncompleteLessonsFile(String filePath) {
    try {
        JSONParser parser = new JSONParser();
        JSONArray lessonsArray = (JSONArray) parser.parse(new FileReader(filePath));

        JSONObject lesson1 = (JSONObject) lessonsArray.get(0);
        lesson1.remove("title");
        lesson1.remove("assignments");

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(lessonsArray.toJSONString());
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}


}
