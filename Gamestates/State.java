package JumpOrRun.Gamestates;

import java.awt.*;
import java.awt.event.*;

import JumpOrRun.Main.Game;

public abstract class State {

    protected Game game;

    State(Game game){
        this.game = game;
    }
    
    public abstract void update();
    public abstract void render(Graphics g);
    public abstract void keyTyped(KeyEvent e);
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
    public abstract void mouseWheelMoved(MouseWheelEvent e);
    public abstract void mouseDragged(MouseEvent e);
    public abstract void mouseMoved(MouseEvent e);
    public abstract void mouseClicked(MouseEvent e);
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public abstract void mouseEntered(MouseEvent e);
    public abstract void mouseExited(MouseEvent e);
}
