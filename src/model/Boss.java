package model;

public class Boss extends Enemy {

	static String m_spriteFile = "assets/sprites/enemy.png";

	public Boss(Model model, int x, int y) {
		super(model, x, y, m_spriteFile);
	}

}
