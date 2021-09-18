import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class BingoCardViewerFrame extends JFrame{
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 700;
    private int i;
    private int num;
    private static Random random;
    private static ArrayList<Integer> arr;

    public BingoCardViewerFrame(ArrayList<BingoCard> buf, int s, int nw){
        super("Bingo Card Viewer");
        i=0;
        arr = new ArrayList<Integer>();
        random = new Random(s);
        ArrayList<Integer> winCards = new ArrayList<>();

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        setResizable(false);
        setVisible(true);

        JPanel cardViewer = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //changeCard();
                g.drawImage(buf.get(i).getImage(), -75, 0, null);
            }
        };
        add(cardViewer);

        JPanel sideComps = new JPanel();
        sideComps.setSize(750, 750);
        sideComps.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(sideComps);
        setLayout(new GridLayout(0, 2));
        GridLayout compsGrid = new GridLayout(3,0);
        compsGrid.setHgap(15);
        compsGrid.setVgap(45);
        sideComps.setLayout(compsGrid);

        JLabel text1 = new JLabel("Change Card");
        text1.setHorizontalAlignment(SwingConstants.CENTER);
        sideComps.add(text1);

        SpinnerModel m = new SpinnerNumberModel(1, 1, buf.size(), 1);
        JSpinner spin = new JSpinner(m);
        sideComps.add(spin);

        JTextArea ballViewer = new JTextArea();
        ballViewer.setSize(400, 200);
        ballViewer.setLineWrap(true);
        ballViewer.setEditable(false);
        ballViewer.setVisible(true);
        JScrollPane scroll = new JScrollPane(ballViewer);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ballViewer.setSize(new Dimension(500, 300));
        sideComps.add(scroll);

        JTextArea winners = new JTextArea();
        winners.setSize(400, 200);
        winners.setLineWrap(true);
        winners.setEditable(false);
        winners.setVisible(true);
        JScrollPane scroll1 = new JScrollPane(winners);
        scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        winners.setSize(new Dimension(500, 300));
        sideComps.add(scroll1);

        JButton results = new JButton("Show Results");
        results.setSize(100, 20);
        results.setLocation(900, 900);

        JButton button = new JButton("Draw Ball");
        button.setSize(100, 20);
        button.setLocation(900, 900);
        sideComps.add(button);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String s = BingoCardBackend.checkWin();
                Scanner k = new Scanner(s.substring(s.indexOf(": ")+1));
                while(k.hasNext()){winCards.add(Integer.parseInt(k.next()));}
                if(winCards.size() == nw-1){
                    button.setVisible(false);
                    sideComps.add(results);
                    if (!s.isBlank()) {
                        String ss = s.substring(0, s.indexOf(": ") + 4);
                        try {
                            File tempFile = new File("src/data.txt");
                            if (tempFile.exists()) {
                                Files.write(Paths.get("src/data.txt"), ss.getBytes(), StandardOpenOption.APPEND);
                            } else {
                                PrintWriter output = new PrintWriter(new File("src/data.txt"));
                                output.println(ss);
                                output.close();
                            }
                            //PrintWriter output = new PrintWriter(new File("src/data.txt"));
                        } catch (IOException b) {
                        }
                        winners.setText(winners.getText() + ss + "\n");
                    }
                    BingoCardViewerFrame.super.repaint();
                }
                else{
                    if (!s.isBlank()) {
                        String ss = s.substring(0, s.indexOf(": ") + 4);
                        try {
                            File tempFile = new File("src/data.txt");
                            if (tempFile.exists()) {
                                Files.write(Paths.get("src/data.txt"), ss.getBytes(), StandardOpenOption.APPEND);
                            } else {
                                PrintWriter output = new PrintWriter(new File("src/data.txt"));
                                output.println(ss);
                                output.close();
                            }
                            //PrintWriter output = new PrintWriter(new File("src/data.txt"));
                        } catch (IOException b) {
                        }
                        winners.setText(winners.getText() + ss + "\n");
                    }
                    num = getNum();
                    BingoCardBackend.findNum(num);
                    ballViewer.setText(ballViewer.getText() + num + " ");
                    BingoCardViewerFrame.super.repaint();
                }
            }});



        spin.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                i= Integer.parseInt("" + ((JSpinner) e.getSource()).getValue()) - 2;
                if(i == buf.size()-1){i=0;}
                else{i++;}
                BingoCardViewerFrame.super.repaint();
            }
        });
    }

    public static int getNum() {
        int n = random.nextInt(75 - 1) + 1;
        if (arr.contains(n)) {
            n = random.nextInt(75 - 1) + 1;
            if (!arr.contains(n)) {

            }
        }
        arr.add(n);
        return n;
    }
}
