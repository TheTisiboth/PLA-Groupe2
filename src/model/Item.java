package model;

import controller.Options;

public class Item extends Entity {

	// static String m_spriteFile = Options.sprites.get("item");
	// static int m_layer = 0;
	// static double m_startingSpeed = Options.velocities.get("item"); 


	public Item(Model model, int x, int y, String spriteFile, Tile t, int life) {
		super(model, x, y, true, spriteFile, 0, t, life);
		m_layer = Options.layers.get("character");
	}
}
