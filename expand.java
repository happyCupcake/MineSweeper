import java.util.ArrayList;

public class expand {
    public static void main(String[] args) { 
        char[][] stuff = {
        {'0',  '0',  '0',  '0',  '0',  '0',  '0',  '0',  '0',  '0'},
        {'1',  '1',  '0',  '0',  '0',  '0',  '0',  '1',  '1',  '1'},
        {'*',  '1',  '1',  '1',  '2',  '1',  '1',  '1',  '*',  '1'},
        {'1',  '1',  '1',  '*',  '2',  '*',  '1',  '1',  '1',  '1'} ,
        {'1',  '0',  '1',  '1',  '2',  '1',  '2',  '1',  '1',  '0' }, 

        {'1',  '2',  '2',  '3',  '2',  '1',  '1',  '*',  '1',  '0'  },

        {'*', '3',  '*',  '*',  '*',  '1',  '1', '1',  '1',  '0'  },

        {'1',  '3',  '*',  '4',  '2',  '1',  '0',  '0',  '0',  '0' },
        };
        Expandfunc bob = new Expandfunc(stuff);

        int[][] blob = bob.calc(4,1);
        for (int i = 0; i < blob.length; i++){ 
            for (int j = 0; j < blob[0].length; j++){
                System.out.print( blob[i][j]+ "  ");
            }
            System.out.println("\n");
        }
    }
}

class Expandfunc{
    //int[][] hi = new int[10][10];
    int m;
    int n;
    int[][] visited;
    int[][] open;
    char[][] arr;
    public Expandfunc(char[][] arrr){
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

    public  int[][] calc(int x, int y){  
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