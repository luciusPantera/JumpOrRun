package JumpOrRun.Entities;

import java.awt.Rectangle;

import JumpOrRun.Levels.*;

public abstract class FlyingEemy extends Enemy{

    public FlyingEemy(Rectangle hitbox, Rectangle spritebox, Rectangle attackbox, Rectangle viewbox, int animationAmount, int animationLength, String sprite, int maxHp, LevelHandler level) {
        super(hitbox, spritebox, attackbox, viewbox, animationAmount, animationLength, sprite, maxHp, level);
    }
}
