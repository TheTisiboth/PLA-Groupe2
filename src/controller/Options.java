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

public class Options {
  public static final boolean USE_DOUBLE_BUFFERING = true;

  public static final boolean ECHO_MOUSE = true;
  public static final boolean ECHO_MOUSE_MOTION = true;
  public static final boolean ECHO_KEYBOARD = true;
  
  public static final int LARGEUR = 32;
  public static final int HAUTEUR = 24;
  public static final int TAILLE_CASE = 32;
  
  public static final int LARGEUR_PX = LARGEUR * TAILLE_CASE;
  public static final int HAUTEUR_PX = HAUTEUR * TAILLE_CASE;
  
  public static final String MAPPATH = "assets/maps/";
  
  public static final int[][] MAPCOLORS = {
		{255,0,0}, //red = ennemi
		{0,255,0}, //green = item
		{0,0,255}, //blue = 
		{255,255,0}, //yellow = porte
		{255,0,255}, //pink = boss
		{0,255,255}, //cyan	= entree
		{0,0,0}, //black = mur
		{255,255,255} //white = rien
	};

  public static final String[] TILES = {
		"enemy",
		"item",
		"",
		"door",
		"boss",
		"spawn",
		"wall",
		"ground"
	};
}
