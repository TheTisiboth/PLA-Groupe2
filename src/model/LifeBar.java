package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import controller.Options;

public class LifeBar {
	
	Entity m_entity;
	BufferedImage m_life;
	BufferedImage m_bar;
	
	public LifeBar(Entity entity) {
			
		m_entity = entity;
		loadSprites("assets/sprites/wall.png","assets/sprites/vie.png");		
	}
	
	
	public void paint(Graphics g){
		if(m_entity.getLife()>-1) {
		g.drawImage(m_bar,m_entity.getPositionX()-5,m_entity.getPositionY(), 42, 5, null);
		g.drawImage(m_life,m_entity.getPositionX()-5,m_entity.getPositionY(), m_entity.m_life*42/m_entity.m_lifeMax, 5, null);
		}

	}
	
	
	public void loadSprites(String spriteFileBar,String spriteFileVie){
		File imageFileBar = new File(spriteFileBar);
		File imageFileVie = new File(spriteFileVie);
		try {
			m_bar = ImageIO.read(imageFileBar);
			m_life = ImageIO.read(imageFileVie);
		} catch (IOException ex) {
		  ex.printStackTrace();
		  System.exit(-1);
		}
		
	}
	
	
}
