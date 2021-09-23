public class TableObj {
    private int dayNum;
    private int roundNum;
    private int cardNum;

    public TableObj(int dn, int rn, int cn){
        dayNum = dn;
        roundNum = rn;
        cardNum = cn;
    }

    public int getDayNum() {
        return dayNum;
    }

    public int getRoundNum() {
        return roundNum;
    }

    public int getCardNum() {
        return cardNum;
    }

}
