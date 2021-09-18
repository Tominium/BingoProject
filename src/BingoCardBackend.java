import java.awt.image.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class BingoCardBackend {
    private static ArrayList<BingoCard> cards;
    private int cardNum;
    private static int numWins;
    private boolean view;
    private static int seed;
    private int numDays;
    public static ArrayList<Integer> alr;
    public static ArrayList<Integer> ranArr;
    public static Random run;

    public BingoCardBackend(int s, int cn, int wn, int nd, boolean b){
        numDays = nd;
        seed = s;
        cardNum = cn;
        numWins = wn;
        view = b;
        cards = new ArrayList<>();
        createCards();
    }

    public void createCards(){
        for(int i=0; i<cardNum;i++){
            BingoCard b = new BingoCard(seed, i+1);
            cards.add(b);
        }
        if(view){PDF(); new BingoCardViewerFrame(cards, seed, numWins);}
        else{printSimulator();}
    }

    private void PDF(){
        int complete = cardNum/4;
        new File("BingoCards").mkdirs();
        try{
            int pos=0;
            for(int i=0; i<complete; i++){
                BufferedImage image = new BufferedImage(2048, 1536, BufferedImage.TYPE_INT_ARGB);
                Graphics g = image.getGraphics();
                for(int r=0; r<= 700&&pos<cards.size(); r+=700){
                    for(int c=0; c<=700; c+=700 ) {
                        g.drawImage(cards.get(pos).getImage(), c, r, null);
                        pos++;
                    }
                }
                ImageIO.write(image, "png", new File("BingoCards/"+(i+1)+ ".png"));
            }
            if(complete*4!=cardNum){
                BufferedImage image = new BufferedImage(2550, 3300, BufferedImage.TYPE_INT_ARGB);
                Graphics g = image.getGraphics();
                for (int r = 0; r <= 700; r += 700) {
                    for (int c = 0; c <= 700&&pos<cards.size(); c += 700) {
                        g.drawImage(cards.get(pos).getImage(), c, r, null);
                        pos++;
                    }
                }
                ImageIO.write(image, "png", new File("BingoCards/" + (complete + 1) + ".png"));
            }
        }
        catch(Exception e){e.printStackTrace();}
    }

    public static void findNum(int ran){
        int pos=0;
        for(int i=0; i<cards.size(); i++){
            if(!cards.get(i).getWin()&&cards.get(i).getString().indexOf("" + ran) != -1){
                pos=i;
                int[][] temp = cards.get(pos).getArr();
                for(int r=0; r<temp.length; r++) {
                    ArrayList<Integer> temp1 = new ArrayList<>();
                    for(int a: temp[r]){temp1.add(a);}
                    if (temp1.contains(ran)) {
                        cards.get(i).zero(r, temp1.indexOf(ran));
                        markNum(r, temp1.indexOf(ran), pos);
                    }
                }
            }
        }
    }
    public static void markNum(int r, int c, int pos){
        BufferedImage b = cards.get(pos).getImage();
        Graphics g = b.getGraphics();
        //System.out.println("R: " + r + " C: " + c);
        r++; c++;
        r=100*r;
        c=100*c;
        g.setColor(new Color(255, 216, 102, 125));
        g.fillOval(c+25, r+25, 50, 50);
        cards.get(pos).changeImage(b);
    }

    public static String checkWin(){
        String s = "";
        if(alr==null) {
            alr = new ArrayList<Integer>();
        }
        for(int i=0; i<cards.size(); i++){
            int[][] temp = cards.get(i).getArr();
            for(int r=0; r<temp.length&&alr.size() < numWins&&!cards.get(i).getWin(); r++){
                int b = i+1;
                if(temp[r][0]+temp[r][1]+temp[r][2]+temp[r][3]+temp[r][4]==0){s += b+ " ";alr.add(i);cards.get(i).setWin();}
            }
            for(int c=0; c<5&&alr.size() < numWins&&!cards.get(i).getWin(); c++){
                int b = i+1;
                if(temp[0][c]+temp[1][c]+temp[2][c]+temp[3][c]+temp[4][c]==0){if(!alr.contains(i)){s += b+ " ";alr.add(i);cards.get(i).setWin();}}
            }
            int b = i+1;
            if(temp[0][0]+temp[1][1]+temp[2][2]+temp[3][3]+temp[4][4]==0&&alr.size() < numWins&&!cards.get(i).getWin()){if(!alr.contains(i)){s += b+ " ";alr.add(i);cards.get(i).setWin();}}
            if(temp[4][0]+temp[3][1]+temp[2][2]+temp[1][3]+temp[0][4]==0&&alr.size() < numWins&&!cards.get(i).getWin()){if(!alr.contains(i)){s += b+ " ";alr.add(i);cards.get(i).setWin();}}
        }
        if(!s.isEmpty()){s = " \n Winning Cards: " + s + "\n";}
        return s;
    }

    public static String checkWin(int a){
        String s = "";
        if(alr==null) {
            alr = new ArrayList<Integer>();
        }
        for(int i=0; i<cards.size(); i++){
            int[][] temp = cards.get(i).getArr();
            for(int r=0; r<temp.length&&alr.size() < numWins&&!cards.get(i).getWin(); r++){
                int b = i+1;
                if(temp[r][0]+temp[r][1]+temp[r][2]+temp[r][3]+temp[r][4]==0){s += b+ " ";alr.add(i);cards.get(i).setWin();}
            }
            for(int c=0; c<5&&alr.size() < numWins&&!cards.get(i).getWin(); c++){
                int b = i+1;
                if(temp[0][c]+temp[1][c]+temp[2][c]+temp[3][c]+temp[4][c]==0){if(!alr.contains(i)){s += b+ " ";alr.add(i);cards.get(i).setWin();}}
            }
            int b = i+1;
            if(temp[0][0]+temp[1][1]+temp[2][2]+temp[3][3]+temp[4][4]==0&&alr.size() < numWins&&!cards.get(i).getWin()){if(!alr.contains(i)){s += b+ " ";alr.add(i);cards.get(i).setWin();}}
            if(temp[4][0]+temp[3][1]+temp[2][2]+temp[1][3]+temp[0][4]==0&&alr.size() < numWins&&!cards.get(i).getWin()){if(!alr.contains(i)){s += b+ " ";alr.add(i);cards.get(i).setWin();}}
        }
        if(!s.isEmpty()){s = a + " " + s;}
        return s;
    }

    public static int getNum(){
        if(ranArr==null){ranArr = new ArrayList<>();}
        if(run==null){run = new Random(seed);}
        int n = run.nextInt(75-1)+1;
        if(ranArr.contains(n)){getNum();}
       return n;
    }


    public void printSimulator(){
        ArrayList<Recorder> winCards = new ArrayList<>();
        ArrayList<Calendar> cal = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        Random ran = new Random(seed);
        while(winCards.size()<numWins){
            int n = getNum();
            arr.add(n);
            System.out.println("Random Num: " + arr.get(arr.size()-1));
            findNum(arr.get(arr.size()-1));
            String s = BingoCardBackend.checkWin(arr.size()-1);
            Scanner k = new Scanner(s.substring(s.indexOf(" ") + 1));
            while (k.hasNext() && winCards.size()<numWins) {
                //System.out.println("winCards.add");
                winCards.add(new Recorder(Integer.parseInt(s.substring(0, s.indexOf(" "))),Integer.parseInt(k.next())));
                System.out.println(winCards.get(winCards.size()-1));
            }
        }
        ArrayList<Integer> bPerR = new ArrayList<>(); //int[arr.size()/(numDays*2)];
        for(int a=0; a<numDays*2; a++){bPerR.add(arr.size()/(numDays*2));}
        if(arr.size()%(numDays*2) != 0){
            int i=0;
            int extra = arr.size()%(numDays*2);
            while(extra>0){
                bPerR.set(i, bPerR.get(i)+1);
                //System.out.println(bPerR.get(i));
                extra--;
                if(i==bPerR.size()-1){i=0;}
                else{i++;}
            }
        }
            for(int ii=0; ii<numDays; ii+=2){
                ArrayList<Integer> temp = new ArrayList<>();
                ArrayList<Integer> temp1 = new ArrayList<>();
                ArrayList<Integer> temp2 = arr;
                for(int b=0; b<bPerR.get(ii); b++){
                    temp.add(temp2.remove(0));
                    System.out.println(temp.get(temp.size()-1));
                }
                for(int b=0; b<bPerR.get(ii+1); b++){
                    temp1.add(temp2.remove(0));
                    System.out.println(temp1.get(temp1.size()-1));
                }
                System.out.println("Calendar For Loop");
                cal.add(new Calendar(ii+1, temp, temp1));
            }
            String finalFile = "Winners\n" ;
            for(int a=0; a<winCards.size(); a++){
                System.out.println("First For Loop");
                int rn = winCards.get(a).getRanNum();
                int id = winCards.get(a).getCardId();
                for(int b=0; b<cal.size(); b++){
                    if(cal.get(b).getR1().contains(rn)){System.out.println("R1");finalFile += ("Day: " + cal.get(b).getDay() + " Round 1 "+ " Card: #" + id + "\n");}
                    else if(cal.get(b).getR2().contains(rn)){System.out.println("R2");finalFile += ("Day: " + cal.get(b).getDay() +" Round 2 "+ " Card: #" + id + "\n");}
                }
            }
        try{PrintWriter output = new PrintWriter(new File("src/printSim.txt"));
            output.println(finalFile);
            output.close();}
        catch(IOException e){}


    }




}
