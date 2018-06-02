package model;

public class Pet extends Entity {

	static String m_spriteFile = "assets/sprites/wall.png";

	public Pet(Model model, int x, int y) {
		super(model, x, y, m_spriteFile);
	}

}
