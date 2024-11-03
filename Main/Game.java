package JumpOrRun.Main;

import java.awt.*;

import JumpOrRun.Levels.*;
import JumpOrRun.Utils.*;
import JumpOrRun.Entities.*;
import JumpOrRun.Gamestates.*;

public class Game implements Runnable{

    public final static int tileSize = 32;
    public final static float scale = 1.5f;
    public final static int scaledTileSize = (int) (tileSize * scale);
    public static final int fpsSet = 120, upsSet = 200;
    public static int widthInTiles;
    public static int heightInTiles;
    public static int GameWidth;
    public static int GameHeight;

    private GamePanel panel;
    private Window window;
    private Thread gameThread;
    private int frames = 0, updates = 0;
    private long lastSecond;
    private Playing playing;
    private GameMenu menu;
    private Settings settings;
    private Paused paused;
    private Won won;
    private Died died;

    public Game(){
        setGameSize(26, 14);
        playing = new Playing(this);
        menu = new GameMenu(this);
        settings = new Settings(this);
        paused = new Paused(this);
        won = new Won(this);
        died = new Died(this);
        panel = new GamePanel(this);
        window = new Window(panel);
        panel.requestFocus();

        startLoop();
    }

    public void startLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void restartLoop(){
        gameThread.start();
    }

    public void run(){

        double tpf = 1000000000 / fpsSet;
        double tpu = 1000000000 / upsSet;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();

        while(true){

            if(System.nanoTime()-lastFrame >= tpf){
                panel.repaint();
                lastFrame += tpf;
                frames++;
            }

            if(System.nanoTime()-lastUpdate >= tpu){
                update();
                lastUpdate += tpu;
                updates++;
            }
            
            if(System.currentTimeMillis()-lastSecond >= 1000){
                lastSecond = System.currentTimeMillis();
                if(Config.Debug.showFPS) System.out.println("Current FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

    }

    public void update(){
        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case SETTINGS:
                settings.update();
                break;
            case PAUSED:
                paused.update();
                break;
            case DIED:
                died.update();
                break;
            case WON:
                won.update();
                break;
            default:
                break;
        }
        
    }

    public void render(Graphics g){
        switch (Gamestate.state){
            case MENU:
                menu.render(g);
                break;
            case PLAYING:
                playing.render(g);
                break;
            case SETTINGS:
                settings.render(g);
                break;
            case PAUSED:
                playing.render(g);
                paused.render(g);
                break;
            case DIED:
                died.render(g);
                break;
            case WON:
                won.render(g);
                break;
            default:
                break;
        }
        
    }

    public void setGameSize(int width, int height){
        widthInTiles = width;
        heightInTiles = height;
        GameWidth = widthInTiles * scaledTileSize;
        GameHeight = heightInTiles * scaledTileSize;
    }

    public Player getPlayer(){
        return playing.getPlayer();
    }

    public GameMenu getMenu(){
        return menu;
    }

    public Playing getPlaying(){
        return playing;
    }

    public Settings getSettings(){
        return settings;
    }

    public Paused getPaused(){
        return paused;
    }

    public Died getDied(){
        return died;
    }

    public Won getWon(){
        return won;
    }

}
