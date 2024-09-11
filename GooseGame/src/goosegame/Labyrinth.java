package goosegame;

public class Labyrinth extends Square {

    public Labyrinth(int sqnum, Player player) {
        super(sqnum, player);
    }

    public Labyrinth(int sqnum) {
        super(sqnum);
    }

    @Override
    public boolean action(Board board) {
       
        board.players.get(board.turn).oldsquare=board.players.get(board.turn).square;
        board.players.get(board.turn).square=30;
        board.swap(board.board.get(30).player, board.players.get(board.turn));
        System.out.println("Player: " + board.players.get(board.turn).name + " moved to Labyrinth");
        System.out.println("Player: " + board.players.get(board.turn).name + " moved to " + board.players.get(board.turn).square);
        return true;
    }

}
