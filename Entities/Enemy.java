package JumpOrRun.Entities;

import java.awt.Rectangle;

import JumpOrRun.Levels.*;
import JumpOrRun.Main.Game;
import JumpOrRun.Utils.*;

public abstract class Enemy extends Entity{

    protected Player target;
    protected int actionTicks = 0 , actionTick = 0, actionSpeed = 30;

    public Enemy(Rectangle hitbox, Rectangle spritebox, Rectangle attackbox, Rectangle viewbox, int animationAmount, int animationLength, String sprite, int maxHp, LevelHandler level) {
        super(hitbox, spritebox, attackbox, viewbox, animationAmount, animationLength, sprite, maxHp, level);
        facing = true;
    }

    public abstract void updateAI();

    @Override
    public void update(){
        actionTick();
        move();
        setAnimation();
        animationTick();
        updateBoxes();
        if(hitbox.y > (int)(level.height * Constants.GAME.TILE_SIZE  * JumpOrRun.Utils.Config.Game.SCALE))ded = true;
    }

    public void actionTick(){
        actionTicks++;
        if(actionTicks >= actionSpeed){
            actionTick++;
            actionTicks = 0;
            if(actionTick >= getFrameAmount(action)){
                actionTick = 0;
                Object inside = level.checkInside(this);
                if(inside != null){
                    hitbox.y = inside.hitbox.y - hitbox.height;
                    hurt(1);
                    System.out.println("inside something");
                }
                updateAI();
            }
        }
    }
}
