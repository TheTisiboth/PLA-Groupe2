package model;

import java.awt.Color;
import java.awt.Graphics;

import java.util.*;

import controller.Options;
import controller.Options.TileObject;
import utils.MapParser;

// import java.util.List;

// import controller.Options;
// import utils.MapParser;

public class Room{

	TileObject[][] m_startingEntities;
	List<Entity> m_entities;
	Level m_level;
	int[] m_spawn;
	int[] m_exit;

	public Room(Level level, int mapID){
		m_level = level;
		m_startingEntities = MapParser.getMap(mapID+"");
		m_entities = new ArrayList<Entity>();
		tileConstructor(m_startingEntities);
	}

	private void tileConstructor(TileObject[][] to){
		for (int i = 0; i < Options.HAUTEUR; i++) {
			for (int j = 0; j < Options.LARGEUR; j++) {
				switch (to[i][j]) {
					case WALL:
						m_entities.add(new Wall(new Model(), j, i));			
						break;
					case ENEMY:
						m_entities.add(new Boss(new Model(), j, i));
						break;
					case BOSS:
						m_entities.add(new Boss(new Model(), j, i));
						break;
					case EXIT:
						m_exit = new int[]{j,i};
						break;
					case SPAWN:
						m_spawn = new int[]{j,i};
						break;
					case PET:
						m_entities.add(new Pet(new Model(), j, i));
						break;
					case GROUND:
						break;
					case ITEM:
						m_entities.add(new Item(new Model(), j, i));
						break;

					default:
						break;
				}
			}
		}
	}

	public void paint(Graphics g){

		for (int i = 0; i < m_entities.size(); i++) {
			m_entities.get(i).paint(g);
		}

	}


}
