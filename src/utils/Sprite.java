package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.Options;

public class Sprite {

    BufferedImage m_spriteSheet;

    public Sprite(String file){
        m_spriteSheet = loadSprite(file);
    }

    public BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public BufferedImage getSprite(int xGrid, int yGrid) {

        return m_spriteSheet.getSubimage(xGrid * Options.TAILLE_CASE, yGrid * Options.TAILLE_CASE, Options.TAILLE_CASE, Options.TAILLE_CASE);
    }
}