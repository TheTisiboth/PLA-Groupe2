package model;

public class Wall extends Entity {

	static String m_spriteFile = "assets/sprites/wall.png";

	public Wall(Model model, int x, int y) {
		super(model, x, y, false, m_spriteFile, 0.0);
	}

}
