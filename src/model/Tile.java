package model;

import java.awt.Graphics;

import java.util.*;

import controller.Options;
import controller.Options.TileObject;

public class Tile {

	int m_x;
	int m_y;
	ArrayList<Entity> m_entities;
	Model m_model;

	public Tile(TileObject to, int x, int y, Model m) {
		m_x = x;
		m_y = y;
		m_model = m;
		m_entities = new ArrayList<Entity>();
		m_entities.add(null);
		m_entities.add(null);
		m_entities.add(null);
		switch (to) {
		case WALL:
			putEntity(Wall.m_layer, new Wall(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this));
			break;
		case ENEMY:
			putEntity(Boss.m_layer,
					m.m_level.getRandomEnemy(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this));
			break;
		case BOSS:
			putEntity(Boss.m_layer, m.m_level.getBoss(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this));
			break;
		case PET:
			putEntity(Pet.m_layer, m.m_level.getRandomPet(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this));
			break;
		case ITEM:
			putEntity(Item.m_layer, m.m_level.getRandomItem(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this));
			break;
		default:
			break;
		}
	}

	public void paint(Graphics g) {
		for (int i = 0; i < 3; i++) {
			if (m_entities.get(i) != null)
				m_entities.get(i).paint(g);
		}
	}

	public void putEntity(int l, Entity e) {
		if (m_entities.get(l) == null)
			m_entities.set(l, e);
	}

}
