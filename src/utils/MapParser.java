package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class MapParser {

	private static final String MAPPATH = "assets/maps/";
	private static final int WIDTH = 32;
	private static final int HEIGHT = 24;
	
	private static final int[][] colors = {
		{255,0,0}, //red = ennemi
		{0,255,0}, //green = item
		{0,0,255}, //blue = 
		{255,255,0}, //yellow = porte
		{255,0,255}, //pink = boss
		{0,255,255}, //cyan	= entree
		{0,0,0}, //black = mur
		{255,255,255} //white = rien
	};

	private static final String[] tiles = {
		"enemy",
		"item",
		"",
		"door",
		"boss",
		"spawn",
		"wall",
		"ground"
	};
	
	public static String[][] getMap(String mapname){

		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(MAPPATH + mapname + ".ppm");
			br = new BufferedReader(fr);

			String line;
			String[][] tile_list = new String[HEIGHT][WIDTH];
			int[] currentcolor = new int[3];

			//P3
			br.readLine();
			//#created by gimp
			br.readLine();
			//dimensions 32 24
			line = br.readLine();
			if(!line.equals("32 24")) throw new Error("Taille de carte incorrecte");
			//taille max = 255
			br.readLine();

			for (int i = 0; i < HEIGHT; i++) {
				for (int j = 0; j < WIDTH; j++) {
					for (int k = 0; k < 3; k++) {
						line = br.readLine();
						currentcolor[k] = Integer.parseInt(line);
					}
					tile_list[i][j] = color_to_entity(currentcolor);

				}
			}

			return tile_list;

		} catch (IOException e) {

			e.printStackTrace();
			return null;

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();
			}
		}
	}
	
	private static String color_to_entity(int[] color){
		for (int i = 0; i < colors.length; i++) {
			if(Arrays.equals(colors[i], color)) return tiles[i];
		}
		return "unknown";
	}

}
