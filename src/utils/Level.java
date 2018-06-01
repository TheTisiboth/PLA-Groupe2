package utils;

import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Entity;

public class Level {

	public ArrayList<String> m_enemies;
	public ArrayList<String> m_items;
	public ArrayList<String> m_rooms;
	public ArrayList<String> m_pets;
	public String m_boss;

	public static void main (String[]args) {
		Level test = new Level("assets/level.json");
	}
	
	public Level(String str) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("assets/level.json"));
			JSONObject jsonObject = (JSONObject) obj;

			m_enemies = new ArrayList<String>();
			JSONArray enemies = (JSONArray) jsonObject.get("enemies");
			for (Object o : enemies) {
				m_enemies.add(""+o);
			}
			
			m_items = new ArrayList<String>();
			JSONArray items = (JSONArray) jsonObject.get("items");
			for (Object o : items) {
				m_items.add(""+o);
			}

			m_rooms = new ArrayList<String>();
			JSONArray rooms = (JSONArray) jsonObject.get("rooms");
			for (Object o : rooms) {
				m_rooms.add(""+o);
			}

			m_pets = new ArrayList<String>();
			JSONArray pets = (JSONArray) jsonObject.get("pets");
			for (Object o : pets) {
				m_pets.add(""+o);
			}

			String boss = (String) jsonObject.get("boss");
			m_boss=(""+boss);

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

	}
}
