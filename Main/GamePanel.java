package JumpOrRun.Main;

import java.awt.*;
import java.awt.image.*;
import java.io.InputStream;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;

import JumpOrRun.Inputs.*;
import static JumpOrRun.Utils.Constants.PlayerConstants.*;

import java.util.*;


public class GamePanel extends JPanel{

    private Mouse mouse;
    private Keyboard keyboard;
    private Game game;

    public GamePanel(Game game){

        this.game = game;
        
        mouse = new Mouse(this);
        keyboard = new Keyboard(this);
        
        addKeyListener(keyboard);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addMouseWheelListener(mouse);

        setPanelSize();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
        //Elements
        //repaint();
    }

    private void setPanelSize(){
        Dimension size = new Dimension(Game.GameWidth, Game.GameHeight + (int)(96 * Game.scale));
        setPreferredSize(size);
    }

    public Game getGame(){
        return game;
    }
}