package model;

import controller.Options;

// import java.util.List;

// import controller.Options;
// import utils.MapParser;

public class Room{

	Tile[][] m_tiles = new Tile[Options.HAUTEUR][Options.LARGEUR];
	Level m_level;

	public Room(Level level, int mapID){
		m_level = level;
		//parse(mapID)
	}


}
