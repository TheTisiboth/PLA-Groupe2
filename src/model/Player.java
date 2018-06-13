package model;

import java.awt.image.BufferedImage;

import controller.Options;
import main.Teams;
import utils.Animation;

public class Player extends AliveEntity {

	static String m_spriteFile = Options.sprites.get("player");
	static double m_startingSpeed = Options.velocities.get("player"); 

	public Player(Model model, int x, int y, Tile t, int life, int damage) {
		super(model, x, y, m_spriteFile, m_startingSpeed, t, life, damage, Teams.Team);
		m_layer = Options.layers.get("character");

		BufferedImage[] m_walkingUpIm = {m_sprite.getSprite(0, 0), m_sprite.getSprite(2, 0)};
		BufferedImage[] m_defaultUpIm = {m_sprite.getSprite(1, 0)};
		BufferedImage[] m_walkingDownIm = {m_sprite.getSprite(0, 1), m_sprite.getSprite(2, 1)};
		BufferedImage[] m_defaultDownIm = {m_sprite.getSprite(1, 1)};
		BufferedImage[] m_walkingLeftIm = {m_sprite.getSprite(0, 2), m_sprite.getSprite(2, 2)};
		BufferedImage[] m_defaultLeftIm = {m_sprite.getSprite(1, 2)};
		BufferedImage[] m_walkingRightIm = {m_sprite.getSprite(0, 3), m_sprite.getSprite(2, 3)}; 
		BufferedImage[] m_defaultRightIm = {m_sprite.getSprite(1, 3)};

		m_walkingLeft = new Animation(m_walkingLeftIm, m_animationSpeed);
		m_walkingRight = new Animation(m_walkingRightIm, m_animationSpeed);
		m_walkingUp = new Animation(m_walkingUpIm, m_animationSpeed);
		m_walkingDown = new Animation(m_walkingDownIm, m_animationSpeed);
		m_defaultLeft = new Animation(m_defaultLeftIm, m_animationSpeed);
		m_defaultRight = new Animation(m_defaultRightIm, m_animationSpeed);
		m_defaultUp = new Animation(m_defaultUpIm, m_animationSpeed);
		m_defaultDown = new Animation(m_defaultDownIm, m_animationSpeed);


	}
	
	

}