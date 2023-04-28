import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    public static Random rnd;

    public static int countDig(int number){ // Method that takes an integer as input and returns the number of digits in that integer
        int count = 0; // Initialize a variable to count the number of digits
        if (number==0) { // If the input number is zero, return 1 since the number of digits in 0 is 1 (i.e., the digit "0")
            return 1;
        }
        while(number!=0){ // While the input number is not equal to zero
            number = number/10;
            count ++;
        }
        return count; // Return the final digit count
    }
    public static void Spacing(int sub){ // Method that takes an integer as input and prints that number of spaces
        for(int index = 0; index < sub; index++ ) { // Loop from 0 up to sub (exclusive), incrementing index by 1 on each iteration
            System.out.print(" ");
        }
    }
    public static int validationAttack(int n, int m, int x, int y, String[][] guessingBoard){ // Method that checks if an attack on the game board is valid
        if ((x >= n) || (x < 0) || (y >= m) || (y < 0)) { // Check if the attack point is within the boundaries of the board
            System.out.println("Illegal tile, try again!"); // If the attack point is outside the board, print an error message
            return 0; // Return 0 to indicate that the attack was invalid
        }
        if (guessingBoard[x][y] != "–") { // Check if the point has already been attacked by looking at the corresponding tile in the guessing board array
            System.out.println("Tile already attacked, try again!"); // If the point has already been attacked, print an error message
            return 0; // Return 0 to indicate that the attack was invalid
        } else { // If the attack is valid
            return 1; // Return 1 to indicate that the attack was valid
        }
    }

    public static int validationComputerAttack(int n, int m, int x, int y, String[][] computerguessingBoard) { // Method that checks if an attack on the game board is valid
        if ((x >= n) || (x < 0) || (y >=m) || (y < 0)) { // Check if the attack point is within the boundaries of the board

            return 0; // Return 0 to indicate that the attack was invalid
        }
        if (computerguessingBoard[x][y] != "–") { // Check if the point has already been attacked by looking at the corresponding tile in the guessing board array

            return 0; // Return 0 to indicate that the attack was invalid
        } else { // If the attack is valid
            return 1; // Return 1 to indicate that the attack was valid
        }
    }

    public static int isDrowned(int n, int m, int x, int y, String[][] currentgameBoard) {
        /* The method takes in five parameters:
          n and m are the dimensions of the game board
          x and y are the coordinates of the attacked point
          currentgameBoard is the 2D array representing the game board **/


        int result = 1; // Initialize the result variable to 1, indicating that the submarine is sunk.

        // Search to the right of the attacked coordinate.
        for (int right = y + 1; right < m; right++) {
            if (currentgameBoard[x][right] == "#") { // If a part of the submarine is found adjacent to the right, the submarine is not sunk
                return 0; // Return 0 to indicate submarine is not sunk
            }
            if (currentgameBoard[x][right] == "–") { // If the loop reaches a point on the game board that is not part of the submarine, exit the loop because we have checked all adjacent points to the right of the attacked coordinate.
                        break;
                    }
            }

        // Search to the left of the attacked coordinate.
        for (int left = y - 1; left >= 0; left--) { // If a part of the submarine is found adjacent to the left, the submarine is not sunk
            if (currentgameBoard[x][left] == "#") { // Returns 0 if there is a submarine part adjacent to the left that not yey attacked
                return 0; // Return 0 to indicate submarine is not sunk
            }
            if (currentgameBoard[x][left] == "–") { // If the loop reaches a point on the game board that is not part of the submarine, exit the loop because we have checked all adjacent points to the left of the attacked coordinate.
                break;
            }
        }

        // Search above the attacked coordinate.
        for (int up = x + 1; up < n; up++) {
            if (currentgameBoard[up][y] == "#") { // If a part of the submarine is found adjacent above the attacked coordinate, the submarine is not sunk.
                return 0; // Return 0 to indicate the submarine is not sunk.
            }
            if (currentgameBoard[up][y] == "–") { // If the loop reaches a point on the game board that is not part of the submarine, exit the loop because we have checked all adjacent points above the attacked coordinate.
                break;
            }
        }

        // Search below the attacked coordinate.
        for (int down = x - 1; down >= 0; down--) {
            if (currentgameBoard[down][y] == "#") { // If a part of the submarine is found adjacent below the attacked coordinate, the submarine is not sunk.
                return 0; // Return 0 to indicate this.
            }
            if (currentgameBoard[down][y] == "–") { // If the loop reaches a point on the game board that is not part of the submarine, exit the loop because we have checked all adjacent points below the attacked coordinate.
                break;
            }
        }
        return result; // If none of the above conditions are met, the submarine is sunk. Return 1 to indicate this.
    }

    public static int validationCheck(int n, int m, int x, int y, int orientation, int size, String[][] gameBoard) { // Method that checks if the positioning of current battleship is valid - player selection
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

            if (orientation == 1) { //if battleship horizontally
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

        return 1; // if all test return positive, the location it valid.
    }

    public static int computervalidationCheck(int n, int m, int x, int y, int orientation, int size, String[][] gameBoard){ // Method that checks if the positioning of current battleship is valid - computer selection
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

    public static void printBoard(int n, int m, int rowLenght, int columnsLength, String[][] String) { //Method that gets row and columns numbers to print the board game and uses the las row and column number to match the right spacing
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
                System.out.print(" " + String[j][k]); // print tiles
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printGuessingBoard(int n, int m, int rowLenght, int columnsLength, String[][] String) { //Method that gets row and columns numbers to print the guessing board game and uses the las row and column number to match the right spacing
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
            subn = rowLenght - countDig(j); // integer that sends to spacing method
            Spacing(subn); // call for spacing function (description in the method)
            System.out.print(j); // print rows numbers
            for (int k = 0; k < m; k++) {
                System.out.print(" " + String[j][k]); // print tiles
            }
            System.out.println();
        }
        System.out.println();
    }

    public static String[][] locatePoint(int x, int y, String[][] string, int size, int orientation) { //Method insert '#' after a coordinate selected by the player or computer validated for battleship assignment
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
        return string; // return the board game updated with # in the designated tiles
    }


    public static void battleshipGame() {
        /* This method manage the game using internal logic and calling for external method as described below:
          received user input for game board size
          received user input for battleships amount and size
          call the printBoard method (refer to methode for further explanation)
          call the validationCheck method (refer to methode for further explanation)
          call the computervalidationCheck method (refer to methode for further explanation)
          call the printGuessingBoard (refer to methode for further explanation)
          call the locatePoint methode (refer to methode for further explanation)
          **/
        System.out.println("Enter the board size");
        String boardSize = scanner.nextLine(); //getting the board size from player.
        String[] sizeArray = boardSize.split("X"); // Split the input string using "X" character as pivot point
        int rowLength = sizeArray[0].length();
        int columnsLength = sizeArray[1].length();
        int n = Integer.parseInt(sizeArray[0]); //n = number of lines.
        int m = Integer.parseInt(sizeArray[1]); //m = number of columns.
        String[][] gameBoard = new String[n + 1][m + 1]; //clear game board for first time
        String[][] computergameBoard = new String[n + 1][m + 1]; //clear computer game board for first time
        String[][] guessingBoard = new String[n + 1][m + 1]; //clear player guessing game board for first time
        String[][] computerguessingBoard = new String[n + 1][m + 1]; //clear computer guessing game board for first time
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
                int y = Integer.parseInt(shipLocation[1]); // converting every argument into int
                int orientation = Integer.parseInt(shipLocation[2]); // converting every argument into int


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


                /*in the following loop we will check for every point if it valid by using the method "validationCheck"
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
            String[] tileLocation = attackTile.split(", "); // Split the input string on "','" character
            int x = Integer.parseInt(tileLocation[0]); //X - coordinate
            int y = Integer.parseInt(tileLocation[1]); //Y- coordinate

                //while loop for the player attack
                while (validationAttack(n, m, x, y, guessingBoard) == 0) { //if the point isn't valid
                    attackTile = scanner.nextLine(); //getting the attacked tile from player.
                    tileLocation = attackTile.split(", "); // Split the input string on "','" character
                    x = Integer.parseInt(tileLocation[0]); //X - coordinate
                    y = Integer.parseInt(tileLocation[1]); //Y- coordinate
                }
                    // if location verified, insert the attack in the right location.
                    if (computergameBoard[x][y] == "#") {//checks if there is a part of a submarine in the located point
                        guessingBoard[x][y] = "V";//changes the user's guessing board to "V" where the point is located
                        computergameBoard[x][y] = "X";////changes the computer's board to "X" where the point is located
                        System.out.println("That is a hit!");
                        if (isDrowned(n, m, x, y, computergameBoard) == 1) {//checks if the submarine has be drowned
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
                int random_y = rnd.nextInt(m); // getting random location for x between 0 to the cols number
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


