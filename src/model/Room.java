package model;

import java.awt.Color;
import java.awt.Graphics;

import controller.Options;
import controller.Options.TileObject;
import utils.MapParser;

// import java.util.List;

// import controller.Options;
// import utils.MapParser;

public class Room{

	TileObject[][] m_startingEntities;
	Entity[][] m_tiles = new Entity[Options.HAUTEUR][Options.LARGEUR];
	Level m_level;

	public Room(Level level, int mapID){
		m_level = level;
		m_startingEntities = MapParser.getMap(mapID+"");
		tileConstructor(m_startingEntities);
	}

	private void tileConstructor(TileObject[][] to){
		for (int i = 0; i < Options.HAUTEUR; i++) {
			for (int j = 0; j < Options.LARGEUR; j++) {
				if(to[i][j].equals(TileObject.WALL)) 
					m_tiles[i][j] = new Wall(new Model(), Color.black, j, i);
				else m_tiles[i][j] = new Wall(new Model(), Color.white, j, i);
			}
		}
	}

	public void paint(Graphics g){
		for (int i = 0; i < Options.HAUTEUR; i++) {
			for (int j = 0; j < Options.LARGEUR; j++) {
				m_tiles[i][j].paint(g);
			}
		}
	}


}
