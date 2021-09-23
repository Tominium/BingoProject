import java.io.IOException;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BFrame extends JFrame {
    private static final int WIDTH = 625;
    private static final int HEIGHT = 380;
    private Font font;

    public BFrame(String title) {
        super(title);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("carbon.ttf")).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        Container win = getContentPane();
        win.setLayout(null);

        JLabel heading = new JLabel("Bingo");
        heading.setSize(200, 60);
        heading.setFont(font);
        heading.setForeground(new Color(255, 216, 102));
        heading.setLocation(260, 50);
        win.add(heading);

        JLabel input = new JLabel("Seed Value");
        input.setSize(100, 20);
        input.setLocation(35, 200);
        win.add(input);
        JTextField inputField = new JTextField("", 10);
        inputField.setSize(100, 20);
        inputField.setLocation(20, 175);
        win.add(inputField);


        JTextField cardNum = new JTextField("", 10);
        cardNum.setSize(100, 20);
        cardNum.setLocation(190, 175);
        win.add(cardNum);
        JLabel cardNumLabel = new JLabel("Number of Cards");
        cardNumLabel.setSize(100, 20);
        cardNumLabel.setLocation(195, 200);
        win.add(cardNumLabel);

        JTextField winNum = new JTextField("", 10);
        winNum.setSize(100, 20);
        winNum.setLocation(340, 175);
        win.add(winNum);
        JLabel winNumLabel = new JLabel("Number of Winners");
        winNumLabel.setSize(150, 20);
        winNumLabel.setLocation(340, 200);
        win.add(winNumLabel);

        JTextField days = new JTextField("", 10);
        days.setSize(100, 20);
        days.setLocation(490, 175);
        win.add(days);
        JLabel days1 = new JLabel("Number of Days");
        days1.setSize(100, 20);
        days1.setLocation(495, 200);
        win.add(days1);

        JButton button = new JButton("Save Cards");
        button.setSize(100, 20);
        button.setLocation(260, 250);
        win.add(button);

        JButton b = new JButton("View Simulation");
        b.setSize(150, 20);
        b.setLocation(235, 280);
        win.add(b);
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(inputField.getText().isBlank() || cardNum.getText().isBlank() || winNum.getText().isBlank() && Integer.parseInt(cardNum.getText()) >= Integer.parseInt(winNum.getText())){
                    try {
                        throw new Exception();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else{new BingoCardBackend(Integer.parseInt(inputField.getText()), Integer.parseInt(cardNum.getText()), Integer.parseInt(winNum.getText()), Integer.parseInt(days.getText()),true);
                    dispose();}
            }
        });

        JButton simu = new JButton("View Simulation");
        simu.setSize(150, 30);
        simu.setLocation(235, 260);
        simu.setVisible(false);
        win.add(simu);
        simu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SimuTable();
            }
        });


        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(inputField.getText().isBlank() || cardNum.getText().isBlank() || winNum.getText().isBlank() && Integer.parseInt(cardNum.getText()) >= Integer.parseInt(winNum.getText())){
                    try {
                        throw new Exception();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else{new BingoCardBackend(Integer.parseInt(inputField.getText()), Integer.parseInt(cardNum.getText()), Integer.parseInt(winNum.getText()), Integer.parseInt(days.getText()), false);
                    button.setVisible(false); b.setVisible(false); simu.setVisible(true);}
            }
        });

        JButton tutorial  = new JButton("How To");
        tutorial.setSize(100, 20);
        tutorial.setLocation(260, 310);
        win.add(tutorial);
        tutorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Tutorial();
            }
        });

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        setResizable(false);
        add(new Panel());
        setVisible(true);
    }
}
