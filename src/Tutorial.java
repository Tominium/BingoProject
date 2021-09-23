import java.io.IOException;
import java.util.Random;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;

public class Tutorial extends JFrame{
    private static final int WIDTH = 650;
    private static final int HEIGHT = 700;
    private Font font;

    public Tutorial() {
        super("Bingo Simulation Tutorial");
        Container win = getContentPane();
        win.setLayout(null);

        try {
            font = Font.createFont(Font.PLAIN, getClass().getClassLoader().getResourceAsStream("carbon.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        JTextPane l = new JTextPane();
        l.setText("Main Menu\n\n"+"The seed value is the number that will be used as a basis for creating cards and drawing numbers, so the same seed value will produce the same result each time. The number of cards box is for the number of cards you want to create. The number of winners box is for the number of winners you want for the game. The number of days is how many days you want the simulation to last. This number will determine how many numbers are drawn to find a set number of winners. Note: Make sure all boxes have numbers in them before clicking any other button.\n\n" + "Save Cards Button\n\n" + "Note: After clicking this button please wait one minute for the bingo cards to be created. This button will save the bingo cards as PNG files in a \"Bingo Cards\" folder. This folder will be stored in the same location as this application. When this button is clicked, a window will open that will show the location of the bingo cards. After clicking this button a \"View Results\" button will appear. Also, a \"printSim.txt\" file will be created and saved to the Bingo Cards folder which will show which cards won in the simulation on each round of each day and will show what numbers are drawn on each round of each day.\n\n" +
                "View Simulation Button\n\n" + "When this button is clicked the bingo cards will be saved to the \"Bingo Cards\" folder and a window will open that displays the cards, a number iterator/spinner, two boxes, and a \"Draw Ball\" button. The number iterator/spinner can be used to view different cards. The box on the left will display the numbers that have been drawn and the box on the right will display the ID of the cards that have won. This ID can be seen in the top-right corner of each bingo card. The \"Draw Number\" button, when clicked, will draw an number and mark it on the bingo cards. Once all the winners have been found, this button will disappear and a \"View Results\" button will appear. \n\n" + "View Results Button \n\n" + "Once clicked, a new window will open with several tabs. The first tab, \"Winners\", will display which cards won in the simulation, in a table organized by day, round, and card ID. The second tab \"Number Drawn\" will display a table that shows what numbers are called, organized by day and round. The third tab \"View Cards\" has an input box, into which a number can be typed. This number must be within the range of 1 to how ever many cards have been created. Once a number has been typed, click the \"View Card\" button  which will show the card, marked with yellow circles, and will show if the card won and on what day and round it won.");

        l.setFont(font);
        l.setEditable(false);
        l.setSize(620, 690);
        StyledDocument doc = l.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        win.add(l);


        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        add(new Panel());
        setVisible(true);


    }
}
