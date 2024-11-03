package JumpOrRun.GUI;

import JumpOrRun.Main.Game;

public class QuitButton extends GenericButton{

    public QuitButton(int x, int y, boolean active, Game game) {
        super(x, y, "button_atlas.png", 2, active, game);
    }

    @Override
    public void action() {
        System.exit(0);
    }
}