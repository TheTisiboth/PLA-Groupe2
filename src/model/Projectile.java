package model;

import java.awt.Graphics;
import java.util.List;

import main.Directions;

public class Projectile extends MovableEntity {

	private boolean isMoving;
	private int m_damage;
	
	public Projectile(Model model, int posX, int posY, String filename, double speed, Tile tile, int damage, Directions d, Team team) {
		super(model, posX, posY, speed, filename, tile);
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
	
	public boolean testCollision() {
		// Checking current tile
		List<Entity> list = m_tile.m_entities;

		for (Entity e : list) {
			if(e instanceof Wall){
				kill();
				return true;
			}
			if(e instanceof AliveEntity && e.getTeam() != m_team){
				kill();
				((AliveEntity)e).setLife(((AliveEntity)e).getLife()-m_damage);
				return true;
			}
		}
		return false;
	}
}
