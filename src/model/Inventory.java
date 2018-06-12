package model;

import java.util.*;

import controller.Options;
import model.Item.ItemType;

public class Inventory {
	
	Map<ItemType,Integer> m_consumable;
	Item m_weapon;

	public Inventory() {
		m_consumable = new HashMap<ItemType,Integer>();
	}

	public Inventory(Item w, HashMap<ItemType,Integer> c){
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
		else{
			
			itemTile.delEntity(i);
			if(m_consumable.containsKey(i.getType())) {
				m_consumable.put(i.getType(), m_consumable.get(i.getType())+1);
			}
			else {
				m_consumable.put(i.getType(),1);
			}
			
		}
		
		for (Map.Entry mapentry : m_consumable.entrySet()) {
	           System.out.println("clé: "+mapentry.getKey() 
	                              + " | valeur: " + mapentry.getValue());
	        }
		
	}
	
	public int getNumberLife() {
		
		if(m_consumable.get(ItemType.LIFE) != null)
		return m_consumable.get(ItemType.LIFE);
		
		else return 0;
			
	}
	
	public int getNumberPoison() {
		
		if(m_consumable.get(ItemType.POISON) != null)
		return m_consumable.get(ItemType.POISON);
		
		else return 0;
			
	}
	
	public Item getWeapon() {
		
		return m_weapon;
			
	}

}
