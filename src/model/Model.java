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
package model;

import edu.ricm3.game.GameModel;

public class Model extends GameModel {
  //BufferedImage m_cowboySprite;

  public Model() {
	  loadSprites();
  }
  
  @Override
  public void shutdown() {
    
  }
  
  /**
   * Simulation step.
   * 
   * @param now
   *          is the current time in milliseconds.
   */
  @Override
  public void step(long now) {
    //appeler step sur toutes les entites
	/*
    for (int i = 0; i < m_cowboys.length; i++)
      m_cowboys[i].step(now);*/
  }

  private void loadSprites() {
	  /*Exemple chargement sprites
    File imageFile = new File("game.sample/sprites/winchester.png");
    try {
      m_cowboySprite = ImageIO.read(imageFile);
    } catch (IOException ex) {
      ex.printStackTrace();
      System.exit(-1);
    }*/
  }

}
