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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EnumMap;

import edu.ricm3.game.GameController;
import main.Directions;
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
  EnumMap<Directions, Boolean> m_keys;


  public Controller(Model m) {
    m_model = m;

    m_keys = new EnumMap<Directions, Boolean>(Directions.class);

    m_keys.put(Directions.DOWN, false);
    m_keys.put(Directions.UP, false);
    m_keys.put(Directions.RIGHT, false);
    m_keys.put(Directions.LEFT, false);
    m_keys.put(Directions.HIT, false);
    m_keys.put(Directions.PROJECTILE, false);
  }

  /**
   * Simulation step. Warning: the model has already executed its step.
   *
   * @param now
   *          is the current time in milliseconds.
   */
  @Override
  public void step(long now) {
	  if(m_keys.get(Directions.UP))
		  m_model.getPlayer().move(Directions.UP);
	  if(m_keys.get(Directions.DOWN))
		  m_model.getPlayer().move(Directions.DOWN);
	  if(m_keys.get(Directions.LEFT))
		  m_model.getPlayer().move(Directions.LEFT);
	  if(m_keys.get(Directions.RIGHT))
		  m_model.getPlayer().move(Directions.RIGHT);
	  if(m_keys.get(Directions.HIT))
		  m_model.getPlayer().attack();
	  if(m_keys.get(Directions.PROJECTILE))
		  m_model.getPlayer().throwProjectile();
  }

  @Override
  public void keyTyped(KeyEvent e) {
    //if (e.getKeyChar() == '+') {œ
  }

  @Override
  public void keyPressed(KeyEvent e) {
	if(e.getKeyCode() == 37)
		m_keys.put(Directions.LEFT, true);
	if(e.getKeyCode() == 38)
		m_keys.put(Directions.UP, true);
	if(e.getKeyCode() == 39)
		m_keys.put(Directions.RIGHT, true);
	if(e.getKeyCode() == 40)
		m_keys.put(Directions.DOWN, true);
	if(e.getKeyCode() == 32)
		m_model.getPlayer().pick();
	if(e.getKeyCode() == 65)
		m_keys.put(Directions.HIT, true);
	if(e.getKeyCode() == 70)
		m_keys.put(Directions.PROJECTILE, true);
    if (Options.ECHO_KEYBOARD)
    	System.out.println("KeyPressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
  }

  @Override
  public void keyReleased(KeyEvent e) {
	if(e.getKeyCode() == 37)
		m_keys.put(Directions.LEFT, false);
	if(e.getKeyCode() == 38)
		m_keys.put(Directions.UP, false);
	if(e.getKeyCode() == 39)
		m_keys.put(Directions.RIGHT, false);
	if(e.getKeyCode() == 40)
		m_keys.put(Directions.DOWN, false);
	if(e.getKeyCode() == 65)
		m_keys.put(Directions.HIT, false);
	if(e.getKeyCode() == 70)
		m_keys.put(Directions.PROJECTILE, false);


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
