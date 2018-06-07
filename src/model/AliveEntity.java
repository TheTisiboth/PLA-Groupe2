package model;

import java.awt.Graphics;
import java.util.*;

import controller.Options;
import main.Directions;

public abstract class AliveEntity extends Entity {

	int m_life;
	int m_lifeMax;
	double m_speed;
	int damage;
	LifeBar m_lifeBar;
	List<Portal> m_portals;

	public AliveEntity(Model model, int posX, int posY, String filename, double speed, Tile t,
			int life, int damage) {
		super(model, posX, posY, true, filename, t);
		m_lifeBar = new LifeBar(this);
		m_life = life;
		m_lifeMax = life;
		m_speed = speed; 
		m_portals = new ArrayList<Portal>();
	}
	
	@Override
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

	@Override
	public void wizz() {
		super.wizz();
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
		Portal portal = new Portal(m_model, newPosX, newPosY, newDir,m_tile,-1);
		
		if(m_portals.size() >= 1) {
			Portal.setPortalPair(portal, m_portals.get(0));
		}
		
		
		//new_tile.setPortal(portal);
		new_tile.putEntity(Options.LAYER_PORTAL, portal);
		m_portals.add(portal);
	}

	@Override
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

					m_pixelX = m_tile.m_x * Options.TAILLE_CASE;
					m_pixelY = m_tile.m_y * Options.TAILLE_CASE;

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

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Iterator<Portal> it = m_portals.iterator();
		while(it.hasNext()) {
			it.next().paint(g);
		}
		m_lifeBar.paint(g);
	}


	public int getLife() {
		return m_life;
	}

	public int getLifeMax() {
		return m_lifeMax;
	}

	public void setLife(int l){
		m_life = l;
	}

	public double getLifePercentage() {
		return (double) ((double)m_life / (double)m_lifeMax);
	}

	public void attack() {
		Directions dir = this.m_orientation;
		
		return;
	}
}
