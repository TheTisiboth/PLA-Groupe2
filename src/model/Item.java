package model;

import controller.Options;

public class Item extends Entity {

	static String m_spriteFile = Options.sprites.get("item");
	static int m_layer = 0;
	static double m_startingSpeed = Options.velocities.get("item"); 

	public Item(Model model, int x, int y, Tile t) {
		super(model, x, y, false, m_spriteFile, m_startingSpeed, t);
	}

}
