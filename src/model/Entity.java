package model;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
// import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import controller.Options;
import main.Directions;

public class Entity{
	int m_pixelX,m_pixelY;
	boolean m_moveable;
	Directions m_moving;
	Directions m_orientation;
	int m_pixelDone;
	double m_speed;

	long m_lastTime;
	long m_updatePhysics;

	String m_state;
	HashMap<String, BufferedImage> m_spritesList;
	BufferedImage m_currentSprite;
	Model m_model;
	Tile m_tile;
	int m_layer;

	List<Portal> m_portals;

	public Entity(Model model, int posX, int posY, boolean moveable, String filename, double speed, Tile t) {
		super();
		m_model = model;
		m_pixelX = posX;
		m_pixelY = posY;
		m_moveable = moveable;
		m_speed = speed;
		m_moving = null;
		m_pixelDone = 0;
		m_updatePhysics = 30;
		m_state = "default";
		m_portals = new ArrayList<Portal>();
		m_tile = t;

		m_spritesList = new HashMap<String,BufferedImage>();
		loadSprites(filename, m_spritesList);
	}

	public void move(Directions moving) {
		int newX = m_tile.m_x;
		int newY = m_tile.m_y;
		if(m_moving == null){
			switch (moving) {
				case RIGHT:
					if (m_pixelX < Options.LARGEUR_PX - Options.TAILLE_CASE){
						newX++;
						if(changeTile(newX, newY))
							m_moving = Directions.RIGHT;
					}
					break;
				case LEFT:
					if (m_pixelX > 0){
						newX--;
						if(changeTile(newX, newY))
							m_moving = Directions.LEFT;				
						}
					break;
				case UP:
					if (m_pixelY > 0){
						newY--;
						if(changeTile(newX, newY))
							m_moving = Directions.UP;
					}
					break;
				case DOWN:
					if (m_pixelY < Options.HAUTEUR_PX - Options.TAILLE_CASE){
						newY++;
						if(changeTile(newX, newY))
							m_moving = Directions.DOWN;
					}
					break;
				default:
					break;
			}
			m_orientation = moving;
		}
	}

	public void wizz() {
		if(m_portals.size() >= 2) {
			m_portals.get(0).delete();
			m_portals.remove(0);
		}
		//TODO MAuvaise tile
		int newPosX = m_pixelX;
		int newPosY = m_pixelY;
		Directions newDir = null;
		
		if(m_orientation == Directions.LEFT) {
			newPosX -= Options.TAILLE_CASE;
			newDir = Directions.RIGHT;
		}
		if(m_orientation == Directions.RIGHT) {
			newDir = Directions.LEFT;
			newPosX += Options.TAILLE_CASE;
		}
		if(m_orientation == Directions.UP) {
			newPosY -= Options.TAILLE_CASE;
			newDir = Directions.DOWN;
		}
		if(m_orientation == Directions.DOWN) {
			newDir = Directions.UP;
			newPosY += Options.TAILLE_CASE;
		}

		Tile new_tile = m_model.getRoom().getTile(newPosX / Options.TAILLE_CASE, newPosY / Options.TAILLE_CASE);
		Portal portal = new Portal(m_model, newPosX, newPosY, newDir);
		
		if(m_portals.size() >= 1) {
			Portal.setPortalPair(portal, m_portals.get(0));
		}
		
		
		//new_tile.setPortal(portal);
		new_tile.putEntity(Options.LAYER_PORTAL, portal);
		m_portals.add(portal);
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

	public void step(long now) {
		long timeElapsed = now-this.m_lastTime;

		if(timeElapsed >= m_updatePhysics) {
			this.m_lastTime = now;


			//Movement
			if(m_moveable && m_moving != null) {
				int deplacement = (int)(m_speed * timeElapsed);
				m_pixelDone += deplacement;

				System.out.print("Deplacement " + deplacement + " time elapsed: " + timeElapsed + "\n");

				switch (this.m_moving) {
				case RIGHT :
					this.m_pixelX += deplacement;
					break;

				case LEFT :
					this.m_pixelX -= deplacement;
					break;

				case UP :
					this.m_pixelY -= deplacement;
					break;

				case DOWN :
					this.m_pixelY += deplacement;
					break;

				default : break;

				}

				//Replace l'entité au milieu de sa case
				if(m_pixelDone> Options.TAILLE_CASE){
					if(m_pixelX <= 0) {
						m_pixelX = 0;
						m_pixelY = (m_pixelY / Options.TAILLE_CASE) * Options.TAILLE_CASE;
						if(m_moving == Directions.UP)
							m_pixelY += Options.TAILLE_CASE;
					}
					else if(m_pixelY <= 0) {
						m_pixelY = 0;
						m_pixelX = (m_pixelX / Options.TAILLE_CASE) * Options.TAILLE_CASE;
						if(m_moving == Directions.LEFT)
							m_pixelX += Options.TAILLE_CASE;
					}
					else {
						m_pixelX = (m_pixelX / Options.TAILLE_CASE) * Options.TAILLE_CASE;
						m_pixelY = (m_pixelY / Options.TAILLE_CASE) * Options.TAILLE_CASE;

						if(m_moving == Directions.LEFT)
							m_pixelX += Options.TAILLE_CASE;
						if(m_moving == Directions.UP)
							m_pixelY += Options.TAILLE_CASE;

					}

					m_moving = null;
					m_pixelDone = 0;
					
					//Passage dans un portail
					Tile new_tile = m_model.getRoom().getTile(m_pixelX / Options.TAILLE_CASE, m_pixelY / Options.TAILLE_CASE);
					if(new_tile.hasPortal()){
						new_tile.getPortal().GoThrough(this);
					}
				}

			}
		}
	}

	public void paint(Graphics g){
		m_currentSprite = m_spritesList.get(m_state);
		g.drawImage(m_currentSprite, m_pixelX, m_pixelY, Options.TAILLE_CASE, Options.TAILLE_CASE, null);

		Iterator<Portal> it = m_portals.iterator();
		while(it.hasNext()) {
			it.next().paint(g);
		}
	}

	public Directions getOrientation() {
		return m_orientation;
	}

	public void setOrientation(Directions directions) {
		m_orientation = directions;
	}

	public Directions getMoving() {
		return m_moving;
	}


	/**
	 * @return the m_layer
	 */
	public int getLayer() {
		return m_layer;
	}

	public void setPosition(int x, int y) {
		m_pixelX = x;
		m_pixelY = y;
	}

	public int getPositionX() {
		return m_pixelX;
	}

	public int getPositionY() {
		return m_pixelY;
	}


	public void setTile(Tile t){
		m_tile = t;
	}

	public Tile getTile(){
		return m_tile;
	}

	public boolean changeTile(int newX, int newY){
		if(newX > Options.LARGEUR || newY > Options.HAUTEUR || newX < 0 || newY < 0)
			return false;
		Tile newTile = m_model.getRoom().getTiles()[newX][newY];
		if(newTile.getEntityOnLayer(m_layer)==null){
			getTile().delEntity(this);
			newTile.putEntity(m_layer, this);
			setTile(newTile);
			return true;
		}
		return false;
	}
}
