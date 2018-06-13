package model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Item.ItemType;

public class Level {

	public ArrayList<String> m_enemies;
	public ArrayList<String> m_items;
	public ArrayList<String> m_rooms;
	public ArrayList<String> m_pets;
	public Model m_model;
	
	Room m_currentRoom;
	Iterator<String> m_roomIt;
	
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

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	};
	private static JSONObject m_allPets;
	private static JSONObject m_allItems;

	public Level(int id, Model m) {
		m_model = m;
		
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("assets/level/level"+id+".json"));
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


		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		m_roomIt = m_rooms.iterator();
	}
	
	
	public Enemy getRandomEnemy(Model m, int x, int y, Tile tile) {
		int size = m_enemies.size();
		int rd_value = (int) (Math.random() * size);
		String str = m_enemies.get(rd_value);
		
		JSONObject enemiesList = (JSONObject) Level.m_allEnemies.get(str);
		String enemySprite = (String) enemiesList.get("sprite");
		
		String speedTxt = (String) enemiesList.get("speed");
		double speed = Double.parseDouble(speedTxt);

		String lifeTxt = (String) enemiesList.get("life");
		int life = Integer.parseInt(lifeTxt);

		String dmgTxt = (String) enemiesList.get("dmg");
		int dmg = Integer.parseInt(dmgTxt);
		
		Enemy returnEnemy = new Enemy(m, x, y, enemySprite, speed, tile, life, dmg);
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
		
		JSONObject itemsList = (JSONObject) Level.m_allItems.get(str);
		String itemSprite = (String) itemsList.get("sprite");
		int itemDamage = Integer.parseInt((String)itemsList.get("damage"));
		
		if((String)itemsList.get("type") == "consumable")
			return new Item(m, x, y, itemSprite, tile, itemDamage, ItemType.CONSUMABLE);
		else
			return new Item(m, x, y, itemSprite, tile, itemDamage, ItemType.WEAPON);
	}
	
	public String getRandomRoom() {
		int size = m_rooms.size();
		int rd_value = (int) (Math.random() * size);
		return m_rooms.get(rd_value);
	}
	
	/**
	 * @return the m_currentRoom
	 */
	public Room getCurrentRoom() {
		return m_currentRoom;
	}
	
	public boolean nextRoom() {
		if(m_roomIt.hasNext()){
			m_currentRoom = new Room(this, Integer.parseInt(m_roomIt.next()));
			return true;
		}
		return false;
	}
}
