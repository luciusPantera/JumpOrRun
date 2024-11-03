package JumpOrRun.Entities;

import java.awt.*;
import java.util.*;

import JumpOrRun.Levels.LevelHandler;
import JumpOrRun.Utils.Constants;

public class Mimic extends WalkingEnemy{

    public Mimic(int x, int y, LevelHandler level) {
        super(new Rectangle(x * Constants.GAME.TILE_SIZE, ((y + 1) * Constants.GAME.TILE_SIZE) - 19, 54, 35), new Rectangle(26, 43, 160, 80), new Rectangle(-54,0,40,30), new Rectangle(60,65,174,100), 5, 4, "mimic_sprite.png", 25, level);
        defaultLeft = false;
    }

    @Override
    public void updateAI() {
        if(ded){
            left = false;
            right = false;
            return;
        }
        ArrayList<Player> players = level.getAllPlayers();
        for (Player player : players) {
            if(target == null)target = player;
            if(getDistance(player) < getDistance(target)) target = player;
        }
        if(!target.hitbox.intersects(viewbox)){
            left = false;
            right = false;
            return;
        }
        if(target.hitbox.x < hitbox.x){
            if(canMoveDirection(true)){
                left = true;
                right = false;
            }else{
                left = false;
                right = false;
            }
        }
        if(target.hitbox.x > hitbox.x){
            if(canMoveDirection(false)){
                left = false;
                right = true;
            }else{
                left = false;
                right = false;
            }
        }
        if(attackbox.intersects(target.hitbox)){
            attacking = true;
            target.hurt(5);
        }
    }

    public int getFrameAmount(int action) {
        switch (action) {
            case 0:
                return 4;
            case 1:
                return 3;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 4;
            default:
                return 4;
            }
    }

    public void setAnimation(){
        int startAction = action;
        if(ded){
            action = 1;
        }else if(attacked){
            action = 2;
        }else if(attacking){
            action = 0;
        }else if(moving){
            action = 4;
        }else{
            action = 3;
        }

        if(startAction != action){
            ticks = 0;
            animationTick = 0;
        }
    }
    
}
