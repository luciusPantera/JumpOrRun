package JumpOrRun.Entities;

import java.awt.*;
import java.util.*;

import JumpOrRun.Utils.*;
import JumpOrRun.Levels.*;
import JumpOrRun.Main.Game;

public class Spawn extends InteractionObject{

    public boolean queued = false;

    public Spawn(int x, int y, LevelHandler level){
        super(new Rectangle(x * Constants.GAME.TILE_SIZE - 12,(y+1) * Constants.GAME.TILE_SIZE - 4,56,6), new Rectangle(14,18,82,24), new Rectangle(0,0,56,0), new Rectangle(0,0,56,0), 3, 5, "spawn_sprite.png", level);
    }

    @Override
    public int getFrameAmount(int action) {
        switch (action) {
            case 0:
                return 5;
            case 1:
                return 5;
            case 2:
                return 1;
            default:
                return 1;
        }
    }

    @Override
    public void endActions() {
        if(stage1){
            stage2 = true;
            stage1 = false;
        }else{
            stage2 = false;
        }
    }

    @Override
    public void action() {
        if(queued && !(stage1 || stage2)){
            stage1 = true;
        }else if(stage2){
            teleportPlayer();
            queued = false;
        }
    }

    public void teleportPlayer(){
        int posX = hitbox.x + (int)(20 * Game.scale);
        int posY = hitbox.y - (int)(28 * Game.scale);
        ArrayList<Player> players = level.getAllPlayers();
        for (Player player : players) {
            player.forceMove(posX, posY);
        }
        level.x = 0;
        level.y = (int)(level.height * Constants.GAME.TILE_SIZE * JumpOrRun.Utils.Config.Game.SCALE) - Game.GameHeight;
    }

    @Override
    public void setAnimation() {
        if(stage1){
            action = 1;
        }else if(stage2){
            action = 0;
        }else{
            action = 2;
        }
    }
    
}
