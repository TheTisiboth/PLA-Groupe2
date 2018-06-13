package model;

import controller.Options;
import main.Teams;
import model.Weapons.ItemInv;
import model.Weapons.WeaponsInv;

public class Item extends Entity {

	// static String m_spriteFile = Options.sprites.get("item");
	// static int m_layer = 0;
	// static double m_startingSpeed = Options.velocities.get("item"); 

	public enum ItemType{
		WEAPON,
		CONSUMABLE
	}

	ItemType m_type;
	String m_filepath;
	int m_damage;
	
	public Item(Model model, int x, int y, String spriteFile, Tile t, int damage, ItemType item) {
		super(model, x, y, false, spriteFile, t, Teams.Pickable);
		m_layer = Options.layers.get("character");
		m_type = ItemType.WEAPON;
		m_filepath = spriteFile;
		m_damage = damage;
		m_type = item;
	}
	
	public ItemInv getItemInv(AliveEntity entity) {
		if(m_type == ItemType.WEAPON) {
			return new WeaponsInv(entity, m_filepath, m_damage);
		}
		return null;
	}

	public ItemType getType(){
		return m_type;
	}
}
