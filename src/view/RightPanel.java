package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Model;
import model.Item;



public class RightPanel extends JPanel {
	
	HUD m_hud;
	Model m_model;
	JLabel m_labelLife;
	JLabel m_labelPoison;	
	JPanel m_panelLife;
	JPanel m_panelPoison;
	JPanel m_panelWeapon;
	JPanel m_panelPictures;
	
	private static final long serialVersionUID = 1L;
	

	public RightPanel(Model model) {
		
		this.setPreferredSize(new Dimension(150,704));
		
		JLabel background = new JLabel(new ImageIcon(new ImageIcon("assets/view/Hud.png").getImage().getScaledInstance(150,704,Image.SCALE_DEFAULT)));
		background.setBounds(0,0,150,704);
		this.add(background);
		
		m_hud = new HUD(model);
		m_model = model;
		
		m_panelWeapon = new JPanel();
		this.add(m_panelWeapon,BorderLayout.NORTH);
		
		m_panelLife = new JPanel();
		m_labelLife = new JLabel("X "+m_model.getPlayer().getInventory().getNumberLife());
		m_panelLife.add(m_labelLife,BorderLayout.CENTER);
		this.add(m_panelLife,BorderLayout.EAST);
		
		m_panelPoison = new JPanel();
		m_labelPoison = new JLabel("X "+m_model.getPlayer().getInventory().getNumberPoison());
		m_panelPoison.add(m_labelPoison,BorderLayout.EAST);
		this.add(m_panelPoison,BorderLayout.CENTER);
		
		
		m_panelPictures = new JPanel();
		m_panelPictures.setPreferredSize(new Dimension(100,400));
		m_panelPictures.add(m_hud);
		this.add(m_panelPictures,BorderLayout.WEST);
		
	}
	
}
