package goosegame;

public class Player {

    public String name, nameboard;
    public int square, oldsquare,dice1,dice2,c=0;
    public boolean direction = true, free = true; // direction:false =left ,true=right
    

    public Player(String name, int square,int oldsquare,String nameboard ,int c,boolean free) { 
        this.name = name;
        this.square = square;
        this.oldsquare = oldsquare;
        this.nameboard = nameboard;
        this.c=c;
        this.free=free;
    }
    

    public Player(String name, String nameboard) {
        this.name = name;
        this.square = 0;
        this.nameboard = nameboard;
        this.oldsquare = square;
    }

    public void moveTo(int dice1, int dice2) {
      if((c==0)&&(free==true)){
        this.dice1=dice1;
        this.dice2=dice2;
       this.oldsquare=square;
        if (this.square == 0) {
            if ((dice1 == 6) && (dice2 == 3)) {
                this.square = 26;
            } else if ((dice1 == 3) && (dice2 == 6)) {
                this.square = 26;
            } else if ((dice1 == 5) && (dice2 == 4)) {
                this.square = 53;
            } else if ((dice1 == 4) && (dice2 == 5)) {
                this.square = 53;
            } else {
                this.square = dice1 + dice2;
            }
        } else {

            if ((this.square + dice1 + dice2 > 63) && (this.direction == true)) {
                this.square = 63 - (dice1 + dice2 - (63 - this.square));//pros ta pisw
                this.direction = false;
            } else if (this.direction == true) {
                this.square = this.square + (dice1 + dice2);
            } else if (this.direction == false) { //yparxei motibo stn xhna ana 4,5 cells ara den paizei na ginei lathos
                this.square = this.square - (dice1 + dice2);
                this.direction = true;
            }

        }
      }
       
    }

}
