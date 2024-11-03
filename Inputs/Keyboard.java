package JumpOrRun.Inputs;

import java.awt.*;
import java.awt.event.*;

import JumpOrRun.Main.*;
import JumpOrRun.Entities.*;
import JumpOrRun.Gamestates.*;

public class Keyboard implements KeyListener{

    GamePanel gamePanel;
    private GameMenu menu;
    private Playing playing;
    private Settings settings;
    private Paused paused;
    private Died died;
    private Won won;

    public Keyboard(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        playing = gamePanel.getGame().getPlaying();
        menu = gamePanel.getGame().getMenu();
        settings = gamePanel.getGame().getSettings();
        paused = gamePanel.getGame().getPaused();
        died = gamePanel.getGame().getDied();
        won = gamePanel.getGame().getWon();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                menu.keyPressed(e);
                break;
            case PLAYING:
                playing.keyPressed(e);
                break;
            case SETTINGS:
                settings.keyPressed(e);
                break;
            case PAUSED:
                paused.keyPressed(e);
                break;
            case DIED:
                died.keyPressed(e);
                break;
            case WON:
                won.keyPressed(e);
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) { 
        switch (Gamestate.state) {
            case MENU:
                menu.keyReleased(e);
                break;
            case PLAYING:
                playing.keyReleased(e);
                break;
            case SETTINGS:
                settings.keyReleased(e);;
                break;
            case PAUSED:
                paused.keyReleased(e);
                break;
            case DIED:
                died.keyReleased(e);
                break;
            case WON:
                won.keyReleased(e);
                break;
            default:
                break;
        }
    }
}
