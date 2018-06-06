package model;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
// import java.util.ArrayList;

import javax.imageio.ImageIO;

import controller.Options;
import controller.Options.Directions;

public class Entity{

	int m_pixelX,m_pixelY;
	boolean m_moveable;
	Directions m_moving;
	double m_speed;
	long m_lastTime;
	int m_pixelDone;
	long m_updatePhysics;
	String m_state;
	HashMap<String, BufferedImage> m_spritesList;
	BufferedImage m_currentSprite;
	Model m_model;
	Tile m_tile;
	int m_layer;

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
		m_tile = t;

		m_spritesList = new HashMap<String,BufferedImage>();
		loadSprites(filename, m_spritesList);
	}

	public void move(Directions moving) {
		if(m_moving == null){
			switch (moving) {
				case RIGHT:
					if (m_pixelX < Options.LARGEUR_PX - Options.TAILLE_CASE){
						if(changeTile(1, 0))
							m_moving = Directions.RIGHT;
					}
					break;
				case LEFT:
					if (m_pixelX > 0){
						if(changeTile(-1, 0))
							m_moving = Directions.LEFT;				
						}
					break;
				case UP:
					if (m_pixelY > 0){
						if(changeTile(0, -1))
							m_moving = Directions.UP;
					}
					break;
				case DOWN:
					if (m_pixelY < Options.HAUTEUR_PX - Options.TAILLE_CASE){
						if(changeTile(0, 1))
							m_moving = Directions.DOWN;
					}
					break;
				default:
					break;
			}
		}
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
				}
				
			}
		}
	}

	public void paint(Graphics g){
		m_currentSprite = m_spritesList.get(m_state);
		g.drawImage(m_currentSprite, m_pixelX, m_pixelY, Options.TAILLE_CASE, Options.TAILLE_CASE, null);
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

	public void setPos(int x, int y){
		m_pixelX = x;
		m_pixelY = y;
	}

	public void setTile(Tile t){
		m_tile = t;
	}

	public Tile getTile(){
		return m_tile;
	}

	public boolean changeTile(int incX, int incY){
		if(m_tile.m_x+incX > Options.LARGEUR || m_tile.m_y+incY > Options.HAUTEUR || m_tile.m_x+incX < 0 || m_tile.m_y+incY < 0)
			return false;
		Tile newTile = m_model.getRoom().getTiles()[m_tile.m_x+incX][m_tile.m_y+incY];
		if(newTile.getEntityOnLayer(m_layer)==null){
			getTile().delEntity(this);
			newTile.putEntity(m_layer, this);
			setTile(newTile);
			return true;
		}
		return false;
	}
}
