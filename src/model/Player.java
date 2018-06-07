package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import controller.Options;

public class Player extends AliveEntity {

	static String m_spriteFile = Options.sprites.get("player");
	static double m_startingSpeed = Options.velocities.get("player"); 


	public Player(Model model, int x, int y, Tile t, int life, int damage) {
		super(model, x, y, m_spriteFile, m_startingSpeed, t, life,damage);
		m_layer = Options.layers.get("character");
	}

}