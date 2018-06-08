package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controller.Options;
import main.Directions;
import utils.Animation;

public abstract class AliveEntity extends Entity {

	int m_life;
	int m_lifeMax;
	int m_damage;
	double m_speed;
	LifeBar m_lifeBar;
	List<Portal> m_portals;

	Inventory m_inventory;

	Animation m_walkingLeft;
	Animation m_walkingRight;
	Animation m_walkingUp;
	Animation m_walkingDown;

	Animation m_defaultLeft;
	Animation m_defaultRight;
	Animation m_defaultUp;
	Animation m_defaultDown;

	public AliveEntity(Model model, int posX, int posY, String filename, double speed, Tile t,
			int life, int damage) {
		super(model, posX, posY, true, filename, t);
		m_lifeBar = new LifeBar(this);
		m_life = life;
		m_lifeMax = life;
		m_speed = speed; 
		m_portals = new ArrayList<Portal>();
		m_inventory = new Inventory();
		m_damage = damage;

		BufferedImage[] m_walkingLeftIm = {m_sprite.getSprite(0, 1), m_sprite.getSprite(2, 1)}; 
		BufferedImage[] m_defaultLeftIm = {m_sprite.getSprite(1, 1)};
		BufferedImage[] m_walkingRightIm = {m_sprite.getSprite(0, 2), m_sprite.getSprite(2, 2)};
		BufferedImage[] m_defaultRightIm = {m_sprite.getSprite(1, 2)};
		BufferedImage[] m_walkingUpIm = {m_sprite.getSprite(0, 3), m_sprite.getSprite(2, 3)};
		BufferedImage[] m_defaultUpIm = {m_sprite.getSprite(1, 3)};
		BufferedImage[] m_walkingDownIm = {m_sprite.getSprite(0, 0), m_sprite.getSprite(2, 0)};
		BufferedImage[] m_defaultDownIm = {m_sprite.getSprite(1, 0)};

		m_walkingLeft = new Animation(m_walkingLeftIm, 10);
		m_walkingRight = new Animation(m_walkingRightIm, 10);
		m_walkingUp = new Animation(m_walkingUpIm, 10);
		m_walkingDown = new Animation(m_walkingDownIm, 10);
		m_defaultLeft = new Animation(m_defaultLeftIm, 10);
		m_defaultRight = new Animation(m_defaultRightIm, 10);
		m_defaultUp = new Animation(m_defaultUpIm, 10);
		m_defaultDown = new Animation(m_defaultDownIm, 10);

		m_animation = m_defaultDown;

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
				int deplacement = (int)(m_speed * timeElapsed * 0.5);
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
					m_animation.reset();
					// m_animation.reset();
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
		updateAnimation();

		Iterator<Portal> it = m_portals.iterator();
		while(it.hasNext()) {
			it.next().paint(g);
		}
		m_lifeBar.paint(g);
	}

	public boolean pick(){
		List<Entity> nEnt= checkTile(m_orientation);
		if (nEnt.get(0) instanceof Item){
			m_inventory.switchItem( (Item) nEnt.get(0));
			return true;
		}
		return false;
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
		Directions dir = m_model.getPlayer().m_orientation;
		List<Entity> list = this.checkTile(dir);
		if(list.get(1) instanceof Enemy) {
			Enemy enemy = (Enemy) list.get(1);
			enemy.m_life = enemy.m_life - this.m_damage;
		}
		return;
	}
	
	public void tryToKill() {
		if(this.m_life <= 0) {
			m_tile.delEntity(this);
		}
	}

	private void updateAnimation(){

		if(m_moving != null){
			switch (m_moving) {
				case RIGHT:
					m_animation = m_walkingRight;
					break;
				case LEFT:
					m_animation = m_walkingLeft;
					break;	
				case UP:
					m_animation = m_walkingUp;
					break;
				case DOWN:
					m_animation = m_walkingDown;
					break;
				default:
					break;
			}
			m_animation.start();
		}
		else
			switch (m_orientation) {
				case RIGHT:
					m_animation = m_defaultRight;
					break;
				case LEFT:
					m_animation = m_defaultLeft;
					break;	
				case UP:
					m_animation = m_defaultUp;
					break;
				case DOWN:
					m_animation = m_defaultDown;
					break;
				default:
					m_animation = m_defaultDown;
					break;
			}
	}
}
