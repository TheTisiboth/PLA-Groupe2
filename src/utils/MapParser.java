package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MapParser {

	private static final String MAPPATH = "assets/maps/";
	
	private static final int[][] colors = {
										{255,0,0}, //red = ennemi
										{0,255,0}, //green = item
										{0,0,255}, //blue = 
										{255,255,0}, //yellow = porte
										{255,0,255}, //pink = boss
										{0,255,255}, //cyan	= 
										{0,0,0} //black = mur
	};
	
	public static void get_map(String mapname){

		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(MAPPATH + mapname + ".ppm");
			br = new BufferedReader(fr);

			String line;

			br.readLine();
			line = br.readLine();
			int[] dim = {Integer.parseInt(line.split(" ")[0]),Integer.parseInt(line.split(" ")[1])};
			br.readLine();
			
			int compt = 0;
			
			int color_list[dim[0]][dim[1]];
			
			int rgb[3];
			
			while ((line = br.readLine()) != null) {
						
				System.out.println(line);
				compt = ++compt == 3 ? 0 : compt;
				
			}

		} catch (IOException e) {

			e.printStackTrace();

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
	


}
