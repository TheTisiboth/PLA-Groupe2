package view;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AutomataWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String [] AUTOMATON = {"Guerrier", "Chien", "Dragon", "Elfe"};
	private JButton m_returnButton;
	private JButton m_chooseButton;

	public AutomataWindow() {
		this.setSize(1024,704);
		this.setLocationRelativeTo(null);
		
		Box automaton = Box.createVerticalBox();
		
		JCheckBox checkAuto;
		for(String auto : AUTOMATON) {
			checkAuto = new JCheckBox(auto);
			checkAuto.setIcon(new ImageIcon("assets/sprites/emptyCheckBox.png"));
			checkAuto.setSelectedIcon(new ImageIcon("assets/sprites/checkBox.png"));
			checkAuto.setDisabledIcon(new ImageIcon());
			automaton.add(checkAuto);
		}
		this.add(automaton, BorderLayout.CENTER);
		
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
		
		this.add(panelSouth, BorderLayout.SOUTH);
		
		
		
		
		this.setVisible(true);
	}
	
	public void saveAutomaton() {
		
	}
}
