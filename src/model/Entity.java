package model;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import controller.Options;
import main.Directions;
import main.Teams;
import utils.Animation;
import utils.Sprite;

public class Entity{
	int m_pixelX,m_pixelY;
	boolean m_moveable;
	Directions m_moving;
	Directions m_orientation;
	int m_pixelDone;

	long m_lastTime;
	long m_updatePhysics;

	String m_state;
	BufferedImage m_currentSprite;
	public Model m_model;
	Tile m_tile;
	int m_layer;
	
	Teams m_team;

	Sprite m_sprite;

	Animation m_animation;

	public Entity(Model model, int posX, int posY, boolean moveable, String filename, Tile t, Teams team) {
		m_model = model;
		m_pixelX = posX;
		m_pixelY = posY;
		m_moveable = moveable;
		m_moving = null;
		m_pixelDone = 0;
		m_updatePhysics = 10;
		m_tile = t;
		m_orientation = Directions.DOWN;

		m_sprite = new Sprite(filename);

		m_animation = new Animation(new BufferedImage[]{m_sprite.getSprite(0, 0)}, 10);

		m_team = team;
	}

	public Directions RelativeToRealDir(Directions dir) {
		switch(dir) {
		case OnMyLeft:
			if(m_orientation == Directions.DOWN)
				return Directions.RIGHT;
			else if(m_orientation  == Directions.RIGHT)
				return Directions.UP;
			else if(m_orientation == Directions.UP)
				return Directions.LEFT;
			else if(m_orientation == Directions.LEFT)
				return Directions.DOWN;
			break;
		case OnMyRight:
			if(m_orientation== Directions.UP)
				return Directions.RIGHT;
			else if(m_orientation== Directions.RIGHT)
				return Directions.DOWN;
			else if(m_orientation== Directions.DOWN)
				return Directions.LEFT;
			else if(m_orientation== Directions.LEFT)
				return Directions.UP;
		case FRONT:
			return m_orientation;
		case BACK:
			if(m_orientation == Directions.UP)
				return Directions.DOWN;
			else if(m_orientation == Directions.DOWN)
				return Directions.UP;
			else if(m_orientation == Directions.RIGHT)
				return Directions.LEFT;
			else if(m_orientation == Directions.LEFT)
				return Directions.RIGHT;
		}
		return dir;
	}
	
	public void move(Directions moving) {
		if(m_moving == null){
			switch (moving) {
				case RIGHT :
					changeTile(m_tile.m_x+1, m_tile.m_y);
					break;
	
				case LEFT :
					changeTile(m_tile.m_x-1, m_tile.m_y);
					break;
	
				case UP :
					changeTile(m_tile.m_x, m_tile.m_y-1);
					break;
	
				case DOWN :
					changeTile(m_tile.m_x, m_tile.m_y+1);
					break;
				default :
					break;
			}
		}
	}

	public void wizz() {

	}

	public void step(long now) {
		long timeElapsed = now-this.m_lastTime;

		if(timeElapsed >= m_updatePhysics) {
			this.m_lastTime = now;

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

	public void paint(Graphics g){
		m_animation.update();
		g.drawImage(m_animation.getSprite(), m_pixelX, m_pixelY, Options.TAILLE_CASE, Options.TAILLE_CASE, null);

	}
	
	public void kill(){
		m_tile.delEntity(this);
	}

	public Directions getOrientation() {
		return m_orientation;
	}

	public void setOrientation(Directions directions) {
		m_orientation = directions;
	}

	public Directions getMoving() {
		return m_moving;
	}

	public Teams getTeam(){
		return m_team;
	}


	/**
	 * @return the m_layer
	 */
	public int getLayer() {
		return m_layer;
	}

	public void setPosition(int x, int y) {
		m_pixelX = x;
		m_pixelY = y;
	}

	public int getPositionX() {
		return m_pixelX;
	}

	public int getPositionY() {
		return m_pixelY;
	}

	public void setTile(Tile t){
		m_tile = t;
	}

	public Tile getTile(){
		return m_tile;
	}

	public boolean changeTile(int newX, int newY){
		if(newX > Options.LARGEUR -1 || newY > Options.HAUTEUR - 1|| newX < 0 || newY < 0)
			return false;
		Tile newTile = m_model.getRoom().getTiles()[newX][newY];
		if(newTile.getEntityOnLayer(m_layer)==null){
			getTile().delEntity(this);
			newTile.putEntity(m_layer, this);
			setTile(newTile);
			return true;
		}
		return false;
	}

	public List<Entity> checkTile(Directions d){
		int x = m_tile.m_x;
		int y = m_tile.m_y;
		switch (d) {
			case RIGHT:
				x += 1;
				break;
			case LEFT:
				x += -1;
				break;
			case UP:
				y += -1;
				break;
			case DOWN:
				y += 1;
				break;
			default:
				break;
		}
		if(x > Options.LARGEUR -1 || y > Options.HAUTEUR - 1|| x < 0 || y < 0)
			return null;
		return m_model.getRoom().getTiles()[x][y].m_entities;
	}
	
	public Tile getLookingTile(Directions d){
		int x = m_tile.m_x;
		int y = m_tile.m_y;
		switch (d) {
			case RIGHT:
				x += 1;
				break;
			case LEFT:
				x += -1;
				break;
			case UP:
				y += -1;
				break;
			case DOWN:
				y += 1;
				break;
			default:
				break;
		}
		return m_model.getRoom().getTile(x, y);
	}
	
}
