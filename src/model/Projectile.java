package model;

import java.awt.Graphics;
import java.util.List;

import controller.Options;
import main.Directions;
import main.Teams;

public class Projectile extends MovableEntity {

	private boolean isMoving;
	private int m_damage;

	public Projectile(Model model, int posX, int posY, String filename, double speed, Tile tile, int damage, Directions d, Teams team) {
		super(model, posX, posY, speed, filename, tile, team);
		// TODO Auto-generated constructor stub
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
		if (isMoving == true) {
			long timeElapsed = now-this.m_lastTime;

			if(timeElapsed >= m_updatePhysics) {
				this.m_lastTime = now;

				if(this.testCollision()) {
					return;
				}
				// Déplacement du projectile
				this.move(this.m_orientation);


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
							m_moving = m_orientation;
					}
					break;
				case LEFT:
					if (m_pixelX > 0){
						newX--;
						if(changeTile(newX, newY))
							m_moving = m_orientation;
						}
					break;
				case UP:
					if (m_pixelY > 0){
						newY--;
						if(changeTile(newX, newY))
							m_moving = m_orientation;
					}
					break;
				case DOWN:
					if (m_pixelY < Options.HAUTEUR_PX - Options.TAILLE_CASE){
						newY++;
						if(changeTile(newX, newY))
							m_moving = m_orientation;
					}
					break;
				default:
					break;
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
			if(e instanceof AliveEntity && e.getTeam() != m_team){
				kill();
				((AliveEntity)e).setLife(((AliveEntity)e).getLife()-m_damage);
				return true;
			}
		}
		return false;
	}
}
