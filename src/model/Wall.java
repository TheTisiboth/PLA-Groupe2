package model;

import java.awt.Color;
import java.awt.Graphics;

import controller.Options;

public class Wall extends Entity {

	Color m_color;

	public Wall(Model model, Color color) {
		super(model);
		m_color = color;
	}
	
	public void paint(Graphics g) {
		g.setColor(m_color);
		g.fillRect(pixelX * Options.LARGEUR, pixelY * Options.HAUTEUR, Options.LARGEUR, Options.HAUTEUR);
	}
	

}
