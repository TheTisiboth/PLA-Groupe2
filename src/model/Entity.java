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

	
	public Entity(int posX, int posY, boolean moveable,BufferedImage m_sprites,double vitesse) {
		super();
		this.m_PixelX = posX;
		this.m_PixelY = posY;
		this.m_Moveable = moveable;
		this.m_sprites = m_sprites;
		this.m_Vitesse = vitesse;
		m_Moving = null;
		m_pixelDone = 0;
	} 
	
	public void move(Directions moving) {
		if(m_Moving == null){
			m_Moving = moving;
		}
	}
	
	public void step(long now) {
		long timeElapsed = now-this.m_LastTime;
		this.m_LastTime = now;
		
		if(m_Moveable && m_Moving != null) {
			


			int deplacement = (int)(m_Vitesse * timeElapsed);
			m_pixelDone += deplacement;
			
			System.out.print("Deplacement " + deplacement + " time elapsed: " + timeElapsed + "\n");
			
			switch (this.m_Moving) {
			case RIGHT : 
				
				this.m_PixelX += m_Vitesse * timeElapsed;
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
			
			if(m_pixelDone> Options.TAILLE_CASE){
			System.out.print("Distance parcourue: "  + m_pixelDone + "\n");
				m_PixelX = (m_PixelX / Options.TAILLE_CASE) * Options.TAILLE_CASE;
				m_PixelY = (m_PixelY / Options.TAILLE_CASE) * Options.TAILLE_CASE;
				m_Moving = null;
				m_pixelDone = 0;
			}
		}
		
		
		
	}
	
	
	
	
	
	
	
public void paint(Graphics g) {		
	
	Image img = m_sprites;

    g.drawImage(img,this.m_PixelX, this.m_PixelY, null);
	
	
	
}



public Directions getM_Moving() {
	return m_Moving;
}










}
	
		
	 

