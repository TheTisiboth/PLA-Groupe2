package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import controller.Options;

public class Background {
	BufferedImage m_sprite;
	
	public Background(){
		File image = new File("assets/img/fond.png");
	    try {
	        m_sprite = ImageIO.read(image);
	    } catch (IOException ex) {
	      ex.printStackTrace();
	      System.exit(-1);
	    }
	}
	
	public void step(long now) {
		
	}
	
	public void paint(Graphics g) {
		g.drawImage(m_sprite, 0, 0, Options.LARGEUR_PX, Options.HAUTEUR_PX, null);
	}
	
}
