package JumpOrRun.Utils;

import java.awt.Rectangle;

import JumpOrRun.Main.Game;

public class Helpers {
    public static boolean freeSpace(int x, int y, Rectangle hitbox, int[][] level){
        if(freePoint(x, y, level)){
            if(freePoint(x + hitbox.width, y + hitbox.height, level)){
                if(freePoint(x, y + hitbox.height, level)){
                    if(freePoint(x + hitbox.width, y, level)) return true;
                }
            }
        }
        return false;
    }

    private static boolean freePoint(int x, int y, int[][] level){
        if(x < 0 || x >= Game.GameWidth || y < 0 || y >= Game.GameHeight) return false;
        int xIndex = (int) (x / Game.scaledTileSize);
        int yIndex = (int) (y / Game.scaledTileSize);

        int value = level[yIndex][xIndex];
        if (value == 11) return true;

        return false;
    }
}
