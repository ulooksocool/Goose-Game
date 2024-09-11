package goosegame;


import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class GooseGame {

    public static Board board = new Board();
    public static String filename = "MyBagIsGucci.txt";
    public static File destfilename = new File(filename);

    public static int dice() {
        Random rand = new Random();
        int dice = rand.nextInt(6) + 1;
        return dice;
    }

    public static void menu() {
        System.out.println("1.NEW GAME 2.LOAD GAME 3.SAVE GAME 4.EXIT");
    }

    public static int GetInt(int min, int max, int fletcher) {
        String harry = "PLEASE ENTER AMOUNT OF PLAYERS";
        if (fletcher == 0) {
            harry = "PLEASE ENTER CHOICE";
        }
        Scanner input = new Scanner(System.in);
        int choise = 0;
        do {
            System.out.println(harry);
            if (input.hasNextInt() == true) {
                choise = input.nextInt();
            } else {
                input.nextLine();
                System.out.println("PLEASE ENTER INTEGER");
            }

        } while ((choise < min) || (choise > max));

        return choise;
    }

    public static boolean setChoise(int choise) {
        int numpl;

        if (choise == 1) {

            board.GetFree();
            System.out.println("Please Enter Game Name(Board)");
            String nameboard = Stringgg(); //elegxos gia palio arxeio me idio onoma game board 

            while (CheckNameboard(nameboard) != -1) {
                System.out.println("Already exists");
                nameboard = Stringgg();
            }

            board.nameboard = nameboard;
            
            numpl = GetInt(2, 6, 1);
            for (int i = 0; i < numpl; i++) {
                System.out.println("Please Enter Names");
                String name = Stringgg();
                board.addPlayers(name);
            }

            board.OriginalBoard();
           
        } else if (choise == 2) {

            System.out.println("Please Enter The Name of the Game You Want To Open");
            PrintSave();
            String nameboard = Stringgg(); //elegxos gia palio arxeio me idio onoma game board 
            while (CheckNameboard(nameboard) == -1) {
                if (nameboard.equals("exit")) {
                    return false; //mporei kai true 
                }
                System.out.println("INVALID NAME SAVE");
                System.out.println("Please Enter The Name of the Game You Want To Open");
                nameboard = Stringgg();
            }
            if (board.nameboard != null) {
                setChoise(3);
                board.GetFree();
            } else {
                board.OriginalBoard();
            }

            board.nameboard = nameboard;
            int line = CheckNameboard(nameboard);
            SoulaElaKsana(line);
            for(Player d: board.players ){
                
             if(d.square==63){
                 board.EndOfGame();
                 System.exit(0);
             }
             
            }
            

        } else if (choise == 3) {
            Write();
        } 
            
        
        return true;
    }

    public static String Stringgg() {
        Scanner input = new Scanner(System.in);
        String name = input.next();
        return name;
    }

    public static boolean WriteOver() {

        try {
            if (destfilename.exists()) {
                FileInputStream oldsave = new FileInputStream(destfilename);
                Scanner reader = new Scanner(oldsave);

                File x = new File("GucciDownToMyDrawrers.txt");
                FileOutputStream tmpFile = new FileOutputStream(x, true);
                PrintWriter writer = new PrintWriter(tmpFile);
                int lineToRemove = CheckNameboard(board.nameboard);
               
                int i = -1;
                String currentLine;

                while (reader.hasNextLine()) {
                    i++;
                    
                    currentLine = reader.nextLine();
                    if (lineToRemove == i) {
                        writer.print(board.nameboard + "," + board.players.size() + "," + board.turn + ",");

                        for (int j = 0; j < board.players.size(); j++) {
                            writer.print(board.players.get(j).name + "," + board.players.get(j).square);
                            writer.print("," + board.players.get(j).oldsquare + "," + board.players.get(j).c);
                            writer.print("," + board.players.get(j).free + ",");

                        }
                        writer.println();
                    } else {   
                        writer.println(currentLine);  
                    }
 
                }

                if (lineToRemove == -1) {

                    writer.print(board.nameboard + "," + board.players.size() + "," + board.turn + ",");

                    for (int j = 0; j < board.players.size(); j++) {
                        writer.print(board.players.get(j).name + "," + board.players.get(j).square);
                        writer.print("," + board.players.get(j).oldsquare + "," + board.players.get(j).c);
                        writer.print("," + board.players.get(j).free + ",");

                    }
                    writer.println();
                }
                writer.close();
                reader.close();
                destfilename.delete();
                boolean successful = x.renameTo(destfilename);

                if (successful) {
                    System.out.println("succesful");
                }

                return successful;

            }
        } catch (IOException e) {
            System.out.println("ERROR! GAME NOT SAVED");
        }

        return false;

    }

    public static int CheckNameboard(String nameboard) {
        String game;
        int x = -1, i = 0;
        try {
            if (destfilename.exists()) {
                //   x=0;
                FileInputStream oldsave = new FileInputStream(destfilename);
                Scanner InputStream = new Scanner(oldsave);
                while (InputStream.hasNextLine()) {
                    game = InputStream.nextLine();
                    if ("".equals(game)) {
                        break;
                    }
                    String[] arr = game.split(",");
                    if (arr[0].equals(nameboard)) {
                        x = i;
                        // InputStream.close();
                        break;
                    }
                    i++;
                }
                InputStream.close();
            }
        } catch (FileNotFoundException e) {
            //System.out.println("File " +filename+ " Was Not Found");
            //System.out.println("Or Could Not Be Opened.");   
        }
        return x;
    }

    public static void PrintSave() {
        String game;
        int i = 0;
        try {
            if (destfilename.exists()) {
                FileInputStream x = new FileInputStream(destfilename);
                Scanner InputStream = new Scanner(x);
                while (InputStream.hasNextLine()) {
                    i++;
                    game = InputStream.nextLine();
                    String[] arr = game.split(",");
                    System.out.println(" " + i + ".  " + arr[0]);
                }

                InputStream.close();
            }
        } catch (FileNotFoundException e) {
            // System.out.println("File " + filename + " Was Not Found");
            // System.out.println("Or Could Not Be Opened.");
        }

    }

    public static void Write() {
        try {

            if (!destfilename.exists()) {

                FileOutputStream tmpFile = new FileOutputStream(destfilename, true);
                PrintWriter OutputStream = new PrintWriter(tmpFile);
                OutputStream.print(board.nameboard + "," + board.players.size() + "," + board.turn + ",");

                for (int i = 0; i < board.players.size(); i++) {
                    OutputStream.print(board.players.get(i).name + "," + board.players.get(i).square);
                    OutputStream.print("," + board.players.get(i).oldsquare + "," + board.players.get(i).c);
                    OutputStream.print("," + board.players.get(i).free + ",");

                }
                OutputStream.println();
                OutputStream.close();

            } else {
                WriteOver();
            }

        } catch (FileNotFoundException e) {
            System.out.println("ERROR OPENING THE FILE " + filename);
            System.out.println("GAME NOT SAVED");

        }

    }

    public static void SoulaElaKsana(int line) {
        String game;
        String[] array = null;
        int i = 0, k = 3;
        try {
            Scanner InputStream = new Scanner(new FileInputStream(filename));
            while (i <= line) {
                game = InputStream.nextLine();
                String[] arr = game.split(",");
                array = arr;
                i++;
            }

            board.turn = Integer.parseInt(array[2]);
            for (i = 0; i < Integer.parseInt(array[1]); i++) {
                board.load(array[k], Integer.parseInt(array[k + 1]), Integer.parseInt(array[k + 2]), Integer.parseInt(array[k + 3]), array[k + 4]);
                k += 5;
            }
            InputStream.close();

        } catch (FileNotFoundException e) {
            //System.out.println("File " +filename+ " Was Not Found");
            //System.out.println("Or Could Not Be Opened.");   
        }

    }

    public static void main(String[] args) {

        int choise, dice1, dice2;
        boolean result;
        String play;
        board.turn = 0;
        menu();
        while (true) {
            choise = GetInt(1, 4, 0);
            result = setChoise(choise);
            if (choise == 4) {
                return;
            } else if (((choise == 1) || (choise == 2)) && (result == true)) {
                break;
            }

        }
        while (true) {
            while (true) {

                System.out.println("Press D to roll the dice \n Please Enter E for main menu");
                play = Stringgg();
                if ((play.equalsIgnoreCase("d")) || (play.equalsIgnoreCase("e"))) {
                    break;
                }
          
            }

            if (play.equalsIgnoreCase("e")) {

                menu();
                while (true) {
                    choise = GetInt(1, 4, 0);
                    result = setChoise(choise);
                    if (choise == 4) {
                        return;
                    } else if (((choise == 1) || (choise == 2)) && (result == true)) {
                        break;
                    }

                }
            } else {

                dice1 = dice();
                dice2 = dice();

                if ((board.players.get(board.turn).c != 0) || (board.players.get(board.turn).free == false)) {

                    board.board.get(board.players.get(board.turn).square).action(board);
                } else {

                    board.players.get(board.turn).moveTo(dice1, dice2);

                    while (true) {
                        result = board.board.get(board.players.get(board.turn).square).action(board);
                        if (result) {
                            board.players.get(board.turn).direction = true;
                            break;
                        }
                    }
                }
                if (board.EndOfGame()) {
                     break; 
                }

            }

            board.turn++;
            if (board.turn > board.players.size() - 1) {
                board.turn = 0;
            }
        }
    }

}
