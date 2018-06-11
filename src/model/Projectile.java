package model;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;

import controller.Options;
import main.Directions;

public class Projectile extends AliveEntity {

	private boolean isMoving;
	
	public Projectile(Model model, int posX, int posY, String filename, double speed, Tile t, int life, int damage) {
		super(model, posX, posY, filename, speed, t, life, damage);
		// TODO Auto-generated constructor stub
		m_layer = 2;
		isMoving=false;
	}
	
	public void tryToKill(Directions moving) {
		return;
	}
	
	@Override
	public void paint(Graphics g) {
		if(isMoving==false)
			isMoving=true;
		m_currentSprite = m_spritesList.get(m_state);
		g.drawImage(m_currentSprite, m_pixelX, m_pixelY, Options.TAILLE_CASE, Options.TAILLE_CASE, null);
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
	
	public boolean testCollision() {
		// Checking current tile
		List<Entity> list = this.m_tile.m_entities;
		if ( list.get(1)instanceof Wall) {
			m_tile.delEntity(this);
			return true;
		}
		if (list.get(1) instanceof AliveEntity) {
			AliveEntity entity = (AliveEntity) list.get(1);
			entity.m_life = entity.m_life - this.m_damage;
			m_tile.delEntity(this);
			return true;
		}
		return false;
	}
}
