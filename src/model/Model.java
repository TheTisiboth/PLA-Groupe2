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

import java.util.ArrayList;
import java.util.List;

import controller.Options;
import edu.ricm3.game.GameModel;
import view.LifeBar;

public class Model extends GameModel {
  Player m_player;
  Level m_level;
  Room m_room;
  List<LifeBar> m_lbList;

  List<Level> m_lvlList;

  int m_lvlID;

  public Model() {
    m_lbList = new ArrayList<LifeBar>();
    m_player = new Player(this, 0, 0, null, 20, 2);
    m_lvlList = new ArrayList<Level>();
    for (int i = 0; i < Options.lvlNb; i++) {
      m_lvlList.add(i, new Level(i,this));
    }
    m_lvlID = 0;
    m_level = m_lvlList.get(m_lvlID);
    m_level.nextRoom();
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
    m_room.update(now);
    for (int i = 0; i < m_lbList.size(); i++) {
      if(m_lbList.get(i) != null && m_lbList.get(i).getEntity().getLife()<=0)
        m_lbList.set(i, null);
    }
    //appeler step sur toutes les entites
	/*
    for (int i = 0; i < m_cowboys.length; i++)
      m_cowboys[i].step(now);*/
  }

  public Player getPlayer() {
    return m_player;
  }
  
  public Room getRoom(){
    return m_room;
  }

  public void addLifeBar(AliveEntity e){
    m_lbList.add(new LifeBar(e));
  }

  public List<LifeBar> getLifeBar(){
    return m_lbList;
  }

  public void nextRoom(){
    flush();
    m_level.nextRoom();
    m_room = m_level.getCurrentRoom();
  }

  public void nextLevel(){

  }

  private void flush(){
    m_lbList = new ArrayList<LifeBar>();
    addLifeBar(m_player);
    m_player.flushPortals();
  }

}
