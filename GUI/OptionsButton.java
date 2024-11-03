package JumpOrRun.GUI;

import JumpOrRun.Main.Game;
import JumpOrRun.Gamestates.*;

public class OptionsButton extends GenericButton{

    public OptionsButton(int x, int y, boolean active, Game game) {
        super(x, y, "button_atlas.png", 1, active, game);
    }

    @Override
    public void action() {
        Gamestate.state = Gamestate.SETTINGS;
    }
}
