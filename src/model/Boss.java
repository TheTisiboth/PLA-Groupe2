package model;

import controller.Options;

public class Boss extends Enemy {

	static String m_spriteFile = Options.sprites.get("boss");
	static int m_layer = 1;
	static double m_startingSpeed = Options.velocities.get("boss"); 

	public Boss(Model model, int x, int y, Tile t,int life) {
		super(model, x, y, m_spriteFile, m_startingSpeed, t, y);
	}

}
