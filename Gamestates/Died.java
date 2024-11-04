package JumpOrRun.Gamestates;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import JumpOrRun.GUI.*;
import JumpOrRun.Main.Game;
import JumpOrRun.Utils.*;

public class Died extends State{

    private BufferedImage background;
    private BufferedImage foregroung;
    private GenericButton[] buttons;

    public Died(Game game) {
        super(game);
        background = Load.GetImage("died_background.png");
        foregroung = Load.GetImage("died_foreground.png");
        buttons = new GenericButton[3];
        int x = (Game.GameWidth/2) - (Constants.GUI.Menu_Button_Width_Scaled / 2);
        buttons[0] = new RetryButton(x, (int)((246 + (0 * 60)) * Game.scale), true, game);
        buttons[1] = new MenuButton(x, (int)((246 + (1 * 60)) * Game.scale), true, game);
        buttons[2] = new QuitButton(x, (int)((246 + (2 * 60)) * Game.scale), true, game);
    }

    @Override
    public void update() {
        for (GenericButton button : buttons) {
            button.update();
        }
    }

    @Override
    public void render(Graphics g) {
        game.getPlaying().render(g);
        int width = (int)(background.getWidth() * Game.scale);
        int height = (int)(background.getHeight() * Game.scale);
        int x = (Game.GameWidth/2) - (width / 2);
        int y = (int)( 126 * Game.scale);
        g.drawImage(background, x, y, width, height, null);
        for (GenericButton button : buttons) {
            button.render(g);
        }
        g.drawImage(foregroung, x, y, width, height, null);
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
                Gamestate.state = Gamestate.MENU;
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
        Rectangle temp;
        for (GenericButton button : buttons) {
            button.setMouseOver(false);
            temp = button.getHitbox();
            if(temp.contains(e.getPoint())) button.setMouseOver(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Rectangle temp;
        for (GenericButton button : buttons) {
            temp = button.getHitbox();
            if(temp.contains(e.getPoint())) button.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Rectangle temp;
        for (GenericButton button : buttons) {
            temp = button.getHitbox();
            if(temp.contains(e.getPoint()) && button.isMousePressed()) button.action();
            button.setMousePressed(false);
            button.setMouseOver(false);
        }
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
