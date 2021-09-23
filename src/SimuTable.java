import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class SimuTable extends JFrame{

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    public SimuTable(){
        super("Post Simulation Output");

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel table = new JPanel();
        JPanel randomNums = new JPanel();
        JPanel view = new JPanel();
//        table.setLayout(null)
        tabbedPane.addTab("Winners", table);
        tabbedPane.addTab("Drawn Numbers", randomNums);
        tabbedPane.addTab("View Cards", view);

        ArrayList<TableObj> tableObjs = BingoCardBackend.tableObjs;
        ArrayList<Calendar> cal = BingoCardBackend.cal;

        String[][] data = new String[tableObjs.size()][];
        for(int i=0; i<tableObjs.size(); i++){
            data[i] = new String[]{"" + tableObjs.get(i).getDayNum(), "" + tableObjs.get(i).getRoundNum(), "" + tableObjs.get(i).getCardNum()};
        }
        String[] column = {"Day", "Round", "Card"};
        JTable jt=new JTable(data,column);
        jt.setEnabled(false);
        jt.setBounds(30,40,100,300);
        JScrollPane sp=new JScrollPane(jt);
        table.add(sp);

        String[][] data1 = new String[BingoCardBackend.ranPos][];
        int pos=0;

        for(int a=0; a<cal.size();a++){
                for (int b = 0; b < cal.get(a).getR1().size(); b++) {
                    if(pos< data1.length){
                        data1[pos] = new String[]{"" + cal.get(a).getDay(), "" + 1, "" + cal.get(a).getR1().get(b)};
                    }
                    pos++;
                }
            for(int b=0; b<cal.get(a).getR2().size(); b++){
                if(pos<data1.length){
                    data1[pos] = new String[]{"" + cal.get(a).getDay(), "" + 2, "" + cal.get(a).getR2().get(b)};
                }
                pos++;
            }
        }

        String[] column1 = {"Day", "Round", "Number"};
        JTable jt1=new JTable(data1,column1);
        jt1.setEnabled(false);
        jt1.setBounds(30,40,50,300);
        JScrollPane sp1=new JScrollPane(jt1);
        randomNums.add(sp1);

        JTextField cardNum = new JTextField();
        cardNum.setSize(100, 20);
        cardNum.setLocation(200, -200);
        JButton c = new JButton("View Card");
        c.setSize(150, 20);
        c.setLocation(200, -280);
        view.add(cardNum);
        view.add(c);
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CardViewer(Integer.parseInt(cardNum.getText()), data);
            }
        });

        add(tabbedPane);


        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        setResizable(false);
        setVisible(true);
    }

    public SimuTable(ArrayList<TableObj> t){
        super("Post Simulation Output");

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel table = new JPanel();
        JPanel randomNums = new JPanel();
        JPanel view = new JPanel();
//        table.setLayout(null)
        tabbedPane.addTab("Winners", table);
        tabbedPane.addTab("Numbers Drawn", randomNums);
        tabbedPane.addTab("View Cards", view);

        ArrayList<TableObj> tableObjs = t;
        ArrayList<Calendar> cal = BingoCardBackend.cal;

        String[][] data = new String[tableObjs.size()][];
        for(int i=0; i<tableObjs.size(); i++){
            data[i] = new String[]{"" + tableObjs.get(i).getDayNum(), "" + tableObjs.get(i).getRoundNum(), "" + tableObjs.get(i).getCardNum()};
        }
        String[] column = {"Day", "Round", "Card"};
        JTable jt=new JTable(data,column);
        jt.setEnabled(false);
        jt.setBounds(30,40,100,300);
        JScrollPane sp=new JScrollPane(jt);
        table.add(sp);

        String[][] data1 = new String[BingoCardBackend.ranPos][];
        int pos=0;

        for(int a=0; a<cal.size();a++){
            for (int b = 0; b < cal.get(a).getR1().size(); b++) {
                if(pos< data1.length){
                    data1[pos] = new String[]{"" + cal.get(a).getDay(), "" + 1, "" + cal.get(a).getR1().get(b)};
                }
                pos++;
            }
            for(int b=0; b<cal.get(a).getR2().size(); b++){
                if(pos<data1.length){
                    data1[pos] = new String[]{"" + cal.get(a).getDay(), "" + 2, "" + cal.get(a).getR2().get(b)};
                }
                pos++;
            }
        }

        String[] column1 = {"Day", "Round", "Number"};
        JTable jt1=new JTable(data1,column1);
        jt1.setEnabled(false);
        jt1.setBounds(30,40,50,300);
        JScrollPane sp1=new JScrollPane(jt1);
        randomNums.add(sp1);

        JTextField cardNum = new JTextField();
        cardNum.setSize(100, 20);
        cardNum.setLocation(200, -200);
        JButton c = new JButton("View Card");
        c.setSize(150, 20);
        c.setLocation(200, -280);
        view.add(cardNum);
        view.add(c);
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(cardNum.getText()) <= BingoCardBackend.cards.size()){
                    new CardViewer(Integer.parseInt(cardNum.getText()), data);
                }
            }
        });

        add(tabbedPane);


        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        setResizable(false);
        setVisible(true);
    }
}
