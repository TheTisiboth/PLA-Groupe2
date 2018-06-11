package model;

import java.awt.Graphics;
import java.util.*;

import controller.Options;
import main.Directions;

public abstract class AliveEntity extends Entity {

	int m_life;
	int m_lifeMax;
	int m_damage;
	double m_speed;
	LifeBar m_lifeBar;
	List<Portal> m_portals;

	Inventory m_inventory;
	Projectile projectile;
	int projectileCooldown;

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
		projectileCooldown = 500;
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
	public void step(long now) {
		long timeElapsed = now-this.m_lastTime;
		projectileCooldown--;

		if(timeElapsed >= m_updatePhysics) {
			this.m_lastTime = now;
			
			// Checking if entity is dead
			this.tryToKill();

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
	
	public void wizz() {
		Directions dir = this.getOrientation();
		Tile spawningTile = this.getLookingTile(dir);
		List<Entity> list = spawningTile.m_entities;
		if(list.get(1)instanceof Wall && !(list.get(3) instanceof Portal)) {
			Directions exitDir = null;
			if(m_orientation==Directions.UP) {exitDir=Directions.DOWN;}
			if(m_orientation==Directions.DOWN) {exitDir=Directions.UP;}
			if(m_orientation==Directions.LEFT) {exitDir=Directions.RIGHT;}
			if(m_orientation==Directions.RIGHT) {exitDir=Directions.LEFT;}
			Portal portal = new Portal(m_model, spawningTile.m_x * Options.TAILLE_CASE, spawningTile.m_y * Options.TAILLE_CASE, dir,spawningTile,m_tile,exitDir);
			
			if(m_portals.size() >= 2) {
				m_portals.get(0).delete();
				m_portals.remove(0);
			}
			
			if(m_portals.size() >= 1) {
				Portal.setPortalPair(portal, m_portals.get(0));
			}
			
			spawningTile.putEntity(Options.LAYER_PORTAL, portal);
			m_portals.add(portal);
		}
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
	
	public void throwProjectile() {
		if (projectileCooldown <= 0) {
			Directions dir = this.getOrientation();
			Tile spawningTile = this.getLookingTile(dir);
			Projectile proj = new Projectile(m_model, spawningTile.m_x * Options.TAILLE_CASE, spawningTile.m_y * Options.TAILLE_CASE, "assets/sprites/fireball.png", 1, spawningTile, 200, 3);
			spawningTile.putEntity(Options.layers.get("projectile"),proj);
			proj.m_orientation=dir;
			projectileCooldown = 500;
			
			//First action of the projectile
			if(proj.testCollision()) {
				return;
			}
			proj.move(dir);
		}
		return;
	}
	
	public void tryToKill() {
		if(this.m_life <= 0) {
			m_tile.delEntity(this);
		}
	}
}
