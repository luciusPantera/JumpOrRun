package JumpOrRun.Entities;

import java.awt.*;
import java.awt.image.*;

import JumpOrRun.Levels.*;
import JumpOrRun.Utils.*;
import JumpOrRun.Main.*;

public abstract class InteractionObject extends Object{
    protected int xOffsetSprite, yOffsetSprite;
    protected int xOffsetAttack, yOffsetAttack;
    protected int xOffsetView, yOffsetView;
    protected int spriteWidth, spriteHeight;
    public Rectangle spritebox, attackbox, viewbox;
    protected int ticks = 0 , animationSpeed = 30;
    protected int actionTicks = 0 , actionTick = 0, actionSpeed = 30;
    protected boolean stage1 = false, stage2 = false;

    public InteractionObject(Rectangle hitbox, Rectangle spritebox, Rectangle attackbox, Rectangle viewbox, int animationAmount, int animationLength, String sprite, LevelHandler level){
        super(hitbox, animationAmount, animationLength, sprite, level);
        this.spriteWidth = spritebox.width;
        this.spriteHeight = spritebox.height;
        setBoxes(hitbox, spritebox, attackbox, viewbox);
        importAnimation(sprite);
    }

    public abstract int getFrameAmount(int action);
    public abstract void endActions();
    public abstract void action();
    public abstract void setAnimation();

    public void update(){
        actionTick();
        setAnimation();
        animationTick();
        updateBoxes();
    }

    private void importAnimation(String source){
        BufferedImage img = Load.GetImage(source);
        animations = new BufferedImage[animationAmount][animationLength];
        for(int j = 0; j < animations.length; j++){
            for(int i = 0; i < animations[j].length; i++){
                animations[j][i] = img.getSubimage(i*spriteWidth, j*spriteHeight, spriteWidth, spriteHeight);
            }
        }
    }

    public void animationTick(){
        ticks++;
        if(ticks >= animationSpeed){
            animationTick++;
            ticks = 0;
            if(animationTick >= getFrameAmount(action)){
                animationTick = 0;
                endActions();
            }
        }
    }

    public void actionTick(){
        actionTicks++;
        if(actionTicks >= actionSpeed){
            actionTick++;
            actionTicks = 0;
            if(actionTick >= getFrameAmount(action)){
                actionTick = 0;
                action();
            }
        }
    }

    public void render(Graphics g){
        int posX = spritebox.x - level.x;
        int posY = spritebox.y - level.y;
        g.drawImage(animations[action][animationTick], posX, posY, spritebox.width, spritebox.height, null);
        if(Config.Debug.showHitbox){
            g.setColor(new Color(255, 0, 0));
            g.drawRect(hitbox.x - level.x, hitbox.y - level.y, hitbox.width, hitbox.height);
        }if(Config.Debug.showViewbox){
            g.setColor(new Color(0, 255, 0));
            g.drawRect(viewbox.x - level.x, viewbox.y - level.y, viewbox.width, viewbox.height);
        }if(Config.Debug.showAttackbox){
            g.setColor(new Color(0, 0, 255));
            g.drawRect(attackbox.x - level.x, attackbox.y - level.y, attackbox.width, attackbox.height);
        }
        g.setColor(new Color(0, 0, 0));
    }

    protected void setBoxes(Rectangle hitbox, Rectangle spritebox, Rectangle attackbox, Rectangle viewbox){
        this.xOffsetSprite = (int)(spritebox.x *Game.scale);
        this.yOffsetSprite = (int)(spritebox.y*Game.scale);
        this.xOffsetAttack = (int)(attackbox.x*Game.scale);
        this.yOffsetAttack = (int)(attackbox.y*Game.scale);
        this.xOffsetView = (int)(viewbox.x*Game.scale);
        this.yOffsetView = (int)(viewbox.y*Game.scale);
        this.hitbox = new Rectangle((int)(hitbox.x * Game.scale), (int)(hitbox.y * Game.scale), (int)(hitbox.width * Game.scale), (int)(hitbox.height * Game.scale));
        this.spritebox = new Rectangle(0,0,(int)(spritebox.width*Game.scale),(int)(spritebox.height*Game.scale));
        this.attackbox = new Rectangle(0,0,(int)(attackbox.width*Game.scale),(int)(attackbox.height*Game.scale));
        this.viewbox = new Rectangle(0,0,(int)(viewbox.width*Game.scale),(int)(viewbox.height*Game.scale));
    }

    protected void updateBoxes(){
        updateBox(spritebox, xOffsetSprite, yOffsetSprite);
        updateBox(attackbox, xOffsetAttack, yOffsetAttack);
        updateBox(viewbox, xOffsetView, yOffsetView);
        //System.out.println("Atackbox at " + attackbox.x + " | " + attackbox.y + " with Size " + attackbox.width + " | " + attackbox.height);
    } 

    private void updateBox(Rectangle box, int xOffset, int yOffset){
        int hitbox_middle = hitbox.x + (hitbox.width/2);
        int xOffMiddle = xOffset + (hitbox.width/2);
        int x = hitbox_middle + xOffMiddle - box.width;
        int y = hitbox.y - yOffset;
        box.setLocation(x, y);
    }

    public int getDistance(Entity entity){
        if(hitbox.x - entity.hitbox.x + entity.hitbox.width > 0) return hitbox.x - entity.hitbox.x + entity.hitbox.width;
        if(entity.hitbox.x - hitbox.x - hitbox.width > 0) return entity.hitbox.x - hitbox.x - hitbox.width;
        return 0;
    }
}