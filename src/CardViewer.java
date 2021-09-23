import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
public class CardViewer extends JFrame{
    private static final int WIDTH = 690;
    private static final int HEIGHT = 750;

    public CardViewer(int pos, String[][] data){
        for(int i=0; i<BingoCardBackend.alr.size(); i++){
            BingoCardBackend.findNum(BingoCardBackend.alr.get(i));
        }
        String day = "";
        String round = "";
        for(int r=0; r<data.length; r++){
            for(int c=0; c<data[r].length; c++){
                if(data[r][c].equals(""+pos)){day=data[r][0]; round=data[r][1];}
            }
        }
        String finalDay = day;
        String finalRound = round;
        JPanel p = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //changeCard();
                g.drawImage(BingoCardBackend.cards.get(pos-1).getImage(), -75, 0, null);
                if(!finalDay.isBlank()){
                    try {
                        Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("carbon.ttf")).deriveFont(20f);
                        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                        ge.registerFont(font);
                        g.setFont(font);
                        g.drawString("Day Won: " + finalDay, 550, 180);
                        g.drawString("Round Won: " + finalRound, 550, 230);
                    } catch (IOException | FontFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        add(p);

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        //pack();
    }
}
