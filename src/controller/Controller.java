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

import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import edu.ricm3.game.GameController;
import edu.ricm3.game.*;
import controller.Options;
import model.Model;

/**
 * This class is to illustrate the most simple game controller. It does not
 * much, but it shows how to obtain the key strokes, mouse buttons, and mouse
 * moves.
 * 
 * With ' ', you see what you should never do, SLEEP. With '+' and '-', you can
 * add or remove some simulated overheads.
 * 
 * @author Pr. Olivier Gruber
 */

public class Controller extends GameController implements ActionListener {

  Model m_model;
  //Music m_player;
  public Controller(Model m) {
    m_model = m;
  }

  /**
   * Simulation step. Warning: the model has already executed its step.
   * 
   * @param now
   *          is the current time in milliseconds.
   */
  @Override
  public void step(long now) {
  }

  @Override
  public void keyTyped(KeyEvent e) {
    //if (e.getKeyChar() == '+') {œ&
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (Options.ECHO_KEYBOARD)
      System.out.println("KeyPressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (Options.ECHO_KEYBOARD)
      System.out.println("KeyReleased: " + e.getKeyChar() + " code=" + e.getKeyCode());
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (Options.ECHO_MOUSE)
      System.out.println("MouseClicked: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (Options.ECHO_MOUSE)
      System.out.println("MousePressed: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (Options.ECHO_MOUSE)
      System.out.println("MouseReleased: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (Options.ECHO_MOUSE_MOTION)
      System.out.println("MouseEntered: (" + e.getX() + "," + e.getY());
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if (Options.ECHO_MOUSE_MOTION)
      System.out.println("MouseExited: (" + e.getX() + "," + e.getY());
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (Options.ECHO_MOUSE_MOTION)
      System.out.println("MouseDragged: (" + e.getX() + "," + e.getY());
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (Options.ECHO_MOUSE_MOTION)
      System.out.println("MouseMoved: (" + e.getX() + "," + e.getY());
  }

  public void notifyVisible() {
	  
  }

  public void actionPerformed(ActionEvent e) {
	  
  }

}
