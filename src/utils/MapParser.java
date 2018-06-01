package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import controller.Options;
import controller.Options.TileObject;


public class MapParser {

	private static final String MAPPATH = Options.MAPPATH;
	private static final int WIDTH = Options.LARGEUR;
	private static final int HEIGHT = Options.HAUTEUR;
	
	private static final int[][] colors = Options.MAPCOLORS;

	private static final TileObject[] tiles = Options.TILES;
	
	public static TileObject[][] getMap(String mapname){

		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(MAPPATH + mapname + ".ppm");
			br = new BufferedReader(fr);

			String line;
			TileObject[][] tile_list = new TileObject[HEIGHT][WIDTH];
			int[] currentcolor = new int[3];

			//P3
			br.readLine();
			//#created by gimp
			br.readLine();
			//dimensions 32 24
			line = br.readLine();
			if(!line.equals(WIDTH + " " + HEIGHT)) throw new Error("Taille de carte incorrecte");
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
	
	//takes a RGB color and outputs its corresponding entity
	private static TileObject color_to_entity(int[] color){
		for (int i = 0; i < colors.length; i++) {
			if(Arrays.equals(colors[i], color)) return tiles[i];
		}
		return TileObject.UNKNOWN;
	}

}
