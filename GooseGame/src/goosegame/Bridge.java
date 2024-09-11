package goosegame;

public class Bridge extends Square {

    public Bridge(int sqnum, Player player) {
        super(sqnum, player);
    }

    public Bridge(int sqnum) {
        super(sqnum);
    }

     @Override
    public boolean action(Board board) {
        board.players.get(board.turn).oldsquare=board.players.get(board.turn).square;
        board.players.get(board.turn).square=12;
        board.swap(board.board.get(12).player, board.players.get(board.turn));
        System.out.println("Player: " + board.players.get(board.turn).name + " moved to Bridge");
        System.out.println("Player: " + board.players.get(board.turn).name + " moved to " + board.players.get(board.turn).square);
       
        return true;
    }

    
}
