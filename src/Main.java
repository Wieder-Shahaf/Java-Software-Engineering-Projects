import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static Scanner scanner;
    public static Random rnd;

    public static void battleshipGame() {
        Scanner input1 = new Scanner(System.in);  // Create a Scanner object
        String boardSize;
        String battleship;

        // Enter boardSize and press Enter
        System.out.println("Enter the board size");
        boardSize = input1.nextLine();  // Read user input

        // boardSize
        int rows = 0, columns = 0;
        int i = 0;

        // While loop to find the rows number from boardSize string
        while (boardSize.charAt(i) != 'X') {
            if (boardSize.charAt(i) >= '0' && boardSize.charAt(i) <= '9') {
                rows = rows * 10 + (boardSize.charAt(i) - '0');
            }
            i++;
        }

        // While loop to find the columns number from boardSize string
        int len = boardSize.length();
        while (i < len ){
            if (boardSize.charAt(i) >= '0' && boardSize.charAt(i) <= '9') {
                columns = columns * 10 + (boardSize.charAt(i) - '0');
            }
            i ++;
        }

        //****************TEST**********************
        System.out.println("The size is " + rows);
        System.out.println("The size is " + columns);
        //****************TEST**********************

        // Creat board
       char[][] board = new char[rows+1][columns+1];

        //initialize
        for(int c_row=0;c_row<= rows;c_row++){
            for (int c_col = 0; c_col <=columns; c_col++) {
                if (c_row == 0 && c_col == 0) {
                    board[c_row][c_col]= ' ';
                    continue;
                }
                if (c_row != c_col && c_row == 0) {
                    board[c_row][c_col] = (char) (c_col - 1 + '0');
                    continue;
                }
                if (c_row != c_col && c_col == 0) {
                    board[c_row][c_col] = (char) (c_row - 1 + '0');
                }
                else {
                    board[c_row][c_col]='–';
                }
            }
        }

        // print board
        for(int c_row=0;c_row<=rows;c_row++){
            for (int c_col = 0; c_col <=columns; c_col++) {
                if (c_row == 0 && c_col == 0) {
                    System.out.print("  ");
                } else if (c_row == 0) {
                    System.out.print(board[c_row][c_col] + " ");
                } else if (c_col == 0) {
                    System.out.print(board[c_row][c_col] + " ");
                } else {
                    System.out.print(board[c_row][c_col] + " ");
                }
                //System.out.print(board[c_row][c_col] + " ");
            }
            System.out.println();
        }

        // Enter battleship and press Enter
        System.out.println("Enter the battleship sizes");
        battleship = input1.nextLine();

        // iterate over the string to find size for array
        int count_x = 0;
        for (int k = 0; k < battleship.length(); k++) {
            if (battleship.charAt(k) == 'X') {
                count_x++;
            }
        }

        int temp = 0; // get the current value (Quantity / Size) of battleship
        i = 0; // while index
        int j = 0; // array index
        int[] subInventory = new int[(count_x)*2];

        while (i < (battleship.length())){
            if (battleship.charAt(i) >= '0' && battleship.charAt(i) <= '9') {
                temp = temp * 10 + (battleship.charAt(i) - '0');
            }
            else {
                subInventory[j] = temp;
                temp = 0;
                j++;
            }
            if (i == (battleship.length() - 1)){
                subInventory[j] = temp;
            }
            i ++;
        }
        //****************TEST**********************
        System.out.println(Arrays.toString(subInventory));
        //****************TEST**********************
    }
        // TODO: Add your code here (and add more methods).

    public static void main(String @NotNull [] args) throws IOException {
        String path = args[0];
        scanner = new Scanner(new File(path));
        int numberOfGames = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Total of " + numberOfGames + " games.");

        for (int i = 1; i <= numberOfGames; i++) {
            scanner.nextLine();
            int seed = scanner.nextInt();
            rnd = new Random(seed);
            scanner.nextLine();
            System.out.println("Game number " + i + " starts.");
            battleshipGame();
            System.out.println("Game number " + i + " is over.");
            System.out.println("------------------------------------------------------------");
        }
        System.out.println("All games are over.");
    }
}



