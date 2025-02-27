package com.model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
	
	
	public static void saveUsers() {
		Users users = Users.getInstance();
		ArrayList<User> userList = users.getUsers();
		
		//hard coding for testing
		//ArrayList<User> userList = new ArrayList();
		//userList.add(new User("pplante", "Portia", "Plante", 29, "2309553344"));

		JSONArray jsonUsers = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< userList.size(); i++) {
			jsonUsers.add(getUserJSON(userList.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(USER_TEMP_FILE_NAME)) {
 
            file.write(jsonUsers.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static JSONObject getUserJSON(User user) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(USER_ID, user.getId().toString());
		userDetails.put(USER_USER_NAME, user.getUserName());
		userDetails.put(USER_FIRST_NAME, user.getFirstName());
		userDetails.put(USER_LAST_NAME, user.getLastName());
		userDetails.put(USER_AGE, user.getAge());
		userDetails.put(USER_PHONE_NUMBER, user.getPhoneNumber());
        
        return userDetails;
	}

	public static void main(String[] args){
		DataWriter.saveUsers();
	}
  public static void saveSongs() {
		Songs Songs = Songs.getInstance();
		ArrayList<Song> songList = songs.getSongs();
		
		//hard coding for testing
		//ArrayList<User> userList = new ArrayList();
		//userList.add(new User("pplante", "Portia", "Plante", 29, "2309553344"));

		JSONArray jsonUsers = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< songList.size(); i++) {
			jsonSongs.add(getSongJSON(songList.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(USER_TEMP_FILE_NAME)) {
 
            file.write(jsonSongs.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static JSONObject getSongJSON(Song song) {
		JSONObject songDetails = new JSONObject();
		songDetails.put(USER_ID, song.getId().toString());
		songDetails.put(USER_USER_NAME, song.getUserName());
		songDetails.put(USER_FIRST_NAME, song.getFirstName());
		songDetails.put(USER_LAST_NAME, song.getLastName());
		songDetails.put(USER_AGE, song.getAge());
		songDetails.put(USER_PHONE_NUMBER, song.getPhoneNumber());
        
        return songDetails;
	}

	public static void main(String[] args){
		DataWriter.saveSongs();
	}
}
