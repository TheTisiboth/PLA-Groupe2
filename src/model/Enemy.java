package model;

public class Enemy extends Entity {

	static String m_spriteFile = "assets/sprites/wall.png";

	static int m_layer = 1;


	public Enemy(Model model, int x, int y, String spriteFile) {
		super(model, x, y, true, spriteFile, 0.1);
	}

}
