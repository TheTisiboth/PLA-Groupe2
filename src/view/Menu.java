package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controller;
import controller.Options;
import edu.ricm3.game.GameUI;
import model.Model;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton m_playButton;
	private JButton m_automataButton;
	private JButton m_quitButton;
	
	public Menu() {
		this.setSize(1024,704);
		this.setLocationRelativeTo(null);
		
		m_playButton = new JButton();
		m_playButton.setBounds(362,400,300,80);
		m_playButton.setOpaque(false);
		m_playButton.setContentAreaFilled(false);
		m_playButton.setBorderPainted(false);
		m_playButton.setIcon(new ImageIcon("assets/sprites/Play.png"));
		m_playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createShowGame();
			}
		});
		this.add(m_playButton);
		
		m_automataButton = new JButton();
		m_automataButton.setBounds(362,490,300,80);
		m_automataButton.setOpaque(false);
		m_automataButton.setContentAreaFilled(false);
		m_automataButton.setBorderPainted(false);
		m_automataButton.setIcon(new ImageIcon("assets/sprites/Automata.png"));
		m_automataButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AutomataWindow();
			}
		});
		this.add(m_automataButton);
		
		m_quitButton = new JButton();
		m_quitButton.setBounds(362,580,300,80);
		m_quitButton.setOpaque(false);
		m_quitButton.setContentAreaFilled(false);
		m_quitButton.setBorderPainted(false);
		m_quitButton.setIcon(new ImageIcon("assets/sprites/Quit.png"));
		m_quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
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
	    new GameUI(model,view,controller,d);
	    this.setVisible(false);
	}
	
}
