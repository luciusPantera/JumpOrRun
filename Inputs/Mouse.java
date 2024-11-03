package JumpOrRun.Inputs;

import java.awt.*;
import java.awt.event.*;

import JumpOrRun.Entities.Player;
import JumpOrRun.Gamestates.*;
import JumpOrRun.Main.GamePanel;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener{

    private GamePanel panel;
    private GameMenu menu;
    private Playing playing;
    private Settings settings;
    private Died died;
    private Won won;

    public Mouse(GamePanel gamePanel){
        this.panel = gamePanel;
        playing = gamePanel.getGame().getPlaying();
        menu = gamePanel.getGame().getMenu();
        settings = gamePanel.getGame().getSettings();
        died = gamePanel.getGame().getDied();
        won = gamePanel.getGame().getWon();
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                menu.mouseMoved(e);;
                break;
            case PLAYING:
                playing.mouseMoved(e);;
                break;
            case SETTINGS:
                settings.mouseMoved(e);
                break;
            case DIED:
                died.mouseMoved(e);
                break;
            case WON:
                won.mouseMoved(e);
                break;
            default:
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                menu.mouseClicked(e);
                break;
            case PLAYING:
                playing.mouseClicked(e);
                break;
            case SETTINGS:
                settings.mouseClicked(e);
                break;
            case DIED:
                died.mouseClicked(e);
                break;
            case WON:
                won.mouseClicked(e);
                break;
            default:
                break;
        }
    }

    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                menu.mousePressed(e);;
                break;
            case PLAYING:
                playing.mousePressed(e);;
                break;
            case SETTINGS:
                settings.mousePressed(e);;
                break;
            case DIED:
                died.mousePressed(e);
                break;
            case WON:
                won.mousePressed(e);
                break;
            default:
                break;
        }
    }

    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                menu.mouseReleased(e);;
                break;
            case PLAYING:
                playing.mouseReleased(e);;
                break;
            case SETTINGS:
                settings.mouseReleased(e);;
                break;
            case DIED:
                died.mouseReleased(e);
                break;
            case WON:
                won.mouseReleased(e);
                break;
            default:
                break;
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    
}
