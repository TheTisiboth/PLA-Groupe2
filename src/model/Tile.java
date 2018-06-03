package model;

import java.awt.Graphics;

import java.util.*;

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
                m_entities.set(Wall.m_layer, new Wall(m, x, y));			
                break;
            case ENEMY:
                m_entities.set(Boss.m_layer, new Boss(m, x, y));			
                break;
            case BOSS:
                m_entities.set(Boss.m_layer, new Boss(m, x, y));			
                break;
            case PET:
                m_entities.set(Pet.m_layer, new Pet(m, x, y));			
                break;
            case ITEM:
                m_entities.set(Item.m_layer, new Item(m, x, y));			
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

}
