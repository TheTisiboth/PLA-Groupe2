package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Options;
import model.Model;

public class HUD extends JPanel {
	
	Model m_model;
	BufferedImage m_spriteLife;
	BufferedImage m_spritePoison;
	BufferedImage m_spriteWeapon;
	
	public HUD(Model m) {
		m_model = m;
		loadSprites();	
		
	}
	
	public void paint(Graphics g){
		
		//g.drawImage(m_spriteLife, 0,0, 500, 500, null);
		//g.drawImage(m_spritePoison, 50,10, 500, 500, null);
		g.drawImage(m_spriteWeapon, 1,0, 100, 100, null);
	}
	
	public void loadSprites(){
		File imageFile1 = new File("assets/sprites/potion.png");
		File imageFile2 = new File("assets/sprites/big_potion.png");
		File imageFile3 = new File("assets/sprites/rifle.png");
		try {
			m_spriteLife = ImageIO.read(imageFile1);
			m_spritePoison = ImageIO.read(imageFile2);
			m_spriteWeapon = ImageIO.read(imageFile3);
			
		} catch (IOException ex) {
		  ex.printStackTrace();
		  System.exit(-1);
		}
	}

	public Model getM_model() {
		return m_model;
	}
	

}
