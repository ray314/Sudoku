/**
 * 
 */
package sudoku;

import java.util.Random;

/**
 * @author Justin Yeung
 *
 */
public class RemoveKDigits {

	private Random rand = new Random();
	
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
	
}
