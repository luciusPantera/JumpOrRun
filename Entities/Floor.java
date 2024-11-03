package JumpOrRun.Entities;

import java.awt.*;
import java.awt.image.*;

import JumpOrRun.Levels.LevelHandler;
import JumpOrRun.Utils.*;

public class Floor extends Object{

    public Floor(int x, int y,int kind, LevelHandler level) {
        super(new Rectangle(x * Constants.GAME.TILE_SIZE, y * Constants.GAME.TILE_SIZE, 32, 32), 4, 12, "outside_sprites.png", level);
        changeAnimation();
        action = kind;
    }

    public void changeAnimation(){
        BufferedImage[][] temp = new BufferedImage[48][1];
        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 12; i++){
                int index = 12*j + i;
                temp[index][0] = animations[j][i];
            }
        }
        animations = temp;
    }
}
