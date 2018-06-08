package model;

import controller.Options;

public class Item extends Entity {

	// static String m_spriteFile = Options.sprites.get("item");
	// static int m_layer = 0;
	// static double m_startingSpeed = Options.velocities.get("item"); 

	enum ItemType{
		WEAPON,
		LIFE,
		POISON,
		TRAP
	}

	ItemType m_type;

	public Item(Model model, int x, int y, String spriteFile, Tile t, int life,String type) {
		super(model, x, y, false, spriteFile, t);
		m_layer = Options.layers.get("character");
		setItemType(type);
	}

	public ItemType getType(){
		return m_type;
	}
	
	private void setItemType(String type) {
		
		if(type.equals("life")){
			m_type = ItemType.LIFE;
		}
		else if(type.equals("poison")) {
			m_type = ItemType.POISON;			
		}
		else {
			m_type = ItemType.WEAPON;
		}
		
	}
}
