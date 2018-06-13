package model;

import controller.Options;
import main.Teams;

public class Wall extends Entity {

	static int m_layer = 0;

	public Wall(Model model, int x, int y, Tile t, String spriteFile) {
		super(model, x, y, false, spriteFile, t, Teams.Wall);
		m_layer = Options.layers.get("wall");
	}

}
