package model;

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
	Tile[][] m_tiles;
	Level m_level;
	Tile m_spawn;
	Tile m_exit;
	Model m_model;

	public Room(Level level, int mapID){
		m_level = level;
		m_model = level.m_model;
		m_startingEntities = MapParser.getMap(mapID+"");
		m_tiles = new Tile[Options.LARGEUR][Options.HAUTEUR];
		tileConstructor(m_startingEntities);
	}

	private void tileConstructor(TileObject[][] to){
		for (int i = 0; i < Options.HAUTEUR; i++) {
			for (int j = 0; j < Options.LARGEUR; j++) {
				m_tiles[j][i] = new Tile(to[i][j], j, i, m_level.m_model);
				switch (to[i][j]) {
					case EXIT:
						m_exit = m_tiles[j][i];
						break;
					case SPAWN:
						m_spawn = m_tiles[j][i];
						m_model.getPlayer().setPosition(j*Options.TAILLE_CASE, i*Options.TAILLE_CASE);
						m_spawn.putEntity(m_model.getPlayer().getLayer(), m_model.getPlayer());
						break;
					default:
						break;
				}
			}
		}
	}

	public void paint(Graphics g){
		for (int i = 0; i < 4; i++) {
			for (int x = 0; x < Options.LARGEUR; x++) {
				for (int y = 0; y < Options.HAUTEUR; y++) {
					m_tiles[x][y].paint(g ,i);
				}
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		return m_tiles[x][y];
	}

	public Tile[][] getTiles(){
		return m_tiles;
	}


}
