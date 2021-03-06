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
package view;

import java.awt.Color;
import java.awt.Graphics;

import controller.Controller;
import edu.ricm3.game.GameView;
import model.Model;
import model.Room;
import view.LifeBar;

public class View extends GameView {

  private static final long serialVersionUID = 1L;

  Color m_background = Color.gray;
  long m_last;
  int m_npaints;
  int m_fps;
  Model m_model;
  Controller m_ctr;
  Background m_back;
  Room m_room;


  public View(Model m, Controller c) {
    m_model = m;
    m_ctr = c;
    m_back = new Background();
    setBG();
    m_room = m.getRoom();
    
  }


private void computeFPS() {
    long now = System.currentTimeMillis();
    if (now - m_last > 1000L) {
      m_fps = m_npaints;
      m_last = now;
      m_npaints = 0;
    }
    m_game.setFPS(m_fps, "npaints=" + m_npaints);
    m_npaints++;
  }

  @Override
  protected void _paint(Graphics g) {
    setBG();
    m_room = m_model.getRoom();
    computeFPS();

    m_back.paint(g);
    m_room.paint(g);
    
    for (LifeBar lb : m_model.getLifeBar()) {
      if(lb != null) {
        lb.paint(g);
      }
    }
  }

  public void setBG(){
    m_back.setNewTile(m_model.getLevel().m_tileSprite);
  }

}
