package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed;
    public boolean downPressed;
    public boolean rigthPressed;
    public boolean leftPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        switch (e.getKeyCode()){
            case KeyEvent.VK_W: upPressed=true; break;
            case KeyEvent.VK_S: downPressed=true; break;
            case KeyEvent.VK_A: leftPressed=true; break;
            case KeyEvent.VK_D: rigthPressed=true; break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W: upPressed=false; break;
            case KeyEvent.VK_S: downPressed=false; break;
            case KeyEvent.VK_A: leftPressed=false; break;
            case KeyEvent.VK_D: rigthPressed=false; break;
        }
    }
}
