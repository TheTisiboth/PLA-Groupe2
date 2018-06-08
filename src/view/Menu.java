package view;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.GameMain;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton m_playButton;
	private JButton m_automataButton;
	private JButton m_quitButton;
	
	public Menu() {
		this.setSize(1024,704);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("PLA - Groupe2 - Bandol Party");
		
		m_playButton = new JButton();
		m_playButton.setBounds(362,400,300,80);
		m_playButton.setOpaque(false);
		m_playButton.setContentAreaFilled(false);
		m_playButton.setBorderPainted(false);
		m_playButton.setIcon(new ImageIcon("assets/sprites/Play.png"));
		m_playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameMain.createShowGame();
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
	
	public static List<String> retrieveChoosenAutomata() {
		List<String> names = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("assets/automata/choosenAutomata.txt")));
			
			String nameAutomaton;
			try {
				nameAutomaton = br.readLine();
				
				while (nameAutomaton != null) {
					names.add(nameAutomaton);
					nameAutomaton = br.readLine();
				}
				
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Can't read file : choosenAutomata.txt");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Can't open file : choosenAutomata.txt");
		}
		
		return names;
		
	}
	
}
