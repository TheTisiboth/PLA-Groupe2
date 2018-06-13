package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Model;



public class RightPanel extends JPanel {
	Model m_model;
	JLabel m_labelLife;
	JLabel m_labelBigLife;	
	JPanel m_panelLife;
	JPanel m_panelBigLife;
	JPanel m_panelWeapon;
	JPanel m_panelPictures;
	Image m_bg;
	
	private static final long serialVersionUID = 1L;
	

	public RightPanel(Model model) {
		
		m_model = model;
		
		this.setPreferredSize(new Dimension(150,704));
		
		m_bg = new ImageIcon("assets/view/Hud.png").getImage().getScaledInstance(150,704,Image.SCALE_DEFAULT);
		
		m_panelWeapon = new AutomataWindow.BackgroundPanel("assets/sprites/item.png",30,25,90,90);
		m_panelWeapon.setLayout(null);
		m_panelWeapon.setPreferredSize(new Dimension(150,130));
		m_panelWeapon.setOpaque(false);
		JLabel fireball = new JLabel(new ImageIcon(new ImageIcon("assets/sprites/item.png").getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT)));
		fireball.setBounds(105,99,32,32);
		m_panelWeapon.add(fireball);
		this.add(m_panelWeapon);
		
		m_panelLife = new AutomataWindow.BackgroundPanel("assets/sprites/littlePotion.png",42,15,65,65);
		m_panelLife.setLayout(null);
		m_panelLife.setPreferredSize(new Dimension(150,95));
		m_panelLife.setOpaque(false);
		m_labelLife = new JLabel("x"+m_model.getPlayer().getInventory().getSmallPotion());
		m_labelLife.setSize(30,10);
		m_labelLife.setLocation(115,47);
		m_panelLife.add(m_labelLife);
		this.add(m_panelLife);
		
		m_panelBigLife = new AutomataWindow.BackgroundPanel("assets/sprites/bigPotion.png",42,1,65,65);
		m_panelBigLife.setLayout(null);
		m_panelBigLife.setPreferredSize(new Dimension(150,81));
		m_panelBigLife.setOpaque(false);
		m_labelBigLife = new JLabel("x"+m_model.getPlayer().getInventory().getBigPotionNbr());
		m_labelBigLife.setSize(30,10);
		m_labelBigLife.setLocation(115,30);
		m_panelBigLife.add(m_labelBigLife);
		this.add(m_panelBigLife);
		
		JButton quitButton = new JButton();
		quitButton.setOpaque(false);
		quitButton.setBorder(BorderFactory.createEmptyBorder(340,0,0,0));
		quitButton.setFocusPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setBorderPainted(false);
		quitButton.setIcon(new ImageIcon(new ImageIcon("assets/view/Quit.png").getImage().getScaledInstance(150,40,Image.SCALE_DEFAULT)));
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m_model.getGameUI().close();
			}
		});
		this.add(quitButton);
		
		//m_hud = new HUD(model);
		
		/*m_panelLife = new JPanel();
		m_labelLife = new JLabel("x"+m_model.getPlayer().getInventory().getNumberLife());
		m_panelLife.add(m_labelLife,BorderLayout.CENTER);
		this.add(m_panelLife,BorderLayout.EAST);
		
		m_panelPoison = new JPanel();
		m_labelPoison = new JLabel("x"+m_model.getPlayer().getInventory().getNumberPoison());
		m_panelPoison.add(m_labelPoison,BorderLayout.EAST);
		this.add(m_panelPoison,BorderLayout.CENTER);
		
		
		m_panelPictures = new JPanel();
		m_panelPictures.setPreferredSize(new Dimension(100,400));
		//m_panelPictures.add(m_hud);
		this.add(m_panelPictures,BorderLayout.WEST);*/
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(m_bg, 0,0,150,704, this);	
	}
	
}
