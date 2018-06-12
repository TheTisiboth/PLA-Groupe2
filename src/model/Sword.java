package model;

public class Sword extends Item{
	
	

	public Sword(Model model, int x, int y, String spriteFile, Tile t, int life, String type, int attack) {
		super(model, x, y, spriteFile, t, life, type);
		
		super.m_damage = attack;
		
	}
	
	public int getdamage(){
		return m_damage;
	}

}
