/*

 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package controller;

import java.awt.Color;
import java.util.HashMap;

public class Options {
	public static final boolean USE_DOUBLE_BUFFERING = true;

	public static final boolean ECHO_MOUSE = false;
	public static final boolean ECHO_MOUSE_MOTION = false;
	public static final boolean ECHO_KEYBOARD = false;

	public static final int LARGEUR = 32;
	public static final int HAUTEUR = 22;
	public static final int TAILLE_CASE = 32;

	public static final int LARGEUR_PX = LARGEUR * TAILLE_CASE;
	public static final int HAUTEUR_PX = HAUTEUR * TAILLE_CASE;

	public static final String MAPPATH = "assets/maps/";

	public static final HashMap<Color,TileObject> tileColorMap = new HashMap<Color,TileObject>();

	public static final int LAYER_OBJECT = 0;
	//Player Pet Ennemy Wall
	public static final int LAYER_PPEW = 1;
	public static final int LAYER_ATTACK = 2;
	public static final int LAYER_PORTAL = 3;
	
	
	static{
		tileColorMap.put(Color.red,TileObject.ENEMY); //RED
		tileColorMap.put(Color.green,TileObject.ITEM); //GREEN
		tileColorMap.put(Color.blue,TileObject.PET); //BLUE
		tileColorMap.put(Color.yellow,TileObject.EXIT); //YELLOW
		tileColorMap.put(Color.pink,TileObject.BOSS); //PINK
		tileColorMap.put(Color.cyan,TileObject.SPAWN); //CYAN
		tileColorMap.put(Color.black,TileObject.WALL); //BLACK
		tileColorMap.put(Color.white,TileObject.GROUND); //WHITE
	}

	public enum TileObject {
		ENEMY,
		ITEM,
		PET,
		EXIT,
		BOSS,
		SPAWN,
		WALL,
		GROUND,
		UNKNOWN
	}

	public static final HashMap<String,String> sprites = new HashMap<String,String>();

	static{
		sprites.put("wall","assets/sprites/wall.png");
		sprites.put("boss","assets/sprites/boss.png");
		sprites.put("player","assets/sprites/player.png");
		sprites.put("pet","assets/sprites/pet.png");
		sprites.put("item","assets/sprites/item.png");
	}

	public static final HashMap<String,Integer> layers = new HashMap<String,Integer>();

	static{
		layers.put("wall",1);
		layers.put("character",1);
		layers.put("item",0);
		layers.put("projectile",2);
	}

	public static final HashMap<String,Double> velocities = new HashMap<String,Double>();

	static{
		velocities.put("wall",0.0);
		velocities.put("boss",0.1);
		velocities.put("player",0.2);
		velocities.put("pet",1.0);
		velocities.put("item",0.0);
	}


}
