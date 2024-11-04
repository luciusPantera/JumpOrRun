package JumpOrRun.Entities;

import static SuperMayoWorld.Utils.Constants.PlayerConstants.*;

import java.awt.Rectangle;
import java.io.ObjectInputFilter.Config;
import java.util.ArrayList;

import JumpOrRun.Gamestates.Gamestate;
import JumpOrRun.Levels.*;
import JumpOrRun.Main.Game;
import JumpOrRun.Utils.Constants;

public class Player extends Entity{

    protected boolean moving = false, falling = false, attacking = false, jumping = false, jump = false, stopJump = false, left = false, right = false;
    private int speed = (int)(1 * Game.scale);
    private static final int leftSpace = 200, rightSpace = 200, topSpace = 50, bottomSpace = 50;
    private int xOnScreen, yOnScreen;

    public Player(int x, int y, LevelHandler level) {
        super(new Rectangle(x * Constants.GAME.TILE_SIZE, ((y + 1) * Constants.GAME.TILE_SIZE)-28, 16, 28), new Rectangle(25, 4, 64, 40), new Rectangle(30,-10,30,30), new Rectangle(0,0,12,12), 9, 6, "player_sprites.png", 50, level);
    }

    public void move(){
        // if it can move down its in the Air
        inAir = false;
        if(canMove(0, 1)) inAir = true;
        moving = false;
        falling = false;
        velX = 0;
        if(stopJump){
            jumping = false;
            if(velY < 0) velY = 2;
        }
        // no Inputs so no movement
        if(!jump && !inAir && !left && !right)return;
        // only jump on ground
        if(jump && !inAir){
            jumping = true;
            velY = (int)(-2.25f * Game.scale); 
            if(!tryMoveY((int)velY)) velY = 0;
        // if its in the air and not jumping its falling
        }else if(inAir && !jumping)falling = true;
        // gravity
        if(inAir){
            velY += gravity;
            if(velY < 0.8 && velY > 0)velY = 1; 
            // bewegt y, wenn es nicht bewegen kann ist velY 0
            if(!tryMoveY((int)velY)) velY = 0;
        }
        // side movement
        if(right) velX += speed;
        if(left) velX -= speed;
        moving = true;
        if(velX < 0) facing = true; else if(velX > 0) facing = false;
        tryMoveX((int)velX);

        // move level to fit
        int Levelx_temp = level.x;
        xOnScreen = hitbox.x - Levelx_temp;
        int xMiddle = (int)(Game.GameWidth) / 2;
        boolean offLeft = xOnScreen < xMiddle - leftSpace;
        boolean offRight = xOnScreen > xMiddle + rightSpace;
        if(offLeft) Levelx_temp = hitbox.x - xMiddle + leftSpace;
        if(offRight) Levelx_temp = hitbox.x - xMiddle - rightSpace;
        boolean atEnd = Levelx_temp >= (int)(level.length * Constants.GAME.TILE_SIZE  * JumpOrRun.Utils.Config.Game.SCALE) - Game.GameWidth; 
        boolean atStart = Levelx_temp <= 0;
        if(atEnd) Levelx_temp = (int)(level.length * Constants.GAME.TILE_SIZE * JumpOrRun.Utils.Config.Game.SCALE) - Game.GameWidth;
        if(atStart) Levelx_temp = 0;
        level.x = Levelx_temp;
        // y
        int Levely_temp = level.y;
        yOnScreen = hitbox.y - Levely_temp;
        int yMiddle = (int)(Game.GameHeight) / 2;
        boolean offTop = yOnScreen < yMiddle - topSpace;
        boolean offBottom = yOnScreen > yMiddle + bottomSpace;
        if(offTop) Levely_temp = hitbox.y - yMiddle + topSpace;
        if(offBottom) Levely_temp = hitbox.y - yMiddle - bottomSpace;
        boolean atBottom = Levely_temp >= (int)(level.height * Constants.GAME.TILE_SIZE  * JumpOrRun.Utils.Config.Game.SCALE) - Game.GameHeight; 
        boolean atTop = Levely_temp <= 0;
        if(atBottom) Levely_temp = (int)(level.height * Constants.GAME.TILE_SIZE * JumpOrRun.Utils.Config.Game.SCALE) - Game.GameHeight;
        if(atTop) Levely_temp = 0;
        level.y = Levely_temp;
    }

    public void setAnimation(){
        int startAction = action;
        if(ded){
            Gamestate.state = Gamestate.DIED;
        }
        if(attacked){
            action = 5;
        }else if(attacking){
            /* if(action != ATTACK){
                attack();
            } */
            action = 6;
            if(velY < 0){
                action = 8;
                jumping = false;
            }else if(velY > 0){
                action = 7;
            }
        }else if(jumping){
            action = 2;
        }else if(falling){
            action = 3;
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

    public int getFrameAmount(int action) {
        return Constants.PlayerConstants.GetFrameAmount(action);
    }

    public void endActions() {
        attacking = false;
        jumping = false;
        attacked = false;
    }

    public void attack(){
        Enemy target = null;
        ArrayList<Enemy> enemies = level.getAllEnemies();
        attacking = true;
        for(Enemy enemy: enemies){
            if(target == null) target = enemy;
            if(getDistance(enemy) < getDistance(target)) target = enemy;
        }
        if(attackbox.intersects(target.hitbox)){
            target.hurt(5);
        }
    }

    public void setLeft(boolean left){
        this.left = left;
    }

    public void setRight(boolean right){
        this.right = right;
    }

    public void setJump(boolean jump){
        this.jump = jump;
    }

    public void setStopJump(boolean stopJump){
        this.stopJump = stopJump;
    }

}
