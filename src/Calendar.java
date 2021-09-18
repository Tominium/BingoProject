import java.util.*;

public class Calendar {
    private int dayNum;
    private ArrayList<Integer> r1;
    private ArrayList<Integer> r2;

    public Calendar(int dn, ArrayList<Integer> r11, ArrayList<Integer> r22){
        dayNum = dn;
        r1 = r11;
        r2 = r22;
    }

    public int getDay(){return dayNum;}
    public ArrayList<Integer> getR1(){return r1;}
    public ArrayList<Integer> getR2(){return r2;}
}
