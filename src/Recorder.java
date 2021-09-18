public class Recorder {
    private int ranNum;
    private int cardId;

    public Recorder(int dn, int ci){
        ranNum = dn;
        cardId = ci;
    }

    public int getRanNum(){return ranNum;}
    public int getCardId(){return cardId;}
    public String toString(){
        return "ID: " + cardId + " Random Num: " + ranNum;
    }
}
