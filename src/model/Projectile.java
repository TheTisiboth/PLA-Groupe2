package model;

import java.awt.Graphics;
import java.util.List;

import controller.Options;
import main.Directions;
import main.Teams;

public class Projectile extends MovableEntity {

	private boolean isMoving;
	private int m_damage;
	private Teams m_equipe;

	public Projectile(Model model, int posX, int posY, String filename, double speed, Tile tile, int damage, Directions d, Teams team, Teams equipe) {
		super(model, posX, posY, speed, filename, tile, team);
		// TODO Auto-generated constructor stub
		m_equipe = equipe;
		m_team = team;
		m_layer = 2;
		tile.putEntity(m_layer,this);
		m_damage = damage;
		isMoving=false;
		m_moving = d;
		m_orientation = m_moving;
		testCollision();
		move(d);
	}

	@Override
	public void paint(Graphics g) {
		if(isMoving==false)
			isMoving=true;
		super.paint(g);
	}

	@Override
	public void step(long now) {
		if(m_moving == null)
			move(m_orientation);
		if (isMoving == true) {
			long timeElapsed = now-this.m_lastTime;
			super.step(now);
			if(timeElapsed >= m_updatePhysics) {
	
				if(this.testCollision()) {
					return;
				}
			}

		}
	}
	@Override
	public boolean changeTile(int newX, int newY){
		if(newX > Options.LARGEUR -1 || newY > Options.HAUTEUR - 1|| newX < 0 || newY < 0)
			return false;
		Tile newTile = m_model.getRoom().getTiles()[newX][newY];
		if(newTile.m_entities.get(2) != null) {
			Entity e = newTile.m_entities.get(2);
			newTile.delEntity(e);
			kill();
			return false;
		}
		Portal portal = (Portal)newTile.getEntityOnLayer(Options.LAYER_PORTAL);
		if(newTile.getEntityOnLayer(m_layer)==null || ( portal!=null && portal.Active() && portal.m_orientation==this.m_orientation) ){
			getTile().delEntity(this);
			if(portal !=null && portal.Active() && portal.m_orientation==this.m_orientation) {
				newTile = portal.m_destPortal.m_exitTile;
				this.m_orientation = portal.m_destPortal.m_exitDir;
			}
			newTile.putEntity(m_layer, this);
			setTile(newTile);
			return true;
		}
		return false;
	}

	public boolean testCollision() {
		// Checking current tile
		List<Entity> list = m_tile.m_entities;

		for (Entity e : list) {
			if(e instanceof Wall){
				kill();
				return true;
			}
			if(e instanceof AliveEntity && e.getTeam() != m_equipe){
				kill();
				((AliveEntity)e).setLife(((AliveEntity)e).getLife() - m_damage);
				return true;
			}
		}
		return false;
	}
}
