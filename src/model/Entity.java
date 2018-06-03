package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
// import java.util.ArrayList;

import javax.imageio.ImageIO;

import controller.Options;

public abstract class Entity {
	Model m_model;
	
	int pixelX, pixelY;
	boolean m_movable;
	boolean m_traversable;

	HashMap<String, BufferedImage> m_spritesList;
	BufferedImage m_currentSprite;

	String m_state;

	static int m_layer = 0;
	
	public Entity(Model model, int x, int y, String spriteFile) {
		m_model = model;
		m_state = "default";
		pixelX = x * Options.TAILLE_CASE;
		pixelY = y * Options.TAILLE_CASE;
		m_spritesList = new HashMap<String,BufferedImage>();
		loadSprites(spriteFile, m_spritesList);

	}
	
	public void move() {
		
	}
	
	public void hit() {
		
	}

	public void paint(Graphics g){
		m_currentSprite = m_spritesList.get(m_state);
		g.drawImage(m_currentSprite, pixelX, pixelY, Options.TAILLE_CASE, Options.TAILLE_CASE, null);
	}

	public void loadSprites(String spriteFile, HashMap<String, BufferedImage> list){
		File imageFile = new File(spriteFile);
		try {
			BufferedImage img = ImageIO.read(imageFile);

			list.put(m_state, img);
		} catch (IOException ex) {
		  ex.printStackTrace();
		  System.exit(-1);
		}
	}

}
