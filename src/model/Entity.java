package model;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import controller.Options;

public class Entity{
	
	int m_PixelX,m_PixelY;
	boolean m_Moveable;
	BufferedImage m_sprites;
	Directions m_Moving;
	double m_Vitesse;
	long m_LastTime;
	int m_pixelDone;
	long m_update_physic;
	
	public Entity(int posX, int posY, boolean moveable,BufferedImage sprites,double vitesse) {
		super();
		m_PixelX = posX;
		m_PixelY = posY;
		m_Moveable = moveable;
		m_sprites = sprites;
		m_Vitesse = vitesse;
		m_Moving = null;
		m_pixelDone = 0;
		m_update_physic = 30;
	} 
	
	public void move(Directions moving) {
		if(m_Moving == null){
			m_Moving = moving;
		}
	}
	
	public void step(long now) {
		long timeElapsed = now-this.m_LastTime;
		
		if(timeElapsed >= m_update_physic) {
			this.m_LastTime = now;
			
			
			//Movement
			if(m_Moveable && m_Moving != null) {
				int deplacement = (int)(m_Vitesse * timeElapsed);
				m_pixelDone += deplacement;
				
				System.out.print("Deplacement " + deplacement + " time elapsed: " + timeElapsed + "\n");
				
				switch (this.m_Moving) {
				case RIGHT : 
					this.m_PixelX += deplacement;
					break;
				
				case LEFT : 
					
					this.m_PixelX -= deplacement;
					break;
				
				case UP : 
					
					this.m_PixelY -= deplacement;
					break;
				
				case DOWN : 
					
					this.m_PixelY += deplacement;
					break;
				
				default : break;
				
				}
				
				//Replace l'entité au milieu de sa case
				if(m_pixelDone> Options.TAILLE_CASE){	
					if(m_PixelX < 0)
						m_PixelX = 0;
					else if(m_PixelY < 0)
						m_PixelY = 0;
					else {
						m_PixelX = (m_PixelX / Options.TAILLE_CASE) * Options.TAILLE_CASE;
						m_PixelY = (m_PixelY / Options.TAILLE_CASE) * Options.TAILLE_CASE;
						
						if(m_Moving == Directions.LEFT)
							m_PixelX += Options.TAILLE_CASE;
						if(m_Moving == Directions.UP)
							m_PixelY += Options.TAILLE_CASE;
					}

					m_Moving = null;
					m_pixelDone = 0;
				}
			}
		}	
	}	
	
	public void paint(Graphics g) {		
		Image img = m_sprites;
	    g.drawImage(img, m_PixelX, m_PixelY, null);
		
	}

	public Directions getM_Moving() {
		return m_Moving;
	}
}
	
		
	 

