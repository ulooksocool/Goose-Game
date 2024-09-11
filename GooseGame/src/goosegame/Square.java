package goosegame;

public class Square {

 
    public int sqnum;
    public Player player; 
    
    public Square(int sqnum, Player player) {
        this.sqnum = sqnum;
        this.player = player;
    }

    public Square(int sqnum) {
        this.player=null;
        this.sqnum = sqnum;
    }
        

    public boolean action(Board board) {
        System.out.println("Super Class Square action method error");
        return false;
    }

    public boolean inSquare() {
        return this.player != null;
    }

    public void SetPlayer(Player player) {
        this.player = player;
    }

    public Player GetPlayer() {
        return this.player;
    }

}
