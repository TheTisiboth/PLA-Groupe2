package model;

import controller.Options;
import main.Teams;

public class Item extends Entity {

	// static String m_spriteFile = Options.sprites.get("item");
	// static int m_layer = 0;
	// static double m_startingSpeed = Options.velocities.get("item"); 

	enum ItemType{
		WEAPON,
		CONSUMABLE,
		TRAP
	}

	ItemType m_type;

	public Item(Model model, int x, int y, String spriteFile, Tile t, int life) {
		super(model, x, y, false, spriteFile, t, Teams.Pickable);
		m_layer = Options.layers.get("character");
		m_type = ItemType.WEAPON;
	}

	public ItemType getType(){
		return m_type;
	}
}
