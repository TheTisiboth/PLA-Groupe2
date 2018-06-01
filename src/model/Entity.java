package model;

import java.awt.Graphics;

public abstract class Entity {
	Model m_model;
	
	int pixelX, pixelY;
	boolean m_movable;
	boolean m_traversable;
	
	public Entity(Model model) {
		m_model = model;
	}
	
	public void move() {
		
	}
	
	public void hit() {
		
	}

	public void paint(Graphics g){
		
	}

}
