package model;

import java.awt.Color;
import java.awt.Graphics;

import controller.Options;

public class Wall extends Entity {

	Color m_color;

	public Wall(Model model, Color color, int x, int y) {
		super(model);
		m_color = color;
		pixelX = x;
		pixelY = y;
	}
	
	public void paint(Graphics g) {
		g.setColor(m_color);
		g.fillRect(pixelX * Options.TAILLE_CASE, pixelY * Options.TAILLE_CASE, Options.TAILLE_CASE, Options.TAILLE_CASE);
	}

}
