package utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class Animation {

    private int m_frameCount;                  
    private int m_frameDelay;                 
    private int m_currentFrame;               
    private int m_totalFrames;                 

    private boolean m_stopped;                

    List<BufferedImage> m_frames;

    public Animation(BufferedImage[] frames, int frameDelay) {
        m_frameDelay = frameDelay;
        m_stopped = true;

        if (m_frameDelay <= 0) {
            System.err.println("Invalid duration: " + frameDelay);
            throw new RuntimeException("Invalid duration: " + frameDelay);
        }

        m_frames = new ArrayList<BufferedImage>();

        for (int i = 0; i < frames.length; i++) {
            m_frames.add(frames[i]);
        }

        m_frameCount = 0;
        m_currentFrame = 0;
        m_totalFrames = m_frames.size();

    }

    public void start() {
        if (!m_stopped) {
            return;
        }

        if (m_frames.size() == 0) {
            return;
        }

        m_stopped = false;
        m_frameCount = 0;
        m_currentFrame = 0;

    }

    public void stop() {
        if (m_frames.size() == 0) {
            return;
        }

        m_stopped = true;
    }

    public BufferedImage getSprite() {
        return m_frames.get(m_currentFrame);
    }

    public void update() {
        if (!m_stopped) {
            m_frameCount++;

            if (m_frameCount > m_frameDelay) {
                System.out.println("current frame : "+ m_currentFrame);
                m_frameCount = 0;
                m_currentFrame ++;

                if (m_currentFrame > m_totalFrames - 1) {
                    m_currentFrame = 0;
                }
            }
        }

    }

}