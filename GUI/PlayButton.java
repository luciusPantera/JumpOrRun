package JumpOrRun.GUI;

import JumpOrRun.Main.Game;
import JumpOrRun.Gamestates.*;

public class PlayButton extends GenericButton{

    public PlayButton(int x, int y, boolean active, Game game) {
        super(x, y, "button_atlas.png", 0, active, game);
        System.out.println("Playbutton erstellt");
    }

    @Override
    public void action() {
        game.getPlaying().startLevel();
        Gamestate.state = Gamestate.PLAYING;
        game.restartLoop();
    }
}
