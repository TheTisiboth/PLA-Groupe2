package model;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import controller.Options;
import utils.MapParser;

public class Map {
	BufferedImage m_sprite;
	String[][] mapTiles;
	public Map(String mapName){
		mapTiles = MapParser.getMap(mapName);
		File enemy_img = new File("assets/sprites/enemy.png");
	    try {
	        m_sprite = ImageIO.read(enemy_img);
	    } catch (IOException ex) {
	      ex.printStackTrace();
	      System.exit(-1);
	    }
	}
	
	public void step(long now) {
		
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		for (int i = 0; i < Options.HAUTEUR; i++) {
			for (int j = 0; j < Options.LARGEUR; j++) {
				g.drawRect(Options.TAILLE_CASE*j, Options.TAILLE_CASE*i, Options.TAILLE_CASE, Options.TAILLE_CASE);
			}
		}
		for (int i = 0; i < Options.HAUTEUR; i++) {
			for (int j = 0; j < Options.LARGEUR; j++) {
				if (mapTiles[i][j] == "wall"){
					g.setColor(Color.black);
					g.fillRect(Options.TAILLE_CASE*j, Options.TAILLE_CASE*i, Options.TAILLE_CASE, Options.TAILLE_CASE);
				}
				if (mapTiles[i][j] == "enemy"){
					g.drawImage(m_sprite, Options.TAILLE_CASE*j, Options.TAILLE_CASE*i, Options.TAILLE_CASE, Options.TAILLE_CASE, null);
				}
				if (mapTiles[i][j] == "door") {
					g.setColor(Color.yellow);
					g.fillRect(Options.TAILLE_CASE*j, Options.TAILLE_CASE*i, Options.TAILLE_CASE, Options.TAILLE_CASE);

				}
			}
		}
	}
	
}
