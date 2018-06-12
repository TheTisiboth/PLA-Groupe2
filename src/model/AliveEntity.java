package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controller.Options;
import main.Directions;
import utils.Animation;

public abstract class AliveEntity extends MovableEntity {

	int m_life;
	int m_lifeMax;
	int m_damage;
	List<Portal> m_portals;
	
	Inventory m_inventory;
	Projectile projectile;
	int projectileCooldown;
	
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
		super(model, posX, posY, speed, filename, t);
		
		m_life = life;
		m_lifeMax = life;
		m_portals = new ArrayList<Portal>();
		m_inventory = new Inventory();
		m_damage = damage;
		
		m_model.addLifeBar(this);

		BufferedImage[] bIm = {m_sprite.getSprite(0, 0)};

		m_walkingLeft = new Animation(bIm, 10);
		m_walkingRight = new Animation(bIm, 10);
		m_walkingUp = new Animation(bIm, 10);
		m_walkingDown = new Animation(bIm, 10);
		m_defaultLeft = new Animation(bIm, 10);
		m_defaultRight = new Animation(bIm, 10);
		m_defaultUp = new Animation(bIm, 10);
		m_defaultDown = new Animation(bIm, 10);

		projectileCooldown = 500;
	}

	@Override
	public void step(long now) {
	
		super.step(now);
		projectileCooldown--;
		if(m_life<=0)
			kill();
	}

	@Override
	public void move(Directions moving) {
		super.move(moving);
		m_orientation = moving;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		updateAnimation();

		Iterator<Portal> it = m_portals.iterator();
		while(it.hasNext()) {
			it.next().paint(g);
		}
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
		while( ! (list.get(1) instanceof Wall) ) {
			spawningTile = spawningTile.nextTile(dir);
			list = spawningTile.m_entities;
		}
		if(list.get(1)instanceof Wall && !(list.get(3) instanceof Portal)) {
			Directions exitDir = null;
			if(m_orientation==Directions.UP) {exitDir=Directions.DOWN;}
			if(m_orientation==Directions.DOWN) {exitDir=Directions.UP;}
			if(m_orientation==Directions.LEFT) {exitDir=Directions.RIGHT;}
			if(m_orientation==Directions.RIGHT) {exitDir=Directions.LEFT;}
			Tile exitTile = spawningTile.nextTile(exitDir);
			Portal portal = new Portal(m_model, spawningTile.m_x * Options.TAILLE_CASE, spawningTile.m_y * Options.TAILLE_CASE, dir,spawningTile,exitTile,exitDir);
			
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
	
	public void pop() {
		return;
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
			if (spawningTile.m_entities.get(Options.layers.get("projectile")) == null){
				Projectile proj = new Projectile(m_model, spawningTile.m_x * Options.TAILLE_CASE, spawningTile.m_y * Options.TAILLE_CASE, "assets/sprites/fireball.png", 1, spawningTile, 3,dir,m_team);
				spawningTile.putEntity(Options.layers.get("projectile"),proj);
				proj.m_orientation=dir;
				projectileCooldown = 500;
				
				//First action of the projectile
				if(proj.testCollision()) {
					return;
				}
				proj.move(dir);
			}
		}
		return;
	}

	private void updateAnimation(){

		if(m_moving != null){
			switch (m_orientation) {
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
		else{
			m_animation.stop();
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

	public void tryToKill() {
		if(this.m_life <= 0) {
			m_tile.delEntity(this);
		}
	}
	
}
