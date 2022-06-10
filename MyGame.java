import java.util.Scanner;
import java.lang.Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// This is the MinesweeperPlayer which contains the main function and test function for Unit Testing.
// The main function in turn asks for input from the user and instantiates the MineSweeper class. It
// then calls methods on the MineSweeper instance to add the mines, count the mines and display the 
// board. Later this will be extended to call more classes to actually play the game of Minesweeper.


public class MyGame{  

    public static void main(String[] args)        throws IOException    {

        /*int[][] hi = {{0,0,0,0,0,0,0,0,0,0},
                      {0,0,0,0,0,0,0,0,0,0},
                      {0,0,0,0,0,0,0,0,0,0},
                      {0,0,0,0,0,0,0,0,0,0},
                      {0,0,0,0,0,0,0,0,0,0},
                      {0,0,0,0,0,0,0,0,0,0},
                      {0,0,0,0,0,0,0,0,0,0},
                      {0,0,0,0,0,0,0,0,0,0}};*/
        
        hi blob = new hi();    
        blob.getInput();      
        blob.coordinateIn();
        
    }




    

    /*void play(){
        System.out.println("You are playing Minesweeper.");
        boolean gameOverWin =false;
        //boolean gameOverLose =false;
        while(true){
            coordinateIn();
            for(int i=0;i<board.length;i++){
                for(int j=0;j<board[i].length;j++){
                    if(open[i][j]==1 || board[i][j]== '*'){

                    }
                }
            }
        }
    }*/
}






// This is the class which implements the MineSweeper Game. It contains all
// the member variables and associated member functions that act on that data.
// Member Variables:
// board: two dimensional character array that holds the minesweeper board at any point 
//        of the game. `*` represents a mine at that location. A number represents the
//        total number of neighboring mines around that cell out of the 8 neighboring
//        positions.
// m:     number of rows in the minesweeper board
// n:     number of columns in the minesweeper board
// k:     number of mines to generate in the minesweeper board
//
// Member Functions:
// MineSweeper:    Constructor to initialize the MineSweeper board
// addMines:       Generates k mines and adds them at random locations in the MineSweeper board
// addMineCount:   Counts the number of neighboring mines for each cell and adds that count to 
//                 the board. It calls a helper function countMines(x,y), which returns the total
//                 number of mines for location x,y.
// displayBoard:   Displays the current layout of the Minesweeper board, includings the mines and
//                 their counts.

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
    char[][] addMineCount() {
        for (int i = 0; i < m; i++){ 
            for (int j = 0; j < n; j++){ 
                if (board[i][j] != '*'){
                    board[i][j] = countMines(i,j);
                }   
            }
        }
        return board;

    }

    // return the board, only used for testing purposes
    char [][] getBoard(){
        return board;
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






//The recursive function used in the actual game
class Expandfunc{
    //int[][] hi = new int[10][10];
    //m and n are the x and y of the inputted array. Open representa the answer and what is outputted. 
    //Visited keeps track of coordinated that have been visited, so calc does not need to be called again
    int m;
    int n;
    int[][] visited;
    int[][] open;
    char[][] arr;
    public Expandfunc(char[][] arrr){
        //initialising stuff
        arr = arrr;
        m = arr.length;
        n = arr[0].length;
        visited = new int[m][n];
        open = new int[m][n];
        /*
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++)
                System.out.println(arr[i][j]);
        }
        */
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                visited[i][j]=0;
                open[i][j]=0;
            }
        } 
    }
//recursive function 
    public int[][] calc(int x, int y){  
        //System.out.println(arr[x][y]);
        if(arr[x][y]=='0'){
            
            visited[x][y]=1;
            open[x][y]=1;

            // top
            if ((y-1) >= 0 && visited[x][y-1]==0){
                calc(x,y-1);
            }
            //bottom
            if ((y+1) < n && visited[x][y+1]==0){
                calc(x,y+1);
            }
            //right
            if ((x+1) < m && visited[x+1][y]==0){
                calc(x+1,y);
            }
            //left
            if ((x-1) >= 0 && visited[x-1][y]==0){
                calc(x-1,y);
            }
    
            //top-left
            if ((x-1) >= 0 && (y-1) >= 0 && visited[x-1][y-1]==0){
                calc(x-1,y-1);
            }
            //top-right
            if ((x+1) < m &&  (y-1) >= 0 && visited[x+1][y-1]==0){
                calc(x+1,y-1);
            }
            //bottom-left
            if ((x-1) >= 0 && (y+1) < n && visited[x-1][y+1]==0){
                calc(x-1,y+1);
            }
            //bottom-right
            if ((y+1) < n && (x+1) < m && visited[x+1][y+1]==0){
                calc(x+1,y+1);
            }
        }else{
            open[x][y]=1;
            return open;
        }
        return open;
    }

}

class hi{
    char [][] board;
    void getInput(){
        // THIS SECTION ASKS USER TO INPUT THE DIMENSIONS OF THE MINESWEEPER BOARD
        // ATTACHED IS SAMPLE INPUT SCREEN. NOTE THE INPUT IS REQUIRED ON THE NEXT
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows you want");
        int m = scanner.nextInt();
        System.out.println("Enter the number of columns you want");
        int n = scanner.nextInt();
        System.out.println("Enter the number of mines you want");
        int k = scanner.nextInt();
        scanner.close();

        MineSweeper game = new MineSweeper(m,n,k);
        
        game.addMines();
        board = game.addMineCount();
        
    }

    // Display the board containing mines and their counts  as a m by n grid
    void displayBoard(char[][] board, int[][] toopen){
        for (int i = 0; i < board.length; i++){ 
            for (int j = 0; j < board[0].length; j++){
                if(toopen[i][j]==1){
                    System.out.print( board[i][j]+ "  ");
                }else{
                    System.out.print("+  ");
                }  
            } 
            System.out.println("\n");
        }
    }

    void coordinateIn()        throws IOException
    {
        Expandfunc bob = new Expandfunc(board);
        System.out.println("You are playing Minesweeper");
        boolean gameOverWin =false;
        boolean gameOverLose =false;
        //Scanner sc = new Scanner(System.in);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(!gameOverWin || !gameOverLose){
            
            /*System.out.print("Enter the x coordinate of the point you want to open");
            int x , y;
            
            x = Integer.parseInt(sc.nextLine());
        
            System.out.println("hello world2");        
        
            System.out.print("Enter the y coordinate of the point you want to open");
            y = Integer.parseInt(sc.nextLine());
        
            System.out.println("hello world1");*/
            
            int x , y;  
            System.out.print("Enter the x coordinate of the point you want to open");
            x = Integer.parseInt(reader.readLine());
            System.out.print("Enter the y coordinate of the point you want to open");
            y = Integer.parseInt(reader.readLine());

            
            //did they hit a mine?
            if(board[x][y]=='*'){
                gameOverLose = true;
            }
            //Printing out the answer as an array
            int[][] opened = bob.calc(x,y);
            displayBoard(board, opened);
            //break;
            for(int i=0;i<board.length;i++){
                for(int j=0;j<board[i].length;j++){
                    if(opened[i][j]==1 || board[i][j]== '*'){
                        gameOverWin= true;
                    }
                }
            }
        }
        //sc.close();
        if(gameOverWin){
            System.out.println("You Won!!");
        }
        if(gameOverLose){
            System.out.println("You Lost!!!");
        }
        
    }

}