package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AutomataWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String [] AUTOMATA = {"Guerrier", "Chien", "Dragon", "Elfe", "Test1", "Test2", "Test3", "Test4", "Test5", "Test6", "Test7", "Test8", "Test9", "Test10", "Test11", "Test12", "Test13", "Test14", "Test15"};
	private JButton m_returnButton;
	private JButton m_chooseButton;
	private List<JCheckBox> m_checkBoxes = new ArrayList<JCheckBox>();

	public AutomataWindow() {
		this.setSize(680,704);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JPanel bgPane = new BackgroundPanel();
		
		
		bgPane.setBackground(Color.RED);
		
		Box automata = Box.createVerticalBox();
		automata.setOpaque(false);
		JScrollPane scroll = new JScrollPane(automata, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setOpaque(false);
		scroll.setBorder(BorderFactory.createEmptyBorder(90,0,0,0));
		scroll.setPreferredSize(new Dimension(150,590));
		scroll.getViewport().setOpaque(false);
		
		List<String> names = Menu.retrieveChoosenAutomata();
		
		JCheckBox checkAuto;
		for(String auto : AUTOMATA) {
			checkAuto = new JCheckBox(auto);
			if(names.contains(auto)) {
				checkAuto.setSelected(true);
			}
			checkAuto.setIcon(new ImageIcon("assets/view/emptyCheckBox.png"));
			checkAuto.setSelectedIcon(new ImageIcon("assets/view/checkBox.png"));
			checkAuto.setDisabledIcon(new ImageIcon());
			checkAuto.setOpaque(false);
			checkAuto.setContentAreaFilled(false);
			checkAuto.setFocusPainted(false);
			automata.add(checkAuto);
			m_checkBoxes.add(checkAuto);
		}
		
		bgPane.add(scroll, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel();
		
		m_chooseButton = new JButton();
		m_chooseButton.setOpaque(false);
		m_chooseButton.setContentAreaFilled(false);
		m_chooseButton.setBorderPainted(false);
		m_chooseButton.setFocusPainted(false);
		m_chooseButton.setIcon(new ImageIcon("assets/view/Choose.png"));
		m_chooseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				writeChoosenAutomata();
				dispose();
			}
		});
		panelSouth.add(m_chooseButton, BorderLayout.WEST);
		
		m_returnButton = new JButton();
		m_returnButton.setOpaque(false);
		m_returnButton.setContentAreaFilled(false);
		m_returnButton.setBorderPainted(false);
		m_returnButton.setFocusPainted(false);
		m_returnButton.setIcon(new ImageIcon("assets/view/Return.png"));
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
	
	private class BackgroundPanel extends JPanel{

		private static final long serialVersionUID = 1L;
		Image image;
		
		public BackgroundPanel() {
			image = new ImageIcon("assets/view/AutomataBg.png").getImage().getScaledInstance(680,704,Image.SCALE_DEFAULT);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0,0,680,704, this);		
		}
	}
	
	public void writeChoosenAutomata() {
		PrintWriter pw;
		
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter("assets/automata/choosenAutomata.txt")));  //on écrase ce qu'il y a déjà sur le fichier
			for (JCheckBox check : m_checkBoxes) {
				if (check.isSelected()) {
					pw.println(check.getText());
				}
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Can't write in file : chooseAutomata.txt");
		}
		
	}
}
