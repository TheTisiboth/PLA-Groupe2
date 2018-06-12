package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import controller.Options;
import main.Directions;

public class Portal extends Entity {
	Portal m_destPortal;
	Tile m_exitTile;
	Directions m_exitDir;
	EnumMap<Directions, BufferedImage> m_sprites;
	
	public Portal(Model model, int posX, int posY, Directions orientation, Tile t, Tile t_exit, Directions exit_dir) {
		super(model, posX, posY, false, "assets/sprites/portal_down.png", t);
		m_orientation = orientation;
		m_destPortal = null;
		m_pixelX = posX;
		m_pixelY = posY;
		m_exitTile = t_exit;
		m_exitDir = exit_dir;
		
		m_sprites = new EnumMap<Directions, BufferedImage>(Directions.class);
		try {
			m_sprites.put(Directions.RIGHT, ImageIO.read(new File("assets/sprites/portal_left.png")));
			m_sprites.put(Directions.LEFT, ImageIO.read(new File("assets/sprites/portal_right.png")));
			m_sprites.put(Directions.DOWN, ImageIO.read(new File("assets/sprites/portal_up.png")));
			m_sprites.put(Directions.UP, ImageIO.read(new File("assets/sprites/portal_down.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void AddDest(Portal portal) {
		m_destPortal = portal;
	}
	
	public boolean Active() {
		if(m_destPortal == null)
			return false;
		return true;
	}
	
	public void setDestPortal(Portal p) {
		m_destPortal = p;
	}
	
	public static void setPortalPair(Portal p1, Portal p2) {
		p1.setDestPortal(p2);
		p2.setDestPortal(p1);
	}
	
	public void delete() {
		m_tile.delEntity(this);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(m_sprites.get(m_orientation), m_pixelX, m_pixelY, Options.TAILLE_CASE, Options.TAILLE_CASE, null);
	}
	
}
