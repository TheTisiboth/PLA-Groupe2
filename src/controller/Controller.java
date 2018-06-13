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
import main.Actions;
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
	EnumMap<Directions, Boolean> m_directions;
	EnumMap<Directions, Boolean> m_orientations;
	EnumMap<Actions, Boolean> m_actions;

	public Controller(Model m) {
		m_model = m;

		m_directions = new EnumMap<Directions, Boolean>(Directions.class);

		m_directions.put(Directions.DOWN, false);
		m_directions.put(Directions.UP, false);
		m_directions.put(Directions.RIGHT, false);
		m_directions.put(Directions.LEFT, false);

		m_orientations = new EnumMap<Directions, Boolean>(m_directions);

		m_actions = new EnumMap<Actions, Boolean>(Actions.class);

		m_actions.put(Actions.HIT, false);
		m_actions.put(Actions.PROJECTILE, false);
		m_actions.put(Actions.PORTAL, false);
		m_actions.put(Actions.PICK, false);
		m_actions.put(Actions.WIZZ, false);
		m_actions.put(Actions.POP, false);
		m_actions.put(Actions.POTION, false);

	}

	/**
	 * Simulation step. Warning: the model has already executed its step.
	 *
	 * @param now
	 *            is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {

		// movement

		if (m_directions.get(Directions.UP))
			m_model.getPlayer().move(Directions.UP);
		else if (m_directions.get(Directions.DOWN))
			m_model.getPlayer().move(Directions.DOWN);
		else if (m_directions.get(Directions.LEFT))
			m_model.getPlayer().move(Directions.LEFT);
		else if (m_directions.get(Directions.RIGHT))
			m_model.getPlayer().move(Directions.RIGHT);

		// orientation

		if (m_orientations.get(Directions.UP))
			m_model.getPlayer().setOrientation(Directions.UP);
		else if (m_orientations.get(Directions.DOWN))
			m_model.getPlayer().setOrientation(Directions.DOWN);
		else if (m_orientations.get(Directions.LEFT))
			m_model.getPlayer().setOrientation(Directions.LEFT);
		else if (m_orientations.get(Directions.RIGHT))
			m_model.getPlayer().setOrientation(Directions.RIGHT);

		if (m_actions.get(Actions.HIT)) {
			m_model.getPlayer().attack();
			m_actions.put(Actions.HIT, false);
		}
		if (m_actions.get(Actions.PROJECTILE)) {
			m_model.getPlayer().throwProjectile();
			m_actions.put(Actions.PROJECTILE, false);
		}
		if (m_actions.get(Actions.PICK)) {
			m_model.getPlayer().pick();
			m_actions.put(Actions.PICK, false);
		}
		if (m_actions.get(Actions.WIZZ)) {
			m_model.getPlayer().wizz();
			m_actions.put(Actions.WIZZ, false);
		}
		if (m_actions.get(Actions.POP)) {
			m_model.getPlayer().pop();
			m_actions.put(Actions.POP, false);
		}
		if (m_actions.get(Actions.POTION)) {
			m_model.getPlayer().usePotion();
			m_actions.put(Actions.POTION, false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 81) // q
			m_directions.put(Directions.LEFT, true);
		if (e.getKeyCode() == 90) // z
			m_directions.put(Directions.UP, true);
		if (e.getKeyCode() == 68) // d
			m_directions.put(Directions.RIGHT, true);
		if (e.getKeyCode() == 83) // s
			m_directions.put(Directions.DOWN, true);
		if (e.getKeyCode() == 37)
			m_orientations.put(Directions.LEFT, true);
		if (e.getKeyCode() == 38)
			m_orientations.put(Directions.UP, true);
		if (e.getKeyCode() == 39)
			m_orientations.put(Directions.RIGHT, true);
		if (e.getKeyCode() == 40)
			m_orientations.put(Directions.DOWN, true);

		if (e.getKeyCode() == 65) // a
			m_actions.put(Actions.HIT, true);
		if (e.getKeyCode() == 70) // f
			m_actions.put(Actions.PROJECTILE, true);
		if (e.getKeyCode() == 69) // e
			m_actions.put(Actions.PICK, true);
		if (e.getKeyCode() == 32) // space
			m_actions.put(Actions.WIZZ, true);
		if (e.getKeyCode() == 17) // ctrl
			m_actions.put(Actions.POP, true);
		if(e.getKeyCode() == 82) 
			m_actions.put(Actions.POTION, true);

		if (Options.ECHO_KEYBOARD)
			System.out.println("KeyPressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37)
			m_orientations.put(Directions.LEFT, false);
		if (e.getKeyCode() == 38)
			m_orientations.put(Directions.UP, false);
		if (e.getKeyCode() == 39)
			m_orientations.put(Directions.RIGHT, false);
		if (e.getKeyCode() == 40)
			m_orientations.put(Directions.DOWN, false);

		if (e.getKeyCode() == 81) // q
			m_directions.put(Directions.LEFT, false);
		if (e.getKeyCode() == 90) // z
			m_directions.put(Directions.UP, false);
		if (e.getKeyCode() == 68) // d
			m_directions.put(Directions.RIGHT, false);
		if (e.getKeyCode() == 83) // s
			m_directions.put(Directions.DOWN, false);

		if (e.getKeyCode() == 65)
			m_actions.put(Actions.HIT, false);
		if (e.getKeyCode() == 70)
			m_actions.put(Actions.PROJECTILE, false);
		if (e.getKeyCode() == 69) // e
			m_actions.put(Actions.PICK, false);
		if (e.getKeyCode() == 32) // space
			m_actions.put(Actions.WIZZ, false);
		if (e.getKeyCode() == 17) // ctrl
			m_actions.put(Actions.POP, false);
		if(e.getKeyCode() == 83) //r
			m_actions.put(Actions.POTION, false);
		
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
