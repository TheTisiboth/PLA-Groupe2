package model;

import controller.Options;

public class Pet extends Entity {

	static String m_spriteFile = Options.sprites.get("pet");
	static int m_layer = 1;
	static double m_startingSpeed = Options.velocities.get("pet"); 

	public Pet(Model model, int x, int y, Tile t) {
		super(model, x, y, true, m_spriteFile, m_startingSpeed, t);
	}

}
