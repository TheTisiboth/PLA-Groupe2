package model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import model.Weapons.WeaponsInv;

public class Inventory {
	AliveEntity m_owner;

	int m_smallPotionNbr;
	int m_bigPotionNbr;
	WeaponsInv m_weapon;

	public Inventory(AliveEntity owner) {
		this(owner, new WeaponsInv(owner, "assets/sprites/pet.png", 1));
	}

	public Inventory(AliveEntity owner, WeaponsInv weapon) {
		m_owner = owner;
		m_weapon = weapon;
	}

	public void usePotion() {
		if(m_smallPotionNbr > 0)
			m_owner.setLife(m_owner.getLife() + 1);
		else if(m_smallPotionNbr > 0)
			m_owner.setLife(m_owner.getLife() + 5);
	}

	public void pick(Item item) {
		switch(item.getType()) {
		case smallPotion:
			m_smallPotionNbr++;
			break;
		case bigPotion:
			m_smallPotionNbr++;
			break;
		case WEAPON:
			switchWeapon(((WeaponsInv)item.getItemInv(m_owner)));
			break;
		}
		item.kill();
	}


	public int getSmallPotion() {
		return m_smallPotionNbr;
	}

	public int getBigPotionNbr() {
		return m_bigPotionNbr;
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

	/*
	public void switchItem(Item i){
		Tile itemTile = i.getTile();
		if(i.getType() == ItemType.WEAPON){
			itemTile.delEntity(i);
			if(hasWeapon()){
				itemTile.putEntity(0, m_weapon);
				m_weapon.setPosition(itemTile.m_x*Options.TAILLE_CASE, itemTile.m_y*Options.TAILLE_CASE);
			}
			m_weapon = i;
<<<<<<< HEAD
<<<<<<< HEAD
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

=======
		}
>>>>>>> master
	}*/

}
