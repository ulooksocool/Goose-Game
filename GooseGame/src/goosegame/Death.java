package goosegame;

public class Death extends Square {

    public Death(int sqnum, Player player) {
        super(sqnum, player);
    }

    public Death(int sqnum) {
        super(sqnum);
    }

     @Override
    public boolean action(Board board) {

        board.players.get(board.turn).oldsquare=board.players.get(board.turn).square;
        board.players.get(board.turn).square=1;
        board.swap(board.board.get(1).player, board.players.get(board.turn));
        System.out.println("Player: " + board.players.get(board.turn).name + " moved to Death");
        System.out.println("Player: " + board.players.get(board.turn).name + " moved to " + board.players.get(board.turn).square);
        return true;
    }

}
