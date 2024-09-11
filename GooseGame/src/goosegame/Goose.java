package goosegame;

public class Goose extends Square {

    public Goose(int sqnum, Player player) {
        super(sqnum, player);
    }

    public Goose(int sqnum) {
        super(sqnum);
    }

    @Override
    public boolean action(Board board) {
        
     
        board.players.get(board.turn).moveTo(board.players.get(board.turn).dice1,board.players.get(board.turn).dice2);
        System.out.println("Player: "+ board.players.get(board.turn).name+" moved to a Goose  Sqaure");
        System.out.println("Player: "+ board.players.get(board.turn).name+" moved to "+board.players.get(board.turn).square );
        return false;
    }
    
}
