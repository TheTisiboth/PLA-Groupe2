package model;

import controller.Options;
import main.Teams;

public class Wall extends Entity {


	static String m_spriteFile = Options.sprites.get("wall");
	static int m_layer = 0;

	public Wall(Model model, int x, int y, Tile t, int life) {
		super(model, x, y, false, m_spriteFile, t, Teams.Wall);
		m_layer = Options.layers.get("wall");
	}

}
