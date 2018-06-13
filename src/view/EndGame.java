package view;

import java.awt.Color;
import java.awt.Graphics;

import controller.Options;
import edu.ricm3.game.GameView;

public class EndGame extends GameView {

	String m_txt;
	Color m_bgColor;

	public EndGame(boolean win) {
		this.setBackground(Color.black);
		this.setBounds(0, 0, Options.LARGEUR_PX, Options.HAUTEUR_PX);
		if(win){
			m_txt = "Vous avez gagné, bravo";
			m_bgColor = Color.GREEN;
		}
		else{
			m_txt = "Pas de bol !";
			m_bgColor = Color.RED;

		}

	}

	@Override
	protected void _paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(m_bgColor);
		g.fillRect(0, 0, Options.LARGEUR_PX, Options.HAUTEUR_PX);
		g.setColor(Color.black);
		g.drawString(m_txt, 200, 200);

	}

}
