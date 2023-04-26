import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static Scanner scanner;
    public static Random rnd;
    public static int validationAttack(int n, int m, int x, int y, String[][] guessingBoard) {
        if ((x > n) || (x < 0) || (y > m) || (y < 0)) { // checks if the point is within the board boundaries.
            System.out.println("Illegal tile,try again!");
            return 0;
        }
        if (guessingBoard[x][y] !="–") {
            System.out.println("Tile already attacked,try again!");
            return 0;
        } else {
            return 1;
        }
    }

    public static int validationComputerAttack(int n, int m, int x, int y, String[][] conputerguessingboard) {
        if ((x > n) || (x < 0) || (y > m) || (y < 0)) { // checks if the point is within the board boundaries.

            return 0;
        }
        if (conputerguessingboard[x][y] != "-") {

            return 0;
        } else {
            return 1;
        }
    }

    public static int isDrowend(int n, int m, int x, int y, String[][] computergameBoard) { //** maybe change to while
        int result = 1;
        for (int right = x + 1; right < n; right++) {
            if (computergameBoard[right][y] == "#") {
                result = 0;
                if (computergameBoard[right][y] == "-") {
                    break;
                }
            }
        }
        for (int left = x - 1; left >= 0; left--) {
            if (computergameBoard[left][y] == "#") {
                result = 0;
            }
            if (computergameBoard[left][y] == "-") {
                break;
            }
        }
        for (int up = y + 1; up < m; up++) {
            if (computergameBoard[x][up] == "#") {
                result = 0;
            }
            if (computergameBoard[x][up] == "-") {
                break;
            }

        }
        for (int down = y - 1; down <= 0; down--) {
            if (computergameBoard[x][down] == "#") {
                result = 0;
            }
            if (computergameBoard[x][down] == "-") {
                break;
            }
        }
        return result;
    }
    public static int validationCheck(int n, int m, int x, int y, int orientation, int size, String[][] gameBoard) {
        if ((orientation != 0) && (orientation != 1)) { // checks if the ORIENTATION is o or 1 as needed.
           System.out.println("Illegal orientation,try again!");
            return 0;
        }
        if ((x > n) || (x < 0) || (y > m) || (y < 0)) { // checks if the point is within the board boundaries.
            System.out.println("Illegal tile,try again!");
            return 0;
        }
        if (orientation == 0) { // if the ship horizontally, checks if ship within the board boundaries.
            if (y + size > m) {
                System.out.println("Battleship exceeds the boundaries of the board,try again!");
                return 0;
            }
        }
        if (orientation == 1) { // if the ship vertically, checks if ship within the board boundaries.
            if (x + size > n) {
                System.out.println("Battleship exceeds the boundaries of the board,try again!");
                return 0;
            }
        }
        for (int i = 0; i < size; i++) { // checks if the Battleship overlaps another battleship.
            if (orientation == 0) {
                if (gameBoard[x][y + i].equals("#")) { //** if battleship horizontally
                    System.out.println("Battleship overlaps another battleship,try again!");
                    return 0;
                }
            } else {
                if (gameBoard[x + i][y].equals("#")) { //** if battleship vertically
                    System.out.println("Battleship overlaps another battleship,try again!");
                    return 0;
                }
            }
        }
        //**in the following for loop we will check every 3 point from the left side of every point on the ship.
        //* two left diagonal points and one straight left point.
        //* we will divide the for loop into two cases; horizontally and vertically.
        //* in every case, we will first ensure if the point we check is valid.
        //* if so, we will check if the point is already occupied.
        //* if not, we will ignore it and continue.
        for (int j = 0; j < size + 2; j++) {
            int minDistanceY = y + j - 1; //declare a variable for y min distance from another ship
            int minDistanceX = x + j - 1; //declare a variable for x min distance from another ship
            if (orientation == 0) { //**if battleship horizontally
                if ((n > (x - 1) && (x - 1) >= 0) && (m > minDistanceY) && ((minDistanceY) >= 0)) {
                    if (gameBoard[x - 1][minDistanceY].equals("#")) {
                        System.out.println("Adjacent battleship detected,try again!");
                        return 0;
                    }
                }
                if (((n > x) && (m > (minDistanceY)) && (minDistanceY >= 0))) {
                    if (gameBoard[x][minDistanceY].equals("#")) {
                        System.out.println("Adjacent battleship detected,try again!");
                        return 0;
                    }
                }
                if ((n > (x + 1)) && (m > minDistanceY) && (minDistanceY >= 0)) {
                    if (gameBoard[x + 1][minDistanceY].equals("#")) {
                        System.out.println("Adjacent battleship detected,try again!");
                        return 0;
                    }
                }
            }

            if (orientation == 1) { //**if battleship horizontally
                if ((n > (minDistanceX) && (minDistanceX) >= 0) && (m > (y - 1) && (y - 1) >= 0)) {
                    if (gameBoard[minDistanceX][y - 1].equals("#")) {
                        System.out.println("Adjacent battleship detected,try again!");
                        return 0;
                    }
                }
                if ((n > (minDistanceX) && (minDistanceX) >= 0) && (m > y)) {
                    if (gameBoard[minDistanceX][y].equals("#")) {
                        System.out.println("Adjacent battleship detected,try again!");
                        return 0;
                    }
                }
                if ((n > (minDistanceX) && (minDistanceX) >= 0) && (m > (y + 1) && (y + 1) >= 0)) {
                    if (gameBoard[minDistanceX][y + 1].equals("#")) {
                        System.out.println("Adjacent battleship detected,try again!");
                        return 0;
                    }
                }
            }
        }

        return 1; // if all test return positive, we know that the location the player gave us it valid.
    }

    public static int validationCheckforComputer(int n, int m, int x, int y, int orientation, int size, String[][] gameBoard) {
        if ((orientation != 0) && (orientation != 1)) { // checks if the ORIENTATION is o or 1 as needed.
            return 0;
        }
        if ((x > n) || (x < 0) || (y > m) || (y < 0)) { // checks if the point is within the board boundaries.
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
                if (gameBoard[x][y + i].equals("#")) { //** if battleship horizontally
                    return 0;
                }
            } else {
                if (gameBoard[x + i][y].equals("#")) { //** if battleship vertically
                    return 0;
                }
            }
        }
        //**in the following for loop we will check every 3 point from the left side of every point on the ship.
        //* two left diagonal points and one straight left point.
        //* we will divide the for loop into two cases; horizontally and vertically.
        //* in every case, we will first ensure if the point we check is valid.
        //* if so, we will check if the point is already occupied.
        //* if not, we will ignore it and continue.
        for (int j = 0; j < size + 2; j++) {
            int minDistanceY = y + j - 1; //declare a variable for y min distance from another ship
            int minDistanceX = x + j - 1; //declare a variable for x min distance from another ship
            if (orientation == 0) { //**if battleship horizontally
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

            if (orientation == 1) { //**if battleship horizontally
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


        return 1; // if all test return positive, we know that the location the player gave us it valid.
    }
    public static void printBoard(int n, int m, String[][] String){//prints the board for every stage of game
        System.out.println("Your current game board:");
        System.out.print(" ");
        for(int i = 0; i < m; i++){
            System.out.print(" " + (i));
        }
        System.out.println();
        for(int j = 0; j < n; j++){
            System.out.print(j);
            for(int k = 0; k < m; k++){
                System.out.print(" " + String[j][k]);
            }
            System.out.println();
        }
    }
    public static void printGuessingBoard(int n, int m, String[][] String){//prints the board for every stage of game
        System.out.print(" ");
        for(int i = 0; i < m; i++){
            System.out.print(" " + (i));
        }
        System.out.println();
        for(int j = 0; j < n; j++){
            System.out.print(j);
            for(int k = 0; k < m; k++){
                System.out.print(" " + String[j][k]);
            }
            System.out.println();
        }
    }
    public static void printComputerBoard(int n, int m, String[][] String){//prints the board game of the compuer
        System.out.println("The computer current game board:");
        System.out.print(" ");
        for(int i = 0; i < m; i++){
            System.out.print(" " + (i));
        }
        System.out.println();
        for(int j = 0; j < n; j++){
            System.out.print(j);
            for(int k = 0; k < m; k++){
                System.out.print(" " + String[j][k]);
            }
            System.out.println();
        }
    }
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
    public static void battleshipGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the board size");
        String boardSize = scanner.nextLine(); //getting the board size from player.
        String[] sizeArray = boardSize.split("X"); // Split the input string on "X" character
        int n = Integer.parseInt(sizeArray[0]); //n = number of lines.
        int m = Integer.parseInt(sizeArray[1]); //m = number of columns.
        String[][] gameBoard = new String[n + 1][m + 1]; //setting the clear game board for the first time
        String[][] computergameBoard = new String[n + 1][m + 1]; //setting the computer game board for the first time
        String[][] guessingBoard = new String[n + 1][m + 1];
        String[][] computerGuessingboard = new String[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                gameBoard[i][j] = "–";
                computergameBoard[i][j] = "–";
                guessingBoard[i][j] = "–";
                computerGuessingboard[i][j] = "–";
            }
        }
        printBoard(n, m, gameBoard);

        System.out.println("Enter the battleShips sizes");
        String battleShipsData = scanner.nextLine(); //getting the ships size and amount from player.

        String[] tempDataArray = battleShipsData.split(" "); //split the data in array so that every small array written in (x,y) format;
        String[][] dataArray = new String[tempDataArray.length][2];// x = number of ships that in size y
        for (int i = 0; i < tempDataArray.length; i++) {           // y = size
            dataArray[i] = tempDataArray[i].split("X");
        }


        for (int j = 0; j < dataArray.length; j++) { // for every size of ship
            for (int k = 0; k < Integer.parseInt(dataArray[j][0]); k++) { //getting the amount of ship in that size
                int sizeOfShip = Integer.parseInt(dataArray[j][1].trim()); // changing size of ship from char to int
                System.out.println("Enter location and orientation for battleships of size " + sizeOfShip); // asking the player for location
                String[] shipLocation = scanner.nextLine().split(", "); // getting location for every ship (and size)
                int x = Integer.parseInt(shipLocation[0]); //converting every argument into int
                int y = Integer.parseInt(shipLocation[1]); //
                int orientation = Integer.parseInt(shipLocation[2]); //

                //**in the following loop we will check for every point if it valid by using the method "validationCheck"
                //*if isn't valid we will ask the player for new location.
                //*if valid we will locate the ship by using the correct method:  0 - Horizon / 1 - vertical.
                while (true) {
                    if (validationCheck(n, m, x, y, orientation, sizeOfShip, gameBoard) == 0) { //if the point isn't valid
                        shipLocation = scanner.nextLine().split(", "); // getting new location
                        x = Integer.parseInt(shipLocation[0]); //converting every argument into int
                        y = Integer.parseInt(shipLocation[1]); //
                        orientation = Integer.parseInt(shipLocation[2].trim());//
                    } else { // if location verified, insert the ship in the right location.
                        gameBoard = locatePoint(x, y, gameBoard, sizeOfShip, orientation); //locating the ship on map
                        printBoard(n, m, gameBoard); // after locating the ship always print current board
                        break;
                    }
                }
            }
        }

        //** in the following loop we will set the computer board by random placing of the submarine
        for (int j = 0; j < dataArray.length; j++) { // for every size of ship
            for (int k = 0; k < Integer.parseInt(dataArray[j][0]); k++) { //getting the amount of ship in that size
                int sizeOfCurrentShip = Integer.parseInt(dataArray[j][1]); // setting the size of ship we are radoming
                Random random = new Random();
                int random_X = random.nextInt(n); // getting random location for x between 0 to the rows number
                int random_y = random.nextInt(m); // getting random location for x between 0 to the cols number
                int random_orientation = random.nextInt(2); // getting random location for oriention between 0 to 1

                //**in the following loop we will check for every point if it valid by using the method "validationCheck"
                //*if isn't valid we will do another random  for a new location.
                //*if valid we will locate the ship by using the correct method:  0 - Horizon / 1 - vertical.
                while (true) {
                    if (validationCheckforComputer(n, m, random_X, random_y, random_orientation, sizeOfCurrentShip, computergameBoard) == 0) { //if the point isn't valid
                        random_X = random.nextInt(n); // getting random location for x between 0 to the rows number
                        random_y = random.nextInt(m); // getting random location for x between 0 to the cols number
                        random_orientation = random.nextInt(2); // getting random location for oriention between 0 to 1
                    } else { // if location verified, insert the ship in the right location.
                        computergameBoard = locatePoint(random_X, random_y, computergameBoard, sizeOfCurrentShip, random_orientation); //locating the ship on map
                        printComputerBoard(n, m, computergameBoard); // after locating the ship print current board
                        break;
                    }
                }
            }
        }

        //** Get the amount of battleships
        int r = 0;
        int r_computer = 0;
        for (int index = 0; index < dataArray.length; index++) {
            r = r + Integer.parseInt(dataArray[index][0]);
        }
        r_computer = r;

        while (r != 0 && r_computer != 0) { //starting attacks
            System.out.println("Your current guessing board:");
            printGuessingBoard(n, m, guessingBoard);
            System.out.println("Enter a tile to attack");
            String attackTile = scanner.nextLine(); //getting the attcket tile from player.
            String[] tileLocation = attackTile.split(", "); // Split the input string on "','" character
            int x = Integer.parseInt(tileLocation[0]); //X - coordinate
            int y = Integer.parseInt(tileLocation[1]); //Y- coordinate

            while (true) { // that is a while loop for the player attack
                if (validationAttack(n, m, x, y, guessingBoard) == 0) { //if the point isn't valid
                    attackTile = scanner.nextLine(); //getting the attcket tile from player.
                    tileLocation = attackTile.split(", "); // Split the input string on "','" character
                    x = Integer.parseInt(tileLocation[0]); //X - coordinate
                    y = Integer.parseInt(tileLocation[1]); //Y- coo
                } else { // if location verified, insert the attack in the right location.
                    if (computergameBoard[x][y] == "#") {
                        guessingBoard[x][y] = "V";
                        computergameBoard[x][y] = "X";
                        System.out.println("That is a hit!");
                        if (isDrowend(n, m, x, y, computergameBoard) == 1) {
                            r = r - 1;
                            System.out.println("The computer's battleship has been drowned " + r + " more battleships to go!");
                        }
                    } else {
                        guessingBoard[x][y] = "X";
                        System.out.println("That is a miss!");
                    }
                }
                break;
            }
            while (true) { // that is a while loop for the player attack
                Random random = new Random();
                int random_X = random.nextInt(n); // getting random location for x between 0 to the rows number
                int random_y = random.nextInt(m); // getting random location for x between 0 to the cols number
                if (validationComputerAttack(n, m, random_X, random_y, computergameBoard) == 0) { //if the point isn't valid
                    random_X = random.nextInt(n); // getting random location for x between 0 to the rows number
                    random_y = random.nextInt(m); // getting random location for x between 0 to the cols number
                } else { // if location verified, insert the ship in the right location.
                    System.out.println("The computer attacked (" + random_X + ", " + random_y + ")");
                    if (gameBoard[random_X][random_y] == "#") {
                        computerGuessingboard[random_X][random_y] = "V";
                        gameBoard[random_X][random_y] = "X";
                        System.out.println("That is a hit!");
                        if (isDrowend(n, m, random_X, random_y, gameBoard) == 1) {
                            r_computer = r_computer - 1;
                            System.out.println("Your battleship has been drowned, " + r + " more battleships!");
                        }
                    } else {
                        computerGuessingboard[random_X][random_y] = "X";
                        System.out.println("That is a miss!");
                    }
                }
                break;
            }
            System.out.println("Your Current game board:");
            printBoard(n, m, gameBoard);
        }
        if (r_computer == 0) {
            System.out.println("You won the game!");
        } else {
            System.out.println("You lost ):");
        }
    }

    // TODO: Add your code here (and add more methods).


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

    public static void battleshipGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the board size");
        String boardSize = scanner.nextLine(); //getting the board size from player.
        String[] sizeArray = boardSize.split("X"); // Split the input string on "X" character
        int n = Integer.parseInt(sizeArray[0]); //n = number of lines.
        int m = Integer.parseInt(sizeArray[1]); //m = number of columns.
        String[][] gameBoard = new String[n + 1][m + 1]; //setting the clear game board for the first time
        String[][] computergameBoard = new String[n + 1][m + 1]; //setting the computer game board for the first time
        String[][] guessingBoard = new String[n + 1][m + 1];
        String[][] computerGuessingboard = new String[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                gameBoard[i][j] = "–";
                computergameBoard[i][j] = "–";
                guessingBoard[i][j] = "–";
                computerGuessingboard[i][j] = "–";
            }
        }
        printBoard(n, m, gameBoard);

        System.out.println("Enter the battleShips sizes");
        String battleShipsData = scanner.nextLine(); //getting the ships size and amount from player.

        String[] tempDataArray = battleShipsData.split(" "); //split the data in array so that every small array written in (x,y) format;
        String[][] dataArray = new String[tempDataArray.length][2];// x = number of ships that in size y
        for (int i = 0; i < tempDataArray.length; i++) {           // y = size
            dataArray[i] = tempDataArray[i].split("X");
        }


        for (int j = 0; j < dataArray.length; j++) { // for every size of ship
            for (int k = 0; k < Integer.parseInt(dataArray[j][0]); k++) { //getting the amount of ship in that size
                int sizeOfShip = Integer.parseInt(dataArray[j][1].trim()); // changing size of ship from char to int
                System.out.println("Enter location and orientation for battleships of size " + sizeOfShip); // asking the player for location
                String[] shipLocation = scanner.nextLine().split(", "); // getting location for every ship (and size)
                int x = Integer.parseInt(shipLocation[0]); //converting every argument into int
                int y = Integer.parseInt(shipLocation[1]); //
                int orientation = Integer.parseInt(shipLocation[2]); //

                //**in the following loop we will check for every point if it valid by using the method "validationCheck"
                //*if isn't valid we will ask the player for new location.
                //*if valid we will locate the ship by using the correct method:  0 - Horizon / 1 - vertical.
                while (true) {
                    if (validationCheck(n, m, x, y, orientation, sizeOfShip, gameBoard) == 0) { //if the point isn't valid
                        shipLocation = scanner.nextLine().split(", "); // getting new location
                        x = Integer.parseInt(shipLocation[0]); //converting every argument into int
                        y = Integer.parseInt(shipLocation[1]); //
                        orientation = Integer.parseInt(shipLocation[2].trim());//
                    } else { // if location verified, insert the ship in the right location.
                        gameBoard = locatePoint(x, y, gameBoard, sizeOfShip, orientation); //locating the ship on map
                        printBoard(n, m, gameBoard); // after locating the ship always print current board
                        break;
                    }
                }
            }
        }

            //** in the following loop we will set the computer board by random placing of the submarine
            for (int j = 0; j < dataArray.length; j++) { // for every size of ship
                for (int k = 0; k < Integer.parseInt(dataArray[j][0]); k++) { //getting the amount of ship in that size
                    int sizeOfCurrentShip = Integer.parseInt(dataArray[j][1]); // setting the size of ship we are radoming
                    Random random = new Random();
                    int random_X = random.nextInt(n); // getting random location for x between 0 to the rows number
                    int random_y = random.nextInt(m); // getting random location for x between 0 to the cols number
                    int random_orientation = random.nextInt(2); // getting random location for oriention between 0 to 1

                //**in the following loop we will check for every point if it valid by using the method "validationCheck"
                //*if isn't valid we will do another random  for a new location.
                //*if valid we will locate the ship by using the correct method:  0 - Horizon / 1 - vertical.
                while (true) {
                    if (validationCheckforComputer(n, m, random_X, random_y, random_orientation, sizeOfCurrentShip, computergameBoard) == 0) { //if the point isn't valid
                        random_X = random.nextInt(n); // getting random location for x between 0 to the rows number
                        random_y = random.nextInt(m); // getting random location for x between 0 to the cols number
                        random_orientation = random.nextInt(2); // getting random location for oriention between 0 to 1
                    } else { // if location verified, insert the ship in the right location.
                        computergameBoard = locatePoint(random_X, random_y, computergameBoard, sizeOfCurrentShip, random_orientation); //locating the ship on map
                        printComputerBoard(n, m, computergameBoard); // after locating the ship print current board
                        break;
                    }
                }
            }
        }

        //** Get the amount of battleships
        int r = 0;
        int r_computer = 0;
        for (int index = 0; index < dataArray.length; index++) {
            r = r + Integer.parseInt(dataArray[index][0]);
        }
        r_computer = r;

        while (r != 0 && r_computer != 0) { //starting attacks
            System.out.println("Your current guessing board:");
            printBoard(n, m, guessingBoard);
            System.out.println("Enter a tile to attack");
            String attackTile = scanner.nextLine(); //getting the attcket tile from player.
            String[] tileLocation = attackTile.split(","); // Split the input string on "','" character
            int x = Integer.parseInt(tileLocation[0]); //X - coordinate
            int y = Integer.parseInt(tileLocation[1]); //Y- coordinate

            while (true) { // that is a while loop for the player attack
                if (validationAttack(n, m, x, y, guessingBoard) == 0) { //if the point isn't valid
                    attackTile = scanner.nextLine(); //getting the attcket tile from player.
                    tileLocation = attackTile.split(","); // Split the input string on "','" character
                    x = Integer.parseInt(tileLocation[0]); //X - coordinate
                    y = Integer.parseInt(tileLocation[1]); //Y- coo
                } else { // if location verified, insert the attack in the right location.
                    if (computergameBoard[x][y] == "#") {
                        guessingBoard[x][y] = "V";
                        computergameBoard[x][y] = "X";
                        System.out.println("That is a hit!");
                        if (isDrowend(n, m, x, y, computergameBoard) == 1) {
                            r = r - 1;
                            System.out.println("The computer's battleship has been drowned " + r + " more battleships to go!");
                        }
                    } else {
                        guessingBoard[x][y] = "X";
                        System.out.println("That is a miss!");
                    }
                }
                break;
            }
            while (true) { // that is a while loop for the player attack
                Random random = new Random();
                int random_X = random.nextInt(n); // getting random location for x between 0 to the rows number
                int random_y = random.nextInt(m); // getting random location for x between 0 to the cols number
                if (validationComputerAttack(n, m, x, y, gameBoard) == 0) { //if the point isn't valid
                    random_X = random.nextInt(n); // getting random location for x between 0 to the rows number
                    random_y = random.nextInt(m); // getting random location for x between 0 to the cols number
                } else { // if location verified, insert the ship in the right location.
                    System.out.println("The computer attacked (" + random_X + ", " + random_y + ")");
                    if (gameBoard[random_X][random_y] == "#") {
                        computerGuessingboard[random_X][random_y] = "V";
                        gameBoard[random_X][random_y] = "X";
                        System.out.println("That is a hit!");
                        if (isDrowend(n, m, random_X, random_y, gameBoard) == 1) {
                            r_computer = r_computer - 1;
                            System.out.println("Your battleship has been drowned, " + r + " more battleships!");
                        }
                    } else {
                        computerGuessingboard[random_X][random_y] = "X";
                        System.out.println("That is a miss!");
                    }
                }
                break;
            }
            System.out.println("Your Current game board:");
            printBoard(n, m, gameBoard);
        }
        if (r_computer == 0) {
            System.out.println("You won the game!");
        } else {
            System.out.println("You lost ):");
        }
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



