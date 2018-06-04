package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import controller.Options;
import edu.ricm3.game.GameUI;
import model.Model;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton m_playButton;
	private JButton m_optionsButton;
	private JButton m_quitButton;
	
	public Menu() {
		this.setSize(1024,704);
		this.setLocationRelativeTo(null);
		
		m_playButton = new JButton("Jouer");
		m_playButton.setBounds(387,400,250,60);
		m_playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createShowGame();
			}
		});
		this.add(m_playButton);
		
		// construct the game elements: model, controller, and view.
	    Model model = new Model();
	    Controller controller = new Controller(model);
	    View view = new View(model,controller);

	    Dimension d = new Dimension(Options.LARGEUR_PX, Options.HAUTEUR_PX);
	    new GameUI(model,view,controller,d);
		
		m_optionsButton = new JButton("Options");
		m_optionsButton.setBounds(387,465,250,60);
		this.add(m_optionsButton);
		
		m_quitButton = new JButton("Quitter");
		m_quitButton.setBounds(387,530,250,60);
		this.add(m_quitButton);
		
		
		JLabel background = new JLabel(new ImageIcon(new ImageIcon("assets/img/MainMenu.png").getImage().getScaledInstance(1024,704,Image.SCALE_DEFAULT)));
		background.setBounds(0,0,1024,704);
		this.add(background);
		
		this.setVisible(true);
	}
	
	private void createShowGame() {
		// construct the game elements: model, controller, and view.
	    Model model = new Model();
	    Controller controller = new Controller(model);
	    View view = new View(model,controller);

	    Dimension d = new Dimension(Options.LARGEUR_PX, Options.HAUTEUR_PX);
	    GameUI game = new GameUI(model,view,controller,d);
	    game.displayWindow();
	    this.setVisible(false);
	}
	
}
