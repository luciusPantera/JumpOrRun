package JumpOrRun.Utils;

import JumpOrRun.Main.Game;

public class Constants {

    public static class GAME {
        public static final int TILE_SIZE = 32;
    }

    public static class GUI {
        public static final int Menu_Button_Width = 140;
        public static final int Menu_Button_Height = 56;
        public static final int Menu_Button_Width_Scaled = (int)(140 * Game.scale);
        public static final int Menu_Button_Height_Scaled = (int)(56 * Game.scale);
    }
    
    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMPING = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK = 6;
        public static final int ATTACK_DOWN = 7;
        public static final int ATTACK_UP = 8;

        public static int GetFrameAmount(int action){
            switch (action) {
                case 0:
                    return 5;
                case 1:
                    return 6;
                case 2:
                    return 3;
                case 3:
                    return 1;
                case 4:
                    return 2;
                case 5:
                    return 4;
                case 6:
                    return 3;
                case 7:
                    return 3;
                case 8:
                    return 3;
            
                default:
                    return 1;
            }
        }
    }
}
