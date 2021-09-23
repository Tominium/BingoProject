import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class BingoCardViewerFrame extends JFrame{
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 700;
    private int i;

    public BingoCardViewerFrame(ArrayList<BingoCard> buf, int s, int nw){
        super("Bingo Card Viewer");
        i=0;
        ArrayList<Recorder> rec = new ArrayList<>();

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

        JButton results = new JButton("View Results");
        results.setSize(100, 20);
        results.setLocation(900, 900);
        results.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BingoCardBackend.simulator(rec, nw);
            }
        });

        JButton button = new JButton("Draw Ball");
        button.setSize(100, 20);
        button.setLocation(900, 900);
        sideComps.add(button);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(rec.size() < nw){
                    int n = BingoCardBackend.getNum();
                    BingoCardBackend.findNum(n);
                    ballViewer.setText(ballViewer.getText() + n + " ");
                    String s = BingoCardBackend.checkWin(n);
                    Scanner k = new Scanner(s.substring(s.indexOf(" ") + 1));
                    if (!s.isBlank()) {
                        while(k.hasNext()){
                            int id = k.nextInt();
                            winners.setText(winners.getText() + "Card: " + id + "\n\n");
                            rec.add(new Recorder(n, id));
                        }
                    }
                }
                else{
                    button.setVisible(false);
                    sideComps.add(results);
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

}
