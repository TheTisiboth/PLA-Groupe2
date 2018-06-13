package model.Weapons;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import controller.Options;
import main.Directions;
import model.AliveEntity;
import model.Entity;

public  class WeaponsInv extends ItemInv{	
	AliveEntity m_entity;
	int m_damage;
	BufferedImage m_sprite;
	BufferedImage m_origSprite;
	
	boolean m_isAttacking;
	
	long cooldown;
	long attackTime = 200;
	long lastAttack;
	
	public WeaponsInv(AliveEntity entity, String filePath, int damage) {
		m_entity = entity;
		m_isAttacking = false;
		m_damage = damage;
		
        try {
            m_sprite = ImageIO.read(new File(filePath));
            m_origSprite = ImageIO.read(new File(filePath));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void step() {
		if(m_isAttacking)
			if(System.currentTimeMillis() - lastAttack > attackTime)
				m_isAttacking = false;
				
	}
	
	public void rotate(Directions dir) {
		dir = m_entity.RelativeToRealDir(dir);
		//Rotate 
		AffineTransform tx = new AffineTransform();
		switch(dir) {
		case DOWN:
			tx.rotate(Math.PI / 2, m_origSprite.getWidth() / 2, m_origSprite.getHeight() / 2);
			break;
		case UP:
			tx.rotate(-(Math.PI / 2), m_origSprite.getWidth() / 2, m_origSprite.getHeight() / 2);
			break;
		case LEFT:
			tx.rotate(Math.PI, m_origSprite.getWidth() / 2, m_origSprite.getHeight() / 2);
		}

		AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
		m_sprite = op.filter(m_origSprite, null);
	}
	
	public void hit() {
		if(m_isAttacking == false) {
			m_isAttacking = true;
			lastAttack = System.currentTimeMillis();
			
			List<Entity> list = m_entity.checkTile(m_entity.getOrientation());
			if(list.get(1) instanceof AliveEntity) {
				AliveEntity enemy = (AliveEntity) list.get(1);
				enemy.setLife(enemy.getLife()- m_damage);
			}
			return;
		}
	}
	
	public void paint(Graphics g) {
		if(m_isAttacking) {
			switch(m_entity.getOrientation()) {
			case LEFT:
				g.drawImage(m_sprite, m_entity.getPositionX() - (Options.TAILLE_CASE / 2), m_entity.getPositionY(), Options.TAILLE_CASE, Options.TAILLE_CASE, null);
				break;
			case RIGHT: 
				g.drawImage(m_sprite, m_entity.getPositionX() + (Options.TAILLE_CASE / 2), m_entity.getPositionY(), Options.TAILLE_CASE, Options.TAILLE_CASE, null);
				break;
			case UP:
				g.drawImage(m_sprite, m_entity.getPositionX(), m_entity.getPositionY() - (Options.TAILLE_CASE / 2), Options.TAILLE_CASE, Options.TAILLE_CASE, null);
				break;
			case DOWN:
				g.drawImage(m_sprite, m_entity.getPositionX(), m_entity.getPositionY() + (Options.TAILLE_CASE / 2), Options.TAILLE_CASE, Options.TAILLE_CASE, null);
				break;
			}			
		}
	}
	

}
