package model;

import controller.Options;

public class Player extends Entity {

	static String m_spriteFile = Options.sprites.get("player");
	static int m_layer = 2;
	static double m_startingSpeed = Options.velocities.get("player"); 

	public Player(Model model, int x, int y) {
		super(model, x, y, true, m_spriteFile, m_startingSpeed);
	}

	public void setPos(int x, int y){
		this.m_pixelX = x;
		this.m_pixelY = y;
	}

}