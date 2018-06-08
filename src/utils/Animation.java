package utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class Animation {

    private int m_frameCount;                 // Counts ticks for change
    private int m_frameDelay;                 // frame delay 1-12 (You will have to play around with 
    private int m_currentFrame;               // animations current frame
    private int m_animationDirection;         // animation direction (i.e counting forward or backward)
    private int m_totalFrames;                // total amount of frames for your animation

    private boolean m_stopped;                // has animations stopped

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
            addFrame(frames[i]);
        }

        m_frameCount = 0;
        m_frameDelay = frameDelay;
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
    }

    public void stop() {
        if (m_frames.size() == 0) {
            return;
        }

        m_stopped = true;
    }

    public void restart() {
        if (m_frames.size() == 0) {
            return;
        }

        m_stopped = false;
        m_currentFrame = 0;
    }

    public void reset() {
        m_stopped = true;
        m_frameCount = 0;
        m_currentFrame = 0;
    }

    private void addFrame(BufferedImage frame) {
        m_frames.add(frame);
        m_currentFrame = 0;
    }

    public BufferedImage getSprite() {
        return m_frames.get(m_currentFrame);
    }

    public void update() {
        if (!m_stopped) {
            m_frameCount++;

            if (m_frameCount > m_frameDelay) {
                m_frameCount = 0;
                m_currentFrame += m_animationDirection;

                if (m_currentFrame > m_totalFrames - 1) {
                    m_currentFrame = 0;
                }
                else if (m_currentFrame < 0) {
                    m_currentFrame = m_totalFrames - 1;
                }
            }
        }

    }

}