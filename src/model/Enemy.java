package model;

import controller.Options;

public class Enemy extends AliveEntity {
	
	// static int m_layer = 1;

	public Enemy(Model model, int x, int y, String filename, double speed, Tile t, int life) {
		super(model, x, y, filename, speed, t, life);
		m_layer = Options.layers.get("character");
	}

}
