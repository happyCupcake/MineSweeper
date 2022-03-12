 /*
MANDATORY QUESTION ANSWERED HERE
=================================
=================================
A robot can be software(like a chat specialist), or a physical thing that will 
help a human in a certain way(like a car). A software robot can be manipulated 
usingcode. A hardware robot can be manipulated using microcontrollers, which 
can convert software to motion. Example; I have programmed an Arduino, which has
 I/O ports, that can convert software instructions into voltage, that can in turn 
 operate motors. Motors, in addition to rotation, can do precise angle measurement, 
 as well as convert rotation into horizontal or vertical motion using gears and gear 
 racks. Another way a robot acts is using sensors. Sensors convert real world 
 physical information like touch, sound, light into electrical signals which can then 
 be read by software and used as input to redirect the motion of the robot.
==================================
==================================
*/
import java.lang.Math;
public class MrinaliMinesweeper {
    

    public static void main(String[] args) {
        // Uncomment this to test my code
        // PLEASE TEST MY CODE!!!!!!
        // testMineSweeper();

        if ( args.length < 3 ){
            System.out.println("MrinaliMinesweeper: Requires 3 arguments m,n,k");
            return;
        }
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        
        

        MineSweeper game = new MineSweeper(m,n,k);
        
        game.addMines();
        game.addMineCount();
        game.displayBoard();
    }

    // Use this function to test my code
    static void testMineSweeper(){
        // test board from google doc
        char [][]expectedTestBoard = {
            {'0','1','*','1','0','0','0','1','*'},
            {'1','3','2','2','0','0','0','1','1'},
            {'*','2','*','1','0','0','1','1','1'},
            {'1','2','2','2','1','0','1','*','1'},
            {'0','1','2','*','1','0','1','1','1'},
            {'1','2','*','3','3','1','1','0','0'},
            {'1','*','3','*','2','*','1','0','0'},
            {'1','1','2','1','2','1','1','0','0'},
            {'0','0','0','0','0','0','0','0','0'}
        };

        // empty testboard with exactly same mines as the google doc
        char [][]testBoard = {
            {'0','0','*','0','0','0','0','0','*'},
            {'0','0','0','0','0','0','0','0','0'},
            {'*','0','*','0','0','0','0','0','0'},
            {'0','0','0','0','0','0','0','*','0'},
            {'0','0','0','*','0','0','0','0','0'},
            {'0','0','*','0','0','0','0','0','0'},
            {'0','*','0','*','0','*','0','0','0'},
            {'0','0','0','0','0','0','0','0','0'},
            {'0','0','0','0','0','0','0','0','0'}
        };
        
        MineSweeper testGame = new MineSweeper(9,9,10, testBoard);
        // Note in test we are not calling addMines because we want to use
        // the mines from the test input
        //testGame.addMines();
        testGame.addMineCount();
        testGame.displayBoard();
        char [][]gameBoard = testGame.getBoard();
        for (int i=0; i<9; i++){
            for (int j=0;j<9; j++) {
                if (expectedTestBoard[i][j]!= gameBoard[i][j]) {
                    System.out.println("Test Failed, Expected output doesnt match");
                    return;
                }
            }
        }
        System.out.println("Test PASSED!");
    }
}

// This is the main class which implements the MineSweeper Game
// Ideally this should be in its own file
class MineSweeper {
    private char[][] board;
    int m,n,k;

    // The main constructor: checks inputs, intitialises board with 0s
    MineSweeper(int m, int n, int k){
        if (m<=0 || n <=0) {
            System.out.printf("m= %d, n=%d, m and n both should greater than zero\n",m,n);
            return;
        }

        if (k > m*n){
            System.out.printf("Field dimensions are %d and the number of mines" + 
                " you requested is %d. There are too many mines.\n",m*n, k);
            return ;
        }
        this.m=m;
        this.n=n;
        this.k=k;
        this.board = new char[m][n];
        // Initialising board with 0s
        for (int i = 0; i < m; i++){ 
            for (int j = 0; j < n; j++){ 
                board[i][j] = '0';
            }
        }

    }

    // Second constructor created for testing purposes
    MineSweeper(int m, int n, int k, char [][]board){
        this.m=m;
        this.n=n;
        this.k=k;
        this.board = board; 
    }

    // generate exactly k mines in the board using myRandom function
    void addMines(){
        int totalmines = 0;

        while(totalmines < k){
            // generate a random number between 0 and m(m exluded)
            int x = myRandom(m);
            int y = myRandom(n);
            if (board[x][y] != '*'){
                board[x][y] = '*';
                totalmines += 1;
            }
        }
    }

    // Count the number of neighboring mines for each cell which is not a mine
    // and add those counts to the board
    void addMineCount() {
        for (int i = 0; i < m; i++){ 
            for (int j = 0; j < n; j++){ 
                if (board[i][j] != '*'){
                    board[i][j] = countMines(i,j);
                }   
            }
        }

    }

    // return the board, only used for testing purposes
    char [][] getBoard(){
        return board;
    }

    // Display the board containing mines and their counts  as a m by n grid
    void displayBoard(){
        for (int i = 0; i < m; i++){ 
            for (int j = 0; j < n; j++) 
                System.out.print( board[i][j]+ "  ");
            System.out.println("\n");
        }
        System.out.printf("m = %d, n = %d, k = %d \n",m,n,k);
    }

    // A random function using Math.random that generates a random number between 0,max
    // Math.random returns a random number(r) r>=0 and r<1.0
    // Scale the result of Math.random by multiplying it by max
    int myRandom(double max) 
    {
        double r  = Math.random(); 
        double result =  r * max;
        return (int)result;
    }
    
    // Fourth Method; Counts surrounding mines of the grid space
    char countMines(int x, int y){
        int surroundingmines = 0;
        //System.out.println("In minecount");
        //displayBoard();
        // top
        if ((y-1) >= 0 && board[x][y-1] == '*' ){
            // System.out.println("found top");
            surroundingmines += 1;
        }
        //bottom
        if ((y+1) < n && board[x][y+1] == '*'){
            // System.out.println("found bottom");
            surroundingmines += 1;
        }
        //right
        if ((x+1) < m && board[x+1][y] == '*'){
            // System.out.println("found right");
            surroundingmines += 1;
        }
        //left
        if ((x-1) >= 0 && board[x-1][y] == '*'){
            // System.out.println("found left");
            surroundingmines += 1;
        }

        //top-left
        if ((x-1) >= 0 && (y-1) >= 0 && board[x-1][y-1] == '*'){
            // System.out.println("found top-left");
            surroundingmines += 1;
        }
        //top-right
        if ((x+1) < m &&  (y-1) >= 0 && board[x+1][y-1] == '*'){
            // System.out.println("found top-right");
            surroundingmines += 1;
        }
        //bottom-left
        if ((x-1) >= 0 && (y+1) < n && board[x-1][y+1] == '*'){
            // System.out.println("found bottom-left");
            surroundingmines += 1;
        }
        //bottom-right
        if ((y+1) < n && (x+1) < m && board[x+1][y+1] == '*'){
            //  System.out.println("found bottom-right");
            surroundingmines += 1;
        }
        // System.out.println(surroundingmines);
        return (char)(surroundingmines+'0');
    }
}


