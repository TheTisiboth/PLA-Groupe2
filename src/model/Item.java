package model;

public class Item extends Entity {

	static String m_spriteFile = "assets/sprites/item.png";

	public Item(Model model, int x, int y) {
		super(model, x, y, false, m_spriteFile, 0.0);
	}

}
