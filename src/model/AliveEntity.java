package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controller.Options;
import main.Directions;
import main.Teams;
import model.Weapons.WeaponsInv;
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
			int life, int damage, Teams team) {
		super(model, posX, posY, speed, filename, t, team);

		m_life = life;
		m_lifeMax = life;
		m_portals = new ArrayList<Portal>();
		m_inventory = new Inventory(this);
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

	public void protect() {
		protect(this.RelativeToRealDir(Directions.FRONT));
	}

	public void protect(Directions dir) {
		dir = this.RelativeToRealDir(dir);
		throwProjectile(dir);
	}

	public void setOrientation(Directions dir) {
		super.setOrientation(dir);
		dir = this.RelativeToRealDir(dir);
		
		m_inventory.getWeapon().rotate(dir);
		
	}

	@Override
	public void step(long now) {
		m_inventory.step();
		
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
		updateAnimation();

		Iterator<Portal> it = m_portals.iterator();
		while(it.hasNext()) {
			it.next().paint(g);
		}
		
		m_inventory.paint(g);
	}

	public boolean pick(){
		List<Entity> nEnt= checkTile(m_orientation);
		if (nEnt.get(0) instanceof Item){
			//m_inventory.switchItem( (Item) nEnt.get(0));
			m_inventory.switchWeapon((WeaponsInv)((Item)nEnt.get(0)).getItemInv(this));
			System.out.println("Changement d'arme");
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
			Portal portal = new Portal(m_model, spawningTile.m_x * Options.TAILLE_CASE, spawningTile.m_y * Options.TAILLE_CASE, dir,spawningTile,exitTile,exitDir, this);

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
		Directions dir = this.getOrientation();
		Tile spawningTile = this.getLookingTile(dir);
		List<Entity> list = spawningTile.m_entities;
		while( ! (list.get(1) instanceof Wall) ) {
			spawningTile = spawningTile.nextTile(dir);
			list = spawningTile.m_entities;
		}
		if (list.get(3) != null) {
			Portal portal = (Portal) list.get(3);
			if(portal.m_owner.m_portals.get(0) == portal) {
				if(portal.m_owner.m_portals.get(0).m_destPortal != null)
					portal.m_owner.m_portals.get(1).m_destPortal= null;
				portal.m_owner.m_portals.get(0).delete();
				portal.m_owner.m_portals.remove(0);
			}
			else {
				portal.m_owner.m_portals.get(0).m_destPortal= null;
				portal.m_owner.m_portals.get(1).delete();
				portal.m_owner.m_portals.remove(1);
			}
		}
		return;
	}

	public void attack() {
		attack(Directions.FRONT);
	}

	public void attack(Directions dir) {
		dir = this.RelativeToRealDir(dir);
		setOrientation(dir);

		m_inventory.getWeapon().hit();;
		
		//Directions dir = m_model.getPlayer().m_orientation;
		/*List<Entity> list = this.checkTile(dir);
		if(list.get(1) instanceof AliveEntity) {
			AliveEntity enemy = (AliveEntity) list.get(1);
			enemy.m_life = enemy.m_life - this.m_damage;
		}
		return;*/
	}

	public void throwProjectile() {
		throwProjectile(Directions.FRONT);
	}

	public void throwProjectile(Directions dir) {
		dir = this.RelativeToRealDir(dir);
		if (projectileCooldown <= 0) {
			setOrientation(dir);
			Tile spawningTile = this.getLookingTile(dir);
			if (spawningTile.m_entities.get(Options.layers.get("projectile")) == null){
				Projectile proj = new Projectile(m_model, spawningTile.m_x * Options.TAILLE_CASE, spawningTile.m_y * Options.TAILLE_CASE, "assets/sprites/fireball.png", 1, spawningTile, 3,dir,Teams.Missile, m_team);
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
