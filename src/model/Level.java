package model;

import java.util.ArrayList;
import java.util.List;

public class Level {

	int m_difficulty;
	int m_nMap;
	List<Enemy> m_enemies = new ArrayList<Enemy>();
	List<Item> m_items = new ArrayList<Item>();
	List<Room> m_rooms = new ArrayList<Room>();
	Boss m_boss;
	Pet m_pet;
	Model m_model;
	
	public Level(int d, int levelID, Model m) {
		m_difficulty = d;
		m_model = m;
		//parse(levelID);
	}



}
