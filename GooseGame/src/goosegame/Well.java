package goosegame;

public class Well extends Square {

    public Well(int sqnum, Player player) {
        super(sqnum, player);
    }

    public Well(int sqnum) {
        super(sqnum);
    }

    @Override
    public boolean action(Board board) {
        
        if(this.player==board.players.get(board.turn)){
            System.out.println("Player: " + this.player.name + " is still trapped in the  Well ");
            return true;
        }

        if(this.player!=null){

            if(this.player.free==false){
               this.player.free=true; 
               System.out.println("Player: " + this.player.name + " is now free"); 
            } 

        }
            board.players.get(board.turn).free=false;
            this.player= board.players.get(board.turn);
            System.out.println("Player: " + this.player.name + " moved to " + this.player.square + " fell in the  Well and got trapped");
               

        return true;
    }

}
