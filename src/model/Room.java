package model;

import java.awt.Graphics;

import java.util.*;

import controller.Options;
import controller.Options.TileObject;
import j.J_AI_Definition;
import ricm3.parser.Ast;
import ricm3.parser.AutomataParser;
import utils.MapParser;
import ricm3.parser.Ast.*;

// import java.util.List;

// import controller.Options;
// import utils.MapParser;

public class Room {

	TileObject[][] m_startingEntities;
	Tile[][] m_tiles;
	Level m_level;
	Tile m_spawn;
	Tile m_exit;
	Model m_model;

	public Room(Level level, int mapID) {
		m_level = level;
		m_model = level.m_model;
		m_startingEntities = MapParser.getMap(mapID + "");
		m_tiles = new Tile[Options.LARGEUR][Options.HAUTEUR];
		tileConstructor(m_startingEntities);
	}

	private void tileConstructor(TileObject[][] to) {
		Ast ast;
		try {
			ast = AutomataParser.from_file("assets/automates.txt");
			J_AI_Definition j_ast = (J_AI_Definition)((AI_Definitions)ast).make();
			
			for (int i = 0; i < Options.HAUTEUR; i++) {
				for (int j = 0; j < Options.LARGEUR; j++) {
					m_tiles[j][i] = new Tile(to[i][j], j, i, m_level.m_model, j_ast.automata.get(0));
					switch (to[i][j]) {
					case EXIT:
						m_exit = m_tiles[j][i];
						break;
					case SPAWN:
						m_spawn = m_tiles[j][i];
						m_model.getPlayer().setPosition(j * Options.TAILLE_CASE, i * Options.TAILLE_CASE);
						m_spawn.putEntity(m_model.getPlayer().getLayer(), m_model.getPlayer());
						break;
					default:
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		for (int i = 0; i < 4; i++) {
			for (int x = 0; x < Options.LARGEUR; x++) {
				for (int y = 0; y < Options.HAUTEUR; y++) {
					m_tiles[x][y].paint(g, i);
				}
			}
		}
	}

	public void update (long now) {
		for (int i = 0; i < 4; i++) {
			for (int x = 0; x < Options.LARGEUR; x++) {
				for (int y = 0; y < Options.HAUTEUR; y++) {
					m_tiles[x][y].update(now);
					
					if(m_tiles[x][y].getAutomate() != null)
						m_tiles[x][y].getAutomate().step(now);
				}
			}
		}
	}

	public Tile getTile(int x, int y) {
		return m_tiles[x][y];
	}

	public Tile[][] getTiles() {
		return m_tiles;
	}

}
