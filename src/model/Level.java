package model;

import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Boss;
import model.Enemy;
import model.Entity;
import model.Item;
import model.Model;
import model.Pet;
import model.Room;
import model.Tile;

public class Level {

	public ArrayList<String> m_enemies;
	public ArrayList<String> m_items;
	public ArrayList<String> m_rooms;
	public ArrayList<String> m_pets;
	public String m_boss;
	public Model m_model;
	
	Room m_currentRoom;
	
	private static JSONObject m_allEnemies;
	{
		{
			JSONParser parser = new JSONParser();
			try {
				Object obj = parser.parse(new FileReader("assets/level/enemies.json"));
				m_allEnemies = (JSONObject) obj;

				Object obj2 = parser.parse(new FileReader("assets/level/pets.json"));
				m_allPets = (JSONObject) obj2;

				Object obj3 = parser.parse(new FileReader("assets/level/items.json"));
				m_allItems = (JSONObject) obj3;

				Object obj4 = parser.parse(new FileReader("assets/level/boss.json"));
				m_allBoss = (JSONObject) obj4;

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

	public Level(String str, Model m) {
		m_model = m;
		
		
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
	
	public void loadLevel() {
		m_currentRoom = new Room(this, 0);
	}

	public Enemy getRandomEnemy(Model m, int x, int y, Tile tile) {
		int size = m_enemies.size();
		int rd_value = (int) (Math.random() * size);
		String str = m_enemies.get(rd_value);

		JSONObject enemiesList = (JSONObject) Level.m_allEnemies.get(str);
		String enemySprite = (String) enemiesList.get("sprite");

		String speedTxt = (String) enemiesList.get("speed");
		double speed = Double.parseDouble(speedTxt);

		//TODO mettre la vraie vie
		Enemy returnEnemy = new Enemy(m, x, y, enemySprite, speed, tile, 10, 2);
		return returnEnemy;
	}

	public Pet getRandomPet(Model m, int x, int y, Tile tile) {
		int size = m_pets.size();
		int rd_value = (int) (Math.random() * size);
		String str = m_pets.get(rd_value);

		JSONObject petsList = (JSONObject) Level.m_allPets.get(str);
		String petSprite = (String) petsList.get("sprite");

		String speedTxt = (String) petsList.get("speed");
		double speed = Double.parseDouble(speedTxt);

		//TODO mettre la vraie vie
		Pet returnPet = new Pet(m, x, y, petSprite, speed, tile, 10, 2);
		return returnPet;
	}

	public Item getRandomItem(Model m, int x, int y, Tile tile) {
		int size = m_items.size();
		int rd_value = (int) (Math.random() * size);
		String str = m_items.get(rd_value);
		
		System.out.print(str);

		JSONObject itemsList = (JSONObject) Level.m_allItems.get(str);
		String itemSprite = (String) itemsList.get("sprite");
		if(str.equals("sword")) {
			String damage = (String) itemsList.get("damage");
			Item returnItem = new Sword(m, x, y, itemSprite, tile, -1,str,Integer.parseInt(damage));
			return returnItem;
		}
		
		//TODO mettre la vraie vie
		Item returnItem = new Item(m, x, y, itemSprite, tile, -1,str);
		return returnItem;
	}

	public String getRandomRoom() {
		int size = m_rooms.size();
		int rd_value = (int) (Math.random() * size);
		return m_rooms.get(rd_value);
	}

	public Boss getBoss(Model m, int x, int y, Tile tile) {
		JSONObject bossList = (JSONObject) Level.m_allBoss.get(m_boss);
		String bossSprite = (String) bossList.get("sprite");

		Double speed = (Double) bossList.get("speed");
		
		//TODO mettre la vraie vie
		Boss returnBoss = new Boss(m, x, y, bossSprite, speed, tile, 10, 4);
		return returnBoss;
	}
	
	/**
	 * @return the m_currentRoom
	 */
	public Room getCurrentRoom() {
		return m_currentRoom;
	}

}
