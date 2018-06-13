package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import controller.Options;

public class Background {
	BufferedImage m_sprite;
	
	public Background(){
	}
	public Background(String filename){
		setNewTile(filename);
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < Options.LARGEUR; i++) {
			for (int j = 0; j < Options.HAUTEUR; j++) {
				g.drawImage(m_sprite, i*Options.TAILLE_CASE, j*Options.TAILLE_CASE, Options.TAILLE_CASE, Options.TAILLE_CASE, null);				
			}
		}
	}

	public void setNewTile(String tileName){
		File image = new File(tileName);
	    try {
	        m_sprite = ImageIO.read(image);
	    } catch (IOException ex) {
	      ex.printStackTrace();
	      System.exit(-1);
	    }
	}
	
}
