package model;

public class Item extends Entity {

	static String m_spriteFile = "assets/sprites/wall.png";

	public Item(Model model, int x, int y) {
		super(model, x, y, m_spriteFile);
	}

}
