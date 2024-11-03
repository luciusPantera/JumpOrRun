package JumpOrRun.GUI;

import JumpOrRun.Main.Game;
import JumpOrRun.Gamestates.*;

public class RetryButton extends GenericButton{

    public RetryButton(int x, int y, boolean active, Game game) {
        super(x, y, "button_atlas_2.png", 0, active, game);
    }

    @Override
    public void action() {
        game.getPlaying().restartLevel();
        Gamestate.state = Gamestate.PLAYING;
    }
}
