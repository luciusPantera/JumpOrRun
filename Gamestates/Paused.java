package JumpOrRun.Gamestates;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import JumpOrRun.Main.Game;
import JumpOrRun.Utils.Load;

public class Paused extends State{

    BufferedImage background;

    public Paused(Game game) {
        super(game);
        background = Load.GetImage("pause_menu.png");
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        int width = (int)(background.getWidth() * Game.scale);
        int height = (int)(background.getHeight() * Game.scale);
        int x = (Game.GameWidth/2) - (width / 2);
        int y = (int)( 30 * Game.scale);
        g.drawImage(background, x, y, width, height, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                Gamestate.state = Gamestate.PLAYING;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseWheelMoved'");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
    
}
