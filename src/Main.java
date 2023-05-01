import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    public static Random rnd;

    /**
     This method takes an integer as input and returns the number of digits in that integer.
     @param number the input integer to count the number of digits
     Return the number of digits in the input integer
     */
    public static int countDig(int number){
        int count = 0;
        if (number==0) {
            return 1;
        }
        while(number!=0){
            number = number/10;
            count ++;
        }
        return count;
    }

    /**
     Method to print a specified number of spaces.
     @param sub an integer representing the number of spaces to print
     Return void
     */
    public static void Spacing(int sub){
        for(int index = 0; index < sub; index++ ) {
            System.out.print(" ");
        }
    }

    /**
     This method validates if an attack on the game board is valid.
     @param n: an integer representing the number of rows in the game board
     @param m: an integer representing the number of columns in the game board
     @param x: an integer representing the row number of the attack point
     @param y: an integer representing the column number of the attack point
     @param guessingBoard: a 2D String array representing the guessing board that tracks the state of previously attacked points
     Return 0 if the attack was invalid, 1 if the attack valid
     */
    public static int validationAttack(int n, int m, int x, int y, String[][] guessingBoard){
        if ((x >= n) || (x < 0) || (y >= m) || (y < 0)) { // Check if the attack point is within the boundaries of the board
            System.out.println("Illegal tile, try again!");
            return 0;
        }
        if (guessingBoard[x][y] != "–") { // Check if the point has already been attacked by looking at the corresponding tile in the guessing board array
            System.out.println("Tile already attacked, try again!");
            return 0;
        } else { // If the attack is valid
            return 1;
        }
    }

    /**
     A method that validates if a computer's attack on the game board is valid.
     @param n the number of rows on the game board
     @param m the number of columns on the game board
     @param x the row index of the attack point
     @param y the column index of the attack point
     @param computerguessingBoard a two-dimensional array representing the computer's guessing board
     Return 0 if the attack is invalid or 1 if the attack is valid
     */
    public static int validationComputerAttack(int n, int m, int x, int y, String[][] computerguessingBoard) {
        if ((x >= n) || (x < 0) || (y >=m) || (y < 0)) { // Check if the attack point is within the boundaries of the board

            return 0;
        }
        if (computerguessingBoard[x][y] != "–") { // Check if the point has already been attacked by looking at the corresponding tile in the guessing board array

            return 0;
        } else { // If the attack is valid
            return 1;
        }
    }

    /**
     This method checks if the attacked submarine is sunk by searching for adjacent submarine parts in all four directions.
     @param n - an integer representing the number of rows of the game board.
     @param m - an integer representing the number of columns of the game board.
     @param x - an integer representing the x-coordinate of the attacked point.
     @param y - an integer representing the y-coordinate of the attacked point.
     @param currentgameBoard - a 2D array of strings representing the current state of the game board.
     Return an integer value of 1 if the submarine is sunk, and 0 if it is not.
     */
    public static int isDrowned(int n, int m, int x, int y, String[][] currentgameBoard) {

        int result = 1;

        // Search to the right of the attacked coordinate.
        for (int right = y + 1; right < m; right++) {
            if (currentgameBoard[x][right] == "#") { // If a part of the submarine is found adjacent to the right, the submarine is not sunk
                return 0;
            }
            if (currentgameBoard[x][right] == "–") { // If the loop reaches a point on the game board that is not part of the submarine, exit the loop because we have checked all adjacent points to the right of the attacked coordinate.
                        break;
                    }
            }

        // Search to the left of the attacked coordinate.
        for (int left = y - 1; left >= 0; left--) { // If a part of the submarine is found adjacent to the left, the submarine is not sunk
            if (currentgameBoard[x][left] == "#") { // Returns 0 if there is a submarine part adjacent to the left that not yet attacked
                return 0;
            }
            if (currentgameBoard[x][left] == "–") { // If the loop reaches a point on the game board that is not part of the submarine, exit the loop because we have checked all adjacent points to the left of the attacked coordinate.
                break;
            }
        }

        // Search above the attacked coordinate.
        for (int up = x + 1; up < n; up++) {
            if (currentgameBoard[up][y] == "#") { // If a part of the submarine is found adjacent above the attacked coordinate, the submarine is not sunk.
                return 0;
            }
            if (currentgameBoard[up][y] == "–") { // If the loop reaches a point on the game board that is not part of the submarine, exit the loop because we have checked all adjacent points above the attacked coordinate.
                break;
            }
        }

        // Search below the attacked coordinate.
        for (int down = x - 1; down >= 0; down--) {
            if (currentgameBoard[down][y] == "#") { // If a part of the submarine is found adjacent below the attacked coordinate, the submarine is not sunk.
                return 0;
            }
            if (currentgameBoard[down][y] == "–") { // If the loop reaches a point on the game board that is not part of the submarine, exit the loop because we have checked all adjacent points below the attacked coordinate.
                break;
            }
        }
        return result;
    }

    /**
     This method checks if the positioning of the current battleship is valid based on player selection.
     @param n The number of rows in the game board.
     @param m The number of columns in the game board.
     @param x The x-coordinate of the starting position of the battleship.
     @param y The y-coordinate of the starting position of the battleship.
     @param orientation The orientation of the battleship: 0 for horizontal and 1 for vertical.
     @param size The size of the battleship.
     @param gameBoard The 2D array representing the game board.
     Return 1 if the location is valid, and 0 otherwise.
     */
    public static int validationCheck(int n, int m, int x, int y, int orientation, int size, String[][] gameBoard) {
        if ((orientation != 0) && (orientation != 1)) { // Checks if the orientation is o (horizontal) or 1 (vertical) as needed
            System.out.println("Illegal orientation, try again!");
            return 0;
        }
        if ((x >= n) || (x < 0) || (y >= m) || (y < 0)) { // Checks if the point is within the board boundaries
            System.out.println("Illegal tile, try again!");
            return 0;
        }
        if (orientation == 0) { // if the ship horizontally, checks if ship within the board boundaries
            if (y + size > m) {
                System.out.println("Battleship exceeds the boundaries of the board, try again!");
                return 0;
            }
        }
        if (orientation == 1) { // if the ship vertically, checks if ship within the board boundaries
            if (x + size > n) {
                System.out.println("Battleship exceeds the boundaries of the board, try again!");
                return 0;
            }
        }
        for (int i = 0; i < size; i++) { // checks if the Battleship overlaps another battleship
            if (orientation == 0) {
                if (gameBoard[x][y + i].equals("#")) { // if battleship horizontally
                    System.out.println("Battleship overlaps another battleship, try again!");
                    return 0;
                }
            } else {
                if (gameBoard[x + i][y].equals("#")) { //if battleship vertically
                    System.out.println("Battleship overlaps another battleship, try again!");
                    return 0;
                }
            }
        }

        for (int j = 0; j < size + 2; j++) {
            int minDistanceY = y + j - 1; //minimal distance from another ship in the Y axis
            int minDistanceX = x + j - 1; //minimal distance from another ship in the X axis
            if (orientation == 0) { //if battleship horizontally
                if ((n > (x - 1) && (x - 1) >= 0) && (m > minDistanceY) && ((minDistanceY) >= 0)) {
                    if (gameBoard[x - 1][minDistanceY].equals("#")) {
                        System.out.println("Adjacent battleship detected, try again!");
                        return 0;
                    }
                }
                if (((n > x) && (m > (minDistanceY)) && (minDistanceY >= 0))) {
                    if (gameBoard[x][minDistanceY].equals("#")) {
                        System.out.println("Adjacent battleship detected, try again!");
                        return 0;
                    }
                }
                if ((n > (x + 1)) && (m > minDistanceY) && (minDistanceY >= 0)) {
                    if (gameBoard[x + 1][minDistanceY].equals("#")) {
                        System.out.println("Adjacent battleship detected, try again!");
                        return 0;
                    }
                }
            }

            if (orientation == 1) { //if battleship vertically
                if ((n > (minDistanceX) && (minDistanceX) >= 0) && (m > (y - 1) && (y - 1) >= 0)) {
                    if (gameBoard[minDistanceX][y - 1].equals("#")) {
                        System.out.println("Adjacent battleship detected, try again!");
                        return 0;
                    }
                }
                if ((n > (minDistanceX) && (minDistanceX) >= 0) && (m > y)) {
                    if (gameBoard[minDistanceX][y].equals("#")) {
                        System.out.println("Adjacent battleship detected, try again!");
                        return 0;
                    }
                }
                if ((n > (minDistanceX) && (minDistanceX) >= 0) && (m > (y + 1) && (y + 1) >= 0)) {
                    if (gameBoard[minDistanceX][y + 1].equals("#")) {
                        System.out.println("Adjacent battleship detected, try again!");
                        return 0;
                    }
                }
            }
        }

        return 1;
    }

    /**
     Computes the validation check for the computer's selection of battleship position on the game board.
     @param n the number of rows on the game board
     @param m the number of columns on the game board
     @param x the x-coordinate of the battleship's starting point
     @param y the y-coordinate of the battleship's starting point
     @param orientation the orientation of the battleship (0 for horizontal, 1 for vertical)
     @param size the size of the battleship
     @param gameBoard the current state of the game board
     Return 1 if the positioning of the current battleship is valid, 0 otherwise
     */
    public static int computervalidationCheck(int n, int m, int x, int y, int orientation, int size, String[][] gameBoard){
        if ((orientation != 0) && (orientation != 1)) { // Checks if the orientation is o (horizontal) or 1 (vertical) as needed
            return 0;
        }
        if ((x >= n) || (x < 0) || (y >= m) || (y < 0)) { // Checks if the point is within the board boundaries
            return 0;
        }
        if (orientation == 0) { // if the ship horizontally, checks if ship within the board boundaries.
            if (y + size > m) {
                return 0;
            }
        }
        if (orientation == 1) { // if the ship vertically, checks if ship within the board boundaries.
            if (x + size > n) {
                return 0;
            }
        }
        for (int i = 0; i < size; i++) { // checks if the Battleship overlaps another battleship.
            if (orientation == 0) {
                if (gameBoard[x][y + i].equals("#")) { // if battleship horizontally
                    return 0;
                }
            } else {
                if (gameBoard[x + i][y].equals("#")) { // if battleship vertically
                    return 0;
                }
            }
        }
        
        
        /*The following for loop check every 3 point from the left side of every point on the ship. 
        two left diagonal points and one straight left point.
        The for loop divided into two cases; horizontally and vertically.
        The point is checked for validation and for occupation.
        if the point validated for both then it can continue and applied the point to the board*/
        for (int j = 0; j < size + 2; j++) {
            int minDistanceY = y + j - 1; //minimal distance from another ship in the Y axis
            int minDistanceX = x + j - 1; //minimal distance from another ship in the X axis
            if (orientation == 0) { //if battleship horizontally
                if ((n > (x - 1) && (x - 1) >= 0) && (m > minDistanceY) && ((minDistanceY) >= 0)) {
                    if (gameBoard[x - 1][minDistanceY].equals("#")) {
                        return 0;
                    }
                }
                if (((n > x) && (m > (minDistanceY)) && (minDistanceY >= 0))) {
                    if (gameBoard[x][minDistanceY].equals("#")) {
                        return 0;
                    }
                }
                if ((n > (x + 1)) && (m > minDistanceY) && (minDistanceY >= 0)) {
                    if (gameBoard[x + 1][minDistanceY].equals("#")) {
                        return 0;
                    }
                }
            }

            if (orientation == 1) { //if battleship horizontally
                if ((n > (minDistanceX) && (minDistanceX) >= 0) && (m > (y - 1) && (y - 1) >= 0)) {
                    if (gameBoard[minDistanceX][y - 1].equals("#")) {
                        return 0;
                    }
                }
                if ((n > (minDistanceX) && (minDistanceX) >= 0) && (m > y)) {
                    if (gameBoard[minDistanceX][y].equals("#")) {
                        return 0;
                    }
                }
                if ((n > (minDistanceX) && (minDistanceX) >= 0) && (m > (y + 1) && (y + 1) >= 0)) {
                    if (gameBoard[minDistanceX][y + 1].equals("#")) {
                        return 0;
                    }
                }
            }
        }


        return 1; // if all test return positive, positive is valid.
    }

    /**
     This method prints the game board with the given row and column numbers and game board array.
     @param n The number of rows in the game board.
     @param m The number of columns in the game board.
     @param rowLenght The maximum length of the row numbers used for spacing.
     @param columnsLength The maximum length of the column numbers used for spacing.
     @param gameBoard The two-dimensional string array representing the game board.
     */
    public static void printBoard(int n, int m, int rowLenght, int columnsLength, String[][] gameBoard) {
        System.out.println("Your current game board:");

        // Print columns
        int colamountSubtraction = columnsLength - countDig(0); // integer that sends to spacing method
        Spacing(colamountSubtraction); // call for spacing function (description in the method)
        System.out.print(" ");
        for (int i = 0; i < m; i++) { // print columns numbers
            System.out.print(" " + i);
        }
        System.out.println();

        //Print rows
        for (int j = 0; j < n; j++) {
            colamountSubtraction = rowLenght - countDig(j); // integer that sends to spacing method
            Spacing(colamountSubtraction); // call for spacing function (description in the method)
            System.out.print(j); // print rows numbers
            for (int k = 0; k < m; k++) {
                System.out.print(" " + gameBoard[j][k]); // print tiles
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     Method that prints the current guessing board game
     @param n an integer representing the number of rows in the board
     @param m an integer representing the number of columns in the board
     @param rowLength an integer representing the length of the rows in the board
     @param columnsLength an integer representing the length of the columns in the board
     @param String a String matrix representing the tiles in the board
     */
    public static void printGuessingBoard(int n, int m, int rowLength, int columnsLength, String[][] String) {
        System.out.println("Your current guessing board:");

        // Print column
        int subn = columnsLength - countDig(0); // integer that sends to spacing method
        Spacing(subn); // call for spacing function (description in the method)
        System.out.print(" ");
        for (int i = 0; i < m; i++) { // print rows numbers
            System.out.print(" " + (i));
        }
        System.out.println();

        //Print rows
        for (int j = 0; j < n; j++) {
            subn = rowLength - countDig(j); // integer that sends to spacing method
            Spacing(subn); // call for spacing function (description in the method)
            System.out.print(j); // print rows numbers
            for (int k = 0; k < m; k++) {
                System.out.print(" " + String[j][k]); // print tiles
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     This method inserts "#" after a coordinate selected by the player or computer validated for battleship assignment.
     @param x the x coordinate of the starting point
     @param y the y coordinate of the starting point
     @param string the game board as a 2D String array
     @param size the size of the battleship
     @param orientation the orientation of the battleship, 0 for horizontal and 1 for vertical
    Return the updated game board with "#" in the designated tiles
     */
    public static String[][] locatePoint(int x, int y, String[][] string, int size, int orientation) {
        if (orientation == 0) {
            for (int i = y; i < y + size; i++) {
                string[x][i] = "#";
            }
        }
        if (orientation == 1) {
            for (int i = x; i < x + size; i++) {
                string[i][y] = "#";
            }
        }
        return string;
    }

    /**
     * This method manage the game using internal logic and calling for external method
     */
    public static void battleshipGame() {
        System.out.println("Enter the board size");
        String boardSize = scanner.nextLine(); //getting the board size from player.
        String[] sizeArray = boardSize.split("X");
        int rowLength = sizeArray[0].length();
        int columnsLength = sizeArray[1].length();
        int n = Integer.parseInt(sizeArray[0]); //n = number of lines.
        int m = Integer.parseInt(sizeArray[1]); //m = number of columns.
        String[][] gameBoard = new String[n + 1][m + 1]; //clear game board for first time
        String[][] computergameBoard = new String[n + 1][m + 1]; //
        String[][] guessingBoard = new String[n + 1][m + 1]; //
        String[][] computerguessingBoard = new String[n + 1][m + 1]; //

        //print tiles for all 4 game boards
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                gameBoard[i][j] = "–";
                computergameBoard[i][j] = "–";
                guessingBoard[i][j] = "–";
                computerguessingBoard[i][j] = "–";
            }
        }


        System.out.println("Enter the battleships sizes");
        String battleShipsData = scanner.nextLine(); //getting ships size and amount from player.

        String[] tempDataArray = battleShipsData.split(" "); //split the data in array so that every small array written in (x,y) format;
        String[][] dataArray = new String[tempDataArray.length][2];// x = number of ships that in size y
        for (int i = 0; i < tempDataArray.length; i++) {           // y = size
            dataArray[i] = tempDataArray[i].split("X");
        }

        printBoard(n, m, rowLength, columnsLength, gameBoard);


        for (int j = 0; j < dataArray.length; j++) { // for every size of ship
            for (int k = 0; k < Integer.parseInt(dataArray[j][0]); k++) { //getting the amount of ship in that size
                int sizeOfShip = Integer.parseInt(dataArray[j][1].trim()); // changing size of ship from char to int
                System.out.println("Enter location and orientation for battleship of size " + sizeOfShip); // asking the player for location
                String[] shipLocation = scanner.nextLine().split(", "); // getting location for every ship (and size)
                int x = Integer.parseInt(shipLocation[0]); //converting every argument into int
                int y = Integer.parseInt(shipLocation[1]); //
                int orientation = Integer.parseInt(shipLocation[2]); //


                /*in the following loop we will check for every point if it valid by using the method "validationCheck"
                if isn't valid we will ask the player for new location.
                if valid we will locate the ship by using the correct method:  0 - Horizon / 1 - vertical.*/

                while (true) {
                    if (validationCheck(n, m, x, y, orientation, sizeOfShip, gameBoard) == 0) { //if the point isn't valid
                        shipLocation = scanner.nextLine().split(", "); // getting new location
                        x = Integer.parseInt(shipLocation[0]); //converting every argument into int
                        y = Integer.parseInt(shipLocation[1]); //
                        orientation = Integer.parseInt(shipLocation[2].trim());//
                    } else { // if location verified, insert the ship in the right location.
                        gameBoard = locatePoint(x, y, gameBoard, sizeOfShip, orientation); //locating the ship on map
                        printBoard(n, m, rowLength, columnsLength, gameBoard); // after locating the ship always print current board
                        break;
                    }
                }
            }
        }

        // in the following loop we will set the computer board by random placing of the submarine
        for (int j = 0; j < dataArray.length; j++) { // for every size of ship
            for (int k = 0; k < Integer.parseInt(dataArray[j][0]); k++) { //getting the amount of ship in that size
                int sizeOfCurrentShip = Integer.parseInt(dataArray[j][1]); // setting the size of ship we are randomizing
                int random_X = rnd.nextInt(n); // getting random location for x between 0 to the rows number
                int random_y = rnd.nextInt(m); // getting random location for x between 0 to the cols number
                int random_orientation = rnd.nextInt(2); // getting random location for orientation between 0 and 1


                /*in the following loop we will check for every point if it valid by using the method "computervalidationCheck"
                if isn't valid we will do another random  for a new location.
                if valid we will locate the ship by using the correct method:  0 - Horizon / 1 - vertical.*/

                while (true) {
                    if (computervalidationCheck(n, m, random_X, random_y, random_orientation, sizeOfCurrentShip, computergameBoard) == 0) { //if the point isn't valid
                        random_X = rnd.nextInt(n); // getting random location for x between 0 to the rows number
                        random_y = rnd.nextInt(m); // getting random location for x between 0 to the cols number
                        random_orientation = rnd.nextInt(2); // getting random location for orientation between 0 and 1
                    } else { // if location verified, insert the ship in the right location.
                        computergameBoard = locatePoint(random_X, random_y, computergameBoard, sizeOfCurrentShip, random_orientation); //locating the ship on map
                        break;
                    }
                }
            }
        }

        // Get the amount of battleships
        int r = 0;
        int r_user = 0;
        for (int index = 0; index < dataArray.length; index++) {
            r = r + Integer.parseInt(dataArray[index][0]);
        }
        r_user = r;


        while (r != 0 && r_user != 0) { //starting attacks
            printGuessingBoard(n, m, rowLength, columnsLength, guessingBoard);
            System.out.println("Enter a tile to attack");
            String attackTile = scanner.nextLine(); //getting the attacked tile from player.
            String[] tileLocation = attackTile.split(", ");
            int x = Integer.parseInt(tileLocation[0]); //X - coordinate
            int y = Integer.parseInt(tileLocation[1]); //Y- coordinate

                //while loop for the player attack
                while (validationAttack(n, m, x, y, guessingBoard) == 0) { //if the point isn't valid
                    attackTile = scanner.nextLine(); //getting the attacked tile from player.
                    tileLocation = attackTile.split(", ");
                    x = Integer.parseInt(tileLocation[0]); //X - coordinate
                    y = Integer.parseInt(tileLocation[1]); //Y- coordinate
                }
                    // if location verified, insert the attack in the right location.
                    if (computergameBoard[x][y] == "#") {//checks if there is a part of a submarine in the located point
                        guessingBoard[x][y] = "V";//changes the user's guessing board to "V" where the point is located
                        computergameBoard[x][y] = "X";//changes the computer's board to "X" where the point is located
                        System.out.println("That is a hit!");
                        if (isDrowned(n, m, x, y, computergameBoard) == 1) {//checks if the submarine drowned
                            r = r - 1;
                            System.out.println("The computer's battleship has been drowned, " + r + " more battleships to go!");
                            if (r == 0){
                                break;
                            }
                        }
                    }
                    else {
                        guessingBoard[x][y] = "X";//changes the user's guessing board to "X" where the point is located
                        System.out.println("That is a miss!");
                    }

                // while loop for the computer attack
                int random_X = rnd.nextInt(n); // getting random location for x between 0 to the rows number
                int random_y = rnd.nextInt(m); // getting random location for x between 0 to the columns number
                while (validationComputerAttack(n, m, random_X, random_y, computerguessingBoard) == 0) { //if the point isn't valid
                    random_X = rnd.nextInt(n); // getting random location for x between 0 to the rows number
                    random_y = rnd.nextInt(m); // getting random location for x between 0 to the cols number
                }
                    // if location verified, insert the ship in the right location
                    System.out.println("The computer attacked (" + random_X + ", " + random_y + ")");
                    if (gameBoard[random_X][random_y] == "#") {//checks if there is a part of a submarine in the located point
                        computerguessingBoard[random_X][random_y] = "V";//changes the computer's guessing board to "V" where the point is located
                        gameBoard[random_X][random_y] = "X";//changes the user's game board to "X" where the point is located
                        System.out.println("That is a hit!");
                        if (isDrowned(n, m, random_X, random_y, gameBoard) == 1) {//checks if the submarine has been drowned
                            r_user = r_user - 1;
                            System.out.println("Your battleship has been drowned, you have left " + r_user + " more battleships!");
                        }
                    }
                    else {
                        computerguessingBoard[random_X][random_y] = "X";//changes the computer's guessing board to "X" where the point is located
                        System.out.println("That is a miss!");
                    }
            printBoard(n, m, rowLength, columnsLength, gameBoard);//prints again the user's game board for the next turn
        }

        // End game condition
        if (r == 0) {//checks if there is no  submarines left in the computer's game board
            System.out.println("You won the game!");
        }
        else {
            System.out.println("You lost ):");
        }
    }

    public static void main(String[] args) throws IOException {
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


