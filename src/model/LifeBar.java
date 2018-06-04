package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.Options;

public class LifeBar {
	
	Entity m_entity;
	BufferedImage m_life;
	BufferedImage m_bar;
	
	public LifeBar(Entity entity) {
			
		m_entity = entity;
		
		
	}
	
	
	public void paint(Graphics g){
		
		g.drawImage(m_currentSprite,m_pixelX, m_pixelY, Options.TAILLE_CASE, Options.TAILLE_CASE, null);
	}
	
	
	
	
}
