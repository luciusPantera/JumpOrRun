package JumpOrRun.Levels;

import JumpOrRun.Main.*;
import JumpOrRun.Utils.*;
import JumpOrRun.Entities.*;
import JumpOrRun.Entities.Object;
import JumpOrRun.Gamestates.Gamestate;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class LevelHandler {

    Game game;
    BufferedImage[] levelSprite;
    private int[][][] levelData;
    private ArrayList<Player> players;
    private ArrayList<Enemy> enemies;
    private ArrayList<Floor> floor;
    private ArrayList<InteractionObject> interactionObjects;
    private Spawn spawn;
    private int waitedTicks;
    private float waitSec = 1;
    private int levelNr = 1;
    public int x = 0, y = 0;
    public int length, height;
    private int enemiesLeft;

    public LevelHandler(Game game){
        this.game = game;
        levelNr = Load.getPlayerdata()[0];
        startLevel();
        //players.add(new Player(100, 100, this));
        //enemies.add(new Crabby(400, 300, this));
        //enemies.add(new Crabby(770, 400, this));
    }

    public void importSprites(){
        BufferedImage img = Load.GetImage("outside_sprites.png");
        levelSprite = new BufferedImage[48];
        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 12; i++){
                int index = 12*j + i;
                levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
    }

    public void render(Graphics g){
        for (Floor floor : floor) {
            floor.render(g);
        }
        for (InteractionObject interactionObject : interactionObjects) {
            interactionObject.render(g);
        }
        for (Enemy enemy : enemies) {
            enemy.render(g);
        }
        for (Player player : players) {
            player.render(g);
        }
    }

    public void update(){
        /* boolean won = true;
        enemiesLeft = 0;
        for (Enemy enemy : enemies) {
            if(!enemy.dead){
                enemiesLeft ++;
                won = false;
            }
        }
        if(won){
            waitedTicks ++;
        }
        if(won && waitedTicks >= (int)(Game.upsSet * waitSec)){
            levelNr ++;
            Load.saveData(levelNr);
            Gamestate.state = Gamestate.WON;
        } */
        for (InteractionObject interactionObject : interactionObjects) {
            interactionObject.update();
        }
        for (Enemy enemy : enemies) {
            enemy.update();
        }
        for (Player player : players) {
            player.update();
        }
    }

    public void win(){
        levelNr ++;
        Load.saveData(levelNr);
        Gamestate.state = Gamestate.WON;
    }

    public void getLevel(int nr){
        int[][][] data = null;
        try {
            data = Load.getLevel(nr);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return;
        }
        levelData = data;
    }

    public void loadLevel(){
        length = levelData[0][0].length;
        height = levelData[0].length;
        for (int j = 0; j < levelData[0].length; j++){
			for (int i = 0; i < levelData[0][0].length; i++) {
                 //Floor
                int value = levelData[0][j][i];
                if(value != 11){
				    Floor floorNew = new Floor(i, j, value, this);
                    floor.add(floorNew);
                }
                //Enemies
                value = levelData[1][j][i];
                if(value >= 2 && value <= 4){
				    Enemy enemyNew = null;
                    switch (value) {
                        case 2:
                            enemyNew = new Crabby(i, j, this);
                            break;
                        case 3:
                            enemyNew = new Mimic(i, j, this);
                            break;
                        case 4:
                            //enemyNew = new Enemy3();
                            break;
                        default:
                            break;
                    }
                    enemies.add(enemyNew);
                }
                //Interaction Objects
                value = levelData[2][j][i];
                InteractionObject object = null;
                switch (value) {
                    case 1:
                        spawn = new Spawn(i, j, this);
                        object = spawn;
                        players.add(new Player(i, j, this));
                        spawn.teleportPlayer();
                        break;
                    case 2:
                        object = new Goal(i, j, this);
                        break;
                    case 3:
                        object = new SpikeTrap(i, j, this);
                        break;
                    default:
                        break;
                }
                if(object != null)interactionObjects.add(object);
			}
        }
    }

    public boolean isFree(Rectangle hitbox, Entity me){
        for (Floor floor : floor) {
            if(floor.hitbox.intersects(hitbox)) return false;
        }
        for (Player player : players) {
            if(player == me) break;
            if(player.hitbox.intersects(hitbox)) return false;
        }
        for (Enemy enemy : enemies) {
            if(enemy == me) break;
            if(enemy.hitbox.intersects(hitbox)) return false;
        }
        for (InteractionObject interactionObject : interactionObjects) {
            if(interactionObject.hitbox.intersects(hitbox)) return false;
        }
        return true;
    }

    public Object checkInside(Entity me){
        for (Floor floor : floor) {
            if(floor.hitbox.intersects(me.hitbox)) return floor;
        }
        for (Player player : players) {
            if(player == me) break;
            if(player.hitbox.intersects(me.hitbox)) return player;
        }
        for (Enemy enemy : enemies) {
            if(enemy == me) break;
            if(enemy.hitbox.intersects(me.hitbox)) return enemy;
        }
        for (InteractionObject interactionObject : interactionObjects) {
            if(interactionObject.hitbox.intersects(me.hitbox)) return interactionObject;
        }
        return null;
    }

    public Player getPlayer(){
        return players.get(0);
    }

    public ArrayList<Player> getAllPlayers(){
        return players;
    }

    public ArrayList<Enemy> getAllEnemies(){
        return enemies;
    }

    public Spawn getSpawn(){
        return spawn;
    }

    public boolean isFloor(int x, int y){
        for (Floor floor : floor) {
            if(floor.hitbox.contains(x, y)) return true;
        }
        return false;
    }

    public void reset(){
        players = new ArrayList<>();
        enemies = new ArrayList<>();
        floor = new ArrayList<>();
        interactionObjects = new ArrayList<>();
        loadLevel();
    }

    public void startLevel(){
        getLevel(levelNr);
        reset();
    }
}
