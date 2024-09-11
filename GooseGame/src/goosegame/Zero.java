 package goosegame;


public class Zero extends Square {

    public Zero(int sqnum) {
        super(sqnum);
    }
    
    @Override
    public boolean action(Board board){
       return true; 
    }
}
