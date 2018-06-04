package utils;

import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Enemy;
import model.Entity;
import model.Item;
import model.Pet;

public class Level {

	public ArrayList<String> m_enemies;
	public ArrayList<String> m_items;
	public ArrayList<String> m_rooms;
	public ArrayList<String> m_pets;
	public String m_boss;

	private static JSONObject m_allEnemies; {
		{
			JSONParser parser = new JSONParser();
			try {
				Object obj = parser.parse(new FileReader("assets/level/enemies.json"));
				JSONObject m_allEnemies = (JSONObject) obj;
				
				Object obj2 = parser.parse(new FileReader("assets/level/pets.json"));
				JSONObject m_allPets = (JSONObject) obj2;
				
				Object obj3 = parser.parse(new FileReader("assets/level/items.json"));
				JSONObject m_allItems = (JSONObject) obj3;
				
				Object obj4 = parser.parse(new FileReader("assets/level/boss.json"));
				JSONObject m_allBoss = (JSONObject) obj4;
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	};
	private static JSONObject m_allPets;
	private static JSONObject m_allItems;
	private static JSONObject m_allBoss;
	
	
	public static void main(String[] args) {
		Level test = new Level("assets/level.json");
		System.out.println(test.getRandomRoom());
		System.out.println(test.getRandomEnemy());
		System.out.println(test.getRandomPet());
		System.out.println(test.getRandomItem());
	}

	public Level(String str) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("assets/level/level.json"));
			JSONObject jsonObject = (JSONObject) obj;

			m_enemies = new ArrayList<String>();
			JSONArray enemies = (JSONArray) jsonObject.get("enemies");
			for (Object o : enemies) {
				m_enemies.add("" + o);
			}

			m_items = new ArrayList<String>();
			JSONArray items = (JSONArray) jsonObject.get("items");
			for (Object o : items) {
				m_items.add("" + o);
			}

			m_rooms = new ArrayList<String>();
			JSONArray rooms = (JSONArray) jsonObject.get("rooms");
			for (Object o : rooms) {
				m_rooms.add("" + o);
			}

			m_pets = new ArrayList<String>();
			JSONArray pets = (JSONArray) jsonObject.get("pets");
			for (Object o : pets) {
				m_pets.add("" + o);
			}

			String boss = (String) jsonObject.get("boss");
			m_boss = ("" + boss);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Enemy getRandomEnemy() {
		int size = m_enemies.size();
		int rd_value = (int) (Math.random() * size);
		String str = m_enemies.get(rd_value);
		
		JSONObject enemiesList = (JSONObject) Level.m_allEnemies.get(str);
		String enemySprite = (String) enemiesList.get("sprite");
		Double speed = (Double) enemiesList.get("speed");
		
		Enemy returnEnemy = new Enemy(null, rd_value, rd_value, enemySprite);
		return returnEnemy;
	}

	public Pet getRandomPet() {
		int size = m_pets.size();
		int rd_value = (int) (Math.random() * size);
		String str = m_pets.get(rd_value);
		
		JSONObject petsList = (JSONObject) Level.m_allPets.get(str);
		String petSprite = (String) petsList.get("sprite");
		Double speed = (Double) petsList.get("speed");
		
		Pet returnPet = new Pet(null, rd_value, rd_value);
		return returnPet;
	}

	public Item getRandomItem() {
		int size = m_items.size();
		int rd_value = (int) (Math.random() * size);
		String str = m_items.get(rd_value);
		
		JSONObject itemsList = (JSONObject) Level.m_allItems.get(str);
		String itemSprite = (String) itemsList.get("sprite");
		Double speed = (Double) itemsList.get("speed");
		
		Item returnItem = new Item(null, rd_value, rd_value);
		return returnItem;
	}


	public String getRandomRoom() {
		int size = m_rooms.size();
		int rd_value = (int) (Math.random() * size);
		return m_rooms.get(rd_value);
	}
}
