package model;

import controller.Options;

public class Pet extends AliveEntity {

	// static String m_spriteFile = Options.sprites.get("pet");
	// static int m_layer = 1;
	// static double m_startingSpeed = Options.velocities.get("pet"); 

	public Pet(Model model, int x, int y, String spriteFile, double startingSpeed, Tile t, int life, int damage) {
		super(model, x, y, spriteFile, startingSpeed, t, life,damage);
		m_layer = Options.layers.get("character");
	}

}
