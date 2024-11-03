package JumpOrRun.GUI;

import java.awt.*;
import java.awt.image.*;

import JumpOrRun.Utils.*;
import JumpOrRun.Main.*;

public abstract class GenericButton {

    private int row;
    private BufferedImage[] img; 
    private int state = 0;
    private boolean mouseOver = false, mousePressed = false;
    private Rectangle hitbox;
    public boolean active;
    protected Game game;

    public GenericButton(int x, int y, String imgSrc, int row, boolean active, Game game){
        this.row = row;
        this.active = active;
        this.game = game;
        hitbox = new Rectangle(x, y, Constants.GUI.Menu_Button_Width_Scaled, Constants.GUI.Menu_Button_Height_Scaled);
        loadImgs(imgSrc);
    }

    public abstract void action();

    private void loadImgs(String imgSrc){
        img = new BufferedImage[3];
        BufferedImage temp = Load.GetImage(imgSrc);
        for(int i = 0; i < img.length; i++){
            img[i] = temp.getSubimage(i * Constants.GUI.Menu_Button_Width, row * Constants.GUI.Menu_Button_Height, Constants.GUI.Menu_Button_Width, Constants.GUI.Menu_Button_Height);
        }
    }

    public void render(Graphics g){
        g.drawImage(img[state], hitbox.x, hitbox.y,Constants.GUI.Menu_Button_Width_Scaled, Constants.GUI.Menu_Button_Height_Scaled, null);
    }

    public void update(){
        state = 0;
        if(mouseOver || !active) state = 1;
        if(mousePressed) state = 2;
    }

    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed){
        this.mousePressed = mousePressed;
    }

    public boolean isMouseOver(){
        return mouseOver;
    }

    public boolean isMousePressed(){
        return mousePressed;
    }

    public Rectangle getHitbox(){
        return hitbox;
    }
}
