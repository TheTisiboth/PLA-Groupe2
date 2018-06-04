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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Model extends GameModel {
  BufferedImage m_sprite;
  Room m_room;
  public Portal m_portal;
  public Portal m_portalDest;
  
  Entity m_perso;

  public Model() {
	  loadSprites();
	  m_perso = new Entity(this, 0, 0, true, "assets/sprite.png",0.1);
	  m_room = new Room(new Level(2, 5, this), 0);
	  
	  m_portal = new Portal(this, 128, 128, Directions.LEFT);
	  m_portalDest = new Portal(this, 256, 256, Directions.UP);
	  m_portal.AddDest(m_portalDest);
	  
	  m_portal.GoThrough(m_perso);
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
	  m_perso.step(now);
	
	  
    //appeler step sur toutes les entites
	/*
    for (int i = 0; i < m_cowboys.length; i++)
      m_cowboys[i].step(now);*/
  }

  private void loadSprites() {
	 
    File imageFile = new File("assets/sprite.png");
    try {
      m_sprite = ImageIO.read(imageFile);
    } catch (IOException ex) {
      ex.printStackTrace();
      System.exit(-1);
    }
  }

  public Entity get_perso() {
	return m_perso;
  }

  public Room get_room() {
	return m_room;
  }
  
  

}
