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
import main.Directions;

import java.awt.image.BufferedImage;

public class Model extends GameModel {
  Player m_player;
  Level m_level;
  Room m_room;

  public Model() {
    m_player = new Player(this, 0, 0);
    m_level = new Level(1, 0, this);
    m_room = m_level.getCurrentRoom();

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
	  m_player.step(now);

    //appeler step sur toutes les entites
	/*
    for (int i = 0; i < m_cowboys.length; i++)
      m_cowboys[i].step(now);*/
  }

public Player getPlayer() {
	return m_player;
}

  public Room get_room() {
	return m_room;
  }
}
