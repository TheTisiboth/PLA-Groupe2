package model;

import controller.Options;

public class Player extends Entity {

	static String m_spriteFile = Options.sprites.get("player");
	//static int m_layer = 1;
	static double m_startingSpeed = Options.velocities.get("player"); 

	public Player(Model model, int x, int y, Tile t) {
		super(model, x, y, true, m_spriteFile, m_startingSpeed, t);
		m_layer = Options.layers.get("character");
		System.out.println("bite" +m_layer);
	}
}