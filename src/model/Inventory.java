package model;

import java.util.*;

import controller.Options;
import model.Item.ItemType;

public class Inventory {
	
	List<Item> m_consumable;
	Item m_weapon;

	public Inventory() {
		m_consumable = new ArrayList<Item>();
	}

	public Inventory(Item w, ArrayList<Item> c){
		m_weapon = w;
		m_consumable = c;
	}

	public boolean hasWeapon(){
		if (m_weapon == null) {
			return false;
		}
		return true;
	}

	public void switchItem(Item i){
		Tile itemTile = i.getTile();
		if(i.getType() == ItemType.WEAPON){
			itemTile.delEntity(i);
			if(hasWeapon()){
				itemTile.putEntity(0, m_weapon);
				m_weapon.setPosition(itemTile.m_x*Options.TAILLE_CASE, itemTile.m_y*Options.TAILLE_CASE);
			}
			m_weapon = i;
		}
	}

}
