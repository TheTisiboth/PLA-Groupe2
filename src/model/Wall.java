package model;

import java.awt.Color;
import java.awt.Graphics;

import controller.Options;

public class Wall extends Entity {

	static String m_spriteFile = "assets/sprites/wall.png";
	static int m_layer = 0;


	public Wall(Model model, int x, int y) {
		super(model, x, y, m_spriteFile);
	}

}
