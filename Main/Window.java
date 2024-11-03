package JumpOrRun.Main;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;


public class Window extends JFrame{

    private int height = 400;
    private int width = 400;

    public Window(GamePanel p){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(p);
        setLocationRelativeTo(null);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
