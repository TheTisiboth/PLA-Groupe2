package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {
	private JButton m_playButton;
	private JButton m_optionsButton;
	private JButton m_quitButton;
	
	public Menu() {
		this.setLayout(null);
		this.setBounds(0,0,1024,704);
		
		JLabel m_text = new JLabel();
		m_text.setText("Starting up...");
		this.add(m_text);
		
		/*ImageIcon backgroundImage = new ImageIcon("assets/img/MainMenu.png");
		JLabel background = new JLabel();
		background.setIcon(backgroundImage);
		background.setVisible(true);
		this.add(background, BorderLayout.CENTER);*/
		
		this.setVisible(true);
	}
	
}
