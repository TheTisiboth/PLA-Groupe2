package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AutomataWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String [] AUTOMATON = {"Guerrier", "Chien", "Dragon", "Elfe"};
	private JButton m_returnButton;
	private JButton m_chooseButton;

	public AutomataWindow() {
		this.setSize(680,704);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		/*JLabel background = new JLabel(new ImageIcon(new ImageIcon("assets/sprites/AutomataBg.png").getImage().getScaledInstance(680,704,Image.SCALE_DEFAULT)));
		background.setBounds(0,0,680,704);
		*/JPanel bgPane = new BackgroundPanel();/*
		bgPane.add(background);*/
		
		
		bgPane.setBackground(Color.RED);
		
		Box automaton = Box.createVerticalBox();
		automaton.setOpaque(false);
		JScrollPane scroll = new JScrollPane(automaton, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setOpaque(false);
		
		JCheckBox checkAuto;
		for(String auto : AUTOMATON) {
			checkAuto = new JCheckBox(auto);
			checkAuto.setIcon(new ImageIcon("assets/sprites/emptyCheckBox.png"));
			checkAuto.setSelectedIcon(new ImageIcon("assets/sprites/checkBox.png"));
			checkAuto.setDisabledIcon(new ImageIcon());
			checkAuto.setOpaque(false);
			checkAuto.setFocusPainted(false);
			automaton.add(checkAuto);
		}
		bgPane.add(scroll, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel();
		
		m_chooseButton = new JButton();
		m_chooseButton.setOpaque(false);
		m_chooseButton.setContentAreaFilled(false);
		m_chooseButton.setBorderPainted(false);
		m_chooseButton.setIcon(new ImageIcon("assets/sprites/Choose.png"));
		m_chooseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				saveAutomaton();
				dispose();
			}
		});
		panelSouth.add(m_chooseButton, BorderLayout.WEST);
		
		m_returnButton = new JButton();
		m_returnButton.setOpaque(false);
		m_returnButton.setContentAreaFilled(false);
		m_returnButton.setBorderPainted(false);
		m_returnButton.setIcon(new ImageIcon("assets/sprites/Return.png"));
		m_returnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		panelSouth.add(m_returnButton, BorderLayout.EAST);
		panelSouth.setOpaque(false);
		
		bgPane.add(panelSouth, BorderLayout.SOUTH);
		
		this.add(bgPane);
		
		this.setVisible(true);
	}
	
	public void saveAutomaton() {
		
	}
	
	class BackgroundPanel extends JPanel{
		Image image;
		
		public BackgroundPanel() {
			image = new ImageIcon("assets/sprites/AutomataBg.png").getImage().getScaledInstance(680,704,Image.SCALE_DEFAULT);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0,0,680,704, this);		
		}
	}
}
