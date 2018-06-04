package model;

import java.awt.Graphics;

import java.util.*;

import controller.Options;
import controller.Options.TileObject;

public class Tile {

    int m_x;
    int m_y;
    ArrayList <Entity> m_entities;

    public Tile(TileObject to, int x, int y, Model m){
        m_x = x;
        m_y = y;
        m_entities = new ArrayList<Entity>();
        m_entities.add(null);
        m_entities.add(null);
        m_entities.add(null);
        switch (to) {
            case WALL:
                putEntity(Options.layers.get("wall"), new Wall(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this));			
                break;
            case ENEMY:
                putEntity(Options.layers.get("character"), new Boss(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this));			
                break;
            case BOSS:
                putEntity(Options.layers.get("character"), new Boss(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this));			
                break;
            case PET:
                putEntity(Options.layers.get("character"), new Pet(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this));			
                break;
            case ITEM:
                putEntity(Options.layers.get("item"), new Item(m, x * Options.TAILLE_CASE, y * Options.TAILLE_CASE, this));			
                break;
            default:
                break;
        }
    }

    public void paint(Graphics g){
        for (int i = 0; i < 3; i++) {
            if(m_entities.get(i) != null)
                m_entities.get(i).paint(g);
        }
    }

    public void putEntity(int l, Entity e){
        if(getEntityOnLayer(l) == null)
            m_entities.set(l, e);			
    }

    public Entity getEntityOnLayer(int l){
        return m_entities.get(l);
    }

}
