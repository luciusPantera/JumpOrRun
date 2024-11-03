package JumpOrRun.Entities;

import java.awt.*;
import java.awt.image.*;

import JumpOrRun.Levels.*;
import JumpOrRun.Utils.*;
import JumpOrRun.Main.*;

public abstract class Object {
    protected int animationAmount, animationLength;
    protected LevelHandler level;
    public Rectangle hitbox;
    protected BufferedImage[][]animations;
    protected int animationTick = 0, action;

    public Object(Rectangle hitbox, int animationAmount, int animationLength, String sprite, LevelHandler level){
        this.level = level;
        this.animationAmount = animationAmount;
        this.animationLength = animationLength;
        this.hitbox = hitbox;
        importAnimation(sprite);
        setBoxes(hitbox);
    }

    private void importAnimation(String source){
        BufferedImage img = Load.GetImage(source);
        animations = new BufferedImage[animationAmount][animationLength];
        for(int j = 0; j < animations.length; j++){
            for(int i = 0; i < animations[j].length; i++){
                animations[j][i] = img.getSubimage(i*hitbox.width, j*hitbox.height, hitbox.width, hitbox.height);
            }
        }
    }

    public void render(Graphics g){
        int posX = hitbox.x - level.x;
        int posY = hitbox.y - level.y;
            g.drawImage(animations[action][animationTick], posX, posY, hitbox.width, hitbox.height, null);
        if(Config.Debug.showHitbox){
            g.setColor(new Color(255, 0, 0));
            g.drawRect(posX, posY, hitbox.width, hitbox.height);
        }
    }

    public void forceMove(int x, int y){
        hitbox.setLocation(x, y);
    }

    protected void setBoxes(Rectangle hitbox){
        this.hitbox = new Rectangle((int)(hitbox.x * Game.scale), (int)(hitbox.y * Game.scale), (int)(hitbox.width * Game.scale), (int)(hitbox.height * Game.scale));
    }
}