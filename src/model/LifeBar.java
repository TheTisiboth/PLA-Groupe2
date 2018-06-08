package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class LifeBar {
	
	AliveEntity m_entity;
	BufferedImage m_life;
	BufferedImage m_bar;
	
	public LifeBar(AliveEntity entity) {			
		m_entity = entity;
	}

	public void paint(Graphics g){
		if(m_entity.getLife()>-1) {
			g.setColor(Color.red);
			g.fillRect(m_entity.getPositionX()-3, m_entity.getPositionY()-5, 35, 5);
			g.setColor(Color.green);
			g.fillRect(m_entity.getPositionX()-3, m_entity.getPositionY()-5, (int)(m_entity.getLifePercentage()*35), 5);
			g.setColor(Color.black);
			g.drawRect(m_entity.getPositionX()-3, m_entity.getPositionY()-5, 35, 5);
		}

	}
	
}
