package model;

import controller.Options;
import main.Directions;

public abstract class MovableEntity extends Entity {


	double m_speed;
	int m_animationSpeed;


	public MovableEntity(Model model, int posX, int posY, double speed, String filename, Tile t) {
		super(model, posX, posY, true, filename, t);
		m_speed = speed; 
		m_animationSpeed = (int) (m_speed * 30);

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
		}
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
