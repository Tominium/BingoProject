import java.io.IOException;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tutorial extends JFrame{
    private static final int WIDTH = 625;
    private static final int HEIGHT = 380;
    private Font font;

    public Tutorial() {
        super("Bingo Simulation Tutorial");
        Container win = getContentPane();
        win.setLayout(null);



        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("carbon.ttf")).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        setResizable(false);
        add(new Panel());
        setVisible(true);


    }
}
