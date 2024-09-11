package goosegame;

public class RegularSquare extends Square {

    public RegularSquare(int sqnum, Player player) {
        super(sqnum, player);
    }

    public RegularSquare(int sqnum) {
        super(sqnum);
    }


    @Override
    public boolean action(Board board) {
        board.swap(player,board.players.get(board.turn));
        System.out.println(board.players.get(board.turn).name + " is at " + board.players.get(board.turn).square);
        return true;
    }

}
