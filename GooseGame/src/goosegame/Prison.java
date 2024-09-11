package goosegame;

public class Prison extends Square {

    public Prison(int sqnum, Player player) {
        super(sqnum, player);
    }

    public Prison(int sqnum) {
        super(sqnum);
    }

    @Override
    public boolean action(Board board){
        
        if(this.player==board.players.get(board.turn)){
           System.out.println("Player: " + this.player.name+" still in Prison "); 
            return true;
        }
        
        if(this.player!=null){

            if(this.player.free==false){
               this.player.free=true; 
               System.out.println("Player: " + this.player.name + " is now free"); 
               System.out.println("Player: " +board.players.get(board.turn).name+ " is free in Prison Square"); 
               this.player=null;
            } 
            return true;
        }
        
            board.players.get(board.turn).free=false;
            this.player= board.players.get(board.turn);
            System.out.print("Player: " + this.player.name + " moved to " + this.player.square);
            System.out.println(" is now in Prison ");
        return true ;

    }

}
