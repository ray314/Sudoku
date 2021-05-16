/**
 * 
 */
package testing;

import java.util.Random;

import sudoku.AbstractSudokuSolver;

/**
 * @author Justin Yeung
 * ID: 16946889
 */
public class NaiveSudokuSolver extends AbstractSudokuSolver{
	
	private Random rand = new Random();
	private int[][] board;
	SolverThread[] thread = new SolverThread[3];
    
    public NaiveSudokuSolver(int size, int side) {
		super(size, side);
		// TODO Auto-generated constructor stub
	}
    
    public void removeKDigits(int[][] mat, int N, int K) 
    { 
        int count = K; 
        
        while (count != 0) 
        { 
            int cellId = rand.nextInt(N); 
            int cellId2 = rand.nextInt(N); 
            
            
             //System.out.println(cellId);
             //System.out.println(cellId2);
            // extract coordinates i  and j 
            int i = cellId; 
            int j = cellId2; 
            //if (j != 0) 
            //    j = j - 1; 
  
            // System.out.println(i+" "+j); 
            if (mat[i][j] != 0) 
            { 
                count--; 
                mat[i][j] = 0; 
            } 
        } 
    } 
    // Inner class for multi threading
    private class SolverThread extends Thread
    {
    	public void run()
    	{
    		solve(board, 0);
    	}
    }
    
	private void solve(int[][] board, int ind){
		
		//printSolution(board);
        if(ind == S*S){
            //System.out.println("Solution via naive algorithm found: ");
            // Remove Randomly K digits to make game
            
            //removeKDigits(board, N, K);
        	printSolution(board);
            addBoard(board);
            //System.out.println();
        }
        else{
            int row = ind / S;
            int col = ind % S;
            //printSolution(board);
            if (boardAdded == true)
            {
            	return;
            }
            
            // Advance forward on cells that are prefilled
            if(board[row][col] != 0) solve(board, ind+1);
            else{
                // we are positioned on something we need to fill in. Try all possibilities
                for(int i = 1; i <= S; i++){
                    if(consistent(board, row, col, i)){
                        board[row][col] = i;
                        solve(board,ind+1);
                        board[row][col] = 0; // unmake move
                        
                    }   
                }
            }
            // no solution
        }

    }
      
    // Check whether putting "c" into index "ind" leaves the board in a consistent state
    private boolean consistent(int[][] board, int row, int col, int c) {        
        // check columns/rows
        for(int i = 0; i < S; i++){
            if(board[row][i] == c) return false;
            if(board[i][col] == c) return false;
        }
        
        // Check subsquare
        
        int rowStart = row - row % side; 
        int colStart = col - col % side;
        
        for(int m = 0; m < side; m++){
            for(int k = 0; k < side; k++){
                if(board[rowStart + k][colStart + m] == c) return false;
            }
        }
        return true;
    }

    protected boolean threadAlive() // Check if all threads are alive
    {
    	return (thread[0].isAlive() || thread[1].isAlive() || thread[2].isAlive());
    }
   
    protected void runSolver(int[][] sudoku){
    	this.board = sudoku;
    	thread[0] = new SolverThread();
    	thread[1] = new SolverThread();
    	thread[2] = new SolverThread();
        thread[0].start();
        thread[1].start();
        thread[2].start();
    }

}
