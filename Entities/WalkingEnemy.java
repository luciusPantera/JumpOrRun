package JumpOrRun.Entities;

import JumpOrRun.Levels.*;
import JumpOrRun.Main.Game;
import JumpOrRun.Utils.*;

import java.awt.Rectangle;

public abstract class WalkingEnemy extends Enemy{

    public WalkingEnemy(Rectangle hitbox, Rectangle spritebox, Rectangle attackbox, Rectangle viewbox, int animationAmount, int animationLength, String sprite, int maxHp, LevelHandler level) {
        super(hitbox, spritebox, attackbox, viewbox, animationAmount, animationLength, sprite, maxHp, level);
    }

    protected boolean moving = false, falling = false, attacking = false, jumping = false;
    protected boolean jump = false, stopJump = false, left = false, right = false;
    private int speed = (int)(1 * Game.scale);

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
        if(ded)return;
        if(right) velX += speed;
        if(left) velX -= speed;
        moving = true;
        if(velX < 0) facing = defaultLeft; else if(velX > 0) facing = !defaultLeft;
        tryMoveX((int)velX);
    }

    public void endActions() {
        attacking = false;
        jumping = false;
        attacked = false;
    }

    public boolean canMoveDirection(boolean left){
        int xFloor = hitbox.x;
        int yFloor = hitbox.y + hitbox.height + Constants.GAME.TILE_SIZE / 2;
        if(left){
            xFloor -= Constants.GAME.TILE_SIZE;
        }else{
            xFloor += hitbox.width + Constants.GAME.TILE_SIZE;
        }
        return level.isFloor(xFloor, yFloor);
    }

}
