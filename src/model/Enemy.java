package model;

public class Enemy extends Entity {

	static String m_spriteFile = "assets/sprites/wall.png";

	public Enemy(Model model, int x, int y, String spriteFile) {
		super(model, x, y, spriteFile);
	}

}
