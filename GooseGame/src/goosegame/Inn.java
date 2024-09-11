package goosegame;

public class Inn extends Square {

    
    public Inn(int sqnum, Player player) {
        super(sqnum, player);
    }

    public Inn(int sqnum) {
        super(sqnum);
    }

    @Override
    public boolean action(Board board){
        
        this.player=board.players.get(board.turn);
        
        if(this.player.c!=0){
            this.player.c--; 
            if(this.player.c==0){
                System.out.println("Player: "+ this.player.name+ " leaves the Inn and can play from now on");
            }
            else{
                System.out.println("Player: "+ this.player.name+" will leave the Inn the next round ");
            }
        }
        else{
            this.player.c=2;
             System.out.println("Player: "+ this.player.name+" moved to "+this.player.square+" and stays at the Inn for the next two rounds!");
        }
        return true;
        
    }

   
}
