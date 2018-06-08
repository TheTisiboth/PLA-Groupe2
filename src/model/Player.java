package model;

import controller.Options;

public class Player extends AliveEntity {

	static String m_spriteFile = Options.sprites.get("player");
	static double m_startingSpeed = Options.velocities.get("player"); 

	public Player(Model model, int x, int y, Tile t, int life, int damage) {
		super(model, x, y, m_spriteFile, m_startingSpeed, t, life, damage);
		m_layer = Options.layers.get("character");
	}

}