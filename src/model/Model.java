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
import java.util.Iterator;
import java.util.List;

import controller.Options;
import edu.ricm3.game.GameModel;
import view.EndGame;
import j.J_AI_Definition;
import view.LifeBar;
import view.View;

public class Model extends GameModel {
  Player m_player;
  Level m_level;
  Room m_room;
  List<LifeBar> m_lbList;
  J_AI_Definition m_ast;

  List<Level> m_lvlList;
  Iterator<Level> m_lvlIt;

  int m_lvlID;

  boolean m_done;

  public Model(J_AI_Definition ast) {
	m_ast = ast;
    m_lbList = new ArrayList<LifeBar>();
    m_player = new Player(this, 0, 0, null, 20, 2);
    m_lvlList = new ArrayList<Level>();
    m_lvlID = 0;
    loadLevels();
    m_room = m_level.getCurrentRoom();
    m_done = false;
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
    if(m_player.getLife()<=0)
      end(false);
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
    if(!m_level.nextRoom())
      nextLevel();
    m_room = m_level.getCurrentRoom();
  }

	public void nextLevel() {
		if(m_lvlIt.hasNext()){
      m_level =m_lvlIt.next();
      if(m_level.getCurrentRoom() == null)
        m_level.nextRoom();
    }
    else
      end(true);
    m_room = m_level.getCurrentRoom();
	}
  private void flush(){
    m_lbList = new ArrayList<LifeBar>();
    addLifeBar(m_player);
    m_player.flushPortals();
  }

  private void loadLevels(){
    for (int i = 0; i < Options.lvlNb; i++) {
      m_lvlList.add(i, new Level(i,this));
    }
    m_lvlIt = m_lvlList.iterator();
    nextLevel();
  }

  private void end(boolean win){
    if(!m_done)
      m_game.setView(new EndGame(win));
    m_done = true;
  }

  public J_AI_Definition getAst() {
	  return m_ast;
  }

  public Level getLevel(){
    return m_level;
  }

}
