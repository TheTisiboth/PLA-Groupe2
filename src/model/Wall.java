package model;

import controller.Options;

public class Wall extends Entity {


	static String m_spriteFile = Options.sprites.get("wall");
	static int m_layer = 0;
	static double m_startingSpeed = Options.velocities.get("wall"); 


	public Wall(Model model, int x, int y, Tile t) {
		super(model, x, y, false, m_spriteFile, m_startingSpeed, t);
		m_layer = Options.layers.get("wall");
	}

}
