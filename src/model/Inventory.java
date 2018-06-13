package model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import model.Weapons.WeaponsInv;

public class Inventory {
	AliveEntity m_owner;
	List<Item> m_consumable;
	WeaponsInv m_weapon;

	public Inventory(AliveEntity owner) {
		this(owner, new WeaponsInv(owner, "assets/sprites/pet.png", 1));
	}

	public Inventory(AliveEntity owner, WeaponsInv weapon) {		
		m_owner = owner;
		m_consumable = new ArrayList<Item>();
		m_weapon = weapon;
	}

	public WeaponsInv getWeapon() {
		return m_weapon;
	}
	
	public void switchWeapon(WeaponsInv weapon) {
		m_weapon = weapon;
	}
	
	public void step() {
		m_weapon.step();
	}

	public void paint(Graphics g) {
		m_weapon.paint(g);
	}
	
	public void switchItem(Item i){
		/*
		Tile itemTile = i.getTile();
		if(i.getType() == ItemType.WEAPON){
			itemTile.delEntity(i);
			if(hasWeapon()){
				itemTile.putEntity(0, m_weapon);
				m_weapon.setPosition(itemTile.m_x*Options.TAILLE_CASE, itemTile.m_y*Options.TAILLE_CASE);
			}
			m_weapon = i;
		}*/
	}

}
