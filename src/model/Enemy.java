package model;

import controller.Options;

public class Enemy extends Entity {
	
	// static int m_layer = 1;

	public Enemy(Model model, int x, int y, String spriteFile, double speed, Tile t) {
		super(model, x, y, true, spriteFile, speed, t);
		m_layer = Options.layers.get("character");
	}

}
