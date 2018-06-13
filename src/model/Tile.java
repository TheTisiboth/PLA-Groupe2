package model;

import java.awt.Graphics;

import java.util.*;

import controller.Options;
import controller.Options.TileObject;
import j.J_AI_Definition;
import j.J_Automaton;
import main.Directions;

public class Tile {

	int m_x;
	int m_y;
	ArrayList<Entity> m_entities;
	Model m_model;

	Automate m_automate;

	public Tile(TileObject to, int x, int y, Model m, J_AI_Definition j_ast) {
		m_x = x;
		m_y = y;
		m_model = m;
		m_entities = new ArrayList<Entity>();
		m_entities.add(null);
		m_entities.add(null);
		m_entities.add(null);
		m_entities.add(null);
		switch (to) {
		case WALL:
			putEntity(Options.layers.get("wall"),
					new Wall(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this, -1));
			break;
		case ENEMY:
			Entity enn = m.m_level.getRandomEnemy(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this);
			putEntity(Options.layers.get("character"), enn);
			J_Automaton auto = j_ast.randomAutomaton();
			m_automate = new Automate(enn, auto.getCopy());

			break;
		case BOSS:
			putEntity(Options.layers.get("character"),
					m.m_level.getBoss(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this));
			break;
		case PET:
			putEntity(Options.layers.get("character"),
					m.m_level.getRandomPet(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this));
			break;
		case ITEM:
			putEntity(Options.layers.get("item"),
					m.m_level.getRandomItem(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this));
			break;
		default:
			break;
		}
	}

	public void paint(Graphics g, int l) {
		if (m_entities.get(l) != null) {
			m_entities.get(l).paint(g);
		}
	}

	// if this tile has nothing on the same layer as e, add it to the entity list
	// and change e's tile
	public boolean putEntity(int l, Entity e) {
		if (getEntityOnLayer(l) == null) {
			m_entities.set(l, e);
			e.setTile(this);
			return true;
		}
		return false;
	}

	public Entity getEntityOnLayer(int l) {
		return m_entities.get(l);
	}

	public void delEntity(Entity e) {
		m_entities.set(m_entities.indexOf(e), null);
	}

	public void paint(Graphics g) {
		for (int i = 0; i < 3; i++) {
			if (m_entities.get(i) != null)
				m_entities.get(i).paint(g);
		}
	}

	public void update(long now) {
		for (int i = 0; i < 4; i++) {
			if (m_entities.get(i) != null) {
				m_entities.get(i).step(now);
			}
		}
	}

	public int getCaseX() {
		return m_x;
	}

	public int getCaseY() {
		return m_y;
	}

	public Portal getPortal() {
		return (Portal) m_entities.get(Options.LAYER_PORTAL);
	}

	public boolean hasPortal() {
		if (m_entities.get(Options.LAYER_PORTAL) == null)
			return false;
		return true;
	}

	public void setPortal(Portal portal) {
		m_entities.set(Options.LAYER_PORTAL, portal);

	}

	public Automate getAutomate() {
		return m_automate;
	}

	public Tile nextTile(Directions dir) {
		int x = m_x;
		int y = m_y;
		switch (dir) {
			case RIGHT:
				x += 1;
				break;
			case LEFT:
				x += -1;
				break;
			case UP:
				y += -1;
				break;
			case DOWN:
				y += 1;
				break;
			default:
				break;
		}
		return m_model.getRoom().getTile(x, y);
	}

}
