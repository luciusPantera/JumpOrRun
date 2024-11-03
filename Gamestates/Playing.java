package JumpOrRun.Gamestates;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import JumpOrRun.Entities.*;
import JumpOrRun.Main.*;
import JumpOrRun.Utils.Load;
import JumpOrRun.Levels.*;

public class Playing extends State{

    private Player player;
    public LevelHandler level;
    private Spawn spawn;
    BufferedImage[] health;

    public Playing(Game game){
        super(game);
        level = new LevelHandler(game);
        player = level.getPlayer();
        spawn = level.getSpawn();
        BufferedImage temp = Load.GetImage("health_bar.png");
        health = new BufferedImage[6];
        for (int i = 0; i < health.length; i++) {
            health[i] = temp.getSubimage(0, i * 163, 500, 160);
        }
    }

    public void startLevel(){
        level.startLevel();
        spawn = level.getSpawn();
        player = level.getPlayer();
    }

    public void restartLevel(){
        level.reset();
        spawn = level.getSpawn();
        player = level.getPlayer();
    }

    public void update() {
        level.update();
    }

    public void render(Graphics g) {
        level.render(g);
        float playerHp = player.hp;
        float playerMaxHp = player.maxHp;
        float healthPercent = playerHp / playerMaxHp;
        int healthIndex = -((int)(healthPercent * 5) -5);
        g.drawImage(health[healthIndex], (int)(33 * Game.scale), (int)(33 * Game.scale), (int)(200 * Game.scale), (int)(64 * Game.scale), null);
    }

    public Player getPlayer(){
        return player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setJump(true);;
                break;

            case KeyEvent.VK_A:
                player.setLeft(true);;
                break;

            case KeyEvent.VK_S:
                player.setStopJump(true);;
                break;

            case KeyEvent.VK_D:
                player.setRight(true);;
                break;
            case KeyEvent.VK_ENTER:
                if(!spawn.queued) spawn.queued = true;
                break;
            case KeyEvent.VK_ESCAPE:
                Gamestate.state = Gamestate.PAUSED;
                game.restartLoop();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setJump(false);
                break;
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_S:
                player.setStopJump(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
        
            default:
                break;
        }
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
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            player.attack();
            System.out.println("Clicked at " + (e.getX() / Game.scale) + " | " + (e.getY() / Game.scale));
        }
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

    public LevelHandler getLevel(){
        return level;
    }
    
}
