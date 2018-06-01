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

	public static final boolean ECHO_MOUSE = true;
	public static final boolean ECHO_MOUSE_MOTION = true;
	public static final boolean ECHO_KEYBOARD = true;
	
	public static final int LARGEUR = 32;
	public static final int HAUTEUR = 22;
	public static final int TAILLE_CASE = 32;
	
	public static final int LARGEUR_PX = LARGEUR * TAILLE_CASE;
	public static final int HAUTEUR_PX = HAUTEUR * TAILLE_CASE;
	
	public static final String MAPPATH = "assets/maps/";

	public static HashMap<Color,TileObject> tileColorMap = new HashMap<Color,TileObject>();

	static{
		tileColorMap.put(Color.red,TileObject.ENEMY); //RED
		tileColorMap.put(Color.green,TileObject.ITEM); //GREEN
		tileColorMap.put(Color.blue,TileObject.PET); //BLUE
		tileColorMap.put(Color.yellow,TileObject.DOOR); //YELLOW
		tileColorMap.put(Color.pink,TileObject.BOSS); //PINK
		tileColorMap.put(Color.cyan,TileObject.SPAWN); //CYAN
		tileColorMap.put(Color.black,TileObject.WALL); //BLACK
		tileColorMap.put(Color.white,TileObject.GROUND); //WHITE
	}

	public enum TileObject {
		ENEMY,
		ITEM,
		PET,
		DOOR,
		BOSS,
		SPAWN,
		WALL,
		GROUND,
		UNKNOWN
	}

}
