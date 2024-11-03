package JumpOrRun.GUI;

import JumpOrRun.Main.Game;
import JumpOrRun.Gamestates.*;

public class MenuButton extends GenericButton{

    public MenuButton(int x, int y, boolean active, Game game) {
        super(x, y, "button_atlas_2.png", 1, active, game);
    }

    @Override
    public void action() {
        Gamestate.state = Gamestate.MENU;
    }
}
