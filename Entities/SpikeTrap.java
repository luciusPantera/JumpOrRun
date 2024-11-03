package JumpOrRun.Entities;

import java.awt.*;
import java.util.*;

import JumpOrRun.Utils.*;
import JumpOrRun.Levels.*;

public class SpikeTrap extends InteractionObject{

    private boolean active = false;

    public SpikeTrap(int x, int y, LevelHandler level){
        super(new Rectangle(x * Constants.GAME.TILE_SIZE - 12,(y+1) * Constants.GAME.TILE_SIZE - 4,56,6), new Rectangle(14,18,82,24), new Rectangle(0,18,56,18), new Rectangle(0,18,56,18), 3, 5, "spike_sprite.png", level);
    }

    @Override
    public int getFrameAmount(int action) {
        switch (action) {
            case 0:
                return 5;
            case 1:
                return 5;
            case 2:
                return 1;
            default:
                return 1;
        }
    }

    @Override
    public void endActions() {
        if(stage1){
            stage2 = true;
            stage1 = false;
        }else{
            stage2 = false;
        }
    }

    @Override
    public void action() {
        ArrayList<Player> players = level.getAllPlayers();
        boolean PlayerInRange = false;
        for (Player player : players) {
            if(player.hitbox.intersects(viewbox)){
                PlayerInRange = true;
            }
        }
        if(PlayerInRange && stage2){
            active = true;
            attack();
        }else if(!PlayerInRange){
            active = false;
        }else if(PlayerInRange && !(stage1 || stage2 || active)){
            stage1 = true;
        }else if(PlayerInRange && active){
            stage2 = true;
            attack();
        }
    }

    public void attack(){
        ArrayList<Player> players = level.getAllPlayers();
        ArrayList<Enemy> enemies = level.getAllEnemies();
        for (Player player : players) {
            if(player.hitbox.intersects(viewbox)){
                player.hurt(10);
            }
        }
        for (Enemy enemy : enemies) {
            if(enemy.hitbox.intersects(viewbox)){
                enemy.hurt(10);
            }
        }
    }

    @Override
    public void setAnimation() {
        if(stage1){
            action = 1;
        }else if(stage2){
            action = 0;
        }else{
            action = 2;
        }
    }
}
