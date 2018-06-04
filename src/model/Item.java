package model;

public class Item extends Entity {

	static String m_spriteFile = "assets/sprites/item.png";
	static int m_layer = 0;


	public Item(Model model, int x, int y) {
		super(model, x, y, m_spriteFile);
	}

}
