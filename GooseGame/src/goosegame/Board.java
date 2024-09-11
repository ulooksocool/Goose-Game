package goosegame;

import java.util.ArrayList;

public class Board {

    String nameboard;
    public int turn;
    ArrayList<Square> board = new ArrayList<>();
    ArrayList<Player> players = new ArrayList<>();

    public void GetFree() {
        for (int i = 0; i < board.size(); i++) {
            board.get(i).player = null;
        }
        players.clear();
        nameboard = null;
    }

    public void load(String name, int square, int oldsquare, int c, String free) {
        Player x = new Player(name, square,oldsquare, this.nameboard,c,free.equals("true"));
        players.add(x);
        board.get(square).player = players.get(players.size() - 1);
    }

    public void addPlayers(String name) {
        Player x = new Player(name, this.nameboard);
        players.add(x);
    }

    public Square addSquare(int i) {
        Square x;

        if (i == 0) {
            x = new Zero(i);
        }
        if ((i == 5) || (i == 9) || (i == 14) || (i == 18) || (i == 23) || (i == 27) || (i == 32) || (i == 36) || (i == 41) || (i == 45) || (i == 50) || (i == 54) || (i == 59)) {
            x = new Goose(i);
        } else if (i == 6) {
            x = new Bridge(i);
        } else if (i == 19) {
            x = new Inn(i);
        } else if (i == 31) {
            x = new Well(i);
        } else if (i == 42) {
            x = new Labyrinth(i);
        } else if (i == 52) {
            x = new Prison(i);
        } else if (i == 58) {
            x = new Death(i);
        } else {
            x = new RegularSquare(i);
        }
        x.sqnum++;
        board.set(i, x);
        return x;
    }

    public void OriginalBoard() {
        for (int i = 0; i < 64; i++) {
            board.add(new Square(i + 1, null));
        }
        for (int i = 0; i < 64; i++) {
            addSquare(i);
        }
    }

    public Square getSquare(int sq) {
        return board.get(sq);
    }

    public void swap(Player oldplayer, Player newplayer) {

        if (oldplayer != null) {
            if ((oldplayer.free == true) && (newplayer.free == true)) {
                System.out.print("Square Taken So Swap!");
                System.out.println("Player " + oldplayer.name + " moved to " + newplayer.oldsquare);

                oldplayer.oldsquare = oldplayer.square;
                oldplayer.square = newplayer.oldsquare;

                getSquare(oldplayer.square).player = oldplayer;
                getSquare(newplayer.square).player = newplayer;

            } else {
                System.out.print("No Square Taken!");
                System.out.println("Player " + newplayer.name + " moved to " + newplayer.square);

                getSquare(newplayer.oldsquare).player = null;
                getSquare(newplayer.square).player = newplayer;

            }
        } else {
            if (newplayer.free == true) {
                System.out.println("No Square Taken!");
                System.out.println("Player " + newplayer.name + " moved to " + newplayer.square);
                getSquare(newplayer.oldsquare).player = null;
                getSquare(newplayer.square).player = newplayer;

            }

        }
          
    }

    public boolean EndOfGame() {
        int c = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).square == 63) {
                System.out.println("\n !!" + players.get(i).name + " IS THE WINNER !!");
                return true;
            }
            if (players.get(i).free) {
                c++;
            }
        }
        if (c == 0) {
            System.out.println("YOU ALL LOST");
            return true;
        }
        return false;
    }

}
