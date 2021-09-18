import java.awt.image.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class BingoCard {
    private int[][] arr;
    private static Random random;
    private Font font;
    private Font font1;
    private Font font2;
    private Font id_font;
    private static int i;
    private static ArrayList<int[][]> used;
    private BufferedImage cardImage;
    private int id;
    private boolean win;

    public BingoCard(int seed, int c){
        win = false;
        if(i==0){used = new ArrayList<>();random = new Random(seed);}
        id = c;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("carbon.ttf")).deriveFont(120f);
            font1 = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("carbon.ttf")).deriveFont(30f);
            font2 = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("carbon.ttf")).deriveFont(50f);
            id_font = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("carbon.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            ge.registerFont(font1);
            ge.registerFont(font2);
            ge.registerFont(id_font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        arr = new int[5][5];
        fillArr();
    }

    private void fillArr(){
        ArrayList<Integer> alreadyUsed = new ArrayList<>();
        boolean valid = false;
        int temp = 0;
        for(int r=0; r<arr.length; r++){
            for(int c=0; c<arr[r].length; c++){
                while(!valid) {
                    temp = random.nextInt(15) + 1 + 15 * c;
                    if (!alreadyUsed.contains(temp)) {
                        valid = true;
                        alreadyUsed.add(temp);
                    }
                }
                arr[r][c] = temp;
                valid = false;
            }
        }
        arr[2][2] = 0;
        if(used.contains(arr)){fillArr();}
        else{used.add(arr); createCard();}
    }

    private BufferedImage createOutline(){
        try {
            BufferedImage image = new BufferedImage(600, 700, BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();
            int i = 0;
            g.setColor(new Color(255, 216, 102));
            g.fillRect(100,0,500,150);
            g.setColor(new Color(45, 42, 46));
            g.setFont(font);
            g.drawString("B", 120, 95);
            g.drawString("I", 235, 95);
            g.drawString("N", 318, 95);
            g.drawString("G", 418, 95);
            g.drawString("O", 517, 95);
            g.setFont(id_font);
            g.drawString("" + id, 585, 20 );
            for(int r=100; r<=500; r+=100){
                for(int c=100; c<=500; c+=100){
                    if(i==0){
                        g.setColor(new Color(45, 42, 46));
                        g.fillRect(r, c, 100, 100);
                        //System.out.println("x: " + r + " y: " + c);
                        i++;
                    }
                    else if(i==1){
                        g.setColor(new Color(39, 40, 34));
                        g.fillRect(r, c, 100, 100);
                        i--;
                    }
                }
            }
            return image;

//            ImageIO.write(image, "png", new File("BingoBoard.png"));
//            System.out.println("Done");
        }
        catch(Exception e){return null;}
    }

    private void createCard(){
        try {
            BufferedImage image = createOutline();
            Graphics g = image.getGraphics();
            g.setFont(font2);
            for(int r=100; r<=500; r+=100){
                for(int c=100; c<=500; c+=100){
                    if(arr[(c/100)-1][(r/100)-1] <= 9){g.drawString("" + arr[(c/100)-1][(r/100)-1], r+35, c+65);}
                    else{g.drawString("" + arr[(c/100)-1][(r/100)-1], r+25, c+65);}
                }
            }
            g.setColor(new Color(45, 42, 46));
            g.fillRect(300, 300, 100, 100);
            g.setColor(Color.WHITE);
            g.setFont(font1);
            g.drawString("FREE", 325, 360);
//            new File("C:\\Users\\Cyric\\Downloads\\BingoProjectTest\\BingoCards1").mkdirs();
//            ImageIO.write(image, "png", new File("C:\\Users\\Cyric\\Downloads\\BingoProjectTest\\BingoCards1/"+i+ ".png"));
            //System.out.println(i + " Done");
            i++;
            cardImage = image;
        }
        catch(Exception e){e.printStackTrace();}

    }

    public String getString(){
        String s = "";
        for(int r=0; r<arr.length; r++){
            for(int c=0; c<arr[r].length; c++){
                s += " " + arr[r][c];
            }
        }
        return s;
    }

    public int[][] getArr(){
        return arr;
    }
    public void zero(int r, int c){
        arr[r][c] = 0;
    }

    public BufferedImage getImage(){
        return cardImage;
    }
    public void changeImage(BufferedImage c){cardImage = c;}
    public void setWin(){win=true;}
    public boolean getWin(){return win;}




}
