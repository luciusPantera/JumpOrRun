package JumpOrRun.Entities;

import java.awt.*;
import java.util.*;

import JumpOrRun.Levels.LevelHandler;
import JumpOrRun.Utils.Constants;

public class Crabby extends WalkingEnemy{

    public Crabby(int x, int y, LevelHandler level) {
        super(new Rectangle(x * Constants.GAME.TILE_SIZE, ((y + 1) * Constants.GAME.TILE_SIZE) - 19, 22, 19), new Rectangle(24, 11, 72, 32), new Rectangle(15,20,52,30), new Rectangle(60,60,144,60), 5, 9, "crabby_sprite.png", 10, level);
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
                return 9;
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 4;
            case 4:
                return 5;
            default:
                return 1;
            }
    }

    public void setAnimation(){
        int startAction = action;
        if(ded){
            action = 4;
        }else if(attacked){
            action = 3;
        }else if(attacking){
            action = 2;
        }else if(moving){
            action = 1;
        }else{
            action = 0;
        }

        if(startAction != action){
            ticks = 0;
            animationTick = 0;
        }
    }
    
}
