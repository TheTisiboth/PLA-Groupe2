package model;

import controller.Options;

public class Boss extends Enemy {

	// static String m_spriteFile = Options.sprites.get("boss");
	// static int m_layer = 1;
	// static double m_startingSpeed = Options.velocities.get("boss"); 
	
	public Boss(Model model, int x, int y, String spriteFile, double startingSpeed, Tile t, int life, int damage) {
		super(model, x, y, spriteFile, startingSpeed, t, life,damage);
		m_layer = Options.layers.get("character");
	}

}
