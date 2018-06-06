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

public class Player extends Entity {

	static String m_spriteFile = Options.sprites.get("player");
	static double m_startingSpeed = Options.velocities.get("player"); 

	public Player(Model model, int x, int y, Tile t) {
		super(model, x, y, true, m_spriteFile, m_startingSpeed, t);
		m_layer = Options.layers.get("character");
	}

}