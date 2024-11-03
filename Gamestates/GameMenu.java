package JumpOrRun.Gamestates;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import JumpOrRun.GUI.Button;
import JumpOrRun.Main.Game;
import JumpOrRun.Utils.*;

public class GameMenu extends State{

    private Button[] buttons;
    private BufferedImage background;

    public GameMenu(Game game){
        super(game);
        background = Load.GetImage("menu_background.png");
        buttons = new Button[3];
        for (int i = 0; i < buttons.length; i++) {
            int y = (int)((150 + (i * 70)) * Game.scale);
            buttons[i] = new Button((Game.GameWidth/2) - (Constants.GUI.Menu_Button_Width_Scaled / 2), y, i, true, game);
        }
        buttons[1].active = false;
    }

    public void update() {
        for (Button button : buttons) {
            button.update();
        }
    }

    public void render(Graphics g) {
        int width = (int)(background.getWidth() * Game.scale);
        int height = (int)(background.getHeight() * Game.scale);
        int x = (Game.GameWidth/2) - (width / 2);
        int y = (int)( 45 * Game.scale);
        g.drawImage(background, x, y, width, height, null);
        for (Button button : buttons) {
            button.render(g);
        }
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
        for (Button button : buttons) {
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
        for (Button button : buttons) {
            temp = button.getHitbox();
            if(temp.contains(e.getPoint())) button.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Rectangle temp;
        for (Button button : buttons) {
            temp = button.getHitbox();
            if(temp.contains(e.getPoint()) && button.isMousePressed()) button.performAction();
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
