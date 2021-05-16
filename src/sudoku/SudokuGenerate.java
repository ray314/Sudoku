/**
 * 
 */
package sudoku;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.ThreadLocalRandom;

/*
 * @author Justin Yeung
 * ID: 16946889
 */

public class SudokuGenerate
{ 
    int[] mat[]; // board
    int[] numbers; // numbers to fill the diagonal board
    int N; // number of columns/rows. 
    int values; // number of values
    int SRN; // square root of N 
    int K; // No. Of missing digits 
    int[] solution;
    Random rand = new Random();
  
    // Constructor 
    public SudokuGenerate(int N) 
    { 
        this.N = N; 

  
        // Compute N divided by 3
        Double SRNd = N/3.0; 
        SRN = SRNd.intValue(); 
        //System.out.println(SRN);
  
        values = N*N;
        mat = new int[N][N]; 
        numbers = new int[values/9];
    } 
  
    // Sudoku Generator 
    public void fillValues() 
    { 
        // Fill the diagonal of SRN x SRN matrices 
        fillDiagonal(); 
        
         
    } 
  
    // Fill the diagonal SRN number of SRN x SRN matrices 
    public void fillDiagonal() 
    { 
  
        for (int i = 0; i<N; i=i+SRN) 
        {
            // for diagonal box, start coordinates->i==j 
        	for (int i2 = 0; i2<values/N; i2++)
        	{
        		numbers[i2] = i2+1; // add all numbers to array
        		//System.out.print(numbers[i2]);
        	}
        	shuffleArray(numbers);
            fillBox(i, i); 
        }
    } 
    
    // Returns false if given 3 x 3 block contains num. 
    boolean unUsedInBox(int rowStart, int colStart, int num) 
    { 
        for (int i = 0; i<SRN; i++) 
            for (int j = 0; j<SRN; j++) 
                if (mat[rowStart+i][colStart+j]==num) 
                {
                    return false; 
                }
                	
  
        return true; 
    } 
  
    // Fill a 3 x 3 matrix. 
    void fillBox(int row,int col) 
    { 
        int num; 
        int count = 0;
        int subtract = 0; // If it is a 12x12 board, subtract by 1
        if (N > 9)
        {
        	subtract = 1;
        }
        for (int i=0; i<SRN - subtract; i++) 
        { 
            for (int j=0; j<SRN; j++) 
            { 
                do
                { 
                    num = numbers[count]; 
                	count++; 
                } 
                while (!unUsedInBox(row, col, num)); 
  
                mat[row+i][col+j] = num; 
            } 
        } 
    } 
 
 
 // Implementing Fisher–Yates shuffle
    static void shuffleArray(int[] ar)
    {
      // If running on Java 6 or older, use new Random() on RHS here
      Random rnd = ThreadLocalRandom.current();
      for (int i = ar.length - 1; i > 0; i--)
      {
        int index = rnd.nextInt(i + 1);
        // Simple swap
        int a = ar[index];
        ar[index] = ar[i];
        ar[i] = a;
      }
    }
    
    // Driver code 
    public int[][] generate(int difficulty) 
    { 
        K = 0; // 10 - Easy, 20 - Normal, 30 - Hard, 50 - Very Hard
        //SudokuGenerate sudoku = new SudokuGenerate(N, K); 
        RemoveKDigits remove = new RemoveKDigits();
        SudokuDLX Solver = new SudokuDLX(N, N/3);
        
        fillValues(); // Fill diagonal values of the board
        //AbstractSudokuSolver.printSolution(sudoku.mat);; 
        
        //System.out.println("----------------------------------------------------");
        
        Solver.runSolver(mat); // Generate the board
        //while (solver.threadAlive())
        //{
        //	// While loop which halts the current thread till solver thread dies
        //}
        
        switch (difficulty) // Select difficulty based on radio button
        {
        case 1:
        	K = (int) ((N*N)*0.1); // 10% of digits are removed
        	break;
        case 2:
        	K = (int) ((N*N)*0.25); // 25%
        	break;
        case 3:
        	K = (int) ((N*N)*0.35); // 35%
        	break;
        case 4:
        	K = (int) ((N*N)*0.6); // 60%
        	break;
        }
        
        int[][] board = Solver.dlx.board; // Get the board from DancingLinks class after generating.
        solution = new int[N*N];
        int count = 0;
        
        for (int i = 0; i < N; i++) // Manually copy array
        {
        	for (int i2 = 0; i2 < N; i2++)
        	{
        		solution[count] = board[i][i2];
        		count++;
        	}
        }
        remove.removeKDigits(board, N, K);
        
    	//System.out.println(AbstractSudokuSolver.validateSudoku(board));
    	
    	//AbstractSudokuSolver.printSolution(ReadFile.ReadBoard(N));
    	return board;
    } 
} 